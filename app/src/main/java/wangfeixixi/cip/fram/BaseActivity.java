package wangfeixixi.cip.fram;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.fram.themvp.presenter.ActivityPresenter;
import wangfeixixi.cip.fram.themvp.view.IDelegate;

public abstract class BaseActivity<T extends IDelegate> extends ActivityPresenter<T> {

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
}
