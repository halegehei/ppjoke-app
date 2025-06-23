package ps.center.library.http.base;

import android.content.Context;

/* loaded from: classes.jar:ps/center/library/http/base/Result.class */
public abstract class Result<T> {
    private Context context;

    public Result() {
    }

    public Result(Context context) {
        this.context = context;
    }

    public Result(Context context, boolean isLoading) {
    }

    public void success(T obj) {
    }

    public void err(int code, String message) {
    }

    public void end() {
    }
}