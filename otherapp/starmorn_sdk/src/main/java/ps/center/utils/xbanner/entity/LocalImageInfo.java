package ps.center.utils.xbanner.entity;

import androidx.annotation.DrawableRes;

/* loaded from: classes.jar:ps/center/utils/xbanner/entity/LocalImageInfo.class */
public class LocalImageInfo extends SimpleBannerInfo {

    @DrawableRes
    private int bannerRes;

    public LocalImageInfo(int bannerRes) {
        this.bannerRes = bannerRes;
    }

    @Override // ps.center.utils.xbanner.entity.BaseBannerInfo
    public Integer getXBannerUrl() {
        return Integer.valueOf(this.bannerRes);
    }
}