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

    private boolean isStart = true;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("------------------------------------------------------------");
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils.testNew();


//                try {
//                    while (isStart) {
//                        Thread.sleep(100);
//                        System.out.println("------------------------------------------------------------");
//                        HttpUtils.testNew();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        isStart = false;
        super.onDestroy();
    }
}
