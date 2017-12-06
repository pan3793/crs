package pc.crs.auth.server.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class AuthController {

    @GetMapping("token")
    fun checkToken(token: String): Pair<Boolean, String> {
        return Pair(true, "Token is valid.")
    }
}