package wangfeixixi.cip.widget.carview.child;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import wangfeixixi.cip.R;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.com.base.UIUtils;

public class ChildCar {
    public static void addBenCar(ViewGroup viewGroup, CarBean bean) {
//        CarBean bean = CarUtils.carBean2Screen(carBean);

        ImageView iv = new ImageView(UIUtils.getContext());
        iv.setBackground(UIUtils.getDrawable(R.mipmap.car));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = bean.getLeftMargin();
        layoutParams.topMargin = bean.getTopMargin();
        layoutParams.width = (int) (bean.width * CarUtils.scale);
        layoutParams.height = (int) (bean.height * CarUtils.scale);


        viewGroup.addView(iv, layoutParams);
    }

}
