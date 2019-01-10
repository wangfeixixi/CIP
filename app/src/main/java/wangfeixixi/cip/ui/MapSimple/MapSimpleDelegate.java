package wangfeixixi.cip.ui.MapSimple;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import wangfeixixi.cip.R;
import wangfeixixi.cip.fram.BaseDelegate;
import wangfeixixi.cip.utils.MediaUtils;
import wangfeixixi.cip.utils.ScreenUtils;
import wangfeixixi.cip.utils.UIUtils;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.map.LBSUtils;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MapSimpleDelegate extends BaseDelegate {
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
        iv_car_center = get(R.id.iv_car_center);
        ll_capion = get(R.id.ll_capion);
        iv_left = get(R.id.iv_left);
        iv_up = get(R.id.iv_up);
        iv_down = get(R.id.iv_down);
        iv_right = get(R.id.iv_right);

        iv_up_self = get(R.id.iv_up_self);
        iv_down_self = get(R.id.iv_down_self);
        tv_log = get(R.id.tv_log);
    }

    public void switchCW(int cw) {
        switch (cw) {
            case 1://上
                iv_up_self.setVisibility(View.GONE);
                iv_down_self.setVisibility(View.VISIBLE);
                iv_up.setVisibility(View.VISIBLE);
                iv_down.setVisibility(View.GONE);
                iv_left.setVisibility(View.GONE);
                iv_right.setVisibility(View.GONE);
                iv_car_center.setVisibility(View.GONE);
//                VoiceUtil.getInstance().speek("前车预警");
                break;
            case 2://下
                iv_up_self.setVisibility(View.VISIBLE);
                iv_down_self.setVisibility(View.GONE);
                iv_up.setVisibility(View.GONE);
                iv_down.setVisibility(View.VISIBLE);
                iv_left.setVisibility(View.GONE);
                iv_right.setVisibility(View.GONE);
                iv_car_center.setVisibility(View.GONE);
//                VoiceUtil.getInstance().speek("后车预警");
                break;
            case 3://左
                iv_up_self.setVisibility(View.GONE);
                iv_down_self.setVisibility(View.VISIBLE);
                iv_left.setVisibility(View.VISIBLE);
                iv_down.setVisibility(View.GONE);
                iv_up.setVisibility(View.GONE);
                iv_right.setVisibility(View.GONE);
                iv_car_center.setVisibility(View.GONE);
//                VoiceUtil.getInstance().speek("左车预警");
                break;
            case 4://右
                iv_up_self.setVisibility(View.GONE);
                iv_down_self.setVisibility(View.VISIBLE);
                iv_up.setVisibility(View.GONE);
                iv_down.setVisibility(View.GONE);
                iv_left.setVisibility(View.GONE);
                iv_right.setVisibility(View.VISIBLE);
                iv_car_center.setVisibility(View.GONE);
//                VoiceUtil.getInstance().speek("右车预警");
                break;
            case 5://对向预警
                iv_up_self.setVisibility(View.GONE);
                iv_down_self.setVisibility(View.GONE);
                iv_up.setVisibility(View.GONE);
                iv_down.setVisibility(View.GONE);
                iv_left.setVisibility(View.GONE);
                iv_right.setVisibility(View.GONE);
                iv_car_center.setVisibility(View.VISIBLE);
//                VoiceUtil.getInstance().speek("对向预警");
                break;
        }
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

    private void initGif() {
        Glide.with(UIUtils.getContext())
                .load(R.mipmap.cw_up)
                .into(iv_up);
        Glide.with(UIUtils.getContext())
                .load(R.mipmap.cw_down)
                .into(iv_down);
        Glide.with(UIUtils.getContext())
                .load(R.mipmap.cw_left)
                .into(iv_left);
        Glide.with(UIUtils.getContext())
                .load(R.mipmap.cw_right)
                .into(iv_right);
        Glide.with(UIUtils.getContext())
                .load(R.mipmap.cw_subtend_1)
                .into(iv_car_center);
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
        initGif();
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
        return LBSUtils.calculateLineDistance(mLbs, startLatitude, startLongitude, endLatitude, endLongitude);
    }

    public void showLogView() {
        tv_log.setVisibility(tv_log.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
}
