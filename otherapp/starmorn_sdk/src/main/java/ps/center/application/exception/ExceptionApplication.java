package ps.center.application.exception;

import android.app.Application;
import android.content.Intent;
import android.os.Process;
import java.lang.Thread;
import ps.center.utils.TimeUtils;
import ps.center.utils.exception.ExceptionLogsActivity;

/* loaded from: classes.jar:ps/center/application/exception/ExceptionApplication.class */
public abstract class ExceptionApplication extends Application {
    private boolean isJumpErrLogPage = false;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(null));
    }

    public void setJumpErrLogPage(boolean isJumpErrLogPage) {
        this.isJumpErrLogPage = isJumpErrLogPage;
    }

    /* loaded from: classes.jar:ps/center/application/exception/ExceptionApplication$ExceptionHandler.class */
    private class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        public Class<?> clazz;

        public ExceptionHandler(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable throwable) {
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
                if (ExceptionApplication.this.isJumpErrLogPage) {
                    Intent intent = new Intent(ExceptionApplication.this.getApplicationContext(), (Class<?>) ExceptionLogsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    ExceptionApplication.this.startActivity(intent);
                } else {
                    Process.killProcess(Process.myPid());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}