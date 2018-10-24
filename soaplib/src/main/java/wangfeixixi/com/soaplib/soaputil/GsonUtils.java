package wangfeixixi.com.soaplib.soaputil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * 描述：  GsonUtils解析工具类
 * 作者：  郭永振
 * 时间：  2018-08-29 16:33:29
 */
public class GsonUtils {
    //线程安全的
    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                // .excludeFieldsWithoutExposeAnnotation()//打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
                // .serializeNulls()  //是否序列化空值
                .setDateFormat("yyyy-MM-dd HH:mm:ss")//序列化日期格式  "yyyy-MM-dd"
                // .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//会把字段首字母大写
                .setPrettyPrinting() //自动格式化换行
                .create();
    }

    /**
     * 获取gson解析器
     *
     * @return
     */
    public static Gson getGson() {
        return GSON;
    }

    /**
     * 对象转换为json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    /**
     * JSON转换为对象1--普通类型
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    /**
     * JSON转换为对象-针对泛型的类型
     *
     * @param json
     * @param typeOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }
}