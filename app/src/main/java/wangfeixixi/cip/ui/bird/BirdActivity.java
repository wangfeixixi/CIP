package wangfeixixi.cip.ui.bird;

import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.ui.NumberTransfer;
import wangfeixixi.cip.utils.AppUtils;
import wangfeixixi.cip.utils.MediaUtils;
import wangfeixixi.cip.utils.ServiceUtils;
import wangfeixixi.cip.utils.date.DateUtils;
import wangfeixixi.cip.widget.udp.sevice.UDPService;

public class BirdActivity extends BaseActivity<BirdDelegate> {

    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void onReceiveJsonBean(JsonRootBean bean) {
        if (bean.hvDatas == null || bean.rvDatas == null) {
            viewDelegate.setLogText(bean.toString());
            return;
        }
        //显示log
        addLog(bean);
//        long nowTime = System.currentTimeMillis();
        //更新俯视图位置
        viewDelegate.carviewAddBenCar(bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            viewDelegate.carviewAddOtherCar(bean.rvDatas.get(i));


        //更新地图位置
        viewDelegate.lbsAddBenMaker(bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            viewDelegate.lbsAddOtherMaker(bean.rvDatas.get(i));

        if (bean.rvDatas.get(0) != null && bean.rvDatas.get(0).fcwAlarm != 0) {
            MediaUtils.getInstance().start();
        }

        viewDelegate.startRoadAnim(bean.hvDatas.speed != 0);
    }

    private void addLog(JsonRootBean bean) {
        StringBuffer sb = new StringBuffer();
        String wifiName = AppUtils.getWifiName();
        sb.append("\nwifiName:" + wifiName);
        sb.append("\n版本：" + AppUtils.getVersionName() + "-" + AppUtils.getVersionCode());
        sb.append("\n日期：" + String.valueOf(DateUtils.getCurrentDate(DateUtils.dateFormatYMDHMS)));
        float distance = viewDelegate.getLbsDistance(bean.hvDatas.latitude, bean.hvDatas.longitude, bean.rvDatas.get(0).latitude, bean.rvDatas.get(0).longitude);
        sb.append("\nmap距离：" + NumberTransfer.double2String(distance) + " m");
        sb.append("\ndistance：" + NumberTransfer.double2String(bean.rvDatas.get(0).distance) + " m");
        sb.append("\n距离差值：" + (NumberTransfer.double2String(distance - bean.rvDatas.get(0).distance) + " m"));

//        double jvli = 0;
//        float mixDiagonal = 0;
//        if (bean.rvDatas != null && bean.rvDatas.size() > 0) {
//            mixDiagonal = CarUtils.getInstance().getMixDiagonal(bean.rvDatas.get(0).x, bean.rvDatas.get(0).y);
//            double sqrt = Math.sqrt(Math.abs(bean.rvDatas.get(0).x) * Math.abs(bean.rvDatas.get(0).x) + Math.abs(bean.rvDatas.get(0).y) * Math.abs(bean.rvDatas.get(0).y));
//            sqrt -= mixDiagonal;
//            jvli = Double.parseDouble(new DecimalFormat("#.##").format(sqrt));
//        }
//        if (jvli >= mixDiagonal) {
//            sb.append("\n嘻嘻距离:" + (jvli - mixDiagonal));
//        } else {
//            sb.append("\n嘻嘻距离:" + 0);
//        }
//        sb.append("\n日期：" + String.valueOf(DateUtils.getCurrentDate(DateUtils.dateFormatYMDHMS)));

        sb.append(bean.toString());
        viewDelegate.setLogText(sb.toString());
    }

    @Override
    protected Class<BirdDelegate> getDelegateClass() {
        return BirdDelegate.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewDelegate.onResume();

        viewDelegate.jumpClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDelegate.LogViewShow();
            }
        });
        viewDelegate.jumpLongClick(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish();
                return true;
            }
        });

        ServiceUtils.startService(UDPService.class);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        viewDelegate.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewDelegate.onPause();
        ServiceUtils.stopService(UDPService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onDestroy() {
        viewDelegate.onDestroy();
        super.onDestroy();
    }

}
