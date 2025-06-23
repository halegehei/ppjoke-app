package ps.center.views.DataChanger;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataChangeManager {
    private static final String TAG = "DateChangeManager";
    private static volatile DataChangeManager dataChangeManager;
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static CopyOnWriteArrayList<DataChangeCallBack> changCallBacks;

    private DataChangeManager() {
    }

    public static DataChangeManager get() {
        if (dataChangeManager == null) {
            synchronized (DataChangeManager.class) {
                if (dataChangeManager == null) {
                    dataChangeManager = new DataChangeManager();
                } else {
                    return dataChangeManager;
                }
            }
        }
        return dataChangeManager;
    }

    public void registerChangCallBack(DataChangeCallBack changCallBack) {
        if (changCallBacks == null) {
            changCallBacks = new CopyOnWriteArrayList<>();
        }
        Iterator<DataChangeCallBack> it = changCallBacks.iterator();
        while (it.hasNext()) {
            DataChangeCallBack dataChangeCallBacks = it.next();
            if (dataChangeCallBacks.equals(changCallBack)) {
                changCallBacks.remove(dataChangeCallBacks);
                Log.e(TAG, "存在相同的被观察者，已将前者移除");
            }
        }
        try {
            changCallBacks.add(changCallBack);
            Log.e(TAG, "已注册被观察者，当前被观察者数量：" + changCallBacks.size() + "\t[" + changCallBack.getClass().getSimpleName() + "]");
        } catch (Exception e) {
            Log.e(TAG, "被观察者添加失败：" + changCallBack.getClass().getName());
        }
    }

    public void unregisterChangCallBack(DataChangeCallBack changCallBack) {
        if (changCallBacks == null || changCallBacks.size() < 1) {
            return;
        }
        try {
            changCallBacks.remove(changCallBack);
            Log.e(TAG, "已删除被观察者，当前被观察者数量：" + changCallBacks.size() + "\t[" + changCallBack.getClass().getSimpleName() + "]");
        } catch (Exception e) {
            Log.e(TAG, "被观察者删除失败：" + changCallBack.getClass().getName());
        }
        if (changCallBacks != null && changCallBacks.size() == 0) {
            changCallBacks = null;
            dataChangeManager = null;
        }
    }

    public void change(int state, Object object) {
        if (changCallBacks == null || changCallBacks.size() <= 0) {
            return;
        }
        Iterator<DataChangeCallBack> it = changCallBacks.iterator();
        while (it.hasNext()) {
            DataChangeCallBack userChangCallBack = it.next();
            handler.post(() -> {
                if (userChangCallBack != null && object != null) {
                    userChangCallBack.change(state, object);
                }
            });
        }
    }

    public void change(int state, Object object, boolean isCheckoutMainThread) {
        if (changCallBacks == null || changCallBacks.size() <= 0) {
            return;
        }
        Iterator<DataChangeCallBack> it = changCallBacks.iterator();
        while (it.hasNext()) {
            DataChangeCallBack userChangCallBack = it.next();
            if (isCheckoutMainThread) {
                handler.post(() -> {
                    if (userChangCallBack != null && object != null) {
                        userChangCallBack.change(state, object);
                    }
                });
            } else if (userChangCallBack != null && object != null) {
                userChangCallBack.change(state, object);
            }
        }
    }
}