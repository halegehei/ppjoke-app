package ps.center.utils.xbanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import ps.center.R;
import ps.center.utils.xbanner.entity.BaseBannerInfo;
import ps.center.utils.xbanner.holder.HolderCreator;
import ps.center.utils.xbanner.holder.ViewHolder;
import ps.center.utils.xbanner.listener.OnDoubleClickListener;
import ps.center.utils.xbanner.transformers.BasePageTransformer;
import ps.center.utils.xbanner.transformers.Transformer;

/* loaded from: classes.jar:ps/center/utils/xbanner/XBanner.class */
public class XBanner extends RelativeLayout implements XBannerViewPager.AutoPlayDelegate, ViewPager.OnPageChangeListener {
    private static final int RMP = -1;
    private static final int RWC = -2;
    private static final int LWC = -2;
    private static final int VEL_THRESHOLD = 400;
    private static final int NO_PLACE_HOLDER = -1;
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private int mPageScrollPosition;
    private float mPageScrollPositionOffset;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private OnItemClickListener mOnItemClickListener;
    private static final int LEFT = 0;
    private static final int CENTER = 1;
    private static final int RIGHT = 2;
    private AutoSwitchTask mAutoSwitchTask;
    private LinearLayout mPointRealContainerLl;
    private XBannerViewPager mViewPager;
    private int mPointLeftRightPadding;
    private int mPointTopBottomPadding;
    private int mPointContainerLeftRightPadding;
    private List<?> mData;
    private boolean mIsOneImg;
    private boolean mIsAutoPlay;
    private int mAutoPlayTime;
    private boolean mIsAllowUserScroll;
    private int mSlideScrollMode;
    private int mPointPosition;

    @DrawableRes
    private int mPointNormal;

    @DrawableRes
    private int mPointSelected;
    private Drawable mPointContainerBackgroundDrawable;
    private LayoutParams mPointRealContainerLp;
    private TextView mTipTv;
    private int mTipTextColor;
    private boolean mPointsIsVisible;
    private int mTipTextSize;
    private boolean mIsShowTips;
    private List<String> mTipData;
    private static final int TOP = 10;
    private static final int BOTTOM = 12;
    private int mPointContainerPosition;
    private XBannerAdapter mAdapter;
    private LayoutParams mPointContainerLp;
    private boolean mIsNumberIndicator;
    private TextView mNumberIndicatorTv;
    private Drawable mNumberIndicatorBackground;
    private boolean mIsShowIndicatorOnlyOne;
    private int mPageChangeDuration;
    private boolean mIsTipsMarquee;
    private boolean mIsFirstInvisible;
    private boolean mIsHandLoop;
    private Transformer mTransformer;
    private Bitmap mPlaceholderBitmap;

    @DrawableRes
    private int mPlaceholderDrawableResId;
    private ImageView mPlaceholderImg;
    private boolean mIsClipChildrenMode;
    private int mClipChildrenLeftMargin;
    private int mClipChildrenRightMargin;
    private int mClipChildrenTopBottomMargin;
    private int mViewPagerMargin;
    private boolean mIsClipChildrenModeLessThree;
    private int mBannerBottomMargin;
    private int currentPos;
    private boolean mShowIndicatorInCenter;

    @LayoutRes
    private int layoutResId;
    private boolean isCanClickSide;
    private boolean overlapStyle;
    private HolderCreator holderCreator;
    private ImageView.ScaleType mScaleType;
    private static final ImageView.ScaleType[] sScaleTypeArray = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.jar:ps/center/utils/xbanner/XBanner$INDICATOR_GRAVITY.class */
    public @interface INDICATOR_GRAVITY {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.jar:ps/center/utils/xbanner/XBanner$INDICATOR_POSITION.class */
    public @interface INDICATOR_POSITION {
    }

    /* loaded from: classes.jar:ps/center/utils/xbanner/XBanner$OnItemClickListener.class */
    public interface OnItemClickListener {
        void onItemClick(XBanner xBanner, Object obj, View view, int i);
    }

    /* loaded from: classes.jar:ps/center/utils/xbanner/XBanner$XBannerAdapter.class */
    public interface XBannerAdapter {
        void loadBanner(XBanner xBanner, Object obj, View view, int i);
    }

    @Deprecated
    public void setmAdapter(XBannerAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public void loadImage(XBannerAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public XBanner(Context context) {
        this(context, null);
    }

    public XBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mIsOneImg = false;
        this.mIsAutoPlay = true;
        this.mAutoPlayTime = 5000;
        this.mIsAllowUserScroll = true;
        this.mSlideScrollMode = 0;
        this.mPointPosition = 1;
        this.mPointsIsVisible = true;
        this.mPointContainerPosition = BOTTOM;
        this.mIsNumberIndicator = false;
        this.mIsShowIndicatorOnlyOne = false;
        this.mPageChangeDuration = 1000;
        this.mIsTipsMarquee = false;
        this.mIsFirstInvisible = true;
        this.mIsHandLoop = false;
        this.mPlaceholderBitmap = null;
        this.mBannerBottomMargin = 0;
        this.currentPos = 0;
        this.layoutResId = -1;
        this.isCanClickSide = true;
        this.overlapStyle = false;
        this.mScaleType = ImageView.ScaleType.FIT_XY;
        init(context);
        initCustomAttrs(context, attrs);
        initView();
    }

    private void init(Context context) {
        this.mAutoSwitchTask = new AutoSwitchTask(this);
        this.mPointLeftRightPadding = XBannerUtils.dp2px(context, 3.0f);
        this.mPointTopBottomPadding = XBannerUtils.dp2px(context, 6.0f);
        this.mPointContainerLeftRightPadding = XBannerUtils.dp2px(context, 10.0f);
        this.mClipChildrenLeftMargin = XBannerUtils.dp2px(context, 30.0f);
        this.mClipChildrenRightMargin = XBannerUtils.dp2px(context, 30.0f);
        this.mClipChildrenTopBottomMargin = XBannerUtils.dp2px(context, 10.0f);
        this.mViewPagerMargin = XBannerUtils.dp2px(context, 10.0f);
        this.mTipTextSize = XBannerUtils.sp2px(context, 10.0f);
        this.mTransformer = Transformer.Default;
        this.mTipTextColor = -1;
        this.mPointContainerBackgroundDrawable = new ColorDrawable(Color.parseColor("#44aaaaaa"));
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XBanner);
        if (typedArray != null) {
            this.mIsAutoPlay = typedArray.getBoolean(R.styleable.XBanner_isAutoPlay, true);
            this.mIsHandLoop = typedArray.getBoolean(R.styleable.XBanner_isHandLoop, false);
            this.mIsTipsMarquee = typedArray.getBoolean(R.styleable.XBanner_isTipsMarquee, false);
            this.mAutoPlayTime = typedArray.getInteger(R.styleable.XBanner_AutoPlayTime, 5000);
            this.mPointsIsVisible = typedArray.getBoolean(R.styleable.XBanner_pointsVisibility, true);
            this.mPointPosition = typedArray.getInt(R.styleable.XBanner_pointsPosition, 1);
            this.mPointContainerLeftRightPadding = typedArray.getDimensionPixelSize(R.styleable.XBanner_pointContainerLeftRightPadding, this.mPointContainerLeftRightPadding);
            this.mPointLeftRightPadding = typedArray.getDimensionPixelSize(R.styleable.XBanner_pointLeftRightPadding, this.mPointLeftRightPadding);
            this.mPointTopBottomPadding = typedArray.getDimensionPixelSize(R.styleable.XBanner_pointTopBottomPadding, this.mPointTopBottomPadding);
            this.mPointContainerPosition = typedArray.getInt(R.styleable.XBanner_pointContainerPosition, BOTTOM);
            this.mPointContainerBackgroundDrawable = typedArray.getDrawable(R.styleable.XBanner_pointsContainerBackground);
            this.mPointNormal = typedArray.getResourceId(R.styleable.XBanner_pointNormal, R.drawable.shape_point_normal);
            this.mPointSelected = typedArray.getResourceId(R.styleable.XBanner_pointSelect, R.drawable.shape_point_select);
            this.mTipTextColor = typedArray.getColor(R.styleable.XBanner_tipTextColor, this.mTipTextColor);
            this.mTipTextSize = typedArray.getDimensionPixelSize(R.styleable.XBanner_tipTextSize, this.mTipTextSize);
            this.mIsNumberIndicator = typedArray.getBoolean(R.styleable.XBanner_isShowNumberIndicator, this.mIsNumberIndicator);
            this.mNumberIndicatorBackground = typedArray.getDrawable(R.styleable.XBanner_numberIndicatorBacgroud);
            this.mIsShowIndicatorOnlyOne = typedArray.getBoolean(R.styleable.XBanner_isShowIndicatorOnlyOne, this.mIsShowIndicatorOnlyOne);
            this.mPageChangeDuration = typedArray.getInt(R.styleable.XBanner_pageChangeDuration, this.mPageChangeDuration);
            this.mPlaceholderDrawableResId = typedArray.getResourceId(R.styleable.XBanner_placeholderDrawable, -1);
            this.mIsClipChildrenMode = typedArray.getBoolean(R.styleable.XBanner_isClipChildrenMode, false);
            this.mClipChildrenLeftMargin = typedArray.getDimensionPixelSize(R.styleable.XBanner_clipChildrenLeftMargin, this.mClipChildrenLeftMargin);
            this.mClipChildrenRightMargin = typedArray.getDimensionPixelSize(R.styleable.XBanner_clipChildrenRightMargin, this.mClipChildrenRightMargin);
            this.mClipChildrenTopBottomMargin = typedArray.getDimensionPixelSize(R.styleable.XBanner_clipChildrenTopBottomMargin, this.mClipChildrenTopBottomMargin);
            this.mViewPagerMargin = typedArray.getDimensionPixelSize(R.styleable.XBanner_viewpagerMargin, this.mViewPagerMargin);
            this.mIsClipChildrenModeLessThree = typedArray.getBoolean(R.styleable.XBanner_isClipChildrenModeLessThree, false);
            this.mIsShowTips = typedArray.getBoolean(R.styleable.XBanner_isShowTips, false);
            this.mBannerBottomMargin = typedArray.getDimensionPixelSize(R.styleable.XBanner_bannerBottomMargin, this.mBannerBottomMargin);
            this.mShowIndicatorInCenter = typedArray.getBoolean(R.styleable.XBanner_showIndicatorInCenter, true);
            int scaleTypeIndex = typedArray.getInt(R.styleable.XBanner_android_scaleType, -1);
            if (scaleTypeIndex >= 0 && scaleTypeIndex < sScaleTypeArray.length) {
                this.mScaleType = sScaleTypeArray[scaleTypeIndex];
            }
            typedArray.recycle();
        }
    }

    private void initView() {
        RelativeLayout pointContainerRl = new RelativeLayout(getContext());
        if (Build.VERSION.SDK_INT >= 16) {
            pointContainerRl.setBackground(this.mPointContainerBackgroundDrawable);
        } else {
            pointContainerRl.setBackgroundDrawable(this.mPointContainerBackgroundDrawable);
        }
        pointContainerRl.setPadding(this.mPointContainerLeftRightPadding, this.mPointTopBottomPadding, this.mPointContainerLeftRightPadding, this.mPointTopBottomPadding);
        this.mPointContainerLp = new LayoutParams(-1, -2);
        this.mPointContainerLp.addRule(this.mPointContainerPosition);
        if (this.mIsClipChildrenMode && this.mShowIndicatorInCenter) {
            if (this.mIsShowTips) {
                this.mPointContainerLp.setMargins(this.mClipChildrenLeftMargin, 0, this.mClipChildrenRightMargin, 0);
            } else {
                this.mPointContainerLp.setMargins(0, 0, 0, 0);
            }
        }
        addView(pointContainerRl, this.mPointContainerLp);
        this.mPointRealContainerLp = new LayoutParams(-2, -2);
        if (this.mIsNumberIndicator) {
            this.mNumberIndicatorTv = new TextView(getContext());
            this.mNumberIndicatorTv.setId(R.id.xbanner_pointId);
            this.mNumberIndicatorTv.setGravity(17);
            this.mNumberIndicatorTv.setSingleLine(true);
            this.mNumberIndicatorTv.setEllipsize(TextUtils.TruncateAt.END);
            this.mNumberIndicatorTv.setTextColor(this.mTipTextColor);
            this.mNumberIndicatorTv.setTextSize(0, this.mTipTextSize);
            this.mNumberIndicatorTv.setVisibility(View.INVISIBLE);
            if (this.mNumberIndicatorBackground != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    this.mNumberIndicatorTv.setBackground(this.mNumberIndicatorBackground);
                } else {
                    this.mNumberIndicatorTv.setBackgroundDrawable(this.mNumberIndicatorBackground);
                }
            }
            pointContainerRl.addView(this.mNumberIndicatorTv, this.mPointRealContainerLp);
        } else {
            this.mPointRealContainerLl = new LinearLayout(getContext());
            this.mPointRealContainerLl.setOrientation(LinearLayout.HORIZONTAL);
            this.mPointRealContainerLl.setId(R.id.xbanner_pointId);
            pointContainerRl.addView(this.mPointRealContainerLl, this.mPointRealContainerLp);
        }
        if (this.mPointRealContainerLl != null) {
            if (this.mPointsIsVisible) {
                this.mPointRealContainerLl.setVisibility(View.VISIBLE);
            } else {
                this.mPointRealContainerLl.setVisibility(View.GONE);
            }
        }
        LayoutParams pointLp = new LayoutParams(-1, -2);
        pointLp.addRule(15);
        if (this.mIsShowTips) {
            this.mTipTv = new TextView(getContext());
            this.mTipTv.setGravity(16);
            this.mTipTv.setSingleLine(true);
            if (this.mIsTipsMarquee) {
                this.mTipTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                this.mTipTv.setMarqueeRepeatLimit(3);
                this.mTipTv.setSelected(true);
            } else {
                this.mTipTv.setEllipsize(TextUtils.TruncateAt.END);
            }
            this.mTipTv.setTextColor(this.mTipTextColor);
            this.mTipTv.setTextSize(0, this.mTipTextSize);
            pointContainerRl.addView(this.mTipTv, pointLp);
        }
        if (1 == this.mPointPosition) {
            this.mPointRealContainerLp.addRule(14);
            pointLp.addRule(0, R.id.xbanner_pointId);
        } else if (0 == this.mPointPosition) {
            this.mPointRealContainerLp.addRule(9);
            if (this.mTipTv != null) {
                this.mTipTv.setGravity(21);
            }
            pointLp.addRule(1, R.id.xbanner_pointId);
        } else if (2 == this.mPointPosition) {
            this.mPointRealContainerLp.addRule(11);
            pointLp.addRule(0, R.id.xbanner_pointId);
        }
        setBannerPlaceholderDrawable();
    }

    private void setBannerPlaceholderDrawable() {
        if (this.mPlaceholderDrawableResId != -1) {
            this.mPlaceholderBitmap = BitmapFactory.decodeResource(getResources(), this.mPlaceholderDrawableResId);
        }
        if (this.mPlaceholderBitmap != null && this.mPlaceholderImg == null) {
            this.mPlaceholderImg = new ImageView(getContext());
            this.mPlaceholderImg.setScaleType(this.mScaleType);
            this.mPlaceholderImg.setImageBitmap(this.mPlaceholderBitmap);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            addView(this.mPlaceholderImg, layoutParams);
        }
    }

    private void removeBannerPlaceHolderDrawable() {
        if (this.mPlaceholderImg != null && equals(this.mPlaceholderImg.getParent())) {
            removeView(this.mPlaceholderImg);
            this.mPlaceholderImg = null;
        }
    }

    @Deprecated
    public void setData(@LayoutRes int layoutResId, List<?> models, List<String> tips) {
        if (models == null) {
            models = new ArrayList();
        }
        if (models.isEmpty()) {
            this.mIsAutoPlay = false;
            this.mIsClipChildrenMode = false;
        }
        if (!this.mIsClipChildrenModeLessThree && models.size() < 3) {
            this.mIsClipChildrenMode = false;
        }
        this.layoutResId = layoutResId;
        this.mData = models;
        this.mTipData = tips;
        this.mIsOneImg = models.size() == 1;
        initPoints();
        initViewPager();
        if (!models.isEmpty()) {
            removeBannerPlaceHolderDrawable();
        } else {
            setBannerPlaceholderDrawable();
        }
    }

    @Deprecated
    public void setData(List<?> models, List<String> tips) {
        setData(R.layout.xbanner_item_image, models, tips);
    }

    public void setBannerData(@LayoutRes int layoutResId, List<? extends BaseBannerInfo> models) {
        if (models == null) {
            models = new ArrayList();
        }
        if (models.isEmpty()) {
            this.mIsAutoPlay = false;
            this.mIsClipChildrenMode = false;
        }
        if (!this.mIsClipChildrenModeLessThree && models.size() < 3) {
            this.mIsClipChildrenMode = false;
        }
        this.layoutResId = layoutResId;
        this.mData = models;
        this.mIsOneImg = models.size() == 1;
        initPoints();
        initViewPager();
        if (!models.isEmpty()) {
            removeBannerPlaceHolderDrawable();
        } else {
            setBannerPlaceholderDrawable();
        }
    }

    public void setBannerData(List<? extends BaseBannerInfo> models, HolderCreator holderCreator) {
        this.holderCreator = holderCreator;
        if (models == null) {
            models = new ArrayList();
        }
        if (models.isEmpty()) {
            this.mIsAutoPlay = false;
            this.mIsClipChildrenMode = false;
        }
        if (!this.mIsClipChildrenModeLessThree && models.size() < 3) {
            this.mIsClipChildrenMode = false;
        }
        this.mData = models;
        this.mIsOneImg = models.size() == 1;
        initPoints();
        initViewPager();
        if (!models.isEmpty()) {
            removeBannerPlaceHolderDrawable();
        } else {
            setBannerPlaceholderDrawable();
        }
    }

    public void setBannerData(List<? extends BaseBannerInfo> models) {
        setBannerData(R.layout.xbanner_item_image, models);
    }

    public void setPointsIsVisible(boolean isVisible) {
        if (null != this.mPointRealContainerLl) {
            if (isVisible) {
                this.mPointRealContainerLl.setVisibility(View.VISIBLE);
            } else {
                this.mPointRealContainerLl.setVisibility(View.GONE);
            }
        }
    }

    public void setPointPosition(int position) {
        if (1 == position) {
            this.mPointRealContainerLp.addRule(14);
        } else if (0 == position) {
            this.mPointRealContainerLp.addRule(9);
        } else if (2 == position) {
            this.mPointRealContainerLp.addRule(11);
        }
    }

    public void setPointContainerPosition(int position) {
        if (BOTTOM == position) {
            this.mPointContainerLp.addRule(BOTTOM);
        } else if (TOP == position) {
            this.mPointContainerLp.addRule(TOP);
        }
    }

    private void initViewPager() {
        if (this.mViewPager != null && equals(this.mViewPager.getParent())) {
            removeView(this.mViewPager);
            this.mViewPager = null;
        }
        this.currentPos = 0;
        this.mViewPager = new XBannerViewPager(getContext());
        this.mViewPager.setAdapter(new XBannerPageAdapter());
        this.mViewPager.clearOnPageChangeListeners();
        this.mViewPager.addOnPageChangeListener(this);
        this.mViewPager.setOverScrollMode(this.mSlideScrollMode);
        this.mViewPager.setIsAllowUserScroll(this.mIsAllowUserScroll);
        this.mViewPager.setPageTransformer(true, BasePageTransformer.getPageTransformer(this.mTransformer));
        setPageChangeDuration(this.mPageChangeDuration);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(0, 0, 0, this.mBannerBottomMargin);
        if (this.mIsClipChildrenMode) {
            setClipChildren(false);
            this.mViewPager.setClipToPadding(false);
            this.mViewPager.setOffscreenPageLimit(2);
            this.mViewPager.setClipChildren(false);
            this.mViewPager.setPadding(this.mClipChildrenLeftMargin, this.mClipChildrenTopBottomMargin, this.mClipChildrenRightMargin, this.mBannerBottomMargin);
            this.mViewPager.setOverlapStyle(this.overlapStyle);
            this.mViewPager.setPageMargin(this.overlapStyle ? -this.mViewPagerMargin : this.mViewPagerMargin);
        }
        addView((View) this.mViewPager, 0, (ViewGroup.LayoutParams) layoutParams);
        if (this.mIsAutoPlay && getRealCount() != 0) {
            this.currentPos = 1073741823 - (1073741823 % getRealCount());
            this.mViewPager.setCurrentItem(this.currentPos);
            this.mViewPager.setAutoPlayDelegate(this);
            startAutoPlay();
            return;
        }
        if (this.mIsHandLoop && getRealCount() != 0) {
            this.currentPos = 1073741823 - (1073741823 % getRealCount());
            this.mViewPager.setCurrentItem(this.currentPos);
        }
        switchToPoint(0);
    }

    public int getRealCount() {
        if (this.mData == null) {
            return 0;
        }
        return this.mData.size();
    }

    public XBannerViewPager getViewPager() {
        return this.mViewPager;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.mPageScrollPosition = position;
        this.mPageScrollPositionOffset = positionOffset;
        if (this.mTipTv == null || this.mData == null || this.mData.size() == 0 || !(this.mData.get(0) instanceof BaseBannerInfo)) {
            if (this.mTipTv != null && this.mTipData != null && !this.mTipData.isEmpty()) {
                if (positionOffset > 0.5d) {
                    this.mTipTv.setText(this.mTipData.get(getRealPosition(position + 1)));
                    this.mTipTv.setAlpha(positionOffset);
                } else {
                    this.mTipTv.setText(this.mTipData.get(getRealPosition(position)));
                    this.mTipTv.setAlpha(1.0f - positionOffset);
                }
            }
        } else if (positionOffset > 0.5d) {
            this.mTipTv.setText(((BaseBannerInfo) this.mData.get(getRealPosition(position + 1))).getXBannerTitle());
            this.mTipTv.setAlpha(positionOffset);
        } else {
            this.mTipTv.setText(((BaseBannerInfo) this.mData.get(getRealPosition(position))).getXBannerTitle());
            this.mTipTv.setAlpha(1.0f - positionOffset);
        }
        if (null != this.mOnPageChangeListener && getRealCount() != 0) {
            this.mOnPageChangeListener.onPageScrolled(position % getRealCount(), positionOffset, positionOffsetPixels);
        }
    }

    public void onPageSelected(int position) {
        if (getRealCount() == 0) {
            return;
        }
        this.currentPos = getRealPosition(position);
        switchToPoint(this.currentPos);
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(this.currentPos);
        }
    }

    public void onPageScrollStateChanged(int state) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(state);
        }
    }

    @Override // ps.center.utils.xbanner.XBannerViewPager.AutoPlayDelegate
    public void handleAutoPlayActionUpOrCancel(float xVelocity) {
        if (this.mViewPager != null) {
            if (this.mPageScrollPosition < this.mViewPager.getCurrentItem()) {
                if (xVelocity > 400.0f || (this.mPageScrollPositionOffset < 0.7f && xVelocity > -400.0f)) {
                    this.mViewPager.setBannerCurrentItemInternal(this.mPageScrollPosition, true);
                    return;
                } else {
                    this.mViewPager.setBannerCurrentItemInternal(this.mPageScrollPosition + 1, true);
                    return;
                }
            }
            if (this.mPageScrollPosition != this.mViewPager.getCurrentItem()) {
                if (this.mIsClipChildrenMode) {
                    int realPosition = getRealPosition(this.mPageScrollPosition);
                    setBannerCurrentItem(realPosition, true);
                    return;
                } else {
                    this.mViewPager.setBannerCurrentItemInternal(this.mPageScrollPosition, true);
                    return;
                }
            }
            if (xVelocity < -400.0f || (this.mPageScrollPositionOffset > 0.3f && xVelocity < 400.0f)) {
                this.mViewPager.setBannerCurrentItemInternal(this.mPageScrollPosition + 1, true);
            } else {
                this.mViewPager.setBannerCurrentItemInternal(this.mPageScrollPosition, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount != 0) {
            return position % realCount;
        }
        return position;
    }

    private void initPoints() {
        if (this.mPointRealContainerLl != null) {
            this.mPointRealContainerLl.removeAllViews();
            if (getRealCount() > 0 && (this.mIsShowIndicatorOnlyOne || !this.mIsOneImg)) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
                lp.gravity = 16;
                lp.setMargins(this.mPointLeftRightPadding, this.mPointTopBottomPadding, this.mPointLeftRightPadding, this.mPointTopBottomPadding);
                for (int i = 0; i < getRealCount(); i++) {
                    ImageView imageView = new ImageView(getContext());
                    imageView.setLayoutParams(lp);
                    if (this.mPointNormal != 0 && this.mPointSelected != 0) {
                        imageView.setImageResource(this.mPointNormal);
                    }
                    this.mPointRealContainerLl.addView(imageView);
                }
            }
        }
        if (this.mNumberIndicatorTv != null) {
            if (getRealCount() > 0 && (this.mIsShowIndicatorOnlyOne || !this.mIsOneImg)) {
                this.mNumberIndicatorTv.setVisibility(View.VISIBLE);
            } else {
                this.mNumberIndicatorTv.setVisibility(View.GONE);
            }
        }
    }

    private void switchToPoint(int currentPoint) {
        if ((this.mPointRealContainerLl != null) & (this.mData != null)) {
            for (int i = 0; i < this.mPointRealContainerLl.getChildCount(); i++) {
                if (i == currentPoint) {
                    ((ImageView) this.mPointRealContainerLl.getChildAt(i)).setImageResource(this.mPointSelected);
                } else {
                    ((ImageView) this.mPointRealContainerLl.getChildAt(i)).setImageResource(this.mPointNormal);
                }
                this.mPointRealContainerLl.getChildAt(i).requestLayout();
            }
        }
        if (this.mTipTv != null && this.mData != null && this.mData.size() != 0 && (this.mData.get(0) instanceof BaseBannerInfo)) {
            this.mTipTv.setText(((BaseBannerInfo) this.mData.get(currentPoint)).getXBannerTitle());
        } else if (this.mTipTv != null && this.mTipData != null && !this.mTipData.isEmpty()) {
            this.mTipTv.setText(this.mTipData.get(currentPoint));
        }
        if (this.mNumberIndicatorTv == null || this.mData == null) {
            return;
        }
        if (this.mIsShowIndicatorOnlyOne || !this.mIsOneImg) {
            this.mNumberIndicatorTv.setText(String.valueOf((currentPoint + 1) + "/" + this.mData.size()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (this.mViewPager != null) {
            switch (ev.getAction()) {
                case 0:
                    float touchX = ev.getRawX();
                    int paddingLeft = this.mViewPager.getLeft();
                    if (touchX >= paddingLeft && touchX < XBannerUtils.getScreenWidth(getContext()) - paddingLeft) {
                        stopAutoPlay();
                        break;
                    }
                    break;
                case 1:
                    startAutoPlay();
                    break;
                case 3:
                    getParent().requestDisallowInterceptTouchEvent(false);
                case 4:
                    startAutoPlay();
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void startAutoPlay() {
        stopAutoPlay();
        if (this.mIsAutoPlay) {
            postDelayed(this.mAutoSwitchTask, this.mAutoPlayTime);
        }
    }

    public void stopAutoPlay() {
        if (this.mAutoSwitchTask != null) {
            removeCallbacks(this.mAutoSwitchTask);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void setSlideScrollMode(int slideScrollMode) {
        this.mSlideScrollMode = slideScrollMode;
        if (null != this.mViewPager) {
            this.mViewPager.setOverScrollMode(slideScrollMode);
        }
    }

    public void setAllowUserScrollable(boolean allowUserScrollable) {
        this.mIsAllowUserScroll = allowUserScrollable;
        if (null != this.mViewPager) {
            this.mViewPager.setIsAllowUserScroll(allowUserScrollable);
        }
    }

    public void setAutoPlayAble(boolean mAutoPlayAble) {
        this.mIsAutoPlay = mAutoPlayAble;
        stopAutoPlay();
        if (this.mViewPager != null && this.mViewPager.getAdapter() != null) {
            this.mViewPager.getAdapter().notifyDataSetChanged();
        }
    }

    public void setAutoPlayTime(int mAutoPlayTime) {
        this.mAutoPlayTime = mAutoPlayTime;
    }

    public void setPageTransformer(Transformer transformer) {
        this.mTransformer = transformer;
        if (this.mViewPager != null && this.mTransformer != null) {
            initViewPager();
        }
    }

    public void setViewPagerMargin(@Dimension int viewPagerMargin) {
        this.mViewPagerMargin = viewPagerMargin;
        if (this.mViewPager != null) {
            this.mViewPager.setPageMargin(XBannerUtils.dp2px(getContext(), viewPagerMargin));
        }
    }

    public void setCustomPageTransformer(ViewPager.PageTransformer transformer) {
        if (transformer != null && this.mViewPager != null) {
            this.mViewPager.setPageTransformer(true, transformer);
        }
    }

    public void setPageChangeDuration(int duration) {
        if (this.mViewPager != null) {
            this.mViewPager.setScrollDuration(duration);
        }
    }

    public void setHandLoop(boolean handLoop) {
        this.mIsHandLoop = handLoop;
    }

    public void setIsClipChildrenMode(boolean mIsClipChildrenMode) {
        this.mIsClipChildrenMode = mIsClipChildrenMode;
    }

    public void setShowIndicatorOnlyOne(boolean showIndicatorOnlyOne) {
        this.mIsShowIndicatorOnlyOne = showIndicatorOnlyOne;
    }

    public int getBannerCurrentItem() {
        if (this.mViewPager == null || this.mData == null || this.mData.size() == 0) {
            return -1;
        }
        return this.mViewPager.getCurrentItem() % getRealCount();
    }

    public void setBannerCurrentItem(int position) {
        setBannerCurrentItem(position, false);
    }

    public void setBannerCurrentItem(int position, boolean smoothScroll) {
        if (this.mViewPager == null || this.mData == null || position > getRealCount() - 1) {
            return;
        }
        if (this.mIsAutoPlay || this.mIsHandLoop) {
            int currentItem = this.mViewPager.getCurrentItem();
            int realCurrentItem = getRealPosition(currentItem);
            int offset = position - realCurrentItem;
            if (offset < 0) {
                for (int i = -1; i >= offset; i--) {
                    this.mViewPager.setCurrentItem(currentItem + i, smoothScroll);
                }
            } else if (offset > 0) {
                for (int i2 = 1; i2 <= offset; i2++) {
                    this.mViewPager.setCurrentItem(currentItem + i2, smoothScroll);
                }
            }
            startAutoPlay();
            return;
        }
        this.mViewPager.setCurrentItem(position, smoothScroll);
    }

    public void setCanClickSide(boolean canClickSide) {
        this.isCanClickSide = canClickSide;
    }

    public void setBannerPlaceholderImg(@DrawableRes int mPlaceholderDrawableResId, ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        this.mPlaceholderDrawableResId = mPlaceholderDrawableResId;
        setBannerPlaceholderDrawable();
    }

    public void setBannerPlaceholderImg(Bitmap mPlaceholderBitmap, ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        this.mPlaceholderBitmap = mPlaceholderBitmap;
        setBannerPlaceholderDrawable();
    }

    public void setOverlapStyle(boolean overlapStyle) {
        this.overlapStyle = overlapStyle;
        if (this.overlapStyle) {
            this.mTransformer = Transformer.OverLap;
        }
    }

    public void setClipChildrenLeftRightMargin(@Dimension int mClipChildrenLeftMargin, @Dimension int mClipChildrenRightMargin) {
        this.mClipChildrenLeftMargin = mClipChildrenLeftMargin;
        this.mClipChildrenRightMargin = mClipChildrenRightMargin;
    }

    public void setIsShowTips(boolean mIsShowTips) {
        this.mIsShowTips = mIsShowTips;
    }

    public void setIsClipChildrenModeLessThree(boolean mIsClipChildrenModeLessThree) {
        this.mIsClipChildrenModeLessThree = mIsClipChildrenModeLessThree;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (0 == visibility) {
            startAutoPlay();
        } else if (8 == visibility || 4 == visibility) {
            onInvisibleToUser();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onInvisibleToUser();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoPlay();
    }

    /* loaded from: classes.jar:ps/center/utils/xbanner/XBanner$AutoSwitchTask.class */
    private static class AutoSwitchTask implements Runnable {
        private final WeakReference<XBanner> mXBanner;

        private AutoSwitchTask(XBanner banner) {
            this.mXBanner = new WeakReference<>(banner);
        }

        @Override // java.lang.Runnable
        public void run() {
            XBanner banner = this.mXBanner.get();
            if (banner != null) {
                if (banner.mViewPager != null) {
                    int currentItem = banner.mViewPager.getCurrentItem() + 1;
                    banner.mViewPager.setCurrentItem(currentItem);
                }
                banner.startAutoPlay();
            }
        }
    }

    private void onInvisibleToUser() {
        stopAutoPlay();
        if (!this.mIsFirstInvisible && this.mIsAutoPlay && this.mViewPager != null && getRealCount() > 0 && this.mPageScrollPositionOffset != 0.0f) {
            this.mViewPager.setCurrentItem(this.mViewPager.getCurrentItem() - 1, false);
            this.mViewPager.setCurrentItem(this.mViewPager.getCurrentItem() + 1, false);
        }
        this.mIsFirstInvisible = false;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /* loaded from: classes.jar:ps/center/utils/xbanner/XBanner$XBannerPageAdapter.class */
    private class XBannerPageAdapter extends PagerAdapter {
        private XBannerPageAdapter() {
        }

        public int getCount() {
            return (XBanner.this.mIsAutoPlay || XBanner.this.mIsHandLoop) ? XBanner.MAX_VALUE : XBanner.this.getRealCount();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View itemView;
            if (XBanner.this.getRealCount() != 0) {
                final int realPosition = XBanner.this.getRealPosition(position);
                if (XBanner.this.holderCreator == null) {
                    itemView = LayoutInflater.from(XBanner.this.getContext()).inflate(XBanner.this.layoutResId, container, false);
                    if (XBanner.this.mOnItemClickListener != null && !XBanner.this.mData.isEmpty()) {
                        itemView.setOnClickListener(new OnDoubleClickListener() { // from class: ps.center.utils.xbanner.XBanner.XBannerPageAdapter.1
                            @Override // ps.center.utils.xbanner.listener.OnDoubleClickListener
                            public void onNoDoubleClick(View v) {
                                if (XBanner.this.isCanClickSide) {
                                    XBanner.this.setBannerCurrentItem(realPosition, true);
                                }
                                XBanner.this.mOnItemClickListener.onItemClick(XBanner.this, XBanner.this.mData.get(realPosition), v, realPosition);
                            }
                        });
                    }
                    if (null != XBanner.this.mAdapter && !XBanner.this.mData.isEmpty()) {
                        XBanner.this.mAdapter.loadBanner(XBanner.this, XBanner.this.mData.get(realPosition), itemView, realPosition);
                    }
                } else {
                    itemView = XBanner.this.getView(container, realPosition);
                }
                container.addView(itemView);
                return itemView;
            }
            return null;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getView(ViewGroup container, int position) {
        ViewHolder holder = this.holderCreator.createViewHolder(this.holderCreator.getViewType(position));
        if (holder == null) {
            throw new NullPointerException("Can not return a null holder");
        }
        return createView(holder, position, container);
    }

    private View createView(ViewHolder holder, int position, ViewGroup container) {
        View itemView = LayoutInflater.from(container.getContext()).inflate(holder.getLayoutId(), container, false);
        if (this.mData != null && this.mData.size() > 0) {
            setViewListener(itemView, position);
            holder.onBind(itemView, this.mData.get(position), position);
        }
        return itemView;
    }

    private void setViewListener(View view, final int position) {
        if (view != null) {
            view.setOnClickListener(new OnDoubleClickListener() { // from class: ps.center.utils.xbanner.XBanner.1
                @Override // ps.center.utils.xbanner.listener.OnDoubleClickListener
                public void onNoDoubleClick(View v) {
                    if (null != XBanner.this.mOnItemClickListener) {
                        XBanner.this.mOnItemClickListener.onItemClick(XBanner.this, XBanner.this.mData.get(position), v, position);
                    }
                }
            });
        }
    }
}