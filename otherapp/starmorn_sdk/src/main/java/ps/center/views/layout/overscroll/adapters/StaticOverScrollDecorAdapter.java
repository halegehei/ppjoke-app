package ps.center.views.layout.overscroll.adapters;

import android.view.View;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/StaticOverScrollDecorAdapter.class */
public class StaticOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final View mView;

    public StaticOverScrollDecorAdapter(View view) {
        this.mView = view;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mView;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return true;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return true;
    }
}