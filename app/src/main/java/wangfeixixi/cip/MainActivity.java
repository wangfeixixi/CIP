package wangfeixixi.cip;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import wangfeixixi.com.lib.base.BaseActivity;
import wangfeixixi.com.lib.body.CarBean;
import wangfeixixi.com.lib.first.FirstView;
import wangfeixixi.com.lib.utils.ThreadUtils;
import wangfeixixi.com.soaplib.soaputil.HttpUtils;

public class MainActivity extends BaseActivity {
    private FirstView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    private void switchPoint() {
//        testView.switchPoint();
//        carView.switchPoint();
//        HttpUtils.test(this);
        HttpUtils.testNew(this);
    }

    public boolean isUpdating = false;

    public void update() {
        if (isUpdating) {
            int rand = (int) (Math.random() * 3);
            ArrayList<CarBean> list = new ArrayList<>();
            for (int i = 0; i < rand; i++) {
                list.add(RandomBodyUtils.getRandowBody());
            }
            CarBean[] beans = list.toArray(new CarBean[list.size()]);
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
}
