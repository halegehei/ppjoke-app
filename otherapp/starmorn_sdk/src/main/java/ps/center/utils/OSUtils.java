package ps.center.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/* loaded from: classes.jar:ps/center/utils/OSUtils.class */
public class OSUtils {
    public static boolean jumpStoreDetailsPage(Activity activity, String packageName) {
        if (activity == null || packageName == null) {
            return false;
        }
        try {
            Uri uri = Uri.parse("market://details?id=" + packageName);
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}