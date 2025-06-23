package ps.center.adsdk;

import android.app.Activity;
import android.content.Intent;
import com.bytedance.sdk.openadsdk.TTAdSdk;

import ps.center.business.BusinessConstant;
import ps.center.centerinterface.constant.CenterConstant;

/* loaded from: classes.jar:ps/center/adsdk/ADTask.class */
public class ADTask {
    private CallBack callBack;
    private final Activity activity;

    /* loaded from: classes.jar:ps/center/adsdk/ADTask$CallBack.class */
    public interface CallBack {
        void show();

        void over();
    }

    public ADTask(Activity activity, CallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
        AdDataSource.setCallBack(callBack);
    }

    public void loadKaiPingAd(LaunchType launchType) {
        if (!TTAdSdk.isSdkReady()) {
            if (this.callBack != null) {
                this.callBack.over();
                this.callBack = null;
                return;
            }
            return;
        }
        if (!BusinessConstant.getConfig().standard_conf.ad_switch.comm.is_active.equals("1")) {
            if (this.callBack != null) {
                this.callBack.over();
                this.callBack = null;
                return;
            }
            return;
        }
        if (!CenterConstant.getUser().isVip) {
            if (!BusinessConstant.getConfig().standard_conf.ad_switch.func.kaiping.equals("1")) {
                if (this.callBack != null) {
                    this.callBack.over();
                    this.callBack = null;
                    return;
                }
                return;
            }
        } else if (!BusinessConstant.getConfig().standard_conf.ad_switch.func.pay_kaiping_sw.equals("1")) {
            if (this.callBack != null) {
                this.callBack.over();
                this.callBack = null;
                return;
            }
            return;
        }
        Intent intent = new Intent(this.activity, (Class<?>) AdActivity.class);
        this.activity.startActivity(intent);
        try {
            this.activity.overridePendingTransition(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}