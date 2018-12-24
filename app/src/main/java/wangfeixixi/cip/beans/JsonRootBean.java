package wangfeixixi.cip.beans;

import java.util.List;

import wangfeixixi.cip.widget.carview.CarBean;
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
//        sb.append("\ncmd:" + cmd);
//        sb.append("\nmagic:" + magic);
//        sb.append("\nnov:" + nov);
        sb.append("\nsn:" + sn);
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