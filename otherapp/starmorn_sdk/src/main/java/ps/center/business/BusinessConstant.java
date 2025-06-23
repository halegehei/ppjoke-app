package ps.center.business;

import android.os.Parcelable;
import ps.center.business.bean.config.AppConfig;
import ps.center.utils.Save;

/* loaded from: classes.jar:ps/center/business/BusinessConstant.class */
public class BusinessConstant {
    public static void setConfig(AppConfig appConfig) {
        Save.instance.put("appConfig", (Parcelable) appConfig);
    }

    public static AppConfig getConfig() {
        return (AppConfig) Save.instance.getParcelable("appConfig", AppConfig.class);
    }
}