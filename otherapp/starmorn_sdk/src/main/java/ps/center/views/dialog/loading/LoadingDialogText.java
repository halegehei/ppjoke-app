package ps.center.views.dialog.loading;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import ps.center.R;
import ps.center.views.dialog.BaseDialog2;

public class LoadingDialogText extends BaseDialog2 {
    private ImageView loading_img;
    private Animation rotateAnimation;
    private CountDownTimer timer;
    private TimeoutCallBack timeoutCallBack;
    private long timeout;
    private TextView text;
    private String textStr;

    /* loaded from: classes.jar:ps/center/views/dialog/loading/LoadingDialogText$TimeoutCallBack.class */
    public interface TimeoutCallBack {
        void timeout();
    }

    public LoadingDialogText(Context context) {
        super(context, R.style.dialogAlphaBack, R.style.dialogAnimation__min__max);
        this.timeout = 8000L;
    }

    public LoadingDialogText(Context context, int style) {
        super(context, style, R.style.dialogAnimation__min__max);
        this.timeout = 8000L;
    }

    public LoadingDialogText(Context context, String textStr, long timeout, TimeoutCallBack timeoutCallBack) {
        super(context, R.style.dialogAlphaBack, R.style.dialogAnimation__min__max);
        this.timeout = 8000L;
        this.timeoutCallBack = timeoutCallBack;
        this.timeout = timeout;
        this.textStr = textStr;
    }

    @Override // ps.center.views.dialog.BaseDialog2
    protected int getLayout() {
        return R.layout.ps_dialog_loading;
    }

    @Override // ps.center.views.dialog.BaseDialog2
    protected void initView() {
        this.text = (TextView) findViewById(R.id.text);
        this.loading_img = (ImageView) findViewById(R.id.loading_img);
        if (this.text != null) {
            this.text.setText(this.textStr);
        }
        this.rotateAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.dialog_anim_voice);
        LinearInterpolator lin = new LinearInterpolator();
        this.rotateAnimation.setInterpolator(lin);
    }

    @Override // ps.center.views.dialog.BaseDialog2
    protected void initData() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [ps.center.views.dialog.loading.LoadingDialogText$1] */
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
        this.timer = new CountDownTimer(this.timeout, 1000L) { // from class: ps.center.views.dialog.loading.LoadingDialogText.1
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (LoadingDialogText.this.timeoutCallBack != null) {
                    LoadingDialogText.this.timeoutCallBack.timeout();
                }
                LoadingDialogText.this.dismiss();
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