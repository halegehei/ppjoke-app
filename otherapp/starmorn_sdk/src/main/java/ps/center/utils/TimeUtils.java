package ps.center.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/TimeUtils.class */
public class TimeUtils {
    public static long dataToTime(String data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse != null) {
            return parse.getTime();
        }
        return 0L;
    }

    public static String timeToData(String format, long time) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
            return simpleDateFormat.format(new Date(time));
        } catch (Exception e) {
            return BuildConfig.VERSION_NAME;
        }
    }

    public static long dataToTime(String format, String data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse != null) {
            return parse.getTime();
        }
        return 0L;
    }

    public static String timeToData(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return simpleDateFormat.format(new Date(time));
    }

    public static String timeToHHMMSS(long time) {
        long h = ((time / 1000) / 60) / 60;
        long m = ((time / 1000) / 60) % 60;
        long s = (time / 1000) % 60;
        return (h < 10 ? "0" + h : Long.valueOf(h)) + ":" + (m < 10 ? "0" + m : Long.valueOf(m)) + ":" + (s < 10 ? "0" + s : Long.valueOf(s));
    }

    public static String timeToMMSS(long time) {
        long s;
        long m = time / 60;
        if (time >= 60) {
            s = time % 60;
        } else {
            s = time;
        }
        String timeTemp = (m < 10 ? "0" + m : Long.valueOf(m)) + ":" + (s < 10 ? "0" + s : Long.valueOf(s));
        return timeTemp;
    }
}