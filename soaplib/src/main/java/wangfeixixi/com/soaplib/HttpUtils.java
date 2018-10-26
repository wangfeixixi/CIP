package wangfeixixi.com.soaplib;

import android.util.Log;

import com.blankj.utilcode.util.ObjectUtils;

import org.ksoap2.SoapEnvelope;

import java.util.HashMap;
import java.util.Map;

import wangfeixixi.com.soaplib.beans.BaseSoapBean;
import wangfeixixi.com.soaplib.beans.FirstXmlResBean;
import wangfeixixi.com.soaplib.network.Callback;
import wangfeixixi.com.soaplib.tool.SoapEnvelopeUtil;

public class HttpUtils {
    public static int i = 0;

    public static String tag = "aaaaaaaaaaaaaaaaaaaaaaaaa";

    //**************************尝试******************************8
    public static void testNew() {
        i++;
        HashMap<String, Integer> params = new HashMap<>();
        params.put("i", 2);

        final long startTime = System.currentTimeMillis();

        Log.d(tag, i + "开始次数----------");
        postSoapTest("getVehicleInfoList", params, FirstXmlResBean.class, new OnSoapCallBack() {
            @Override
            public void onOk(BaseSoapBean response) {
                long endTime = System.currentTimeMillis();
                Log.d(tag, response.toString());
                Log.d(tag, i + "结束次数----------" + (endTime - startTime));

//                FirstXmlResBean xmlBean = (FirstXmlResBean) response;
//                LogUtils.i(ThreadUtils.isMainThread() + "::" + xmlBean.getDATAPACKET().getROWDATA().getROW().get(0).getDetpname());
            }

            @Override
            public void onNo(Exception e) {
                long endTime = System.currentTimeMillis();
                Log.d(tag, i + "结束次数" + (endTime - startTime) + e.getMessage());

//                LogUtils.i(e.getMessage());
            }
        });
    }


    public static void postSoapTest(String methodName, Map<String, Integer> params, final Class<? extends BaseSoapBean> clazz, final OnSoapCallBack callBack) {
        //添加请求参数
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.putAll(params);
        //获取网络请求工具类实例
        SoapUtil.getInstance().getID(methodName, new Callback() {
            // 将请求成功的数据返回到主线程进行数据更新
//            Handler mainHandler = new Handler(context.getMainLooper());

            @Override
            public void onResponse(SoapEnvelope soapEnvelope) {
                final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
//                mainHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        String jsonStr = XmlParser.xml2json(response);
//                        BaseSoapBean resBean = GsonUtils.fromJson(jsonStr, clazz);
//                        if (!ObjectUtils.isEmpty(resBean)) {
//                            callBack.onOk(resBean);v
//                        }
//                    }
//                });
                String jsonStr = XmlParser.xml2json(response);

                BaseSoapBean baseSoapBean = GsonUtils.fromJson(jsonStr, clazz);
                BaseSoapBean resBean = GsonUtils.fromJson(jsonStr, clazz);
                if (!ObjectUtils.isEmpty(resBean)) {
                    callBack.onOk(resBean);
                }
            }

            @Override
            public void onFailure(final Exception failMsg) {
//                mainHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        callBack.onNo(failMsg);
//                    }
//                });
                callBack.onNo(failMsg);
            }
        }, reqBody);
    }

    public static void postSoapExcuteTest(String methodName, Map<String, Integer> params, final Class<? extends BaseSoapBean> clazz, final OnSoapCallBack callBack) {
        //添加请求参数
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.putAll(params);
        //获取网络请求工具类实例

        SoapEnvelope soapEnvelope = SoapUtil.getInstance().getIdExecute(methodName, reqBody);
        final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
//        String jsonStr = XmlParser.xml2json(response);
//        BaseSoapBean resBean = GsonUtils.fromJson(jsonStr, clazz);
//        if (!ObjectUtils.isEmpty(resBean)) {
//            callBack.onOk(resBean);
//        }
        BaseSoapBean baseSoapBean = new BaseSoapBean();
        callBack.onOk(baseSoapBean);
    }

}
