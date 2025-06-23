package ps.center.views.layout.overscroll;

import android.view.MotionEvent;
import android.view.View;

import ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/VerticalOverScrollBounceEffectDecorator.class */
public class VerticalOverScrollBounceEffectDecorator extends OverScrollBounceEffectDecoratorBase {

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/VerticalOverScrollBounceEffectDecorator$MotionAttributesVertical.class */
    protected static class MotionAttributesVertical extends OverScrollBounceEffectDecoratorBase.MotionAttributes {
        protected MotionAttributesVertical() {
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.MotionAttributes
        public boolean init(View view, MotionEvent event) {
            if (event.getHistorySize() == 0) {
                return false;
            }
            float dy = event.getY(0) - event.getHistoricalY(0, 0);
            float dx = event.getX(0) - event.getHistoricalX(0, 0);
            if (Math.abs(dx) > Math.abs(dy)) {
                return false;
            }
            this.mAbsOffset = view.getTranslationY();
            this.mDeltaOffset = dy;
            this.mDir = this.mDeltaOffset > 0.0f;
            return true;
        }
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/VerticalOverScrollBounceEffectDecorator$AnimationAttributesVertical.class */
    protected static class AnimationAttributesVertical extends OverScrollBounceEffectDecoratorBase.AnimationAttributes {
        public AnimationAttributesVertical() {
            this.mProperty = View.TRANSLATION_Y;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.AnimationAttributes
        protected void init(View view) {
            this.mAbsOffset = view.getTranslationY();
            this.mMaxOffset = view.getHeight();
        }
    }

    public VerticalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter viewAdapter) {
        this(viewAdapter, 3.0f, 1.0f, -2.0f);
    }

    public VerticalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter viewAdapter, float touchDragRatioFwd, float touchDragRatioBck, float decelerateFactor) {
        super(viewAdapter, decelerateFactor, touchDragRatioFwd, touchDragRatioBck);
    }

    @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase
    protected OverScrollBounceEffectDecoratorBase.MotionAttributes createMotionAttributes() {
        return new MotionAttributesVertical();
    }

    @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase
    protected OverScrollBounceEffectDecoratorBase.AnimationAttributes createAnimationAttributes() {
        return new AnimationAttributesVertical();
    }

    @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase
    protected void translateView(View view, float offset) {
        view.setTranslationY(offset);
    }

    @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase
    protected void translateViewAndEvent(View view, float offset, MotionEvent event) {
        view.setTranslationY(offset);
        event.offsetLocation(offset - event.getY(0), 0.0f);
    }
}