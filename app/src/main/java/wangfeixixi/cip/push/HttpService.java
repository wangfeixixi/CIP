package wangfeixixi.cip.push;

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
//        HttpUtils.setIsStart(true);
        HttpUtils.testEnqueue();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
//        HttpUtils.setIsStart(false);
        super.onDestroy();
    }
}
