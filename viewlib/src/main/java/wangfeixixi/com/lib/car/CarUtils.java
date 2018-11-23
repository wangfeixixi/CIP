package wangfeixixi.com.lib.car;


import android.graphics.Rect;

import wangfeixixi.com.lib.body.CarShelfBean;

/**
 * 转换坐标类，将接收到的信息转换成屏幕坐标点
 * <p>
 * 人和车的坐标都应该是左上角
 */
public class CarUtils {

    /**
     * 转化比例：1米scale个像素
     */
    public static int scale = 40;
    //默认车宽
    public static int carWidth = 2;
    //默认车长
    public static int carLength = 4;

    /**
     * 将自身坐标系转换为屏幕坐标系
     *
     * @param carX     车辆的x像素值,坐标原点，车辆中心
     * @param carY     车辆的y像素值，坐标原点，车辆中心
     * @param bodyBean 扫描的物体信息
     * @return 屏幕坐标系物体区域
     */
    public static Rect shelf2Screen(int carX, int carY, CarShelfBean bodyBean) {
        //1.车辆自身中心坐标（x1,y1）

        //2.






        int x = (carX + bodyBean.x * scale);
        int y = (carY - bodyBean.y * scale);
        int width = bodyBean.width * scale;
        int length = bodyBean.length * scale;
        return new Rect(x - width, y - length, x, y);
    }


//    怎么求一个点绕一个点（a，b）顺时针旋转α度数后的点的位置（x，y）
//    x'=(x-a)cosα+(y-b)sinα+a
//    y'=-(x-a)sinα+(y-b)cosα+b
}
