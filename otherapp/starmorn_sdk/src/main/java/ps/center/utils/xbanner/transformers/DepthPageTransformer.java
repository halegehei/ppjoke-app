package ps.center.utils.xbanner.transformers;

import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/DepthPageTransformer.class */
public class DepthPageTransformer extends BasePageTransformer {
    private float mMinScale = 0.8f;

    public DepthPageTransformer() {
    }

    public DepthPageTransformer(float minScale) {
        setMinScale(minScale);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleInvisiblePage(View view, float position) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleLeftPage(View view, float position) {
        view.setAlpha(1.0f);
        view.setTranslationX(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleRightPage(View view, float position) {
        view.setAlpha(1.0f - position);
        view.setTranslationX((-view.getWidth()) * position);
        float scale = this.mMinScale + ((1.0f - this.mMinScale) * (1.0f - position));
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

    public void setMinScale(float minScale) {
        if (minScale >= 0.6f && minScale <= 1.0f) {
            this.mMinScale = minScale;
        }
    }
}