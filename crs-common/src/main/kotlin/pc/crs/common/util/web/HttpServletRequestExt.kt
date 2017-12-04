package pc.crs.common.util.web

import com.alibaba.fastjson.JSONObject
import javax.servlet.http.HttpServletRequest

fun HttpServletRequest.getPostJSONObject(): JSONObject =
        if (this.method == "POST") JSONObject.parseObject(this.reader.readText()) else JSONObject()
