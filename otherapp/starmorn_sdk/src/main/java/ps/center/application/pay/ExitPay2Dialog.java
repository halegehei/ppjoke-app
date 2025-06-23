package ps.center.application.pay;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.centerinterface.bean.PayPage;
import ps.center.databinding.BusinessDialogExitPay2Binding;
import ps.center.utils.LogUtils;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.TimeUtils;
import ps.center.utils.ToastUtils;
import ps.center.views.activity.webview.NativeWebActivity;
import ps.center.views.dialog.BaseDialogVB2;
import ps.center.utils.ui.OnClickListener;

/* loaded from: classes.jar:ps/center/application/pay/ExitPay2Dialog.class */
public class ExitPay2Dialog extends BaseDialogVB2<BusinessDialogExitPay2Binding> {
    private final PayPage.GroupsBean.PriceInfoBean obj;
    private final BaseDialogVB2.Call call;
    private final String action;
    private final ArrayList<String> dialogShowRules;
    private boolean checkPermission;
    private CountDownTimer timer;
    private CountDownTimer closeCountDownTimer;

    public ExitPay2Dialog(Context context, String action, PayPage.GroupsBean.PriceInfoBean priceInfoBean, ArrayList<String> dialogShowRules, boolean checkPermission, BaseDialogVB2.Call call) {
        super(context);
        this.obj = priceInfoBean;
        this.call = call;
        this.dialogShowRules = dialogShowRules;
        this.action = action;
        this.checkPermission = checkPermission;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogExitPay2Binding getLayout() {
        return BusinessDialogExitPay2Binding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
        Glide.with(Super.getContext()).load(BusinessConstant.getConfig().standard_conf.pay_page.func.popup_pic).into(((BusinessDialogExitPay2Binding) this.binding).loadImage);
        ((BusinessDialogExitPay2Binding) this.binding).bottomLabel1.setText(this.obj.dingyueshuoming.replace(" ", "\n"));
        ((BusinessDialogExitPay2Binding) this.binding).submit.setText(this.obj.button);
        ((BusinessDialogExitPay2Binding) this.binding).topLabel.setText(this.obj.spare_label2.replace(" ", "\n"));
        startCloseBtnCountDownTimerShow();
        AppConfig.StandardConfBean.AgreementContentBean agreement_content = BusinessConstant.getConfig().standard_conf.agreement_content;
        if (agreement_content.comm.is_active.equals("1")) {
            ((BusinessDialogExitPay2Binding) this.binding).checkboxLayout1.setVisibility(View.VISIBLE);
            ((BusinessDialogExitPay2Binding) this.binding).leftClick.setVisibility(agreement_content.func.user_agreement.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessDialogExitPay2Binding) this.binding).centerClick.setVisibility(agreement_content.func.privacy_policy.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessDialogExitPay2Binding) this.binding).rightClick.setVisibility(agreement_content.func.pay_agreement.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessDialogExitPay2Binding) this.binding).lastClick.setVisibility(agreement_content.func.auto_renew.equals("0") ? View.GONE : View.VISIBLE);
        } else {
            ((BusinessDialogExitPay2Binding) this.binding).checkboxLayout1.setVisibility(View.GONE);
        }
        setCheckedPermissionStatus();
        this.timer = new CountDownTimer(getDownTimerNumber(this.dialogShowRules.get(2)), 1000L) { // from class: ps.center.application.pay.ExitPay2Dialog.1
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String time = TimeUtils.timeToHHMMSS(millisUntilFinished);
                ((BusinessDialogExitPay2Binding) ExitPay2Dialog.this.binding).timeText.setVisibility(View.VISIBLE);
                ((BusinessDialogExitPay2Binding) ExitPay2Dialog.this.binding).timeText.setText(String.format("%s", time));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ((BusinessDialogExitPay2Binding) ExitPay2Dialog.this.binding).timeText.setVisibility(View.GONE);
            }
        };
        this.timer.start();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.timer != null) {
            this.timer.cancel();
        }
        cancelCountDownTimer();
        super.dismiss();
    }

    private void cancelCountDownTimer() {
        if (this.closeCountDownTimer != null) {
            this.closeCountDownTimer.cancel();
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [ps.center.application.pay.ExitPay2Dialog$2] */
    private void startCloseBtnCountDownTimerShow() {
        int time;
        if (this.closeCountDownTimer == null) {
            try {
                time = Integer.parseInt(BusinessConstant.getConfig().standard_conf.pay_page.func.return_delay_time) * 1000;
            } catch (NumberFormatException e) {
                LogUtils.e("关闭按钮显示的倒计时配置项配置类型错误， 不是整数");
                e.printStackTrace();
                time = 0;
            }
            this.closeCountDownTimer = new CountDownTimer(time, 1000L) { // from class: ps.center.application.pay.ExitPay2Dialog.2
                @Override // android.os.CountDownTimer
                public void onTick(long millisUntilFinished) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    ((BusinessDialogExitPay2Binding) ExitPay2Dialog.this.binding).closeBtn.setVisibility(View.VISIBLE);
                }
            }.start();
        }
    }

    private int getDownTimerNumber(String timeStr) {
        long firstOpenPayPagerTime = Save.instance.getLong(this.action + "firstOpenPayPagerTime", 0L);
        return (int) ((firstOpenPayPagerTime + (Integer.parseInt(timeStr) * 1000)) - System.currentTimeMillis());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    public void setListener() {
        ((BusinessDialogExitPay2Binding) this.binding).closeBtn.setOnClickListener(v -> {
            this.call.call("close");
        });
        ((BusinessDialogExitPay2Binding) this.binding).submit.setOnClickListener(v2 -> {
            if (!isCheckedPermission()) {
                ToastUtils.show(Super.getContext(), "请先阅读同意并勾选协议");
            } else {
                this.call.call("submit");
            }
        });
        ((BusinessDialogExitPay2Binding) this.binding).payCheckBox.setOnClickListener(v3 -> {
            checkedPermissionStatus();
            this.call.call(Boolean.valueOf(this.checkPermission));
        });


        ((BusinessDialogExitPay2Binding) this.binding).leftClick.setOnClickListener(new ps.center.utils.ui.OnClickListener() {
            @Override
            public void click(View view) {
                NativeWebActivity.jump(ExitPay2Dialog.this.getContext(), "用户协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.user_agreement);
            }
        });
        ((BusinessDialogExitPay2Binding) this.binding).centerClick.setOnClickListener(new ps.center.utils.ui.OnClickListener() { // from class: ps.center.application.pay.ExitPay2Dialog.4
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(ExitPay2Dialog.this.getContext(), "隐私政策", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.privacy_policy);
            }
        });
        ((BusinessDialogExitPay2Binding) this.binding).rightClick.setOnClickListener(new ps.center.utils.ui.OnClickListener() { // from class: ps.center.application.pay.ExitPay2Dialog.5
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(ExitPay2Dialog.this.getContext(), "付费协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.pay_agreement);
            }
        });
        ((BusinessDialogExitPay2Binding) this.binding).lastClick.setOnClickListener(new ps.center.utils.ui.OnClickListener() { // from class: ps.center.application.pay.ExitPay2Dialog.6
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(ExitPay2Dialog.this.getContext(), "自动续费协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.auto_renew);
            }
        });
    }

    private void checkedPermissionStatus() {
        this.checkPermission = !this.checkPermission;
        setCheckedPermissionStatus();
    }

    private boolean isCheckedPermission() {
        return BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up.equals("0") || this.checkPermission;
    }

    private void setCheckedPermissionStatus() {
        if (BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up.equals("0")) {
            ((BusinessDialogExitPay2Binding) this.binding).payCheckBox.setVisibility(View.GONE);
            ((BusinessDialogExitPay2Binding) this.binding).permissionTipsText.setVisibility(View.GONE);
        } else {
            ((BusinessDialogExitPay2Binding) this.binding).payCheckBox.setVisibility(View.VISIBLE);
            ((BusinessDialogExitPay2Binding) this.binding).permissionTipsText.setVisibility(View.VISIBLE);
            ((BusinessDialogExitPay2Binding) this.binding).permissionTipsText.setText(BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up_txt);
        }
        if (this.checkPermission) {
            ((BusinessDialogExitPay2Binding) this.binding).payCheckBox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxSelectImage);
        } else {
            ((BusinessDialogExitPay2Binding) this.binding).payCheckBox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxDefaultImage);
        }
    }
}