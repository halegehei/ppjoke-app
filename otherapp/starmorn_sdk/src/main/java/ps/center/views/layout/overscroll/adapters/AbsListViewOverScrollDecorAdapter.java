package ps.center.views.layout.overscroll.adapters;

import android.view.View;
import android.widget.AbsListView;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/AbsListViewOverScrollDecorAdapter.class */
public class AbsListViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final AbsListView mView;

    public AbsListViewOverScrollDecorAdapter(AbsListView view) {
        this.mView = view;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mView;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return this.mView.getChildCount() > 0 && !canScrollListUp();
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return this.mView.getChildCount() > 0 && !canScrollListDown();
    }

    public boolean canScrollListUp() {
        int firstTop = this.mView.getChildAt(0).getTop();
        int firstPosition = this.mView.getFirstVisiblePosition();
        return firstPosition > 0 || firstTop < this.mView.getListPaddingTop();
    }

    public boolean canScrollListDown() {
        int childCount = this.mView.getChildCount();
        int itemsCount = this.mView.getCount();
        int firstPosition = this.mView.getFirstVisiblePosition();
        int lastPosition = firstPosition + childCount;
        int lastBottom = this.mView.getChildAt(childCount - 1).getBottom();
        return lastPosition < itemsCount || lastBottom > this.mView.getHeight() - this.mView.getListPaddingBottom();
    }
}