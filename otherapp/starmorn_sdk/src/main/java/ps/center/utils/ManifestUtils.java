package ps.center.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/ManifestUtils.class */
public class ManifestUtils {
    public static String getMetaDataValue(Context context, String key, String defaultValue) {
        ApplicationInfo applicationInfo;
        String resultData = defaultValue;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) != null && applicationInfo.metaData != null) {
                Object tempValue = applicationInfo.metaData.get(key);
                resultData = null == tempValue ? defaultValue : ((tempValue instanceof String) || (tempValue instanceof Integer)) ? String.valueOf(tempValue) : defaultValue;
            }
        } catch (PackageManager.NameNotFoundException e) {
            resultData = defaultValue;
        }
        return resultData;
    }

    public static String getMetaDataValue(Context context, String key) {
        return getMetaDataValue(context, key, BuildConfig.VERSION_NAME);
    }

    public static boolean getBoolValue(Context context, String key, boolean defaultValue) {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) != null && applicationInfo.metaData != null) {
                return applicationInfo.metaData.getBoolean(key, defaultValue);
            }
            return defaultValue;
        } catch (PackageManager.NameNotFoundException e) {
            return defaultValue;
        }
    }

    public static int getIntValue(Context context, String key, int defaultValue) {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) != null && applicationInfo.metaData != null) {
                return applicationInfo.metaData.getInt(key, defaultValue);
            }
            return defaultValue;
        } catch (PackageManager.NameNotFoundException e) {
            return defaultValue;
        }
    }

    public static String getStringValue(Context context, String key, String defaultValue) {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) != null && applicationInfo.metaData != null) {
                return applicationInfo.metaData.getString(key, defaultValue);
            }
            return defaultValue;
        } catch (PackageManager.NameNotFoundException e) {
            return defaultValue;
        }
    }

    public static long getLongValue(Context context, String key, long defaultValue) {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) != null && applicationInfo.metaData != null) {
                return applicationInfo.metaData.getLong(key, defaultValue);
            }
            return defaultValue;
        } catch (PackageManager.NameNotFoundException e) {
            return defaultValue;
        }
    }
}