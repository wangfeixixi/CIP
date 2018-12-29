package wangfeixixi.cip.widget.map;

import android.graphics.BitmapFactory;

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
        LocationInfo local = new LocationInfo("本车", gps.getWgLat(), gps.getWgLon());
        mLbs.addOrUpdateMarker(local, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.dot));
        mLbs.moveCamera(local, 18);
    }

    public static void addOtherMarker(GaodeMapService mLbs, CarBean bean) {
        Gps gps = PositionUtil.gps84_To_Gcj02(bean.latitude / 10000000, bean.longitude / 10000000);
        LocationInfo local = new LocationInfo("远车", gps.getWgLat(), gps.getWgLon());
//        mLbs.removeMarker("远车");
        mLbs.addOrUpdateMarker(local, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.dot_red));
    }

    public static float calculateLineDistance(GaodeMapService mLbs, double latStart, double lonStart, double latEnd, double lonEnd) {
        Gps gpsStart = PositionUtil.gps84_To_Gcj02(latStart / 10000000, lonStart / 10000000);
        Gps gpsEnd = PositionUtil.gps84_To_Gcj02(latEnd / 10000000, lonEnd / 10000000);
        return mLbs.calculateLineDistance(new LocationInfo(gpsStart.getWgLat(), gpsStart.getWgLon()), new LocationInfo(gpsEnd.getWgLat(), gpsEnd.getWgLon()));
    }

}
