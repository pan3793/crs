package pc.crs.forum.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackages = ["pc.crs.forum.server", "pc.crs.auth.client", "pc.crs.common.aop"])
@EnableFeignClients(basePackages = ["pc.crs.forum.server", "pc.crs.auth.client"])
@EnableEurekaClient
class ForumServerApplication

fun main(args: Array<String>) {
    runApplication<ForumServerApplication>(*args)
}
