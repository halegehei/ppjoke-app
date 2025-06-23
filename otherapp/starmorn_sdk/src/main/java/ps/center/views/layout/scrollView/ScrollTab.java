package ps.center.views.layout.scrollView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import ps.center.R;

/* loaded from: classes.jar:ps/center/views/layout/scrollView/ScrollTab.class */
public class ScrollTab extends View {
    private int selectTextColor;
    private int defaultTextColor;
    private List<? extends BaseTitle> data;
    private List<Bean> beans;
    private float textSize;
    private float selectTextMaxSize;
    private Paint textPaint;
    private Paint rectPaint;
    private int move;
    private float needP;
    private int selectPosition;
    private Callback callback;
    private float itemHorizontalPadding;
    public boolean isDrawRect;
    private boolean isResetPosition;
    private int defaultMoveItem;
    private int viewWidth;
    private int viewHeight;
    private RectF vRect;
    private RectF down;
    private boolean enableTouch;
    private boolean isUp;

    /* loaded from: classes.jar:ps/center/views/layout/scrollView/ScrollTab$BaseTitle.class */
    public interface BaseTitle {
        String getTitle();
    }

    /* loaded from: classes.jar:ps/center/views/layout/scrollView/ScrollTab$Callback.class */
    public interface Callback<T> {
        void selectPosition(int i, T t);
    }

    public ScrollTab(Context context) {
        super(context);
        this.textSize = 0.0f;
        this.selectTextMaxSize = 0.0f;
        this.move = -1;
        this.needP = 0.0f;
        this.selectPosition = -1;
        this.itemHorizontalPadding = 0.0f;
        this.isDrawRect = false;
        this.isResetPosition = false;
        this.down = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.enableTouch = true;
        this.isUp = true;
    }

    public ScrollTab(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.textSize = 0.0f;
        this.selectTextMaxSize = 0.0f;
        this.move = -1;
        this.needP = 0.0f;
        this.selectPosition = -1;
        this.itemHorizontalPadding = 0.0f;
        this.isDrawRect = false;
        this.isResetPosition = false;
        this.down = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.enableTouch = true;
        this.isUp = true;
        resetPaint(context, attrs);
    }

    public ScrollTab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.textSize = 0.0f;
        this.selectTextMaxSize = 0.0f;
        this.move = -1;
        this.needP = 0.0f;
        this.selectPosition = -1;
        this.itemHorizontalPadding = 0.0f;
        this.isDrawRect = false;
        this.isResetPosition = false;
        this.down = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.enableTouch = true;
        this.isUp = true;
        resetPaint(context, attrs);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.viewWidth = measureWidth(widthMeasureSpec);
        this.viewHeight = measureHeight(heightMeasureSpec);
        if (this.vRect == null) {
            this.vRect = new RectF(0.0f, 0.0f, this.viewWidth, this.viewHeight);
        }
        if (getVisibility() == View.VISIBLE) {
            this.vRect.right = this.viewWidth;
            this.vRect.bottom = this.viewHeight;
        }
        if (this.beans != null) {
            for (Bean item : this.beans) {
                item.rect.bottom = this.viewHeight;
            }
        }
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
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
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
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

    private void resetPaint(Context context, AttributeSet attrs) {
        if (this.textPaint == null) {
            this.textPaint = new Paint();
            this.rectPaint = new Paint();
        }
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScrollTab);
        this.selectTextColor = typedArray.getColor(R.styleable.ScrollTab_selectColor, 0);
        this.defaultTextColor = typedArray.getColor(R.styleable.ScrollTab_defaultColor, 0);
        this.textSize = typedArray.getDimension(R.styleable.ScrollTab_textSize, 10.0f);
        this.selectTextMaxSize = typedArray.getDimension(R.styleable.ScrollTab_selectTextMaxSize, 0.0f);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setColor(this.defaultTextColor);
        this.textPaint.setTextSize(this.textSize);
        this.textPaint.setStyle(Paint.Style.FILL);
        this.rectPaint.setAntiAlias(true);
        this.rectPaint.setColor(Color.parseColor("#000000"));
        this.rectPaint.setStyle(Paint.Style.STROKE);
        this.rectPaint.setStrokeWidth(4.0f);
    }

    public void move(int position) {
        if (position == this.selectPosition) {
            return;
        }
        post(() -> {
            if (this.beans == null || this.beans.size() == 0) {
                return;
            }
            if (position < 0) {
                this.selectPosition = 0;
            } else if (position >= this.beans.size()) {
                this.selectPosition = this.beans.size() - 1;
            } else {
                this.selectPosition = position;
            }
            float p = this.vRect.centerX() - this.beans.get(this.selectPosition).rect.centerX();
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, p);
            valueAnimator.setDuration(170L);
            valueAnimator.setRepeatCount(0);
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.addUpdateListener(valueAnimator1 -> {
                this.needP = ((Float) valueAnimator1.getAnimatedValue()).floatValue();
                invalidate();
            });
            valueAnimator.addListener(new Animator.AnimatorListener() { // from class: ps.center.views.layout.scrollView.ScrollTab.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    for (Bean item : ScrollTab.this.beans) {
                        item.rect.left += ScrollTab.this.needP;
                        item.rect.right += ScrollTab.this.needP;
                    }
                    ScrollTab.this.needP = 0.0f;
                    ScrollTab.this.enableTouch = true;
                    if (ScrollTab.this.callback != null) {
                        ScrollTab.this.callback.selectPosition(ScrollTab.this.selectPosition, ((Bean) ScrollTab.this.beans.get(ScrollTab.this.selectPosition)).baseTitle);
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }
            });
            valueAnimator.start();
        });
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        if (visibility == getVisibility()) {
            return;
        }
        super.setVisibility(visibility);
        if (visibility == 0 && !this.isResetPosition) {
            this.isResetPosition = true;
            defaultMove();
        }
    }

    @Override // android.view.View
    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.beans == null || this.beans.size() == 0) {
            return;
        }
        for (int i = 0; i < this.beans.size(); i++) {
            if (i == this.selectPosition) {
                this.textPaint.setColor(this.selectTextColor);
                this.textPaint.setFakeBoldText(true);
                this.textPaint.setTextSize(this.selectTextMaxSize);
            } else {
                this.textPaint.setColor(this.defaultTextColor);
                this.textPaint.setFakeBoldText(false);
                this.textPaint.setTextSize(this.textSize);
            }
            if (this.isDrawRect) {
                canvas.drawRect(this.beans.get(i).rect.left + this.needP, this.beans.get(i).rect.top, this.beans.get(i).rect.right + this.needP, this.beans.get(i).rect.bottom, this.rectPaint);
            }
            if (i == this.selectPosition) {
                canvas.drawText(this.beans.get(i).baseTitle.getTitle(), ((this.beans.get(i).rect.left + this.needP) + (this.itemHorizontalPadding / 2.0f)) - (((this.selectTextMaxSize - this.textSize) / 2.0f) * this.beans.get(i).baseTitle.getTitle().length()), (this.beans.get(i).rect.bottom / 2.0f) + (this.textSize / 2.0f), this.textPaint);
            } else {
                canvas.drawText(this.beans.get(i).baseTitle.getTitle(), this.beans.get(i).rect.left + this.needP + (((this.beans.get(i).rect.right - this.beans.get(i).rect.left) - (this.textSize * this.beans.get(i).baseTitle.getTitle().length())) / 2.0f), (this.beans.get(i).rect.bottom / 2.0f) + (this.textSize / 2.0f), this.textPaint);
            }
        }
    }

    public <T> void setCallback(Callback<T> callback) {
        this.callback = callback;
    }

    public void setData(List<? extends BaseTitle> data, int itemHorizontalPadding) {
        setData(data, itemHorizontalPadding, -2);
    }

    public void setData(List<? extends BaseTitle> data, int itemHorizontalPadding, int defaultMoveItem) {
        this.data = data;
        this.itemHorizontalPadding = itemHorizontalPadding;
        this.defaultMoveItem = defaultMoveItem;
        this.beans = new ArrayList();
        post(() -> {
            float reSize = 0.0f;
            for (int i = 0; i < data.size(); i++) {
                Bean bean = new Bean();
                float width = this.textPaint.measureText(((BaseTitle) data.get(i)).getTitle()) + itemHorizontalPadding;
                if (i == 0) {
                    bean.rect = new RectF(0.0f, 0.0f, width, this.viewHeight);
                    reSize = width;
                } else {
                    float f = reSize;
                    float f2 = reSize + width;
                    reSize = f2;
                    bean.rect = new RectF(f, 0.0f, f2, this.viewHeight);
                }
                bean.baseTitle = (BaseTitle) data.get(i);
                this.beans.add(bean);
            }
            if (getVisibility() == View.VISIBLE) {
                defaultMove();
            }
            postInvalidate();
        });
    }

    private void defaultMove() {
        if (this.defaultMoveItem != -1) {
            if (this.defaultMoveItem == -2) {
                return;
            }
            if (this.defaultMoveItem < this.data.size()) {
                this.move = this.defaultMoveItem;
                move(this.defaultMoveItem);
                return;
            } else {
                move(this.data.size() - 1);
                return;
            }
        }
        move(0);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.down.left = event.getX() - 30.0f;
                this.down.right = event.getX() + 30.0f;
                this.down.top = event.getY() - 30.0f;
                this.down.bottom = event.getY() + 30.0f;
                this.isUp = false;
                break;
            case 1:
                this.isUp = true;
                if (!this.enableTouch) {
                    return true;
                }
                if (this.down.contains(event.getX(), event.getY())) {
                    for (int i = 0; i < this.beans.size(); i++) {
                        if (this.beans.get(i).rect.contains(event.getX(), event.getY())) {
                            move(i);
                            return true;
                        }
                    }
                    break;
                }
                break;
            case 2:
                if (!this.enableTouch || this.isUp) {
                    return true;
                }
                if (!this.down.contains(event.getX(), event.getY())) {
                    if (event.getX() < this.down.left) {
                        if (this.selectPosition + 1 == this.beans.size()) {
                            return true;
                        }
                        this.enableTouch = false;
                        this.isUp = true;
                        move(this.selectPosition + 1);
                        break;
                    } else if (event.getX() > this.down.right) {
                        if (this.selectPosition - 1 < 0) {
                            return true;
                        }
                        this.enableTouch = false;
                        this.isUp = true;
                        move(this.selectPosition - 1);
                        break;
                    }
                }
                break;
        }
        invalidate();
        return true;
    }

    public int dp2px(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
    }

    /* loaded from: classes.jar:ps/center/views/layout/scrollView/ScrollTab$Bean.class */
    private static class Bean {
        public RectF rect;
        public BaseTitle baseTitle;

        private Bean() {
        }
    }
}