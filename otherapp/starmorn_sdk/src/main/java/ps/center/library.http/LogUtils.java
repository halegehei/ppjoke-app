package ps.center.library.http;

import android.util.Log;

/* loaded from: classes.jar:ps/center/library/http/LogUtils.class */
public class LogUtils {
    public static boolean DEBUG = false;

    public static void e(Object str) {
        if (!DEBUG) {
            Log.e("http-log", "DEBUG = false");
            return;
        }
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        Log.e("http-log", targetStackTraceElement.getMethodName() + "(" + targetStackTraceElement.getFileName() + ":" + targetStackTraceElement.getLineNumber() + ")");
        Log.e("http-log", (str == null ? "null" : str.toString()) + "\n\u3000");
    }

    public static void ee(Object str) {
        if (!DEBUG) {
            Log.e("http-log", "DEBUG = false");
        } else {
            Log.e("http-log", str == null ? "null" : str.toString());
        }
    }

    public static void d(Object str) {
        if (!DEBUG) {
            Log.d("http-log", "DEBUG = false");
            return;
        }
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        Log.d("http-log", targetStackTraceElement.getMethodName() + "(" + targetStackTraceElement.getFileName() + ":" + targetStackTraceElement.getLineNumber() + ")");
        Log.d("http-log", (str == null ? "null" : str.toString()) + "\n\u3000");
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

    /* loaded from: classes.jar:ps/center/library/http/LogUtils$longE.class */
    public static class longE {
        private static int LOG_MAX_LENGTH = 2000;

        public static void e(String TAG, String msg) {
            if (!LogUtils.DEBUG) {
                Log.e("http-log", "DEBUG = false");
                return;
            }
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAX_LENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.e(TAG + i, msg.substring(start, end));
                    start = end;
                    end += LOG_MAX_LENGTH;
                } else {
                    Log.e(TAG, msg.substring(start, strLength));
                    return;
                }
            }
        }
    }
}