package pc.crs.auth.server.config;

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.AntPathMatcher
import org.springframework.util.PathMatcher

@Configuration
class PathMatcherConfiguration {
    @Bean
    fun pathMatcher(): PathMatcher = AntPathMatcher()
}