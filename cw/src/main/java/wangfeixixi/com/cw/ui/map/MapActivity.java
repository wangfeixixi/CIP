package wangfeixixi.com.cw.ui.map;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import wangfeixixi.com.commen.fram.BaseActivity;
import wangfeixixi.com.commen.utils.AppUtils;
import wangfeixixi.com.commen.utils.ServiceUtils;
import wangfeixixi.com.commen.utils.date.DateUtils;
import wangfeixixi.com.cw.R;
import wangfeixixi.com.cw.beans.JsonRootBean;
import wangfeixixi.com.cw.ui.NumberTransfer;
import wangfeixixi.com.commen.constant.ArouterUrlConstant;
import wangfeixixi.com.cw.widget.udp.sevice.UDPService;


@Route(path = ArouterUrlConstant.MAP)
public class MapActivity extends BaseActivity<MapDelegate> implements View.OnClickListener, View.OnLongClickListener {
    @Override
    public Class<MapDelegate> getDelegateClass() {
        return MapDelegate.class;
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
        viewDelegate.setOnClickListener(this, R.id.btn_jump);
        viewDelegate.setOnLongClickListener(this, R.id.btn_jump);
        ServiceUtils.startService(UDPService.class);
    }

    @Override
    public void onClick(View v) {
        viewDelegate.showLogView();
    }

    @Override
    public boolean onLongClick(View v) {
        ARouter.getInstance().build(ArouterUrlConstant.BIRD).navigation();
        return true;
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
    protected void onDestroy() {
        viewDelegate.onDestroy();
        super.onDestroy();
    }

    private int lastHeading = 0;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveJsonBean(JsonRootBean bean) {
        if (bean.hvDatas == null || bean.rvDatas == null) {
            viewDelegate.setLogText(bean.toString());
            return;
        }
        //显示log
        addLog(bean);
        viewDelegate.setSpeed(NumberTransfer.double2String(bean.hvDatas.speed * 3.6f));
        viewDelegate.setDistance(NumberTransfer.double2String(bean.rvDatas.get(0).distance));


        if (++lastHeading == 5) {
            viewDelegate.lbsChangeBearing(bean.hvDatas.heading);//旋转角度
            lastHeading = 0;
        }
        //更新地图位置
        viewDelegate.lbsAddBenMaker(bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            viewDelegate.lbsAddOtherMaker(bean.rvDatas.get(i));
        viewDelegate.switchCapionHeight(bean.hvDatas.cw != 0);
        viewDelegate.switchCW(bean.hvDatas.direction);
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
//            sb.append("\n微调距离:" + (jvli - mixDiagonal));
//        } else {
//            sb.append("\n微调距离:" + 0);
//        }

        sb.append(bean.toString());
        viewDelegate.setLogText(sb.toString());
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
}
