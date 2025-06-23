package ps.center.application.config;

import ps.center.application.config.evnet.PayCustomListener;

/* loaded from: classes.jar:ps/center/application/config/AppCustomEvent.class */
public class AppCustomEvent {
    private static volatile AppCustomEvent appCustomEvent;
    private static final Object lock = new Object();
    private PayCustomListener payCustomListener;

    public static AppCustomEvent get() {
        if (appCustomEvent == null) {
            synchronized (lock) {
                if (appCustomEvent == null) {
                    appCustomEvent = new AppCustomEvent();
                }
            }
        }
        return appCustomEvent;
    }

    public PayCustomListener getPayCustomListener() {
        return this.payCustomListener == null ? new PayCustomListener() { // from class: ps.center.application.config.AppCustomEvent.1
        } : this.payCustomListener;
    }

    public void setPayCustomListener(PayCustomListener payCustomListener) {
        this.payCustomListener = payCustomListener;
    }
}