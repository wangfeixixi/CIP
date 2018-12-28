package wangfeixixi.cip.widget.udp.client.data;

/**
 * 车辆自身为原点坐标系
 */
public class TestBean {
    public int fcwAlarm = 0;
    public float heading = 0;
    public float speed = 0;
    public float latitude;
    public float longitude;
    public String remoteId = "0";
    public long timestampMs;
    public long timestampSecond;
    public double distance;
    public float x = 0;
    public float y = 0;

    public TestBean() {

    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("remoteId:" + remoteId);
        sb.append("\ndistance：" + distance);
        sb.append("\nfcwAlarm:" + fcwAlarm);
        sb.append("\nheading:" + heading);
        sb.append("\nspeed:" + speed * 3.6f);
        sb.append("\nlatitude:" + latitude);
        sb.append("\nlongitude:" + longitude);
        sb.append("\nx:" + x);
        sb.append("\ny:" + y);
//        sb.append("\nleftMargin:" + getLeftMargin());
//        sb.append("\ntopMargin:" + getTopMargin());
//        sb.append("\n原点y:" + (ScreenUtils.getScreenHeight() / 3 * 2));
//        sb.append("\n原点x:" + (ScreenUtils.getScreenWidth() / 2));
//        CarBean bean = CarUtils.getInstance().filterOver(this);
//        sb.append("\n修正:" + bean.x + "  " + bean.y);


//        sb.append("\ntimestampMs:" + timestampMs);
//        sb.append("\ntimestampSecond:" + timestampSecond);
//        sb.append("\nrotate:" + rotate);
//        sb.append("\nwidth:" + width);
//        sb.append("\nheight:" + height);
        return sb.toString();
    }
}
