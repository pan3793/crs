package pc.crs.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author pancheng
 */
public class JsonUtil {

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
    }

    public static String toJsonString(Object object, boolean prettyFormat) {
        if (prettyFormat)
            return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat);
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
    }

    public static String formatJson(String origin) {
        return JSON.toJSONString(JSON.parse(origin), SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat);
    }
}
