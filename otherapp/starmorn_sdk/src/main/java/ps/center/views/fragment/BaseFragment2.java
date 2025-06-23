package ps.center.views.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ps.center.R;
import ps.center.views.activity.BaseActivity;

@Deprecated
/* loaded from: classes.jar:ps/center/views/fragment/BaseFragment2.class */
public abstract class BaseFragment2 extends Fragment {
    private BaseActivity mActivity;
    public View mRootView;

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        } else {
            throw new IllegalStateException("宿主 Activity 必须继承 BaseActivity");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.mRootView != null) {
            ViewGroup viewGroup = (ViewGroup) this.mRootView.getRootView();
            if (viewGroup != null) {
                viewGroup.removeView(this.mRootView);
            }
            return this.mRootView;
        }
        this.mRootView = inflater.inflate(getLayout(), container, false);
        initView();
        initData();
        setListener();
        return this.mRootView;
    }

    public final <VIEW extends View> VIEW findViewById(int i) {
        return (VIEW) this.mRootView.findViewById(i);
    }

    protected void setListener() {
    }

    @CheckResult
    public <T> ActivityGo<T> go(Class<T> tClass) {
        return new ActivityGo<>(tClass);
    }

    /* loaded from: classes.jar:ps/center/views/fragment/BaseFragment2$ActivityGo.class */
    public class ActivityGo<T> {
        private final Class<T> toClass;
        private int animaIn;
        private int animaOut;
        private Map<String, Object> param;

        public ActivityGo(Class<T> toClass) {
            this.toClass = toClass;
        }

        public void jump(Activity activity, boolean finishSelf) {
            if (activity == null) {
                return;
            }
            if (this.toClass != null) {
                Intent intent = new Intent(BaseFragment2.this.getContext(), (Class<?>) this.toClass);
                if (this.param != null) {
                    paramSaveToIntent(intent, this.param);
                }
                activity.startActivity(intent);
                if (finishSelf) {
                    activity.finish();
                }
            }
            if (this.animaIn == 0 && this.animaOut == 0) {
                return;
            }
            activity.overridePendingTransition(this.animaIn == 0 ? R.anim.activity_animation__not : this.animaIn, this.animaOut == 0 ? R.anim.activity_animation__not : this.animaOut);
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