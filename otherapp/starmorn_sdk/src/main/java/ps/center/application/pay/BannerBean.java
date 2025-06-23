package ps.center.application.pay;

import ps.center.application.BuildConfig;
import ps.center.utils.xbanner.entity.BaseBannerInfo;

/* loaded from: classes.jar:ps/center/application/pay/BannerBean.class */
public class BannerBean implements BaseBannerInfo {
    public String imageUrl;

    public BannerBean(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override // ps.center.utils.xbanner.entity.BaseBannerInfo
    public Object getXBannerUrl() {
        return this.imageUrl;
    }

    @Override // ps.center.utils.xbanner.entity.BaseBannerInfo
    public String getXBannerTitle() {
        return BuildConfig.VERSION_NAME;
    }
}