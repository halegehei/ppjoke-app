package ps.center.utils.xbanner;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.DrawableRes;
import androidx.core.view.ViewCompat;
import java.util.List;

/* loaded from: classes.jar:ps/center/utils/xbanner/XBannerUtils.class */
public class XBannerUtils {
    public static StateListDrawable getSelector(Drawable normalDraw, Drawable pressedDraw) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_enabled}, pressedDraw);
        stateListDrawable.addState(new int[0], normalDraw);
        return stateListDrawable;
    }

    public static Drawable getDrawable(Context context, @DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(resId);
        }
        return context.getResources().getDrawable(resId);
    }

    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    public static int getScreenWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static void resetPageTransformer(List<? extends View> views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
            ViewCompat.setAlpha(view, 1.0f);
            ViewCompat.setPivotX(view, view.getMeasuredWidth() * 0.5f);
            ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
            ViewCompat.setTranslationX(view, 0.0f);
            ViewCompat.setTranslationY(view, 0.0f);
            ViewCompat.setScaleX(view, 1.0f);
            ViewCompat.setScaleY(view, 1.0f);
            ViewCompat.setRotationX(view, 0.0f);
            ViewCompat.setRotationY(view, 0.0f);
            ViewCompat.setRotation(view, 0.0f);
        }
    }

    public static int getRealPosition(int position, int dataSize) {
        return position % dataSize;
    }
}