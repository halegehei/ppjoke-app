package ps.center.utils.xbanner.transformers;

import android.view.View;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/ScalePageTransformer.class */
public class ScalePageTransformer extends BasePageTransformer {
    private static final float MIN_SCALE = 0.8f;

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleInvisiblePage(View view, float position) {
        view.setScaleY(MIN_SCALE);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleLeftPage(View view, float position) {
        float scale = Math.max(MIN_SCALE, 1.0f - Math.abs(position));
        view.setScaleY(scale);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleRightPage(View view, float position) {
        float scale = Math.max(MIN_SCALE, 1.0f - Math.abs(position));
        view.setScaleY(scale);
    }
}