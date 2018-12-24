package wangfeixixi.cip.widget.carview.child;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import wangfeixixi.cip.R;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.com.base.UIUtils;

public class ChildOther {

    public static ImageView addRightHouse(ViewGroup rl_carview) {
        ImageView iv_right_house = new ImageView(UIUtils.getContext());
        iv_right_house.setBackgroundResource(R.mipmap.right_house);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.leftMargin = (int) (CarUtils.getCarViewWidth() - CarUtils.getRoadWidth()) + 40;
        rllp.topMargin = -CarUtils.getCarViewHeight();
        rllp.width = (int) (CarUtils.getHouseWidth());
        rllp.height = (int) (CarUtils.getCarViewHeight()) * 2;
        rl_carview.addView(iv_right_house, rllp);
        return iv_right_house;
    }

    public static ImageView addLeftHouse(ViewGroup rl_carview) {
        ImageView iv_left_house = new ImageView(UIUtils.getContext());
        iv_left_house.setBackgroundResource(R.mipmap.left_house);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.topMargin = -CarUtils.getCarViewHeight();
        rllp.width = (int) (CarUtils.getHouseWidth());
        rllp.height = (int) (CarUtils.getCarViewHeight()) * 2;
        rl_carview.addView(iv_left_house, rllp);
        return iv_left_house;
    }

    public static ImageView addRightFloor(ViewGroup rl_carview) {
        ImageView iv_right_floor = new ImageView(UIUtils.getContext());
        iv_right_floor.setBackgroundResource(R.mipmap.right);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.leftMargin = (int) (CarUtils.getCarViewWidth() - CarUtils.getRoadWidth());
        rllp.topMargin = -CarUtils.getCarViewHeight();
        rllp.width = (int) (CarUtils.getRoadWidth());
        rllp.height = (int) (CarUtils.getCarViewHeight() * 2);
        rl_carview.addView(iv_right_floor, rllp);
        return iv_right_floor;
    }

    public static ImageView addLeftFloor(ViewGroup rl_carview) {
        ImageView iv_left_floor = new ImageView(UIUtils.getContext());
        iv_left_floor.setBackgroundResource(R.mipmap.left);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.topMargin = -CarUtils.getCarViewHeight();
        rllp.width = (int) (CarUtils.getRoadWidth());
        rllp.height = (int) (CarUtils.getCarViewHeight() * 2);
        rl_carview.addView(iv_left_floor, rllp);
        return iv_left_floor;
    }

}
