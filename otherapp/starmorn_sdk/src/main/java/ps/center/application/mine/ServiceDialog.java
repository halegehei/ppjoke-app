package ps.center.application.mine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import ps.center.R;
import ps.center.application.config.ApplicationConfig;
import ps.center.application.config.SettingConfig;
import ps.center.databinding.BusinessDialogServiceBinding;
import ps.center.utils.ToastUtils;
import ps.center.utils.UIUtils;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/mine/ServiceDialog.class */
public class ServiceDialog extends BaseDialogVB2<BusinessDialogServiceBinding> {
    private final String serviceCode;

    public ServiceDialog(Context context, String serviceCode) {
        super(context);
        this.serviceCode = serviceCode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogServiceBinding getLayout() {
        return BusinessDialogServiceBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
        SettingConfig settingConfig = ApplicationConfig.getSettingConfig();
        UIUtils.changeViewBackDrawableColor(((BusinessDialogServiceBinding) this.binding).rootLayout, settingConfig.serviceDialogBackColor);
        ((BusinessDialogServiceBinding) this.binding).title.setText(settingConfig.serviceDialogTitleText);
        ((BusinessDialogServiceBinding) this.binding).title.setTextColor(Color.parseColor(settingConfig.serviceDialogContentColor));
        ((BusinessDialogServiceBinding) this.binding).content.setTextColor(Color.parseColor(settingConfig.serviceDialogContentColor));
        ((BusinessDialogServiceBinding) this.binding).content2.setTextColor(Color.parseColor(settingConfig.serviceDialogContentColor));
        ((BusinessDialogServiceBinding) this.binding).submitLayout.setBackgroundResource(settingConfig.serviceDialogCopyBtnDrawable);
        ((BusinessDialogServiceBinding) this.binding).copyServiceNumber.setTextColor(Color.parseColor(settingConfig.serviceDialogCopyBtnTextColor));
        ((BusinessDialogServiceBinding) this.binding).closeBtn.setImageResource(settingConfig.serviceDialogCloseBtnImage);
        ((BusinessDialogServiceBinding) this.binding).content2.setText(this.serviceCode);
        ((BusinessDialogServiceBinding) this.binding).copyServiceNumber.setOnClickListener(v -> {
            try {
                ClipboardManager clipboard =
                        (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(null, this.serviceCode);
                clipboard.setPrimaryClip(clipData);
            } catch (Exception e) {
            }
            new Handler().postDelayed(() -> {
                ToastUtils.show(getContext(), "复制成功");
            }, 200L);
            dismiss();
        });
        findViewById(R.id.close_btn).setOnClickListener(v2 -> {
            dismiss();
        });
    }
}