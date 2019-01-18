package wangfeixixi.com.cw.ui.bird.child;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wangfeixixi.com.commen.utils.UIUtils;

public class ChildLog {
    public static TextView addLogView(RelativeLayout rl_father) {
        final TextView tv_warning = new TextView(UIUtils.getContext());
        tv_warning.setVisibility(View.GONE);
        tv_warning.setTextColor(Color.parseColor("#000000"));
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        rllp.width = ScreenUtils.getScreenWidth();
//        rllp.height = ScreenUtils.getScreenHeight();
        rl_father.addView(tv_warning, rllp);
        return tv_warning;
    }
}
