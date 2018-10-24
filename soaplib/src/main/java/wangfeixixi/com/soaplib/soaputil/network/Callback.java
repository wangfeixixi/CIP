package wangfeixixi.com.soaplib.soaputil.network;

import org.ksoap2.SoapEnvelope;

public interface Callback {
    void onResponse(SoapEnvelope var1);
    void onFailure(Exception var1);
}