package ps.center.views.fragment;

import androidx.viewbinding.ViewBinding;
import ps.center.views.dialog.loading.LoadingDialog2;

/* loaded from: classes.jar:ps/center/views/fragment/LoadingBaseFragment.class */
public abstract class LoadingBaseFragment<T extends ViewBinding> extends BaseFragmentVB<T> {
    private LoadingDialog2 backLoadingDialog;

    public void showLoading(boolean dismissAsShow) {
        showLoading(12000, dismissAsShow, null);
    }

    public void showLoading() {
        showLoading(12000, true, null);
    }

    public void showLoading(int timeout, boolean dismissAsShow, LoadingDialog2.TimeoutCallBack timeoutCallBack) {
        try {
            if (this.backLoadingDialog == null && getContext() != null) {
                this.backLoadingDialog = new LoadingDialog2(getContext(), timeout, timeoutCallBack);
            }
            if (this.backLoadingDialog.isShowing()) {
                if (dismissAsShow) {
                    this.backLoadingDialog.dismiss();
                    this.backLoadingDialog.show();
                }
            } else {
                this.backLoadingDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissLoading() {
        try {
            if (this.backLoadingDialog != null && this.backLoadingDialog.isShowing()) {
                this.backLoadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}