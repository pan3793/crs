package pc.crs.forum.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class ForumServerApplication

fun main(args: Array<String>) {
    runApplication<ForumServerApplication>(*args)
}
