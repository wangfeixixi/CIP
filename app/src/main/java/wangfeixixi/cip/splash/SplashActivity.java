package wangfeixixi.cip.splash;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import wangfeixixi.com.commen.arouter.ArouterMainUrl;
import wangfeixixi.com.commen.fram.BaseActivity;


@Route(path = ArouterMainUrl.SPLASH)
public class SplashActivity extends BaseActivity<SplashDelgate> {
    @Override
    protected Class<SplashDelgate> getDelegateClass() {
        return SplashDelgate.class;
    }

    @Override
    protected void onResume() {
        super.onResume();

        ARouter.getInstance().build(ArouterMainUrl.MAIN).navigation();
    }
}
