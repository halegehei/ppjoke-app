package ps.center.application.guide;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import java.util.List;
import ps.center.application.BuildConfig;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.databinding.BusinessFragmentH5GuideBinding;
import ps.center.utils.Super;
import ps.center.views.fragment.BaseFragmentVB;
import ps.center.views.fragment.BundleGet;

/* loaded from: classes.jar:ps/center/application/guide/GuideH5Fragment.class */
public class GuideH5Fragment extends BaseFragmentVB<BusinessFragmentH5GuideBinding> {
    private String url;
    private String type;
    private int index;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.fragment.BaseFragmentVB
    public BusinessFragmentH5GuideBinding getLayout() {
        return BusinessFragmentH5GuideBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.fragment.BaseFragmentVB
    protected void initData(BundleGet bundleGet) {
        this.url = (String) bundleGet.getIntentValue("url", BuildConfig.VERSION_NAME);
        this.type = (String) bundleGet.getIntentValue("type", BuildConfig.VERSION_NAME);
        this.index = ((Integer) bundleGet.getIntentValue("index", 0)).intValue();
        initWebPage();
    }

    @Override // ps.center.views.fragment.BaseFragmentVB
    protected void setListener() {
        ((BusinessFragmentH5GuideBinding) this.binding).diskClickView.setOnClickListener(v -> {
        });
        ((BusinessFragmentH5GuideBinding) this.binding).nextBtn.setOnClickListener(v2 -> {
            List<AppConfig.StandardConfBean.GuidePageBean.FuncBean.GuideWithTypeBean> guide_with_type = BusinessConstant.getConfig().standard_conf.guide_page.func.guide_with_type;
            if (this.url.equals(guide_with_type.get(BusinessConstant.getConfig().standard_conf.guide_page.func.guide_with_type.size() - 1).data)) {
                if (getActivity() != null) {
                    ((GuideActivity) requireActivity()).goMain();
                }
            } else if (getActivity() != null) {
                ((GuideActivity) requireActivity()).nextPage();
            }
        });
        ((BusinessFragmentH5GuideBinding) this.binding).nextBtn.post(() -> {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((BusinessFragmentH5GuideBinding) this.binding).nextBtn.getLayoutParams();
            layoutParams.bottomMargin = (int) (Super.getWidth() * ApplicationConfig.getSettingConfig().guidePageSubmitOffsetX);
            ((BusinessFragmentH5GuideBinding) this.binding).nextBtn.setLayoutParams(layoutParams);
        });
        if (this.index == 0) {
            Glide.with(Super.getContext()).load(Integer.valueOf(ApplicationConfig.getSettingConfig().guidePageDefaultImage)).into(((BusinessFragmentH5GuideBinding) this.binding).defaultGuideImage);
        }
        ((BusinessFragmentH5GuideBinding) this.binding).nextBtn.setBackgroundResource(ApplicationConfig.getSettingConfig().guidePageSubmitDrawable);
        ((BusinessFragmentH5GuideBinding) this.binding).nextBtn.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().guidePageSubmitTextColor));
        ((BusinessFragmentH5GuideBinding) this.binding).nextBtn.setText(ApplicationConfig.getSettingConfig().guidePageSubmitText);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebPage() {
        WebSettings webSettings = ((BusinessFragmentH5GuideBinding) this.binding).webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(true);
        webSettings.setDefaultFontSize(12);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        ((BusinessFragmentH5GuideBinding) this.binding).webView.setWebViewClient(new WebViewClient() { // from class: ps.center.application.guide.GuideH5Fragment.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
            }
        });
        ((BusinessFragmentH5GuideBinding) this.binding).webView.loadUrl(this.url);
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            ((BusinessFragmentH5GuideBinding) this.binding).webView.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}