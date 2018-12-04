package wangfeixixi.cip.widget;

import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarUtils;

/**
 * 物体形状，距离，随机生成单位米
 */
public class RandomBodyUtils {
    public static CarBean getRandowBody() {
        CarBean bodyBean = new CarBean();
        bodyBean.rotate = (float) (Math.random() * 360);
        bodyBean.x = (int) (Math.random() * 10);
        bodyBean.y = (int) (Math.random() * 10);
        bodyBean.width = CarUtils.carWidth;
        bodyBean.length = CarUtils.carLength;
        return bodyBean;
    }
}
