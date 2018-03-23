package pc.crs.common.config

import com.alibaba.fastjson.JSON
import feign.Response
import feign.codec.DecodeException
import feign.codec.Decoder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder
import pc.crs.common.bean.RestResult
import pc.crs.common.constant.SUCCESS_CODE
import java.lang.reflect.Type

class CustomizedResponseEntityDecoder(decoder: Decoder)
    : ResponseEntityDecoder(decoder) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun decode(response: Response, type: Type): Any {
        val restResult = super.decode(response, RestResult::class.java) as RestResult?
        if (restResult?.code == SUCCESS_CODE) {
            // Jackson居然无法识别Kotlin嵌套可空泛型，还是用FastJson吧
            return JSON.parseObject(JSON.toJSONString(restResult.data), type)
        } else {
            logger.error("Feign请求异常，url={}，httpCode={}, reason={}, {}",
                    response.request().url(), response.status(), response.reason(), restResult)
            throw DecodeException(restResult?.msg ?: response.reason())
        }
    }
}