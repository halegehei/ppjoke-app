package ps.center.application.dialog;

import android.content.Context;
import ps.center.application.BuildConfig;
import ps.center.business.bean.common.IdentityAuthentication;
import ps.center.business.http.base.BusHttp;
import ps.center.databinding.BusinessDialogIdentityAuthenticationBinding;
import ps.center.library.http.base.Result;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/dialog/IdentityAuthenticationDialog.class */
public class IdentityAuthenticationDialog extends BaseDialogVB2<BusinessDialogIdentityAuthenticationBinding> {
    private BaseDialogVB2.Call call;

    public IdentityAuthenticationDialog(Context context, BaseDialogVB2.Call call) {
        super(context);
        this.call = call;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogIdentityAuthenticationBinding getLayout() {
        return BusinessDialogIdentityAuthenticationBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    public void setListener() {
        ((BusinessDialogIdentityAuthenticationBinding) this.binding).closeBtn.setOnClickListener(v -> {
            dismiss();
        });
        ((BusinessDialogIdentityAuthenticationBinding) this.binding).submit.setOnClickListener(v2 -> {
            String name = ((BusinessDialogIdentityAuthenticationBinding) this.binding).name.getText().toString().trim();
            String idCard = ((BusinessDialogIdentityAuthenticationBinding) this.binding).idCard.getText().toString().trim();
            if (name.equals(BuildConfig.VERSION_NAME) || idCard.equals(BuildConfig.VERSION_NAME)) {
                ToastUtils.show(Super.getContext(), "身份证信息格式错误");
            } else {
                BusHttp.common().identityAuthentication(name, idCard, new Result<IdentityAuthentication>() { // from class: ps.center.application.dialog.IdentityAuthenticationDialog.1
                    @Override // ps.center.library.http.base.Result
                    public void success(IdentityAuthentication obj) {
                        if (obj.isAuthentication()) {
                            ToastUtils.show(Super.getContext(), "认证成功");
                        } else {
                            ToastUtils.show(Super.getContext(), "认证失败");
                        }
                        Save.instance.put("IdentityAuthenticationStatus", Boolean.valueOf(obj.isAuthentication()));
                        IdentityAuthenticationDialog.this.dismiss();
                        DataChangeManager.get().change(1, BuildConfig.VERSION_NAME);
                        IdentityAuthenticationDialog.this.call.call(BuildConfig.VERSION_NAME);
                    }

                    @Override // ps.center.library.http.base.Result
                    public void err(int code, String message) {
                        ToastUtils.show(Super.getContext(), message);
                    }
                });
            }
        });
    }
}