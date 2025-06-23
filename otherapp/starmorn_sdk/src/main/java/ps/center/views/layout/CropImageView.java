package ps.center.views.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import ps.center.utils.LogUtils;

/* loaded from: classes.jar:ps/center/views/layout/CropImageView.class */
public class CropImageView extends AppCompatImageView {
    public static final int MODEL1_1 = 0;
    public static final int MODEL3_4 = 1;
    public static final int MODEL4_3 = 2;
    public static final int MODEL9_16 = 3;
    public static final int MODEL16_9 = 4;
    public static final int MODEL_NULL = 5;
    private int cropMode;
    private RectF cropRect;
    private Paint cropPaint;
    private RectF boxRect;
    private Paint boxPaint;
    private RectF imageRect;
    private Paint imagePaint;
    private boolean isTouch;
    float downX;
    float downY;
    RectF downRect;

    public CropImageView(@NonNull Context context) {
        super(context);
        this.cropMode = 5;
        this.isTouch = false;
        setPaint();
    }

    public CropImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.cropMode = 5;
        this.isTouch = false;
        setPaint();
    }

    public CropImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.cropMode = 5;
        this.isTouch = false;
        setPaint();
    }

    private void setPaint() {
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        if (this.cropPaint == null) {
            this.cropPaint = new Paint();
        }
        this.cropPaint.setAntiAlias(true);
        this.cropPaint.setColor(0);
        this.cropPaint.setStrokeWidth(20.0f);
        this.cropPaint.setStyle(Paint.Style.FILL);
        this.cropPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        if (this.boxPaint == null) {
            this.boxPaint = new Paint();
        }
        this.boxPaint.setAntiAlias(true);
        this.boxPaint.setColor(Color.parseColor("#46BA3A"));
        this.boxPaint.setStrokeWidth(5.0f);
        this.boxPaint.setStyle(Paint.Style.STROKE);
        if (this.imagePaint == null) {
            this.imagePaint = new Paint();
        }
        this.imagePaint.setAntiAlias(true);
        this.imagePaint.setColor(Color.parseColor("#CC000000"));
        this.imagePaint.setStyle(Paint.Style.FILL);
    }

    public void setCropMode( int cropMode) {
        this.cropMode = cropMode;
        float height = getHeight();
        float width = getWidth();
        this.cropRect.left = 0.0f;
        this.cropRect.top = 0.0f;
        if (width > height) {
            switch (this.cropMode) {
                case 0:
                    this.cropRect.right = height;
                    this.cropRect.bottom = height;
                    break;
                case 1:
                    this.cropRect.right = height * 0.75f;
                    this.cropRect.bottom = height;
                    break;
                case 2:
                    this.cropRect.right = height * 1.3333334f;
                    this.cropRect.bottom = height;
                    break;
                case 3:
                    this.cropRect.right = height * 0.5625f;
                    this.cropRect.bottom = height;
                    break;
                case 4:
                    this.cropRect.right = height * 1.7777778f;
                    this.cropRect.bottom = height;
                    break;
                case 5:
                    this.cropRect.right = width;
                    this.cropRect.bottom = height;
                    break;
            }
        } else {
            switch (this.cropMode) {
                case 0:
                    this.cropRect.right = width;
                    this.cropRect.bottom = width;
                    break;
                case 1:
                    this.cropRect.right = width * 0.75f;
                    this.cropRect.bottom = width;
                    break;
                case 2:
                    this.cropRect.right = width;
                    this.cropRect.bottom = width * 0.75f;
                    break;
                case 3:
                    this.cropRect.right = width;
                    this.cropRect.bottom = width * 1.7777778f;
                    break;
                case 4:
                    this.cropRect.right = width;
                    this.cropRect.bottom = width * 0.5625f;
                    break;
                case 5:
                    this.cropRect.right = width;
                    this.cropRect.bottom = height;
                    break;
            }
        }
        yan();
        postInvalidate();
    }

    private void yan() {
        float cropScale;
        if (!this.imageRect.contains(this.cropRect)) {
            if (this.cropRect.right > this.cropRect.bottom) {
                cropScale = this.cropRect.bottom / this.cropRect.right;
            } else {
                cropScale = this.cropRect.right / this.cropRect.bottom;
            }
            LogUtils.e(Float.valueOf(cropScale));
            if (this.cropRect.right > this.imageRect.right) {
                this.cropRect.bottom -= (this.cropRect.right - this.imageRect.right) * cropScale;
                this.cropRect.right -= this.cropRect.right - this.imageRect.right;
                return;
            }
            this.cropRect.right -= (this.cropRect.bottom - this.imageRect.bottom) * cropScale;
            this.cropRect.bottom -= this.cropRect.bottom - this.imageRect.bottom;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initImageRect();
        initCorpRect();
        canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null);
        canvas.drawRect(this.imageRect, this.imagePaint);
        this.boxRect = new RectF(this.cropRect);
        canvas.drawRect(this.cropRect, this.cropPaint);
        canvas.drawRect(this.boxRect, this.boxPaint);
    }

    public Bitmap getCropBitmap() {
        setDrawingCacheEnabled(true);
        Bitmap drawingCache = getDrawingCache();
        int bitmapWidth = (int) (this.cropRect.right - this.cropRect.left);
        int bitmapHeight = (int) (this.cropRect.bottom - this.cropRect.top);
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(drawingCache, new Rect((int) this.cropRect.left, (int) this.cropRect.top, (int) this.cropRect.right, (int) this.cropRect.bottom), new Rect(0, 0, bitmapWidth, bitmapHeight), (Paint) null);
        setDrawingCacheEnabled(false);
        return bitmap;
    }

    private void initCorpRect() {
        if (this.cropRect == null) {
            this.cropRect = new RectF();
            if (this.cropMode == 5) {
                setCropMode(5);
            } else {
                setCropMode(this.cropMode);
            }
        }
    }

    private void initImageRect() {
        if (this.imageRect == null) {
            this.imageRect = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (this.cropMode == 5) {
            return true;
        }
        switch (event.getAction()) {
            case 0:
                this.isTouch = true;
                this.downX = x;
                this.downY = y;
                this.downRect = new RectF(this.cropRect);
                break;
            case 1:
                this.isTouch = false;
                this.downRect = null;
                break;
            case 2:
                if (this.isTouch) {
                    this.cropRect.offsetTo(this.downRect.left + (x - this.downX), this.downRect.top + (y - this.downY));
                    break;
                }
                break;
        }
        postInvalidate();
        return true;
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.UNKNOWN ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }
}