package pc.crs.auth.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.feign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AuthClientApplication

fun main(args: Array<String>) {
    runApplication<AuthClientApplication>(*args)
}
