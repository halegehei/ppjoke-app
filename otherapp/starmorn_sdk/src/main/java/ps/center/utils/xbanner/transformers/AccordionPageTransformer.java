package ps.center.utils.xbanner.transformers;

import android.view.View;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/AccordionPageTransformer.class */
public class AccordionPageTransformer extends BasePageTransformer {
    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleInvisiblePage(View view, float position) {
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleLeftPage(View view, float position) {
        view.setPivotX(view.getWidth());
        view.setScaleX(1.0f + position);
    }

    @Override // ps.center.utils.xbanner.transformers.BasePageTransformer
    public void handleRightPage(View view, float position) {
        view.setPivotX(0.0f);
        view.setScaleX(1.0f - position);
        view.setAlpha(1.0f);
    }
}