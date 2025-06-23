package ps.center.views.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;
import com.gyf.immersionbar.ImmersionBar;
import ps.center.views.ViewsLibraryConfig;
import ps.center.views.listener.OnClickListener;

/* loaded from: classes.jar:ps/center/views/activity/BaseActivityVB.class */
public abstract class BaseActivityVB<T extends ViewBinding> extends AppCompatActivity {
    public Bundle savedInstanceState;
    public IntentGet intentGet;
    public T binding;

    protected abstract T getLayout();

    protected abstract void initData(IntentGet intentGet);

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.savedInstanceState = bundle;
        if (ViewsLibraryConfig.IS_OPEN_ALL_FLAG_TRANSLUCENT_STATUS) {
            getWindow().addFlags(67108864);
        }
        if (ViewsLibraryConfig.IS_OPEN_ALL_FLAG_TRANSLUCENT_NAVIGATION) {
            getWindow().addFlags(134217728);
        }
        if (ViewsLibraryConfig.IS_OPEN_ALL_FLAG_SECURE) {
            getWindow().addFlags(8192);
        }
        if (getImmersionBarEnable()) {
            ImmersionBar.with(this).statusBarDarkFont(true).init();
        }
        this.binding = (T) getLayout();
        setContentView(this.binding.getRoot());
        this.intentGet = new IntentGet(getIntent());
        initData(this.intentGet);
        setListener();
    }

    protected void setListener() {
    }

    public void toggleInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, 2);
    }

    public final <VIEW extends View> VIEW findViewById(int i, View.OnClickListener onClickListener) {
        VIEW view = (VIEW) findViewById(i);
        view.setOnClickListener(onClickListener);
        return view;
    }

    public final <VIEW extends View> VIEW findViewById(int i, OnClickListener onClickListener) {
        VIEW view = (VIEW) findViewById(i);
        view.setOnClickListener(onClickListener);
        return view;
    }

    public void hideInput(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showInput(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

    public boolean getImmersionBarEnable() {
        return true;
    }

    public void enableScreenshot(boolean isEnable) {
        if (isEnable) {
            getWindow().addFlags(8192);
        } else {
            getWindow().clearFlags(8192);
        }
    }
}