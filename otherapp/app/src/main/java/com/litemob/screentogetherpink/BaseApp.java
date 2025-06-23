package com.litemob.screentogetherpink;



import java.net.URLDecoder;
import java.util.HashMap;

import ps.center.application.BaseApplication;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.utils.free.FreeManager;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.library.http.base.HttpManager;
import ps.center.utils.LogUtils;
import ps.center.utils.ManifestUtils;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;


public class BaseApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        init();

        ApplicationConfig.with(getApplicationContext())
//        所有SDK的弹窗取消按钮都叫  default_cancel_btn_bg   所有同意按钮都叫   default_btn_bg

                //隐私弹窗设置
                .setPermissionDialogCancelDrawable(R.drawable.default_cancel_btn_bg)// 隐私协议取消按钮drawable
                .setPermissionDialogCancelTextColor("#000000")// 隐私协议取消按钮文字颜色
                .setPermissionDialogSubmitDrawable(R.drawable.default_btn_bg)// 隐私协议同意按钮drawable
                .setPermissionDialogSubmitTextColor("#ffffff")//  隐私协议弹窗同意按钮颜色
                .setPermissionDialogBackColor("#ffffff")// 隐私协议弹窗背景颜色
                .setPermissionDialogTitleColor("#333333")// 隐私协议标题颜色
                .setPermissionDialogContentTextColor("#666666")// 隐私协议内容颜色
                .setPermissionDialogClickTextColor("#666666")// 协议点击区域文案颜色
                //引导页设置
                .setGuidePageSubmitDrawable(R.drawable.default_btn_bg)//引导页下一步按钮drawable
                .setGuidePageSubmitTextColor("#ffffff")// 引导页下一步按钮文案颜色
//                .setGuidePageDefaultImage(R.mipmap.img_guide_default)//引导页默认兜底图片
                .setGuidePageSubmitOffsetX(0.1F)// 设置引导页按钮居底部的偏移值，以屏幕宽度为参考值  0.1f是屏幕宽度*0.1
                .setGuidePageSubmitText("下一步")//设置引导页按钮文案
//                .setGuidePageSubmitTextColor("#FFFFFF")//设置引导页按钮文案颜色

                //个人中心设置
                .setMinePageThemeColor("#F7F7F7")// 个人中心主题背景色
                .setMineTopThemeImage(R.mipmap.img_mine_top)// 个人中心顶部image
                .setMineVipImage(R.mipmap.vip_banner)// 个人中心VipBanner图片
                .setMineNicknameColor("#333333")// 个人中心用户昵称颜色
                .setMineUserInfoJumpBtnDrawable(R.mipmap.img_mine_right)//已登录右边个箭头
                .setMineVipBtnDrawable(R.drawable.bg_vip_go)// 个人中心vipBanner的开通按钮背景
                .setMineUserDefaultIcon(R.mipmap.img_mine_avatar)// 个人中心默认头像图片
                .setMineVipTitleTextColor("#ffffff")//个人中心VipBanner Title颜色
                .setMineVipTipsTextColor("#ffffff")//个人中心VipBanner Content颜色
                .setMineVipBtnTextColor("#DE2F6C")// 个人中心vipBanner的开通按钮文字颜色
                .setMineVipBannerLogoIcon(R.mipmap.img_mine_vip_left)// 个人中心VipBannerLogo
                .setMineListTextColor("#1A1A1A")// 个人中心列表文字颜色
//                .setMineListItemJumpIcon(R.mipmap.img_mine_item_right)//我的页面item箭头
                .setMineListFeedbackIcon(R.mipmap.img_mine_comment)// 个人中心图标 - 问题反馈
                .setMineListAboutIcon(R.mipmap.img_mine_aboat)// 个人中心图标 - 关于我们
                .setMineListShareIcon(R.mipmap.img_mine_share)// 个人中心图标 - 邀请好友
                .setMineListOnlineServiceIcon(R.mipmap.web_service)// 个人中心图标 - 在线客服
                .setMineListPhoneServiceIcon(R.mipmap.phone_service)// 个人中心图标 - 电话客服
                .setMineListScoreIcon(R.mipmap.img_mine_score)// 个人中心图标 - 欢迎评分
                .setMineListFreePlayIcon(R.mipmap.img_mine_free)// 个人中心图标 - 免费试玩
                .setMineListIdentityAuthenticationIcon(R.mipmap.img_mine_identity)//个人中心图标 - 实名认证
                .setMineBeiAnTextColor("#333333")// 个人中心备案信息颜色
                //客服设置
                .setServiceDialogCopyBtnDrawable(R.drawable.default_btn_bg)// 客服弹窗复制按钮Drawable
                .setServiceDialogCopyBtnTextColor("#ffffff")//客服弹窗复制按钮文案颜色
                .setServiceDialogBackColor("#ffffff")// 客服弹窗背景色
                .setServiceDialogContentColor("#666666")// 客服弹窗内容色
                .setServiceDialogCloseBtnImage(R.mipmap.img_dialog_close)//弹窗关闭按钮
                .setScoreDialogBackImage(R.mipmap.img_score_top_bg)//评分弹窗顶部

                //登录设置
                .setLoginPageThemeBackDrawable(R.drawable.bg_login_bg)// 登录页面背景
                .setLoginPageThemeTextColor("#333333")// 登录页文字颜色
                .setLoginPageThemeBtnTextColor("#ffffff")// 登录页按钮文字颜色
                .setLoginOneKeyWechatBtnDrawable(R.drawable.default_btn_bg)// 登录页微信登录按钮颜色
                .setLoginOneKeyWechatIcon(R.mipmap.img_login_btn_wechat)// 登录页微信登录按钮图标
                .setLoginOneKeyWechatTextColor("#ffffff")//登录页微信登录按钮文字颜色
                .setLoginPageThemeBtnDrawable(R.drawable.default_btn_bg)// 登录页按钮Drawable背景
                .setLoginSMSPageEditBackDrawable(R.drawable.bg_edit_)// 验证码登录页EditText背景色
                .setLoginSMSPageEditTextColor("#333333")
//                .setLoginPhoneNumberIcon(R.mipmap.img_login_phone)// 验证码登录页手机号编辑框前面的logo
//                .setLoginCodeNumberIcon(R.mipmap.img_login_code)// 验证码登录页验证码编辑框前面的logo
                .setLoginCheckBoxSelectImage(R.mipmap.login_checkbox_on)//设置登录页权限选中checkbox
                .setLoginCheckBoxDefaultImage(R.mipmap.login_checkbox_off)//设置登录页权限默认checkbox
//                .setLoginPageReturnBtnDrawable(R.mipmap.icon_back_w)//设置登录页面返回按钮
//                .setLoginOneKeyNumberTextColor("#FFFFFF")//设置一键登录电话号码的颜色

                //支付页设置
//                .setPayPageDefaultBackImage(R.mipmap.mine_page_top_image)// 支付页兜底图
//                .setPayLoginBtnBackDrawable(R.mipmap.mine_page_top_image)//付费页登录按钮背景
//                .setPayIsShowCheckbox(true)//付费页面是否显示可勾选的协议
                .setPayItemListOffsetTopLocation(1.05f)// 付费页item布局居顶部的偏移位置-------
                .setPayPageDefaultBackColor("#ffffff")// 支付页背景色
                .setPaySubmitBtnTextColor("#FFFFFF")// 支付页支付按钮文字颜色
                .setPayOtherTextColor("#333333")//支付页其他文案颜色
                .setPayBigTitleFreeDayTextColor("#000000")  // 设置付费页大标签FreeDay文案颜色
                .setPaySpareLabelTextColor("#333333")       // 设置付费页大标签下面的备用标签文案颜色
                .setPayFreeDayTextColor("#1468DD") //支付页FreeDay文案颜色  -----不展示支付的时候该位置的文案颜色
                .setPayDingYueTextColor("#999999") // 支付页订阅说明文案颜色
                .setPayAgreementTextColor("#666666")// 支付页协议文案颜色
                .setPayTypeWeChatTextColor("#333333")//支付页微信支付方式的文案颜色
                .setPayTypeZFBTextColor("#333333")//支付页支付宝支付方式的文案颜色
                .setPayLoginBtnTextColor("#ffffff")//付费页登录按钮文字颜色
                .setPayPageDanMuIsShow(false)// 付费页弹幕信息是否显示
//                .setPayPageDanMuItemHeaderImageIsShow(true)//付费页弹幕头像是否显示
//                .setPayPageDanMuItemDrawable(R.drawable.pay_dan_mu_item_bg)//付费页弹幕头像是否显示
//                .setPayPageDanMuItemTextColor("#8D3E41")//付费页弹幕文字颜色
                .setPaySubmitBtnDrawable(R.drawable.default_btn_bg)// 支付页按钮Drawable
                //单价格
                .setPay1ItemSelectTitleTextColor("#784F00")// 支付页1价格选中Item标题颜色
                .setPay1ItemSelectPriceTextColor("#FF3F22")//支付页1价格选中Item价钱颜色
                .setPay1ItemSelectBottomTextColor("#784F00")//支付页1价格选中ItemBottom颜色
                .setPay1ItemDefaultTitleTextColor("#333333")// 支付页1价格默认Item标题颜色
                .setPay1ItemDefaultPriceTextColor("#333333")// 支付页1价格默认Item价钱颜色
                .setPay1ItemDefaultBottomTextColor("#333333")// 支付页1价格默认ItemBottom颜色
                .setPay1ItemSelectBackRes(R.drawable.bg_pay_y)// 1价格被选中的item背景
                .setPay1ItemDefaultBackRes(R.drawable.bg_pay_n)//1价格默认的item背景
                //三价铬
                .setPay3ItemSelectTitleTextColor("#784F00")//支付页3价格选中Item标题颜色
                .setPay3ItemSelectPriceTextColor("#FF3F22")//支付页3价格选中Item价钱颜色
                .setPay3ItemSelectBottomTextColor("#784F00")//支付页3价格选中ItemBottom颜色
                .setPay3ItemDefaultTitleTextColor("#333333")//支付页3价格默认Item标题颜色
                .setPay3ItemDefaultPriceTextColor("#333333")//支付页3价格默认Item价钱颜色
                .setPay3ItemDefaultBottomTextColor("#333333")// 支付页3价格默认ItemBottom颜色
                .setPay3ItemSelectBackRes(R.mipmap.img_pay_3_y)// 3价格被选中的item背景
                .setPay3ItemDefaultBackRes(R.mipmap.img_pay_3_n)//3价格默认的item背景

                .build();

        //免费设置
        FreeManager.get().setDialogColors("#ffffff", "#000000", "#000000", "#0686FF", "#ffffff");

    }


    private void init() {
        ToastUtils.setToastTime(1500);

        LogUtils.DEBUG = BaseConstant.DEBUG;
        ps.center.library.http.LogUtils.DEBUG = BaseConstant.DEBUG;

        HttpManager.initHttp(new HttpManager.Headers() {
            @Override
            public HashMap<String, String> add(HashMap<String, String> hashMap) {
                long uid = Save.instance.getLong(CenterConstant.UID, 0L);
                hashMap.put("uid", uid == 0 ? "" + 0 : uid + "");
//                hashMap.put("uid", "45660346");
                hashMap.put("param", Save.instance.getString("param", ""));
                hashMap.put("app_id", ManifestUtils.getMetaDataValue(Super.getContext(), "CENTER_APP_ID", ""));
                hashMap.put("version", Super.getSelfVersion());
                hashMap.put("packet", ManifestUtils.getMetaDataValue(Super.getContext(), "PACKET", ""));
                hashMap.put("os", "1");
                if (BaseConstant.DEBUG) {
                    hashMap.put("money", "100");
                    hashMap.put("is_trial", "2");
                } else {
                    if (FreeManager.get().isFreeUser()) {
                        hashMap.put("money", "5");
                    } else {
                        hashMap.put("money", FreeManager.get().getHttpHeaderToMoney());
                    }
                    hashMap.put("is_trial", FreeManager.get().getHttpHeaderToIsTrial());
                }
                hashMap.put("access_token", Save.instance.getString("access_token", ""));
                hashMap.put("Content-Type", "application/json;charset=utf-8");
                return hashMap;
            }
        }, new HttpManager.Encrypt() {
            @Override
            public String deCode(String s) {
                String deCode;
                try {
                    deCode =s;
                    if (BaseConstant.DEBUG) {
                        LogUtils.e(deCode);
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                    deCode = "";
                }
                return deCode;
            }
        });
    }
}
