package wangfeixixi.cip.ui.map;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import wangfeixixi.cip.R;
import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.cip.widget.carview.CarView;
import wangfeixixi.cip.widget.carview.utils.BitmapUtils;
import wangfeixixi.cip.widget.udp.UDPResultListener;
import wangfeixixi.cip.widget.udp.UDPUtils;
import wangfeixixi.com.base.ThreadUtils;
import wangfeixixi.com.base.UIUtils;
import wangfeixixi.com.base.test.LogUtils;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MapActivity extends BaseActivity implements View.OnClickListener {
    public String TAG = "MapActivity";
    private GaodeMapService mLbs;
    private FrameLayout mapContainer;
    private Button btn_show_view;
    private CarView carview;
    private Button btn_start;
    //    private Button btn_setting;
    private View rl_container_car;
    private TextView tv_warning;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.map_activity);
        mapContainer = findViewById(R.id.fl_map_container);
        mLbs = new GaodeMapService(this);

        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);

        btn_show_view = findViewById(R.id.btn_show_view);
        carview = findViewById(R.id.carview);
        btn_start = (Button) findViewById(R.id.btn_start);
//        btn_setting = findViewById(R.id.btn_setting);
        rl_container_car = findViewById(R.id.rl_container_car);
        tv_warning = findViewById(R.id.tv_warning);

        btn_show_view.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        rl_container_car.setOnClickListener(this);
//        btn_setting.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tv_warning.setText("预警信息提示");
        mLbs.setLocationRes(R.mipmap.car);
//        VoiceUtil.getInstance().initKey(UIUtils.getContext(), "14678940", "F7aZGFVk9cOQdb9X6nPw2Aog", "2wkI4xprZ8sMmxICY9iZYim704j1qy65");

//        mLbs.setLocationChangeListener(new OnLocationListener() {
//            @Override
//            public void onLocationChange(LocationInfo locationInfo) {
//                locationInfo.key = "自身坐标车";
//                mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car_map));
//                mLbs.moveCamera(locationInfo, 20);
//
//            }
//        });
//
//        mLbs.startOnceLocation();

//        mLbs.startAimlessMode(this, new MapNaviListener());
        mLbs.setLocationRes(R.mipmap.car);
    }

    float i = 100f;

    public void test() {
        i -= 0.2;
        if (i < -10f) return;
        final ArrayList<CarBean> list = new ArrayList<>();
        list.add(new CarBean(0, 0, 0, 0, 0, CarUtils.carWidth, CarUtils.carLength));
        list.add(new CarBean(0, 0, 50, 0, 0, CarUtils.carWidth, CarUtils.carLength));
        list.add(new CarBean(0, 0, i, 0, 0, CarUtils.carWidth, CarUtils.carLength));
        final CarBean[] beans = list.toArray(new CarBean[list.size()]);
        carview.updateBodys(beans);
        tv_warning.setText("距离 " + i + " 米");
        ThreadUtils.runOnUiThreadDelayed(new Runnable() {
            @Override
            public void run() {
                test();
            }
        }, 1);
    }

//    public void speek(float i) {
//        if (((int) (i)) % 10 != 0) return;
//        if (i < 100) {
//            VoiceUtil.getInstance().speek("路口有车辆汇入");
//        } else if (i < 50) {
//            VoiceUtil.getInstance().speek("附近有车辆，请小心驾驶");
//        } else if (i < 10) {
//            VoiceUtil.getInstance().speek("请保持安全距离！");
//        }
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show_view:
                rl_container_car.setVisibility(rl_container_car.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                btn_show_view.setText(rl_container_car.getVisibility() == View.VISIBLE ? "显示" : "隐藏");
                break;
            case R.id.btn_start:
                btn_start.setText(isStart ? "启动" : "结束");
                if (!isStart) {//开始
                    mLbs.clearAllMarker();
                    carview.updateBodys(new CarBean[0]);
//                VoiceUtil.getInstance().speek("开始驾驶");

                    UDPUtils.startServer(new UDPResultListener() {
                        @Override
                        public void onResultListener(final JsonRootBean jsonRootBean) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    receiveCars(jsonRootBean);
                                }
                            });
                        }
                    });
//                    UDPUtils.startUDPServer();
                } else {
//                    UDPUtils.stopUDPServer();
                    UDPUtils.stopServer();
                }

                isStart = !isStart;
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLbs.onResume();
    }

    public void moveSelfCar(LocationInfo locationInfo) {
        locationInfo.key = "自身坐标车";
        mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car));
        mLbs.moveCamera(locationInfo, 20);
        mLbs.clearAllMarker();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLbs.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mLbs.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLbs.onDestroy();
    }

    private boolean isStart = false;

    public long lastTime = 0;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveCars(JsonRootBean bean) {
        LogUtils.d(bean);
        ArrayList<CarBean> list = new ArrayList<>();
        if (bean.hvDatas != null) {
            list.add(new CarBean(0, bean.hvDatas.x, bean.hvDatas.y, bean.hvDatas.longitude, bean.hvDatas.latitude, CarUtils.carWidth, CarUtils.carLength));
            mLbs.addOrUpdateMarker(new LocationInfo("自身", bean.hvDatas.latitude, bean.hvDatas.longitude), BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.car), 0.1f));
            mLbs.moveCamera(new LocationInfo("自身", bean.hvDatas.latitude, bean.hvDatas.longitude), 20);
        }
        if (bean.rvDatas != null)
            for (int i = 0; i < bean.rvDatas.size(); i++) {
                list.add(new CarBean(0, bean.rvDatas.get(i).x, bean.rvDatas.get(i).y, bean.rvDatas.get(i).longitude, bean.rvDatas.get(i).latitude, CarUtils.carWidth, CarUtils.carLength));
                mLbs.addOrUpdateMarker(new LocationInfo(String.valueOf(i), bean.rvDatas.get(i).latitude, bean.rvDatas.get(i).longitude), BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.car), 0.1f));
            }

        carview.updateBodys(list.toArray(new CarBean[list.size()]));

        mLbs.clearAllMarker();

        long nowTime = System.currentTimeMillis();

        switch (list.size()) {
            case 0:
                tv_warning.setText("车辆数据异常");
                break;
            case 1:
                tv_warning.setText("附近没有车辆");
                break;
            default:
                double tem = Math.sqrt(Math.abs(list.get(1).x) * Math.abs(list.get(1).x) + Math.abs(list.get(1).y) * Math.abs(list.get(1).y));
                tv_warning.setText(
                        "x  " + list.get(1).x + "   x轴距离：  " + (list.get(0).x - list.get(1).x)
                                + "\n" + "y  " + list.get(1).y + "   y轴距离：  " + (list.get(0).y - list.get(1).y)
                                + "\n" + "latitude  " + list.get(1).latitude
                                + "\n" + "longitude  " + list.get(1).longitude
                                + "\n" + "时间  " + (nowTime - lastTime)
                                + "\n" + "近车3航向角  " + (bean.hvDatas.heading)
                                + "\n" + "远车航向角  " + (bean.rvDatas.get(0).heading)
                                + "\n" + "远车fcwAlarm  " + (bean.rvDatas.get(0).fcwAlarm)
                                + "\n" + "近车fcwAlarm  " + (bean.hvDatas.fcwAlarm)
                                + "\n" + "距离长度:  " + tem);
                break;
        }
        lastTime = nowTime;
    }

    //    /**
//     * 获取巡航拥堵数据
//     * <p>
//     * 在巡航过程中，出现拥堵长度大于500米且拥堵时间大于5分钟时，
//     * 会进到 updateAimlessModeCongestionInfo 回调中，
//     * 通过 AimLessModeCongestionInfo 对象，可获取到道路拥堵信息（如：导致拥堵的事件类型、拥堵的状态等）。
//     *
//     * @param aimLessModeCongestionInfo
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
//        ToastUtils.showToast("获取巡航拥堵数据");
//        VoiceUtil.speek("获取巡航拥堵数据");
//        Log.d(TAG, "roadName=" + aimLessModeCongestionInfo.getRoadName());
//        Log.d(TAG, "CongestionStatus=" + aimLessModeCongestionInfo.getCongestionStatus());
//        Log.d(TAG, "eventLonLat=" + aimLessModeCongestionInfo.getEventLon() + "," + aimLessModeCongestionInfo.getEventLat());
//        Log.d(TAG, "length=" + aimLessModeCongestionInfo.getLength());
//        Log.d(TAG, "time=" + aimLessModeCongestionInfo.getTime());
//        for (AMapCongestionLink link : aimLessModeCongestionInfo.getAmapCongestionLinks()) {
//            Log.d(TAG, "status=" + link.getCongestionStatus());
//            for (NaviLatLng latlng : link.getCoords()) {
//                Log.d(TAG, latlng.toString());
//            }
//        }
//    }
//
//    /**
//     * 获取巡航统计数据
//     * <p>
//     * 连续5个点速度大于15km/h后触发 updateAimlessModeStatistics 回调，通过 AimLessModeStat 对象可获取巡航的连续行驶距离和连
//     *
//     * @param aimLessModeStat
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
//        ToastUtils.showToast("获取巡航统计数据");
//        VoiceUtil.speek("获取巡航统计数据");
//        Log.d(TAG, "distance=" + aimLessModeStat.getAimlessModeDistance());
//        Log.d(TAG, "time=" + aimLessModeStat.getAimlessModeTime());
//    }
//
//    /**
//     * 获取特殊道路设施数据
//     * <p>
//     * 在巡航过程中，出现特殊道路设施（如：测速摄像头、测速雷达；违章摄像头；铁路道口；应急车道等等）时，
//     * 回进到 OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] infos)，通过 AMapNaviTrafficFacilityInfo  对象可获取道路交通设施信息。
//     *
//     * @param aMapNaviTrafficFacilityInfos
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {
//        ToastUtils.showToast("获取特殊道路设施数据");
//        VoiceUtil.speek("获取特殊道路设施数据");
//        for (AMapNaviTrafficFacilityInfo info : aMapNaviTrafficFacilityInfos) {
////            Toast.makeText(this, , Toast.LENGTH_LONG).show();
//            Log.d(TAG, "(trafficFacilityInfo.coor_X+trafficFacilityInfo.coor_Y+trafficFacilityInfo.distance+trafficFacilityInfo.limitSpeed):"
//                    + (info.getCoorX() + info.getCoorY() + info.getDistance() + info.getLimitSpeed()));
//        }
//    }
}
