package wangfeixixi.com.base;

import android.app.Application;

import me.goldze.mvvmhabit.base.BaseApplication;

/**
 * Created by xuany on 2018/10/26.
 */

public class BaseApp extends BaseApplication {
    private static BaseApp instance;

    public BaseApp() {
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        initData();
    }

    private void initData() {

    }

    public static Application getInstance() {
        return instance;
    }
}
