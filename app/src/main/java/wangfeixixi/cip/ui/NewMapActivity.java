package wangfeixixi.cip.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wangfeixixi.cip.R;
import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.anim.TranslateAnim;
import wangfeixixi.cip.widget.carview.child.ChildCar;
import wangfeixixi.cip.widget.carview.child.ChildContainer;
import wangfeixixi.cip.widget.carview.child.ChildLog;
import wangfeixixi.cip.widget.carview.child.ChildOther;
import wangfeixixi.cip.widget.carview.utils.BitmapUtils;
import wangfeixixi.cip.widget.udp.UDPUtils;
import wangfeixixi.cip.widget.udp.server.IUDPResultListener;
import wangfeixixi.com.base.ScreenUtils;
import wangfeixixi.com.base.UIUtils;
import wangfeixixi.com.base.location.Gps;
import wangfeixixi.com.base.location.PositionUtil;
import wangfeixixi.com.base.mvvm.utils.ToastUtils;
import wangfeixixi.com.bdvoice.VoiceUtil;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.OnLocationListener;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class NewMapActivity extends AppCompatActivity implements IUDPResultListener {

    private RelativeLayout rl_carview;
    private RelativeLayout rl_father;

    private ImageView iv_right_floor;
    private ImageView iv_left_floor;

    private FrameLayout mapContainer;
    private GaodeMapService mLbs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//        mLbs.setLocationRes(R.mipmap.carDiagonal);
        mLbs.setLocationChangeListener(new OnLocationListener() {
            @Override
            public void onLocationChange(LocationInfo locationInfo) {
//                mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.carDiagonal));
                mLbs.moveCamera(locationInfo, 20);
            }
        });

        VoiceUtil.getInstance().initKey(UIUtils.getContext(), "14678940", "F7aZGFVk9cOQdb9X6nPw2Aog", "2wkI4xprZ8sMmxICY9iZYim704j1qy65");


        ChildCar.getInstance().addBenCar(rl_carview, new CarBean());
    }

    TextView tv_warning;
    public long lastTime = 0;

    public void receiveCars(JsonRootBean bean) {
        long nowTime = System.currentTimeMillis();
        long timeTemp = nowTime - lastTime;
        int time = 2000;
//        if (bean.hvDatas != null) {
//            ChildCar.getInstance().addUpdateBenCar(rl_carview, bean.hvDatas);
//            time = speed2Time(bean.hvDatas.speed * 3.6f);
//        }

        if (bean.rvDatas != null && bean.rvDatas.size() > 0)
            for (int i = 0; i < bean.rvDatas.size(); i++)
                ChildCar.getInstance().addUpdateOtherCar(rl_carview, bean.rvDatas.get(i));

        if (timeTemp > time) {
            updateLbs(bean);//相当耗时
//            TranslateAnim.switchSpeedAnim(iv_left_floor, time);
            if (bean.hvDatas != null && bean.hvDatas.speed != 0)
                TranslateAnim.switchSpeedAnim(iv_left_floor, iv_right_floor, 3000);
            lastTime = nowTime;
            if (bean.rvDatas != null && bean.rvDatas.get(0).fcwAlarm != 0)
                VoiceUtil.getInstance().speek("保持距离");
        }

//        if (timeTemp > time) {
//            updateLbs(bean);//相当耗时
//
//            TranslateAnim.switchSpeedAnim(iv_left_floor, time);
//            TranslateAnim.switchSpeedAnim(iv_right_floor, time);
//            lastTime = nowTime;
//        }
    }

//    private int speed2Time(float v) {
//        if (v == 0) {
//            return 3000;
//        } else if (v < 30) {
//            return 2000;
//        } else if (v < 60) {
//            return 1800;
//        } else if (v < 90) {
//            return 1600;
//        } else if (v < 120) {
//            return 1400;
//        } else {
//            return 1000;
//        }
//    }

    private void updateLbs(JsonRootBean jsonRootBean) {
//        mLbs.clearAllMarker();
        CarBean bean = null;
        Gps gps = null;
        LocationInfo local = null;
        if (jsonRootBean.rvDatas != null && jsonRootBean.rvDatas.size() > 0)
            for (int i = 0; i < jsonRootBean.rvDatas.size(); i++) {
                bean = jsonRootBean.rvDatas.get(i);
                gps = PositionUtil.gps84_To_Gcj02(bean.latitude / 10000000, bean.longitude / 10000000);
                local = new LocationInfo(String.valueOf(i), "carDiagonal", gps.getWgLat(), gps.getWgLon(), bean.heading);
                mLbs.addOrUpdateMarker(local, BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.car), 0.1f));
//                mLbs.moveCamera(local, 20);
            }
    }

    @Override
    public void onResultListener(final JsonRootBean bean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_warning.setText(bean.toString());
                receiveCars(bean);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLbs.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        UDPUtils.startServer(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        UDPUtils.stopServer();
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

    private long lastBackTime = 0;

    @Override
    public void onBackPressed() {
        long nowBackTime = System.currentTimeMillis();
        if (nowBackTime - lastBackTime < 300) {
            super.onBackPressed();
        } else {
            ToastUtils.showShort("双击推出");
            lastBackTime = nowBackTime;
        }
    }
}
