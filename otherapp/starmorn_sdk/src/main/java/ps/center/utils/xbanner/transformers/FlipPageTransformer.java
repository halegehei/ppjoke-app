package ps.center.utils.xbanner.transformers;

import android.view.View;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/FlipPageTransformer.class */
public class FlipPageTransformer extends BasePageTransformer {
    private static final float ROTATION = 180.0f;

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleInvisiblePage(View view, float position) {
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleLeftPage(View view, float position) {
        view.setTranslationX((-view.getWidth()) * position);
        float rotation = ROTATION * position;
        view.setRotationY(rotation);
        if (position > -0.5d) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleRightPage(View view, float position) {
        view.setTranslationX((-view.getWidth()) * position);
        float rotation = ROTATION * position;
        view.setRotationY(rotation);
        if (position < 0.5d) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }
}