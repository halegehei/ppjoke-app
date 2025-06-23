package ps.center.views.layout;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ps.center.R;

/* loaded from: classes.jar:ps/center/views/layout/ContrastImageView.class */
public class ContrastImageView extends View {
    private Bitmap leftDrawable;
    private Bitmap rightDrawable;
    private Bitmap centerXDraw;
    private Paint linePaint;
    private Paint rectPaint;
    private boolean isTouch;
    private boolean isDrawCenterLine;
    private boolean isDrawCenterDraw;
    private int padding;
    private int lineColor;
    private int centerX;

    public ContrastImageView(@NonNull Context context) {
        super(context);
        this.isTouch = false;
        this.isDrawCenterLine = true;
        this.isDrawCenterDraw = true;
        this.padding = 30;
        this.lineColor = 0;
        this.centerX = -1;
    }

    public ContrastImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.isTouch = false;
        this.isDrawCenterLine = true;
        this.isDrawCenterDraw = true;
        this.padding = 30;
        this.lineColor = 0;
        this.centerX = -1;
        setValues(context, attrs);
    }

    public ContrastImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.isTouch = false;
        this.isDrawCenterLine = true;
        this.isDrawCenterDraw = true;
        this.padding = 30;
        this.lineColor = 0;
        this.centerX = -1;
        post(() -> {
            setValues(context, attrs);
        });
    }

    private void setValues(Context context, AttributeSet attrs) {
        setLayerType(2, null);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ContrastImageView);
        int leftDrawableId = typedArray.getResourceId(R.styleable.ContrastImageView_leftDrawable, 0);
        int rightDrawableId = typedArray.getResourceId(R.styleable.ContrastImageView_rightDrawable, 0);
        int centerXDrawableId = typedArray.getResourceId(R.styleable.ContrastImageView_centerXDrawable, 0);
        if (leftDrawableId != 0) {
            this.leftDrawable = BitmapFactory.decodeResource(getContext().getResources(), leftDrawableId);
        }
        if (rightDrawableId != 0) {
            this.rightDrawable = BitmapFactory.decodeResource(getContext().getResources(), rightDrawableId);
        }
        if (centerXDrawableId != 0) {
            this.centerXDraw = BitmapFactory.decodeResource(getContext().getResources(), centerXDrawableId);
        }
        this.linePaint = new Paint();
        this.linePaint.setColor(this.lineColor == 0 ? Color.parseColor("#80FFFFFF") : this.lineColor);
        this.linePaint.setAntiAlias(true);
        this.linePaint.setStrokeWidth(8.0f);
        this.linePaint.setStyle(Paint.Style.STROKE);
        this.rectPaint = new Paint();
        this.rectPaint.setColor(0);
        this.rectPaint.setAntiAlias(true);
        this.rectPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        postDelayed(this::startAnimation, 300L);
    }

    private void startAnimation() {
        if (this.isTouch) {
            return;
        }
        ValueAnimator valueAnimator = ValueAnimator.ofInt(this.centerX, this.centerX + 100, this.centerX - 100, this.centerX);
        valueAnimator.setStartDelay(1000L);
        valueAnimator.setDuration(2000L);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(valueAnimator1 -> {
            if (this.isTouch) {
                valueAnimator.cancel();
            } else {
                this.centerX = ((Integer) valueAnimator1.getAnimatedValue()).intValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    public void setCenterX(int x) {
        this.centerX = x;
        postInvalidate();
    }

    public void setPadding(int padding) {
        this.padding = padding;
        postInvalidate();
    }

    public void isDrawCenterLine(boolean isDrawCenterLine) {
        this.isDrawCenterLine = isDrawCenterLine;
        postInvalidate();
    }

    public void isDrawCenterDraw(boolean isDrawCenterDraw) {
        this.isDrawCenterDraw = isDrawCenterDraw;
        postInvalidate();
    }

    public void centerLineColor(int color) {
        this.lineColor = color;
        postInvalidate();
    }

    public void setDrawBitmap(int leftDrawableId, int rightDrawableId) {
        this.leftDrawable = BitmapFactory.decodeResource(getContext().getResources(), leftDrawableId);
        this.rightDrawable = BitmapFactory.decodeResource(getContext().getResources(), rightDrawableId);
        postInvalidate();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.rightDrawable != null && this.leftDrawable != null) {
            Rect rect2 = new Rect(0, 0, this.centerX, getHeight());
            canvas.drawBitmap(this.leftDrawable, (Rect) null, new Rect(this.padding, this.padding, getWidth() - this.padding, getHeight() - this.padding), (Paint) null);
            canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null);
            canvas.drawBitmap(this.rightDrawable, (Rect) null, new Rect(this.padding, this.padding, getWidth() - this.padding, getHeight() - this.padding), (Paint) null);
            canvas.drawRect(rect2, this.rectPaint);
            if (this.isDrawCenterLine) {
                canvas.drawLine(rect2.right, rect2.top, rect2.right, rect2.bottom, this.linePaint);
            }
            if (this.isDrawCenterDraw && this.centerXDraw != null) {
                canvas.drawBitmap(this.centerXDraw, this.centerX - (this.centerXDraw.getWidth() / 2), ((rect2.bottom - this.centerXDraw.getHeight()) - this.padding) - 10, (Paint) null);
            }
        }
    }

    @Override // android.view.View
    @SuppressLint({"DefaultLocale"})
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 2:
                this.isTouch = true;
                this.centerX = (int) event.getX();
                if (this.centerX < 20 + this.padding) {
                    this.centerX = 20 + this.padding;
                }
                if (this.centerX > (getWidth() - this.padding) - 20) {
                    this.centerX = (getWidth() - this.padding) - 20;
                }
                postInvalidate();
                break;
        }
        return true;
    }
}