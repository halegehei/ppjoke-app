package ps.center.application.login;

import android.content.Context;
import android.view.View;

import ps.center.databinding.BusinessDialogUnUserBinding;
import ps.center.utils.ui.OnClickListener;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/login/UnUserDialog.class */
public class UnUserDialog extends BaseDialogVB2<BusinessDialogUnUserBinding> {
    private BaseDialogVB2.Call call;
    private boolean isSubmitClose;

    public UnUserDialog(Context context, boolean isSubmitClose, BaseDialogVB2.Call call) {
        super(context);
        this.call = call;
        this.isSubmitClose = isSubmitClose;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogUnUserBinding getLayout() {
        return BusinessDialogUnUserBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {


        ((BusinessDialogUnUserBinding) this.binding).cancel.setOnClickListener(new ps.center.utils.ui.OnClickListener() {
            @Override
            public void click(View view) {
                UnUserDialog.this.dismiss();
            }
        });
        ((BusinessDialogUnUserBinding) this.binding).submit.setOnClickListener(new ps.center.utils.ui.OnClickListener()  { // from class: ps.center.application.login.UnUserDialog.2
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                if (UnUserDialog.this.call != null) {
                    UnUserDialog.this.call.call(UnUserDialog.this);
                }
                if (UnUserDialog.this.isSubmitClose) {
                    UnUserDialog.this.dismiss();
                }
            }
        });
    }
}