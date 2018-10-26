package wangfeixixi.cip;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

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
                while (isStart) {
                    getdata();
                }
            }
        }.start();
    }

    public int num = 0;

    private void getdata() {
        long startTime = System.currentTimeMillis();
//        HttpUtils.testEnqueue();
        HttpUtils.testExecute();//同步

        long endTime = System.currentTimeMillis() - startTime;

        Log.d("aaaaaaaa       ", String.valueOf(endTime));

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
