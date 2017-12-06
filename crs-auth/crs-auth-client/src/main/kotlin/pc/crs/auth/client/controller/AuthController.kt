package pc.crs.auth.client.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pc.crs.auth.client.service.AuthService

@RestController
@RequestMapping("/")
class AuthController(@Autowired val authService: AuthService) {

    @GetMapping("token")
    fun checkToken(@RequestParam token: String) = authService.checkToken(token)
}