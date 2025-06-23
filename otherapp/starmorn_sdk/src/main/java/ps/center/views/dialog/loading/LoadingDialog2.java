package ps.center.views.dialog.loading;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import ps.center.R;
import ps.center.views.dialog.BaseDialog2;

/* loaded from: classes.jar:ps/center/views/dialog/loading/LoadingDialog2.class */
public class LoadingDialog2 extends BaseDialog2 {
    private ImageView loading_img;
    private Animation rotateAnimation;
    private CountDownTimer timer;
    private TimeoutCallBack timeoutCallBack;
    private long timeout;

    /* loaded from: classes.jar:ps/center/views/dialog/loading/LoadingDialog2$TimeoutCallBack.class */
    public interface TimeoutCallBack {
        void timeout();
    }

    public LoadingDialog2(Context context) {
        super(context, R.style.dialogAlphaBack, R.style.dialogAnimation__min__max);
        this.timeout = 8000L;
    }

    public LoadingDialog2(Context context, int style) {
        super(context, style, R.style.dialogAnimation__min__max);
        this.timeout = 8000L;
    }

    public LoadingDialog2(Context context, long timeout, TimeoutCallBack timeoutCallBack) {
        super(context, R.style.dialogAlphaBack, R.style.dialogAnimation__min__max);
        this.timeout = 8000L;
        this.timeoutCallBack = timeoutCallBack;
        this.timeout = timeout;
    }

    @Override // ps.center.views.dialog.BaseDialog2
    protected int getLayout() {
        return R.layout.ps_dialog_loading;
    }

    @Override // ps.center.views.dialog.BaseDialog2
    protected void initView() {
        this.loading_img = (ImageView) findViewById(R.id.loading_img);
        this.rotateAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.dialog_anim_voice);
        LinearInterpolator lin = new LinearInterpolator();
        this.rotateAnimation.setInterpolator(lin);
    }

    @Override // ps.center.views.dialog.BaseDialog2
    protected void initData() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [ps.center.views.dialog.loading.LoadingDialog2$1] */
    @Override // android.app.Dialog
    public void show() {
        super.show();
        if (this.rotateAnimation != null) {
            this.loading_img.startAnimation(this.rotateAnimation);
        }
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
        this.timer = new CountDownTimer(this.timeout, 1000L) { // from class: ps.center.views.dialog.loading.LoadingDialog2.1
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (LoadingDialog2.this.timeoutCallBack != null) {
                    LoadingDialog2.this.timeoutCallBack.timeout();
                }
                LoadingDialog2.this.dismiss();
            }
        }.start();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.loading_img.clearAnimation();
        this.rotateAnimation.cancel();
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
    }

    @Override // ps.center.views.dialog.BaseDialog2, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}