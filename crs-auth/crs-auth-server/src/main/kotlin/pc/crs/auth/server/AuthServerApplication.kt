package pc.crs.auth.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AuthServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(AuthServerApplication::class.java, *args)
}
