package ps.center.application.config;

import android.content.Context;
import androidx.annotation.Size;
import com.google.gson.Gson;
import ps.center.application.BuildConfig;
import ps.center.utils.LogUtils;
import ps.center.utils.Save;

/* loaded from: classes.jar:ps/center/application/config/ApplicationConfig.class */
public class ApplicationConfig {
    private static final String SAVE_TAG = "Application:SettingConfig";
    private static final Object look = new Object();
    private static SettingConfig settingConfig;

    public static Builder with(Context context) {
        return new Builder(context);
    }

    public static SettingConfig getSettingConfig() {
        SettingConfig settingConfig2;
        synchronized (look) {
            if (settingConfig == null) {
                try {
                    String string = Save.instance.getString(SAVE_TAG, BuildConfig.VERSION_NAME);
                    if (string.equals(BuildConfig.VERSION_NAME)) {
                        settingConfig = new SettingConfig();
                    } else {
                        settingConfig = (SettingConfig) new Gson().fromJson(string, SettingConfig.class);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    settingConfig = new SettingConfig();
                }
            }
            settingConfig2 = settingConfig;
        }
        return settingConfig2;
    }

    public static void clear() {
        settingConfig = null;
    }

    /* loaded from: classes.jar:ps/center/application/config/ApplicationConfig$Builder.class */
    public static final class Builder {
        private Context context;
        private SettingConfig settingConfig = new SettingConfig();

        public Builder(Context context) {
            this.context = context;
        }

        @Deprecated
        public Builder setWelcomeThemeDrawable(int welcomeThemeDrawable) {
            this.settingConfig.welcomeThemeDrawable = welcomeThemeDrawable;
            return this;
        }

        public Builder setPermissionDialogContent(String permissionDialogContent) {
            this.settingConfig.permissionDialogContent = permissionDialogContent;
            return this;
        }

        public Builder setPermissionDialogCancelDrawable(int permissionDialogCancelDrawable) {
            this.settingConfig.permissionDialogCancelDrawable = permissionDialogCancelDrawable;
            return this;
        }

        public Builder setPermissionDialogCancelTextColor(@Size(min = 7) String permissionDialogCancelTextColor) {
            this.settingConfig.permissionDialogCancelTextColor = permissionDialogCancelTextColor;
            return this;
        }

        public Builder setPermissionDialogSubmitDrawable(int permissionDialogSubmitDrawable) {
            this.settingConfig.permissionDialogSubmitDrawable = permissionDialogSubmitDrawable;
            return this;
        }

        public Builder setPermissionDialogSubmitTextColor(@Size(min = 7) String permissionDialogSubmitTextColor) {
            this.settingConfig.permissionDialogSubmitTextColor = permissionDialogSubmitTextColor;
            return this;
        }

        public Builder setPermissionDialogBackColor(@Size(min = 7) String permissionDialogBackColor) {
            this.settingConfig.permissionDialogBackColor = permissionDialogBackColor;
            return this;
        }

        public Builder setPermissionDialogTitleColor(@Size(min = 7) String permissionDialogTitleColor) {
            this.settingConfig.permissionDialogTitleColor = permissionDialogTitleColor;
            return this;
        }

        public Builder setPermissionDialogContentTextColor(@Size(min = 7) String permissionDialogContentTextColor) {
            this.settingConfig.permissionDialogContentTextColor = permissionDialogContentTextColor;
            return this;
        }

        public Builder setPermissionDialogClickTextColor(@Size(min = 7) String permissionDialogClickTextColor) {
            this.settingConfig.permissionDialogClickTextColor = permissionDialogClickTextColor;
            return this;
        }

        public Builder setGuidePageSubmitDrawable(int guidePageSubmitDrawable) {
            this.settingConfig.guidePageSubmitDrawable = guidePageSubmitDrawable;
            return this;
        }

        public Builder setGuidePageSubmitOffsetX(float guidePageSubmitOffsetX) {
            this.settingConfig.guidePageSubmitOffsetX = guidePageSubmitOffsetX;
            return this;
        }

        public Builder setGuidePageSubmitTextColor(@Size(min = 7) String guidePageSubmitTextColor) {
            this.settingConfig.guidePageSubmitTextColor = guidePageSubmitTextColor;
            return this;
        }

        public Builder setGuidePageSubmitText(String guidePageSubmitText) {
            this.settingConfig.guidePageSubmitText = guidePageSubmitText;
            return this;
        }

        public Builder setGuidePageDefaultImage(int guidePageDefaultImage) {
            this.settingConfig.guidePageDefaultImage = guidePageDefaultImage;
            return this;
        }

        public Builder setMinePageThemeColor(@Size(min = 7) String minePageThemeColor) {
            this.settingConfig.minePageThemeColor = minePageThemeColor;
            return this;
        }

        public Builder setMineTopThemeImage(int mineTopThemeImage) {
            this.settingConfig.mineTopThemeImage = mineTopThemeImage;
            return this;
        }

        public Builder setMineVipImage(int mineVipImage) {
            this.settingConfig.mineVipImage = mineVipImage;
            return this;
        }

        public Builder setMineNicknameColor(@Size(min = 7) String mineNicknameColor) {
            this.settingConfig.mineNicknameColor = mineNicknameColor;
            return this;
        }

        public Builder setMineUserInfoJumpBtnDrawable(int mineUserInfoJumpBtnDrawable) {
            this.settingConfig.mineUserInfoJumpBtnDrawable = mineUserInfoJumpBtnDrawable;
            return this;
        }

        public Builder setMineVipBtnDrawable(int mineVipBtnDrawable) {
            this.settingConfig.mineVipBtnDrawable = mineVipBtnDrawable;
            return this;
        }

        public Builder setMineUserDefaultIcon(int mineUserDefaultIcon) {
            this.settingConfig.mineUserDefaultIcon = mineUserDefaultIcon;
            return this;
        }

        public Builder setMineVipTitleTextColor(@Size(min = 7) String mineVipTitleTextColor) {
            this.settingConfig.mineVipTitleTextColor = mineVipTitleTextColor;
            return this;
        }

        public Builder setMineVipTipsTextColor(@Size(min = 7) String mineVipTipsTextColor) {
            this.settingConfig.mineVipTipsTextColor = mineVipTipsTextColor;
            return this;
        }

        public Builder setMineVipBtnTextColor(@Size(min = 7) String mineVipBtnTextColor) {
            this.settingConfig.mineVipBtnTextColor = mineVipBtnTextColor;
            return this;
        }

        public Builder setMineVipBannerLogoIcon(int mineVipBannerLogoIcon) {
            this.settingConfig.mineVipBannerLogoIcon = mineVipBannerLogoIcon;
            return this;
        }

        public Builder setMineListTextColor(@Size(min = 7) String mineListTextColor) {
            this.settingConfig.mineListTextColor = mineListTextColor;
            return this;
        }

        public Builder setMineListItemJumpIcon(int mineListItemJumpIcon) {
            this.settingConfig.mineListItemJumpIcon = mineListItemJumpIcon;
            return this;
        }

        public Builder setMineListMarkColor(@Size(min = 7) String mineListMarkColor) {
            this.settingConfig.mineListMarkColor = mineListMarkColor;
            return this;
        }

        public Builder setMineListTitleColor(@Size(min = 7) String mineListTitleColor) {
            this.settingConfig.mineListTitleColor = mineListTitleColor;
            return this;
        }

        public Builder setMineListBackColor(@Size(min = 7) String mineListBackColor) {
            this.settingConfig.mineListBackColor = mineListBackColor;
            return this;
        }

        public Builder setMineListFeedbackIcon(int mineListFeedbackIcon) {
            this.settingConfig.mineListFeedbackIcon = mineListFeedbackIcon;
            return this;
        }

        public Builder setMineListAboutIcon(int mineListAboutIcon) {
            this.settingConfig.mineListAboutIcon = mineListAboutIcon;
            return this;
        }

        public Builder setMineListShareIcon(int mineListShareIcon) {
            this.settingConfig.mineListShareIcon = mineListShareIcon;
            return this;
        }

        public Builder setMineListScoreIcon(int mineListScoreIcon) {
            this.settingConfig.mineListScoreIcon = mineListScoreIcon;
            return this;
        }

        public Builder setMineListOnlineServiceIcon(int mineListOnlineServiceIcon) {
            this.settingConfig.mineListOnlineServiceIcon = mineListOnlineServiceIcon;
            return this;
        }

        public Builder setMineListPhoneServiceIcon(int mineListPhoneServiceIcon) {
            this.settingConfig.mineListPhoneServiceIcon = mineListPhoneServiceIcon;
            return this;
        }

        public Builder setMineListFreePlayIcon(int mineListFreePlayIcon) {
            this.settingConfig.mineListFreePlayIcon = mineListFreePlayIcon;
            return this;
        }

        public Builder setMineListIdentityAuthenticationIcon(int mineListIdentityAuthenticationIcon) {
            this.settingConfig.mineListIdentityAuthenticationIcon = mineListIdentityAuthenticationIcon;
            return this;
        }

        public Builder setMineListComplaintIcon(int mineListComplaintIcon) {
            this.settingConfig.mineListComplaintIcon = mineListComplaintIcon;
            return this;
        }

        public Builder setMineBeiAnTextColor(@Size(min = 7) String mineBeiAnTextColor) {
            this.settingConfig.mineBeiAnTextColor = mineBeiAnTextColor;
            return this;
        }

        public Builder setLoginPageThemeBackDrawable(int loginPageThemeBackDrawable) {
            this.settingConfig.loginPageThemeBackDrawable = loginPageThemeBackDrawable;
            return this;
        }

        public Builder setLoginPageThemeTextColor(@Size(min = 7) String loginPageThemeTextColor) {
            this.settingConfig.loginPageThemeTextColor = loginPageThemeTextColor;
            return this;
        }

        public Builder setLoginPageThemeBtnTextColor(@Size(min = 7) String loginPageThemeBtnTextColor) {
            this.settingConfig.loginPageThemeBtnTextColor = loginPageThemeBtnTextColor;
            return this;
        }

        public Builder setLoginOneKeyWechatBtnDrawable(int loginOneKeyWechatBtnDrawable) {
            this.settingConfig.loginOneKeyWechatBtnDrawable = loginOneKeyWechatBtnDrawable;
            return this;
        }

        public Builder setLoginOneKeyWechatIcon(int loginOneKeyWechatIcon) {
            this.settingConfig.loginOneKeyWechatIcon = loginOneKeyWechatIcon;
            return this;
        }

        public Builder setLoginOneKeyWechatTextColor(@Size(min = 7) String loginOneKeyWechatTextColor) {
            this.settingConfig.loginOneKeyWechatTextColor = loginOneKeyWechatTextColor;
            return this;
        }

        public Builder setLoginOneKeyNumberTextColor(@Size(min = 7) String loginOneKeyNumberTextColor) {
            this.settingConfig.loginOneKeyNumberTextColor = loginOneKeyNumberTextColor;
            return this;
        }

        public Builder setLoginOneKeyOtherLoginTextColor(@Size(min = 7) String loginOneKeyOtherLoginTextColor) {
            this.settingConfig.loginOneKeyOtherLoginTextColor = loginOneKeyOtherLoginTextColor;
            return this;
        }

        public Builder setLoginPageThemeBtnDrawable(int loginPageThemeBtnDrawable) {
            this.settingConfig.loginPageThemeBtnDrawable = loginPageThemeBtnDrawable;
            return this;
        }

        public Builder setLoginPageReturnBtnDrawable(int loginPageReturnBtnDrawable) {
            this.settingConfig.loginPageReturnBtnDrawable = loginPageReturnBtnDrawable;
            return this;
        }

        public Builder setLoginSMSPageEditBackDrawable(int loginSMSPageEditBackDrawable) {
            this.settingConfig.loginSMSPageEditBackDrawable = loginSMSPageEditBackDrawable;
            return this;
        }

        public Builder setLoginSMSPageEditTextColor(@Size(min = 7) String loginSMSPageEditTextColor) {
            this.settingConfig.loginSMSPageEditTextColor = loginSMSPageEditTextColor;
            return this;
        }

        public Builder setLoginPhoneNumberIcon(int loginPhoneNumberIcon) {
            this.settingConfig.loginPhoneNumberIcon = loginPhoneNumberIcon;
            return this;
        }

        public Builder setLoginCodeNumberIcon(int loginCodeNumberIcon) {
            this.settingConfig.loginCodeNumberIcon = loginCodeNumberIcon;
            return this;
        }

        public Builder setLoginCheckBoxSelectImage(int loginCheckBoxSelectImage) {
            this.settingConfig.loginCheckBoxSelectImage = loginCheckBoxSelectImage;
            return this;
        }

        public Builder setLoginCheckBoxDefaultImage(int loginCheckBoxDefaultImage) {
            this.settingConfig.loginCheckBoxDefaultImage = loginCheckBoxDefaultImage;
            return this;
        }

        public Builder setServiceDialogTitleText(String serviceDialogTitleText) {
            this.settingConfig.serviceDialogTitleText = serviceDialogTitleText;
            return this;
        }

        public Builder setServiceDialogCopyBtnDrawable(int serviceDialogCopyBtnDrawable) {
            this.settingConfig.serviceDialogCopyBtnDrawable = serviceDialogCopyBtnDrawable;
            return this;
        }

        public Builder setServiceDialogCopyBtnTextColor(@Size(min = 7) String serviceDialogCopyBtnTextColor) {
            this.settingConfig.serviceDialogCopyBtnTextColor = serviceDialogCopyBtnTextColor;
            return this;
        }

        public Builder setServiceDialogBackColor(@Size(min = 7) String serviceDialogBackColor) {
            this.settingConfig.serviceDialogBackColor = serviceDialogBackColor;
            return this;
        }

        public Builder setServiceDialogContentColor(@Size(min = 7) String serviceDialogContentColor) {
            this.settingConfig.serviceDialogContentColor = serviceDialogContentColor;
            return this;
        }

        public Builder setServiceDialogCloseBtnImage(int serviceDialogCloseBtnImage) {
            this.settingConfig.serviceDialogCloseBtnImage = serviceDialogCloseBtnImage;
            return this;
        }

        public Builder setPayPageDefaultBackImage(int payPageDefaultBackImage) {
            this.settingConfig.payPageDefaultBackImage = payPageDefaultBackImage;
            return this;
        }

        public Builder setPayPageDefaultBackColor(@Size(min = 7) String payPageDefaultBackColor) {
            this.settingConfig.payPageDefaultBackColor = payPageDefaultBackColor;
            return this;
        }

        public Builder setPaySubmitBtnTextColor(@Size(min = 7) String paySubmitBtnTextColor) {
            this.settingConfig.paySubmitBtnTextColor = paySubmitBtnTextColor;
            return this;
        }

        public Builder setPayOtherTextColor(@Size(min = 7) String payOtherTextColor) {
            this.settingConfig.payOtherTextColor = payOtherTextColor;
            return this;
        }

        public Builder setPayFreeDayTextColor(@Size(min = 7) String payFreeDayTextColor) {
            this.settingConfig.payFreeDayTextColor = payFreeDayTextColor;
            return this;
        }

        public Builder setPayBigTitleFreeDayTextColor(@Size(min = 7) String payBigTitleFreeDayTextColor) {
            this.settingConfig.payBigTitleFreeDayTextColor = payBigTitleFreeDayTextColor;
            return this;
        }

        public Builder setPaySpareLabelTextColor(@Size(min = 7) String paySpareLabelTextColor) {
            this.settingConfig.paySpareLabelTextColor = paySpareLabelTextColor;
            return this;
        }

        public Builder setPayDingYueTextColor(@Size(min = 7) String payDingYueTextColor) {
            this.settingConfig.payDingYueTextColor = payDingYueTextColor;
            return this;
        }

        public Builder setPayTitleTextIsShow(boolean payTitleTextIsShow) {
            this.settingConfig.payTitleTextIsShow = payTitleTextIsShow;
            return this;
        }

        public Builder setPayTitleTextColor(@Size(min = 7) String payTitleTextColor) {
            this.settingConfig.payTitleTextColor = payTitleTextColor;
            return this;
        }

        public Builder setPayListIsHide(boolean payListIsHide) {
            this.settingConfig.payListIsHide = payListIsHide;
            return this;
        }

        public Builder setPayTitleText(String payTitleText) {
            this.settingConfig.payTitleText = payTitleText;
            return this;
        }

        public Builder setPayTitleTextNotAction(String payTitleTextNotAction) {
            this.settingConfig.payTitleTextNotAction = payTitleTextNotAction;
            return this;
        }

        public Builder setPayAgreementTextColor(@Size(min = 7) String payAgreementTextColor) {
            this.settingConfig.payAgreementTextColor = payAgreementTextColor;
            return this;
        }

        public Builder setPay1ItemSelectTitleTextColor(@Size(min = 7) String pay1ItemSelectTitleTextColor) {
            this.settingConfig.pay1ItemSelectTitleTextColor = pay1ItemSelectTitleTextColor;
            return this;
        }

        public Builder setPay1ItemSelectPriceTextColor(@Size(min = 7) String pay1ItemSelectPriceTextColor) {
            this.settingConfig.pay1ItemSelectPriceTextColor = pay1ItemSelectPriceTextColor;
            return this;
        }

        public Builder setPay1ItemSelectBottomTextColor(@Size(min = 7) String pay1ItemSelectBottomTextColor) {
            this.settingConfig.pay1ItemSelectBottomTextColor = pay1ItemSelectBottomTextColor;
            return this;
        }

        public Builder setPay1ItemDefaultTitleTextColor(@Size(min = 7) String pay1ItemDefaultTitleTextColor) {
            this.settingConfig.pay1ItemDefaultTitleTextColor = pay1ItemDefaultTitleTextColor;
            return this;
        }

        public Builder setPay1ItemDefaultPriceTextColor(@Size(min = 7) String pay1ItemDefaultPriceTextColor) {
            this.settingConfig.pay1ItemDefaultPriceTextColor = pay1ItemDefaultPriceTextColor;
            return this;
        }

        public Builder setPay1ItemDefaultBottomTextColor(@Size(min = 7) String pay1ItemDefaultBottomTextColor) {
            this.settingConfig.pay1ItemDefaultBottomTextColor = pay1ItemDefaultBottomTextColor;
            return this;
        }

        public Builder setPay3ItemSelectTitleTextColor(@Size(min = 7) String pay3ItemSelectTitleTextColor) {
            this.settingConfig.pay3ItemSelectTitleTextColor = pay3ItemSelectTitleTextColor;
            return this;
        }

        public Builder setPay3ItemSelectPriceTextColor(@Size(min = 7) String pay3ItemSelectPriceTextColor) {
            this.settingConfig.pay3ItemSelectPriceTextColor = pay3ItemSelectPriceTextColor;
            return this;
        }

        public Builder setPay3ItemSelectBottomTextColor(@Size(min = 7) String pay3ItemSelectBottomTextColor) {
            this.settingConfig.pay3ItemSelectBottomTextColor = pay3ItemSelectBottomTextColor;
            return this;
        }

        public Builder setPay3ItemDefaultTitleTextColor(@Size(min = 7) String pay3ItemDefaultTitleTextColor) {
            this.settingConfig.pay3ItemDefaultTitleTextColor = pay3ItemDefaultTitleTextColor;
            return this;
        }

        public Builder setPay3ItemDefaultPriceTextColor(@Size(min = 7) String pay3ItemDefaultPriceTextColor) {
            this.settingConfig.pay3ItemDefaultPriceTextColor = pay3ItemDefaultPriceTextColor;
            return this;
        }

        public Builder setPay3ItemDefaultBottomTextColor(@Size(min = 7) String pay3ItemDefaultBottomTextColor) {
            this.settingConfig.pay3ItemDefaultBottomTextColor = pay3ItemDefaultBottomTextColor;
            return this;
        }

        public Builder setPay1ItemSelectBackRes(int pay1ItemSelectBackRes) {
            this.settingConfig.pay1ItemSelectBackRes = pay1ItemSelectBackRes;
            return this;
        }

        public Builder setPay1ItemDefaultBackRes(int pay1ItemDefaultBackRes) {
            this.settingConfig.pay1ItemDefaultBackRes = pay1ItemDefaultBackRes;
            return this;
        }

        public Builder setPay3ItemSelectBackRes(int pay3ItemSelectBackRes) {
            this.settingConfig.pay3ItemSelectBackRes = pay3ItemSelectBackRes;
            return this;
        }

        public Builder setPay3ItemDefaultBackRes(int pay3ItemDefaultBackRes) {
            this.settingConfig.pay3ItemDefaultBackRes = pay3ItemDefaultBackRes;
            return this;
        }

        public Builder setPayTypeWeChatTextColor(@Size(min = 7) String payTypeWeChatTextColor) {
            this.settingConfig.payTypeWeChatTextColor = payTypeWeChatTextColor;
            return this;
        }

        public Builder setPayTypeZFBTextColor(@Size(min = 7) String payTypeZFBTextColor) {
            this.settingConfig.payTypeZFBTextColor = payTypeZFBTextColor;
            return this;
        }

        @Deprecated
        public Builder setPayPageDanMuIsShow(boolean payPageDanMuIsShow) {
            this.settingConfig.payPageDanMuIsShow = payPageDanMuIsShow;
            this.settingConfig.payPageDanMuMaxLine = 2;
            return this;
        }

        public Builder setPayLoginBtnTextColor(@Size(min = 7) String payLoginBtnTextColor) {
            this.settingConfig.payLoginBtnTextColor = payLoginBtnTextColor;
            return this;
        }

        public Builder setPayLoginBtnBackDrawable(int payLoginBtnBackDrawable) {
            this.settingConfig.payLoginBtnBackDrawable = payLoginBtnBackDrawable;
            return this;
        }

        public Builder setPayItemListOffsetTopLocation(float payItemListOffsetTopLocation) {
            this.settingConfig.payItemListOffsetTopLocation = payItemListOffsetTopLocation;
            return this;
        }

        public Builder setPayPageDanMuItemHeaderImageIsShow(boolean payPageDanMuItemHeaderImageIsShow) {
            this.settingConfig.payPageDanMuItemHeaderImageIsShow = payPageDanMuItemHeaderImageIsShow;
            return this;
        }

        public Builder setPayPageDanMuItemDrawable(int payPageDanMuItemDrawable) {
            this.settingConfig.payPageDanMuItemDrawable = payPageDanMuItemDrawable;
            return this;
        }

        public Builder setPayPageDanMuMaxLine(int payPageDanMuMaxLine) {
            this.settingConfig.payPageDanMuMaxLine = payPageDanMuMaxLine;
            return this;
        }

        public Builder setPayPageDanMuItemTextColor(@Size(min = 7) String payPageDanMuItemTextColor) {
            this.settingConfig.payPageDanMuItemTextColor = payPageDanMuItemTextColor;
            return this;
        }

        public Builder setPaySubmitBtnDrawable(int paySubmitBtnDrawable) {
            this.settingConfig.paySubmitBtnDrawable = paySubmitBtnDrawable;
            return this;
        }

        @Deprecated
        public Builder setPayIsShowCheckbox(boolean payIsShowCheckbox) {
            this.settingConfig.payIsShowCheckbox = payIsShowCheckbox;
            return this;
        }

        public Builder payPageSurePayDialogBtnBg(int payPageSurePayDialogBtnBg) {
            this.settingConfig.payPageSurePayDialogBtnBg = payPageSurePayDialogBtnBg;
            return this;
        }

        public Builder setScoreDialogBackImage(int scoreDialogBackImage) {
            this.settingConfig.scoreDialogBackImage = scoreDialogBackImage;
            return this;
        }

        public Builder setCouponsPayChannel(boolean isCouponsPayChannel) {
            this.settingConfig.isCouponsPayChannel = isCouponsPayChannel ? 1 : 0;
            return this;
        }

        public Builder setRunUMEng(boolean isRunUMEng) {
            this.settingConfig.isRunUMEng = isRunUMEng;
            return this;
        }

        public void build() {
            Save.instance.put(ApplicationConfig.SAVE_TAG, new Gson().toJson(this.settingConfig));
            String string = Save.instance.getString(ApplicationConfig.SAVE_TAG, BuildConfig.VERSION_NAME);
            LogUtils.e(string);
        }
    }
}