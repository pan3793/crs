package pc.crs.auth.client.controller

import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pc.crs.auth.client.manager.AuthManager
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.failureRestResult
import pc.crs.common.bean.successRestResult

@RestController
@RequestMapping("/api/auth")
class AuthController : ApplicationContextAware {

    // 暂时绕开当前版本 Feign Bug
    lateinit var appCtx: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appCtx = applicationContext
    }

    val authManager: AuthManager by lazy {
        appCtx.getBean<AuthManager>(AuthManager::javaClass)
    }

    @PostMapping("/login")
    fun login(@RequestParam loginName: String, @RequestParam password: String): RestResult {
        val (success, userInfo) = authManager.login(loginName, password)

        return if (success) successRestResult("登录成功", userInfo!!)
        else failureRestResult("用户名或密码错误")
    }

    @PostMapping("/logout")
    fun logout(@RequestParam token: String): RestResult {
        authManager.logout(token)
        return successRestResult("注销成功")
    }
}