package ps.center.utils;

/* loaded from: classes.jar:ps/center/utils/StringUtils.class */
public class StringUtils {
    public static boolean isChinese(String str, int minCount, int maxCount) {
        if (str != null) {
            return str.matches("^[\\u4e00-\\u9fa5]{" + minCount + "," + maxCount + "}");
        }
        return false;
    }

    public static boolean isNumbers(String numStr, int minCount, int maxCount) {
        if (numStr != null) {
            return numStr.matches("[0-9]{" + minCount + "," + maxCount + "}");
        }
        return false;
    }

    public static boolean isLetterAndNumber(String numStr, int minCount, int maxCount) {
        if (numStr != null) {
            return numStr.matches("[a-zA-Z0-9]{" + minCount + "," + maxCount + "}");
        }
        return false;
    }

    public static boolean isLetter(String numStr, int minCount, int maxCount) {
        if (numStr != null) {
            return numStr.matches("[a-zA-Z]{" + minCount + "," + maxCount + "}");
        }
        return false;
    }
}