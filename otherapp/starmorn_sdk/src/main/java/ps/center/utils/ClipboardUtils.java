package ps.center.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/ClipboardUtils.class */
public class ClipboardUtils {
    public static void copyToClip(Context context, String str, OrSuccess<String> orSuccess) {
        try {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService("clipboard");
            ClipData clipData = ClipData.newPlainText(null, str);
            clipboard.setPrimaryClip(clipData);
            orSuccess.success(str);
        } catch (Exception e) {
            orSuccess.success(str);
        }
    }

    public static void getClipContent(final Activity activity, final OrSuccess<String> orSuccess) {
        try {
            activity.runOnUiThread(new Runnable() { // from class: ps.center.utils.ClipboardUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = clipboard.getPrimaryClip();
                    if (clipData != null && clipData.getItemCount() > 0) {
                        CharSequence text = clipData.getItemAt(0).getText();
                        String pasteString = text.toString();
                        orSuccess.success(pasteString);
                        return;
                    }
                    orSuccess.err(BuildConfig.VERSION_NAME);
                }
            });
        } catch (Exception e) {
            orSuccess.err(BuildConfig.VERSION_NAME);
        }
    }
}