package ps.center.views.layout.overscroll;

import android.view.View;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/IOverScrollDecor.class */
public interface IOverScrollDecor {
    View getView();

    void setOverScrollStateListener(IOverScrollStateListener iOverScrollStateListener);

    void setOverScrollUpdateListener(IOverScrollUpdateListener iOverScrollUpdateListener);

    int getCurrentState();

    void detach();
}