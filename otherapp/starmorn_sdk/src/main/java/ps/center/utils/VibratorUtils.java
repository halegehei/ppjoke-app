package ps.center.utils;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

/* loaded from: classes.jar:ps/center/utils/VibratorUtils.class */
public class VibratorUtils {
    public static final int MODEL_RESTART = 0;
    public static final int MODEL_NULL = -1;

    /* loaded from: classes.jar:ps/center/utils/VibratorUtils$MODEL.class */
    public @interface MODEL {
    }

    public static void play(Context context, @MODEL int model, long... pattern) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator == null) return;
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, model), new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_UNKNOWN).build());
        } else {
            vibrator.vibrate(pattern, model);
        }
    }

    public static void play(Context context, long pattern) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern);
    }
}