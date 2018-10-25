package wangfeixixi.com.soaplib;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ThreadUtils;

import org.ksoap2.SoapEnvelope;

import java.util.HashMap;
import java.util.Map;

import wangfeixixi.com.soaplib.beans.BaseSoapBean;
import wangfeixixi.com.soaplib.beans.FirstXmlResBean;
import wangfeixixi.com.soaplib.network.Callback;
import wangfeixixi.com.soaplib.provide.HgEncrypKey;
import wangfeixixi.com.soaplib.tool.SoapEnvelopeUtil;

/**
 * 作者：guoyzh
 * 时间：2018/8/27
 * 功能：对网络请求进行封装
 */

public class HttpUtils {

    /**
     * 基于kSoap2对post请求进行封装
     *
     * @param methodName
     * @param params
     * @param callBack
     */
    public static void postSoap(final Context context, String methodName, Map<String, String> params, final Class<? extends BaseSoapBean> clazz, final OnSoapCallBack callBack) {
        //添加请求参数
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.putAll(params);
        //获取网络请求工具类实例
        SoapUtil.getInstance().getSupportCity(methodName, new Callback() {
            // 将请求成功的数据返回到主线程进行数据更新
            Handler mainHandler = new Handler(context.getMainLooper());

            @Override
            public void onResponse(SoapEnvelope soapEnvelope) {
                final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        String jsonStr = XmlParser.xml2json(response);
                        BaseSoapBean resBean = GsonUtils.fromJson(jsonStr, clazz);
                        if (!ObjectUtils.isEmpty(resBean)) {
                            callBack.onOk(resBean);
                        }
                    }
                });
            }

            @Override
            public void onFailure(final Exception failMsg) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onNo(failMsg);
                    }
                });
            }
        }, reqBody);
    }

    public static void test(Context context) {
        String aCmdText = "SELECT TOP 10 deptid, deptcode, detpname FROM V_st_userOfdept";
        String Code = "075500080000";
        String check = HgEncrypKey.getEncryptStr(aCmdText);
        String aCheckCode = HgEncrypKey.HgEncrypKey(Code + check);

        HashMap<String, String> params = new HashMap<>();
        params.put("aCmdText", aCmdText);
        params.put("aCheckCode", aCheckCode);
        postSoap(context, "getRecordSet", params, FirstXmlResBean.class, new OnSoapCallBack() {
            @Override
            public void onOk(BaseSoapBean response) {
                FirstXmlResBean xmlBean = (FirstXmlResBean) response;
                LogUtils.i(ThreadUtils.isMainThread() + "::" + xmlBean.getDATAPACKET().getROWDATA().getROW().get(0).getDetpname());
            }

            @Override
            public void onNo(Exception e) {
                LogUtils.i(e.getMessage());
            }
        });
    }

    public static int i = 0;

    public static String tag = "aaaaaaaaaaaaaaaaaaaaaaaaa";

    //**************************尝试******************************8
    public static void testNew() {
        i++;
        HashMap<String, Integer> params = new HashMap<>();
        params.put("i", 1);

        final long startTime = System.currentTimeMillis();

        Log.d(tag, i + "开始次数----------");
        postSoapTest("getID", params, FirstXmlResBean.class, new OnSoapCallBack() {
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
//                            callBack.onOk(resBean);
//                        }
//                    }
//                });
                String jsonStr = XmlParser.xml2json(response);
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
