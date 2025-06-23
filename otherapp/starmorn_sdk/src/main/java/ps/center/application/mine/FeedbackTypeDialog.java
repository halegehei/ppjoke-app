package ps.center.application.mine;

import android.content.Context;

import ps.center.databinding.BusinessDialogFeedbackTypeBinding;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/mine/FeedbackTypeDialog.class */
public class FeedbackTypeDialog extends BaseDialogVB2<BusinessDialogFeedbackTypeBinding> {
    private BaseDialogVB2.Call call;

    public FeedbackTypeDialog(Context context, BaseDialogVB2.Call call) {
        super(context);
        this.call = call;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogFeedbackTypeBinding getLayout() {
        return BusinessDialogFeedbackTypeBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    public void setListener() {
        ((BusinessDialogFeedbackTypeBinding) this.binding).item1.setOnClickListener(v -> {
            dismiss();
            this.call.call(1);
        });
        ((BusinessDialogFeedbackTypeBinding) this.binding).item2.setOnClickListener(v2 -> {
            dismiss();
            this.call.call(2);
        });
        ((BusinessDialogFeedbackTypeBinding) this.binding).item3.setOnClickListener(v3 -> {
            dismiss();
            this.call.call(3);
        });
        ((BusinessDialogFeedbackTypeBinding) this.binding).item4.setOnClickListener(v4 -> {
            dismiss();
            this.call.call(4);
        });
        ((BusinessDialogFeedbackTypeBinding) this.binding).item5.setOnClickListener(v5 -> {
            dismiss();
            this.call.call(5);
        });
        ((BusinessDialogFeedbackTypeBinding) this.binding).item6.setOnClickListener(v6 -> {
            dismiss();
            this.call.call(6);
        });
        ((BusinessDialogFeedbackTypeBinding) this.binding).item7.setOnClickListener(v7 -> {
            dismiss();
            this.call.call(7);
        });
    }
}