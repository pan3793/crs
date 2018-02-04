package pc.crs.auth.client.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pc.crs.auth.client.service.cloud.AuthService
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.failureRestResult
import pc.crs.common.bean.successRestResult

@RestController
@RequestMapping("/api/auth")
class AuthController(@Autowired val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestParam loginName: String, @RequestParam password: String): RestResult {
        val (success, userInfo) = authService.login(loginName, password)

        return if (success) successRestResult("登录成功", userInfo!!)
        else failureRestResult("用户名或密码错误")
    }

    @PostMapping("/logout")
    fun logout(@RequestParam token: String): RestResult {
        authService.logout(token)
        return successRestResult("注销成功")
    }
}