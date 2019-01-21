package wangfeixixi.com.cw.ui.bird;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import wangfeixixi.com.cw.ui.CarBeanLog;
import wangfeixixi.com.commen.arouter.ArouterUrlConstant;
import wangfeixixi.com.commen.fram.BaseActivity;
import wangfeixixi.com.commen.utils.MediaUtils;
import wangfeixixi.com.cw.beans.JsonRootBean;


@Route(path = ArouterUrlConstant.BIRD)
public class BirdActivity extends BaseActivity<BirdDelegate> {
    private int lastHeading = 0;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveJsonBean(JsonRootBean bean) {
        viewDelegate.setLogText(CarBeanLog.bean2log(bean));
        if (bean.hvDatas == null || bean.rvDatas == null) {
            return;
        }
//        long nowTime = System.currentTimeMillis();
        //更新俯视图位置
        viewDelegate.carviewAddBenCar(bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            viewDelegate.carviewAddOtherCar(bean.rvDatas.get(i));

        if (++lastHeading == 5) {
            viewDelegate.lbsChangeBearing(bean.hvDatas.heading);//旋转角度
            lastHeading = 0;
        }

        //更新地图位置
        viewDelegate.lbsAddBenMaker(bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            viewDelegate.lbsAddOtherMaker(bean.rvDatas.get(i));

        if (bean.rvDatas.get(0) != null && bean.rvDatas.get(0).fcwAlarm != 0) {
            MediaUtils.getInstance().start();
        }

        viewDelegate.startRoadAnim(bean.hvDatas.speed != 0);
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
