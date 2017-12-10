package pc.crs.auth.client.service.cloud

import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import pc.crs.auth.common.dto.UserInfo

@FeignClient(name = "crs-auth-server", path = "/api/client")
interface AuthService {

    @PostMapping("/checkAnonymous")
    fun checkAnonymous(@RequestParam clientId: Long, @RequestParam url: String): Boolean

    @PostMapping("/checkPermission")
    fun checkPermission(@RequestParam clientId: Long, @RequestParam url: String, @RequestParam token: String): Boolean

    @PostMapping("/checkToken")
    fun checkToken(@RequestParam clientId: Long, @RequestParam token: String): Pair<Boolean, UserInfo?>

    @PostMapping("/login")
    fun login(@RequestParam clientId: Long, @RequestParam loginName: String, @RequestParam password: String)
            : Pair<Boolean, UserInfo?>

    @PostMapping("/logout")
    fun logout(@RequestParam clientId: Long, @RequestParam token: String)
}