package ps.center.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/FileUtils.class */
public class FileUtils {
    public static boolean saveToDownloadDir(String dirName, File file, boolean remove) {
        if (null == file) {
            return false;
        }
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        if (null != dirName) {
            if (dirName.endsWith("/")) {
                dirName = dirName.replace("/", BuildConfig.VERSION_NAME);
            }
            path = path + "/" + dirName;
        }
        boolean mkdir = mkdir(path);
        if (!mkdir) {
            return false;
        }
        File newFile = new File(path + "/" + file.getName());
        try {
            FileInputStream inputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                try {
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    fileOutputStream.write(bytes);
                    fileOutputStream.close();
                    inputStream.close();
                    if (remove) {
                        return file.delete();
                    }
                    return true;
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } finally {
            }
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean saveToDownloadDir(File file, boolean remove) {
        return saveToDownloadDir(null, file, remove);
    }

    public static List<File> getFileAll(String dirPath) {
        if (dirPath == null) {
            return new ArrayList();
        }
        File file = new File(dirPath);
        File[] files = file.listFiles();
        if (files != null) {
            return Arrays.asList(files);
        }
        return new ArrayList();
    }

    public static List<File> getFiles(String dirPath) {
        if (dirPath == null) {
            return new ArrayList();
        }
        File file = new File(dirPath);
        File[] files = file.listFiles();
        if (files != null) {
            List<File> list = new ArrayList<>();
            for (File item : files) {
                boolean isFile = item.isFile();
                if (isFile) {
                    list.add(item);
                }
            }
            return list;
        }
        return new ArrayList();
    }

    public static List<File> getDirs(String path) {
        if (path == null) {
            return new ArrayList();
        }
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            List<File> list = new ArrayList<>();
            for (File item : files) {
                boolean isFile = item.isDirectory();
                if (isFile) {
                    list.add(item);
                }
            }
            return list;
        }
        return new ArrayList();
    }

    public static boolean removeFile(File file) {
        return file.delete();
    }

    public static void removeDirAllFile(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        removeDirAllFile(file1);
                    } else if (file1.isFile()) {
                        file1.delete();
                    }
                }
            }
            file.delete();
        }
    }

    public static boolean mkdir(String path) {
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            return dirFile.mkdirs();
        }
        return true;
    }

    public static boolean createDirs(String dirPath) {
        if (!dirPath.endsWith("/")) {
            throw new RuntimeException("路径必须是 / 结尾，才能证明是文件夹哈");
        }
        File file = new File(dirPath);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public static void updatePhoto(Context context, File file) {
        ContentValues values = new ContentValues();
        values.put("_data", file.getAbsolutePath());
        values.put("mime_type", "image/jpeg");
        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
    }

    public static void updateVideo(Context context, File file) {
        String[] paths = {file.getAbsolutePath()};
        String[] mimeTypes = {"video/mp4"};
        MediaScannerConnection.scanFile(context, paths, mimeTypes, (path, uri) -> {
        });
    }

    public static void updateFile(Context context, File file) {
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + file.getAbsolutePath())));
    }
}