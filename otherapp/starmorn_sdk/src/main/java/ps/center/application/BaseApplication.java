package ps.center.application;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.tencent.mmkv.MMKV;
import java.io.File;
import ps.center.adsdk.ADManager;
import ps.center.adsdk.ADTask;
import ps.center.adsdk.LaunchType;
import ps.center.application.config.Settings;
import ps.center.application.exception.ExceptionApplication;
import ps.center.application.welcome.BaseWelcomeActivity;
import ps.center.business.BusinessConstant;
import ps.center.utils.ActivityStackUtils;
import ps.center.utils.LogUtils;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;


public abstract class BaseApplication extends ExceptionApplication {
    private static final String TAG = "BaseApplication";
    public static String a = "myClassName";

    @Override // ps.center.application.exception.ExceptionApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        try {
            MMKV.initialize(this);
        } catch (Exception e) {
            Log.e(TAG, "请检查是否集成MMKV -> implementation 'com.tencent:mmkv-static:1.2.10'  版本请具体根据项目调整");
        }
        Super.init(this);
        setGlideCacheDir();
        checkInstallConfig();
        Settings.init(this, a, getPackageName());
        ActivityStackUtils.getInstance().registerActivityLifecycleCallBack(this, new ActivityStackUtils.ActivityLifecycleCallBack() { // from class: ps.center.application.BaseApplication.1
            @Override // ps.center.utils.ActivityStackUtils.ActivityLifecycleCallBack
            public void outAppReBack(Activity activity, long outTime) {
                if (!(activity instanceof BaseWelcomeActivity) && BusinessConstant.getConfig() != null && BusinessConstant.getConfig().standard_conf.ad_switch.comm.is_active.equals("1") && outTime > Integer.parseInt(BusinessConstant.getConfig().standard_conf.ad_switch.func.countdown_time) * 1000) {
                    ADManager.loadKaiPingAd(activity, LaunchType.StartTheAdLoadFirst, new ADTask.CallBack() { // from class: ps.center.application.BaseApplication.1.1
                        @Override // ps.center.adsdk.ADTask.CallBack
                        public void show() {
                        }

                        @Override // ps.center.adsdk.ADTask.CallBack
                        public void over() {
                        }
                    });
                }
            }

            @Override // ps.center.utils.ActivityStackUtils.ActivityLifecycleCallBack
            public void onStackInfo(ActivityStackUtils.StackInfo stackInfo) {
                LogUtils.e(stackInfo.stackListToString());
            }
        });
    }

    private void checkInstallConfig() {
        StringBuilder sb = new StringBuilder();
        try {
            Class.forName("com.alipay.sdk.app.PayTask");
        } catch (Exception e) {
            e.printStackTrace();
            sb.append("未集成支付宝SDK");
            sb.append("\n");
        }
        try {
            String packageName = getPackageName();
            Class.forName(String.format("%s.wxapi.WXEntryActivity", packageName));
            Class.forName(String.format("%s.wxapi.WXPayEntryActivity", packageName));
        } catch (Exception e2) {
            e2.printStackTrace();
            sb.append("未添加微信回调类，wxapi.WXEntryActivity 或者 wxapi.WXPayEntryActivity");
            sb.append("\n");
        }
        try {
            String packageName2 = getPackageName();
            Class<?> aClass = Class.forName(String.format("%s.wxapi.WXEntryActivity", packageName2));
            Class<?> aClass1 = Class.forName(String.format("%s.wxapi.WXPayEntryActivity", packageName2));
            String entry = aClass.getSuperclass().getName();
            String payEntry = aClass1.getSuperclass().getName();
            if (!entry.equals("ps.center.application.pay.wxapi.BaseWXEntryActivity") || !payEntry.equals("ps.center.application.pay.wxapi.BaseWXPayEntryActivity")) {
                sb.append("微信回调类未继承BaseEntry");
                sb.append("\n");
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            sb.append("微信api回调类未继承BaseEntry");
            sb.append("\n");
        }
        try {
            String version = PhoneNumberAuthHelper.getVersion();
            if (!version.equals("2.13.7")) {
                sb.append("号码认证SDK版本需更新至2.13.7");
            }
        } catch (Exception e4) {
            e4.printStackTrace();
            sb.append("号码认证SDK版本需更新至2.13.7");
        }
        if (sb.length() > 0) {
            ToastUtils.show(sb.toString());
            throw new RuntimeException(String.format("请检查SDK集成环境:%s", sb));
        }
    }

    @SuppressLint({"VisibleForTests"})
    private void setGlideCacheDir() {
        File cacheDir = getCacheDir();
        DiskLruCacheFactory factory = new DiskLruCacheFactory(cacheDir.getAbsolutePath(), 5368709120L);
        GlideBuilder builder = new GlideBuilder().setDiskCache(factory);
        Glide.init(this, builder);
    }
}