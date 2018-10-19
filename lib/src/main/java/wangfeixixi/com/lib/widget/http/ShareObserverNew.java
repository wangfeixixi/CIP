package wangfeixixi.com.lib.widget.http;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class ShareObserverNew<K> implements Observer<BaseBean<K>> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseBean<K> baseBean) {
        if (baseBean.code == 20000) {
            onOk(baseBean.results);
        } else {
            onNo(baseBean.code, baseBean.msg);
        }
    }

    @Override
    public void onError(Throwable e) {
        onNo(99999, e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onOk(K result);

    public abstract void onNo(int code, String msg);
}
