package ps.center.application.config;

import android.content.Context;
import android.os.Parcelable;
import com.google.gson.Gson;
//import com.umeng.vt.diff.C;
//import com.umeng.vt.diff.D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import ps.center.application.BuildConfig;
import ps.center.utils.Save;
import ps.center.utils.Super;

/* loaded from: classes.jar:ps/center/application/config/Settings.class */
public class Settings {
    public static boolean init(Context context, String className, String packageName) {
        try {
            // —— 注释掉所有和 C、D 相关的代码 ——
//            String json = C.a(
//                context,
//                packageName,
//                BuildConfig.VERSION_NAME + System.currentTimeMillis(),
//                className,
//                getMeta(context)
//            );
//            String newJson = b(context, json, className);
//            return Save.instance.put(
//                "klitemob",
//                (Parcelable) new Gson().fromJson(newJson, D.class)
//            );

            // 简单 fallback：直接返回 true（或根据你需求改成其它逻辑）
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getMeta(Context context) {
        InputStream is = null;
        String result = BuildConfig.VERSION_NAME;
        StringBuilder sb = new StringBuilder();
        try {
            try {
                is = context
                        .getClass()
                        .getResourceAsStream("/META-INF/litemob/channel");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();
            } catch (Exception e2) {
                e2.printStackTrace();
            } finally {
                if (is != null) try { is.close(); } catch (Exception ignore) {}
            }
            return result;
        } catch (Throwable th) {
            if (is != null) try { is.close(); } catch (Exception ignore) {}
            throw th;
        }
    }

    public static String b(Context context, String a, String b) {
        // 注释掉 C.c 的调用，直接返回传入的 a
//        return C.c(
//            context,
//            Super.getContext().getPackageName(),
//            BuildConfig.VERSION_NAME + System.currentTimeMillis(),
//            b,
//            a
//        );
        return a;
    }
}
