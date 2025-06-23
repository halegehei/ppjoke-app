package ps.center.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import ps.center.application.BuildConfig;

public final class ActivityStackUtils {
    private Application application;
    private static ActivityStackUtils instance;
    private List<ActivityLifecycleCallBack> callbacks;
    private String activityName = BuildConfig.VERSION_NAME;
    private long outAppTime = 0;
    private final StackInfo stackInfo = new StackInfo();

    private ActivityStackUtils() { }

    public static ActivityStackUtils getInstance() {
        if (instance == null) {
            synchronized (ActivityStackUtils.class) {
                if (instance == null) {
                    instance = new ActivityStackUtils();
                }
            }
        }
        return instance;
    }

    public void registerActivityLifecycleCallBack(Application application, ActivityLifecycleCallBack callback) {
        this.application = application;
        if (callbacks == null) {
            callbacks = new ArrayList<>();
        }
        if (!callbacks.contains(callback)) {
            callbacks.add(callback);
        }
        if (this.application != null) {
            this.application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                    for (ActivityLifecycleCallBack cb : callbacks) {
                        cb.onActivityCreate(activity, savedInstanceState);
                    }
                    activityName = BuildConfig.VERSION_NAME;
                    stackInfo.getActivityStackList().add(activity);
                    for (ActivityLifecycleCallBack cb : callbacks) {
                        cb.onStackInfo(stackInfo);
                    }
                }

                @Override
                public void onActivityStarted(@NonNull Activity activity) {
                    for (ActivityLifecycleCallBack cb : callbacks) {
                        cb.onActivityStart(activity);
                    }
                    if (!activityName.equals(activity.getLocalClassName())) {
                        activityName = BuildConfig.VERSION_NAME;
                    }
                    if (activityName.equals(activity.getLocalClassName())) {
                        for (ActivityLifecycleCallBack cb : callbacks) {
                            cb.outAppReBack(activity, System.currentTimeMillis() - outAppTime);
                        }
                    }
                }

                @Override
                public void onActivityResumed(@NonNull Activity activity) {
                    for (ActivityLifecycleCallBack cb : callbacks) {
                        cb.onActivityResumed(activity);
                    }
                }

                @Override
                public void onActivityPaused(@NonNull Activity activity) {
                    for (ActivityLifecycleCallBack cb : callbacks) {
                        cb.onActivityPaused(activity);
                    }
                    outAppTime = System.currentTimeMillis();
                    activityName = activity.getLocalClassName();
                }

                @Override
                public void onActivityStopped(@NonNull Activity activity) {
                    for (ActivityLifecycleCallBack cb : callbacks) {
                        cb.onActivityStopped(activity);
                    }
                }

                @Override
                public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                    for (ActivityLifecycleCallBack cb : callbacks) {
                        cb.onActivitySaveInstanceState(activity, outState);
                    }
                }

                @Override
                public void onActivityDestroyed(@NonNull Activity activity) {
                    for (ActivityLifecycleCallBack cb : callbacks) {
                        cb.onActivityDestroyed(activity);
                    }
                    stackInfo.getActivityStackList().remove(activity);
                    for (ActivityLifecycleCallBack cb : callbacks) {
                        cb.onStackInfo(stackInfo);
                    }
                }
            });
        }
    }

    public void unregisterActivityLifecycleCallBack(ActivityLifecycleCallBack callback) {
        if (callbacks != null) {
            callbacks.remove(callback);
        }
    }

    public StackInfo getStackInfo() {
        return stackInfo;
    }

    public void finishAllActivity(Activity current) {
        for (Activity a : stackInfo.getActivityStackList()) {
            if (a != null && !a.isFinishing() && a != current) {
                a.finish();
            }
        }
    }

    public void finishAllActivityExcept(Activity current, Class<?>... classes) {
        outer:
        for (Activity a : stackInfo.getActivityStackList()) {
            if (a == null || a.isFinishing() || a == current) continue;
            for (Class<?> cls : classes) {
                if (a.getClass() == cls) {
                    continue outer;
                }
            }
            a.finish();
        }
    }

    public static abstract class ActivityLifecycleCallBack {
        public void onActivityCreate(Activity activity, Bundle saveInstanceState) {}
        public void onActivityStart(Activity activity) {}
        public void onActivityResumed(Activity activity) {}
        public void onActivityPaused(Activity activity) {}
        public void onActivityStopped(Activity activity) {}
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
        public void onActivityDestroyed(Activity activity) {}
        public void outAppReBack(Activity activity, long outTime) {}
        public void onStackInfo(StackInfo stackInfo) {}
    }

    public static class StackInfo {
        private List<Activity> activityStackList = new ArrayList<>();

        public List<Activity> getActivityStackList() {
            return activityStackList;
        }

        public void setActivityStackList(List<Activity> activityStackList) {
            this.activityStackList = activityStackList;
        }

        public String stackListToString() {
            if (activityStackList == null) {
                return "list is null";
            }
            StringBuilder sb = new StringBuilder("============================================\n");
            for (Activity activity : activityStackList) {
                sb.append("\t ").append(activity.getLocalClassName()).append("\n");
            }
            sb.append("============================================");
            return sb.toString();
        }
    }
}
