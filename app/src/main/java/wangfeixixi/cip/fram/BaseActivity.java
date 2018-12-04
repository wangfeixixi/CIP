package wangfeixixi.cip.fram;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import wangfeixixi.com.soaplib.beans.CarTest;

public abstract class BaseActivity extends AppCompatActivity {

    public Activity mCtx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mCtx = this;

        initView(savedInstanceState);
        initData();
    }


    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(CarTest carBean) {
        receiveDatas(carBean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(String log) {
        receiveLog(log);
    }

    protected abstract void receiveLog(String log);

    protected abstract void receiveDatas(CarTest carBean);
}
