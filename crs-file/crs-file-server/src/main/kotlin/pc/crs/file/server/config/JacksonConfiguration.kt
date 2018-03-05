package pc.crs.file.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pc.crs.common.config.Jackson2ObjectMapperBuilderCustomizerImpl

@Configuration
class JacksonConfiguration {

    @Bean
    fun jackson2ObjectMapperBuilderCustomizer() = Jackson2ObjectMapperBuilderCustomizerImpl()
}