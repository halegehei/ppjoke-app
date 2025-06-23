package ps.center.views.toolsViews;

import android.content.Context;

import ps.center.databinding.DialogPermissionRequestTipsBinding;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/views/toolsViews/PermissionRequestTipsDialog.class */
public class PermissionRequestTipsDialog extends BaseDialogVB2<DialogPermissionRequestTipsBinding> {
    private String title;
    private String cancel;
    private String submit;
    private final String content;
    private final BaseDialogVB2.Call call;

    public PermissionRequestTipsDialog(Context context, String content, BaseDialogVB2.Call call) {
        super(context);
        this.title = "提示";
        this.cancel = "取消";
        this.submit = "确认";
        this.content = content;
        this.call = call;
    }

    public PermissionRequestTipsDialog(Context context, String title, String content, String cancel, String submit, BaseDialogVB2.Call call) {
        super(context);
        this.title = "提示";
        this.cancel = "取消";
        this.submit = "确认";
        this.title = title;
        this.content = content;
        this.cancel = cancel;
        this.submit = submit;
        this.call = call;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public DialogPermissionRequestTipsBinding getLayout() {
        return DialogPermissionRequestTipsBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
        ((DialogPermissionRequestTipsBinding) this.binding).title.setText(this.title);
        ((DialogPermissionRequestTipsBinding) this.binding).content.setText(this.content);
        ((DialogPermissionRequestTipsBinding) this.binding).cancel.setText(this.cancel);
        ((DialogPermissionRequestTipsBinding) this.binding).submit.setText(this.submit);
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    public void setListener() {
        ((DialogPermissionRequestTipsBinding) this.binding).cancel.setOnClickListener(v -> {
            dismiss();
            this.call.call("cancel");
        });
        ((DialogPermissionRequestTipsBinding) this.binding).submit.setOnClickListener(v2 -> {
            dismiss();
            this.call.call("submit");
        });
    }
}