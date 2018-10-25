package wangfeixixi.com.soaplib;


import org.ksoap2.SoapEnvelope;

import java.util.Map;

import wangfeixixi.com.soaplib.network.Callback;
import wangfeixixi.com.soaplib.network.SoapClient;
import wangfeixixi.com.soaplib.network.SoapRequest;

/**
 * Created by xu on 2018/5/15.
 */

public class SoapUtil {
    private static final String TAG = "SoapUtil";
    private static SoapUtil mInstance;
    private SoapClient mSoapClient = SoapSingle.getInstance();
    public static String URL = "http://www.hencego.com:213/WSDL?service=HgServicePrivate";
    public static String mNameSpace = "http://tempuri.org/";
    public static int mSOAPVersion = SoapEnvelope.VER11;

    public static synchronized SoapUtil getInstance() {
        if (mInstance == null) {
            mInstance = new SoapUtil();
        }
        return mInstance;
    }

    /**
     * 异步调用
     *
     * @param
     * @param callback
     */
    public void getSupportCity(String methodName, Callback callback, Map<String, Object> parms) {
        SoapRequest request = new SoapRequest.Builder().endPoint(URL)
                .methodName(methodName)
                .soapAction("" + methodName)
                .setParams(parms)
                .nameSpace("")
                .setVersion(mSOAPVersion)
                .setDotNet(true)
                .build();
        mSoapClient.newCall(request).enqueue(callback);
    }

    /**
     * 同步调用
     *
     * @param
     * @return
     */
    public SoapEnvelope getSupportCity(String methodName, Map<String, Object> parms) {
        SoapRequest request = new SoapRequest.Builder().endPoint(URL)
                .methodName(methodName)
                .soapAction(mNameSpace + methodName)
                .setParams(parms)
                .nameSpace(mNameSpace)
                .setVersion(mSOAPVersion)
                .setDotNet(true)
                .build();
        return mSoapClient.newCall(request).execute();
    }

    //***************************尝试******************88

//    public static String wsdl = "http://10.106.60.207:9999/api?wsdl";
//    public static String url = "http://10.106.60.207:9999/getID";

    public static String URL_My = "http://10.106.60.207:9999/";
    //    public static String URL_My = "http://192.168.43.166:9999/";
//    public static String mNameSpace_My = "http://schemas.xmlsoap.org/soap/encoding/";
    public static int mSOAPVersion_My = SoapEnvelope.VER11;

    /**
     * 异步调用
     *
     * @param
     * @param callback
     */
    public void getID(String methodName, Callback callback, Map<String, Object> parms) {
        SoapRequest request = new SoapRequest.Builder().endPoint(URL_My)
                .methodName(methodName)
                .soapAction("" + methodName)
                .setParams(parms)
                .nameSpace("urn:api")
                .setVersion(mSOAPVersion_My)
                .setDotNet(true)
                .build();
        mSoapClient.newCall(request).enqueue(callback);
    }

    /**
     * 同步调用
     *
     * @param
     * @return
     */
    public SoapEnvelope getIdExecute(String methodName, Map<String, Object> parms) {
        SoapRequest request = new SoapRequest.Builder().endPoint(URL)
                .methodName(methodName)
                .soapAction("" + methodName)
                .setParams(parms)
                .nameSpace("urn:api")
                .setVersion(mSOAPVersion_My)
                .setDotNet(true)
                .build();
        return mSoapClient.newCall(request).execute();
    }
}
