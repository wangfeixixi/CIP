package wangfeixixi.com.commen.arouter;

import android.app.Activity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

public class ArouterAop {
    /**
     * App main 页面
     *
     */
    public static void gotoAppMainActivity(final Activity activity) {
        ARouter.getInstance().build(ArouterMainUrl.MAIN).navigation(activity, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {

            }

            @Override
            public void onLost(Postcard postcard) {

            }

            @Override
            public void onArrival(Postcard postcard) {

            }

            @Override
            public void onInterrupt(Postcard postcard) {

            }
        });
    }

    /**
     * A 模块
     *
     */
    public static void gotoMap(final Activity activity) {
        ARouter.getInstance().build(ArouterMainUrl.MAP).navigation(activity, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {

            }

            @Override
            public void onLost(Postcard postcard) {

            }

            @Override
            public void onArrival(Postcard postcard) {

            }

            @Override
            public void onInterrupt(Postcard postcard) {

            }
        });
    }

    public static void gotoAvp(final Activity activity) {
        ARouter.getInstance().build(ArouterMainUrl.BIRD).navigation(activity, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {

            }

            @Override
            public void onLost(Postcard postcard) {

            }

            @Override
            public void onArrival(Postcard postcard) {

            }

            @Override
            public void onInterrupt(Postcard postcard) {
            }
        });
    }
}
