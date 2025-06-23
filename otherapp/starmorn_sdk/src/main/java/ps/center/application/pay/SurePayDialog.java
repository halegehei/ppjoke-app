package ps.center.application.pay;

import android.content.Context;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.databinding.BusinessDialogSurePayBinding;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/pay/SurePayDialog.class */
public class SurePayDialog extends BaseDialogVB2<BusinessDialogSurePayBinding> {
    private final BaseDialogVB2.Call call;

    public SurePayDialog(Context context, BaseDialogVB2.Call call) {
        super(context);
        this.call = call;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogSurePayBinding getLayout() {
        return BusinessDialogSurePayBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        StringBuilder stringBuilder = new StringBuilder();
        if (BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up_txt.contains("#")) {
            for (String item : BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up_txt.split("#")) {
                stringBuilder.append(item);
                stringBuilder.append("\n");
            }
            if (stringBuilder.toString().endsWith("\n")) {
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
            }
        } else {
            stringBuilder.append(BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up_txt);
        }
        ((BusinessDialogSurePayBinding) this.binding).text.setText(stringBuilder.toString());
        ((BusinessDialogSurePayBinding) this.binding).submit.setBackgroundResource(ApplicationConfig.getSettingConfig().payPageSurePayDialogBtnBg);
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    public void setListener() {
        ((BusinessDialogSurePayBinding) this.binding).cancel.setOnClickListener(v -> {
            dismiss();
        });
        ((BusinessDialogSurePayBinding) this.binding).submit.setOnClickListener(v2 -> {
            dismiss();
            this.call.call("submit");
        });
    }
}