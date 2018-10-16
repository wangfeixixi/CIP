package wangfeixixi.com.lib.first;

import android.content.Context;
import android.view.View;

import wangfeixixi.com.lib.body.BodyBean;
import wangfeixixi.com.lib.open.IGService;

public abstract class FirstService implements IGService {

    private View mFirstView;

    @Override
    public View getView(Context context) {
        if (mFirstView != null) {
            return mFirstView;
        } else {
            mFirstView = new FirstView(context);
            return mFirstView;
        }
    }

    @Override
    public void updateBodys(BodyBean[] beans) {

    }
}
