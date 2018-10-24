//package wangfeixixi.com.lib.widget.http.api;
//
//
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import wangfeixixi.com.lib.widget.http.converter.FastJsonConverterFactory;
//
//public class RetrofitManager {
//
//    private static RetrofitManager mRetrofitManager = null;
//
//    private RetrofitManager() {
//        setApi();
////        setFaceApi();
//        setApiPostNoIntercepter();
//        setApiPost();
//    }
//
//    public static RetrofitManager getInstance() {
//        if (mRetrofitManager == null) {
//            synchronized (RetrofitManager.class) {
//                if (mRetrofitManager == null) {
//                    mRetrofitManager = new RetrofitManager();
//                }
//            }
//        }
//        return mRetrofitManager;
//    }
//
//    //*************face++*****************************
//
////    private static FaceService faceApi;
////
////    public FaceService getFaceApi() {
////        return faceApi;
////    }
////
////    private void setFaceApi() {
////        Retrofit faceRetrofit = new Retrofit.Builder()
////                .baseUrl("https://api-cn.faceplusplus.com")
////                .addConverterFactory(FastJsonConverterFactory.create())
////                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
////                .build();
////
////        faceApi = faceRetrofit.create(FaceService.class);
////    }
//
//
//    //**************通用api***********************************
//    private ApiService apiService;
//
//    public ApiService getApiService() {
//        return apiService;
//    }
//
//    private void setApi() {
//        //请求头log
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(new OkHttpClient.Builder()
//                        .addInterceptor(new PreTreatInterceptor())
//                        .build())
//                .baseUrl(ApiConstant.BASE_URL)
//                .addConverterFactory(FastJsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//        //通用请求头
//        apiService = retrofit.create(ApiService.class);
//    }
//
//
//    //************特殊api*******************************
//    private ApiService postJsonNoIntercepter;
//
//    public ApiService getPostJsonNoIntercepter() {
//        return postJsonNoIntercepter;
//    }
//
//    private void setApiPostNoIntercepter() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(new OkHttpClient.Builder()
//                        .build())
//                .baseUrl(ApiConstant.BASE_URL)
//                .addConverterFactory(FastJsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//        //通用请求头
//        postJsonNoIntercepter = retrofit.create(ApiService.class);
//    }
//
//    //************特殊api*******************************
//    private ApiService postJson;
//
//    public ApiService getPostJson() {
//        return postJson;
//    }
//
//    private void setApiPost() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(new OkHttpClient.Builder()
//                        .addInterceptor(new PreTreatInterceptor())
//                        .build())
//                .baseUrl(ApiConstant.BASE_URL)
//                .addConverterFactory(FastJsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//        //通用请求头
//        postJson = retrofit.create(ApiService.class);
//    }
//
//}
