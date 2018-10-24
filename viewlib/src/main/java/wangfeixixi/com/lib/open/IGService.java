package wangfeixixi.com.lib.open;

import android.content.Context;
import android.view.View;

import wangfeixixi.com.lib.body.CarBean;

public interface IGService {
    /**
     * 获取第一视角
     */
    View getView(Context context);

//    View getView(Context context, boolean isFirstView);

//    View switchView();

    void updateBodys(CarBean[] beans);
}
