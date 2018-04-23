package pc.crs.auth.server.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pc.crs.auth.server.interceptor.AuthInterceptor

@Configuration
class WebMvcConfiguration(@Autowired val authInterceptor: AuthInterceptor)
    : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
    }
}