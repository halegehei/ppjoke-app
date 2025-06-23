package ps.center.centerinterface.service;

import android.os.CountDownTimer;
import ps.center.business.utils.free.FreeManager;
import ps.center.centerinterface.bean.User;
import ps.center.utils.LogUtils;

/* loaded from: classes.jar:ps/center/centerinterface/service/VipCountDownService.class */
public class VipCountDownService {
    private static VipCountDownService vipCountDownService;
    private static final Object look = new Object();
    private CountDownTimer timer;

    public static VipCountDownService get() {
        VipCountDownService vipCountDownService2;
        synchronized (look) {
            if (vipCountDownService == null) {
                vipCountDownService = new VipCountDownService();
            }
            vipCountDownService2 = vipCountDownService;
        }
        return vipCountDownService2;
    }

    public void run(User user) {
        if (user.vip_time * 1000 > System.currentTimeMillis()) {
            if (this.timer != null) {
                this.timer.cancel();
                this.timer = null;
            }
            this.timer = new CountDownTimer(((user.vip_time * 1000) - System.currentTimeMillis()) + 5000, 5000L) { // from class: ps.center.centerinterface.service.VipCountDownService.1
                @Override // android.os.CountDownTimer
                public void onTick(long ms) {
                    LogUtils.e("会员时间倒计时中(5s/次).. %s ms", Long.valueOf(ms));
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    try {
                        FreeManager.FreeTimeOverListener freeTimeOverListener = FreeManager.get().getFreeTimeOverListener();
                        if (freeTimeOverListener != null) {
                            freeTimeOverListener.timeOver();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            this.timer.start();
            return;
        }
        if (this.timer != null) {
            this.timer.cancel();
        }
    }

    public void close() {
        if (this.timer != null) {
            this.timer.cancel();
        }
    }
}