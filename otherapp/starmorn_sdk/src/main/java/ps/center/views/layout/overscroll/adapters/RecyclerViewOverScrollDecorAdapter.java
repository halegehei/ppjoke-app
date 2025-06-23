package ps.center.views.layout.overscroll.adapters;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.List;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/RecyclerViewOverScrollDecorAdapter.class */
public class RecyclerViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final RecyclerView mRecyclerView;
    protected final Impl mImpl;
    protected boolean mIsItemTouchInEffect;

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/RecyclerViewOverScrollDecorAdapter$Impl.class */
    protected interface Impl {
        boolean isInAbsoluteStart();

        boolean isInAbsoluteEnd();
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView) {

        this.mIsItemTouchInEffect = false;
        this.mRecyclerView = recyclerView;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager
                || layoutManager instanceof StaggeredGridLayoutManager) {

            int orientation;
            if (layoutManager instanceof LinearLayoutManager) {
                orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            } else {
                // now safe to cast to StaggeredGridLayoutManager
                orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            }

            if (orientation == LinearLayoutManager.HORIZONTAL) {
                this.mImpl = new ImplHorizLayout();
            } else {
                this.mImpl = new ImplVerticalLayout();
            }
            return;
        }

        throw new IllegalArgumentException(
                "Recycler views with custom layout managers are not supported by this adapter…"
        );
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, Impl impl) {
        this.mIsItemTouchInEffect = false;
        this.mRecyclerView = recyclerView;
        this.mImpl = impl;
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, ItemTouchHelper.Callback itemTouchHelperCallback) {
        this(recyclerView);
        setUpTouchHelperCallback(itemTouchHelperCallback);
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, Impl impl, ItemTouchHelper.Callback itemTouchHelperCallback) {
        this(recyclerView, impl);
        setUpTouchHelperCallback(itemTouchHelperCallback);
    }

    protected void setUpTouchHelperCallback(ItemTouchHelper.Callback itemTouchHelperCallback) {
        new ItemTouchHelper(new ItemTouchHelperCallbackWrapper(itemTouchHelperCallback) { // from class: ps.center.views.layout.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.1
            @Override // ps.center.views.layout.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.ItemTouchHelperCallbackWrapper
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                RecyclerViewOverScrollDecorAdapter.this.mIsItemTouchInEffect = actionState != 0;
                super.onSelectedChanged(viewHolder, actionState);
            }
        }).attachToRecyclerView(this.mRecyclerView);
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mRecyclerView;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return !this.mIsItemTouchInEffect && this.mImpl.isInAbsoluteStart();
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return !this.mIsItemTouchInEffect && this.mImpl.isInAbsoluteEnd();
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/RecyclerViewOverScrollDecorAdapter$ImplHorizLayout.class */
    protected class ImplHorizLayout implements Impl {
        protected ImplHorizLayout() {
        }

        @Override // ps.center.views.layout.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean isInAbsoluteStart() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollHorizontally(-1);
        }

        @Override // ps.center.views.layout.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean isInAbsoluteEnd() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollHorizontally(1);
        }
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/RecyclerViewOverScrollDecorAdapter$ImplVerticalLayout.class */
    protected class ImplVerticalLayout implements Impl {
        protected ImplVerticalLayout() {
        }

        @Override // ps.center.views.layout.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean isInAbsoluteStart() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollVertically(-1);
        }

        @Override // ps.center.views.layout.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean isInAbsoluteEnd() {
            return !RecyclerViewOverScrollDecorAdapter.this.mRecyclerView.canScrollVertically(1);
        }
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/RecyclerViewOverScrollDecorAdapter$ItemTouchHelperCallbackWrapper.class */
    private static class ItemTouchHelperCallbackWrapper extends ItemTouchHelper.Callback {
        final ItemTouchHelper.Callback mCallback;

        private ItemTouchHelperCallbackWrapper(ItemTouchHelper.Callback callback) {
            this.mCallback = callback;
        }

        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return this.mCallback.getMovementFlags(recyclerView, viewHolder);
        }

        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return this.mCallback.onMove(recyclerView, viewHolder, target);
        }

        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            this.mCallback.onSwiped(viewHolder, direction);
        }

        public int convertToAbsoluteDirection(int flags, int layoutDirection) {
            return this.mCallback.convertToAbsoluteDirection(flags, layoutDirection);
        }

        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
            return this.mCallback.canDropOver(recyclerView, current, target);
        }

        public boolean isLongPressDragEnabled() {
            return this.mCallback.isLongPressDragEnabled();
        }

        public boolean isItemViewSwipeEnabled() {
            return this.mCallback.isItemViewSwipeEnabled();
        }

        public int getBoundingBoxMargin() {
            return this.mCallback.getBoundingBoxMargin();
        }

        public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
            return this.mCallback.getSwipeThreshold(viewHolder);
        }

        public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
            return this.mCallback.getMoveThreshold(viewHolder);
        }

        public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder selected, List<RecyclerView.ViewHolder> dropTargets, int curX, int curY) {
            return this.mCallback.chooseDropTarget(selected, dropTargets, curX, curY);
        }

        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            this.mCallback.onSelectedChanged(viewHolder, actionState);
        }

        public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
            this.mCallback.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            this.mCallback.clearView(recyclerView, viewHolder);
        }

        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            this.mCallback.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            this.mCallback.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        public long getAnimationDuration(RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
            return this.mCallback.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int viewSize, int viewSizeOutOfBounds, int totalSize, long msSinceStartScroll) {
            return this.mCallback.interpolateOutOfBoundsScroll(recyclerView, viewSize, viewSizeOutOfBounds, totalSize, msSinceStartScroll);
        }
    }
}