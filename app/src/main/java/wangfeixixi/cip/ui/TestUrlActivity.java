package wangfeixixi.cip.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import wangfeixixi.cip.R;
import wangfeixixi.cip.widget.carview.anim.BlinkAnim;
import wangfeixixi.cip.widget.carview.anim.TranslateAnim;

public class TestUrlActivity extends AppCompatActivity implements View.OnClickListener {

    private View iv_car;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_url_activity);

        findViewById(R.id.btn_test).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);

        iv_car = findViewById(R.id.iv_car);


    }

    public boolean isStart = false;

    private long time = 2000;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                BlinkAnim.blink(iv_car);
                break;
            case R.id.btn_plus:
//                time += 200;
//                Animation animation = iv_car.getAnimation();
//
//                animation.getDuration();
//
//                iv_car.clearAnimation();
//                iv_car.startAnimation(TranslateAnim.getAnimation(time));

                TranslateAnim.switchSpeedAnim(iv_car, 300);

                break;
            case R.id.btn_minus:

                break;
        }
    }
}
