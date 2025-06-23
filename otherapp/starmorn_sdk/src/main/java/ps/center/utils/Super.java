package ps.center.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/Super.class */
public class Super {

    @SuppressLint({"StaticFieldLeak"})
    private static Context context;

    private Super() {
        throw new UnsupportedOperationException("禁止实例化这个对象");
    }

    public static void init(Context context2) {
        context = context2.getApplicationContext();
    }

    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("请先调用初始化ControlCenter.init( getApplicationContext() );");
    }

    public static int getWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHeight() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dp2px(float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
    }

    public static int px2dp(float px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((px / scale) + 0.5f);
    }

    public static int sp2px(float sp) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) ((sp * fontScale) + 0.5f);
    }

    public static int px2sp(float px) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) ((px / fontScale) + 0.5f);
    }

    public static String getSelfVersion() {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 16384);
            return String.valueOf(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return BuildConfig.VERSION_NAME;
        }
    }

    public static String getSelfVersionCode() {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 16384);
            return String.valueOf(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0";
        }
    }

    @Deprecated
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getIMEI(Context context2) {
        try {
            TelephonyManager tm = (TelephonyManager) context2.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                return tm.getDeviceId();
            }
            return BuildConfig.VERSION_NAME;
        } catch (Exception e) {
            return BuildConfig.VERSION_NAME;
        }
    }

    @SuppressLint({"HardwareIds"})
    public static String getAndroidID(Context context2) {
        try {
            String androidID = Settings.Secure.getString(context2.getContentResolver(), "android_id");
            return androidID;
        } catch (Exception e) {
            return BuildConfig.VERSION_NAME;
        }
    }

    public static String getMacAddress(Context context2) {
        if (Build.VERSION.SDK_INT < 23) {
            String macAddress = getMacDefault(context2);
            if (macAddress != null) {
                android.util.Log.d("Utils", "android 5.0以前的方式获取mac" + macAddress);
                String macAddress2 = macAddress.replaceAll(":", BuildConfig.VERSION_NAME);
                if (!macAddress2.equalsIgnoreCase("020000000000")) {
                    return macAddress2;
                }
            }
        } else if (Build.VERSION.SDK_INT < 24) {
            String macAddress3 = getMacAddress6();
            if (macAddress3 != null) {
                android.util.Log.d("Utils", "android 6~7 的方式获取的mac" + macAddress3);
                String macAddress4 = macAddress3.replaceAll(":", BuildConfig.VERSION_NAME);
                if (!macAddress4.equalsIgnoreCase("020000000000")) {
                    return macAddress4;
                }
            }
        } else {
            String macAddress5 = getMacFromHardware();
            if (macAddress5 != null) {
                android.util.Log.d("Utils", "android 7以后 的方式获取的mac" + macAddress5);
                String macAddress6 = macAddress5.replaceAll(":", BuildConfig.VERSION_NAME);
                if (!macAddress6.equalsIgnoreCase("020000000000")) {
                    return macAddress6;
                }
            }
        }
        android.util.Log.d("Utils", "没有获取到MAC");
        return BuildConfig.VERSION_NAME;
    }

    private static String getMacFromHardware() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            android.util.Log.d("Utils", "all:" + all.size());
            for (NetworkInterface nif : all) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return null;
                    }
                    android.util.Log.d("Utils", "macBytes:" + macBytes.length + "," + nif.getName());
                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:", Byte.valueOf(b)));
                    }
                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getMacAddress6() {
        String WifiAddress = null;
        try {
            WifiAddress = new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WifiAddress;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    private static String getMacDefault(Context context2) {
        WifiManager wifi;
        if (context2 != null && (wifi = (WifiManager) context2.getApplicationContext().getSystemService(Context.WIFI_SERVICE)) != null) {
            WifiInfo info = null;
            try {
                info = wifi.getConnectionInfo();
            } catch (Exception e) {
            }
            if (info == null) {
                return null;
            }
            String mac = info.getMacAddress();
            if (!TextUtils.isEmpty(mac)) {
                mac = mac.toUpperCase(Locale.ENGLISH);
            }
            return mac;
        }
        return null;
    }
}