package wangfeixixi.cip;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import wangfeixixi.com.soaplib.HttpUtils;

public class HttpService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    while (true) {
                        Thread.sleep(10);
//                        HttpUtils.postSoapTest();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
