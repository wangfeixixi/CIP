package wangfeixixi.cip.widget.carview.child;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import wangfeixixi.cip.R;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.com.base.UIUtils;

public class ChildCar {
    ImageView iv_ben;
    private final ImageView iv_other;

    private ChildCar() {
        iv_ben = new ImageView(UIUtils.getContext());
        iv_other = new ImageView(UIUtils.getContext());
    }

    public static class Inner {
        private static ChildCar instance = new ChildCar();
    }

    public static ChildCar getInstance() {
        return Inner.instance;
    }

    public void addBenCar(ViewGroup viewGroup, CarBean bean) {
        viewGroup.removeView(iv_ben);
        iv_ben.setBackgroundResource(R.mipmap.car_self);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = bean.getLeftMargin();
        layoutParams.topMargin = bean.getTopMargin();
        layoutParams.width = (int) (bean.width * CarUtils.scale);
        layoutParams.height = (int) (bean.height * CarUtils.scale);
        viewGroup.addView(iv_ben, layoutParams);
    }

    public void addOtherCar(ViewGroup viewGroup, CarBean bean) {
        viewGroup.removeView(iv_other);
        iv_other.setBackgroundResource(R.mipmap.car_other);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = bean.getLeftMargin();
        layoutParams.topMargin = bean.getTopMargin();
        layoutParams.width = (int) (bean.width * CarUtils.scale);
        layoutParams.height = (int) (bean.height * CarUtils.scale);
        viewGroup.addView(iv_other, layoutParams);
    }

}
