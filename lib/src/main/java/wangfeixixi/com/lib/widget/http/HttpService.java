package wangfeixixi.com.lib.widget.http;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import wangfeixixi.com.lib.widget.http.api.RetrofitManager;
import wangfeixixi.com.lib.widget.http.bean.ContactBean;

public class HttpService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        RetrofitManager.getInstance().getApiService().pullData().subscribe(new ShareObserverNew<ContactBean>() {
            @Override
            public void onOk(ContactBean result) {
                EventBus.getDefault().post("");
            }

            @Override
            public void onNo(int code, String msg) {

            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
