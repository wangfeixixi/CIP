package wangfeixixi.com.cw.ui.map;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import debug.CarBeanLog;
import wangfeixixi.com.commen.arouter.ArouterUrlConstant;
import wangfeixixi.com.commen.fram.BaseActivity;
import wangfeixixi.com.cw.R;
import wangfeixixi.com.cw.beans.JsonRootBean;
import wangfeixixi.com.cw.ui.NumberTransfer;


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
    }

    @Override
    protected void onDestroy() {
        viewDelegate.onDestroy();
        super.onDestroy();
    }

    private int lastHeading = 0;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveJsonBean(JsonRootBean bean) {
        viewDelegate.setLogText(CarBeanLog.bean2log(bean));
        if (bean.hvDatas == null || bean.rvDatas == null) {
            return;
        }
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
