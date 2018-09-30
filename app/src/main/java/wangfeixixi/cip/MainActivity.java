package wangfeixixi.cip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import wangfeixixi.com.thirdview.background.BackgourndAllView;
import wangfeixixi.com.thirdview.body.BodyBean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final BackgourndAllView carView = findViewById(R.id.bg);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                carView.addBody();
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carView.stop();
            }
        });
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand = (int) (Math.random() * 10);
                ArrayList<BodyBean> list = new ArrayList<>();
                for (int i = 0; i < rand; i++) {
                    list.add(RandomBodyUtils.getRandowBody());
                }
                BodyBean[] beans = list.toArray(new BodyBean[list.size()]);
                carView.updateBodys(beans);
            }
        });
    }
}
