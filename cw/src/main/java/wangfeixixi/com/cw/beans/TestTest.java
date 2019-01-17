package wangfeixixi.com.cw.beans;//package wangfeixixi.cip.beans;
//
//import com.amap.api.maps.AMapException;
//import com.amap.api.maps.model.LatLng;
//
//import wangfeixixi.lbs.LocationInfo;
//
//public class TestTest {
//    public static double pi = 3.1415926535897932384626;
//    public static double a = 6378245.0;
//    public static double ee = 0.00669342162296594323;
//
//
//    //转换距离
//    public static float calculateLineDistance(double latStart, double lonStart, double latEnd, double lonEnd) {
//        Gps gpsStart = gps84_To_Gcj02(latStart / 10000000, lonStart / 10000000);
//        Gps gpsEnd = gps84_To_Gcj02(latEnd / 10000000, lonEnd / 10000000);
//        return calculateLineDistance(new LocationInfo(gpsStart.wgLat, gpsStart.wgLon), new LocationInfo(gpsEnd.wgLat, gpsEnd.wgLon));
//    }
//
//    /**
//     * 84 to 火星坐标系 (GCJ-02) World Geodetic System ==> Mars Geodetic System
//     *
//     * @param lat
//     * @param lon
//     * @return
//     */
//    public static Gps gps84_To_Gcj02(double lat, double lon) {
////        if (outOfChina(lat, lon)) {
////            return null;
////        }
//
////        LogUtils.d("是否为中国坐标" + outOfChina(lat, lon));
//
//        double dLat = transformLat(lon - 105.0, lat - 35.0);
//        double dLon = transformLon(lon - 105.0, lat - 35.0);
//        double radLat = lat / 180.0 * pi;
//        double magic = Math.sin(radLat);
//        magic = 1 - ee * magic * magic;
//        double sqrtMagic = Math.sqrt(magic);
//        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
//        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
//        double mgLat = lat + dLat;
//        double mgLon = lon + dLon;
//
//        Gps gps = new Gps();
//        gps.wgLat = mgLat;
//        gps.wgLon = mgLon;
//        return gps;
//    }
//
//    public static double transformLat(double x, double y) {
//        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
//                + 0.2 * Math.sqrt(Math.abs(x));
//        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
//        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
//        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
//        return ret;
//    }
//
//    public static double transformLon(double x, double y) {
//        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
//                * Math.sqrt(Math.abs(x));
//        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
//        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
//        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
//                * pi)) * 2.0 / 3.0;
//        return ret;
//    }
//
//
//    //根据经纬度计算距离
//    public static float calculateLineDistance(LatLng startLatLng, LatLng endLatLng) {
//        if (startLatLng != null && endLatLng != null) {
//            try {
//                double var2 = 0.01745329251994329D;
//                double startLongitude = startLatLng.longitude;
//                double startLatitude = startLatLng.latitude;
//                double endLongitude = endLatLng.longitude;
//                double endLatitude = endLatLng.latitude;
//                startLongitude *= 0.01745329251994329D;
//                startLatitude *= 0.01745329251994329D;
//                endLongitude *= 0.01745329251994329D;
//                endLatitude *= 0.01745329251994329D;
//                double var12 = Math.sin(startLongitude);
//                double var14 = Math.sin(startLatitude);
//                double var16 = Math.cos(startLongitude);
//                double var18 = Math.cos(startLatitude);
//                double var20 = Math.sin(endLongitude);
//                double var22 = Math.sin(endLatitude);
//                double var24 = Math.cos(endLongitude);
//                double var26 = Math.cos(endLatitude);
//                double[] var28 = new double[3];
//                double[] var29 = new double[3];
//                var28[0] = var18 * var16;
//                var28[1] = var18 * var12;
//                var28[2] = var14;
//                var29[0] = var26 * var24;
//                var29[1] = var26 * var20;
//                var29[2] = var22;
//                double var30 = Math.sqrt((var28[0] - var29[0]) * (var28[0] - var29[0]) + (var28[1] - var29[1]) * (var28[1] - var29[1]) + (var28[2] - var29[2]) * (var28[2] - var29[2]));
//                return (float) (Math.asin(var30 / 2.0D) * 1.27420015798544E7D);
//            } catch (Throwable var32) {
//                var32.printStackTrace();
//                return 0.0F;
//            }
//        } else {
//            try {
//                throw new AMapException("非法坐标值");
//            } catch (AMapException var33) {
//                var33.printStackTrace();
//                return 0.0F;
//            }
//        }
//    }
//
//
//    public static class Gps {
//        private double wgLat;
//        private double wgLon;
//    }
//}
