package wangfeixixi.cip.fram;

import wangfeixixi.cip.fram.themvp.presenter.ActivityPresenter;
import wangfeixixi.cip.fram.themvp.view.IDelegate;

public abstract class BaseActivity<T extends IDelegate> extends ActivityPresenter<T> {
}
