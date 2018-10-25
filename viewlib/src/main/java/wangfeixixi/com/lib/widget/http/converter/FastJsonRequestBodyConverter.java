//package wangfeixixi.com.lib.widget.http.converter;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.serializer.SerializeConfig;
//
//import java.io.IOException;
//
//import okhttp3.MediaType;
//import okhttp3.RequestBody;
//import retrofit2.Converter;
//import wangfeixixi.com.lib.widget.http.api.ApiConstant;
//
//final class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
//
//    private static final MediaType MEDIA_TYPE = MediaType.parse(ApiConstant.MediaType);
//
//    private SerializeConfig serializeConfig;
//
//    public FastJsonRequestBodyConverter(SerializeConfig serializeConfig) {
//        this.serializeConfig = serializeConfig;
//    }
//
//    @Override
//    public RequestBody shelf2Screen(T value) throws IOException {
//        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value, serializeConfig));
//    }
//}
