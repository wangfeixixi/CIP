package wangfeixixi.com.soaplib.soaputil.network;


public final class SoapClient {
    private Dispatcher mDispatcher = new Dispatcher();
    private boolean isDebug;

    public SoapClient() {
    }

    public Dispatcher getDispatcher() {
        return this.mDispatcher;
    }

    public SoapCall newCall(SoapRequest request) {
        return new SoapCall(request, this);
    }

    public boolean isDebug() {
        return this.isDebug;
    }

    public void setDebug(boolean debug) {
        this.isDebug = debug;
    }
}

