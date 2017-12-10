package pc.crs.auth.client.interceptor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import pc.crs.auth.client.service.cloud.AuthService
import pc.crs.common.bean.failureRestResult
import pc.crs.common.ext.getPostJSONObject
import pc.crs.common.ext.logger
import pc.crs.common.ext.writeJSON
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor(@Autowired val authService: AuthService,
                      @Value("\${crs.auth.clientId}") val clientId: Long) : HandlerInterceptorAdapter() {

    companion object {
        val CRS_TOKEN = "CRS-TOKEN"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        val uri: String = request.requestURI
        logger.info("访问路径 uri={}", uri)

        if (authService.checkAnonymous(clientId, uri)) {
            logger.info("uri={} 允许匿名访问", uri)
            return true
        }

        logger.info("uri={} 需要登录，开始获取token", uri)
        val token: String? = request.getHeader(CRS_TOKEN).takeUnless { it.isNullOrBlank() }
                ?: request.getParameter("token").takeUnless { it.isNullOrBlank() }
                ?: request.getPostJSONObject().getString("token").takeUnless { it.isNullOrBlank() }

        token?.let {
            logger.info("获取到 token={}", token)
            val (tokenExist, userInfo) = authService.checkToken(clientId, token)
            if (tokenExist) {
                logger.info("token={} 验证通过", token)
                userInfo?.let {
                    logger.info("获取到 userInfo={}", userInfo)
                    request.setAttribute("userInfo", userInfo)
                    return true
                }
                logger.error("无法获取 userInfo")
                response.writeJSON(401, failureRestResult("获取用户信息失败"))
                return false
            } else {
                logger.error("token={} 验证失败", token)
                response.writeJSON(401, failureRestResult("Token无效"))
                return false
            }
        }

        logger.error("获取不到 token")
        response.writeJSON(401, failureRestResult("Token not found."))
        return false
    }
}