package pc.crs.common.ext

import com.alibaba.fastjson.JSONObject
import javax.servlet.http.HttpServletRequest

fun HttpServletRequest.getPostJSONObject(): JSONObject =
        if (this.method == "POST") JSONObject.parseObject(this.reader.readText()) else JSONObject()
