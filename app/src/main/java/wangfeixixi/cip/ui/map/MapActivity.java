package wangfeixixi.cip.ui.map;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import wangfeixixi.cip.R;
import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.child.ChildContainer;
import wangfeixixi.cip.widget.carview.utils.BitmapUtils;
import wangfeixixi.cip.widget.udp.UDPUtils;
import wangfeixixi.cip.widget.udp.server.IUDPResultListener;
import wangfeixixi.com.base.ScreenUtils;
import wangfeixixi.com.base.UIUtils;
import wangfeixixi.com.base.location.Gps;
import wangfeixixi.com.base.location.PositionUtil;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.OnLocationListener;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MapActivity extends BaseActivity implements IUDPResultListener {
    private RelativeLayout rl_father;
    private FrameLayout mapContainer;
    private GaodeMapService mLbs;

    @Override
    protected void initView(Bundle savedInstanceState) {
        rl_father = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = (int) (ScreenUtils.getScreenWidth());
        layoutParams.height = (int) (ScreenUtils.getScreenHeight());
        getWindow().addContentView(rl_father, layoutParams);


        //添加地图
        mapContainer = ChildContainer.addAllMap(rl_father);
        mLbs = new GaodeMapService(this);
        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);
//        mLbs.setLocationRes(R.mipmap.carDiagonal);
        mLbs.setLocationChangeListener(new OnLocationListener() {
            @Override
            public void onLocationChange(LocationInfo locationInfo) {
//                mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.carDiagonal));
                mLbs.moveCamera(locationInfo, scale);
            }
        });


    }

    private int scale = 16;

    @Override
    protected void initData() {
    }


    public long lastTime = 0;

    @Override
    public void onResultListener(JsonRootBean bean) {
        long nowTime = System.currentTimeMillis();
        long timeTemp = nowTime - lastTime;
        int time = 2000;
        if (bean.hvDatas != null) {
//            ChildCar.getInstance().addUpdateBenCar(rl_carview, bean.hvDatas);
        }

        if (timeTemp > time) {
            updateLbs(bean);//相当耗时
            if (bean.hvDatas != null) {
            }

            lastTime = nowTime;
            if (bean.rvDatas != null && bean.rvDatas.size() > 0 && bean.rvDatas.get(0).fcwAlarm != 0) {
                scale = 20;
                //                VoiceUtil.getInstance().speek("保持距离");
            } else {
                scale = 16;
            }
        }
    }

    public void moveSelfCar(LocationInfo locationInfo) {
        locationInfo.key = "自身坐标车";
//        mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.carDiagonal));
        mLbs.moveCamera(locationInfo, 20);
        mLbs.clearAllMarker();
    }

    private void updateLbs(JsonRootBean jsonRootBean) {
//        mLbs.clearAllMarker();
//        mLbs.removeMarker("远车");
        CarBean bean = null;
        Gps gps = null;
        LocationInfo local = null;
        if (jsonRootBean.rvDatas != null && jsonRootBean.rvDatas.size() > 0) {
            bean = jsonRootBean.rvDatas.get(0);
            gps = PositionUtil.gps84_To_Gcj02(bean.latitude / 10000000, bean.longitude / 10000000);
            local = new LocationInfo("远车", gps.getWgLat(), gps.getWgLon());
            mLbs.addOrUpdateMarker(local, BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car_other), 0.1f));
        }


//        for (int i = 0; i < jsonRootBean.rvDatas.size(); i++) {
//            bean = jsonRootBean.rvDatas.get(i);
//            gps = PositionUtil.gps84_To_Gcj02(bean.latitude / 10000000, bean.longitude / 10000000);
//            local = new LocationInfo(String.valueOf(i), "carDiagonal", gps.getWgLat(), gps.getWgLon(), bean.heading);
//            mLbs.addOrUpdateMarker(local, BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.car), 0.1f));
////                mLbs.moveCamera(local, 20);
//        }
    }


    /**
     * 获取巡航拥堵数据
     * <p>
     * 在巡航过程中，出现拥堵长度大于500米且拥堵时间大于5分钟时，
     * 会进到 updateAimlessModeCongestionInfo 回调中，
     * 通过 AimLessModeCongestionInfo 对象，可获取到道路拥堵信息（如：导致拥堵的事件类型、拥堵的状态等）。
     *
     * @param aimLessModeCongestionInfo
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
//        ToastUtils.showToast("获取巡航拥堵数据");
//        VoiceUtil.speek("获取巡航拥堵数据");
//        Log.d(TAG, "roadName=" + aimLessModeCongestionInfo.getRoadName());
//        Log.d(TAG, "CongestionStatus=" + aimLessModeCongestionInfo.getCongestionStatus());
//        Log.d(TAG, "eventLonLat=" + aimLessModeCongestionInfo.getEventLon() + "," + aimLessModeCongestionInfo.getEventLat());
//        Log.d(TAG, "height=" + aimLessModeCongestionInfo.getLength());
//        Log.d(TAG, "time=" + aimLessModeCongestionInfo.getTime());
//        for (AMapCongestionLink link : aimLessModeCongestionInfo.getAmapCongestionLinks()) {
//            Log.d(TAG, "status=" + link.getCongestionStatus());
//            for (NaviLatLng latlng : link.getCoords()) {
//                Log.d(TAG, latlng.toString());
//            }
//        }
    }

    /**
     * 获取巡航统计数据
     * <p>
     * 连续5个点速度大于15km/h后触发 updateAimlessModeStatistics 回调，通过 AimLessModeStat 对象可获取巡航的连续行驶距离和连
     *
     * @param aimLessModeStat
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
//        ToastUtils.showToast("获取巡航统计数据");
//        VoiceUtil.speek("获取巡航统计数据");
//        Log.d(TAG, "distance=" + aimLessModeStat.getAimlessModeDistance());
//        Log.d(TAG, "time=" + aimLessModeStat.getAimlessModeTime());
    }

    /**
     * 获取特殊道路设施数据
     * <p>
     * 在巡航过程中，出现特殊道路设施（如：测速摄像头、测速雷达；违章摄像头；铁路道口；应急车道等等）时，
     * 回进到 OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] infos)，通过 AMapNaviTrafficFacilityInfo  对象可获取道路交通设施信息。
     *
     * @param aMapNaviTrafficFacilityInfos
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {
//        ToastUtils.showToast("获取特殊道路设施数据");
//        VoiceUtil.speek("获取特殊道路设施数据");
//        for (AMapNaviTrafficFacilityInfo info : aMapNaviTrafficFacilityInfos) {
////            Toast.makeText(this, , Toast.LENGTH_LONG).show();
//            Log.d(TAG, "(trafficFacilityInfo.coor_X+trafficFacilityInfo.coor_Y+trafficFacilityInfo.distance+trafficFacilityInfo.limitSpeed):"
//                    + (info.getCoorX() + info.getCoorY() + info.getDistance() + info.getLimitSpeed()));
//        }
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
    protected void onReceiveJsonBean(JsonRootBean bean) {

    }
}
