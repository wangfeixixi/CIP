package wangfeixixi.cip.beans;

import java.util.List;

import wangfeixixi.cip.widget.carview.CarBean;

/**
 * UDP根bean
 */
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