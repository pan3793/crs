package pc.crs.common.config

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.DecodeException
import feign.codec.Decoder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder
import pc.crs.common.bean.RestResult
import pc.crs.common.constant.SUCCESS_CODE
import java.lang.reflect.Type

class CustomizedResponseEntityDecoder(decoder: Decoder, private val objectMapper: ObjectMapper)
    : ResponseEntityDecoder(decoder) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun decode(response: Response, type: Type): Any {
        val restResult = super.decode(response, RestResult::class.java) as RestResult?
        if (restResult?.code == SUCCESS_CODE) {
            // 虽然这样效率低下，但是暂时找不到更好的办法了
            return objectMapper.readValue(objectMapper.writeValueAsString(restResult.data),
                    objectMapper.constructType(type))
            // FastJson也可以支持泛型嵌套
//                return JSON.parseObject(JSON.toJSONString(restResult.data), type)
        } else {
            logger.error("Feign请求异常，url={}，httpCode={}, reason={}, {}",
                    response.request().url(), response.status(), response.reason(), restResult)
            throw DecodeException(restResult?.msg ?: response.reason())
        }
    }
}