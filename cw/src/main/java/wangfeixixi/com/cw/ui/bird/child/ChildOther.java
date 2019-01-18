package wangfeixixi.com.cw.ui.bird.child;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import wangfeixixi.com.commen.utils.UIUtils;
import wangfeixixi.com.cw.R;
import wangfeixixi.com.cw.widget.carview.CarUtils;

public class ChildOther {

    public static ImageView addRightFloor(ViewGroup rl_carview) {
        ImageView iv_right_floor = new ImageView(UIUtils.getContext());
        iv_right_floor.setBackgroundResource(R.mipmap.right);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.leftMargin = (int) (CarUtils.getInstance().carViewWidth - CarUtils.getInstance().roadWidth);
        rllp.topMargin = -CarUtils.getInstance().carViewHeight;
        rllp.width = (int) (CarUtils.getInstance().roadWidth);
        rllp.height = (int) (CarUtils.getInstance().carViewHeight * 2);
        rl_carview.addView(iv_right_floor, rllp);
        return iv_right_floor;
    }

    public static ImageView addLeftFloor(ViewGroup rl_carview) {
        ImageView iv_left_floor = new ImageView(UIUtils.getContext());
        iv_left_floor.setBackgroundResource(R.mipmap.left);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.topMargin = -CarUtils.getInstance().carViewHeight;
        rllp.width = (int) (CarUtils.getInstance().roadWidth);
        rllp.height = (int) (CarUtils.getInstance().carViewHeight * 2);
        rl_carview.addView(iv_left_floor, rllp);
        return iv_left_floor;
    }

}
