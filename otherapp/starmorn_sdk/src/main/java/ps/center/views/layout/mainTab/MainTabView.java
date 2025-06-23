package ps.center.views.layout.mainTab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.jar:ps/center/views/layout/mainTab/MainTabView.class */
public class MainTabView extends View {
    private Paint testPatin;
    private MainTab mainTab;
    private int height;
    private int width;
    private List<RectF> itemRectFs;

    public MainTabView(Context context) {
        super(context);
        this.itemRectFs = new ArrayList();
    }

    public MainTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.itemRectFs = new ArrayList();
    }

    public MainTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.itemRectFs = new ArrayList();
    }

    public void setData(MainTab mainTab) {
        this.mainTab = mainTab;
        if (mainTab != null) {
            mainTab.builder.rootLayout.addView(this);
            post(this::initOn);
        }
    }

    private void initOn() {
        this.height = getHeight();
        this.width = getWidth();
        if (this.mainTab.builder != null && this.mainTab.builder.items != null && this.mainTab.builder.items.size() > 0) {
            int rectFWidth = this.width / this.mainTab.builder.items.size();
            for (int i = 0; i < this.mainTab.builder.items.size(); i++) {
                this.itemRectFs.add(new RectF(0.0f, 0.0f, rectFWidth * (i + 1), this.height));
            }
        }
        this.testPatin = new Paint();
        this.testPatin.setColor(-16777216);
        this.testPatin.setStyle(Paint.Style.STROKE);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (RectF item : this.itemRectFs) {
            canvas.drawRect(item, this.testPatin);
        }
    }

    public int dp2px(float dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
    }

    public int px2dp(float px) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) ((px / scale) + 0.5f);
    }
}