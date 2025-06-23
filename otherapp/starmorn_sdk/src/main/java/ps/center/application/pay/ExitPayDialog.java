package ps.center.application.pay;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import ps.center.R;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.centerinterface.bean.PayPage;
import ps.center.databinding.BusinessDialogExitPayBinding;
import ps.center.utils.LogUtils;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.TimeUtils;
import ps.center.utils.ToastUtils;
import ps.center.views.activity.webview.NativeWebActivity;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/pay/ExitPayDialog.class */
public class ExitPayDialog extends BaseDialogVB2<BusinessDialogExitPayBinding> {
    private final PayPage.GroupsBean.PriceInfoBean obj;
    private final BaseDialogVB2.Call call;
    private final String action;
    private final ArrayList<String> dialogShowRules;
    private boolean checkPermission;
    private CountDownTimer timer;
    private CountDownTimer closeCountDownTimer;

    public ExitPayDialog(Context context, String action, PayPage.GroupsBean.PriceInfoBean priceInfoBean, ArrayList<String> dialogShowRules, boolean checkPermission, BaseDialogVB2.Call call) {
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
    public BusinessDialogExitPayBinding getLayout() {
        return BusinessDialogExitPayBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
        ((BusinessDialogExitPayBinding) this.binding).bottomLabel1.setText(this.obj.bottom_label.replace(" ", "\n"));
        ((BusinessDialogExitPayBinding) this.binding).bottomLabel2.setText(this.obj.bottom_label.replace(" ", "\n"));
        ((BusinessDialogExitPayBinding) this.binding).dingyueshuoming.setText(this.obj.dingyueshuoming);
        ((BusinessDialogExitPayBinding) this.binding).freeDay.setText(this.obj.free_day);
        ((BusinessDialogExitPayBinding) this.binding).labelTips.setText(this.obj.spare_label);
        ((BusinessDialogExitPayBinding) this.binding).submit.setText(this.obj.button);
        ((BusinessDialogExitPayBinding) this.binding).topLabel.setText(this.obj.top_label.replace(" ", "\n"));
        startCloseBtnCountDownTimerShow();
        AppConfig.StandardConfBean.AgreementContentBean agreement_content = BusinessConstant.getConfig().standard_conf.agreement_content;
        if (agreement_content.comm.is_active.equals("1")) {
            ((BusinessDialogExitPayBinding) this.binding).checkboxLayout1.setVisibility(View.VISIBLE);
            ((BusinessDialogExitPayBinding) this.binding).leftClick.setVisibility(agreement_content.func.user_agreement.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessDialogExitPayBinding) this.binding).centerClick.setVisibility(agreement_content.func.privacy_policy.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessDialogExitPayBinding) this.binding).rightClick.setVisibility(agreement_content.func.pay_agreement.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessDialogExitPayBinding) this.binding).lastClick.setVisibility(agreement_content.func.auto_renew.equals("0") ? View.GONE : View.VISIBLE);
        } else {
            ((BusinessDialogExitPayBinding) this.binding).checkboxLayout1.setVisibility(View.GONE);
        }
        setCheckedPermissionStatus();
        this.timer = new CountDownTimer(getDownTimerNumber(this.dialogShowRules.get(2)), 1000L) { // from class: ps.center.application.pay.ExitPayDialog.1
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String time = TimeUtils.timeToHHMMSS(millisUntilFinished);
                ((BusinessDialogExitPayBinding) ExitPayDialog.this.binding).timeText.setText(String.format("限时%s", time));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ((BusinessDialogExitPayBinding) ExitPayDialog.this.binding).timeText.setVisibility(View.GONE);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((BusinessDialogExitPayBinding) ExitPayDialog.this.binding).contentLayout.getLayoutParams();
                layoutParams.topMargin = Super.dp2px(70.0f);
                ((BusinessDialogExitPayBinding) ExitPayDialog.this.binding).contentLayout.setLayoutParams(layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) ((BusinessDialogExitPayBinding) ExitPayDialog.this.binding).rootLayout.getLayoutParams();
                layoutParams2.height = Super.dp2px(410.0f);
                ((BusinessDialogExitPayBinding) ExitPayDialog.this.binding).rootLayout.setLayoutParams(layoutParams2);
                ((BusinessDialogExitPayBinding) ExitPayDialog.this.binding).rootLayout.setBackgroundResource(R.mipmap.business_exit_pay_dialog_bg_2);
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

    /* JADX WARN: Type inference failed for: r1v0, types: [ps.center.application.pay.ExitPayDialog$2] */
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
            this.closeCountDownTimer = new CountDownTimer(time, 1000L) { // from class: ps.center.application.pay.ExitPayDialog.2
                @Override // android.os.CountDownTimer
                public void onTick(long millisUntilFinished) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    ((BusinessDialogExitPayBinding) ExitPayDialog.this.binding).closeBtn.setVisibility(View.VISIBLE);
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
        ((BusinessDialogExitPayBinding) this.binding).closeBtn.setOnClickListener(v -> {
            this.call.call("close");
        });
        ((BusinessDialogExitPayBinding) this.binding).submit.setOnClickListener(v2 -> {
            if (!isCheckedPermission()) {
                ToastUtils.show(Super.getContext(), "请先阅读同意并勾选协议");
            } else {
                this.call.call("submit");
            }
        });
        ((BusinessDialogExitPayBinding) this.binding).payCheckBox.setOnClickListener(v3 -> {
            checkedPermissionStatus();
            this.call.call(Boolean.valueOf(this.checkPermission));
        });
        ((BusinessDialogExitPayBinding) this.binding).leftClick.setOnClickListener(new ps.center.utils.ui.OnClickListener()  { // from class: ps.center.application.pay.ExitPayDialog.3
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(ExitPayDialog.this.getContext(), "用户协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.user_agreement);
            }
        });
        ((BusinessDialogExitPayBinding) this.binding).centerClick.setOnClickListener(new ps.center.utils.ui.OnClickListener()  { // from class: ps.center.application.pay.ExitPayDialog.4
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(ExitPayDialog.this.getContext(), "隐私政策", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.privacy_policy);
            }
        });
        ((BusinessDialogExitPayBinding) this.binding).rightClick.setOnClickListener(new ps.center.utils.ui.OnClickListener()  { // from class: ps.center.application.pay.ExitPayDialog.5
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(ExitPayDialog.this.getContext(), "付费协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.pay_agreement);
            }
        });
        ((BusinessDialogExitPayBinding) this.binding).lastClick.setOnClickListener(new ps.center.utils.ui.OnClickListener()  { // from class: ps.center.application.pay.ExitPayDialog.6
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(ExitPayDialog.this.getContext(), "自动续费协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.auto_renew);
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
            ((BusinessDialogExitPayBinding) this.binding).payCheckBox.setVisibility(View.GONE);
            ((BusinessDialogExitPayBinding) this.binding).permissionTipsText.setVisibility(View.GONE);
        } else {
            ((BusinessDialogExitPayBinding) this.binding).payCheckBox.setVisibility(View.VISIBLE);
            ((BusinessDialogExitPayBinding) this.binding).permissionTipsText.setVisibility(View.VISIBLE);
            ((BusinessDialogExitPayBinding) this.binding).permissionTipsText.setText(BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up_txt);
        }
        if (this.checkPermission) {
            ((BusinessDialogExitPayBinding) this.binding).payCheckBox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxSelectImage);
        } else {
            ((BusinessDialogExitPayBinding) this.binding).payCheckBox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxDefaultImage);
        }
    }
}