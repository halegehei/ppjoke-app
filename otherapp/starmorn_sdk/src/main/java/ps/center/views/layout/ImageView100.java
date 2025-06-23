package ps.center.views.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

@Deprecated
/* loaded from: classes.jar:ps/center/views/layout/ImageView100.class */
public class ImageView100 extends AppCompatImageView {
    private final float r;
    private final float[] rids;

    public ImageView100(Context context) {
        super(context);
        this.r = dp2px(100.0f);
        this.rids = new float[]{this.r, this.r, this.r, this.r, this.r, this.r, this.r, this.r};
    }

    public ImageView100(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.r = dp2px(100.0f);
        this.rids = new float[]{this.r, this.r, this.r, this.r, this.r, this.r, this.r, this.r};
    }

    public ImageView100(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.r = dp2px(100.0f);
        this.rids = new float[]{this.r, this.r, this.r, this.r, this.r, this.r, this.r, this.r};
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = getWidth();
        int h = getHeight();
        path.addRoundRect(new RectF(0.0f, 0.0f, w, h), this.rids, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

    public int dp2px(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
    }
}