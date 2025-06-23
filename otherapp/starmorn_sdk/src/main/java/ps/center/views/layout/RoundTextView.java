package ps.center.views.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import ps.center.R;

/* loaded from: classes.jar:ps/center/views/layout/RoundTextView.class */
public class RoundTextView extends AppCompatTextView {
    private float round;
    private int color;
    private float shadowDx;
    private float shadowDy;
    private float shadowRadius;
    private int shadowColor;
    private float shadowPadding;

    public RoundTextView(Context context) {
        super(context);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RoundTextView);
        this.round = typedArray.getDimension(R.styleable.RoundTextView_round_text_bg, 0.0f);
        this.color = typedArray.getColor(R.styleable.RoundTextView_backgroundColor_text_bg, -1);
        this.shadowColor = typedArray.getColor(R.styleable.RoundTextView_shadowColor_text_bg, -1);
        this.shadowDx = typedArray.getFloat(R.styleable.RoundTextView_shadowDx_text_bg, 0.0f);
        this.shadowDy = typedArray.getFloat(R.styleable.RoundTextView_shadowDy_text_bg, 0.0f);
        this.shadowRadius = typedArray.getFloat(R.styleable.RoundTextView_shadowRadius_text_bg, 0.0f);
        this.shadowPadding = typedArray.getDimension(R.styleable.RoundTextView_shadowPadding_text_bg, 0.0f);
        typedArray.recycle();
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(this.color);
        paint.setShadowLayer(this.shadowRadius, this.shadowDx, this.shadowDy, this.shadowColor);
        canvas.drawRoundRect(new RectF(0.0f + this.shadowDx + this.shadowPadding, 0.0f + this.shadowDy + this.shadowPadding, (w - this.shadowDx) - this.shadowPadding, (h - this.shadowDy) - this.shadowPadding), this.round, this.round, paint);
        super.onDraw(canvas);
    }
}