package wangfeixixi.com.soaplib.network;


import android.util.Log;

import org.ksoap2.SoapEnvelope;

public class SoapCall implements Call {
    private SoapRequest mSoapRequest;
    private Dispatcher mDispatcher;
    private SoapClient mSoapClient;
    private SoapHttpEngine mSoapHttpEngine;
    private boolean executed = false;

    public SoapCall(SoapRequest soapRequest, SoapClient client) {
        try {
            this.mSoapRequest = soapRequest;
            this.mSoapClient = client;
            this.mDispatcher = client.getDispatcher();
            this.mSoapHttpEngine = new SoapHttpEngine(soapRequest);
            this.mSoapHttpEngine.setDebug(this.mSoapClient.isDebug());
        } catch (Exception var4) {
            var4.printStackTrace();
            Log.e("SoapCall", "4----->" + var4.toString());
        }

    }

    public SoapEnvelope execute() {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }

            this.executed = true;
        }

        SoapEnvelope result = null;

        try {
            this.mDispatcher.executed(this);
            result = this.mSoapHttpEngine.doPost();
        } catch (Exception var7) {
            var7.printStackTrace();
            Log.e("SoapCall", "7----->" + var7.toString());
        } finally {
            this.mDispatcher.finished(this);

        }

        return result;
    }

    public void enqueue(Callback callback) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }

            this.executed = true;
        }

        this.mDispatcher.enqueue(new AsyncCall(callback));
    }

    public boolean isExecuted() {
        return this.executed;
    }

    final class AsyncCall implements Runnable {
        private final Callback callback;

        AsyncCall(Callback callback) {
            this.callback = callback;
        }

        SoapRequest request() {
            return SoapCall.this.mSoapRequest;
        }

        public void run() {
            try {
                SoapHttpEngine engine = new SoapHttpEngine(SoapCall.this.mSoapRequest);
                SoapEnvelope respose = engine.doPost();
                this.callback.onResponse(respose);
            } catch (Exception var6) {
                this.callback.onFailure(var6);
                var6.printStackTrace();
                Log.e("SoapCall", "6----->" + var6.toString());
            } finally {
                SoapCall.this.mDispatcher.finished(this);
            }

        }
    }
}
