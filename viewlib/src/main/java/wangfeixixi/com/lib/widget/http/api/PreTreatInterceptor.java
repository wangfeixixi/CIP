//package wangfeixixi.com.lib.widget.http.api;
//
//import java.io.IOException;
//
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class PreTreatInterceptor implements Interceptor {
//    String TAG = "PreTreatInterceptor";
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request oldRequest = chain.request();
////        String method = oldRequest.method();
////        String token = SpUtil.getString(Constant.token, "");
////        String userId = SpUtil.getString(Constant.userId, "");
////        String timeStamp = String.valueOf(System.currentTimeMillis());
////        HttpUrl oldUrl = oldRequest.url();
//        Request signRequest = oldRequest.newBuilder()
////                    .url(signUrl)
////                .addHeader("X-Token", token)
////                .addHeader("X-UserId", userId)
//                .build();
//
//        return chain.proceed(signRequest);
//
//    }
//}
