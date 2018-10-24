package wangfeixixi.com.soaplib.soaputil;

import android.content.Context;
import android.os.Handler;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ThreadUtils;

import org.ksoap2.SoapEnvelope;

import java.util.HashMap;
import java.util.Map;

import wangfeixixi.com.soaplib.soaputil.network.Callback;
import wangfeixixi.com.soaplib.soaputil.provide.HgEncrypKey;
import wangfeixixi.com.soaplib.soaputil.tool.SoapEnvelopeUtil;

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
    public static void postSoap(final Context context, String methodName, Map<String, String> params, final Class<? extends BaseSoapResBean> clazz, final OnSoapResponse callBack) {
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
                        BaseSoapResBean resBean = GsonUtils.fromJson(jsonStr, clazz);
                        if (!ObjectUtils.isEmpty(resBean)) {
                            callBack.onSuccess(resBean);
                        }
                    }
                });
            }

            @Override
            public void onFailure(final Exception failMsg) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFail(failMsg);
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
        postSoap(context, "getRecordSet", params, FirstXmlResBean.class, new OnSoapResponse() {
            @Override
            public void onSuccess(BaseSoapResBean response) {
                FirstXmlResBean xmlBean = (FirstXmlResBean) response;
                LogUtils.i(ThreadUtils.isMainThread() + "::" + xmlBean.getDATAPACKET().getROWDATA().getROW().get(0).getDetpname());
            }

            @Override
            public void onFail(Exception failMsg) {
                LogUtils.i(failMsg.getMessage());
            }
        });
    }

    //**************************尝试******************************8
    public static void testNew(Context context) {
        HashMap<String, Integer> params = new HashMap<>();
        params.put("i", 1);
        postSoapTest(context, "getID", params, FirstXmlResBean.class, new OnSoapResponse() {
            @Override
            public void onSuccess(BaseSoapResBean response) {
                FirstXmlResBean xmlBean = (FirstXmlResBean) response;
                LogUtils.i(ThreadUtils.isMainThread() + "::" + xmlBean.getDATAPACKET().getROWDATA().getROW().get(0).getDetpname());
            }

            @Override
            public void onFail(Exception e) {
                LogUtils.i(e.getMessage());
            }
        });
    }


    public static void postSoapTest(final Context context, String methodName, Map<String, Integer> params, final Class<? extends BaseSoapResBean> clazz, final OnSoapResponse callBack) {
        //添加请求参数
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.putAll(params);
        //获取网络请求工具类实例
        SoapUtil.getInstance().getID(methodName, new Callback() {
            // 将请求成功的数据返回到主线程进行数据更新
            Handler mainHandler = new Handler(context.getMainLooper());

            @Override
            public void onResponse(SoapEnvelope soapEnvelope) {
                final String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);


//                mainHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        String jsonStr = XmlParser.xml2json(response);
//                        BaseSoapResBean resBean = GsonUtils.fromJson(jsonStr, clazz);
//                        if (!ObjectUtils.isEmpty(resBean)) {
//                            callBack.onSuccess(resBean);
//                        }
//                    }
//                });
            }

            @Override
            public void onFailure(final Exception failMsg) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFail(failMsg);
                    }
                });
            }
        }, reqBody);
    }

}
