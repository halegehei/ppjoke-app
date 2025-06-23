package ps.center.views.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import ps.center.R;
import ps.center.views.listener.OnClickListener;

/* loaded from: classes.jar:ps/center/views/dialog/DefaultBuilder.class */
public class DefaultBuilder {
    private final Context mContext;
    private CustomDialog customDialog;
    private View rootView;
    private boolean mach;

    /* loaded from: classes.jar:ps/center/views/dialog/DefaultBuilder$CallBack.class */
    public interface CallBack {
        void createOver(View view, CustomDialog customDialog);
    }

    public DefaultBuilder(Context context) {
        this.mContext = context;
    }

    public DefaultBuilder layout(int layoutId) {
        setLayout(layoutId, DialogStyle.blackBack);
        return this;
    }

    public DefaultBuilder layout(int layoutId, DialogStyle style) {
        setLayout(layoutId, style);
        return this;
    }

    public DefaultBuilder anima(DialogAnima animation) {
        setAnimation(animation);
        return this;
    }

    public DefaultBuilder isMach(boolean isMach) {
        this.mach = isMach;
        return this;
    }

    public DefaultBuilder setText(int id, String text) {
        ((TextView) this.rootView.findViewById(id)).setText(text);
        return this;
    }

    public DefaultBuilder setTextColor(int id, int color) {
        ((TextView) this.rootView.findViewById(id)).setTextColor(color);
        return this;
    }

    public DefaultBuilder setImage(int id, int mipmapId) {
        ((ImageView) this.rootView.findViewById(id)).setImageResource(mipmapId);
        return this;
    }

    public DefaultBuilder setBackground(int id, int mipmapId) {
        this.rootView.findViewById(id).setBackgroundResource(mipmapId);
        return this;
    }

    public DefaultBuilder outCancel(boolean outCancel) {
        this.customDialog.setCanceledOnTouchOutside(outCancel);
        return this;
    }

    public DefaultBuilder keyBackCancel(boolean isKeyBackCancel) {
        this.customDialog.setKeyBackCancel(isKeyBackCancel);
        return this;
    }

    public DefaultBuilder listener(int id, DialogClickListener dialogClickListener) {
        bindListener(200L, id, dialogClickListener);
        return this;
    }

    public DefaultBuilder listener(long time, int id, DialogClickListener dialogClickListener) {
        bindListener(time, id, dialogClickListener);
        return this;
    }

    public void show(CallBack callBack) {
        this.customDialog.show();
        callBack.createOver(this.rootView, this.customDialog);
    }

    public void show() {
        this.customDialog.show();
    }

    private void setLayout(int layoutId, DialogStyle style) {
        int themeId;
        if (this.rootView != null) {
            return;
        }
        this.rootView = LayoutInflater.from(this.mContext).inflate(layoutId, (ViewGroup) null);
        switch (style) {
            case blackBack:
                themeId = R.style.dialogBlackBack;
                break;
            case NotBack:
                themeId = R.style.dialogAlphaBack;
                break;
            default:
                themeId = R.style.dialogBlackBack;
                break;
        }
        this.customDialog = new CustomDialog(this.mContext, themeId, this.rootView, this.mach);
    }

    private void bindListener(long time, int id, final DialogClickListener dialogClickListener) {
        this.rootView.findViewById(id).setOnClickListener(new OnClickListener(time) { // from class: ps.center.views.dialog.DefaultBuilder.1
            @Override // ps.center.views.listener.OnClickListener
            public void click(View view) {
                dialogClickListener.onClick(DefaultBuilder.this.rootView, DefaultBuilder.this.customDialog);
            }
        });
    }

    private void setAnimation(DialogAnima dialogAnima) {
        Window window = this.customDialog.getWindow();
        if (window != null) {
            switch (dialogAnima) {
                case enlarge:
                    window.setWindowAnimations(R.style.dialogAnimation__min__max);
                    break;
                case narrow:
                    window.setWindowAnimations(R.style.dialogAnimation__max__min);
                    break;
                case rise:
                    window.setWindowAnimations(R.style.dialogAnimation__down__center);
                    break;
                case decline:
                    window.setWindowAnimations(R.style.dialogAnimation__up__center);
                    break;
            }
        }
    }

    public CustomDialog getCustomDialog() {
        return this.customDialog;
    }

    public View getRootView() {
        return this.rootView;
    }

    public Context getContext() {
        return this.mContext;
    }
}