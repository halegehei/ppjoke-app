package ps.center.ossutils;

import android.content.Context;

/* loaded from: classes.jar:ps/center/ossutils/OSSUpload.class */
public final class OSSUpload {
    public static OSSBuilder with(Context context) {
        return new OSSBuilder(context);
    }
}