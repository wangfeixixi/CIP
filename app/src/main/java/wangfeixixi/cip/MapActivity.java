package wangfeixixi.cip;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.fram.UIUtils;
import wangfeixixi.com.soaplib.beans.CarTest;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.OnLocationListener;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MapActivity extends BaseActivity {

    private GaodeMapService mLbs;
    private FrameLayout mapContainer;
    private View btn_update_location;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.map_activity);
        mapContainer = findViewById(R.id.fl_map_container);
        mLbs = new GaodeMapService(this);

        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);

        btn_update_location = findViewById(R.id.btn_update);

    }

    @Override
    protected void initData() {
        btn_update_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLbs.startOnceLocation();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLbs.onResume();

        mLbs.setLocationChangeListener(new OnLocationListener() {
            @Override
            public void onLocationChange(LocationInfo locationInfo) {
//                ToastUtils.showShort(locationInfo.name + locationInfo.address);
                locationInfo.key = "自身坐标车";
                mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car_map));
                mLbs.moveCamera(locationInfo, 20);

            }
        });

        mLbs.startOnceLocation();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLbs.onDestroy();
    }

    @Override
    protected void receiveDatas(CarTest carBean) {

    }
}
