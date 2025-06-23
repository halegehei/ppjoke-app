package ps.center.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/Log.class */
public final class Log {
    public static final Log input = new Log();

    private Log() {
        LogUtils.e("日志地址：" + getRootPath() + "/log/");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [ps.center.utils.Log$1] */
    public void write(final String log) {
        new Thread() { // from class: ps.center.utils.Log.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Log.writeLog(log);
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [ps.center.utils.Log$2] */
    public void writeHttp(final String log) {
        new Thread() { // from class: ps.center.utils.Log.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Log.writeHttpLog(log);
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void writeLog(String logStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH点");
            String format = sdf.format(new Date(System.currentTimeMillis()));
            File mkdir = new File(getRootPath() + "/log");
            if (!mkdir.exists()) {
                boolean mkdirs = mkdir.mkdirs();
                if (!mkdirs) {
                    LogUtils.e("日志文件夹创建失败！");
                }
            }
            File file = new File(getRootPath() + "/log/", "log-" + format + ".txt");
            SimpleDateFormat sdf1 = new SimpleDateFormat("mm分ss秒 -> ");
            String format1 = sdf1.format(new Date(System.currentTimeMillis()));
            if (file.exists()) {
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write((format1 + logStr).getBytes());
                fos.write("\r\n".getBytes());
                fos.write("\r\n".getBytes());
                fos.write("\r\n".getBytes());
                fos.flush();
                fos.close();
            } else {
                FileOutputStream fos2 = new FileOutputStream(file);
                fos2.write((format1 + logStr).getBytes());
                fos2.write("\r\n".getBytes());
                fos2.write("\r\n".getBytes());
                fos2.write("\r\n".getBytes());
                fos2.flush();
                fos2.close();
            }
        } catch (Exception e) {
            LogUtils.e("写日志失败：" + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void writeHttpLog(String logStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH点");
            String format = sdf.format(new Date(System.currentTimeMillis()));
            File mkdir = new File(getRootPath() + "/log");
            if (!mkdir.exists()) {
                boolean mkdirs = mkdir.mkdirs();
                if (!mkdirs) {
                    LogUtils.e("日志文件夹创建失败！");
                }
            }
            File file = new File(getRootPath() + "/log/", "logHttp-" + format + ".txt");
            SimpleDateFormat sdf1 = new SimpleDateFormat("mm分ss秒 -> ");
            String format1 = sdf1.format(new Date(System.currentTimeMillis()));
            if (file.exists()) {
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write((format1 + logStr).getBytes());
                fos.write("\r\n".getBytes());
                fos.write("\r\n".getBytes());
                fos.write("\r\n".getBytes());
                fos.flush();
                fos.close();
            } else {
                FileOutputStream fos2 = new FileOutputStream(file);
                fos2.write((format1 + logStr).getBytes());
                fos2.write("\r\n".getBytes());
                fos2.write("\r\n".getBytes());
                fos2.write("\r\n".getBytes());
                fos2.flush();
                fos2.close();
            }
        } catch (Exception e) {
            LogUtils.e("写日志失败：" + e.toString());
        }
    }

    private static String getRootPath() {
        return Super.getContext().getFilesDir() + "/";
    }

    public static List<String> getLogFilePath() {
        File[] files;
        List<String> list = new ArrayList<>();
        File file = new File(getRootPath() + "/log/");
        if (file.exists() && (files = file.listFiles()) != null) {
            for (File file1 : files) {
                list.add(file1.getAbsolutePath());
            }
        }
        return list;
    }

    public static String getLogContent(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                byte[] bytes = new byte[bis.available()];
                bis.read(bytes);
                bis.close();
                fis.close();
                return new String(bytes, StandardCharsets.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
                return BuildConfig.VERSION_NAME;
            }
        }
        return BuildConfig.VERSION_NAME;
    }
}