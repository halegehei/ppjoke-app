package ps.center.utils.xbanner.holder;

import android.view.View;
import androidx.annotation.LayoutRes;

/* loaded from: classes.jar:ps/center/utils/xbanner/holder/ViewHolder.class */
public interface ViewHolder<T> {
    @LayoutRes
    int getLayoutId();

    void onBind(View view, T t, int i);
}