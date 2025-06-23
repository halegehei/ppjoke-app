package ps.center.utils.xbanner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import androidx.core.view.VelocityTrackerCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes.jar:ps/center/utils/xbanner/XBannerViewPager.class */
public class XBannerViewPager extends ViewPager {
    private boolean overlapStyle;
    private boolean mIsAllowUserScroll;
    private AutoPlayDelegate mAutoPlayDelegate;

    /* loaded from: classes.jar:ps/center/utils/xbanner/XBannerViewPager$AutoPlayDelegate.class */
    public interface AutoPlayDelegate {
        void handleAutoPlayActionUpOrCancel(float f);
    }

    public XBannerViewPager(Context context) {
        super(context);
        this.overlapStyle = false;
        this.mIsAllowUserScroll = true;
    }

    public XBannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.overlapStyle = false;
        this.mIsAllowUserScroll = true;
    }

    public void setPageTransformer(boolean reverseDrawingOrder, PageTransformer transformer) {
        try {
            boolean hasTransformer = transformer != null;
            Field pageTransformerField = ViewPager.class.getDeclaredField("mPageTransformer");
            pageTransformerField.setAccessible(true);
            PageTransformer mPageTransformer = (PageTransformer) pageTransformerField.get(this);
            boolean needsPopulate = hasTransformer != (mPageTransformer != null);
            pageTransformerField.set(this, transformer);
            Method setChildrenDrawingOrderEnabledCompatMethod = ViewPager.class.getDeclaredMethod("setChildrenDrawingOrderEnabledCompat", Boolean.TYPE);
            setChildrenDrawingOrderEnabledCompatMethod.setAccessible(true);
            setChildrenDrawingOrderEnabledCompatMethod.invoke(this, Boolean.valueOf(hasTransformer));
            Field drawingOrderField = ViewPager.class.getDeclaredField("mDrawingOrder");
            drawingOrderField.setAccessible(true);
            if (hasTransformer) {
                drawingOrderField.setInt(this, reverseDrawingOrder ? 2 : 1);
            } else {
                drawingOrderField.setInt(this, 0);
            }
            if (needsPopulate) {
                Method populateMethod = ViewPager.class.getDeclaredMethod("populate", new Class[0]);
                populateMethod.setAccessible(true);
                populateMethod.invoke(this, new Object[0]);
            }
        } catch (Exception e) {
        }
    }

    public void setScrollDuration(int duration) {
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            scrollerField.set(this, new XBannerScroller(getContext(), duration));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setBannerCurrentItemInternal(int position, boolean smoothScroll) {
        try {
            Method setCurrentItemInternalMethod = ViewPager.class.getDeclaredMethod("setCurrentItemInternal", Integer.TYPE, Boolean.TYPE, Boolean.TYPE);
            setCurrentItemInternalMethod.setAccessible(true);
            setCurrentItemInternalMethod.invoke(this, Integer.valueOf(position), Boolean.valueOf(smoothScroll), true);
            ViewCompat.postInvalidateOnAnimation(this);
        } catch (Exception e) {
        }
    }

    public void setIsAllowUserScroll(boolean iSallowUserScroll) {
        this.mIsAllowUserScroll = iSallowUserScroll;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.mIsAllowUserScroll && super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (this.mIsAllowUserScroll) {
            if (this.mAutoPlayDelegate != null && (ev.getAction() == 3 || ev.getAction() == 1)) {
                this.mAutoPlayDelegate.handleAutoPlayActionUpOrCancel(getXVelocity());
                return false;
            }
            return super.onTouchEvent(ev);
        }
        return false;
    }

    protected int getChildDrawingOrder(int childCount, int i) {
        if (this.overlapStyle) {
            if (i == childCount - 1) {
                return getCurrentItem();
            }
            return i >= getCurrentItem() ? i + 1 : i;
        }
        return super.getChildDrawingOrder(childCount, i);
    }

    private float getXVelocity() {
        float xVelocity = 0.0f;
        try {
            Field velocityTrackerField = ViewPager.class.getDeclaredField("mVelocityTracker");
            velocityTrackerField.setAccessible(true);
            VelocityTracker velocityTracker = (VelocityTracker) velocityTrackerField.get(this);
            Field activePointerIdField = ViewPager.class.getDeclaredField("mActivePointerId");
            activePointerIdField.setAccessible(true);
            Field maximumVelocityField = ViewPager.class.getDeclaredField("mMaximumVelocity");
            maximumVelocityField.setAccessible(true);
            int maximumVelocity = maximumVelocityField.getInt(this);
            velocityTracker.computeCurrentVelocity(1000, maximumVelocity);
            xVelocity = VelocityTrackerCompat.getXVelocity(velocityTracker, activePointerIdField.getInt(this));
        } catch (Exception e) {
        }
        return xVelocity;
    }

    public void setOverlapStyle(boolean overlapStyle) {
        this.overlapStyle = overlapStyle;
    }

    public void setAutoPlayDelegate(AutoPlayDelegate autoPlayDelegate) {
        this.mAutoPlayDelegate = autoPlayDelegate;
    }
}