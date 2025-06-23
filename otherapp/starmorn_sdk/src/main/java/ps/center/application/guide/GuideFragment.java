package ps.center.application.guide;

import android.graphics.Color;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
import ps.center.application.BuildConfig;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.databinding.BusinessFragmentGuideBinding;
import ps.center.utils.Super;
import ps.center.views.fragment.BaseFragmentVB;
import ps.center.views.fragment.BundleGet;

/* loaded from: classes.jar:ps/center/application/guide/GuideFragment.class */
public class GuideFragment extends BaseFragmentVB<BusinessFragmentGuideBinding> {
    private String url;
    private String type;
    private int index;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.fragment.BaseFragmentVB
    public BusinessFragmentGuideBinding getLayout() {
        return BusinessFragmentGuideBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.fragment.BaseFragmentVB
    protected void initData(BundleGet bundleGet) {
        this.url = (String) bundleGet.getIntentValue("url", BuildConfig.VERSION_NAME);
        this.type = (String) bundleGet.getIntentValue("type", BuildConfig.VERSION_NAME);
        this.index = ((Integer) bundleGet.getIntentValue("index", 0)).intValue();
        ((BusinessFragmentGuideBinding) this.binding).nextBtn.post(() -> {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((BusinessFragmentGuideBinding) this.binding).nextBtn.getLayoutParams();
            layoutParams.bottomMargin = (int) (Super.getWidth() * ApplicationConfig.getSettingConfig().guidePageSubmitOffsetX);
            ((BusinessFragmentGuideBinding) this.binding).nextBtn.setLayoutParams(layoutParams);
        });
        if (this.index == 0) {
            Glide.with(Super.getContext()).load(this.url).apply(new RequestOptions().placeholder(ApplicationConfig.getSettingConfig().guidePageDefaultImage).error(ApplicationConfig.getSettingConfig().guidePageDefaultImage)).into(((BusinessFragmentGuideBinding) this.binding).guideImage);
        } else {
            Glide.with(Super.getContext()).load(this.url).into(((BusinessFragmentGuideBinding) this.binding).guideImage);
        }
        ((BusinessFragmentGuideBinding) this.binding).nextBtn.setBackgroundResource(ApplicationConfig.getSettingConfig().guidePageSubmitDrawable);
        ((BusinessFragmentGuideBinding) this.binding).nextBtn.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().guidePageSubmitTextColor));
        ((BusinessFragmentGuideBinding) this.binding).nextBtn.setText(ApplicationConfig.getSettingConfig().guidePageSubmitText);
    }

    @Override // ps.center.views.fragment.BaseFragmentVB
    protected void setListener() {
        ((BusinessFragmentGuideBinding) this.binding).nextBtn.setOnClickListener(v -> {
            List<AppConfig.StandardConfBean.GuidePageBean.FuncBean.GuideWithTypeBean> guide_with_type = BusinessConstant.getConfig().standard_conf.guide_page.func.guide_with_type;
            if (this.url.equals(guide_with_type.get(BusinessConstant.getConfig().standard_conf.guide_page.func.guide_with_type.size() - 1).data)) {
                if (getActivity() != null) {
                    ((GuideActivity) requireActivity()).goMain();

                }
            } else if (getActivity() != null) {
                ((GuideActivity) requireActivity()).nextPage();
            }
        });
    }
}