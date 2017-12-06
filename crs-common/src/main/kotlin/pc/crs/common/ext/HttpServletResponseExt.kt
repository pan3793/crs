package pc.crs.common.ext

import com.alibaba.fastjson.JSON
import org.springframework.http.MediaType
import javax.servlet.http.HttpServletResponse

fun HttpServletResponse.writeJSON(httpCode: Int, any: Any?) {

    this.characterEncoding = "UTF-8"
    this.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE
    this.status = httpCode

    this.writer.append(JSON.toJSONString(any))
}