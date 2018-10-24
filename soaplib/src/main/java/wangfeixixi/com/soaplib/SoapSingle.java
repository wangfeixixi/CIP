package wangfeixixi.com.soaplib;


import wangfeixixi.com.soaplib.network.SoapClient;

public class SoapSingle {
static SoapClient instance = null;
    public static synchronized SoapClient getInstance() {
        if (instance == null){
            instance = new SoapClient();
            instance.setDebug(true);
        }
        instance.setDebug(true);
        return instance;
    }
}