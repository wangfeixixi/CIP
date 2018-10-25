package wangfeixixi.com.lib.base;

import wangfeixixi.com.lib.body.CarBean;

public interface IBaseView {
    public void updateBodys(CarBean[] beans);

    public void switchPoint();

    public void stop();
}
