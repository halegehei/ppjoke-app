package ps.center.utils.luban;

import java.io.File;

/* loaded from: classes.jar:ps/center/utils/luban/OnCompressListener.class */
public interface OnCompressListener {
    void onStart();

    void onSuccess(File file);

    void onError(Throwable th);
}