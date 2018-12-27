package wangfeixixi.cip.beans;

import java.text.DecimalFormat;
import java.util.List;

import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.com.base.VertionUtils;
import wangfeixixi.com.base.WifiUtils;
import wangfeixixi.com.base.data.DateUtils;

public class JsonRootBean {
    public int cmd;
    public CarBean hvDatas;
    public long magic;
    public int nov;
    public List<CarBean> rvDatas;
    public int sn;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String wifiName = WifiUtils.getWifiName();
        sb.append("\nwifiName:" + wifiName);
        sb.append("\n版本号：" + VertionUtils.getVersionCode());
        sb.append("\n版本名称：" + VertionUtils.getVersionName());
        sb.append("\nsn:" + sn);

        double jvli = 0;
        float mixDiagonal = 0;
        if (rvDatas != null && rvDatas.size() > 0) {
            mixDiagonal = CarUtils.getInstance().getMixDiagonal(rvDatas.get(0).x, rvDatas.get(0).y);
            double sqrt = Math.sqrt(Math.abs(rvDatas.get(0).x) * Math.abs(rvDatas.get(0).x) + Math.abs(rvDatas.get(0).y) * Math.abs(rvDatas.get(0).y));
//            sqrt -= mixDiagonal;
            jvli = Double.parseDouble(new DecimalFormat("#.##").format(sqrt));
        }
        if (jvli >= mixDiagonal) {
            sb.append("\n修正距离:" + (jvli - mixDiagonal));
        } else {
            sb.append("\n距离:" + 0);
        }
        sb.append("\n日期：" + String.valueOf(DateUtils.getCurrentDate(DateUtils.dateFormatYMDHMS)));
        sb.append("\n\n本车数据：");
        sb.append(hvDatas.toString());
        sb.append("\n\n远车数据:");

        if (rvDatas != null && rvDatas.size() > 0)
            for (int i = 0; i < rvDatas.size(); i++) {
                sb.append(rvDatas.get(i).toString());
            }
        return sb.toString();
    }
}