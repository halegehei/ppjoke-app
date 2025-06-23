package ps.center.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

/* loaded from: classes.jar:ps/center/utils/UIUtils.class */
public class UIUtils {
    public static int stringToResourceId(Context context, String type, String sourceName) {
        try {
            return context.getResources().getIdentifier(sourceName, type, context.getPackageName());
        } catch (Exception e) {
            return 0;
        }
    }

    public static void changeViewBackDrawableColor(View view, String color) {
        if (color != null) {
            try {
                if (color.length() >= 7) {
                    GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
                    int backColor = Color.parseColor(color);
                    gradientDrawable.setColor(backColor);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static int getSystemComponentDimen(Context context, String dimenName) {
        int statusHeight;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField(dimenName).get(object).toString();
            int height = Integer.parseInt(heightStr);
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
            statusHeight = 0;
        }
        return statusHeight;
    }

    public static int getNavigationBarHeight(Context context) {
        return getSystemComponentDimen(context, "navigation_bar_height");
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        if (specMode == 1073741824) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == Integer.MIN_VALUE) {
                result = Math.min(200, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        if (specMode == 1073741824) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == Integer.MIN_VALUE) {
                result = Math.min(200, specSize);
            }
        }
        return result;
    }

    public static void removeViewFormParent(View v) {
        if (v == null) {
            return;
        }
        ViewParent parent = v.getParent();
        if (parent instanceof FrameLayout) {
            ((FrameLayout) parent).removeView(v);
        }
    }
}