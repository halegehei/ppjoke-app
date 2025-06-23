package ps.center.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.CheckResult;
import androidx.appcompat.app.AppCompatActivity;
import com.gyf.immersionbar.ImmersionBar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ps.center.views.ViewsLibraryConfig;
import ps.center.views.listener.OnClickListener;

/* loaded from: classes.jar:ps/center/views/activity/BaseActivity2.class */
public abstract class BaseActivity2 extends AppCompatActivity {
    public Bundle savedInstanceState;

    protected abstract View getLayout();

    protected abstract void initView();

    protected abstract void initData();

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
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
        setContentView(getLayout());
        initView();
        initData();
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

    @CheckResult
    public <T> ActivityGo<T> go(Class<T> tClass) {
        return new ActivityGo<>(tClass);
    }

    /* loaded from: classes.jar:ps/center/views/activity/BaseActivity2$ActivityGo.class */
    public class ActivityGo<T> {
        private final Class<T> toClass;
        private int animaIn;
        private int animaOut;
        private Map<String, Object> param;

        public ActivityGo(Class<T> toClass) {
            this.toClass = toClass;
        }

        public void jump(boolean finishSelf) {
            if (this.toClass != null) {
                Intent intent = new Intent((Context) BaseActivity2.this, (Class<?>) this.toClass);
                if (this.param != null) {
                    paramSaveToIntent(intent, this.param);
                }
                BaseActivity2.this.startActivity(intent);
                if (finishSelf) {
                    BaseActivity2.this.finish();
                }
            }
            if (this.animaIn == 0 && this.animaOut == 0) {
                return;
            }
            BaseActivity2.this.overridePendingTransition(this.animaIn, this.animaOut);
        }

        @CheckResult
        public ActivityGo<T> put(String key, Object obj) {
            if (this.param == null) {
                this.param = new HashMap();
            }
            this.param.put(key, obj);
            return this;
        }

        @CheckResult
        public ActivityGo<T> anima(int animaIn, int animaOut) {
            this.animaIn = animaIn;
            this.animaOut = animaOut;
            return this;
        }

        private void paramSaveToIntent(Intent intent, Map<String, Object> map) {
            for (Map.Entry<String, Object> item : map.entrySet()) {
                if (item.getValue() instanceof String) {
                    intent.putExtra(item.getKey(), (String) item.getValue());
                } else if (item.getValue() instanceof Integer) {
                    intent.putExtra(item.getKey(), (Integer) item.getValue());
                } else if (item.getValue() instanceof Parcelable) {
                    intent.putExtra(item.getKey(), (Parcelable) item.getValue());
                } else if (item.getValue() instanceof Serializable) {
                    intent.putExtra(item.getKey(), (Serializable) item.getValue());
                } else if (item.getValue() instanceof Long) {
                    intent.putExtra(item.getKey(), (Long) item.getValue());
                } else if (item.getValue() instanceof Float) {
                    intent.putExtra(item.getKey(), (Float) item.getValue());
                } else if (item.getValue() instanceof Double) {
                    intent.putExtra(item.getKey(), (Double) item.getValue());
                } else if (item.getValue() instanceof Byte) {
                    intent.putExtra(item.getKey(), (Byte) item.getValue());
                } else if (item.getValue() instanceof Boolean) {
                    intent.putExtra(item.getKey(), (Boolean) item.getValue());
                } else if (item.getValue() instanceof ArrayList) {
                    try {
                        List<Object> obj = (List) map.get(item.getKey());
                        if (obj != null && obj.size() > 0) {
                            if (obj.get(0) instanceof Parcelable) {
                                intent.putParcelableArrayListExtra(item.getKey(), (ArrayList) item.getValue());
                            } else if (obj.get(0) instanceof String) {
                                intent.putStringArrayListExtra(item.getKey(), (ArrayList) item.getValue());
                            }
                        }
                    } catch (Exception e) {
                        Log.e("BaseActivityException:", "传值失败了，用传统的Intent吧～～");
                    }
                }
            }
        }
    }
}