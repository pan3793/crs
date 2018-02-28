package pc.crs.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackages = ["pc.crs.server", "pc.crs.auth.client"])
@EnableFeignClients(basePackages = ["pc.crs.server", "pc.crs.auth.client"])
@EnableEurekaClient
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}