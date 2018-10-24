package wangfeixixi.com.soaplib.soaputil;



import org.ksoap2.SoapEnvelope;

import java.util.Map;

import wangfeixixi.com.soaplib.soaputil.network.Callback;
import wangfeixixi.com.soaplib.soaputil.network.SoapClient;
import wangfeixixi.com.soaplib.soaputil.network.SoapRequest;

/**
 * Created by xu on 2018/5/15.
 */

public class SoapUtil {
    private static final String TAG = "SoapUtil";
    private static SoapUtil mInstance;
    private SoapClient mSoapClient= SoapSingle.getInstance();
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
     * @param
     * @param callback
     */
    public  void getSupportCity(String methodName, Callback callback, Map<String,Object> parms) {
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
    public SoapEnvelope getSupportCity(String methodName, Map<String,Object> parms) {
        SoapRequest request = new SoapRequest.Builder().endPoint(URL)
                .methodName(methodName)
                .soapAction(mNameSpace +methodName)
                .setParams(parms)
                .nameSpace(mNameSpace)
                .setVersion(mSOAPVersion)
                .setDotNet(true)
                .build();
        return mSoapClient.newCall(request).execute();
    }

}
