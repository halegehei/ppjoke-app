package ps.center.utils;

import android.util.Base64;

/* loaded from: classes.jar:ps/center/utils/Base64Utils.class */
public class Base64Utils {
    public static String base64ToString(String deCode) {
        return new String(Base64.decode(deCode, 0));
    }

    public static String stringToBase64(String enCode) {
        return new String(Base64.encode(enCode.getBytes(), 0));
    }
}