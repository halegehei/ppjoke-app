package ps.center.views.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import androidx.viewbinding.ViewBinding;
import ps.center.R;

/* loaded from: classes.jar:ps/center/views/dialog/BaseDialogVB2.class */
public abstract class BaseDialogVB2<T extends ViewBinding> extends android.app.Dialog {
    private boolean us_close;
    private final int animationStyle;
    public T binding;

    /* loaded from: classes.jar:ps/center/views/dialog/BaseDialogVB2$Call.class */
    public interface Call {
        void call(Object obj);
    }

    protected abstract T getLayout();

    protected abstract void initData();

    public BaseDialogVB2(Context context) {
        super(context, R.style.dialogBlackBack);
        this.us_close = true;
        this.animationStyle = R.style.dialogAnimation__min__max;
    }

    public BaseDialogVB2(Context context, int themeResId, int animationStyle) {
        super(context, themeResId);
        this.us_close = true;
        this.animationStyle = animationStyle;
    }

    public BaseDialogVB2(Context context, int themeResId, int animationStyle, boolean us_close) {
        super(context, themeResId);
        this.us_close = true;
        this.us_close = us_close;
        this.animationStyle = animationStyle;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = getLayout();
        setContentView(this.binding.getRoot());
        Window window = getWindow();
        if (this.animationStyle != 0 && window != null) {
            window.setWindowAnimations(this.animationStyle);
        }
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.dimAmount = 0.8f;
            window.setAttributes(layoutParams);
        }
        initData();
        setListener();
    }

    public void setListener() {
    }

    protected boolean isLayoutMatchParent() {
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            if (this.us_close) {
                return super.onKeyDown(keyCode, event);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}