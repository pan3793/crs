package pc.crs.auth.client.interceptor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import pc.crs.auth.client.context.AuthContextHolder
import pc.crs.auth.client.service.cloud.AuthService
import pc.crs.auth.common.dto.UserInfo
import pc.crs.common.bean.failureRestResult
import pc.crs.common.ext.getPostJSONObject
import pc.crs.common.ext.writeJSON
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor(@Autowired val authService: AuthService) : HandlerInterceptorAdapter() {

    companion object {
        const val CRS_TOKEN = "CRS-TOKEN"
    }

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        val uri: String = request.requestURI
        logger.info("访问路径 uri={}", uri)

        if (authService.checkAnonymous(uri)) {
            logger.info("uri={} 允许匿名访问", uri)
            AuthContextHolder.setUserInfo(UserInfo(name = "anonymous"))
            return true
        }

        logger.info("uri={} 需要登录，开始获取token", uri)
        val token: String? = request.getHeader(CRS_TOKEN).takeUnless { it.isNullOrBlank() }
                ?: request.getParameter("token").takeUnless { it.isNullOrBlank() }
                ?: request.getPostJSONObject().getString("token").takeUnless { it.isNullOrBlank() }

        token?.let {
            logger.info("获取到 token={}，开始权限检查", token)
            val (pass, message, userInfo) = authService.checkPermission(token, uri)
            logger.info(message)
            if (pass) {
                userInfo?.let {
                    logger.info("获取到 userInfo={}", userInfo)
                    AuthContextHolder.setUserInfo(userInfo)
                    return true
                }
            }
            response.writeJSON(401, failureRestResult(message))
            return false
        }

        logger.error("获取不到 token")
        response.writeJSON(401, failureRestResult("Token not found."))
        return false
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any?, ex: Exception?) {
        AuthContextHolder.clean()
    }
}