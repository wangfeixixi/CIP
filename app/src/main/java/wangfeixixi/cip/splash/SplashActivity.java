package wangfeixixi.cip.splash;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import wangfeixixi.com.commen.arouter.ArouterUrlConstant;
import wangfeixixi.com.commen.fram.BaseActivity;


@Route(path = ArouterUrlConstant.SPLASH)
public class SplashActivity extends BaseActivity<SplashDelgate> {
    @Override
    protected Class<SplashDelgate> getDelegateClass() {
        return SplashDelgate.class;
    }

    @Override
    protected void onResume() {
        super.onResume();

        ARouter.getInstance().build(ArouterUrlConstant.MAIN).navigation();
    }
}
