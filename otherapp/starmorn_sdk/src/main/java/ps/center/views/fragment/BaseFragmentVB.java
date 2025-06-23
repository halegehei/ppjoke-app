package ps.center.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;

/* loaded from: classes.jar:ps/center/views/fragment/BaseFragmentVB.class */
public abstract class BaseFragmentVB<T extends ViewBinding> extends Fragment {
    public Bundle savedInstanceState;
    public BundleGet bundleGet;
    public AppCompatActivity mActivity;
    public View mRootView;
    public T binding;

    protected abstract T getLayout();

    protected abstract void initData(BundleGet bundleGet);

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (this.mActivity == null) {
            this.mActivity = (AppCompatActivity) context;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        if (this.mRootView != null) {
            ViewGroup viewGroup = (ViewGroup) this.mRootView.getRootView();
            if (viewGroup != null) {
                viewGroup.removeView(this.mRootView);
            }
            return this.mRootView;
        }
        this.binding = getLayout();
        this.mRootView = this.binding.getRoot();
        if (isAdded()) {
            this.bundleGet = new BundleGet(getArguments());
            initData(this.bundleGet);
            setListener();
        } else {
            ToastUtils.show(Super.getContext(), "当前页面异常，请重启APP");
        }
        return this.mRootView;
    }

    protected void setListener() {
    }
}