package wangfeixixi.cip.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.widget.carview.anim.TranslateAnim;
import wangfeixixi.cip.widget.carview.child.ChildCar;
import wangfeixixi.cip.widget.carview.child.ChildContainer;
import wangfeixixi.cip.widget.carview.child.ChildLog;
import wangfeixixi.cip.widget.carview.child.ChildOther;
import wangfeixixi.cip.widget.map.LBSUtils;
import wangfeixixi.com.base.ScreenUtils;
import wangfeixixi.com.base.VertionUtils;
import wangfeixixi.com.base.WifiUtils;
import wangfeixixi.com.base.data.DateUtils;
import wangfeixixi.com.bdvoice.VoiceUtil;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class NewMapActivity extends BaseActivity {

    private RelativeLayout rl_carview;
    private RelativeLayout rl_father;

    private ImageView iv_right_floor;
    private ImageView iv_left_floor;

    private FrameLayout mapContainer;
    private GaodeMapService mLbs;

    @Override
    protected void initView(Bundle savedInstanceState) {
        rl_father = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = (int) (ScreenUtils.getScreenWidth());
        layoutParams.height = (int) (ScreenUtils.getScreenHeight());
        getWindow().addContentView(rl_father, layoutParams);

        //添加分界线
        ChildContainer.addLine(rl_father);

        //添加carview
        rl_carview = ChildContainer.addCarView(rl_father);
        //添加道路信息
        iv_left_floor = ChildOther.addLeftFloor(rl_carview);
        iv_right_floor = ChildOther.addRightFloor(rl_carview);
        //添加地图
        mapContainer = ChildContainer.addMap(rl_father);
        mLbs = new GaodeMapService(this);
        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);
        //添加日志
        tv_warning = ChildLog.addLogView(rl_father);
    }


    @Override
    protected void initData() {
        //添加隐藏按钮
        Button btn = ChildContainer.addButton(rl_father);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_warning.setVisibility(tv_warning.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish();
                return true;
            }
        });
    }

    TextView tv_warning;
    public long lastTime = 0;

    @Override
    protected void onReceiveJsonBean(JsonRootBean bean) {
        //显示log
        addLog(bean);

        if (bean.hvDatas == null || bean.rvDatas == null) {
            return;
        }
        long nowTime = System.currentTimeMillis();
        //更新俯视图位置
        ChildCar.getInstance().addBenCar(rl_carview, bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            ChildCar.getInstance().addOtherCar(rl_carview, bean.rvDatas.get(i));

        //更新地图位置
        LBSUtils.addBenMarker(mLbs, bean.hvDatas);
        for (int i = 0; i < bean.rvDatas.size(); i++)
            LBSUtils.addOtherMarker(mLbs, bean.rvDatas.get(i));
        if (nowTime - lastTime > 2000) {

            //语音提示
            if (bean.rvDatas.get(0) != null && bean.rvDatas.get(0).fcwAlarm != 0)
                VoiceUtil.getInstance().speek("保持距离");
            //车道动画
            if (bean.hvDatas.speed != 0) {
                TranslateAnim.startTranslateAnim(iv_left_floor, iv_right_floor, 2000);
            } else {
                iv_left_floor.clearAnimation();
                iv_right_floor.clearAnimation();
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

        if (bean.hvDatas == null || bean.rvDatas == null) {
            float distance = LBSUtils.calculateLineDistance(mLbs, bean.hvDatas.latitude, bean.hvDatas.longitude, bean.rvDatas.get(0).latitude, bean.rvDatas.get(0).longitude);
            sb.append("\nmap距离：" + distance + " m");
            sb.append("\ndistance：" + bean.rvDatas.get(0).distance + " m");
            sb.append("\n距离差值：" + (distance - bean.rvDatas.get(0).distance + " m"));
            sb.append(bean.toString());
            tv_warning.setText(sb.toString());
            return;
        }

//        double jvli = 0;
//        float mixDiagonal = 0;
//        if (bean.rvDatas != null && bean.rvDatas.size() > 0) {
//            mixDiagonal = CarUtils.getInstance().getMixDiagonal(bean.rvDatas.get(0).x, bean.rvDatas.get(0).y);
//            double sqrt = Math.sqrt(Math.abs(bean.rvDatas.get(0).x) * Math.abs(bean.rvDatas.get(0).x) + Math.abs(bean.rvDatas.get(0).y) * Math.abs(bean.rvDatas.get(0).y));
//            sqrt -= mixDiagonal;
//            jvli = Double.parseDouble(new DecimalFormat("#.##").format(sqrt));
//        }
//        if (jvli >= mixDiagonal) {
//            sb.append("\n嘻嘻距离:" + (jvli - mixDiagonal));
//        } else {
//            sb.append("\n嘻嘻距离:" + 0);
//        }
//        sb.append("\n日期：" + String.valueOf(DateUtils.getCurrentDate(DateUtils.dateFormatYMDHMS)));

        sb.append(bean.toString());
        tv_warning.setText(sb.toString());
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
    protected void onDestroy() {
        super.onDestroy();
        mLbs.onDestroy();
        iv_right_floor.clearAnimation();
        iv_left_floor.clearAnimation();
        rl_carview.removeAllViews();
        rl_father.removeAllViews();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mLbs.onSaveInstanceState(outState);
    }
}
