package wangfeixixi.cip;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.amap.api.navi.model.AMapCongestionLink;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviLatLng;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.fram.UIUtils;
import wangfeixixi.cip.utils.MapNaviListener;
import wangfeixixi.com.soaplib.beans.CarTest;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.OnLocationListener;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MapActivity extends BaseActivity {
    public String TAG = "MapActivity";
    private GaodeMapService mLbs;
    private FrameLayout mapContainer;
    private View btn_update_location;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.map_activity);
        mapContainer = findViewById(R.id.fl_map_container);
        mLbs = new GaodeMapService(this);

        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);

        btn_update_location = findViewById(R.id.btn_update);

    }

    @Override
    protected void initData() {
        btn_update_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLbs.startOnceLocation();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLbs.onResume();

        mLbs.setLocationChangeListener(new OnLocationListener() {
            @Override
            public void onLocationChange(LocationInfo locationInfo) {
                locationInfo.key = "自身坐标车";
                mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car_map));
                mLbs.moveCamera(locationInfo, 20);

            }
        });

        mLbs.startOnceLocation();

        mLbs.startAimlessMode(this, new MapNaviListener());
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
        Log.d(TAG, "roadName=" + aimLessModeCongestionInfo.getRoadName());
        Log.d(TAG, "CongestionStatus=" + aimLessModeCongestionInfo.getCongestionStatus());
        Log.d(TAG, "eventLonLat=" + aimLessModeCongestionInfo.getEventLon() + "," + aimLessModeCongestionInfo.getEventLat());
        Log.d(TAG, "length=" + aimLessModeCongestionInfo.getLength());
        Log.d(TAG, "time=" + aimLessModeCongestionInfo.getTime());
        for (AMapCongestionLink link : aimLessModeCongestionInfo.getAmapCongestionLinks()) {
            Log.d(TAG, "status=" + link.getCongestionStatus());
            for (NaviLatLng latlng : link.getCoords()) {
                Log.d(TAG, latlng.toString());
            }
        }
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
        Log.d(TAG, "distance=" + aimLessModeStat.getAimlessModeDistance());
        Log.d(TAG, "time=" + aimLessModeStat.getAimlessModeTime());
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
        for (AMapNaviTrafficFacilityInfo info : aMapNaviTrafficFacilityInfos) {
//            Toast.makeText(this, , Toast.LENGTH_LONG).show();
            Log.d(TAG, "(trafficFacilityInfo.coor_X+trafficFacilityInfo.coor_Y+trafficFacilityInfo.distance+trafficFacilityInfo.limitSpeed):"
                    + (info.getCoorX() + info.getCoorY() + info.getDistance() + info.getLimitSpeed()));
        }
    }
}
