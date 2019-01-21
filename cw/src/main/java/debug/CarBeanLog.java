package debug;

import java.text.DecimalFormat;

import wangfeixixi.com.commen.utils.AppUtils;
import wangfeixixi.com.commen.utils.date.DateUtils;
import wangfeixixi.com.cw.beans.CarBean;
import wangfeixixi.com.cw.beans.JsonRootBean;
import wangfeixixi.com.cw.ui.bird.CarUtils;
import wangfeixixi.com.cw.widget.map.LBSUtils;

public class CarBeanLog {
    public static String bean2log(JsonRootBean bean) {
        if (bean.hvDatas == null || bean.rvDatas == null) {
            return bean.toString();
        }

        StringBuffer sb = new StringBuffer();
        sb.append("\nwifiName:" + AppUtils.getWifiName());
        sb.append("\nversion：" + AppUtils.getVersionName() + "-" + AppUtils.getVersionCode());
        sb.append("\ndate：" + String.valueOf(DateUtils.getCurrentDate(DateUtils.dateFormatYMDHMS)));
        sb.append("\nsn：" + bean.sn);
        sb.append("\nscale:" + CarUtils.getInstance().getScaleGrade());

        if (bean.hvDatas != null) {//本车
            sb.append("\n\nhvCar：" + bean.hvDatas.remoteId);
            sb.append("\ncw:" + bean.hvDatas.cw);
            sb.append("\ndirection:" + bean.hvDatas.cw);
            sb.append("\nheading:" + bean.hvDatas.heading);
            sb.append("\nspeed:" + double2String(bean.hvDatas.speed));
        }
        if (bean.rvDatas != null)
            for (CarBean rvBean : bean.rvDatas) {
                sb.append("\n\nrvCar：" + rvBean.remoteId);
                sb.append("\nx:" + double2String(rvBean.x));
                sb.append("\ny:" + double2String(rvBean.y));
                sb.append("\naveragex:" + double2String(rvBean.averagex));
                sb.append("\naveragey:" + double2String(rvBean.averagey));
                sb.append("\nheading:" + rvBean.heading);
                sb.append("\nspeed:" + double2String(rvBean.speed));

                sb.append("\ndistance：" + double2String(rvBean.distance) + " m");
                //辆车计算
                if (bean.hvDatas != null) {
                    sb.append("\nmapDistance：" + double2String(LBSUtils.calculateLineDistance(bean.hvDatas.latitude, bean.hvDatas.longitude, rvBean.latitude, rvBean.longitude)) + " m");
                }
            }


        return sb.toString();
    }

    public static String double2String(double a) {
        return String.valueOf(Double.parseDouble(new DecimalFormat("#.##").format(a)));
    }
}
