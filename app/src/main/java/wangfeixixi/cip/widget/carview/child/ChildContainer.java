package wangfeixixi.cip.widget.carview.child;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.com.base.UIUtils;

public class ChildContainer {
    public static RelativeLayout addFatherView(RelativeLayout rl_father) {
        RelativeLayout rl_carview = new RelativeLayout(UIUtils.getContext());
        rl_carview.setBackgroundColor(Color.parseColor("#f7f6f3"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = (int) (CarUtils.getCarViewWidth());
        layoutParams.height = (int) (CarUtils.getCarViewHeight());
        rl_father.addView(rl_carview, layoutParams);
        return rl_carview;
    }

    public static RelativeLayout addCarView(RelativeLayout rl_father) {
        RelativeLayout rl_carview = new RelativeLayout(UIUtils.getContext());
        rl_carview.setBackgroundColor(Color.parseColor("#f7f6f3"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = (int) (CarUtils.getCarViewWidth());
        layoutParams.height = (int) (CarUtils.getCarViewHeight());
        rl_father.addView(rl_carview, layoutParams);
        return rl_carview;
    }

    public static void swithLayout() {

    }

    public static FrameLayout addMap(RelativeLayout rl_father) {
        FrameLayout fl = new FrameLayout(UIUtils.getContext());
//        imageView.setBackgroundResource(R.mipmap.map);
        RelativeLayout.LayoutParams ivLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ivLP.width = (int) (CarUtils.getCarViewWidth());
        ivLP.topMargin = CarUtils.getCarViewHeight() + 8;
        ivLP.height = (int) (CarUtils.getCarViewHeight() / 2);
        rl_father.addView(fl, ivLP);
        return fl;
    }

    private static int lineHeight = 8;

    public static void addLine(RelativeLayout rl_father) {
        View line = new View(UIUtils.getContext());
        line.setBackgroundColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams ivLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ivLP.width = CarUtils.getCarViewWidth();
        ivLP.height = lineHeight;
        ivLP.topMargin = CarUtils.getCarViewHeight();
        rl_father.addView(line, ivLP);
    }
}
