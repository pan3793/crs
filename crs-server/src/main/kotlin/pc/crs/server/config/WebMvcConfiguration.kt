package pc.crs.server.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import pc.crs.auth.client.interceptor.AuthInterceptor

@Configuration
class WebMvcConfiguration : WebMvcConfigurer {
    /**
     * 避免IoC循环依赖，不使用构造器注入
     */
    @Autowired lateinit var authInterceptor: AuthInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
//        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
    }
}