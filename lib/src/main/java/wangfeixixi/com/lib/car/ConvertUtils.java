package wangfeixixi.com.lib.car;


import android.content.Context;

import wangfeixixi.com.lib.body.BodyBean;

/**
 * 转换坐标类，将接收到的信息转换成屏幕坐标点
 */
public class ConvertUtils {

    /**
     * 转化比例：1米scale个像素
     */
    public static int scale = 40;

    /**
     * 将获取的物体信息转换成屏幕坐标的区域
     *
     * @param context
     * @param carX     车辆的x像素值
     * @param carY     车辆的y像素值
     * @param bodyBean 扫描的物体信息
     * @return 屏幕坐标系物体区域
     */
    public static CarViewBean getCarView(Context context, int carX, int carY, BodyBean bodyBean) {

        CarViewBean carViewBean = new CarViewBean();
//        double y = (Math.abs(Math.sin(bodyBean.angle))) * bodyBean.distance * scale;
//        double x = (Math.abs(Math.cos(bodyBean.angle))) * bodyBean.distance * scale;

        double y = (Math.sin(bodyBean.angle)) * bodyBean.distance * scale;
        double x = (Math.cos(bodyBean.angle)) * bodyBean.distance * scale;

        carViewBean.carX = (int) (x + carX);
        carViewBean.carY = (int) (y + carY);
        carViewBean.carWidth = bodyBean.width * scale;
        carViewBean.carLength = bodyBean.length * scale;
        return carViewBean;
    }

}
