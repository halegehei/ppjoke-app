package ps.center.application.guide;

import android.graphics.Color;
import android.widget.RelativeLayout;
import java.util.List;
import ps.center.application.BuildConfig;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.databinding.BusinessFragmentGuideVideoBinding;
import ps.center.utils.Super;
import ps.center.views.fragment.BaseFragmentVB;
import ps.center.views.fragment.BundleGet;


public class GuideVideoFragment extends BaseFragmentVB<BusinessFragmentGuideVideoBinding> {
    private String url;
    private String type;
    private int index;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.fragment.BaseFragmentVB
    public BusinessFragmentGuideVideoBinding getLayout() {
        return BusinessFragmentGuideVideoBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.fragment.BaseFragmentVB
    protected void initData(BundleGet bundleGet) {
        this.url = (String) bundleGet.getIntentValue("url", BuildConfig.VERSION_NAME);
        this.type = (String) bundleGet.getIntentValue("type", BuildConfig.VERSION_NAME);
        this.index = ((Integer) bundleGet.getIntentValue("index", 0)).intValue();
        ((BusinessFragmentGuideVideoBinding) this.binding).nextBtn.post(() -> {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((BusinessFragmentGuideVideoBinding) this.binding).nextBtn.getLayoutParams();
            layoutParams.bottomMargin = (int) (Super.getWidth() * ApplicationConfig.getSettingConfig().guidePageSubmitOffsetX);
            ((BusinessFragmentGuideVideoBinding) this.binding).nextBtn.setLayoutParams(layoutParams);
        });
        ((BusinessFragmentGuideVideoBinding) this.binding).nextBtn.setBackgroundResource(ApplicationConfig.getSettingConfig().guidePageSubmitDrawable);
        ((BusinessFragmentGuideVideoBinding) this.binding).nextBtn.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().guidePageSubmitTextColor));
        ((BusinessFragmentGuideVideoBinding) this.binding).nextBtn.setText(ApplicationConfig.getSettingConfig().guidePageSubmitText);
    }

    @Override // ps.center.views.fragment.BaseFragmentVB
    protected void setListener() {
        ((BusinessFragmentGuideVideoBinding) this.binding).nextBtn.setOnClickListener(v -> {
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