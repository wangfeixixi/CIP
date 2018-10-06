package wangfeixixi.com.thirdview.car;


import android.content.Context;

import wangfeixixi.com.thirdview.body.BodyBean;

public class CarViewUtils {

    public static int scale = 20;

    /**
     * 将获取的物体信息转换成屏幕坐标的区域
     *
     * @param context
     * @param carX     车辆的x值
     * @param carY     车辆的y值
     * @param bodyBean 扫描的物体信息
     * @return 屏幕坐标系物体区域
     */
    public static CarViewBean getCarView(Context context, int carX, int carY, BodyBean bodyBean) {

        CarViewBean carViewBean = new CarViewBean();
        double y = (Math.abs(Math.sin(bodyBean.angle))) * bodyBean.distance;
        double x = (Math.abs(Math.cos(bodyBean.angle))) * bodyBean.distance;

        if (Math.sin(bodyBean.angle) > 0) {
            carViewBean.carY = (int) (y + carY);
        } else {
            carViewBean.carY = (int) (y - carY);
        }
        if (Math.cos(bodyBean.angle) > 0) {

        } else {

        }
        carViewBean.carX = (int) (x + carX);

        carViewBean.carWidth = bodyBean.width;
        carViewBean.carLength = bodyBean.length;


        carViewBean.carX *= scale;
        carViewBean.carY *= scale;
        carViewBean.carWidth *= scale;
        carViewBean.carLength *= scale;
        return carViewBean;
    }

}
