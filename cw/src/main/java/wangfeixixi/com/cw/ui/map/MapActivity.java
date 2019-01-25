package wangfeixixi.com.cw.ui.map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

import wangfeixixi.com.commen.arouter.ArouterMainUrl;
import wangfeixixi.com.commen.fram.BaseActivity;
import wangfeixixi.com.cw.R;
import wangfeixixi.com.cw.beans.JsonRootBean;
import wangfeixixi.com.cw.ui.CarBeanLog;


@Route(path = ArouterMainUrl.MAP)
public class MapActivity extends BaseActivity<MapDelegate> implements View.OnClickListener, View.OnLongClickListener {
    @Override
    public Class<MapDelegate> getDelegateClass() {
        return MapDelegate.class;
    }

    private static class MessageHandler extends Handler {
        private final WeakReference<MapActivity> contextWeakReference;

        public MessageHandler(MapActivity context) {
            contextWeakReference = new WeakReference<MapActivity>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            if (contextWeakReference.get() != null) {
                switch (msg.what) {
                    case 0:
                        contextWeakReference.get().isAlert = contextWeakReference.get().isAlert - 1;
                        if (contextWeakReference.get().isAlert == 0) {
                            contextWeakReference.get().viewDelegate.setLogText("设备异常");
                        }
                        removeMessages(0);
                        sendEmptyMessageDelayed(0, 2000);
                        break;
                }
            }
        }
    }

    private MessageHandler mHandler = new MessageHandler(this);


    /**
     * 0:正常
     * 1：没有远车数据
     * 2：没有本车数据
     * 3：没有收到数据
     */
    private int isAlert = 2;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveJsonBean(JsonRootBean bean) {
        isAlert = 2;
        viewDelegate.setLogText(CarBeanLog.bean2log(bean));
        if (bean.hvDatas == null) {
//            isAlert = 2;
            return;
        }
        if (bean.rvDatas == null) {
//            isAlert = 1;
            return;
        }
        viewDelegate.setSpeed(String.valueOf(Double.parseDouble(new DecimalFormat("#.##").format(bean.hvDatas.speed * 3.6f))));
        viewDelegate.setDistance(String.valueOf(Double.parseDouble(new DecimalFormat("#.##").format(bean.rvDatas.get(0).distance))));

        if (++lastHeading == 5) {
            viewDelegate.lbsChangeBearing(bean.hvDatas.heading);//旋转角度
            lastHeading = 0;
        }
        //更新地图位置
        viewDelegate.lbsAddBenMaker(bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++) {
            viewDelegate.lbsAddOtherMaker(bean.rvDatas.get(i));
        }
        viewDelegate.switchCapionHeight(bean.rvDatas.get(0).cw != 0);
        viewDelegate.switchCW(bean.rvDatas.get(0).cw);
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
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onClick(View v) {
        viewDelegate.showLogView();
    }

    @Override
    public boolean onLongClick(View v) {
        ARouter.getInstance().build(ArouterMainUrl.BIRD).navigation();
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
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        viewDelegate.onDestroy();
        super.onDestroy();
    }

    private int lastHeading = 0;


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
