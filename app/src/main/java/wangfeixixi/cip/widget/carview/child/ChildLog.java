package wangfeixixi.cip.widget.carview.child;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wangfeixixi.com.base.ScreenUtils;
import wangfeixixi.com.base.UIUtils;
import wangfeixixi.com.base.VertionUtils;
import wangfeixixi.com.base.WifiUtils;
import wangfeixixi.com.base.mvvm.utils.ToastUtils;

public class ChildLog {

    public static long lastBackTime = 0;

    public static TextView addLogView(RelativeLayout rl_father) {
        final TextView tv_warning = new TextView(UIUtils.getContext());
        tv_warning.setVisibility(View.GONE);
        tv_warning.setTextColor(Color.parseColor("#000000"));

        tv_warning.setText(initLog());
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        rllp.width = ScreenUtils.getScreenWidth();
//        rllp.height = ScreenUtils.getScreenHeight();
        rl_father.addView(tv_warning, rllp);

        rl_father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long nowBackTime = System.currentTimeMillis();
                if (nowBackTime - lastBackTime < 300) {
                    tv_warning.setVisibility(tv_warning.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                } else {
                    lastBackTime = nowBackTime;
                }
            }
        });
        return tv_warning;
    }

    private static String initLog() {
        StringBuffer sb = new StringBuffer();
        String wifiName = WifiUtils.getWifiName();
        sb.append("/nwifiName:" + wifiName);
        sb.append("/版本号：" + VertionUtils.getVersionCode());
        sb.append("/版本名称：" + VertionUtils.getVersionName());


        return sb.toString();
    }
}
