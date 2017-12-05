package pc.crs.auth.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthClientApplication

fun main(args: Array<String>) {
    runApplication<AuthClientApplication>(*args)
}
