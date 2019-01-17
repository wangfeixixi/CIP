package wangfeixixi.com.cw.widget.carview;


import wangfeixixi.com.cw.ui.NumberTransfer;
import wangfeixixi.com.cw.widget.CarConfig;

public class CarBean {
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
    public float averagex;
    public float averagey;


    //本车预警
    public int cw;
    public int direction;//上下左右：1234

    public float width = CarConfig.carWidth;//宽
    public float height = CarConfig.carLength;//长

    public CarBean() {

    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        if (!remoteId.equals("0")) {
            sb.append("\n远车Id: " + remoteId);
//            sb.append("\ntimestampMs: " + timestampMs);
//            sb.append("\ntimestampSecond: " + timestampSecond);
            sb.append("\ndistance：" + NumberTransfer.double2String(distance));
            sb.append("\nx: " + x);
            sb.append("\ny: " + y);
            sb.append("\naveragex: " + averagex);
            sb.append("\naveragey: " + averagey);
        } else {
            sb.append("\n本车Id: " + remoteId);
            sb.append("\ncw: " + cw);
            sb.append("\ndirection: " + direction);
        }
        sb.append("\nfcwAlarm: " + fcwAlarm);
        sb.append("\nheading: " + heading + " °");
        sb.append("\nspeed: " + NumberTransfer.double2String(speed * 3.6f) + " km/h");
//        sb.append("\nlatitude:" + latitude);
//        sb.append("\nlongitude:" + longitude);
//        sb.append("\n原点y:" + (ScreenUtils.getScreenHeight() / 3 * 2));
//        sb.append("\n原点x:" + (ScreenUtils.getScreenWidth() / 2));
//        CarBean bean = CarUtils.getInstance().filterOver(this);
//        sb.append("\n修正:" + bean.x + "  " + bean.y);


//        sb.append("\nrotate:" + rotate);
//        sb.append("\nwidth:" + width);
//        sb.append("\nheight:" + height);
        return sb.toString();
    }
}
