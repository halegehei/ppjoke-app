package ps.center.application.utils;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import java.util.HashMap;
import ps.center.application.BuildConfig;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.utils.ManifestUtils;
import ps.center.utils.Save;
import ps.center.utils.Super;

/* loaded from: classes.jar:ps/center/application/utils/UmengUtil.class */
public class UmengUtil {
    private final Context context;
    private final CallBack callBack;
    private final boolean isPermission;

    /* loaded from: classes.jar:ps/center/application/utils/UmengUtil$CallBack.class */
    public interface CallBack {
        void result(String str, String str2);
    }

    public UmengUtil(Context context, boolean isPermission, CallBack callBack) {
        this.callBack = callBack;
        this.context = context;
        this.isPermission = isPermission;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [ps.center.application.utils.UmengUtil$1] */
    public void build() {
        new Thread() { // from class: ps.center.application.utils.UmengUtil.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (ApplicationConfig.getSettingConfig().isRunUMEng) {
                    UMConfigure.setLogEnabled(true);
                    UMConfigure.init(UmengUtil.this.context, BusinessConstant.getConfig().config.umeng.umeng_appid, ManifestUtils.getMetaDataValue(Super.getContext(), "PACKET", BuildConfig.VERSION_NAME), 1, BuildConfig.VERSION_NAME);
                    MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
                    UMConfigure.getOaid(UmengUtil.this.context, s -> {
                        String oAid = s == null ? BuildConfig.VERSION_NAME : s;
                        Save.instance.put("oaid", oAid);
                        if (UmengUtil.this.isPermission) {
                            String mac = Super.getMacAddress(UmengUtil.this.context);
                            if (mac.equals("02:00:00:00:00:00") || mac.equals("020000000000")) {
                                mac = BuildConfig.VERSION_NAME;
                            }
                            String iMei = Super.getIMEI(UmengUtil.this.context);
                            Save.instance.put("imei", iMei);
                            Save.instance.put("mac", mac);
                        }
                        HashMap<String, String> param = new HashMap<>();
                        param.put("mac", Save.instance.getString("mac", BuildConfig.VERSION_NAME));
                        param.put("oaid", Save.instance.getString("oaid", BuildConfig.VERSION_NAME));
                        param.put("imei", Save.instance.getString("imei", BuildConfig.VERSION_NAME));
                        String jsonParma = new Gson().toJson(param);
                        Save.instance.put("param", jsonParma);
                        new Handler(Looper.getMainLooper()).post(() -> {
                            String brand = Build.BRAND;
                            if (brand == null) {
                                brand = BuildConfig.VERSION_NAME;
                            }
                            UmengUtil.this.callBack.result(brand.trim(), String.valueOf(Build.VERSION.SDK_INT));
                        });
                    });
                    return;
                }
                HashMap<String, String> param = new HashMap<>();
                param.put("mac", Save.instance.getString("mac", BuildConfig.VERSION_NAME));
                param.put("oaid", Save.instance.getString("oaid", BuildConfig.VERSION_NAME));
                param.put("imei", Save.instance.getString("imei", BuildConfig.VERSION_NAME));
                String jsonParma = new Gson().toJson(param);
                Save.instance.put("param", jsonParma);
                new Handler(Looper.getMainLooper()).post(() -> {
                    String brand = Build.BRAND;
                    if (brand == null) {
                        brand = BuildConfig.VERSION_NAME;
                    }
                    UmengUtil.this.callBack.result(brand.trim(), String.valueOf(Build.VERSION.SDK_INT));
                });
            }
        }.start();
    }
}