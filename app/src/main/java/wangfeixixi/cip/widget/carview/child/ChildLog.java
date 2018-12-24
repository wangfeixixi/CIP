package wangfeixixi.cip.widget.carview.child;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wangfeixixi.com.base.ScreenUtils;
import wangfeixixi.com.base.UIUtils;

public class ChildLog {

    public static TextView addLogView(RelativeLayout rl_father) {
        final TextView tv_warning = new TextView(UIUtils.getContext());
        tv_warning.setVisibility(View.GONE);
        tv_warning.setText("数据日志");
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.width = ScreenUtils.getScreenWidth();
        rllp.height = ScreenUtils.getScreenHeight();
        rl_father.addView(tv_warning, rllp);

        rl_father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_warning.setVisibility(tv_warning.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        return tv_warning;
    }
}
