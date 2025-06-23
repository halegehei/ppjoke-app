package ps.center.views.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

/* loaded from: classes.jar:ps/center/views/dialog/BaseDialog2.class */
public abstract class BaseDialog2 extends android.app.Dialog {
    private boolean us_close;
    private final int animationStyle;

    /* loaded from: classes.jar:ps/center/views/dialog/BaseDialog2$Call.class */
    public interface Call {
        void call(Object obj);
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    public BaseDialog2(Context context, int themeResId, int animationStyle) {
        super(context, themeResId);
        this.us_close = true;
        this.animationStyle = animationStyle;
    }

    public BaseDialog2(Context context, int themeResId, int animationStyle, boolean us_close) {
        super(context, themeResId);
        this.us_close = true;
        this.us_close = us_close;
        this.animationStyle = animationStyle;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        Window window = getWindow();
        if (this.animationStyle != 0 && window != null) {
            window.setWindowAnimations(this.animationStyle);
        }
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.dimAmount = 0.8f;
            window.setAttributes(layoutParams);
        }
        initView();
        initData();
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