//package wangfeixixi.com.lib.widget.http.converter;
//
//import com.alibaba.fastjson.JSON;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
//import okhttp3.ResponseBody;
//import retrofit2.Converter;
//
//final class FastJsonResponseBodyConvert<T> implements Converter<ResponseBody, T> {
//
//    private Type type;
//
//    public FastJsonResponseBodyConvert(Type type) {
//        this.type = type;
//    }
//
//    @Override
//    public T shelf2Screen(ResponseBody value) throws IOException {
//        return JSON.parseObject(value.string(), type);
//    }
//}
