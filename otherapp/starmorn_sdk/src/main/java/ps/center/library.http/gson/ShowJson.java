package ps.center.library.http.gson;

import okhttp3.logging.HttpLoggingInterceptor;
import ps.center.library.http.LogUtils;

/* loaded from: classes.jar:ps/center/library/http/gson/ShowJson.class */
public class ShowJson implements HttpLoggingInterceptor.Logger {
    public void log(String message) {
        LogUtils.longE.e("http-log-", message);
    }
}