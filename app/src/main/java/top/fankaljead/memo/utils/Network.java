package top.fankaljead.memo.utils;

import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

public class Network {
    public static boolean isWifi(Context context) {
//        ConnectivityManager connManager = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo mWifi = connManager
//                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
            return true;
        } else {
            return false;
        }
    }
}
