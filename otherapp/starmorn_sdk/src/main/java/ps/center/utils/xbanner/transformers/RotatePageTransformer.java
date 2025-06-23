package ps.center.utils.xbanner.transformers;

import android.view.View;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/RotatePageTransformer.class */
public class RotatePageTransformer extends BasePageTransformer {
    private float mMaxRotation = 15.0f;

    public RotatePageTransformer() {
    }

    public RotatePageTransformer(float maxRotation) {
        setMaxRotation(maxRotation);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleInvisiblePage(View view, float position) {
        view.setPivotX(view.getMeasuredWidth() * 0.5f);
        view.setPivotY(view.getMeasuredHeight());
        view.setRotation(0.0f);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleLeftPage(View view, float position) {
        float rotation = this.mMaxRotation * position;
        view.setPivotX(view.getMeasuredWidth() * 0.5f);
        view.setPivotY(view.getMeasuredHeight());
        view.setRotation(rotation);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleRightPage(View view, float position) {
        handleLeftPage(view, position);
    }

    public void setMaxRotation(float maxRotation) {
        if (maxRotation >= 0.0f && maxRotation <= 40.0f) {
            this.mMaxRotation = maxRotation;
        }
    }
}