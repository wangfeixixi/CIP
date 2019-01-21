package wangfeixixi.com.cw.ui.bird.child;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import wangfeixixi.com.commen.utils.UIUtils;
import wangfeixixi.com.cw.R;
import wangfeixixi.com.cw.beans.CarBean;
import wangfeixixi.com.cw.ui.bird.CarUtils;
import wangfeixixi.com.cw.ui.bird.anim.BlinkAnim;

public class ChildCar {
    ImageView iv_ben;
    private final ImageView iv_other;

    private ChildCar() {
        iv_ben = new ImageView(UIUtils.getContext());
        iv_ben.setBackgroundResource(R.mipmap.car_self);

        iv_other = new ImageView(UIUtils.getContext());
        iv_other.setBackgroundResource(R.mipmap.car_other);
    }

    public static class Inner {
        private static ChildCar instance = new ChildCar();
    }

    public static ChildCar getInstance() {
        return Inner.instance;
    }

    public float benHeading;

    public void addBenCar(ViewGroup viewGroup, CarBean bean) {
        viewGroup.removeView(iv_ben);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = CarUtils.getInstance().getLeftMargin(bean.averagex, bean.width);
        layoutParams.topMargin = CarUtils.getInstance().getTopMargin(bean.averagey, bean.height);
        layoutParams.width = (int) (bean.width * CarUtils.getInstance().scaleCarPixel);
        layoutParams.height = (int) (bean.height * CarUtils.getInstance().scaleCarPixel);
        viewGroup.addView(iv_ben, layoutParams);
        benHeading = bean.heading;
    }

    public void addOtherCar(ViewGroup viewGroup, CarBean bean) {
        CarUtils.getInstance().changeScale(bean);
        viewGroup.removeView(iv_other);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = CarUtils.getInstance().getLeftMargin(bean.averagex, bean.width);
        layoutParams.topMargin = CarUtils.getInstance().getTopMargin(bean.averagey, bean.height);
        layoutParams.width = (int) (bean.width * CarUtils.getInstance().scaleCarPixel);
        layoutParams.height = (int) (bean.height * CarUtils.getInstance().scaleCarPixel);
        viewGroup.addView(iv_other, layoutParams);
        iv_other.setRotation(Math.abs(bean.heading - benHeading));
        BlinkAnim.blink(iv_other, bean.cw != 0);
    }
}
