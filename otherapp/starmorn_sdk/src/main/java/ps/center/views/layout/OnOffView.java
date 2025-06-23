package ps.center.views.layout;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import ps.center.R;

/* loaded from: classes.jar:ps/center/views/layout/OnOffView.class */
public class OnOffView extends FrameLayout implements View.OnClickListener {
    private static String TAG = "OnOffView";
    private int onColor;
    private int offColor;
    private float iconSize;
    private float iconPaddingSize;
    private int animationTime;
    private CardView icon;
    private boolean animationRun;
    private ImageView backView;
    public boolean defOff;
    private boolean defOff2;
    private CheckBoxCall checkBoxCall;
    private OnClick onClick;

    /* loaded from: classes.jar:ps/center/views/layout/OnOffView$CheckBoxCall.class */
    public interface CheckBoxCall {
        void check(boolean z);
    }

    /* loaded from: classes.jar:ps/center/views/layout/OnOffView$OnClick.class */
    public interface OnClick {
        boolean click();
    }

    public OnOffView(Context context) {
        super(context);
        this.onColor = 0;
        this.offColor = 0;
        this.iconSize = 0.0f;
        this.iconPaddingSize = 0.0f;
        this.animationTime = 300;
        this.animationRun = false;
        this.defOff = false;
        this.defOff2 = false;
    }

    public OnOffView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.onColor = 0;
        this.offColor = 0;
        this.iconSize = 0.0f;
        this.iconPaddingSize = 0.0f;
        this.animationTime = 300;
        this.animationRun = false;
        this.defOff = false;
        this.defOff2 = false;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OnOffView);
        this.iconSize = typedArray.getDimension(R.styleable.OnOffView_iconSize, 0.0f);
        this.iconPaddingSize = typedArray.getDimension(R.styleable.OnOffView_paddingSize, 5.0f);
        boolean z = typedArray.getBoolean(R.styleable.OnOffView_defOff, false);
        this.defOff = z;
        this.defOff2 = z;
        this.onColor = typedArray.getColor(R.styleable.OnOffView_onColor, 5025616);
        this.offColor = typedArray.getColor(R.styleable.OnOffView_offColor, 16777215);
        this.backView = new ImageView(getContext());
        addView(this.backView);
        LayoutParams layoutParams1 = (LayoutParams) this.backView.getLayoutParams();
        layoutParams1.height = -1;
        layoutParams1.width = -1;
        this.icon = new CardView(getContext());
        addView(this.icon);
        LayoutParams layoutParams2 = (LayoutParams) this.icon.getLayoutParams();
        layoutParams2.height = (int) (this.iconSize - (this.iconPaddingSize * 2.0f));
        layoutParams2.width = (int) (this.iconSize - (this.iconPaddingSize * 2.0f));
        this.icon.setX(this.iconPaddingSize);
        this.icon.setY(this.iconPaddingSize);
        this.icon.setRadius((this.iconSize - this.iconPaddingSize) / 2.0f);
        typedArray.recycle();
        setOnClickListener(this);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initIcon();
    }

    public OnOffView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.onColor = 0;
        this.offColor = 0;
        this.iconSize = 0.0f;
        this.iconPaddingSize = 0.0f;
        this.animationTime = 300;
        this.animationRun = false;
        this.defOff = false;
        this.defOff2 = false;
    }

    @RequiresApi(api = 21)
    public OnOffView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.onColor = 0;
        this.offColor = 0;
        this.iconSize = 0.0f;
        this.iconPaddingSize = 0.0f;
        this.animationTime = 300;
        this.animationRun = false;
        this.defOff = false;
        this.defOff2 = false;
    }

    @RequiresApi(api = 21)
    private void startAnimation(final boolean isOff) {
        TranslateAnimation animation;
        ValueAnimator animator;
        int w = this.backView.getMeasuredWidth();
        if (w == 0 || w == -1) {
            w = this.backView.getWidth();
        }
        if (!this.defOff2) {
            if (isOff) {
                animation = new TranslateAnimation((w - this.icon.getLayoutParams().width) - (this.iconPaddingSize * 2.0f), 0.0f, 0.0f, 0.0f);
            } else {
                animation = new TranslateAnimation(0.0f, (w - this.icon.getLayoutParams().width) - (this.iconPaddingSize * 2.0f), 0.0f, 0.0f);
            }
        } else if (isOff) {
            animation = new TranslateAnimation(0.0f, -((w - this.icon.getLayoutParams().width) - (this.iconPaddingSize * 2.0f)), 0.0f, 0.0f);
        } else {
            animation = new TranslateAnimation(-((w - this.icon.getLayoutParams().width) - (this.iconPaddingSize * 2.0f)), 0.0f, 0.0f, 0.0f);
        }
        animation.setDuration(this.animationTime);
        animation.setRepeatMode(2);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() { // from class: ps.center.views.layout.OnOffView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation2) {
                OnOffView.this.animationRun = true;
                if (OnOffView.this.checkBoxCall != null) {
                    OnOffView.this.checkBoxCall.check(isOff);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation2) {
                OnOffView.this.animationRun = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation2) {
            }
        });
        this.icon.startAnimation(animation);
        GradientDrawable background = (GradientDrawable) this.backView.getBackground();
        if (isOff) {
            animator = ObjectAnimator.ofInt(background, "color", this.onColor, this.offColor);
        } else {
            animator = ObjectAnimator.ofInt(background, "color", this.offColor, this.onColor);
        }
        animator.setDuration(this.animationTime);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }

    private void initIcon() {
        if (this.defOff) {
            this.icon.setX((this.backView.getMeasuredWidth() - this.icon.getLayoutParams().width) - this.iconPaddingSize);
        } else {
            this.icon.setX(this.iconPaddingSize);
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(dp2px(50.0f));
        drawable.setStroke(dp2px(1.0f), Color.parseColor("#00EAEAEA"));
        if (this.defOff) {
            drawable.setColor(this.onColor);
        } else {
            drawable.setColor(this.offColor);
        }
        this.backView.setBackground(drawable);
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @Override // android.view.View.OnClickListener
    @RequiresApi(api = 21)
    public void onClick(View v) {
        if (this.onClick != null) {
            boolean click = this.onClick.click();
            if (click) {
                animationCheck();
            }
        }
    }

    public void setDefOff(boolean off) {
        this.defOff = off;
        this.defOff2 = off;
        initIcon();
    }

    @RequiresApi(api = 21)
    public void animationCheck() {
        if (this.animationRun) {
            return;
        }
        if (this.defOff) {
            startAnimation(true);
            this.defOff = false;
        } else {
            startAnimation(false);
            this.defOff = true;
        }
    }

    @RequiresApi(api = 21)
    public void animationCheck(boolean onOff) {
        if (this.animationRun || onOff == this.defOff) {
            return;
        }
        if (this.defOff) {
            startAnimation(true);
            this.defOff = false;
        } else {
            startAnimation(false);
            this.defOff = true;
        }
    }

    public int dp2px(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
    }

    public void setCheckBoxCall(CheckBoxCall checkBoxCall) {
        this.checkBoxCall = checkBoxCall;
    }
}