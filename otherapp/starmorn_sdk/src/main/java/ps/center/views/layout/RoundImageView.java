package ps.center.views.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import ps.center.R;

/* loaded from: classes.jar:ps/center/views/layout/RoundImageView.class */
public class RoundImageView extends AppCompatImageView {
    private float topLeft;
    private float topRight;
    private float bottomLeft;
    private float bottomRight;

    public RoundImageView(Context context) {
        super(context);
        this.topLeft = 0.0f;
        this.topRight = 0.0f;
        this.bottomLeft = 0.0f;
        this.bottomRight = 0.0f;
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.topLeft = 0.0f;
        this.topRight = 0.0f;
        this.bottomLeft = 0.0f;
        this.bottomRight = 0.0f;
        init(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.topLeft = 0.0f;
        this.topRight = 0.0f;
        this.bottomLeft = 0.0f;
        this.bottomRight = 0.0f;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RoundImageView);
        float round = typedArray.getDimension(R.styleable.RoundImageView_img_round, 0.0f);
        this.topLeft = typedArray.getDimension(R.styleable.RoundImageView_img_roundTopLeft, 0.0f);
        this.topRight = typedArray.getDimension(R.styleable.RoundImageView_img_roundTopRight, 0.0f);
        this.bottomLeft = typedArray.getDimension(R.styleable.RoundImageView_img_roundBottomLeft, 0.0f);
        this.bottomRight = typedArray.getDimension(R.styleable.RoundImageView_img_roundBottomRight, 0.0f);
        if (round != 0.0f) {
            this.topLeft = round;
            this.topRight = round;
            this.bottomLeft = round;
            this.bottomRight = round;
        }
        typedArray.recycle();
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        float[] rids = {this.topLeft, this.topLeft, this.topRight, this.topRight, this.bottomRight, this.bottomRight, this.bottomLeft, this.bottomLeft};
        Path path = new Path();
        int w = getWidth();
        int h = getHeight();
        path.addRoundRect(new RectF(0.0f, 0.0f, w, h), rids, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}