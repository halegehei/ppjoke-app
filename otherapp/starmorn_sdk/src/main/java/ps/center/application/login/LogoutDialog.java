package ps.center.application.login;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import ps.center.databinding.BusinessDialogLogoutBinding;

import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/login/LogoutDialog.class */
public class LogoutDialog extends BaseDialogVB2<BusinessDialogLogoutBinding> {
    private BaseDialogVB2.Call call;
    private CountDownTimer timer;
    private boolean isTimeOver;
    private boolean isSubmitClose;

    public LogoutDialog(Context context, boolean isSubmitClose, BaseDialogVB2.Call call) {
        super(context);
        this.isTimeOver = false;
        this.call = call;
        this.isSubmitClose = isSubmitClose;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogLogoutBinding getLayout() {
        return BusinessDialogLogoutBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
        setTipsLabelClickListener();
        if (this.timer == null) {
            this.timer = new CountDownTimer(3000L, 1000L) { // from class: ps.center.application.login.LogoutDialog.1
                @Override // android.os.CountDownTimer
                public void onTick(long time) {
                    ((BusinessDialogLogoutBinding) LogoutDialog.this.binding).submit.setText(String.format("退出(%s)", Long.valueOf(time / 1000)));
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    ((BusinessDialogLogoutBinding) LogoutDialog.this.binding).submit.setText("退出");
                    LogoutDialog.this.isTimeOver = true;
                }
            };
            this.timer.start();
        }
//        ((BusinessDialogLogoutBinding) this.binding).cancel.setOnClickListener(new OnClickListener() { // from class: ps.center.application.login.LogoutDialog.2
//            @Override // ps.center.utils.ui.OnClickListener
//            public void click(View view) {
//                LogoutDialog.this.dismiss();
//            }
//        });

        ((BusinessDialogLogoutBinding) this.binding).cancel.setOnClickListener(new ps.center.utils.ui.OnClickListener() {
            @Override
            public void click(View view) {
                LogoutDialog.this.dismiss();
            }
        });
        ((BusinessDialogLogoutBinding) this.binding).submit.setOnClickListener(new ps.center.utils.ui.OnClickListener() { // from class: ps.center.application.login.LogoutDialog.3
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                if (LogoutDialog.this.isTimeOver) {
                    if (LogoutDialog.this.call != null) {
                        LogoutDialog.this.call.call(LogoutDialog.this);
                    }
                    if (LogoutDialog.this.isSubmitClose) {
                        LogoutDialog.this.dismiss();
                    }
                }
            }
        });
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
    }

    private void setTipsLabelClickListener() {
        SpannableString textSpanned1 = new SpannableString("升级为VIP后，您的信息会与会员信息绑定成功，退出后则无法使用VIP功能，需重新登录");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) textSpanned1);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 23, 42, 33);
        ((BusinessDialogLogoutBinding) this.binding).desc.setText(spannableStringBuilder);
    }
}