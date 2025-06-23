package ps.center.library.http;

import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* loaded from: classes.jar:ps/center/library/http/OkDown.class */
public class OkDown {
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    private boolean isCancel;
    private static final byte[] gSyncCode = new byte[0];

    /* loaded from: classes.jar:ps/center/library/http/OkDown$DownloadListener.class */
    public interface DownloadListener {
        void onDownloadSuccess(String str);

        void onDownloading(int i);

        void onDownloadFailed(Exception exc);
    }

    public void download(String url, final String saveDir, final String fileName, final DownloadListener downloadListener) {
        Request request = new Request.Builder().url(url).build();
        this.okHttpClient.newCall(request).enqueue(new Callback() { // from class: ps.center.library.http.OkDown.1
            public void onFailure(Call call, IOException e) {
                if (!OkDown.this.isCancel) {
                    downloadListener.onDownloadFailed(e);
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:43:0x0156 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:62:0x0135 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onResponse(Call r6, okhttp3.Response r7) throws IOException {
                /*
                    Method dump skipped, instructions count: 356
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: ps.center.library.http.OkDown.AnonymousClass1.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String isExistDir(String saveDir) throws IOException {
        File downloadFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    public static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public static boolean deleteFile(String path) {
        synchronized (gSyncCode) {
            if (TextUtils.isEmpty(path)) {
                return true;
            }
            File file = new File(path);
            if (!file.exists()) {
                return true;
            }
            if (file.isFile()) {
                return file.delete();
            }
            if (!file.isDirectory()) {
                return false;
            }
            File[] filesList = file.listFiles();
            if (filesList != null) {
                for (File f : filesList) {
                    if (f.isFile()) {
                        f.delete();
                    } else if (f.isDirectory()) {
                        deleteFile(f.getAbsolutePath());
                    }
                }
            }
            return file.delete();
        }
    }

    public static boolean fileRename(String fromName, String toName) {
        synchronized (gSyncCode) {
            File fromFile = new File(fromName);
            File toFile = new File(toName);
            if (!fromFile.exists()) {
                return false;
            }
            boolean result = fromFile.renameTo(toFile);
            if (result) {
            }
            return result;
        }
    }

    public void cancel() {
        this.isCancel = true;
    }
}