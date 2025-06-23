package ps.center.library.http.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import com.google.gson.JsonSyntaxException;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SSLHandshakeException;
import ps.center.library.http.LogUtils;

/* loaded from: classes.jar:ps/center/library/http/base/HttpObserver.class */
public abstract class HttpObserver<T> implements Observer<BaseBean<T>>, Disposable {
    final AtomicReference<Disposable> s = new AtomicReference<>();
    private Object obj;
    private Result<T> result;
    private boolean isLoading;

    protected abstract void success(T t);

    protected abstract void err(int i, String str);

    protected abstract void end();

    protected HttpObserver(Object obj) {
        this.obj = obj;
    }

    protected HttpObserver() {
    }

    protected HttpObserver(Result<T> result, boolean isLoading) {
        this.result = result;
        this.isLoading = isLoading;
    }

    public final void onSubscribe(@NonNull Disposable s) {
        if (EndConsumerHelper.setOnce(this.s, s, getClass())) {
        }
    }

    public void onNext(@NonNull BaseBean<T> baseBean) {
        if (isCancel()) {
            return;
        }
        if (baseBean.code == 200) {
            if (baseBean.data != null) {
                success(baseBean.data);
                return;
            } else {
                err(200, baseBean.message);
                return;
            }
        }
        err(baseBean.code, baseBean.message);
    }

    public void onError(@NonNull Throwable e) {
        if (e instanceof JsonSyntaxException) {
            err(HttpStatus.JSON_EXCEPTION, e.toString());
        } else if ((e instanceof SocketTimeoutException) || (e instanceof UnknownHostException) || (e instanceof SocketException)) {
            err(HttpStatus.SOCKET_TIMEOUT, "网络请求超时!");
        } else if (e instanceof SSLHandshakeException) {
            err(202, "证书验证失败！");
        } else {
            err(0, e.toString());
        }
        e.printStackTrace();
        LogUtils.e("HttpException: " + e.toString());
    }

    public void onComplete() {
        end();
    }

    public final boolean isDisposed() {
        return this.s.get() == DisposableHelper.DISPOSED;
    }

    public final void dispose() {
        DisposableHelper.dispose(this.s);
    }

    private boolean isCancel() {
        try {
            if (this.obj != null) {
                if (this.obj instanceof Activity) {
                    return ((Activity) this.obj).isFinishing();
                }
                return ((this.obj instanceof Fragment) || !(this.obj instanceof Dialog) || ((Dialog) this.obj).isShowing()) ? false : true;
            }
            return false;
        } catch (Exception e) {
            LogUtils.e("HttpObserver Exception:" + e.toString());
            return true;
        }
    }
}