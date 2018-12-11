package wangfeixixi.cip.ui.main;

import android.databinding.BindingAdapter;

import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarView;

public class ViewAdapter {
    @BindingAdapter({"carView"})
    public static void carView(CarView carView, final CarBean[] beans) {
        carView.updateBodys(beans);
    }

//    @BindingAdapter({"carView"})
//    public static void carView(SwipeRefreshLayout swipeRefreshLayout, final BindingCommand onRefreshCommand) {
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                if (onRefreshCommand != null) {
//                    onRefreshCommand.execute();
//                }
//            }
//        });
//    }

}
