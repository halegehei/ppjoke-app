package ps.center.utils.ui;

import android.view.View;

/* loaded from: classes.jar:ps/center/utils/ui/OnClickListener.class */
public abstract class OnClickListener implements View.OnClickListener {
    private long clickTime;
    private long setTime;

    public abstract void click(View view);

    public OnClickListener(long time) {
        this.clickTime = 0L;
        this.setTime = 250L;
        this.setTime = time;
    }

    public OnClickListener() {
        this.clickTime = 0L;
        this.setTime = 250L;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (System.currentTimeMillis() - this.clickTime > this.setTime) {
            this.clickTime = System.currentTimeMillis();
            click(view);
        }
    }
}