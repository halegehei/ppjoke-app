package ps.center.views.dialog;

import android.content.Context;

/* loaded from: classes.jar:ps/center/views/dialog/Dialog.class */
public class Dialog {
    public static DefaultBuilder create(Context context) {
        return new DefaultBuilder(context);
    }

    public static LoadingBuilder createLoading(Context context, DialogStyle dialogStyle, long timeout) {
        return new LoadingBuilder(context, dialogStyle, timeout);
    }
}