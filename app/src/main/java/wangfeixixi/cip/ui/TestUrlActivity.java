package wangfeixixi.cip.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wangfeixixi.cip.R;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.cip.widget.carview.child.ChildCar;
import wangfeixixi.com.base.UIUtils;

public class TestUrlActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl_container;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_url_activity);

        tv = findViewById(R.id.tv);

        findViewById(R.id.btn_test).setOnClickListener(this);
        rl_container = findViewById(R.id.rl_container);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                testadd(0, 0);
                testadd(5, 5);
                testadd(5, -5);
                testadd(-5, 5);
                testadd(-5, -5);

                break;

        }
    }

    private void testadd(int x, int y) {
        CarBean bean = new CarBean();
        bean.x = x;
        bean.y = y;


//        addBenCar(bean);
        ChildCar.addBenCar(rl_container, bean);
    }
}
