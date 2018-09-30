package wangfeixixi.cip;

import wangfeixixi.com.thirdview.body.BodyBean;


public class RandomBodyUtils {

    public static BodyBean getRandowBody() {
        BodyBean bodyBean = new BodyBean();
        bodyBean.angle = (int) (Math.random() * 360);
        bodyBean.distance = (int) (Math.random() * 10);
        bodyBean.width = (int) (Math.random() * 3);
        bodyBean.length = (int) (Math.random() * 10);
        return bodyBean;
    }
}
