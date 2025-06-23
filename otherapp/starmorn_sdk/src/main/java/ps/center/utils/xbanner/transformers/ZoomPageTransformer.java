package ps.center.utils.xbanner.transformers;

import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/ZoomPageTransformer.class */
public class ZoomPageTransformer extends BasePageTransformer {
    private float mMinScale = 0.85f;
    private float mMinAlpha = 0.65f;

    public ZoomPageTransformer() {
    }

    public ZoomPageTransformer(float minAlpha, float minScale) {
        setMinAlpha(minAlpha);
        setMinScale(minScale);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleInvisiblePage(View view, float position) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleLeftPage(View view, float position) {
        float scale = Math.max(this.mMinScale, 1.0f + position);
        float vertMargin = (view.getHeight() * (1.0f - scale)) / 2.0f;
        float horzMargin = (view.getWidth() * (1.0f - scale)) / 2.0f;
        view.setTranslationX(horzMargin - (vertMargin / 2.0f));
        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setAlpha(this.mMinAlpha + (((scale - this.mMinScale) / (1.0f - this.mMinScale)) * (1.0f - this.mMinAlpha)));
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleRightPage(View view, float position) {
        float scale = Math.max(this.mMinScale, 1.0f - position);
        float vertMargin = (view.getHeight() * (1.0f - scale)) / 2.0f;
        float horzMargin = (view.getWidth() * (1.0f - scale)) / 2.0f;
        view.setTranslationX((-horzMargin) + (vertMargin / 2.0f));
        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setAlpha(this.mMinAlpha + (((scale - this.mMinScale) / (1.0f - this.mMinScale)) * (1.0f - this.mMinAlpha)));
    }

    public void setMinAlpha(float minAlpha) {
        if (minAlpha >= 0.6f && minAlpha <= 1.0f) {
            this.mMinAlpha = minAlpha;
        }
    }

    public void setMinScale(float minScale) {
        if (minScale >= 0.6f && minScale <= 1.0f) {
            this.mMinScale = minScale;
        }
    }
}