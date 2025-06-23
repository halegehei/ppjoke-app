package ps.center.utils;

import android.text.TextUtils;
import java.security.MessageDigest;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/MD5Utils.class */
public class MD5Utils {
    public static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return BuildConfig.VERSION_NAME;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 255);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (Exception e) {
            android.util.Log.e("MD5", "字符串md5失败！");
            return BuildConfig.VERSION_NAME;
        }
    }
}