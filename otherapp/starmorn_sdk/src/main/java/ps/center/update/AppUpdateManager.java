package ps.center.update;

import android.content.Context;
import android.widget.Toast;
import java.util.List;

/* loaded from: classes.jar:ps/center/update/AppUpdateManager.class */
public class AppUpdateManager {

    /* loaded from: classes.jar:ps/center/update/AppUpdateManager$Model.class */
    public enum Model {
        Download,
        Update
    }

    public static void show(Context context, boolean isMust, String url, String title, List<String> content) {
        if (context == null) {
            return;
        }
        if (url == null || title == null || content == null) {
            Toast.makeText(context, "更新接口参数错误", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder contentStr = new StringBuilder();
        for (String item : content) {
            contentStr.append(item);
            contentStr.append("\n");
        }
        show(context, isMust, url, title, contentStr.toString());
    }

    public static void showDownload(Context context, boolean isMust, String url, String title, List<String> content) {
        if (context == null) {
            return;
        }
        if (url == null || title == null || content == null) {
            Toast.makeText(context, "参数不足", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder contentStr = new StringBuilder();
        for (String item : content) {
            contentStr.append(item);
            contentStr.append("\n");
        }
        showDownload(context, isMust, url, title, contentStr.toString());
    }

    public static void show(Context context, boolean isMust, String url, String title, String content) {
        if (context == null) {
            return;
        }
        if (url == null || title == null || content == null) {
            Toast.makeText(context, "更新接口参数错误", Toast.LENGTH_SHORT).show();
        } else {
            VersionUpDataDialog versionUpDataDialog = new VersionUpDataDialog(context, isMust, url, title, content.replace(",", "\n"));
            versionUpDataDialog.show();
        }
    }

    public static void showDownload(Context context, boolean isMust, String url, String title, String content) {
        if (context == null) {
            return;
        }
        if (url == null || title == null || content == null) {
            Toast.makeText(context, "参数不足", Toast.LENGTH_SHORT).show();
        } else {
            DownloadApkDialog versionUpDataDialog = new DownloadApkDialog(context, isMust, url, title, content.replace(",", "\n"));
            versionUpDataDialog.show();
        }
    }
}