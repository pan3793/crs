package pc.crs.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.feign.EnableFeignClients

@SpringBootApplication(scanBasePackages = ["pc.crs.server", "pc.crs.auth.client"])
@EnableFeignClients(basePackages = ["pc.crs.server", "pc.crs.auth.client"])
@EnableEurekaClient
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}