package ps.center.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;
import java.io.File;

/* loaded from: classes.jar:ps/center/utils/LocalShareUtils.class */
public class LocalShareUtils {
    public static boolean shareImage(Context context, String imageFilePath) {
        try {
            Uri imageUir = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", new File(imageFilePath));
            Intent shareIntent = new Intent();
            shareIntent.setAction("android.intent.action.SEND");
            shareIntent.putExtra("android.intent.extra.STREAM", imageUir);
            shareIntent.setType("image/*");
            context.startActivity(Intent.createChooser(shareIntent, "图片分享"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean shareFile(Context context, String filePath) {
        try {
            Uri imageUir = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", new File(filePath));
            Intent shareIntent = new Intent();
            shareIntent.setAction("android.intent.action.SEND");
            shareIntent.putExtra("android.intent.extra.STREAM", imageUir);
            shareIntent.setType("*/*");
            context.startActivity(Intent.createChooser(shareIntent, "文件分享"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean shareText(Context context, String text) {
        try {
            Intent textIntent = new Intent("android.intent.action.SEND");
            textIntent.setType("text/plain");
            textIntent.putExtra("android.intent.extra.TEXT", text);
            context.startActivity(Intent.createChooser(textIntent, "文本分享"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}