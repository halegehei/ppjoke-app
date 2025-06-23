package ps.center.views.layout.overscroll;

import android.view.MotionEvent;
import android.view.View;

import ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/HorizontalOverScrollBounceEffectDecorator.class */
public class HorizontalOverScrollBounceEffectDecorator extends OverScrollBounceEffectDecoratorBase {

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/HorizontalOverScrollBounceEffectDecorator$MotionAttributesHorizontal.class */
    protected static class MotionAttributesHorizontal extends OverScrollBounceEffectDecoratorBase.MotionAttributes {
        protected MotionAttributesHorizontal() {
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.MotionAttributes
        public boolean init(View view, MotionEvent event) {
            if (event.getHistorySize() == 0) {
                return false;
            }
            float dy = event.getY(0) - event.getHistoricalY(0, 0);
            float dx = event.getX(0) - event.getHistoricalX(0, 0);
            if (Math.abs(dx) < Math.abs(dy)) {
                return false;
            }
            this.mAbsOffset = view.getTranslationX();
            this.mDeltaOffset = dx;
            this.mDir = this.mDeltaOffset > 0.0f;
            return true;
        }
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/HorizontalOverScrollBounceEffectDecorator$AnimationAttributesHorizontal.class */
    protected static class AnimationAttributesHorizontal extends OverScrollBounceEffectDecoratorBase.AnimationAttributes {
        public AnimationAttributesHorizontal() {
            this.mProperty = View.TRANSLATION_X;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.AnimationAttributes
        protected void init(View view) {
            this.mAbsOffset = view.getTranslationX();
            this.mMaxOffset = view.getWidth();
        }
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter viewAdapter) {
        this(viewAdapter, 3.0f, 1.0f, -2.0f);
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter viewAdapter, float touchDragRatioFwd, float touchDragRatioBck, float decelerateFactor) {
        super(viewAdapter, decelerateFactor, touchDragRatioFwd, touchDragRatioBck);
    }

    @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase
    protected OverScrollBounceEffectDecoratorBase.MotionAttributes createMotionAttributes() {
        return new MotionAttributesHorizontal();
    }

    @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase
    protected OverScrollBounceEffectDecoratorBase.AnimationAttributes createAnimationAttributes() {
        return new AnimationAttributesHorizontal();
    }

    @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase
    protected void translateView(View view, float offset) {
        view.setTranslationX(offset);
    }

    @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase
    protected void translateViewAndEvent(View view, float offset, MotionEvent event) {
        view.setTranslationX(offset);
        event.offsetLocation(offset - event.getX(0), 0.0f);
    }
}