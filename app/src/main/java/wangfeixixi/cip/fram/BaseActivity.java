package wangfeixixi.cip.fram;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.widget.udp.sevice.UDPService;
import wangfeixixi.com.base.ServiceUtils;
import wangfeixixi.com.base.mvvm.utils.ToastUtils;

public abstract class BaseActivity extends AppCompatActivity {

    public Activity mCtx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCtx = this;

        initView(savedInstanceState);
        initData();
    }


    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveDatas(JsonRootBean bean) {
        onReceiveJsonBean(bean);
    }

    protected abstract void onReceiveJsonBean(JsonRootBean bean);

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private long lastBackTime = 0;

    @Override
    public void onBackPressed() {
        long nowBackTime = System.currentTimeMillis();
        if (nowBackTime - lastBackTime < 300) {
            super.onBackPressed();
        } else {
            ToastUtils.showShort("双击推出");
            lastBackTime = nowBackTime;
        }
    }
}
