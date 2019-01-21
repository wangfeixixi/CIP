package wangfeixixi.com.cw.widget.map;

import android.graphics.BitmapFactory;

import com.amap.api.maps.AMapException;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;

import wangfeixixi.com.commen.utils.UIUtils;
import wangfeixixi.com.commen.utils.location.Gps;
import wangfeixixi.com.commen.utils.location.PositionUtil;
import wangfeixixi.com.cw.R;
import wangfeixixi.com.cw.beans.CarBean;
import wangfeixixi.lbs.LocationInfo;
import wangfeixixi.lbs.gaode.GaodeMapService;

public class LBSUtils {
    private static long lastTime = 0;

    public static void addBenMarker(GaodeMapService mLbs, CarBean bean) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - lastTime > 500) {
            Gps gps = PositionUtil.gps84_To_Gcj02(bean.latitude / 10000000, bean.longitude / 10000000);
            LocationInfo local = new LocationInfo("本车", gps.getLatitude(), gps.getLongitude(), bean.heading);
            mLbs.addOrUpdateMarker(local, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.dot));
            mLbs.moveCamera(local, 20);
            lastTime = nowTime;
        }
    }

    private static int alarm = 0;

    public static void addOtherMarker(GaodeMapService mLbs, CarBean bean) {
        Gps gps = PositionUtil.gps84_To_Gcj02(bean.latitude / 10000000, bean.longitude / 10000000);
        LocationInfo local = new LocationInfo("远车", gps.getLatitude(), gps.getLongitude(), bean.heading);
        if (bean.cw != alarm) {
            mLbs.clearAllMarker();
            alarm = bean.cw;
            Animation animation = new ScaleAnimation(0.8f, 1.2f, 0.8f, 1.2f);
            animation.setDuration(2000);
//            animation.setInterpolator(new LinearInterpolator());
            local.animation = animation;
        }
        mLbs.addOrUpdateMarker(local, BitmapFactory.decodeResource(UIUtils.getResources(), bean.cw == 0 ? R.mipmap.dot_gray : R.mipmap.dot_red));
    }

    public static float calculateLineDistance(double latStart, double lonStart, double latEnd, double lonEnd) {
        Gps gpsStart = PositionUtil.gps84_To_Gcj02(latStart / 10000000, lonStart / 10000000);
        Gps gpsEnd = PositionUtil.gps84_To_Gcj02(latEnd / 10000000, lonEnd / 10000000);
        return calculateLineDistance(gpsStart, gpsEnd);
    }

    public static float calculateLineDistance(Gps start, Gps end) {
        if (start != null && end != null) {
            try {
                double var2 = 0.01745329251994329D;
                double var4 = start.longitude;
                double var6 = start.latitude;
                double var8 = end.longitude;
                double var10 = end.latitude;
                var4 *= 0.01745329251994329D;
                var6 *= 0.01745329251994329D;
                var8 *= 0.01745329251994329D;
                var10 *= 0.01745329251994329D;
                double var12 = Math.sin(var4);
                double var14 = Math.sin(var6);
                double var16 = Math.cos(var4);
                double var18 = Math.cos(var6);
                double var20 = Math.sin(var8);
                double var22 = Math.sin(var10);
                double var24 = Math.cos(var8);
                double var26 = Math.cos(var10);
                double[] var28 = new double[3];
                double[] var29 = new double[3];
                var28[0] = var18 * var16;
                var28[1] = var18 * var12;
                var28[2] = var14;
                var29[0] = var26 * var24;
                var29[1] = var26 * var20;
                var29[2] = var22;
                double var30 = Math.sqrt((var28[0] - var29[0]) * (var28[0] - var29[0]) + (var28[1] - var29[1]) * (var28[1] - var29[1]) + (var28[2] - var29[2]) * (var28[2] - var29[2]));
                return (float) (Math.asin(var30 / 2.0D) * 1.27420015798544E7D);
            } catch (Throwable var32) {
                var32.printStackTrace();
                return 0.0F;
            }
        } else {
            try {
                throw new AMapException("非法坐标值");
            } catch (AMapException var33) {
                var33.printStackTrace();
                return 0.0F;
            }
        }
    }
}
