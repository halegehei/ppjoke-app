package ps.center.utils;

import android.os.CountDownTimer;

/* loaded from: classes.jar:ps/center/utils/DownTimer.class */
public final class DownTimer {
    private CountDownTimer timer;
    public boolean isRunning = false;

    public void start(long time, final TimeCallBack timeCallBack) {
        if (this.timer != null) {
            timeCallBack.running();
            return;
        }
        this.isRunning = true;
        this.timer = new CountDownTimer(time, 1000L) { // from class: ps.center.utils.DownTimer.1
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                timeCallBack.down(millisUntilFinished);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                DownTimer.this.timer = null;
                DownTimer.this.isRunning = false;
                timeCallBack.over();
            }
        };
        timeCallBack.start();
        this.timer.start();
    }

    public void cancel() {
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
            this.isRunning = false;
        }
    }

    /* loaded from: classes.jar:ps/center/utils/DownTimer$TimeCallBack.class */
    public static abstract class TimeCallBack {
        public void start() {
        }

        public void running() {
        }

        public void down(long lastTime) {
        }

        public void over() {
        }
    }
}