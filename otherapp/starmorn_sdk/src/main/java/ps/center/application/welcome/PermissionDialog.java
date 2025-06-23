package ps.center.application.welcome;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import ps.center.application.config.ApplicationConfig;
import ps.center.application.config.SettingConfig;
import ps.center.business.BusinessConstant;
import ps.center.databinding.BusinessDialogPermissionBinding;
import ps.center.utils.UIUtils;
import ps.center.utils.ui.OnClickListener;
import ps.center.views.activity.webview.NativeWebActivity;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/welcome/PermissionDialog.class */
public class PermissionDialog extends BaseDialogVB2<BusinessDialogPermissionBinding> {
    private final BaseDialogVB2.Call call;

    public PermissionDialog(Context context, BaseDialogVB2.Call call) {
        super(context);
        this.call = call;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogPermissionBinding getLayout() {
        return BusinessDialogPermissionBinding.inflate(getLayoutInflater());
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }

    @Override // ps.center.views.dialog.BaseDialogVB2, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setTipsLabelClickListener();
        SettingConfig settingConfig = ApplicationConfig.getSettingConfig();
        UIUtils.changeViewBackDrawableColor(((BusinessDialogPermissionBinding) this.binding).rootLayout, settingConfig.permissionDialogBackColor);
        ((BusinessDialogPermissionBinding) this.binding).titleLabel.setTextColor(Color.parseColor(settingConfig.permissionDialogTitleColor));
        ((BusinessDialogPermissionBinding) this.binding).tipsLabel.setTextColor(Color.parseColor(settingConfig.permissionDialogContentTextColor));
        ((BusinessDialogPermissionBinding) this.binding).submit.setBackgroundResource(settingConfig.permissionDialogSubmitDrawable);
        ((BusinessDialogPermissionBinding) this.binding).submit.setTextColor(Color.parseColor(settingConfig.permissionDialogSubmitTextColor));
        ((BusinessDialogPermissionBinding) this.binding).cancel.setBackgroundResource(settingConfig.permissionDialogCancelDrawable);
        ((BusinessDialogPermissionBinding) this.binding).cancel.setTextColor(Color.parseColor(settingConfig.permissionDialogCancelTextColor));


        ((BusinessDialogPermissionBinding) this.binding).cancel.setOnClickListener(new ps.center.utils.ui.OnClickListener() {
            @Override
            public void click(View view) {
                PermissionDialog.this.call.call("cancel");
            }
        });
        ((BusinessDialogPermissionBinding) this.binding).submit.setOnClickListener(new ps.center.utils.ui.OnClickListener()  {
            @Override
            public void click(View view) {
                PermissionDialog.this.dismiss();
                PermissionDialog.this.call.call("submit");
            }
        });
    }

    private void setTipsLabelClickListener() {
        String tr = ApplicationConfig.getSettingConfig().permissionDialogContent;
        int left1Df = tr.indexOf(12298);
        int left2Df = tr.lastIndexOf(12298);
        int right1Df = tr.indexOf(12299) + 1;
        int right2Df = tr.lastIndexOf(12299) + 1;
        SpannableString textSpanned1 = new SpannableString(tr);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) textSpanned1);
        ClickableSpan clickableSpan1 = new ClickableSpan() { // from class: ps.center.application.welcome.PermissionDialog.3
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                NativeWebActivity.jump(PermissionDialog.this.getContext(), "用户协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.user_agreement);
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() { // from class: ps.center.application.welcome.PermissionDialog.4
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                NativeWebActivity.jump(PermissionDialog.this.getContext(), "隐私政策", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.privacy_policy);
            }
        };
        spannableStringBuilder.setSpan(clickableSpan1, left1Df, right1Df, 34);
        spannableStringBuilder.setSpan(clickableSpan2, left2Df, right2Df, 34);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(ApplicationConfig.getSettingConfig().permissionDialogClickTextColor)), left1Df, right1Df, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(ApplicationConfig.getSettingConfig().permissionDialogClickTextColor)), left2Df, right2Df, 33);
        ((BusinessDialogPermissionBinding) this.binding).tipsLabel.setMovementMethod(LinkMovementMethod.getInstance());
        ((BusinessDialogPermissionBinding) this.binding).tipsLabel.setText(spannableStringBuilder);
    }
}