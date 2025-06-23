package ps.center.views.layout.overscroll.adapters;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/ViewPagerOverScrollDecorAdapter.class */
public class ViewPagerOverScrollDecorAdapter implements IOverScrollDecoratorAdapter, ViewPager.OnPageChangeListener {
    protected final ViewPager mViewPager;
    protected int mLastPagerPosition;
    protected float mLastPagerScrollOffset;

    public ViewPagerOverScrollDecorAdapter(ViewPager viewPager) {
        this.mLastPagerPosition = 0;
        this.mViewPager = viewPager;
        this.mViewPager.addOnPageChangeListener(this);
        this.mLastPagerPosition = this.mViewPager.getCurrentItem();
        this.mLastPagerScrollOffset = 0.0f;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mViewPager;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return this.mLastPagerPosition == 0 && this.mLastPagerScrollOffset == 0.0f;
    }

    @Override // ps.center.views.layout.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return this.mLastPagerPosition == this.mViewPager.getAdapter().getCount() - 1 && this.mLastPagerScrollOffset == 0.0f;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.mLastPagerPosition = position;
        this.mLastPagerScrollOffset = positionOffset;
    }

    public void onPageSelected(int position) {
    }

    public void onPageScrollStateChanged(int state) {
    }
}