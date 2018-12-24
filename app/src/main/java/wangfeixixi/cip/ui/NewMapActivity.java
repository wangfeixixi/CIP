package wangfeixixi.cip.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

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
import wangfeixixi.cip.widget.udp.server.UDPResultListener;
import wangfeixixi.com.base.UIUtils;
import wangfeixixi.com.base.location.Gps;
import wangfeixixi.com.base.location.PositionUtil;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.OnLocationListener;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class NewMapActivity extends AppCompatActivity implements UDPResultListener {

    private RelativeLayout rl_carview;
    private RelativeLayout rl_father;

    private ImageView iv_right_floor;
    private ImageView iv_left_floor;

    private FrameLayout mapContainer;
    private GaodeMapService mLbs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_url_activity);

        rl_father = findViewById(R.id.rl_father);

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
//        mLbs.setLocationRes(R.mipmap.car);
        mLbs.setLocationChangeListener(new OnLocationListener() {
            @Override
            public void onLocationChange(LocationInfo locationInfo) {
//                mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car));
                mLbs.moveCamera(locationInfo, 20);
            }
        });
    }

    TextView tv_warning;
    public long lastTime = 0;

    public void receiveCars(ArrayList<CarBean> beans) {
        long nowTime = System.currentTimeMillis();
        long timeTemp = nowTime - lastTime;
        for (int i = 0; i < beans.size(); i++) {
            if (i == 0) {
                ChildCar.getInstance().addUpdateBenCar(rl_carview, beans.get(i));
            } else {
                ChildCar.getInstance().addUpdateOtherCar(rl_carview, beans.get(i));
            }
        }


        int time = speed2Time(beans.get(0).speed * 3.6f);
        if (timeTemp > time) {
            if (beans.get(0).fcwAlarm != 0) {//报警

            }
//            updateLbs(beans);//相当耗时
            TranslateAnim.switchSpeedAnim(iv_left_floor, time);
            TranslateAnim.switchSpeedAnim(iv_right_floor, time);
            lastTime = nowTime;
        }
    }

    private int speed2Time(float v) {
        if (v == 0) {
            return 3000;
        } else if (v < 30) {
            return 2000;
        } else if (v < 60) {
            return 1800;
        } else if (v < 90) {
            return 1600;
        } else if (v < 120) {
            return 1400;
        } else {
            return 1000;
        }
    }

    private void updateLbs(ArrayList<CarBean> list) {
//        mLbs.clearAllMarker();
        CarBean bean = null;
        Gps gps = null;
        LocationInfo local = null;
        for (int i = 0; i < list.size(); i++) {
            bean = list.get(i);
            gps = PositionUtil.gps84_To_Gcj02(bean.latitude / 10000000, bean.longitude / 10000000);
            local = new LocationInfo(String.valueOf(i), "car", gps.getWgLat(), gps.getWgLon(), bean.heading);
            if (i == 0) {
                mLbs.moveCamera(local, 20);
            }

            mLbs.addOrUpdateMarker(local, BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.car), 0.1f));
        }
    }

    @Override
    public void onResultListener(final JsonRootBean bean) {
        final ArrayList<CarBean> list = new ArrayList<>();
        if (bean.hvDatas != null)
            list.add(bean.hvDatas);
        if (bean.rvDatas != null)
            list.addAll(bean.rvDatas);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_warning.setText(bean.toString());
                if (list.size() < 2) {
                    return;
                }
                receiveCars(list);
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
}
