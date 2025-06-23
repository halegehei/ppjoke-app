package ps.center.views.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;


public class CustomDialog extends android.app.Dialog {
    private final View rootView;
    private boolean isKeyBackCancel;
    private boolean mach;
    private DismissListener dismissListener;

    public CustomDialog(@NonNull Context context, int style, View rootView, boolean mach) {
        super(context, style);
        this.isKeyBackCancel = true;
        this.rootView = rootView;
        this.mach = mach;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.rootView);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            if (this.mach) {
            }
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.dimAmount = 0.7f;
            window.setAttributes(layoutParams);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        if (this.dismissListener != null) {
            this.dismissListener.dismiss();
        }
    }

    public void setKeyBackCancel(boolean isKeyBackCancel) {
        this.isKeyBackCancel = isKeyBackCancel;
    }

    public void setDismissListener(DismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == 4 && !this.isKeyBackCancel) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}