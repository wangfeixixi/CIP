package wangfeixixi.com.car.utils;


import android.graphics.Rect;

import wangfeixixi.com.car.CarBean;

/**
 * 转换坐标类，将接收到的信息转换成屏幕坐标点
 * <p>
 * 人和车的坐标都应该是左上角
 */
public class CarUtils {

    /**
     * 转化比例：1米scale个像素
     */
    public static float scale = 10;

    /**
     * 车辆图片的缩放
     */
    public static float carBitmapScale = 0.1f;

    //默认车宽
    public static float carWidth = 2;
    //默认车长
    public static float carLength = 4;

    /**
     * 将自身坐标系转换为屏幕坐标系
     *
     * @param carX     车辆的x像素值,坐标原点，车辆中心
     * @param carY     车辆的y像素值，坐标原点，车辆中心
     * @param bodyBean 扫描的物体信息
     * @return 屏幕坐标系物体区域
     */
    public static Rect shelf2Screen(float carX, float carY, CarBean bodyBean) {
        //1.车辆自身中心坐标（x1,y1）

        //2.


        float x = (carX + bodyBean.x * scale);
        float y = (carY - bodyBean.y * scale);
        float width = bodyBean.width * scale;
        float length = bodyBean.length * scale;
        return new Rect((int) (x - width), (int) (y - length), (int) x, (int) y);
    }

    public static float x2XView(float carX, CarBean bodyBean) {
        return carX + bodyBean.x * scale - bodyBean.width * scale;
    }

    public static float y2YView(float carY, CarBean bodyBean) {
        return carY - bodyBean.y * scale - bodyBean.width * scale;
    }


//    怎么求一个点绕一个点（a，b）顺时针旋转α度数后的点的位置（x，y）
//    x'=(x-a)cosα+(y-b)sinα+a
//    y'=-(x-a)sinα+(y-b)cosα+b
}
