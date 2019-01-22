package wangfeixixi.com.avpv.handle;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import wangfeixixi.com.avpv.R;
import wangfeixixi.com.avpv.arouter.ArouterAvpUrl;
import wangfeixixi.com.commen.fram.BaseActivity;

@Route(path = ArouterAvpUrl.HANDLE)
public class HandleActivity extends BaseActivity<HandleDelegate> {

    @Override
    protected void onResume() {
        super.onResume();
        NavView navView = viewDelegate.get(R.id.nav);
        final PointView pointView = viewDelegate.get(R.id.pointview);
        final TextView textView = viewDelegate.get(R.id.textView);
        //回调函数
        navView.setOnNavAndSpeedListener(new NavView.OnNavAndSpeedListener() {
            @Override
            public void onNavAndSpeed(float nav, float speed) {
                textView.setText(String.valueOf(nav) + "g" + String.valueOf(speed));
                pointView.setX(nav);//将偏移量传递给pointview
                pointView.setY(speed);
            }

        });

    }

    @Override
    protected Class<HandleDelegate> getDelegateClass() {
        return HandleDelegate.class;
    }
}
