package wangfeixixi.com.cw.beans;

import java.util.List;

import wangfeixixi.com.cw.widget.carview.CarBean;


public class JsonRootBean {
    public int cmd;
    public long magic;
    public int nov;
    public int sn;
    public CarBean hvDatas;
    public List<CarBean> rvDatas;
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\nsn:" + sn);
//        sb.append("\ncmd:" + cmd);
//        sb.append("\nmagic:" + magic);
//        sb.append("\nnov:" + nov);
        sb.append(hvDatas.toString());
        if (rvDatas != null && rvDatas.size() > 0)
            for (int i = 0; i < rvDatas.size(); i++) {
                sb.append(rvDatas.get(i).toString());
            }
        return sb.toString();
    }
}