package ps.center.adsdk;

import com.bytedance.sdk.openadsdk.CSJSplashAd;

/* loaded from: classes.jar:ps/center/adsdk/ADStatusEvent.class */
public class ADStatusEvent {
    private static ADStatusEvent adStatusEvent;
    public static final Object look = new Object();
    private LoadStatusCallBack loadStatusCallBack;
    private ActivityStatusCallBack activityStatusCallBack;

    /* loaded from: classes.jar:ps/center/adsdk/ADStatusEvent$ActivityStatusCallBack.class */
    public interface ActivityStatusCallBack {
        void activityStartAndRegister();

        void activityFinish();

        void activityShowAd();
    }

    /* loaded from: classes.jar:ps/center/adsdk/ADStatusEvent$LoadStatusCallBack.class */
    public interface LoadStatusCallBack {
        void loadSuccess(CSJSplashAd cSJSplashAd);
    }

    public static ADStatusEvent getInstance() {
        ADStatusEvent aDStatusEvent;
        synchronized (look) {
            if (adStatusEvent == null) {
                adStatusEvent = new ADStatusEvent();
            }
            aDStatusEvent = adStatusEvent;
        }
        return aDStatusEvent;
    }

    public void registerLoadStatusCallBack(LoadStatusCallBack loadStatusCallBack) {
        this.loadStatusCallBack = loadStatusCallBack;
    }

    public void registerActivityStatusCallBack(ActivityStatusCallBack activityStatusCallBack) {
        this.activityStatusCallBack = activityStatusCallBack;
    }

    public ActivityStatusCallBack getActivityStatusCallBack() {
        return this.activityStatusCallBack;
    }

    public LoadStatusCallBack getLoadStatusCallBack() {
        return this.loadStatusCallBack;
    }
}