package wangfeixixi.com.cw.ui.map;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import wangfeixixi.com.commen.fram.BaseDelegate;
import wangfeixixi.com.commen.utils.MediaUtils;
import wangfeixixi.com.commen.utils.ScreenUtils;
import wangfeixixi.com.commen.utils.UIUtils;
import wangfeixixi.com.cw.R;
import wangfeixixi.com.cw.beans.CarBean;
import wangfeixixi.com.cw.widget.map.LBSUtils;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MapDelegate extends BaseDelegate {
    private FrameLayout mapContainer;
    private GaodeMapService mLbs;
    private LinearLayout ll_capion;
    private TextView tv_speed;
    private TextView tv_distance;
    private TextView tv_log;
    private ImageView iv_car_center;

    @Override
    public int getRootLayoutId() {
        return R.layout.map_activity;
    }

    @Override
    public void initWidget() {
        mapContainer = get(R.id.fl_map_container);
        tv_speed = get(R.id.tv_speed);
        tv_distance = get(R.id.tv_distance);
        ll_capion = get(R.id.ll_capion);
        tv_log = get(R.id.tv_log);

        iv_car_center = get(R.id.iv_car_center);
    }

    public void switchCW(int cw) {
        int gifID = 0;
        switch (cw) {
            case 1://前向碰撞
                gifID = R.mipmap.cw_front;
                break;
            case 2://后向碰撞
                gifID = R.mipmap.cw_back;
                break;
            case 3://左向碰撞
                gifID = R.mipmap.cw_left;
                break;
            case 4://右向碰撞
                gifID = R.mipmap.cw_right;
                break;
            case 5://逆向超车
                gifID = R.mipmap.cw_reverse_left;
                break;
            case 6://盲区左侧
                gifID = R.mipmap.cw_dead_zone_left;
                break;
            case 7://盲区右侧
                gifID = R.mipmap.cw_dead_zone_right;
                break;
            case 8://左转辅助
                gifID = R.mipmap.cw_left_assist;
                break;
            case 9://异常车辆
            default:
                gifID = R.mipmap.cw_abnormal;
        }
        Glide.with(UIUtils.getContext())
                .load(gifID)
                .into(iv_car_center);
    }

    public long lastTime = 0;

    public void switchCapionHeight(boolean isShow) {
        long nowTime = System.currentTimeMillis();
        if (isShow) {
            lastTime = nowTime;
            MediaUtils.getInstance().start();
            mLbs.setPointToCenter(ScreenUtils.getScreenWidth() / 2, ScreenUtils.getScreenHeight() / 6);
            ll_capion.setVisibility(View.VISIBLE);
        } else {
            if (nowTime - lastTime > 4000) {
                lastTime = nowTime;
                ll_capion.setVisibility(View.GONE);
                mLbs.setPointToCenter(ScreenUtils.getScreenWidth() / 2, ScreenUtils.getScreenHeight() / 2);
            }
        }
    }

    public void setLogText(String log) {
        tv_log.setText(log);
    }

    public void setSpeed(String speed) {
        tv_speed.setText(speed);
    }

    public void setDistance(String distance) {
        tv_distance.setText(distance);
    }

    public void lbsChangeBearing(float rotate) {
        mLbs.changeBearing(rotate);
    }

    public void lbsAddBenMaker(CarBean bean) {
        LBSUtils.addBenMarker(mLbs, bean);
    }

    public void lbsAddOtherMaker(CarBean bean) {
        LBSUtils.addOtherMarker(mLbs, bean);
    }

    public void onCreate(Bundle savedInstanceState) {
        mLbs = new GaodeMapService(UIUtils.getContext());
        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);
        mLbs.changeTilt(90);//倾斜角度
    }

    protected void onResume() {
        mLbs.onResume();
        switchCapionHeight(false);
        //init map
        CarBean bean = new CarBean();
        bean.heading = 0;
        bean.longitude = 1212388181;
        bean.latitude = 303377296;
        LBSUtils.addBenMarker(mLbs, bean);
    }

    protected void onPause() {
        mLbs.onPause();
    }

    protected void onSaveInstanceState(Bundle outState) {
        mLbs.onSaveInstanceState(outState);
    }

    protected void onDestroy() {
        mLbs.onDestroy();
    }

    public float getLbsDistance(float startLatitude, float startLongitude, float endLatitude, float endLongitude) {
        return LBSUtils.calculateLineDistance(startLatitude, startLongitude, endLatitude, endLongitude);
    }

    public void showLogView() {
        tv_log.setVisibility(tv_log.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
}
