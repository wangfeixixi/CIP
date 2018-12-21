package wangfeixixi.cip.widget.carview;

import wangfeixixi.com.base.ScreenUtils;

/**
 * 车辆自身为原点坐标系
 */
public class CarBean {
    public int fcwAlarm = 0;
    public float heading = 0;
    public float speed = 0;
    public float latitude;
    public float longitude;
    public String remoteId;
    public long timestampMs;
    public long timestampSecond;
    public float x = 0;
    public float y = 0;

    public float width = CarUtils.carWidth;//宽
    public float height = CarUtils.carLength;//长


    public int getLeftMargin() {
        return (int) (CarUtils.x0 + x * CarUtils.scale - width * CarUtils.scale / 2);
    }

    public int getTopMargin() {
        return (int) (CarUtils.y0 - y * CarUtils.scale - height * CarUtils.scale / 2);
    }


    public CarBean() {

    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\nremoteId:" + remoteId);
        sb.append("\nfcwAlarm:" + fcwAlarm);
        sb.append("\nheading:" + heading);
        sb.append("\nspeed:" + speed * 3.6f);
        sb.append("\nlatitude:" + latitude);
        sb.append("\nlongitude:" + longitude);
        sb.append("\nx:" + x);
        sb.append("\ny:" + y);
        sb.append("\nleftMargin:" + getLeftMargin());
        sb.append("\ntopMargin:" + getTopMargin());
        sb.append("\n原点y:" + (ScreenUtils.getScreenHeight() / 3 * 2));
        sb.append("\n原点x:" + (ScreenUtils.getScreenWidth() / 2));


//        sb.append("\ntimestampMs:" + timestampMs);
//        sb.append("\ntimestampSecond:" + timestampSecond);
//        sb.append("\nrotate:" + rotate);
//        sb.append("\nwidth:" + width);
//        sb.append("\nheight:" + height);
        return sb.toString();
    }
}
