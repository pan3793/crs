package pc.crs.auth.client

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AuthClientApplication

fun main(args: Array<String>) {
    SpringApplication.run(AuthClientApplication::class.java, *args)
}
