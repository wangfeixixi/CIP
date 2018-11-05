package wangfeixixi.cip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.push.HttpService;
import wangfeixixi.cip.utils.RandomBodyUtils;
import wangfeixixi.cip.utils.ServiceUtils;
import wangfeixixi.com.lib.body.CarShelfBean;
import wangfeixixi.com.lib.first.FirstView;
import wangfeixixi.com.lib.utils.ThreadUtils;
import wangfeixixi.com.soaplib.beans.CarTest;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FirstView testView;
    private TextView tv_info;
    private View btn_switch;
    private View btn_stop;
    private View btn_update;
    private View btn_map;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.main_activity);
        tv_info = findViewById(R.id.tv_info);
        btn_switch = findViewById(R.id.btn_test);
        btn_stop = findViewById(R.id.btn_stop);
        btn_update = findViewById(R.id.btn_update);
        testView = findViewById(R.id.testView);
        btn_map = findViewById(R.id.btn_map);

        btn_switch.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_map.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                isUpdating = true;
                update();
                break;
            case R.id.btn_stop:
                testView.stop();
                ServiceUtils.stopService(HttpService.class);
                isUpdating = false;
                break;
            case R.id.btn_test:
                startActivity(new Intent(this, TestDataActivity.class));
                break;
            case R.id.btn_map:
                startActivity(new Intent(this, MapActivity.class));
                break;
        }
    }

    @Override
    protected void initData() {

    }

    public boolean isUpdating = false;

    public void update() {
        if (isUpdating) {
            int rand = (int) (Math.random() * 3);
            ArrayList<CarShelfBean> list = new ArrayList<>();
            for (int i = 0; i < rand; i++) {
                list.add(RandomBodyUtils.getRandowBody());
            }
            CarShelfBean[] beans = list.toArray(new CarShelfBean[list.size()]);
//            carView.updateBodys(beans);
            testView.updateBodys(beans);

            ThreadUtils.runOnUiThreadDelayed(new Runnable() {
                @Override
                public void run() {
                    update();
                }
            }, 10);
        } else {
            ThreadUtils.stop();
        }
    }

    @Override
    public void receiveDatas(CarTest carBean) {
    }
}
