package wangfeixixi.com.soaplib;

//import com.blankj.utilcode.util.ObjectUtils;

import org.greenrobot.eventbus.EventBus;
import org.ksoap2.SoapEnvelope;

import java.util.HashMap;
import java.util.Map;

import wangfeixixi.com.soaplib.network.Callback;
import wangfeixixi.com.soaplib.tool.SoapEnvelopeUtil;

public class HttpUtils {
    public static int i = 0;


    public static void executeGetVehicleDataList() {
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("count", 1);
        //获取网络请求工具类实例
        SoapEnvelope soapEnvelope = SoapUtil.getInstance().execute("getVehicleDataList", reqBody);
        final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
        EventBus.getDefault().post(String.valueOf(response));
    }

    public static void executeSetUpSystem() {
        Map<String, Object> reqBody = new HashMap<>();
        SoapEnvelope soapEnvelope = SoapUtil.getInstance().execute("setUpSystem", reqBody);
        final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
        if (response == null) {
            EventBus.getDefault().post("初始化网络异常");
            return;
        } else {
            EventBus.getDefault().post("初始化网络成功");
        }
        new Thread() {
            @Override
            public void run() {
                while (isStart)
                    executeGetVehicleDataList();
            }
        }.start();
    }

    public static boolean isStart = true;

    public static void setIsStart(boolean isStartRun) {
        isStart = isStartRun;
    }

//    public static void testExecutePre() {
//        long startTime = System.currentTimeMillis();
//        Map<String, Object> reqBody = new HashMap<>();
//        reqBody.put("count", 5);
//        //获取网络请求工具类实例
//        SoapEnvelope soapEnvelope = SoapUtil.getInstance().execute("setUpSystem", reqBody);
//        final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
//        long endTime = System.currentTimeMillis();
//        Log.d("ddddddddd", response);
//        Log.d("cccccccccccccc", String.valueOf(endTime - startTime));
//        String jsonStr = XmlParser.xml2json(response);
//        BaseSoapBean resBean = GsonUtils.fromJson(jsonStr, CarTest.class);
//
//        EventBus.getDefault().post(new CarTest());
//        testEnqueue();
//    }

    //**************************尝试******************************8
    public static void testEnqueue() {
        Map<String, Object> reqBody = new HashMap<>();
        SoapUtil.getInstance().enqueue("setUpSystem", new Callback() {
            @Override
            public void onResponse(SoapEnvelope soapEnvelope) {
                final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
//                String jsonStr = XmlParser.xml2json(response);
                EventBus.getDefault().post("初始化成功" + response);
                new Thread() {
                    @Override
                    public void run() {
                        while (isStart)
                            executeGetVehicleDataList();
                    }
                }.start();
            }

            @Override
            public void onFailure(Exception var1) {
                EventBus.getDefault().post("初始化失败" + var1.getMessage());
            }
        }, reqBody);
    }

    //**************************尝试******************************8
//    public static void testUrl() {
//        Map<String, Object> reqBody = new HashMap<>();
//        SoapUtil.getInstance().enqueue("setUpSystem", new Callback() {
//            @Override
//            public void onResponse(SoapEnvelope soapEnvelope) {
//                final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        }, reqBody);
//    }


//    public static void enqueue(String methodName, Map<String, Integer> params, final Class<? extends BaseSoapBean> clazz, final OnSoapCallBack callBack) {
//        //添加请求参数
//        Map<String, Object> reqBody = new HashMap<>();
//        reqBody.putAll(params);
//        //获取网络请求工具类实例
//        SoapUtil.getInstance().enqueue(methodName, new Callback() {
//            // 将请求成功的数据返回到主线程进行数据更新
////            Handler mainHandler = new Handler(context.getMainLooper());
//
//            @Override
//            public void onResponse(SoapEnvelope soapEnvelope) {
//                final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
////                mainHandler.post(new Runnable() {
////                    @Override
////                    public void run() {
////                        String jsonStr = XmlParser.xml2json(response);
////                        BaseSoapBean resBean = GsonUtils.fromJson(jsonStr, clazz);
////                        if (!ObjectUtils.isEmpty(resBean)) {
////                            callBack.onOk(resBean);v
////                        }
////                    }
////                });
//                String jsonStr = XmlParser.xml2json(response);
//
//                BaseSoapBean baseSoapBean = GsonUtils.fromJson(jsonStr, clazz);
//                BaseSoapBean resBean = GsonUtils.fromJson(jsonStr, clazz);
////                if (!ObjectUtils.isEmpty(resBean)) {
////                    callBack.onOk(resBean);
////                }
//
//                EventBus.getDefault().post(new CarTest());
//            }
//
//            @Override
//            public void onFailure(final Exception failMsg) {
////                mainHandler.post(new Runnable() {
////                    @Override
////                    public void run() {
////                        callBack.onNo(failMsg);
////                    }
////                });
//                callBack.onNo(failMsg);
//            }
//        }, reqBody);
//    }

//    public static void execute(String methodName, Map<String, Integer> params, final Class<? extends BaseSoapBean> clazz, final OnSoapCallBack callBack) {
//        //添加请求参数
//        Map<String, Object> reqBody = new HashMap<>();
//        reqBody.putAll(params);
//        //获取网络请求工具类实例
//
//        SoapEnvelope soapEnvelope = SoapUtil.getInstance().execute(methodName, reqBody);
//        final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
////        String jsonStr = XmlParser.xml2json(response);
////        BaseSoapBean resBean = GsonUtils.fromJson(jsonStr, clazz);
////        if (!ObjectUtils.isEmpty(resBean)) {
////            callBack.onOk(resBean);
////        }
//        BaseSoapBean baseSoapBean = new BaseSoapBean();
//        callBack.onOk(baseSoapBean);
//    }


}
