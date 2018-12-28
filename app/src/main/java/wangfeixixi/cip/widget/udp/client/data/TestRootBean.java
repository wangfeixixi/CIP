package wangfeixixi.cip.widget.udp.client.data;

import java.util.List;

public class TestRootBean {
    public int cmd;
    public TestBean hvDatas;
    public long magic;
    public int nov;
    public List<TestBean> rvDatas;
    public int sn;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
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