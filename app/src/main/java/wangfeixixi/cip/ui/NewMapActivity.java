package wangfeixixi.cip.ui;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.cip.widget.carview.anim.TranslateAnim;
import wangfeixixi.cip.widget.carview.child.ChildCar;
import wangfeixixi.cip.widget.carview.child.ChildContainer;
import wangfeixixi.cip.widget.carview.child.ChildLog;
import wangfeixixi.cip.widget.carview.child.ChildOther;
import wangfeixixi.cip.widget.map.LBSUtils;
import wangfeixixi.com.base.ScreenUtils;
import wangfeixixi.com.base.ThreadUtils;
import wangfeixixi.com.base.UIUtils;
import wangfeixixi.com.base.VertionUtils;
import wangfeixixi.com.base.WifiUtils;
import wangfeixixi.com.base.data.DateUtils;
import wangfeixixi.com.bdvoice.VoiceUtil;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class NewMapActivity extends BaseActivity {

    private RelativeLayout rl_carview;
    private RelativeLayout rl_father;

    private ImageView iv_right_floor;
    private ImageView iv_left_floor;

    private FrameLayout mapContainer;
    private GaodeMapService mLbs;

    @Override
    protected void initView(Bundle savedInstanceState) {
        rl_father = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = (int) (ScreenUtils.getScreenWidth());
        layoutParams.height = (int) (ScreenUtils.getScreenHeight());
        getWindow().addContentView(rl_father, layoutParams);

        //添加分界线
        ChildContainer.addLine(rl_father);

        //添加carview
        rl_carview = ChildContainer.addCarView(rl_father);
        //添加道路信息
        iv_left_floor = ChildOther.addLeftFloor(rl_carview);
        iv_right_floor = ChildOther.addRightFloor(rl_carview);

        //添加日志
        tv_warning = ChildLog.addLogView(rl_father);

        //添加地图
        mapContainer = ChildContainer.addMap(rl_father);
        mLbs = new GaodeMapService(this);
        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        ThreadUtils.runOnBackThread(new Runnable() {
            @Override
            public void run() {
                VoiceUtil.getInstance().initKey(UIUtils.getContext(), "14678940", "F7aZGFVk9cOQdb9X6nPw2Aog", "2wkI4xprZ8sMmxICY9iZYim704j1qy65");
            }
        });
    }

    TextView tv_warning;
    public long lastTime = 0;

    @Override
    protected void onReceiveJsonBean(JsonRootBean bean) {
        //显示log
        addLog(bean);

        if (bean.hvDatas == null || bean.rvDatas == null) {
            return;
        }
        long nowTime = System.currentTimeMillis();
        //更新俯视图位置
        ChildCar.getInstance().addUpdateBenCar(rl_carview, bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            ChildCar.getInstance().addUpdateOtherCar(rl_carview, bean.rvDatas.get(i));

        //更新地图位置
        LBSUtils.addBenMarker(mLbs, bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            LBSUtils.addOtherMarker(mLbs, bean.rvDatas.get(i));
        if (nowTime - lastTime > 2000) {

            //语音提示
            if (bean.rvDatas.get(0) != null && bean.rvDatas.get(0).fcwAlarm != 0)
                VoiceUtil.getInstance().speek("保持距离");
            //车道动画
            if (bean.hvDatas.speed != 0) {
                TranslateAnim.startTranslateAnim(iv_left_floor, iv_right_floor, 2000);
            } else {
                iv_left_floor.clearAnimation();
                iv_right_floor.clearAnimation();
            }
            lastTime = nowTime;
        }
    }

    private void addLog(JsonRootBean bean) {
        StringBuffer sb = new StringBuffer();
        String wifiName = WifiUtils.getWifiName();
        sb.append("\nwifiName:" + wifiName);
        sb.append("\n版本号：" + VertionUtils.getVersionCode());
        sb.append("\n版本名称：" + VertionUtils.getVersionName());


        double jvli = 0;
        float mixDiagonal = 0;
        if (bean.rvDatas != null && bean.rvDatas.size() > 0) {
            mixDiagonal = CarUtils.getInstance().getMixDiagonal(bean.rvDatas.get(0).x, bean.rvDatas.get(0).y);
            double sqrt = Math.sqrt(Math.abs(bean.rvDatas.get(0).x) * Math.abs(bean.rvDatas.get(0).x) + Math.abs(bean.rvDatas.get(0).y) * Math.abs(bean.rvDatas.get(0).y));
//            sqrt -= mixDiagonal;
            jvli = Double.parseDouble(new DecimalFormat("#.##").format(sqrt));
        }
        if (jvli >= mixDiagonal) {
            sb.append("\n修正距离:" + (jvli - mixDiagonal));
        } else {
            sb.append("\n距离:" + 0);
        }
        sb.append("\n高德经纬度求距离：" + String.valueOf(mLbs.calculateLineDistance(new LocationInfo(bean.hvDatas.latitude, bean.hvDatas.longitude), new LocationInfo(bean.rvDatas.get(0).latitude, bean.rvDatas.get(0).longitude))));
        sb.append("\n日期：" + String.valueOf(DateUtils.getCurrentDate(DateUtils.dateFormatYMDHMS)));


        tv_warning.setText(bean.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLbs.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLbs.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLbs.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mLbs.onSaveInstanceState(outState);
    }
}
