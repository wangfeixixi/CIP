package wangfeixixi.cip.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import wangfeixixi.cip.R;
import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.cip.widget.carview.anim.TranslateAnim;
import wangfeixixi.cip.widget.carview.child.ChildCar;
import wangfeixixi.cip.widget.udp.UDPUtils;
import wangfeixixi.cip.widget.udp.server.UDPResultListener;

public class NewMapActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl_carview;
    private RelativeLayout rl_father;

    private UDPResultListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_url_activity);

        rl_father = findViewById(R.id.rl_father);

        //添加carview
        rl_carview = new RelativeLayout(this);
        addCarView(rl_carview);
        //添加道路信息
        addLeftFloor();
        addRightFloor();
        addLeftHouse();
        addRightHouse();


        //添加地图
        addMap(rl_father);


        //数据监听
        listener = new UDPResultListener() {
            @Override
            public void onResultListener(final JsonRootBean bean) {
                final ArrayList<CarBean> list = new ArrayList<>();
                if (bean.hvDatas != null)
                    list.add(bean.hvDatas);
                if (bean.rvDatas != null)
                    list.addAll(bean.rvDatas);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (list.size() < 2) {
//                            tv_warning.setText(bean.toString());
                            return;
                        }
                        receiveCars(list);
                    }
                });
            }
        };
    }

    private void addRightHouse() {
        ImageView iv_right_house = new ImageView(this);
        iv_right_house.setBackgroundResource(R.mipmap.right_house);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.leftMargin = (int) (CarUtils.getCarViewWidth() - CarUtils.getRoadWidth());
        rllp.topMargin = -CarUtils.getCarViewHeight();
        rllp.width = (int) (CarUtils.getHouseWidth());
        rllp.height = (int) (CarUtils.getCarViewHeight()) * 2;
        rl_carview.addView(iv_right_house, rllp);
        TranslateAnim.switchSpeedAnim(iv_right_house, 100);
    }

    private void addLeftHouse() {
        ImageView iv_left_house = new ImageView(this);
        iv_left_house.setBackgroundResource(R.mipmap.left_house);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.topMargin = -CarUtils.getCarViewHeight();
        rllp.width = (int) (CarUtils.getHouseWidth());
        rllp.height = (int) (CarUtils.getCarViewHeight()) * 2;
        rl_carview.addView(iv_left_house, rllp);
    }

    private void addRightFloor() {
        ImageView iv_right_floor = new ImageView(this);
        iv_right_floor.setBackgroundResource(R.mipmap.right_floor);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.leftMargin = (int) (CarUtils.getCarViewWidth() - CarUtils.getRoadWidth());
        rllp.width = (int) (CarUtils.getRoadWidth());
        rllp.height = (int) (CarUtils.getCarViewHeight());
        rl_carview.addView(iv_right_floor, rllp);
    }

    private void addLeftFloor() {
        ImageView iv_left_floor = new ImageView(this);
        iv_left_floor.setBackgroundResource(R.mipmap.left_floor);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.width = (int) (CarUtils.getRoadWidth());
        rllp.height = (int) (CarUtils.getCarViewHeight());
        rl_carview.addView(iv_left_floor, rllp);
    }

    private void addMap(RelativeLayout rl_father) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.mipmap.map);
        RelativeLayout.LayoutParams ivLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ivLP.width = (int) (CarUtils.getCarViewWidth());
        ivLP.topMargin = CarUtils.getCarViewHeight();
        ivLP.height = (int) (CarUtils.getCarViewHeight() / 2);
        rl_father.addView(imageView, ivLP);
    }


    private void addCarView(RelativeLayout rl_carview) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = (int) (CarUtils.getCarViewWidth());
        layoutParams.height = (int) (CarUtils.getCarViewHeight());
        rl_father.addView(rl_carview, layoutParams);
    }

    @Override
    protected void onStart() {
        super.onStart();
        UDPUtils.startServer(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        UDPUtils.stopServer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    public long lastTime = 0;

    public void receiveCars(ArrayList<CarBean> beans) {
        for (int i = 0; i < beans.size(); i++) {
            if (i == 0) {
                ChildCar.addBenCar(rl_carview, beans.get(i));
            } else {
                ChildCar.addOtherCar(rl_carview, beans.get(i));
            }
        }
    }
}
