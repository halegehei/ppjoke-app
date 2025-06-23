package ps.center.adsdk;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.bytedance.sdk.openadsdk.LocationProvider;
import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTCustomController;
import com.bytedance.sdk.openadsdk.mediation.init.IMediationPrivacyConfig;

import ps.center.application.BuildConfig;
import ps.center.business.BusinessConstant;
import ps.center.utils.ManifestUtils;

/* loaded from: classes.jar:ps/center/adsdk/ADManager.class */
public class ADManager {
    private static boolean init = false;

    public static void initAndEmpower(Activity activity) {
        init(activity, null);
    }

    public static void initAndEmpower(Activity activity, TTAdSdk.Callback initCallback) {
        init(activity, initCallback);
    }

    private static void init(Activity activity, final TTAdSdk.Callback initCallback) {
        if (!BusinessConstant.getConfig().standard_conf.ad_switch.comm.is_active.equals("1")) {
            if (initCallback != null) {
                initCallback.fail(-1, "未开启广告开关");
            }
        } else {
            if (init) {
                return;
            }
            TTAdSdk.init(activity, new TTAdConfig.Builder().appId(BusinessConstant.getConfig().config.csjplatform.csj_appid).useTextureView(true).appName(ManifestUtils.getMetaDataValue(activity, "APP_NAME")).titleBarTheme(1).customController(new TTCustomController() { // from class: ps.center.adsdk.ADManager.1
                public boolean isCanUseLocation() {
                    return false;
                }

                @Nullable
                public LocationProvider getTTLocation() {
                    return null;
                }

                public boolean alist() {
                    return false;
                }

                public boolean isCanUsePhoneState() {
                    return false;
                }

                @Nullable
                public String getDevImei() {
                    return BuildConfig.VERSION_NAME;
                }

                public boolean isCanUseWifiState() {
                    return false;
                }

                @Nullable
                public String getMacAddress() {
                    return BuildConfig.VERSION_NAME;
                }

                public boolean isCanUseWriteExternal() {
                    return false;
                }

                @Nullable
                public String getDevOaid() {
                    return BuildConfig.VERSION_NAME;
                }

                public boolean isCanUseAndroidId() {
                    return false;
                }

                @Nullable
                public IMediationPrivacyConfig getMediationPrivacyConfig() {
                    return null;
                }

                @Nullable
                public String getAndroidId() {
                    return BuildConfig.VERSION_NAME;
                }

                public boolean isCanUsePermissionRecordAudio() {
                    return false;
                }
            }).allowShowNotify(true).debug(true).directDownloadNetworkType(new int[]{4}).supportMultiProcess(false).build());
            TTAdSdk.start(new TTAdSdk.Callback() { // from class: ps.center.adsdk.ADManager.2
                public void success() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    TTAdSdk.Callback callback = initCallback;
                    handler.post(() -> {
                        if (callback != null) {
                            callback.success();
                        }
                    });
                }

                public void fail(int i, String s) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    TTAdSdk.Callback callback = initCallback;
                    handler.post(() -> {
                        if (callback != null) {
                            callback.fail(i, s);
                        }
                    });
                }
            });
            init = true;
        }
    }

    public static void loadKaiPingAd(Activity activity, LaunchType launchType, ADTask.CallBack callBack) {
        new ADTask(activity, callBack).loadKaiPingAd(launchType);
    }
}