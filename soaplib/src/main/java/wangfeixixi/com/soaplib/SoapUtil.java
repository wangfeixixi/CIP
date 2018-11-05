package wangfeixixi.com.soaplib;


import org.ksoap2.SoapEnvelope;

import java.util.Map;

import wangfeixixi.com.soaplib.network.Callback;
import wangfeixixi.com.soaplib.network.SoapClient;
import wangfeixixi.com.soaplib.network.SoapRequest;

public class SoapUtil {
    private static final String TAG = "SoapUtil";
    private static SoapUtil mInstance;
    private SoapClient mSoapClient = SoapSingle.getInstance();

    public static synchronized SoapUtil getInstance() {
        if (mInstance == null) {
            mInstance = new SoapUtil();
        }
        return mInstance;
    }

    /**
     * 异步调用
     */
    public void enqueue(String methodName, Callback callback, Map<String, Object> parms) {
        SoapRequest request = new SoapRequest.Builder().endPoint(SoapApiConstant.URL)
                .methodName(methodName)
                .soapAction("" + methodName)
                .setParams(parms)
                .nameSpace(SoapApiConstant.ns)
                .setVersion(SoapApiConstant.SOAPVersion)
                .setDotNet(true)
                .build();
        mSoapClient.newCall(request).enqueue(callback);
    }

    /**
     * 同步调用
     */
    public SoapEnvelope execute(String methodName, Map<String, Object> parms) {
        SoapRequest request = new SoapRequest.Builder().endPoint(SoapApiConstant.URL)
                .methodName(methodName)
                .soapAction("" + methodName)
                .setParams(parms)
                .nameSpace(SoapApiConstant.ns)
                .setVersion(SoapApiConstant.SOAPVersion)
                .setDotNet(true)
                .build();
        return mSoapClient.newCall(request).execute();
    }
}
