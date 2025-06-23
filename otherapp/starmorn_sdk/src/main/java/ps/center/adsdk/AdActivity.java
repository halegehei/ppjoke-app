package ps.center.adsdk;

import android.R;
import android.content.res.TypedArray;
import android.view.KeyEvent;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.CSJAdError;
import com.bytedance.sdk.openadsdk.CSJSplashAd;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;

import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.databinding.BusinessActivityAdBinding;
import ps.center.utils.LogUtils;
import ps.center.utils.ManifestUtils;
import ps.center.utils.Super;
import ps.center.utils.UIUtils;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;

/* loaded from: classes.jar:ps/center/adsdk/AdActivity.class */
public class AdActivity extends BaseActivityVB<BusinessActivityAdBinding> {
    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityAdBinding getLayout() {
        return BusinessActivityAdBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        try {
            String themeName = ManifestUtils.getMetaDataValue(getApplicationContext(), "THEME");
            int style = UIUtils.stringToResourceId(getApplicationContext(), "style", themeName);
            TypedArray typedArray = getTheme().obtainStyledAttributes(style, new int[]{R.attr.windowBackground});
            int resourceId = typedArray.getResourceId(0, 0);
            ((BusinessActivityAdBinding) this.binding).theme.setImageResource(resourceId);
        } catch (Exception e) {
            e.printStackTrace();
            ((BusinessActivityAdBinding) this.binding).theme.setImageResource(ApplicationConfig.getSettingConfig().welcomeThemeDrawable);
        }
        loadAD();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void loadAD() {
        TTAdNative mTTAdNative = TTAdSdk.getAdManager().createAdNative(this);
        AdSlot adSlot = new AdSlot.Builder().setCodeId(BusinessConstant.getConfig().config.csjplatform.csj_kaip).setImageAcceptedSize(Super.getWidth(), Super.getHeight()).setExpressViewAcceptedSize(Super.getWidth(), Super.getHeight()).build();
        mTTAdNative.loadSplashAd(adSlot, new TTAdNative.CSJSplashAdListener() { // from class: ps.center.adsdk.AdActivity.1
            public void onSplashLoadSuccess(CSJSplashAd csjSplashAd) {
            }

            public void onSplashLoadFail(CSJAdError csjAdError) {
                LogUtils.e("onSplashLoadFail：广告加载异常" + csjAdError.getCode() + ":" + csjAdError.getMsg());
                if (AdDataSource.getCallBack() != null) {
                    AdDataSource.getCallBack().over();
                    AdDataSource.setCallBack(null);
                    AdActivity.this.finish();
                }
            }

            public void onSplashRenderSuccess(CSJSplashAd csjSplashAd) {
                csjSplashAd.setSplashAdListener(new CSJSplashAd.SplashAdListener() { // from class: ps.center.adsdk.AdActivity.1.1
                    public void onSplashAdShow(CSJSplashAd csjSplashAd2) {
                        if (AdDataSource.getCallBack() != null) {
                            AdDataSource.getCallBack().show();
                        }
                    }

                    public void onSplashAdClick(CSJSplashAd csjSplashAd2) {
                    }

                    public void onSplashAdClose(CSJSplashAd csjSplashAd2, int i) {
                        if (AdDataSource.getCallBack() != null) {
                            AdDataSource.getCallBack().over();
                            AdDataSource.setCallBack(null);
                            AdActivity.this.finish();
                        }
                    }
                });
                try {
                    ((BusinessActivityAdBinding) AdActivity.this.binding).sdkAdLayout.removeAllViews();
                    csjSplashAd.showSplashView(((BusinessActivityAdBinding) AdActivity.this.binding).sdkAdLayout);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onSplashRenderFail(CSJSplashAd csjSplashAd, CSJAdError csjAdError) {
                LogUtils.e("onSplashRenderFail：" + csjAdError.getCode() + "   msg:" + csjAdError.getMsg());
                if (AdDataSource.getCallBack() != null) {
                    AdDataSource.getCallBack().over();
                    AdDataSource.setCallBack(null);
                    AdActivity.this.finish();
                }
            }
        }, 3000);
    }

    public void finish() {
        super.finish();
        try {
            overridePendingTransition(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}