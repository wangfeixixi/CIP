package wangfeixixi.cip.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.cip.widget.carview.child.ChildContainer;
import wangfeixixi.cip.widget.map.LBSUtils;
import wangfeixixi.com.base.ScreenUtils;
import wangfeixixi.com.base.VertionUtils;
import wangfeixixi.com.base.WifiUtils;
import wangfeixixi.com.base.data.DateUtils;
import wangfeixixi.com.bdvoice.VoiceUtil;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MapActivity extends BaseActivity {
    private RelativeLayout rl_father;
    private FrameLayout mapContainer;
    private GaodeMapService mLbs;
    private RelativeLayout rl_capion;
    private TextView tv_log;

    @Override
    protected void initView(Bundle savedInstanceState) {
        //添加总布局
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

        //添加半屏提示
        rl_capion = new RelativeLayout(this);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.width = (int) (ScreenUtils.getScreenWidth());
        rllp.height = (int) (ScreenUtils.getScreenHeight() / 3);
        rl_capion.setBackgroundColor(Color.parseColor("#dddddd"));
        rl_father.addView(rl_capion, rllp);
        //添加按钮1
        Button btn_1 = new Button(this);
        RelativeLayout.LayoutParams btn_rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        btn_rllp.width = 300;
        btn_rllp.height = 180;
        btn_rllp.topMargin = ScreenUtils.getScreenHeight() - 280;
        btn_1.setText("日志");
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchLogView();
            }
        });
        rl_father.addView(btn_1, btn_rllp);


        switchCapionHeight(0);

        tv_log = new TextView(this);
        tv_log.setVisibility(View.GONE);
//        RelativeLayout.LayoutParams tv_rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        rl_father.addView(tv_log, tv_rllp);
        rl_father.addView(tv_log);
    }

    private void switchLogView() {
        tv_log.setVisibility(tv_log.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//        //使用Glide框架加载图片
//        Glide.with(imageView.getContext())
//                .load(url)
//                .apply(new RequestOptions().placeholder(placeholderRes))
//                .into(imageView);
    }


    public void switchCapionHeight(int height) {
        RelativeLayout.LayoutParams map_rllp = (RelativeLayout.LayoutParams) mapContainer.getLayoutParams();
        RelativeLayout.LayoutParams capion_rllp = (RelativeLayout.LayoutParams) rl_capion.getLayoutParams();
        if (height == 0) {
            mLbs.setPointToCenter(ScreenUtils.getScreenWidth() / 2, ScreenUtils.getScreenHeight() / 2);
            map_rllp.width = ScreenUtils.getScreenWidth();
            map_rllp.height = ScreenUtils.getScreenHeight();
            map_rllp.topMargin = 0;

            capion_rllp.width = ScreenUtils.getScreenWidth();
            capion_rllp.height = 10;
        } else {
            map_rllp.width = ScreenUtils.getScreenWidth();
            map_rllp.height = ScreenUtils.getScreenHeight() / 3;
            map_rllp.topMargin = ScreenUtils.getScreenHeight() / 3 * 2;
            mLbs.setPointToCenter(ScreenUtils.getScreenWidth() / 2, ScreenUtils.getScreenHeight() / 6);

            capion_rllp.width = ScreenUtils.getScreenWidth();
            capion_rllp.height = ScreenUtils.getScreenHeight() / 3 * 2;
        }
        rl_capion.setLayoutParams(capion_rllp);
        mapContainer.setLayoutParams(map_rllp);
    }

    @Override
    protected void initData() {
    }

    public long lastTime = 0;

    @Override
    protected void onReceiveJsonBean(JsonRootBean bean) {
        //显示log
        addLog(bean);
        if (bean.hvDatas == null || bean.rvDatas == null) {
            return;
        }

        long nowTime = System.currentTimeMillis();
        //更新地图位置
        LBSUtils.addBenMarker(mLbs, bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            LBSUtils.addOtherMarker(mLbs, bean.rvDatas.get(i));
        if (nowTime - lastTime > 2000) {
            //语音提示
            if (bean.rvDatas.get(0) != null && bean.rvDatas.get(0).fcwAlarm != 0) {
                switchCapionHeight(1);
            } else {
                switchCapionHeight(0);
            }
            VoiceUtil.getInstance().speek("保持距离");
            lastTime = nowTime;
        }
    }

    private void addLog(JsonRootBean bean) {
        StringBuffer sb = new StringBuffer();
        String wifiName = WifiUtils.getWifiName();
        sb.append("\nwifiName:" + wifiName);
        sb.append("\n版本号：" + VertionUtils.getVersionCode());
        sb.append("\n版本名称：" + VertionUtils.getVersionName());
        float distance = LBSUtils.calculateLineDistance(mLbs, bean.hvDatas.latitude, bean.hvDatas.longitude, bean.rvDatas.get(0).latitude, bean.rvDatas.get(0).longitude);
        sb.append("\n高德地图距离：" + distance);
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
        sb.append("\n日期：" + String.valueOf(DateUtils.getCurrentDate(DateUtils.dateFormatYMDHMS)));
        sb.append(bean.toString());
        tv_log.setText(sb.toString());
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
}
