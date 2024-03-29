package pc.crs.file.server.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.CurrentDateTimeProvider
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import pc.crs.auth.common.context.AuthContextHolder
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "dateTimeProvider")
@EntityScan(basePackages = ["pc.crs.file.domain"])
class JpaConfiguration {
    @Bean
    fun auditorAware() = AuditorAware<String> { Optional.of(AuthContextHolder.getUserInfo()?.name ?: "anonymous") }

    @Bean
    fun dateTimeProvider() = CurrentDateTimeProvider.INSTANCE
}