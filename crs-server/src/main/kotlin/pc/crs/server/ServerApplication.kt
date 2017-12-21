package pc.crs.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}