package wangfeixixi.com.soaplib.soaputil;


import wangfeixixi.com.soaplib.soaputil.network.SoapClient;

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