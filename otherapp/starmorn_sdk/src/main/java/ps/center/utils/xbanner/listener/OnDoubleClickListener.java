package ps.center.utils.xbanner.listener;

import android.view.View;

/* loaded from: classes.jar:ps/center/utils/xbanner/listener/OnDoubleClickListener.class */
public abstract class OnDoubleClickListener implements View.OnClickListener {
    private int mThrottleFirstTime;
    private long mLastClickTime;

    public abstract void onNoDoubleClick(View view);

    public OnDoubleClickListener() {
        this.mThrottleFirstTime = 1000;
        this.mLastClickTime = 0L;
    }

    public OnDoubleClickListener(int throttleFirstTime) {
        this.mThrottleFirstTime = 1000;
        this.mLastClickTime = 0L;
        this.mThrottleFirstTime = throttleFirstTime;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.mLastClickTime > this.mThrottleFirstTime) {
            this.mLastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }
}