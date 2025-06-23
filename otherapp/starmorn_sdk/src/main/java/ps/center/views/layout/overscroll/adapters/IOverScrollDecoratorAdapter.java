package ps.center.views.layout.overscroll.adapters;

import android.view.View;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/adapters/IOverScrollDecoratorAdapter.class */
public interface IOverScrollDecoratorAdapter {
    View getView();

    boolean isInAbsoluteStart();

    boolean isInAbsoluteEnd();
}