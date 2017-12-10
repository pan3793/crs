package pc.crs.auth.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer

@Configuration
@EnableRedisRepositories(basePackages = ["pc.crs.auth.domain.redis"])
class RedisConfiguration {
    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory) = RedisTemplate<String, Any>().apply {
        connectionFactory = redisConnectionFactory
        valueSerializer = Jackson2JsonRedisSerializer(Any::class.java).apply {
            setObjectMapper(ObjectMapper().apply {
                setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
            })
        }
        keySerializer = StringRedisSerializer()
        afterPropertiesSet()
    }
}