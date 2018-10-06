package wangfeixixi.cip;

import wangfeixixi.com.thirdview.body.BodyBean;


/**
 * 物体形状，距离，随机生成单位米
 */
public class RandomBodyUtils {

    public static BodyBean getRandowBody() {
        BodyBean bodyBean = new BodyBean();
        bodyBean.angle = (int) (Math.random() * 360);
        bodyBean.distance = (int) (Math.random() * 10);
        bodyBean.width = (int) (Math.random() * 3);
        bodyBean.length = (int) (Math.random() * 10);
        if (bodyBean.width == 0) {
            bodyBean.width = 2;
        }
        if (bodyBean.length == 0) {
            bodyBean.width = 5;
        }
        return bodyBean;
    }
}
