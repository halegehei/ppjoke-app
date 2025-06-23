package ps.center.views.layout.overscroll;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/OverScrollBounceEffectDecoratorBase.class */
public abstract class OverScrollBounceEffectDecoratorBase implements IOverScrollDecor, View.OnTouchListener {
    public static final String TAG = "OverScrollDecor";
    public static final float DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD = 3.0f;
    public static final float DEFAULT_TOUCH_DRAG_MOVE_RATIO_BCK = 1.0f;
    public static final float DEFAULT_DECELERATE_FACTOR = -2.0f;
    protected static final int MAX_BOUNCE_BACK_DURATION_MS = 800;
    protected static final int MIN_BOUNCE_BACK_DURATION_MS = 200;
    protected final IOverScrollDecoratorAdapter mViewAdapter;
    protected final OverScrollingState mOverScrollingState;
    protected final BounceBackState mBounceBackState;
    protected float mVelocity;
    protected final OverScrollStartAttributes mStartAttr = new OverScrollStartAttributes();
    protected IOverScrollStateListener mStateListener = new ListenerStubs.OverScrollStateListenerStub();
    protected IOverScrollUpdateListener mUpdateListener = new ListenerStubs.OverScrollUpdateListenerStub();
    protected final IdleState mIdleState = new IdleState();
    protected IDecoratorState mCurrentState = this.mIdleState;

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/OverScrollBounceEffectDecoratorBase$IDecoratorState.class */
    protected interface IDecoratorState {
        boolean handleMoveTouchEvent(MotionEvent motionEvent);

        boolean handleUpOrCancelTouchEvent(MotionEvent motionEvent);

        void handleEntryTransition(IDecoratorState iDecoratorState);

        int getStateId();
    }

    protected abstract MotionAttributes createMotionAttributes();

    protected abstract AnimationAttributes createAnimationAttributes();

    protected abstract void translateView(View view, float f);

    protected abstract void translateViewAndEvent(View view, float f, MotionEvent motionEvent);

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/OverScrollBounceEffectDecoratorBase$MotionAttributes.class */
    protected static abstract class MotionAttributes {
        public float mAbsOffset;
        public float mDeltaOffset;
        public boolean mDir;

        protected abstract boolean init(View view, MotionEvent motionEvent);

        protected MotionAttributes() {
        }
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/OverScrollBounceEffectDecoratorBase$OverScrollStartAttributes.class */
    protected static class OverScrollStartAttributes {
        protected int mPointerId;
        protected float mAbsOffset;
        protected boolean mDir;

        protected OverScrollStartAttributes() {
        }
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/OverScrollBounceEffectDecoratorBase$AnimationAttributes.class */
    protected static abstract class AnimationAttributes {
        public Property<View, Float> mProperty;
        public float mAbsOffset;
        public float mMaxOffset;

        protected abstract void init(View view);

        protected AnimationAttributes() {
        }
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/OverScrollBounceEffectDecoratorBase$IdleState.class */
    protected class IdleState implements IDecoratorState {
        final MotionAttributes mMoveAttr;

        public IdleState() {
            this.mMoveAttr = OverScrollBounceEffectDecoratorBase.this.createMotionAttributes();
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public int getStateId() {
            return 0;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleMoveTouchEvent(MotionEvent event) {
            View view = OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView();
            if (!this.mMoveAttr.init(view, event)) {
                return false;
            }
            if ((OverScrollBounceEffectDecoratorBase.this.mViewAdapter.isInAbsoluteStart() && this.mMoveAttr.mDir) || (OverScrollBounceEffectDecoratorBase.this.mViewAdapter.isInAbsoluteEnd() && !this.mMoveAttr.mDir)) {
                OverScrollBounceEffectDecoratorBase.this.mStartAttr.mPointerId = event.getPointerId(0);
                OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset = this.mMoveAttr.mAbsOffset;
                OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir = this.mMoveAttr.mDir;
                OverScrollBounceEffectDecoratorBase.this.issueStateTransition(OverScrollBounceEffectDecoratorBase.this.mOverScrollingState);
                return OverScrollBounceEffectDecoratorBase.this.mOverScrollingState.handleMoveTouchEvent(event);
            }
            return false;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleUpOrCancelTouchEvent(MotionEvent event) {
            return false;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public void handleEntryTransition(IDecoratorState fromState) {
            OverScrollBounceEffectDecoratorBase.this.mStateListener.onOverScrollStateChange(OverScrollBounceEffectDecoratorBase.this, fromState.getStateId(), getStateId());
        }
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/OverScrollBounceEffectDecoratorBase$OverScrollingState.class */
    protected class OverScrollingState implements IDecoratorState {
        protected final float mTouchDragRatioFwd;
        protected final float mTouchDragRatioBck;
        final MotionAttributes mMoveAttr;
        int mCurrDragState;

        public OverScrollingState(float touchDragRatioFwd, float touchDragRatioBck) {
            this.mMoveAttr = OverScrollBounceEffectDecoratorBase.this.createMotionAttributes();
            this.mTouchDragRatioFwd = touchDragRatioFwd;
            this.mTouchDragRatioBck = touchDragRatioBck;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public int getStateId() {
            return this.mCurrDragState;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleMoveTouchEvent(MotionEvent event) {
            if (OverScrollBounceEffectDecoratorBase.this.mStartAttr.mPointerId != event.getPointerId(0)) {
                OverScrollBounceEffectDecoratorBase.this.issueStateTransition(OverScrollBounceEffectDecoratorBase.this.mBounceBackState);
                return true;
            }
            View view = OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView();
            if (!this.mMoveAttr.init(view, event)) {
                return true;
            }
            float deltaOffset = this.mMoveAttr.mDeltaOffset / (this.mMoveAttr.mDir == OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir ? this.mTouchDragRatioFwd : this.mTouchDragRatioBck);
            float newOffset = this.mMoveAttr.mAbsOffset + deltaOffset;
            if ((OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir && !this.mMoveAttr.mDir && newOffset <= OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset) || (!OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir && this.mMoveAttr.mDir && newOffset >= OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset)) {
                OverScrollBounceEffectDecoratorBase.this.translateViewAndEvent(view, OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset, event);
                OverScrollBounceEffectDecoratorBase.this.mUpdateListener.onOverScrollUpdate(OverScrollBounceEffectDecoratorBase.this, this.mCurrDragState, 0.0f);
                OverScrollBounceEffectDecoratorBase.this.issueStateTransition(OverScrollBounceEffectDecoratorBase.this.mIdleState);
                return true;
            }
            if (view.getParent() != null) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
            }
            long dt = event.getEventTime() - event.getHistoricalEventTime(0);
            if (dt > 0) {
                OverScrollBounceEffectDecoratorBase.this.mVelocity = deltaOffset / dt;
            }
            OverScrollBounceEffectDecoratorBase.this.translateView(view, newOffset);
            OverScrollBounceEffectDecoratorBase.this.mUpdateListener.onOverScrollUpdate(OverScrollBounceEffectDecoratorBase.this, this.mCurrDragState, newOffset);
            return true;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleUpOrCancelTouchEvent(MotionEvent event) {
            OverScrollBounceEffectDecoratorBase.this.issueStateTransition(OverScrollBounceEffectDecoratorBase.this.mBounceBackState);
            return false;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public void handleEntryTransition(IDecoratorState fromState) {
            this.mCurrDragState = OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir ? 1 : 2;
            OverScrollBounceEffectDecoratorBase.this.mStateListener.onOverScrollStateChange(OverScrollBounceEffectDecoratorBase.this, fromState.getStateId(), getStateId());
        }
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/OverScrollBounceEffectDecoratorBase$BounceBackState.class */
    protected class BounceBackState implements IDecoratorState, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        protected final Interpolator mBounceBackInterpolator = new DecelerateInterpolator();
        protected final float mDecelerateFactor;
        protected final float mDoubleDecelerateFactor;
        protected final AnimationAttributes mAnimAttributes;

        public BounceBackState(float decelerateFactor) {
            this.mDecelerateFactor = decelerateFactor;
            this.mDoubleDecelerateFactor = 2.0f * decelerateFactor;
            this.mAnimAttributes = OverScrollBounceEffectDecoratorBase.this.createAnimationAttributes();
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public int getStateId() {
            return 3;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public void handleEntryTransition(IDecoratorState fromState) {
            OverScrollBounceEffectDecoratorBase.this.mStateListener.onOverScrollStateChange(OverScrollBounceEffectDecoratorBase.this, fromState.getStateId(), getStateId());
            Animator bounceBackAnim = createAnimator();
            bounceBackAnim.addListener(this);
            bounceBackAnim.start();
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleMoveTouchEvent(MotionEvent event) {
            return true;
        }

        @Override // ps.center.views.layout.overscroll.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleUpOrCancelTouchEvent(MotionEvent event) {
            return true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            OverScrollBounceEffectDecoratorBase.this.issueStateTransition(OverScrollBounceEffectDecoratorBase.this.mIdleState);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator animation) {
            OverScrollBounceEffectDecoratorBase.this.mUpdateListener.onOverScrollUpdate(OverScrollBounceEffectDecoratorBase.this, 3, ((Float) animation.getAnimatedValue()).floatValue());
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animation) {
        }

        protected Animator createAnimator() {
            View view = OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView();
            this.mAnimAttributes.init(view);
            if (OverScrollBounceEffectDecoratorBase.this.mVelocity == 0.0f || ((OverScrollBounceEffectDecoratorBase.this.mVelocity < 0.0f && OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir) || (OverScrollBounceEffectDecoratorBase.this.mVelocity > 0.0f && !OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir))) {
                return createBounceBackAnimator(this.mAnimAttributes.mAbsOffset);
            }
            float slowdownDuration = (-OverScrollBounceEffectDecoratorBase.this.mVelocity) / this.mDecelerateFactor;
            float slowdownDuration2 = slowdownDuration < 0.0f ? 0.0f : slowdownDuration;
            float slowdownDistance = ((-OverScrollBounceEffectDecoratorBase.this.mVelocity) * OverScrollBounceEffectDecoratorBase.this.mVelocity) / this.mDoubleDecelerateFactor;
            float slowdownEndOffset = this.mAnimAttributes.mAbsOffset + slowdownDistance;
            ObjectAnimator slowdownAnim = createSlowdownAnimator(view, (int) slowdownDuration2, slowdownEndOffset);
            ObjectAnimator bounceBackAnim = createBounceBackAnimator(slowdownEndOffset);
            AnimatorSet wholeAnim = new AnimatorSet();
            wholeAnim.playSequentially(slowdownAnim, bounceBackAnim);
            return wholeAnim;
        }

        protected ObjectAnimator createSlowdownAnimator(View view, int slowdownDuration, float slowdownEndOffset) {
            ObjectAnimator slowdownAnim = ObjectAnimator.ofFloat(view, this.mAnimAttributes.mProperty, slowdownEndOffset);
            slowdownAnim.setDuration(slowdownDuration);
            slowdownAnim.setInterpolator(this.mBounceBackInterpolator);
            slowdownAnim.addUpdateListener(this);
            return slowdownAnim;
        }

        protected ObjectAnimator createBounceBackAnimator(float startOffset) {
            View view = OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView();
            float bounceBackDuration = (Math.abs(startOffset) / this.mAnimAttributes.mMaxOffset) * 800.0f;
            ObjectAnimator bounceBackAnim = ObjectAnimator.ofFloat(view, this.mAnimAttributes.mProperty, OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset);
            bounceBackAnim.setDuration(Math.max((int) bounceBackDuration, OverScrollBounceEffectDecoratorBase.MIN_BOUNCE_BACK_DURATION_MS));
            bounceBackAnim.setInterpolator(this.mBounceBackInterpolator);
            bounceBackAnim.addUpdateListener(this);
            return bounceBackAnim;
        }
    }

    public OverScrollBounceEffectDecoratorBase(IOverScrollDecoratorAdapter viewAdapter, float decelerateFactor, float touchDragRatioFwd, float touchDragRatioBck) {
        this.mViewAdapter = viewAdapter;
        this.mBounceBackState = new BounceBackState(decelerateFactor);
        this.mOverScrollingState = new OverScrollingState(touchDragRatioFwd, touchDragRatioBck);
        attach();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case 1:
            case 3:
                return this.mCurrentState.handleUpOrCancelTouchEvent(event);
            case 2:
                return this.mCurrentState.handleMoveTouchEvent(event);
            default:
                return false;
        }
    }

    @Override // ps.center.views.layout.overscroll.IOverScrollDecor
    public void setOverScrollStateListener(IOverScrollStateListener listener) {
        this.mStateListener = listener != null ? listener : new ListenerStubs.OverScrollStateListenerStub();
    }

    @Override // ps.center.views.layout.overscroll.IOverScrollDecor
    public void setOverScrollUpdateListener(IOverScrollUpdateListener listener) {
        this.mUpdateListener = listener != null ? listener : new ListenerStubs.OverScrollUpdateListenerStub();
    }

    @Override // ps.center.views.layout.overscroll.IOverScrollDecor
    public int getCurrentState() {
        return this.mCurrentState.getStateId();
    }

    @Override // ps.center.views.layout.overscroll.IOverScrollDecor
    public View getView() {
        return this.mViewAdapter.getView();
    }

    protected void issueStateTransition(IDecoratorState state) {
        IDecoratorState oldState = this.mCurrentState;
        this.mCurrentState = state;
        this.mCurrentState.handleEntryTransition(oldState);
    }

    protected void attach() {
        getView().setOnTouchListener(this);
        getView().setOverScrollMode(2);
    }

    @Override // ps.center.views.layout.overscroll.IOverScrollDecor
    public void detach() {
        if (this.mCurrentState != this.mIdleState) {
            Log.w(TAG, "Decorator detached while over-scroll is in effect. You might want to add a precondition of that getCurrentState()==STATE_IDLE, first.");
        }
        getView().setOnTouchListener(null);
        getView().setOverScrollMode(0);
    }
}