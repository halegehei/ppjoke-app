package ps.center.views.layout.manager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.jar:ps/center/views/layout/manager/TiktokLayoutManager.class */
public class TiktokLayoutManager extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private OnScrollChangeListener onScrollChangeListener;
    private PagerSnapHelper pagerSnapHelper;

    /* loaded from: classes.jar:ps/center/views/layout/manager/TiktokLayoutManager$OnScrollChangeListener.class */
    public interface OnScrollChangeListener {
        void itemShow(View view, int i);

        void itemOut(View view, int i);
    }

    public OnScrollChangeListener getOnScrollChangeListener() {
        return this.onScrollChangeListener;
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    private TiktokLayoutManager(Context context) {
        super(context);
    }

    public TiktokLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.pagerSnapHelper = new PagerSnapHelper();
    }

    private TiktokLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void onAttachedToWindow(RecyclerView view) {
        view.addOnChildAttachStateChangeListener(this);
        this.pagerSnapHelper.attachToRecyclerView(view);
        super.onAttachedToWindow(view);
    }

    public void onScrollStateChanged(int state) {
        if (state == 0) {
            View viewIdle = this.pagerSnapHelper.findSnapView(this);
            if (viewIdle == null) {
                Log.e("mylog", "....");
                return;
            }
            int positionIdle = getPosition(viewIdle);
            Log.e("mylog", "...." + positionIdle);
            this.onScrollChangeListener.itemShow(viewIdle, positionIdle);
        }
    }

    public void onChildViewAttachedToWindow(@NonNull View view) {
    }

    public void onChildViewDetachedFromWindow(@NonNull View view) {
        this.onScrollChangeListener.itemOut(view, getPosition(view));
    }
}