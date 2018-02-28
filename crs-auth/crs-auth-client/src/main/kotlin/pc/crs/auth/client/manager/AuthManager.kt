package pc.crs.auth.client.manager

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import pc.crs.auth.common.dto.UserInfo

@FeignClient(name = "crs-auth-server", path = "/api/client")
interface AuthManager {

    @PostMapping("/checkAnonymous")
    fun checkAnonymous(@RequestParam url: String): Boolean

    @PostMapping("/checkPermission")
    fun checkPermission(@RequestParam url: String, @RequestParam token: String): Triple<Int, String, UserInfo?>

    @PostMapping("/checkToken")
    fun checkToken(@RequestParam token: String): Pair<Boolean, UserInfo?>

    @PostMapping("/login")
    fun login(@RequestParam loginName: String, @RequestParam password: String)
            : Pair<Boolean, UserInfo?>

    @PostMapping("/logout")
    fun logout(@RequestParam token: String)
}