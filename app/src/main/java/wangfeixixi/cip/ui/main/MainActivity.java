package wangfeixixi.cip.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.tatarka.bindingcollectionadapter2.BR;
import wangfeixixi.cip.R;
import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.databinding.MainActivityBinding;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.cip.widget.carview.utils.BitmapUtils;
import wangfeixixi.com.base.UIUtils;
import wangfeixixi.com.base.test.LogUtils;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MainActivity extends BaseActivity<MainActivityBinding, MainViewModel> {

    private GaodeMapService mLbs;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.main_activity;
    }

    @Override
    public int initVariableId() {
        return BR.mainViewModel;
    }

    @Override
    public MainViewModel initViewModel() {
        return ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mLbs = new GaodeMapService(this);
        binding.flMapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        mLbs.setLocationRes(R.mipmap.car);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLbs.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLbs.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLbs.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mLbs.onSaveInstanceState(outState);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveCars(JsonRootBean bean) {
        mLbs.clearAllMarker();
        LogUtils.d(bean);
        ArrayList<CarBean> list = new ArrayList<>();
        list.add(new CarBean(0, bean.hvDatas.x, bean.hvDatas.y, bean.hvDatas.longitude, bean.hvDatas.latitude, CarUtils.carWidth, CarUtils.carLength));
        mLbs.addOrUpdateMarker(new LocationInfo("自身", bean.hvDatas.latitude, bean.hvDatas.longitude), BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.car), 0.1f));
        mLbs.moveCamera(new LocationInfo("自身", bean.hvDatas.latitude, bean.hvDatas.longitude), 20);

        for (int i = 0; i < bean.rvDatas.size(); i++) {
            list.add(new CarBean(0, bean.rvDatas.get(i).x, bean.rvDatas.get(i).y, bean.rvDatas.get(i).longitude, bean.rvDatas.get(i).latitude, CarUtils.carWidth, CarUtils.carLength));
            mLbs.addOrUpdateMarker(new LocationInfo(String.valueOf(i), bean.rvDatas.get(i).latitude, bean.rvDatas.get(i).longitude), BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.car), 0.1f));
        }
        viewModel.carBeas.set(list.toArray(new CarBean[list.size()]));
        double tem = Math.sqrt(Math.abs(list.get(1).x) * Math.abs(list.get(1).x) + Math.abs(list.get(1).y) * Math.abs(list.get(1).y));
        viewModel.warningText.set("x  " + list.get(1).x + "  y  " + list.get(1).y
                + "\n" + "latitude  " + list.get(1).latitude + "  longitude  " + list.get(1).longitude
                + "\n" + "距离长度为    " + tem);
    }
}
