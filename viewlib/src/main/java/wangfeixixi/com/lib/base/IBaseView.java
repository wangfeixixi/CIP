package wangfeixixi.com.lib.base;

import wangfeixixi.com.lib.body.CarShelfBean;

public interface IBaseView {
    public void updateBodys(CarShelfBean[] beans);

    public void switchPoint();

    public void stop();
}
