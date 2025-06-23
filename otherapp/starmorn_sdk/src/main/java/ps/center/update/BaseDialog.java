package ps.center.update;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

/* loaded from: classes.jar:ps/center/update/BaseDialog.class */
public abstract class BaseDialog extends Dialog {
    private boolean us_close;
    private int animationStyle;
    private Window window;

    /* loaded from: classes.jar:ps/center/update/BaseDialog$Call.class */
    public interface Call {
        void call(Object obj);
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListener();

    public BaseDialog(Context context, int themeResId, int animationStyle) {
        super(context, themeResId);
        this.us_close = true;
        this.animationStyle = 0;
        this.animationStyle = animationStyle;
    }

    public BaseDialog(Context context, int themeResId, int animationStyle, boolean close) {
        super(context, themeResId);
        this.us_close = true;
        this.animationStyle = 0;
        this.us_close = close;
        this.animationStyle = animationStyle;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        this.window = getWindow();
        if (this.animationStyle != 0 && this.window != null) {
            this.window.setWindowAnimations(this.animationStyle);
        }
        if (this.window != null) {
            WindowManager.LayoutParams layoutParams = this.window.getAttributes();
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.dimAmount = 0.6f;
            this.window.setAttributes(layoutParams);
        }
        initView();
        initData();
        setListener();
    }

    public void setRootAlpha(float alpha) {
        if (this.window != null) {
            WindowManager.LayoutParams layoutParams = this.window.getAttributes();
            layoutParams.dimAmount = alpha;
            this.window.setAttributes(layoutParams);
        }
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