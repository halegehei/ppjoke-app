package ps.center.utils;

import android.app.ActivityManager;
import android.content.Context;
import java.util.List;

/* loaded from: classes.jar:ps/center/utils/ProcessUtils.class */
public class ProcessUtils {
    public static boolean isProcessRunning(Context context, String processName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = activityManager.getRunningAppProcesses();
        if (runningProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.processName.contains(processName) && processInfo.importance == 125) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}