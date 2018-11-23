package wangfeixixi.cip;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.fram.UIUtils;
import wangfeixixi.cip.push.HttpService;
import wangfeixixi.cip.utils.RandomBodyUtils;
import wangfeixixi.cip.utils.ServiceUtils;
import wangfeixixi.com.bdvoice.VoiceUtil;
import wangfeixixi.com.lib.body.CarShelfBean;
import wangfeixixi.com.lib.car.CarUtils;
import wangfeixixi.com.lib.first.FirstView;
import wangfeixixi.com.soaplib.beans.CarTest;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MapActivity extends BaseActivity implements View.OnClickListener {
    public String TAG = "MapActivity";
    private GaodeMapService mLbs;
    private FrameLayout mapContainer;
    private View btn_update;
    private View btn_clear;
    private View btn_second_view;
    private FirstView carview;
    private View btn_gone_view;
    private View btn_start;
    private View btn_end;
    private View rl_container_car;
    private TextView tv_warning;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.map_activity);
        mapContainer = findViewById(R.id.fl_map_container);
        mLbs = new GaodeMapService(this);

        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);

        btn_update = findViewById(R.id.btn_update);
        btn_clear = findViewById(R.id.btn_clear);
        btn_second_view = findViewById(R.id.btn_second_view);
        carview = findViewById(R.id.carview);
        btn_gone_view = findViewById(R.id.btn_gone_view);
        btn_start = findViewById(R.id.btn_start);
        btn_end = findViewById(R.id.btn_end);
        rl_container_car = findViewById(R.id.rl_container_car);
        tv_warning = findViewById(R.id.tv_warning);

        btn_update.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_second_view.setOnClickListener(this);
        btn_gone_view.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        btn_end.setOnClickListener(this);
        rl_container_car.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tv_warning.setText("预警信息提示");
        mLbs.setLocationRes(R.mipmap.car_map);


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
        mLbs.setLocationRes(R.mipmap.car_map);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update:

                int rand = (int) (Math.random() * 10);
                ArrayList<CarShelfBean> list = new ArrayList<>();

//                list.add(new CarShelfBean(90, 0, 0, CarUtils.carWidth, CarUtils.carLength));
//                list.add(new CarShelfBean(15, 0, 0, CarUtils.carWidth, CarUtils.carLength));
//                list.add(new CarShelfBean(0, 0, 0, CarUtils.carWidth, CarUtils.carLength));
//                list.add(new CarShelfBean(45, 0, 0, CarUtils.carWidth, CarUtils.carLength));
//                list.add(new CarShelfBean(90, 0, 0, CarUtils.carWidth, CarUtils.carLength));

                for (int i = 0; i < 360; i++) {
//                    list.add(RandomBodyUtils.getRandowBody());
                    list.add(new CarShelfBean(i, 0, 0, CarUtils.carWidth, CarUtils.carLength));
                }
                CarShelfBean[] beans = list.toArray(new CarShelfBean[list.size()]);


                carview.updateBodys(beans);

//                mLbs.startOnceLocation();
                break;
            case R.id.btn_clear:
                mLbs.clearAllMarker();
                break;
            case R.id.btn_second_view:
                rl_container_car.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_gone_view:
                rl_container_car.setVisibility(View.GONE);
                break;
            case R.id.btn_start:
                ServiceUtils.startService(HttpService.class);
                break;
            case R.id.btn_end:
                ServiceUtils.stopService(HttpService.class);
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
        mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car_map));
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

    @Override
    protected void receiveDatas(CarTest carBean) {
        mLbs.clearAllMarker();
        LocationInfo locationInfo = null;
        int size = carBean.cars.size();
        for (int i = 0; i < size; i++) {
            locationInfo = new LocationInfo(carBean.cars.get(i).latitude, carBean.cars.get(i).longitude);
            if (i == 0) {
                mLbs.moveCamera(locationInfo, 20);
            }
            mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car_map));
            switch (carBean.cars.get(i).warning) {
                case 0:
                    VoiceUtil.speek("附近车辆，请注意避让");
                    break;
                case 1:
                    VoiceUtil.speek("危险距离，警告");
                    break;
                case 2:
                    VoiceUtil.speek("紧急避让，紧急避让");
                    break;
            }
        }
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
