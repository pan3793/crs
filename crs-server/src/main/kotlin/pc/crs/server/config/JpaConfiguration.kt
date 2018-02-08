package pc.crs.server.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.CurrentDateTimeProvider
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import pc.crs.auth.client.context.AuthContextHolder
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "dateTimeProvider")
@EntityScan(basePackages = ["pc.crs.domain"])
class JpaConfiguration {
    @Bean
    fun auditorAware() = AuditorAware<String> { Optional.of(AuthContextHolder.getUserInfo()?.name ?: "anonymous") }

    @Bean
    fun dateTimeProvider() = CurrentDateTimeProvider.INSTANCE
}