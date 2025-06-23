package ps.center.utils.xbanner.transformers;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import ps.center.application.DataChangeEvent.DataChangeStatus;

/* loaded from: classes.jar:ps/center/utils/xbanner/transformers/BasePageTransformer.class */
public abstract class BasePageTransformer implements ViewPager.PageTransformer {
    public abstract void handleInvisiblePage(View view, float f);

    public abstract void handleLeftPage(View view, float f);

    public abstract void handleRightPage(View view, float f);

    public void transformPage(View view, float position) {
        if (view.getParent() instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) view.getParent();
            float position2 = getRealPosition(viewPager, view);
            if (position2 < -1.0f) {
                handleInvisiblePage(view, position2);
                return;
            }
            if (position2 <= 0.0f) {
                handleLeftPage(view, position2);
            } else if (position2 <= 1.0f) {
                handleRightPage(view, position2);
            } else {
                handleInvisiblePage(view, position2);
            }
        }
    }

    private float getRealPosition(ViewPager viewPager, View page) {
        int width = (viewPager.getMeasuredWidth() - viewPager.getPaddingLeft()) - viewPager.getPaddingRight();
        return ((page.getLeft() - viewPager.getScrollX()) - viewPager.getPaddingLeft()) / width;
    }

    /* renamed from: ps.center.utils.xbanner.transformers.BasePageTransformer$1, reason: invalid class name */
    /* loaded from: classes.jar:ps/center/utils/xbanner/transformers/BasePageTransformer$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ps$center$utils$xbanner$transformers$Transformer = new int[Transformer.values().length];

        static {
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.Alpha.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.Rotate.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.Cube.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.Flip.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.Accordion.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.ZoomFade.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.ZoomCenter.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.ZoomStack.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.Stack.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.Depth.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.Zoom.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.Scale.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$ps$center$utils$xbanner$transformers$Transformer[Transformer.OverLap.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    public static BasePageTransformer getPageTransformer(Transformer effect) {
        switch (AnonymousClass1.$SwitchMap$ps$center$utils$xbanner$transformers$Transformer[effect.ordinal()]) {
            case 1:
                return new AlphaPageTransformer();
            case 2:
                return new RotatePageTransformer();
            case 3:
                return new CubePageTransformer();
            case 4:
                return new FlipPageTransformer();
            case 5:
                return new AccordionPageTransformer();
            case DataChangeStatus.PAY_CANCEL /* 6 */:
                return new ZoomFadePageTransformer();
            case 7:
                return new ZoomCenterPageTransformer();
            case 8:
                return new ZoomStackPageTransformer();
            case 9:
                return new StackPageTransformer();
            case 10:
                return new DepthPageTransformer();
            case 11:
                return new ZoomPageTransformer();
            case 12:
                return new ScalePageTransformer();
            case 13:
                return new OverLapPageTransformer();
            default:
                return new DefaultPageTransformer();
        }
    }
}