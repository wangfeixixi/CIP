package wangfeixixi.cip.widget.map;

import android.graphics.BitmapFactory;
import android.view.animation.LinearInterpolator;

import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;

import wangfeixixi.cip.R;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.com.base.UIUtils;
import wangfeixixi.com.base.location.Gps;
import wangfeixixi.com.base.location.PositionUtil;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class LBSUtils {
    public static void addBenMarker(GaodeMapService mLbs, CarBean bean) {
        Gps gps = PositionUtil.gps84_To_Gcj02(bean.latitude / 10000000, bean.longitude / 10000000);
        LocationInfo local = new LocationInfo("本车", gps.getWgLat(), gps.getWgLon(), bean.heading);
        mLbs.addOrUpdateMarker(local, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.dot));
        mLbs.moveCamera(local, 18);
    }

    private static int alarm = 0;

    public static void addOtherMarker(GaodeMapService mLbs, CarBean bean) {
        Gps gps = PositionUtil.gps84_To_Gcj02(bean.latitude / 10000000, bean.longitude / 10000000);
        LocationInfo local = new LocationInfo("远车", gps.getWgLat(), gps.getWgLon(), bean.heading);
        if (bean.fcwAlarm != alarm) {
            mLbs.clearAllMarker();
            alarm = bean.fcwAlarm;
            Animation animation = new ScaleAnimation(0.8f, 1.2f, 0.8f, 1.2f);
            animation.setDuration(2000);
//            animation.setInterpolator(new LinearInterpolator());
            local.animation = animation;
        }
        mLbs.addOrUpdateMarker(local, BitmapFactory.decodeResource(UIUtils.getResources(), bean.fcwAlarm == 0 ? R.mipmap.dot_gray : R.mipmap.dot_red));
    }

    public static void dfas(GaodeMapService mLbs) {

    }

    public static float calculateLineDistance(GaodeMapService mLbs, double latStart, double lonStart, double latEnd, double lonEnd) {
        Gps gpsStart = PositionUtil.gps84_To_Gcj02(latStart / 10000000, lonStart / 10000000);
        Gps gpsEnd = PositionUtil.gps84_To_Gcj02(latEnd / 10000000, lonEnd / 10000000);
        return mLbs.calculateLineDistance(new LocationInfo(gpsStart.getWgLat(), gpsStart.getWgLon()), new LocationInfo(gpsEnd.getWgLat(), gpsEnd.getWgLon()));
    }

}
