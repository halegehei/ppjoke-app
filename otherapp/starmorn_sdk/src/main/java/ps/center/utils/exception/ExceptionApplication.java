package ps.center.utils.exception;

import android.app.Application;
import androidx.annotation.NonNull;
import java.lang.Thread;
import ps.center.application.exception.ExceptionLogsBean;
import ps.center.utils.TimeUtils;

/* loaded from: classes.jar:ps/center/utils/exception/ExceptionApplication.class */
public abstract class ExceptionApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    }

    /* loaded from: classes.jar:ps/center/utils/exception/ExceptionApplication$ExceptionHandler.class */
    private static class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
            try {
                StackTraceElement[] stackTrace = throwable.getStackTrace();
                StringBuilder stringBuilder = new StringBuilder();
                String title = throwable.getLocalizedMessage();
                stringBuilder.append(title).append("\n");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    String log = stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
                    stringBuilder.append(log).append("\n");
                }
                stringBuilder.append("\n\n");
                if (throwable.getCause() != null) {
                    StackTraceElement[] stackTrace1 = throwable.getCause().getStackTrace();
                    for (StackTraceElement stackTraceElement2 : stackTrace1) {
                        String log2 = stackTraceElement2.getMethodName() + "(" + stackTraceElement2.getFileName() + ":" + stackTraceElement2.getLineNumber() + ")";
                        stringBuilder.append(log2).append("\n");
                    }
                }
                String time = TimeUtils.timeToData("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis());
                ExceptionLogsBean.putExceptionInfo(time, stringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}