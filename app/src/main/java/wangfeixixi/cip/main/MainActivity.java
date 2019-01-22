package wangfeixixi.cip.main;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import wangfeixixi.cip.R;
import wangfeixixi.com.commen.arouter.ArouterAop;
import wangfeixixi.com.commen.arouter.ArouterMainUrl;
import wangfeixixi.com.commen.fram.BaseActivity;

@Route(path = ArouterMainUrl.MAIN)
public class MainActivity extends BaseActivity<MainDelegate> implements View.OnClickListener {
    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewDelegate.setOnClickListener(this, R.id.btn_map, R.id.btn_bird);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bird:
                ArouterAop.gotoAvp(this);
                break;
            case R.id.btn_map:
                ArouterAop.gotoMap(this);
                break;
        }
    }
}
