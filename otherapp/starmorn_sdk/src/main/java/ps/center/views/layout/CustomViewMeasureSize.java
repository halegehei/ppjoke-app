package ps.center.views.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/* loaded from: classes.jar:ps/center/views/layout/CustomViewMeasureSize.class */
public abstract class CustomViewMeasureSize extends View {
    public float viewWidth;
    public float viewHeight;

    public CustomViewMeasureSize(Context context) {
        super(context);
        this.viewWidth = 0.0f;
        this.viewHeight = 0.0f;
    }

    public CustomViewMeasureSize(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.viewWidth = 0.0f;
        this.viewHeight = 0.0f;
    }

    public CustomViewMeasureSize(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.viewWidth = 0.0f;
        this.viewHeight = 0.0f;
    }

    public CustomViewMeasureSize(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.viewWidth = 0.0f;
        this.viewHeight = 0.0f;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == 1073741824) {
            this.viewWidth = widthSize;
        }
        if (heightMode == 1073741824) {
            this.viewHeight = heightSize;
        }
    }
}