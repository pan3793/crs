package pc.crs.auth.client.interceptor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.PathMatcher
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import pc.crs.auth.client.service.TokenService
import pc.crs.common.bean.failureRestResult
import pc.crs.common.ext.getPostJSONObject
import pc.crs.common.ext.writeJSON
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor(@Autowired val pathMatcher: PathMatcher,
                      @Autowired val tokenService: TokenService) : HandlerInterceptorAdapter() {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    companion object {
        val CRS_TOKEN = "CRS-TOKEN"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {

        // 分别尝试从HttpHeader、RequestParameter、RequestBody(Json)中获取token
        val token: String? = request.getHeader(CRS_TOKEN).takeUnless { it.isNullOrBlank() }
                ?: request.getParameter("token").takeUnless { it.isNullOrBlank() }
                ?: request.getPostJSONObject().getString("token").takeUnless { it.isNullOrBlank() }

        token?.let {
            val (isValid, msg) = tokenService.checkToken(token)
            return if (isValid) {
                logger.info("token={} 验证通过", token)
                request.setAttribute("token", token)
                true
            } else {
                logger.info("token={} 验证失败", token)
                response.writeJSON(401, failureRestResult(msg))
                false
            }
        }

        logger.info("获取不到 token")
        response.writeJSON(401, failureRestResult("Token not found."))
        return false
    }
}