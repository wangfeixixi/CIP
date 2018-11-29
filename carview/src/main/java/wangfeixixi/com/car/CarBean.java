package wangfeixixi.com.car;

/**
 * 车辆自身为原点坐标系
 */
public class CarBean {
    //向上，顺时针
    public float rotate = 30.0f;//航向角
    public float width;//宽
    public float length;//长
    public float longitude;
    public float latitude;
    public float x;
    public float y;
    //    public int distance;//距离
//    public int angle;//角度
    public boolean isAlert;//是否警告信息

    public CarBean(float rotate, float x, float y, float longitude, float latitude, float carWidth, float carLength) {
        this.rotate = rotate;
        this.x = x;
        this.y = y;
        this.longitude = longitude;
        this.latitude = latitude;
        this.width = carWidth;
        this.length = carLength;

    }

    public CarBean() {

    }
}
