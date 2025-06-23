package ps.center.views.layout.overscroll.adapters;

import android.view.View;
import android.widget.HorizontalScrollView;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/HorizontalScrollViewOverScrollDecorAdapter.class */
public class HorizontalScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final HorizontalScrollView mView;

    public HorizontalScrollViewOverScrollDecorAdapter(HorizontalScrollView view) {
        this.mView = view;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mView;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return !this.mView.canScrollHorizontally(-1);
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return !this.mView.canScrollHorizontally(1);
    }
}