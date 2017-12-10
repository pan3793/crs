package pc.crs.auth.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pc.crs.auth.common.dto.UserInfo
import pc.crs.auth.server.service.AclService
import pc.crs.auth.server.service.TokenService

@RestController
@RequestMapping("/api/client")
class AuthClientController(@Autowired val aclService: AclService,
                           @Autowired val tokenService: TokenService) {

    @PostMapping("/checkAnonymous")
    fun checkAnonymous(@RequestParam clientId: Long, @RequestParam url: String): Boolean {
        return aclService.checkAnonymous(clientId, url)
    }

    @PostMapping("/checkPermission")
    fun checkPermission(@RequestParam clientId: Long, @RequestParam token: String, @RequestParam url: String): Boolean {
        return aclService.checkPermission(clientId, token, url)
    }

    @PostMapping("/checkToken")
    fun checkToken(@RequestParam clientId: Long, @RequestParam token: String): Pair<Boolean, UserInfo?> {
        return tokenService.checkToken(clientId, token)
    }

    @PostMapping("/login")
    fun login(@RequestParam clientId: Long, @RequestParam loginName: String, @RequestParam password: String)
            : Pair<Boolean, UserInfo?> {
        return tokenService.login(clientId, loginName, password)
    }

    @PostMapping("/logout")
    fun logout(@RequestParam clientId: Long, @RequestParam token: String) {
        tokenService.logout(clientId, token)
    }
}