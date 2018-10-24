package wangfeixixi.com.soaplib.soaputil.network;

import org.ksoap2.SoapEnvelope;

public interface Call {
    SoapEnvelope execute();

    void enqueue(Callback var1);
}