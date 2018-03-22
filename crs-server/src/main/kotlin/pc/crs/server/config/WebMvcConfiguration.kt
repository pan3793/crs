package pc.crs.server.config

import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pc.crs.auth.client.interceptor.AuthInterceptor

@Configuration
class WebMvcConfiguration : WebMvcConfigurer, ApplicationContextAware {

    // 暂时绕开当前版本 Feign Bug
    lateinit var appCtx: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appCtx = applicationContext
    }

    val authInterceptor: AuthInterceptor by lazy {
        appCtx.getBean<AuthInterceptor>(AuthInterceptor::javaClass)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
//        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
    }
}