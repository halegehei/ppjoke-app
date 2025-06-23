package ps.center.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/* loaded from: classes.jar:ps/center/utils/ActivityUtils.class */
public class ActivityUtils {
    public static void goAppSettings(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "跳转设置失败，请手动前往设置", 0).show();
        }
    }

    public static void goAppSettings(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
            activity.startActivityForResult(intent, 200);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "跳转设置失败，请手动前往设置", 0).show();
        }
    }

    public static void goMainSettings(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.SETTINGS");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "跳转设置失败，请手动前往设置", 0).show();
        }
    }

    public static void goLocationServiceSettings(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "跳转设置失败，请手动前往设置", 0).show();
        }
    }

    public static void jumpPhoneHome(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory("android.intent.category.HOME");
        context.startActivity(intent);
    }

    public static void start(Activity activity, Class<?> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }

    public static void startForResult(Activity activity, int requestCode, Class<?> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivityForResult(intent, requestCode);
    }
}