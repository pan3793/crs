package pc.crs.auth.client.service

import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("crs-auth-server")
interface AuthService {
    @GetMapping("/token")
    fun checkToken(@RequestParam token: String): Pair<Boolean, String>
}