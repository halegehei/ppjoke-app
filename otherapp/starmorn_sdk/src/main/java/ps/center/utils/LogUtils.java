package ps.center.utils;

import java.io.FileOutputStream;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/LogUtils.class */
public class LogUtils {
    public static boolean DEBUG = false;

    public static void e(String format, Object... obj) {
        e(String.format(format, obj));
    }

    public static void e(Object str) {
        if (!DEBUG) {
            android.util.Log.e("mylog", "DEBUG = false");
            return;
        }
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        android.util.Log.e("mylog", targetStackTraceElement.getMethodName() + "(" + targetStackTraceElement.getFileName() + ":" + targetStackTraceElement.getLineNumber() + ")");
        android.util.Log.e("mylog", (str == null ? "null" : str.toString()) + "\n\u3000");
    }

    public static void ee(Object str) {
        if (!DEBUG) {
            android.util.Log.e("mylog", "DEBUG = false");
        } else {
            android.util.Log.e("mylog", str == null ? "null" : str.toString());
        }
    }

    public static void d(String format, Object... obj) {
        if (!DEBUG) {
            android.util.Log.d("mylog", "DEBUG = false");
        } else {
            d(String.format(format, obj));
        }
    }

    public static void d(Object str) {
        if (!DEBUG) {
            android.util.Log.d("mylog", "DEBUG = false");
            return;
        }
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        android.util.Log.d("mylog", targetStackTraceElement.getMethodName() + "(" + targetStackTraceElement.getFileName() + ":" + targetStackTraceElement.getLineNumber() + ")");
        android.util.Log.d("mylog", (str == null ? "null" : str.toString()) + "\n\u3000");
    }

    private static StackTraceElement getTargetStackTraceElement() {
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int length = stackTrace.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            boolean isLogMethod = stackTraceElement.getClassName().equals(LogUtils.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
            i++;
        }
        return targetStackTrace;
    }

    /* loaded from: classes.jar:ps/center/utils/LogUtils$longE.class */
    public static class longE {
        private static int LOG_MAX_LENGTH = 2000;

        public static void e(String TAG, String msg) {
            if (!LogUtils.DEBUG) {
                android.util.Log.e("mylog", "DEBUG = false");
                return;
            }
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAX_LENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    android.util.Log.e(TAG + i, msg.substring(start, end));
                    start = end;
                    end += LOG_MAX_LENGTH;
                } else {
                    android.util.Log.e(TAG, msg.substring(start, strLength));
                    return;
                }
            }
        }
    }

    public static void saveLog(String path, String log) {
        if (path == null) {
            return;
        }
        try {
            if (path.equals(BuildConfig.VERSION_NAME) || log == null || log.equals(BuildConfig.VERSION_NAME)) {
                return;
            }
            FileOutputStream outStream = new FileOutputStream(path, true);
            outStream.write(log.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}