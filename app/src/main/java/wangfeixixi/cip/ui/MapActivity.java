package wangfeixixi.cip.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

import wangfeixixi.cip.R;
import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.cip.widget.map.LBSUtils;
import wangfeixixi.com.base.ScreenUtils;
import wangfeixixi.com.base.VertionUtils;
import wangfeixixi.com.base.WifiUtils;
import wangfeixixi.com.base.data.DateUtils;
import wangfeixixi.com.base.mvvm.utils.ToastUtils;
import wangfeixixi.com.bdvoice.VoiceUtil;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MapActivity extends BaseActivity {
    private FrameLayout mapContainer;
    private GaodeMapService mLbs;
    private LinearLayout ll_capion;
    //    private TextView tv_log;
    private TextView tv_speed;
    private TextView tv_distance;
    private ImageView iv_left;
    private ImageView iv_up;
    private ImageView iv_down;
    private ImageView iv_right;
    private ImageView iv_up_self;
    private ImageView iv_down_self;
    private TextView tv_log;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.map_activity);

        mapContainer = findViewById(R.id.fl_map_container);
        mLbs = new GaodeMapService(this);
        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);

        tv_speed = findViewById(R.id.tv_speed);
        tv_distance = findViewById(R.id.tv_distance);

        ll_capion = findViewById(R.id.ll_capion);

        iv_left = findViewById(R.id.iv_left);
        iv_up = findViewById(R.id.iv_up);
        iv_down = findViewById(R.id.iv_down);
        iv_right = findViewById(R.id.iv_right);

        iv_up_self = findViewById(R.id.iv_up_self);
        iv_down_self = findViewById(R.id.iv_down_self);
        tv_log = findViewById(R.id.tv_log);

        final View btn_jump = findViewById(R.id.btn_jump);
        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_log.setVisibility(tv_log.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        btn_jump.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(mCtx, NewMapActivity.class));
                return true;
            }
        });
    }

    private void switchCW(int cw) {
        switch (cw) {
            case 1://上
                iv_up_self.setVisibility(View.GONE);
                iv_down_self.setVisibility(View.VISIBLE);
                iv_up.setVisibility(View.VISIBLE);
                iv_down.setVisibility(View.GONE);
                iv_left.setVisibility(View.GONE);
                iv_right.setVisibility(View.GONE);
                VoiceUtil.getInstance().speek("前车预警");
                break;
            case 2://下
                iv_up_self.setVisibility(View.VISIBLE);
                iv_down_self.setVisibility(View.GONE);
                iv_up.setVisibility(View.GONE);
                iv_down.setVisibility(View.VISIBLE);
                iv_left.setVisibility(View.GONE);
                iv_right.setVisibility(View.GONE);
                VoiceUtil.getInstance().speek("后车预警");
                break;
            case 3://左
                iv_up_self.setVisibility(View.GONE);
                iv_down_self.setVisibility(View.VISIBLE);
                iv_left.setVisibility(View.VISIBLE);
                iv_down.setVisibility(View.GONE);
                iv_up.setVisibility(View.GONE);
                iv_right.setVisibility(View.GONE);
                VoiceUtil.getInstance().speek("左车预警");
                break;
            case 4://右
                iv_up_self.setVisibility(View.GONE);
                iv_down_self.setVisibility(View.VISIBLE);
                iv_up.setVisibility(View.GONE);
                iv_down.setVisibility(View.GONE);
                iv_left.setVisibility(View.GONE);
                iv_right.setVisibility(View.VISIBLE);
                VoiceUtil.getInstance().speek("右车预警");
                break;
            default:
                iv_up_self.setVisibility(View.GONE);
                iv_down_self.setVisibility(View.GONE);
                iv_up.setVisibility(View.GONE);
                iv_down.setVisibility(View.GONE);
                iv_left.setVisibility(View.GONE);
                iv_right.setVisibility(View.GONE);
                VoiceUtil.getInstance().speek("预警");
                break;
        }
    }

    public void switchCapionHeight(int height) {
        if (height == 0) {
            mLbs.setPointToCenter(ScreenUtils.getScreenWidth() / 2, ScreenUtils.getScreenHeight() / 2);
            ll_capion.setVisibility(View.GONE);
        } else {
            mLbs.setPointToCenter(ScreenUtils.getScreenWidth() / 2, ScreenUtils.getScreenHeight() / 6);
            ll_capion.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initData() {
        initGif();
        switchCapionHeight(0);
    }

    private void initGif() {
        Glide.with(mCtx)
                .load(R.mipmap.cw_front)
                .into(iv_up);
        Glide.with(mCtx)
                .load(R.mipmap.cw_back)
                .into(iv_down);
        Glide.with(mCtx)
                .load(R.mipmap.cw_left)
                .into(iv_left);
        Glide.with(mCtx)
                .load(R.mipmap.cw_right)
                .into(iv_right);
    }

    public long lastTime = 0;

    @Override
    protected void onReceiveJsonBean(JsonRootBean bean) {
        //显示log
        addLog(bean);
        if (bean.hvDatas == null || bean.rvDatas == null) {
            return;
        }
        tv_speed.setText(NumberTransfer.double2String(bean.hvDatas.speed * 3.6f));
        tv_distance.setText(NumberTransfer.double2String(bean.rvDatas.get(0).distance));

        long nowTime = System.currentTimeMillis();
        //更新地图位置
        LBSUtils.addBenMarker(mLbs, bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            LBSUtils.addOtherMarker(mLbs, bean.rvDatas.get(i));
        if (nowTime - lastTime > 2000) {
            //语音提示
            if (bean.rvDatas.get(0) != null && bean.hvDatas.cw != 0) {
                switchCapionHeight(1);
                switchCW(bean.hvDatas.direction);
            } else {
                switchCapionHeight(0);
            }
            lastTime = nowTime;
        }
    }

    private void addLog(JsonRootBean bean) {
        StringBuffer sb = new StringBuffer();
        String wifiName = WifiUtils.getWifiName();
        sb.append("\nwifiName:" + wifiName);
        sb.append("\n版本：" + VertionUtils.getVersionName() + "-" + VertionUtils.getVersionCode());
        sb.append("\n日期：" + String.valueOf(DateUtils.getCurrentDate(DateUtils.dateFormatYMDHMS)));
        float distance = LBSUtils.calculateLineDistance(mLbs, bean.hvDatas.latitude, bean.hvDatas.longitude, bean.rvDatas.get(0).latitude, bean.rvDatas.get(0).longitude);
        sb.append("\nmap距离：" + NumberTransfer.float2String(distance) + " m");
        sb.append("\ndistance：" + NumberTransfer.float2String(bean.rvDatas.get(0).distance) + " m");
        sb.append("\n距离差值：" + (NumberTransfer.float2String(distance - bean.rvDatas.get(0).distance) + " m"));
//        double jvli = 0;
//        float mixDiagonal = 0;
//        if (bean.rvDatas != null && bean.rvDatas.size() > 0) {
//            mixDiagonal = CarUtils.getInstance().getMixDiagonal(bean.rvDatas.get(0).x, bean.rvDatas.get(0).y);
//            double sqrt = Math.sqrt(Math.abs(bean.rvDatas.get(0).x) * Math.abs(bean.rvDatas.get(0).x) + Math.abs(bean.rvDatas.get(0).y) * Math.abs(bean.rvDatas.get(0).y));
//            sqrt -= mixDiagonal;
//            jvli = Double.parseDouble(new DecimalFormat("#.##").format(sqrt));
//        }
//        if (jvli >= mixDiagonal) {
//            sb.append("\n微调距离:" + (jvli - mixDiagonal));
//        } else {
//            sb.append("\n微调距离:" + 0);
//        }

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
