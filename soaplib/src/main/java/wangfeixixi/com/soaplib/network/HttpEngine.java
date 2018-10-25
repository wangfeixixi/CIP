package wangfeixixi.com.soaplib.network;

import org.ksoap2.SoapEnvelope;

public interface HttpEngine {
    SoapEnvelope doGet() throws Exception;

    SoapEnvelope doPost() throws Exception;
}
