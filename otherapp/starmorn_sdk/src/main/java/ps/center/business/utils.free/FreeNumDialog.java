package ps.center.business.utils.free;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.KeyEvent;
import ps.center.R;
import ps.center.databinding.BusinessDialogFreeNumBinding;
import ps.center.utils.Save;
import ps.center.views.dialog.BaseDialogVB;

/* loaded from: classes.jar:ps/center/business/utils/free/FreeNumDialog.class */
public class FreeNumDialog extends BaseDialogVB<BusinessDialogFreeNumBinding> {
    private final Call call;
    private final String num;

    public FreeNumDialog(Context context, String num, Call call) {
        super(context, R.style.free_number_dialog_style, R.anim.free_number_dialog_animation);
        this.call = call;
        this.num = num;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB
    public BusinessDialogFreeNumBinding getLayout() {
        return BusinessDialogFreeNumBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB
    protected void initData() {
        int colorNum;
        int colorNum2;
        int colorNum3;
        int colorNum4;
        int colorNum5;
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        ((BusinessDialogFreeNumBinding) this.binding).desc.setText(String.format("剩余%s次免费使用次数", this.num));
        GradientDrawable gradientDrawable = (GradientDrawable) ((BusinessDialogFreeNumBinding) this.binding).rootLayout.getBackground();
        String color = Save.instance.getString("freeDialogDialogBackColor", "#FFFFFF");
        try {
            colorNum = Color.parseColor(color);
        } catch (Exception e) {
            colorNum = 0;
        }
        gradientDrawable.setColor(colorNum);
        GradientDrawable gradientDrawable2 = (GradientDrawable) ((BusinessDialogFreeNumBinding) this.binding).submit.getBackground();
        String color2 = Save.instance.getString("freeButtonColor", "#4D55FF");
        try {
            colorNum2 = Color.parseColor(color2);
        } catch (Exception e2) {
            colorNum2 = 0;
        }
        gradientDrawable2.setColor(colorNum2);
        String color3 = Save.instance.getString("freeTitleTextColor", "#222222");
        try {
            colorNum3 = Color.parseColor(color3);
        } catch (Exception e3) {
            colorNum3 = 0;
        }
        ((BusinessDialogFreeNumBinding) this.binding).title.setTextColor(colorNum3);
        String color4 = Save.instance.getString("freeButtonTextColor", "#FFFFFF");
        try {
            colorNum4 = Color.parseColor(color4);
        } catch (Exception e4) {
            colorNum4 = 0;
        }
        ((BusinessDialogFreeNumBinding) this.binding).submit.setTextColor(colorNum4);
        String color5 = Save.instance.getString("freeContentTextColor", "#999999");
        try {
            colorNum5 = Color.parseColor(color5);
        } catch (Exception e5) {
            colorNum5 = 0;
        }
        ((BusinessDialogFreeNumBinding) this.binding).desc.setTextColor(colorNum5);
        ((BusinessDialogFreeNumBinding) this.binding).submit.setOnClickListener(v -> {
            dismiss();
            this.call.call("submit");
        });
        ((BusinessDialogFreeNumBinding) this.binding).returnBtn.setOnClickListener(v2 -> {
            dismiss();
            this.call.call("cancel");
        });
    }

    @Override // ps.center.views.dialog.BaseDialogVB, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}