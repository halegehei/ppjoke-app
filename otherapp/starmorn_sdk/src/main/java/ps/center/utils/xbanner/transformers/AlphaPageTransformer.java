package ps.center.utils.xbanner.transformers;

import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/AlphaPageTransformer.class */
public class AlphaPageTransformer extends BasePageTransformer {
    private float mMinScale = 0.4f;

    public AlphaPageTransformer() {
    }

    public AlphaPageTransformer(float minScale) {
        setMinScale(minScale);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleInvisiblePage(View view, float position) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleLeftPage(View view, float position) {
        view.setAlpha(this.mMinScale + ((1.0f - this.mMinScale) * (1.0f + position)));
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleRightPage(View view, float position) {
        view.setAlpha(this.mMinScale + ((1.0f - this.mMinScale) * (1.0f - position)));
    }

    public void setMinScale(float minScale) {
        if (minScale >= 0.0f && minScale <= 1.0f) {
            this.mMinScale = minScale;
        }
    }
}