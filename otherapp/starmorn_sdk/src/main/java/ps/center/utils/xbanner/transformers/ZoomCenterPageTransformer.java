package ps.center.utils.xbanner.transformers;

import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/ZoomCenterPageTransformer.class */
public class ZoomCenterPageTransformer extends BasePageTransformer {
    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleInvisiblePage(View view, float position) {
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleLeftPage(View view, float position) {
        view.setTranslationX((-view.getWidth()) * position);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setScaleX(1.0f + position);
        view.setScaleY(1.0f + position);
        if (position < -0.95f) {
            view.setAlpha(0.0f);
        } else {
            view.setAlpha(1.0f);
        }
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleRightPage(View view, float position) {
        view.setTranslationX((-view.getWidth()) * position);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setScaleX(1.0f - position);
        view.setScaleY(1.0f - position);
        if (position > 0.95f) {
            ViewCompat.setAlpha(view, 0.0f);
        } else {
            ViewCompat.setAlpha(view, 1.0f);
        }
    }
}