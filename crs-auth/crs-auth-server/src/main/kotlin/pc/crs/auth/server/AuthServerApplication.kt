package pc.crs.auth.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackages = ["pc.crs.auth.server", "pc.crs.common.aop"])
@EnableFeignClients(basePackages = ["pc.crs.auth.server", "pc.crs.auth.client"])
@EnableEurekaClient
class AuthServerApplication

fun main(args: Array<String>) {
    runApplication<AuthServerApplication>(*args)
}
