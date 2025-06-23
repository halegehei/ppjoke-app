package ps.center.application.dialog;

import android.content.Context;
import ps.center.application.BuildConfig;
import ps.center.databinding.BusinessDialogIdentityAuthenticationTipsBinding;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/dialog/IdentityAuthenticationTipsDialog.class */
public class IdentityAuthenticationTipsDialog extends BaseDialogVB2<BusinessDialogIdentityAuthenticationTipsBinding> {
    private BaseDialogVB2.Call call;

    public IdentityAuthenticationTipsDialog(Context context, BaseDialogVB2.Call call) {
        super(context);
        this.call = call;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogIdentityAuthenticationTipsBinding getLayout() {
        return BusinessDialogIdentityAuthenticationTipsBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    public void setListener() {
        ((BusinessDialogIdentityAuthenticationTipsBinding) this.binding).cancel.setOnClickListener(v -> {
            dismiss();
        });
        ((BusinessDialogIdentityAuthenticationTipsBinding) this.binding).submit.setOnClickListener(v2 -> {
            dismiss();
            this.call.call(BuildConfig.VERSION_NAME);
        });
    }
}