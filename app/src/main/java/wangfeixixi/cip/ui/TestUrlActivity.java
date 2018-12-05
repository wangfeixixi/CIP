package wangfeixixi.cip.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import wangfeixixi.cip.R;
import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.com.base.SpUtil;

public class TestUrlActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_url;
    private Button btn_test;
    private TextView tv_result;
    private EditText et_method;
    private EditText et_param_name;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.test_url_activity);
    }

    @Override
    protected void initData() {
        et_url = findViewById(R.id.et_url);
        et_method = findViewById(R.id.et_method);
        et_param_name = findViewById(R.id.et_param_name);
        btn_test = findViewById(R.id.btn_test);
        tv_result = findViewById(R.id.tv_result);


        btn_test.setOnClickListener(this);


        et_url.setHint(SpUtil.getString("url", null));
        et_method.setHint(SpUtil.getString("method", null));
        et_param_name.setHint(SpUtil.getString("param", null));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                CharSequence et_url_s = et_url.getHint();
                CharSequence et_method_s = et_method.getHint();
                CharSequence et_param_name_s = et_param_name.getHint();

                SpUtil.putString("url", et_url.getText().toString());
                SpUtil.putString("method", et_method.getText().toString());
                SpUtil.putString("param", et_param_name.getText().toString());
                break;
        }
    }
}
