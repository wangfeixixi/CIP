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
    public String remoteId = "0";
    public long timestampMs;
    public long timestampSecond;
    public float x = 0;
    public float y = 0;

    public float width = CarUtils.getInstance().carWidth;//宽
    public float height = CarUtils.getInstance().carLength;//长


    public int getLeftMargin() {
        return (int) (CarUtils.getInstance().x0 + x * CarUtils.getInstance().scale - width * CarUtils.getInstance().scale / 2);
    }

    public int getTopMargin() {
        return (int) (CarUtils.getInstance().y0 - y * CarUtils.getInstance().scale - height * CarUtils.getInstance().scale / 2);
    }
//    public int getLeftMargin() {
//        return (int) (CarUtils.getInstance().x0 + x * CarUtils.getInstance().scale - (remoteId.equals(0) ? CarUtils.getInstance().carWidth : CarUtils.getInstance().carOtherWidth));
//    }
//
//    public int getTopMargin() {
//        return (int) (CarUtils.getInstance().y0 - y * CarUtils.getInstance().scale - (remoteId.equals(0) ? CarUtils.getInstance().carLength : CarUtils.getInstance().carOtherLength));
//    }

    public int getOtherCarLeftMargin() {
        return (int) (CarUtils.getInstance().x0 + x * CarUtils.getInstance().scale - width * CarUtils.getInstance().scale / 2);
    }

//    public int getAlarmLeftMargin() {
//        float juli = 0;
//        switch (getAlarmOritation()) {
//            case 1:
//                return (int) (getLeftMargin() + (CarUtils.getInstance().carWidth - CarUtils.getInstance().alarmWidth) / 2 * CarUtils.getInstance().scale);
//            case 2:
//                return (int) (getLeftMargin() + (CarUtils.getInstance().carWidth - CarUtils.getInstance().alarmWidth) / 2 * CarUtils.getInstance().scale);
//            case 3:
//                return (int) (getLeftMargin() - (CarUtils.getInstance().alarmWidth + juli) * CarUtils.getInstance().scale);
//            default:
//                return (int) (getLeftMargin() + (CarUtils.getInstance().carWidth + juli) * CarUtils.getInstance().scale);
//        }
//    }
//
//    public int getAlarmTopMargin() {
//        float juli = 0;
//        switch (getAlarmOritation()) {
//            case 1:
//                return (int) (getTopMargin() - (CarUtils.getInstance().alarmWidth + juli) * CarUtils.getInstance().scale);
//            case 2:
//                return (int) (getTopMargin() + (CarUtils.getInstance().carLength + juli) * CarUtils.getInstance().scale);
//            case 3:
//                return (int) (getTopMargin() + (CarUtils.getInstance().carLength - CarUtils.getInstance().alarmWidth) / 2 * CarUtils.getInstance().scale);
//            default:
//                return (int) (getTopMargin() + (CarUtils.getInstance().carLength - CarUtils.getInstance().alarmWidth) / 2 * CarUtils.getInstance().scale);
//        }
//    }

//    //上下左右，1234
//    public int getAlarmOritation() {
//        if (x > 0) {
//            if (y >= 0) {
//                if (Math.abs(x) >= Math.abs(y)) {
//                    //右
//                    return 3;
//                } else {
//                    //上
//                    return 2;
//                }
//            } else {
//                if (Math.abs(x) >= Math.abs(y)) {
//                    //右
//                    return 3;
//                } else {
//                    //下
//                    return 1;
//                }
//            }
//        } else {
//            if (y >= 0) {
//                if (Math.abs(x) >= Math.abs(y)) {
//                    //左
//                    return 4;
//                } else {
//                    //上
//                    return 2;
//                }
//            } else {
//                if (Math.abs(x) >= Math.abs(y)) {
//                    //左
//                    return 4;
//                } else {
//                    //下
//                    return 1;
//                }
//            }
//        }
//    }

    public CarBean() {

    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("remoteId:" + remoteId);
        sb.append("\nfcwAlarm:" + fcwAlarm);
        sb.append("\nheading:" + heading);
        sb.append("\nspeed:" + speed * 3.6f);
        sb.append("\nlatitude:" + latitude);
        sb.append("\nlongitude:" + longitude);
        sb.append("\nx:" + x);
        sb.append("\ny:" + y);
//        sb.append("\nleftMargin:" + getLeftMargin());
//        sb.append("\ntopMargin:" + getTopMargin());
        sb.append("\n原点y:" + (ScreenUtils.getScreenHeight() / 3 * 2));
        sb.append("\n原点x:" + (ScreenUtils.getScreenWidth() / 2));
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
