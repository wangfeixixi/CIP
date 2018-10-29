package wangfeixixi.cip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class MapActivity extends BaseActivity {

    private GaodeMapService mLbs;
    private FrameLayout mapContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapContainer = findViewById(R.id.fl_map_container);
        mLbs = new GaodeMapService(this);

        mapContainer.addView(mLbs.getMap());
        mLbs.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLbs.onResume();
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
}
