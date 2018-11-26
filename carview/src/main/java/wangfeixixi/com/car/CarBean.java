package wangfeixixi.com.car;

/**
 * 车辆自身为原点坐标系
 */
public class CarBean {
    //向上，顺时针
    public float rotate = 30.0f;//航向角
    public int width;//宽
    public int length;//长
    public int x;
    public int y;
    //    public int distance;//距离
//    public int angle;//角度
    public boolean isAlert;//是否警告信息

    public CarBean(float rotate, int x, int y, int carWidth, int carLength) {
        this.rotate = rotate;
        this.x = x;
        this.y = y;
        this.width = carWidth;
        this.length = carLength;

    }

    public CarBean() {

    }
}
