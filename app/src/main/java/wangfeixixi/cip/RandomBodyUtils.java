package wangfeixixi.cip;

import wangfeixixi.com.lib.body.CarShelfBean;


/**
 * 物体形状，距离，随机生成单位米
 */
public class RandomBodyUtils {

    public static CarShelfBean getRandowBody() {
        CarShelfBean bodyBean = new CarShelfBean();
        bodyBean.x = (int) (Math.random() * 10);
        bodyBean.y = (int) (Math.random() * 10);
        bodyBean.width = 3;
        bodyBean.length = 5;
        return bodyBean;
    }
}
