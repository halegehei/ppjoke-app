package ps.center.utils.xbanner.transformers;

import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/OverLapPageTransformer.class */
public class OverLapPageTransformer extends BasePageTransformer {
    private float scaleValue;
    private float alphaValue;

    public OverLapPageTransformer() {
        this.scaleValue = 0.8f;
        this.alphaValue = 1.0f;
    }

    public OverLapPageTransformer(float scaleValue, float alphaValue) {
        this.scaleValue = 0.8f;
        this.alphaValue = 1.0f;
        this.scaleValue = scaleValue;
        this.alphaValue = alphaValue;
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleInvisiblePage(View view, float position) {
        view.setAlpha(1.0f);
        view.setScaleX(this.scaleValue);
        view.setScaleY(this.scaleValue);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleLeftPage(View view, float position) {
        view.setAlpha(1.0f + (position * (1.0f - this.alphaValue)));
        float scale = Math.max(this.scaleValue, 1.0f - Math.abs(position));
        view.setScaleX(scale);
        view.setScaleY(scale);
        ViewCompat.setTranslationZ(view, position);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleRightPage(View view, float position) {
        view.setAlpha(1.0f - (position * (1.0f - this.alphaValue)));
        float scale = Math.max(this.scaleValue, 1.0f - Math.abs(position));
        view.setScaleX(scale);
        view.setScaleY(scale);
        ViewCompat.setTranslationZ(view, -position);
    }
}