package wangfeixixi.cip.widget.udp.server;

import java.util.ArrayList;

import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.widget.carview.CarBean;

public abstract class IUDPResultListner implements IUDPResultListener {

    private boolean isShow = false;

    @Override
    public void onResultListener(final JsonRootBean bean) {
        onLogJson(bean);
        final ArrayList<CarBean> list = new ArrayList<>();
        if (bean.hvDatas != null)
            list.add(bean.hvDatas);
        if (bean.rvDatas != null)
            list.addAll(bean.rvDatas);
        if (list.size() < 2) {
            return;
        }
        //两车相距
        boolean temp = Math.sqrt(Math.abs(list.get(1).x) * Math.abs(list.get(1).x) + Math.abs(list.get(1).y) * Math.abs(list.get(1).y)) < 100;
        if (!isShow & temp) {
            if (temp) {
                onReceiveData(list, temp);
            } else {
                onReceiveData(list, temp);
            }
            isShow = temp;
        }
    }

    protected abstract void onReceiveData(ArrayList<CarBean> list, boolean isShow);

    protected abstract void onLogJson(JsonRootBean bean);
}
