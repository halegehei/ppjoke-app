package ps.center.utils.xbanner;

import android.content.Context;
import android.widget.Scroller;

/* loaded from: classes.jar:ps/center/utils/xbanner/XBannerScroller.class */
public class XBannerScroller extends Scroller {
    private int mDuration;

    XBannerScroller(Context context, int duration) {
        super(context);
        this.mDuration = 800;
        this.mDuration = duration;
    }

    @Override // android.widget.Scroller
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, this.mDuration);
    }

    @Override // android.widget.Scroller
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, this.mDuration);
    }
}