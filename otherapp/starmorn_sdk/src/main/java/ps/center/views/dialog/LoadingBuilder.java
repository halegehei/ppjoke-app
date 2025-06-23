package ps.center.views.dialog;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import ps.center.R;

/* loaded from: classes.jar:ps/center/views/dialog/LoadingBuilder.class */
public class LoadingBuilder {
    private final Context mContext;
    private final long timeout;
    private LoadingListener loadingListener;
    private CountDownTimer countDownTimer;
    private final CustomDialog customDialog;
    private final View rootView;
    private ImageView loading_img;
    private Animation animation;

    public LoadingBuilder(Context context, DialogStyle dialogStyle, long timeout) {
        int themeId;
        this.timeout = timeout;
        this.mContext = context;
        this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.ps_center_dialog_loading, (ViewGroup) null);
        switch (dialogStyle) {
            case blackBack:
                themeId = R.style.dialogBlackBack;
                break;
            case NotBack:
                themeId = R.style.dialogAlphaBack;
                break;
            default:
                themeId = R.style.dialogBlackBack;
                break;
        }
        this.customDialog = new CustomDialog(this.mContext, themeId, this.rootView, true);
        DismissListener dismissListener = () -> {
            if (this.countDownTimer != null) {
                this.countDownTimer.cancel();
                this.countDownTimer = null;
            }
            if (this.customDialog.isShowing()) {
                this.customDialog.dismiss();
            }
            if (this.loading_img != null) {
                this.loading_img.clearAnimation();
            }
            if (this.animation != null) {
                this.animation.cancel();
            }
        };
        this.customDialog.setDismissListener(dismissListener);
    }

    public LoadingBuilder setTimeoutListener(LoadingListener loadingListener) {
        bindTimeoutListener(loadingListener);
        return this;
    }

    public LoadingBuilder outCancel(boolean outCancel) {
        this.customDialog.setCanceledOnTouchOutside(outCancel);
        return this;
    }

    public LoadingBuilder keyBackCancel(boolean isKeyBackCancel) {
        this.customDialog.setKeyBackCancel(isKeyBackCancel);
        return this;
    }

    public void show() {
        this.customDialog.show();
        if (this.loadingListener != null) {
            this.loadingListener.start(this.customDialog);
        }
        createTimer();
        createAnimation();
    }

    private void bindTimeoutListener(LoadingListener loadingListener) {
        this.loadingListener = loadingListener;
    }

    private void createTimer() {
        this.countDownTimer = new CountDownTimer(this.timeout, 1000L) { // from class: ps.center.views.dialog.LoadingBuilder.1
            @Override // android.os.CountDownTimer
            public void onTick(long l) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (LoadingBuilder.this.loadingListener != null) {
                    LoadingBuilder.this.loadingListener.timeout(LoadingBuilder.this.customDialog);
                    LoadingBuilder.this.loadingListener = null;
                }
                if (LoadingBuilder.this.customDialog != null && LoadingBuilder.this.customDialog.isShowing()) {
                    LoadingBuilder.this.customDialog.dismiss();
                }
            }
        };
        this.countDownTimer.start();
    }

    private void createAnimation() {
        this.loading_img = (ImageView) this.rootView.findViewById(R.id.loading_img);
        this.animation = AnimationUtils.loadAnimation(this.mContext, R.anim.dialog_anim_voice);
        LinearInterpolator lin = new LinearInterpolator();
        this.animation.setInterpolator(lin);
        this.loading_img.startAnimation(this.animation);
    }
}