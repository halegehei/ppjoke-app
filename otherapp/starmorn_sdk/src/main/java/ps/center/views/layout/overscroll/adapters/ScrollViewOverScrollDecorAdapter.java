package ps.center.views.layout.overscroll.adapters;

import android.view.View;
import android.widget.ScrollView;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/ScrollViewOverScrollDecorAdapter.class */
public class ScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final ScrollView mView;

    public ScrollViewOverScrollDecorAdapter(ScrollView view) {
        this.mView = view;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mView;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return !this.mView.canScrollVertically(-1);
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return !this.mView.canScrollVertically(1);
    }
}