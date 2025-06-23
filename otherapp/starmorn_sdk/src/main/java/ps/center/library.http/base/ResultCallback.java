package ps.center.library.http.base;

import ps.center.utils.Super;
import ps.center.utils.ToastUtils;

/* loaded from: classes.jar:ps/center/library/http/base/ResultCallback.class */
public abstract class ResultCallback<T> extends HttpObserver<T> {
    private boolean isShowErrToast;

    public ResultCallback() {
        this.isShowErrToast = true;
    }

    public ResultCallback(boolean isShowErrToast) {
        this.isShowErrToast = true;
        this.isShowErrToast = isShowErrToast;
    }

    @Override // ps.center.library.http.base.HttpObserver
    protected void success(T obj) {
    }

    @Override // ps.center.library.http.base.HttpObserver
    protected void err(int code, String message) {
        if (code != 200) {
            if (message != null) {
                ToastUtils.show(Super.getContext(), message);
            } else {
                ToastUtils.show(Super.getContext(), "message=null");
            }
        }
    }

    @Override // ps.center.library.http.base.HttpObserver
    public void end() {
    }
}