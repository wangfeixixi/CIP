package wangfeixixi.com.lib.base;

import wangfeixixi.com.lib.body.BodyBean;

public interface IBaseView {
    public void updateBodys(BodyBean[] beans);

    public void switchPoint();

    public void stop();
}
