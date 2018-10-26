package wangfeixixi.cip.fram;

import android.app.Application;

/**
 * Created by xuany on 2018/10/26.
 */

public class BaseApp extends Application {
    private static BaseApp instance;

    public BaseApp() {
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application getInstance() {
        return instance;
    }
}
