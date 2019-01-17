package wangfeixixi.com.commen.fram;

import wangfeixixi.com.commen.fram.themvp.presenter.ActivityPresenter;
import wangfeixixi.com.commen.fram.themvp.view.IDelegate;

public abstract class BaseActivity<T extends IDelegate> extends ActivityPresenter<T> {
}
