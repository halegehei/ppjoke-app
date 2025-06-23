package ps.center.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import ps.center.application.BuildConfig;
import ps.center.R;

/* loaded from: classes.jar:ps/center/utils/ToastUtils.class */
public class ToastUtils {
    private static Toast toastForText;
    private static int time = 1;
    public static int toastTextColor = Color.parseColor("#FFFFFF");

    public static void show(String msg) {
        show(Super.getContext(), msg);
    }

    public static void show(Context context, String msg) {
        show(context, R.drawable.business_toast_bg, msg);
    }

    public static void show(Context context, int bgRes, String msg) {
        if (context == null) {
            return;
        }
        View layout = LayoutInflater.from(context).inflate(R.layout.business_leyout_business_sdk_toast_bg, (ViewGroup) null);
        RelativeLayout rootLayout = (RelativeLayout) layout.findViewById(R.id.rootLayout);
        rootLayout.setBackgroundResource(bgRes);
        TextView content = (TextView) layout.findViewById(R.id.content);
        content.setText(msg);
        if (toastForText != null) {
            toastForText.cancel();
            toastForText = null;
            if (msg.equals(BuildConfig.VERSION_NAME)) {
                return;
            }
        }
        toastForText = new Toast(context);
        toastForText.setGravity(17, 0, 0);
        toastForText.setDuration(time);
        toastForText.setView(layout);
        toastForText.show();
    }

    public static void show2(Context context, String msg) {
        if (context == null) {
            return;
        }
        TextView textView = new TextView(context);
        textView.setText(msg);
        textView.setTextColor(toastTextColor);
        textView.setTextSize(14.0f);
        textView.setGravity(17);
        textView.setTextColor(Color.parseColor("#000000"));
        RoundFrameLayout rootLayout = new RoundFrameLayout(context);
        rootLayout.addView(textView);
        textView.getLayoutParams().width = -2;
        rootLayout.setPadding(100, 25, 100, 25);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        rootLayout.setLayoutParams(layoutParams);
        rootLayout.setRound(25);
        if (toastForText != null) {
            toastForText.cancel();
            toastForText = null;
            if (msg.equals(BuildConfig.VERSION_NAME)) {
                return;
            }
        }
        toastForText = new Toast(context);
        toastForText.setGravity(17, 0, 0);
        toastForText.setDuration(time);
        toastForText.setView(rootLayout);
        toastForText.show();
    }

    /* loaded from: classes.jar:ps/center/utils/ToastUtils$RoundFrameLayout.class */
    private static class RoundFrameLayout extends FrameLayout {
        private int round;

        public RoundFrameLayout(Context context) {
            super(context);
            this.round = 15;
        }

        public RoundFrameLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.round = 15;
        }

        public RoundFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.round = 15;
        }

        public void setRound(int round) {
            this.round = round;
            invalidate();
        }

        @Override // android.view.ViewGroup
        public void addView(View child) {
            super.addView(child);
            invalidate();
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            setLayerType(1, null);
            super.draw(canvas);
            if (getWidth() == 0 || getHeight() == 0) {
                return;
            }
            Bitmap viewBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas viewCanvas = new Canvas(viewBitmap);
            Paint viewPaint = new Paint(1);
            viewPaint.setColor(-1);
            viewCanvas.drawRoundRect(0.0f, 0.0f, getWidth(), getHeight(), this.round, this.round, viewPaint);
            viewPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawBitmap(viewBitmap, 0.0f, 0.0f, viewPaint);
        }
    }

    public static void setToastTime(int toastTime) {
        time = toastTime;
    }
}