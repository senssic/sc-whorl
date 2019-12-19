package sc.whorl.system.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * FastJson工具包
 */
public class FastJsonUtil {
    /**
     * 将string转化为序列化的json字符串
     */
    public static Object textToJson(String text) {
        Object objectJson = JSON.parse(text);
        return objectJson;
    }

    /**
     * json字符串转化为map
     */
    public static Map stringToCollect(String s) {
        Map m = (Map) JSONObject.parseObject(s);
        return m;
    }

    /**
     * 将map转化为string
     */
    public static String collectToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    /**
     * 返回解析json后对应的Map
     */
    public static Map<String, Object> jsonToMap(String json) {
        JSONObject jsonObject = (JSONObject) JSON.parse(json);
        Map<String, Object> map = new HashMap<String, Object>(jsonObject);
        return map;
    }


    /**
     * 返回解析json后对应的Object
     */
    public static JSONObject jsonToObject(String json) {
        JSONObject jsonObject = (JSONObject) JSON.parse(json);
        return jsonObject;
    }
}
