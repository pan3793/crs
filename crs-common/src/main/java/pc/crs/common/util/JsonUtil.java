package pc.crs.common.util;

import com.alibaba.fastjson.JSON;

/**
 * @author pancheng
 */
public class JsonUtil {

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public static String toJsonString(Object object, boolean prettyFormat) {
        return JSON.toJSONString(object, prettyFormat);
    }

    public static String formatJson(String origin) {
        return JSON.toJSONString(JSON.parse(origin), true);
    }
}
