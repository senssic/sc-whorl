package sc.whorl.system.utils;

import com.google.common.base.MoreObjects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sc.whorl.system.utils.collection.Collections3;


public class ScUtils {

    public static <T> String stringValue(Object object) {
        if (object == null) {
            return "null";
        }
        Class cz = object.getClass();
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(cz);
        if (cz.isArray()) {
            if (cz.getComponentType().isPrimitive()) {

                if (cz == byte[].class) {
                    stringHelper.addValue(Arrays.toString((byte[]) object));
                } else if (cz == short[].class) {
                    stringHelper.addValue(Arrays.toString((short[]) object));
                } else if (cz == int[].class) {
                    stringHelper.addValue(Arrays.toString((int[]) object));
                } else if (cz == long[].class) {
                    stringHelper.addValue(Arrays.toString((long[]) object));
                } else if (cz == char[].class) {
                    stringHelper.addValue(Arrays.toString((char[]) object));
                } else if (cz == float[].class) {
                    stringHelper.addValue(Arrays.toString((float[]) object));
                } else if (cz == double[].class) {
                    stringHelper.addValue(Arrays.toString((double[]) object));
                } else if (cz == boolean[].class) {
                    stringHelper.addValue(Arrays.toString((boolean[]) object));
                }
                return stringHelper.toString();
            } else {
                return cz.getSimpleName() + "{" + Arrays.deepToString((Object[]) object) + "}";
            }
        }
        if (object instanceof List) {
            List ls = ((List) object);
            for (Object obj : ls) {
                stringHelper.addValue(JSON.toJSONString(obj));
            }
            return stringHelper.toString();
        }
        if (object instanceof Set) {
            Set sets = ((Set) object);
            for (Object obj : sets) {
                stringHelper.addValue(JSON.toJSONString(obj));
            }
            return stringHelper.toString();
        }
        if (object instanceof Map) {
            for (Map.Entry<Object, Object> entry : ((Map<Object, Object>) object).entrySet()) {
                stringHelper.add(String.valueOf(entry.getKey()), JSON.toJSONString(entry.getValue()));
            }

            return stringHelper.toString();
        }
        Field fields[] = cz.getDeclaredFields();
        Field.setAccessible(fields, true);

        try {
            for (Field field : fields) {
                stringHelper.add(field.getName(), field.get(object));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return stringHelper.toString();
    }


    public static boolean isNotEmpty(Collection collection, long size) {
        return collection != null && !collection.isEmpty() && collection.size() == size;
    }


    public static Map<String, Object> getMap(String[] strings, Object... objects) {
        Map<String, Object> map = new HashMap();
        if (strings.length > objects.length) {
            return map;
        }
        for (int i = 0; i < strings.length; i++) {
            map.put(strings[i], objects[i]);
        }
        return map;
    }


    public static <T> List<T> transList(T... ts) {
        List<T> ls = new ArrayList<>();
        for (T t : ts) {
            if (t != null) {
                ls.add(t);
            }
        }
        return ls;
    }

    /**
     * 将JSONArray转换成Map[{"aa":xx,"cc":xx,"bb":xx},{"aa":xx,"cc":xx,"bb":xx}]
     * 注意一定是ListJson对象转换
     *
     * @param json
     * @param jsonPath
     * @param keyPropertyName
     * @param valuePropertyName
     * @return
     */
    public static Map<Object, Object> jsonPathToMap(final String json, final String jsonPath, final String keyPropertyName, final String valuePropertyName) {
        JSONArray jsonArray = (JSONArray) JSONPath.read(json, jsonPath);
        return Collections3.extractToMap(jsonArray, keyPropertyName, valuePropertyName);
    }


    public static boolean isEmpty(Object obj) {
        return obj == null ? true : (obj.getClass().isArray() ? Array.getLength(obj) == 0 : (obj instanceof CharSequence ? ((CharSequence) obj).length() == 0 : (obj instanceof Collection ? ((Collection) obj).isEmpty() : (obj instanceof Map ? ((Map) obj).isEmpty() : false))));
    }


    public static String changeF2Y(Long amount) throws Exception {
        return BigDecimal.valueOf(amount).divide(new BigDecimal(100)).toString();
    }


    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }


    public static <T> T findInJson(String json, String key, Class<T> clazz) {

        return (T) JSON.parseObject(json).getObject(key, clazz);
    }


    public static <T> T firstNonNull(T first, T second) {
        if (second == null) {
            throw new NullPointerException();
        }
        return first != null ? first : second;
    }



    /**
     * 费波纳茨
     *
     * @param number
     * @return
     */
    public static int fibonacci(int number) {
        if ((number == 0) || (number == 1)) {
            return number;
        } else {
            return fibonacci(number - 1) + fibonacci(number - 2);
        }
    }


}
