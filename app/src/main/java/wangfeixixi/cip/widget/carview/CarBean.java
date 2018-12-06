package wangfeixixi.cip.widget.carview;

/**
 * 车辆自身为原点坐标系
 */
public class CarBean {
    public int fcwAlarm;
    public double heading;
    public double speed;
    public float latitude;
    public float longitude;
    public int remoteId;
    public long timestampMs;
    public long timestampSecond;
    public float x;
    public float y;

    //向上，顺时针
    public float rotate = 0f;//航向角
    public float width = CarUtils.carWidth;//宽
    public float length = CarUtils.carLength;//长

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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\nremoteId:" + remoteId);
        sb.append("\nfcwAlarm:" + fcwAlarm);
        sb.append("\nheading:" + heading);
        sb.append("\nspeed:" + speed);
        sb.append("\nlatitude:" + latitude);
        sb.append("\nlongitude:" + longitude);
        sb.append("\nx:" + x);
        sb.append("\ny:" + y);
//        sb.append("\ntimestampMs:" + timestampMs);
//        sb.append("\ntimestampSecond:" + timestampSecond);
//        sb.append("\nrotate:" + rotate);
//        sb.append("\nwidth:" + width);
//        sb.append("\nlength:" + length);
        return sb.toString();
    }
}
