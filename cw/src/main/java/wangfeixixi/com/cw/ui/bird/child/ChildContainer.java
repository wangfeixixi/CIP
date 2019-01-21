package wangfeixixi.com.cw.ui.bird.child;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import wangfeixixi.com.commen.utils.ScreenUtils;
import wangfeixixi.com.commen.utils.UIUtils;
import wangfeixixi.com.cw.ui.bird.CarUtils;

public class ChildContainer {
    public static RelativeLayout addFatherView(RelativeLayout rl_father) {
        RelativeLayout rl_carview = new RelativeLayout(UIUtils.getContext());
        rl_carview.setBackgroundColor(Color.parseColor("#f7f6f3"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = (int) (CarUtils.getInstance().carViewWidth);
        layoutParams.height = (int) (CarUtils.getInstance().carViewHeight);
        rl_father.addView(rl_carview, layoutParams);
        return rl_carview;
    }

    public static RelativeLayout addCarView(RelativeLayout rl_father) {
        RelativeLayout rl_carview = new RelativeLayout(UIUtils.getContext());
        rl_carview.setBackgroundColor(Color.parseColor("#f7f6f3"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = (int) (CarUtils.getInstance().carViewWidth);
        layoutParams.height = (int) (CarUtils.getInstance().carViewHeight);
        rl_father.addView(rl_carview, layoutParams);
        return rl_carview;
    }

    public static void swithLayout() {

    }

    public static FrameLayout addMap(RelativeLayout rl_father) {
        FrameLayout fl = new FrameLayout(UIUtils.getContext());
//        imageView.setBackgroundResource(R.mipmap.map);
        RelativeLayout.LayoutParams ivLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ivLP.width = (int) (CarUtils.getInstance().carViewWidth);
        ivLP.topMargin = CarUtils.getInstance().carViewHeight + 8;
        ivLP.height = (int) (CarUtils.getInstance().carViewHeight / 2);
        rl_father.addView(fl, ivLP);
        return fl;
    }

    public static FrameLayout addAllMap(RelativeLayout rl_father) {
        FrameLayout fl = new FrameLayout(UIUtils.getContext());
//        imageView.setBackgroundResource(R.mipmap.map);
        RelativeLayout.LayoutParams ivLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        ivLP.width = (int) (CarUtils.getInstance().carViewWidth);
//        ivLP.topMargin = CarUtils.getInstance().carViewHeight + 8;
//        ivLP.height = (int) (CarUtils.getInstance().carViewHeight / 2);
        rl_father.addView(fl, ivLP);
        return fl;
    }

    private static int lineHeight = 8;

    public static void addLine(RelativeLayout rl_father) {
        View line = new View(UIUtils.getContext());
        line.setBackgroundColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams ivLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ivLP.width = CarUtils.getInstance().carViewWidth;
        ivLP.height = lineHeight;
        ivLP.topMargin = CarUtils.getInstance().carViewHeight;
        rl_father.addView(line, ivLP);
    }

    public static Button addButton(RelativeLayout rl_father) {
        Button button = new Button(UIUtils.getContext());
        button.setBackgroundColor(Color.parseColor("#00ffffff"));
        RelativeLayout.LayoutParams ivLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivLP.topMargin = ScreenUtils.getScreenHeight() - 120;
        rl_father.addView(button, ivLP);
        return button;
    }
}
