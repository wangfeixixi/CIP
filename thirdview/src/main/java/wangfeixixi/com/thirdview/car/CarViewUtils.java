package wangfeixixi.com.thirdview.car;


import android.content.Context;

import wangfeixixi.com.thirdview.body.BodyBean;

public class CarViewUtils {

    public static int scale = 20;

    public static CarViewBean getCarView(Context context, int carX, int carY, BodyBean bodyBean) {

        CarViewBean carViewBean = new CarViewBean();
        double y = Math.sin(bodyBean.angle) * bodyBean.distance;
        double x = Math.cos(bodyBean.angle) * bodyBean.distance;

        carViewBean.carX = (int) (x - carX);
        carViewBean.carY = (int) (y - carY);

        carViewBean.carWidth = bodyBean.width;
        carViewBean.carLength = bodyBean.length;


        carViewBean.carX *= scale;
        carViewBean.carY *= scale;
        carViewBean.carWidth *= scale;
        carViewBean.carLength *= scale;
        return carViewBean;
    }

}
