package wangfeixixi.cip.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiUtils {

//    public static InetAddress getWifiIp() {
//        WifiManager wifiMgr = (WifiManager) UIUtils.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        if (isWifiEnabled()) {
//            int ipAsInt = wifiMgr.getConnectionInfo().getIpAddress();
//            if (ipAsInt == 0) {
//                return null;
//            } else {
//                return Util.intToInet(ipAsInt);
//            }
//        } else {
//            return null;
//        }
//    }

    public static String getWifiName() {
        WifiManager wifiMgr = (WifiManager) UIUtils.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int wifiState = wifiMgr.getWifiState();
        WifiInfo info = wifiMgr.getConnectionInfo();
        return info.getSSID();
    }

//    public static boolean isWifiEnabled() {
//        WifiManager wifiMgr = (WifiManager) UIUtils.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        if (wifiMgr.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
//            ConnectivityManager connManager = (ConnectivityManager) UIUtils.getContext()
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            return wifiInfo.isConnected();
//        } else {
//            return false;
//        }
//    }
}
