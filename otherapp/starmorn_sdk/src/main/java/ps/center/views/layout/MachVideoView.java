package ps.center.views.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/* loaded from: classes.jar:ps/center/views/layout/MachVideoView.class */
public class MachVideoView extends VideoView {
    public MachVideoView(Context context) {
        super(context);
    }

    public MachVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MachVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}