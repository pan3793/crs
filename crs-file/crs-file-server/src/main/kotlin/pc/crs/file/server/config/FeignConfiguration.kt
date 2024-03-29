package pc.crs.file.server.config

import feign.codec.Decoder
import feign.optionals.OptionalDecoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pc.crs.common.config.CustomizedResponseEntityDecoder

@Configuration
class FeignConfiguration(@Autowired val messageConverters: ObjectFactory<HttpMessageConverters>) {

    @Bean
    fun feignDecoder(): Decoder {
        return OptionalDecoder(CustomizedResponseEntityDecoder(SpringDecoder(this.messageConverters)))
    }
}

