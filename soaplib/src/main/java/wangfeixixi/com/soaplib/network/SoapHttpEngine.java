package wangfeixixi.com.soaplib.network;


import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Iterator;
import java.util.Map;

import wangfeixixi.com.soaplib.SoapApiConstant;

public class SoapHttpEngine implements HttpEngine {
    private static final String TAG = "SoapHttpEngine";
    private SoapRequest mSoapRequest;
    private boolean isDebug = false;

    public SoapHttpEngine(SoapRequest soapRequest) {
        this.mSoapRequest = soapRequest;
    }

    public SoapEnvelope doGet() throws Exception {
        return null;
    }

    public SoapEnvelope doPost() throws Exception {
        SoapObject rpc = new SoapObject(this.mSoapRequest.getNameSpace(), this.mSoapRequest.getMethodName());
        if (this.mSoapRequest.getParamsMap() != null) {
            Iterator var2 = this.mSoapRequest.getParamsMap().entrySet().iterator();

            while (var2.hasNext()) {
                Map.Entry<String, Object> e = (Map.Entry) var2.next();
                rpc.addProperty((String) e.getKey(), e.getValue());
            }
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //*******
        envelope.enc = SoapApiConstant.SOAP_ENC;
        envelope.env = SoapApiConstant.SOAP_ENV;
        envelope.xsi = SoapApiConstant.xsi;
        envelope.xsd = SoapApiConstant.xsd;
        //***********

        envelope.bodyOut = rpc;
        envelope.dotNet = this.mSoapRequest.isDotNet();
        HttpTransportSE transport = new HttpTransportSE(this.mSoapRequest.getEndPoint());
        transport.debug = this.isDebug;
        transport.call(this.mSoapRequest.getSoapAction(), envelope);
        Log.e("LoadInfoFragment,class", "kkk----------" + envelope.bodyIn.toString());
        return envelope;
    }

    public boolean isDebug() {
        return this.isDebug;
    }

    public void setDebug(boolean debug) {
        this.isDebug = debug;
    }
}