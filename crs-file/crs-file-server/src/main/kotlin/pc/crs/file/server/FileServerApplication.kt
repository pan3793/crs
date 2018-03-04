package pc.crs.file.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["pc.crs.file.server"])
class FileServerApplication

fun main(args: Array<String>) {
    runApplication<FileServerApplication>(*args)
}