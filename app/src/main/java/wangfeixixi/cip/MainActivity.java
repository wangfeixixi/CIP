package wangfeixixi.cip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.intellij.lang.annotations.Subst;

import java.util.ArrayList;

import wangfeixixi.com.lib.body.BodyBean;
import wangfeixixi.com.lib.first.FirstView;
import wangfeixixi.com.lib.utils.ThreadUtils;

public class MainActivity extends AppCompatActivity {
    private FirstView testView;

//    private BackgourndAllView carView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       CrashHandler.getInstance().init(this);
//        carView = findViewById(R.id.bg);
//
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
        testView.switchPoint();
//        carView.switchPoint();
    }

    public boolean isUpdating = false;

    public void update() {
        if (isUpdating) {
            updateDatas();
            int rand = (int) (Math.random() * 10);
            ArrayList<BodyBean> list = new ArrayList<>();
            for (int i = 0; i < rand; i++) {
                list.add(RandomBodyUtils.getRandowBody());
            }
            BodyBean[] beans = list.toArray(new BodyBean[list.size()]);
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

    private void updateDatas() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receviceData(){

    }

}
