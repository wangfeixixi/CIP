package wangfeixixi.com.cw.ui.bird;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wangfeixixi.com.commen.fram.BaseDelegate;
import wangfeixixi.com.commen.utils.ScreenUtils;
import wangfeixixi.com.commen.utils.UIUtils;
import wangfeixixi.com.cw.ui.bird.anim.TranslateAnim;
import wangfeixixi.com.cw.ui.bird.child.ChildCar;
import wangfeixixi.com.cw.ui.bird.child.ChildContainer;
import wangfeixixi.com.cw.ui.bird.child.ChildLog;
import wangfeixixi.com.cw.ui.bird.child.ChildOther;
import wangfeixixi.com.cw.widget.carview.CarBean;
import wangfeixixi.com.cw.widget.map.LBSUtils;
import wangfeixixi.lbs.gaode.GaodeMapService;


public class BirdDelegate extends BaseDelegate {
    private RelativeLayout rl_carview;
    private RelativeLayout rl_father;

    private ImageView iv_right_floor;
    private ImageView iv_left_floor;

    private FrameLayout mapContainer;
    private GaodeMapService mLbs;
    TextView tv_warning;
    private Button btn_jump;

    @Override
    public int getRootLayoutId() {
        return 0;
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rl_father = new RelativeLayout(UIUtils.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = (int) (ScreenUtils.getScreenWidth());
        layoutParams.height = (int) (ScreenUtils.getScreenHeight());

        setRootView(rl_father);

    }

    @Override
    public void initWidget() {
        //添加分界线
        ChildContainer.addLine(rl_father);

        //添加carview
        rl_carview = ChildContainer.addCarView(rl_father);
        //添加道路信息
        iv_left_floor = ChildOther.addLeftFloor(rl_carview);
        iv_right_floor = ChildOther.addRightFloor(rl_carview);

        //添加日志
        tv_warning = ChildLog.addLogView(rl_father);


        //添加隐藏按钮
        btn_jump = ChildContainer.addButton(rl_father);
    }


    public void jumpClick(View.OnClickListener listener) {
        btn_jump.setOnClickListener(listener);
    }

    public void jumpLongClick(View.OnLongClickListener listener) {
        btn_jump.setOnLongClickListener(listener);
    }

    public void onCreate(Bundle savedInstanceState) {
        mapContainer = ChildContainer.addMap(rl_father);
        mLbs = new GaodeMapService(UIUtils.getContext());
        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);
        mLbs.changeTilt(90);//倾斜角度
    }

    protected void onResume() {
        mLbs.onResume();
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
        if (mLbs != null)
            mLbs.onDestroy();
        mapContainer.removeAllViews();
        rl_carview.removeAllViews();
        rl_father.removeAllViews();
    }

    public float getLbsDistance(float startLatitude, float startLongitude, float endLatitude, float endLongitude) {
        return LBSUtils.calculateLineDistance(startLatitude, startLongitude, endLatitude, endLongitude);
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

    public void setLogText(String s) {
        tv_warning.setText(s);
    }

    public void startRoadAnim(boolean isStart) {
        if (isStart) {
            TranslateAnim.startTranslateAnim(iv_left_floor, iv_right_floor, 2000);
        } else {
            iv_left_floor.clearAnimation();
            iv_right_floor.clearAnimation();
        }
    }

    public void carviewAddBenCar(CarBean hvDatas) {
        ChildCar.getInstance().addBenCar(rl_carview, hvDatas);
    }

    public void carviewAddOtherCar(CarBean bean) {
        ChildCar.getInstance().addOtherCar(rl_carview, bean);
    }

    public void LogViewShow() {
        tv_warning.setVisibility(tv_warning.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
    }
}
