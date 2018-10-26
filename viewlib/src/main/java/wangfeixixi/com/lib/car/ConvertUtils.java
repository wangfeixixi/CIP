package wangfeixixi.com.lib.car;


import android.content.Context;
import android.graphics.Rect;

import wangfeixixi.com.lib.body.CarShelfBean;

/**
 * 转换坐标类，将接收到的信息转换成屏幕坐标点
 * <p>
 * 人和车的坐标都应该是左上角
 */
public class ConvertUtils {

    /**
     * 转化比例：1米scale个像素
     */
    public static int scale = 40;

    /**
     * 将自身坐标系转换为屏幕坐标系
     *
     * @param carX     车辆的x像素值
     * @param carY     车辆的y像素值
     * @param bodyBean 扫描的物体信息
     * @return 屏幕坐标系物体区域
     */
    public static Rect shelf2Screen(int carX, int carY, CarShelfBean bodyBean) {
        int x = (carX + bodyBean.x * scale);
        int y = (carY - bodyBean.y * scale);
        int width = bodyBean.width * scale;
        int length = bodyBean.length * scale;
        return new Rect(x - width, y - length, x, y);
    }

}
