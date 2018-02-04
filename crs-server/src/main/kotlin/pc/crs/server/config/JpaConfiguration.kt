package pc.crs.server.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = ["pc.crs.domain"])
class JpaConfiguration {
    @Bean
    fun auditorAware() = object : AuditorAware<Long> {
        override fun getCurrentAuditor(): Optional<Long> {
            return Optional.empty()
        }
    }
}