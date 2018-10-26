package wangfeixixi.cip;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.utils.ServiceUtils;
import wangfeixixi.com.lib.body.CarShelfBean;
import wangfeixixi.com.lib.first.FirstView;
import wangfeixixi.com.lib.utils.ThreadUtils;
import wangfeixixi.com.soaplib.HttpUtils;
import wangfeixixi.com.soaplib.beans.CarTest;

public class MainActivity extends BaseActivity {
    private FirstView testView;
    private TextView tv_speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_speed = findViewById(R.id.tv_speed);


        findViewById(R.id.btn_switch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchPoint();
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
                isUpdating = false;
            }
        });
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUpdating = true;
                update();
            }
        });

        testView = findViewById(R.id.testView);
    }

    private void stop() {
        testView.stop();
//        carView.stop();
        ServiceUtils.stopService(HttpService.class);
    }

    private void switchPoint() {
//        ServiceUtils.startService(HttpService.class);

        HttpUtils.testEnqueue();
//        HttpUtils.testExecute();
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
        tv_speed.setText("收到数据");
    }
}
