package ps.center.utils.xbanner.transformers;

import android.view.View;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/ZoomFadePageTransformer.class */
public class ZoomFadePageTransformer extends BasePageTransformer {
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
        view.setAlpha(1.0f + position);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleRightPage(View view, float position) {
        view.setTranslationX((-view.getWidth()) * position);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setScaleX(1.0f - position);
        view.setScaleY(1.0f - position);
        view.setAlpha(1.0f - position);
    }
}