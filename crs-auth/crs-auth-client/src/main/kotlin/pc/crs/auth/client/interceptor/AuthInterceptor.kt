package pc.crs.auth.client.interceptor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import pc.crs.auth.client.manager.AuthManager
import pc.crs.auth.common.context.AuthContextHolder
import pc.crs.auth.common.dto.UserInfo
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.noPermissionRestResult
import pc.crs.common.constant.NO_PERMISSION_CODE
import pc.crs.common.constant.SUCCESS_CODE
import pc.crs.common.constant.TOKEN_INVALID_CODE
import pc.crs.common.ext.getPostJSONObject
import pc.crs.common.ext.writeJSON
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor : HandlerInterceptorAdapter(), ApplicationContextAware {

    // 暂时绕开当前版本 Feign Bug
    lateinit var appCtx: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appCtx = applicationContext
    }

    val authManager: AuthManager by lazy {
        appCtx.getBean<AuthManager>(AuthManager::javaClass)
    }

    companion object {
        const val CRS_TOKEN = "CRS-TOKEN"
    }

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        val uri: String = request.requestURI
        logger.info("访问路径 uri={}", uri)

        if (authManager.checkAnonymous(uri)) {
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
            val (code, message, userInfo) = authManager.checkPermission(token, uri)
            logger.info(message)
            when (code) {
                SUCCESS_CODE -> {
                    userInfo?.let {
                        logger.info("获取到 userInfo={}", userInfo)
                        AuthContextHolder.setUserInfo(userInfo)
                        return true
                    }
                }
                TOKEN_INVALID_CODE -> {
                    response.writeJSON(RestResult(TOKEN_INVALID_CODE, "Token 无效"))
                    return false
                }
                NO_PERMISSION_CODE -> {
                    response.writeJSON(noPermissionRestResult())
                    return false
                }
                else -> {
                    return false
                }
            }
        }

        logger.error("获取不到 token")
        response.writeJSON(RestResult(TOKEN_INVALID_CODE, "Token 无效"))
        return false
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any?, ex: Exception?) {
        AuthContextHolder.clean()
    }
}