package ps.center.application.mine.v2;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.List;
import ps.center.application.BuildConfig;
import ps.center.R;
import ps.center.application.config.ApplicationConfig;
import ps.center.application.config.SettingConfig;
import ps.center.application.login.LoginManager;
import ps.center.application.manager.IdentityAuthenticationManager;
import ps.center.application.manager.PayManager;
import ps.center.application.manager.WeChat;
import ps.center.application.mine.ComplaintActivity;
import ps.center.application.mine.FeedbackActivity;
import ps.center.application.mine.MoreSettingActivity;
import ps.center.application.mine.ScoreDialog;
import ps.center.application.mine.ServiceDialog;
import ps.center.application.mine.SettingBean;
import ps.center.application.mine.SettingListAdapter;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.common.IdentityAuthentication;
import ps.center.business.bean.config.AppConfig;
import ps.center.business.bean.share.ShareBean;
import ps.center.business.http.base.BusHttp;
import ps.center.business.utils.free.FreeManager;
import ps.center.business.utils.free.FreeReceiveStatus;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.databinding.BusinessFragmentMineV2Binding;
import ps.center.library.http.base.Result;
import ps.center.utils.ChannelUtils;
import ps.center.utils.ManifestUtils;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.TimeUtils;
import ps.center.utils.ToastUtils;
import ps.center.views.DataChanger.DataChangeCallBack;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.activity.webview.NativeWebActivity;
import ps.center.views.fragment.BaseFragmentVB;
import ps.center.views.fragment.BundleGet;

/* loaded from: classes.jar:ps/center/application/mine/v2/MineFragmentV2.class */
public class MineFragmentV2 extends BaseFragmentVB<BusinessFragmentMineV2Binding> implements DataChangeCallBack, FreeManager.FreeTimeOverListener {
    private SettingListAdapter settingListAdapter;
    private SettingConfig settingConfig;
    private List<SettingBean> listData;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.fragment.BaseFragmentVB
    public BusinessFragmentMineV2Binding getLayout() {
        return BusinessFragmentMineV2Binding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.fragment.BaseFragmentVB
    protected void initData(BundleGet bundleGet) {
        this.settingConfig = ApplicationConfig.getSettingConfig();
        this.listData = new ArrayList();
        this.settingListAdapter = new SettingListAdapter(getContext(), R.layout.business_item_mine_settings_layout_v2, this.settingConfig, this.listData);
        ((BusinessFragmentMineV2Binding) this.binding).settings.setLayoutManager(new GridLayoutManager(getContext(), 3));
        ((BusinessFragmentMineV2Binding) this.binding).settings.setAdapter(this.settingListAdapter);
        AppConfig.StandardConfBean.PayPageBean pay_page = BusinessConstant.getConfig().standard_conf.pay_page;
        if (pay_page.comm.is_active.equals("1")) {
            if (pay_page.func.backpay_sw.equals("1")) {
                ((BusinessFragmentMineV2Binding) this.binding).vipBannerLayout.setVisibility(View.VISIBLE);
            } else {
                ((BusinessFragmentMineV2Binding) this.binding).vipBannerLayout.setVisibility(View.GONE);
            }
        } else {
            ((BusinessFragmentMineV2Binding) this.binding).vipBannerLayout.setVisibility(View.GONE);
        }
        ((BusinessFragmentMineV2Binding) this.binding).rootLayout.setBackgroundColor(Color.parseColor(this.settingConfig.minePageThemeColor));
        ((BusinessFragmentMineV2Binding) this.binding).topLayout.setImageResource(this.settingConfig.mineTopThemeImage);
        ((BusinessFragmentMineV2Binding) this.binding).vipBannerLayout.setBackgroundResource(this.settingConfig.mineVipImage);
        ((BusinessFragmentMineV2Binding) this.binding).vipBannerBtn.setTextColor(Color.parseColor(this.settingConfig.mineVipBtnTextColor));
        ((BusinessFragmentMineV2Binding) this.binding).vipBannerBtn.setBackgroundResource(this.settingConfig.mineVipBtnDrawable);
        ((BusinessFragmentMineV2Binding) this.binding).vipBannerTitle.setTextColor(Color.parseColor(this.settingConfig.mineVipTitleTextColor));
        ((BusinessFragmentMineV2Binding) this.binding).vipBannerDesc.setTextColor(Color.parseColor(this.settingConfig.mineVipTipsTextColor));
        ((BusinessFragmentMineV2Binding) this.binding).userName.setTextColor(Color.parseColor(this.settingConfig.mineNicknameColor));
        ((BusinessFragmentMineV2Binding) this.binding).userInfoGotoImg.setImageResource(this.settingConfig.mineUserInfoJumpBtnDrawable);
        ((BusinessFragmentMineV2Binding) this.binding).minePageSettingsBack.setBackgroundColor(Color.parseColor(this.settingConfig.mineListBackColor));
        ((BusinessFragmentMineV2Binding) this.binding).minePageSettingsTitleMark.setBackgroundColor(Color.parseColor(this.settingConfig.mineListMarkColor));
        ((BusinessFragmentMineV2Binding) this.binding).minePageSettingsTitle.setTextColor(Color.parseColor(this.settingConfig.mineListTitleColor));
        AppConfig.StandardConfBean.LoginModeBean login_mode = BusinessConstant.getConfig().standard_conf.login_mode;
        if (login_mode.comm.is_active.equals("1")) {
            ((BusinessFragmentMineV2Binding) this.binding).userInfoLayout.setVisibility(View.VISIBLE);
        } else {
            ((BusinessFragmentMineV2Binding) this.binding).userInfoLayout.setVisibility(View.GONE);
        }
        if (this.settingConfig.mineVipBannerLogoIcon == 0) {
            ((BusinessFragmentMineV2Binding) this.binding).vipBannerLogo.setVisibility(View.GONE);
            ((BusinessFragmentMineV2Binding) this.binding).paddingView.setVisibility(View.VISIBLE);
        } else {
            ((BusinessFragmentMineV2Binding) this.binding).vipBannerLogo.setVisibility(View.VISIBLE);
            ((BusinessFragmentMineV2Binding) this.binding).paddingView.setVisibility(View.GONE);
            ((BusinessFragmentMineV2Binding) this.binding).vipBannerLogo.setImageResource(this.settingConfig.mineVipBannerLogoIcon);
        }
        setUserData(CenterConstant.getUser());
        DataChangeManager.get().registerChangCallBack(this);
        FreeManager.get().registerFreeTimeOverListener(this);
    }

    @Override // ps.center.views.fragment.BaseFragmentVB
    protected void setListener() {
        ((BusinessFragmentMineV2Binding) this.binding).userInfoLayout.setOnClickListener(v -> {
            if (getActivity() != null) {
                LoginManager.login(getActivity(), 0);
            }
        });
        ((BusinessFragmentMineV2Binding) this.binding).vipBannerLayout.setOnClickListener(v2 -> {
            if (CenterConstant.getUser().vip_time != 4092595200L && getActivity() != null) {
                customPayAction();
            }
        });
        this.settingListAdapter.setOnItemClickListener((position, item) -> {
            switch (item.name) {
                case "问题反馈":
                    if (getActivity() != null) {
                        FeedbackActivity.start(getActivity());
                        break;
                    }
                    break;
                case "关于我们":
                    MoreSettingActivity.start(getActivity());
                    break;
                case "邀请好友":
                    BusHttp.share().getShareInfo("1", BusinessConstant.getConfig().standard_conf.share_control.func.share_id, new Result<ShareBean>() { // from class: ps.center.application.mine.v2.MineFragmentV2.1
                        @Override // ps.center.library.http.base.Result
                        public void success(ShareBean obj) {
                            WeChat.getInstance().shareToChat(obj);
                        }

                        @Override // ps.center.library.http.base.Result
                        public void err(int code, String message) {
                            ToastUtils.show(Super.getContext(), "邀请未配置，请检查");
                        }
                    });
                    break;
                case "欢迎评分":
                    new ScoreDialog(getContext()).show();
                    break;
                case "在线客服":
                    if (!ChannelUtils.isStorePacket()) {
                        if (BusinessConstant.getConfig().standard_conf.customer_service.func.is_vip.equals("1")) {
                            if (CenterConstant.getUser().isVip) {
                                jumpWebServerPager();
                                break;
                            } else {
                                ToastUtils.show(Super.getContext(), "Vip专属特权");
                                break;
                            }
                        } else {
                            jumpWebServerPager();
                            break;
                        }
                    } else {
                        jumpWebServerPager();
                        break;
                    }
                case "电话客服":
                    if (!ChannelUtils.isStorePacket()) {
                        if (BusinessConstant.getConfig().standard_conf.customer_service.func.is_vip.equals("1")) {
                            if (CenterConstant.getUser().isVip) {
                                jumpPhoneServerPager();
                                break;
                            } else {
                                ToastUtils.show(Super.getContext(), "Vip专属特权");
                                break;
                            }
                        } else {
                            jumpPhoneServerPager();
                            break;
                        }
                    } else {
                        jumpPhoneServerPager();
                        break;
                    }
                case "免费试玩":
                    FreeManager.get().receiveFreeUser(new FreeManager.ReceiveFreeVipListener() { // from class: ps.center.application.mine.v2.MineFragmentV2.2
                        @Override // ps.center.business.utils.free.FreeManager.ReceiveFreeVipListener
                        public void receiveFreeUserSuccess(FreeReceiveStatus freeReceiveStatus) {
                            ToastUtils.show(Super.getContext(), "免费试用资格领取成功");
                        }

                        @Override // ps.center.business.utils.free.FreeManager.ReceiveFreeVipListener
                        public void receiveFreeUserErr(FreeReceiveStatus freeReceiveStatus) {
                            if (freeReceiveStatus == FreeReceiveStatus.VIP_NOT_CAN_RECEIVE) {
                                ToastUtils.show(Super.getContext(), "当前已是VIP，无法领取免费试用");
                            } else if (freeReceiveStatus == FreeReceiveStatus.FREE_RECEIVE_OVER) {
                                ToastUtils.show(Super.getContext(), "您已经领取过免费试用资格，不可重复领取");
                            }
                        }
                    });
                    break;
                case "实名认证":
                    IdentityAuthenticationManager.get().getStatus(getContext());
                    break;
                case "投诉举报":
                    if (getActivity() != null) {
                        ComplaintActivity.start(getActivity());
                        break;
                    }
                    break;
                case "投诉退款":
                    if (getContext() != null) {
                        String url = BusinessConstant.getConfig().standard_conf.complaint_control.func.compain_url;
                        String sb = url + "?uid=" + Save.instance.getLong(CenterConstant.UID, 0L) + "&packet=" + ManifestUtils.getMetaDataValue(Super.getContext(), "PACKET") + "&version=" + Super.getSelfVersion() + "&app_id=" + ManifestUtils.getMetaDataValue(Super.getContext(), "CENTER_APP_ID");
                        NativeWebActivity.jump(getContext(), BusinessConstant.getConfig().standard_conf.complaint_control.comm.conf_name, R.mipmap.business_img_close, sb);
                        break;
                    }
                    break;
            }
        });
    }

    public void customPayAction() {
        new PayManager(getActivity(), "后置付费", 0).go();
    }

    private void setUserData(User userData) {
        this.listData.clear();
        Glide.with(Super.getContext()).load(userData.avatar).placeholder(this.settingConfig.mineUserDefaultIcon).error(this.settingConfig.mineUserDefaultIcon).apply(new RequestOptions().centerCrop().circleCrop()).into(((BusinessFragmentMineV2Binding) this.binding).userIcon);
        if (CenterConstant.getUser().isSign) {
            ((BusinessFragmentMineV2Binding) this.binding).userInfoGotoImg.setVisibility(View.VISIBLE);
        } else {
            ((BusinessFragmentMineV2Binding) this.binding).userInfoGotoImg.setVisibility(View.GONE);
        }
        ((BusinessFragmentMineV2Binding) this.binding).userName.setText(userData.username);
        if (userData.isVip) {
            ((BusinessFragmentMineV2Binding) this.binding).vipBannerBtn.setText("续费");
            if (userData.vip_time == 4092595200L) {
                ((BusinessFragmentMineV2Binding) this.binding).vipBannerTitle.setText("您已是永久超级会员 ");
                ((BusinessFragmentMineV2Binding) this.binding).vipBannerDesc.setText("到期时间:终身使用");
                ((BusinessFragmentMineV2Binding) this.binding).vipBannerBtn.setVisibility(View.GONE);
            } else {
                ((BusinessFragmentMineV2Binding) this.binding).vipBannerTitle.setText("您已是超级会员 ");
                ((BusinessFragmentMineV2Binding) this.binding).vipBannerDesc.setText(String.format("到期时间:%s", TimeUtils.timeToData(userData.vip_time * 1000).split(" ")[0]));
                ((BusinessFragmentMineV2Binding) this.binding).vipBannerBtn.setVisibility(View.VISIBLE);
            }
        } else {
            ((BusinessFragmentMineV2Binding) this.binding).vipBannerBtn.setText("开通");
            ((BusinessFragmentMineV2Binding) this.binding).vipBannerTitle.setText("成为超级会员 ");
            ((BusinessFragmentMineV2Binding) this.binding).vipBannerDesc.setText("享受所有会员权益");
        }
        AppConfig config = BusinessConstant.getConfig();
        this.listData.add(new SettingBean("问题反馈", "问题反馈", this.settingConfig.mineListFeedbackIcon));
        this.listData.add(new SettingBean("关于我们", "关于我们", this.settingConfig.mineListAboutIcon));
        if (config.standard_conf.share_control.comm.is_active.equals("1") && config.standard_conf.share_control.func.share_sw.equals("1")) {
            if (config.standard_conf.share_control.func.is_vip.equals("1")) {
                if (userData.isVip) {
                    this.listData.add(new SettingBean("邀请好友", "邀请好友", this.settingConfig.mineListShareIcon));
                }
            } else {
                this.listData.add(new SettingBean("邀请好友", "邀请好友", this.settingConfig.mineListShareIcon));
            }
        }
        if (config.standard_conf.rating_pop.comm.is_active.equals("1") && config.standard_conf.rating_pop.func.score_sw.equals("1")) {
            if (config.standard_conf.rating_pop.func.is_vip.equals("1")) {
                if (CenterConstant.getUser().isVip) {
                    this.listData.add(new SettingBean("欢迎评分", "欢迎评分", this.settingConfig.mineListScoreIcon));
                }
            } else {
                this.listData.add(new SettingBean("欢迎评分", "欢迎评分", this.settingConfig.mineListScoreIcon));
            }
        }
        if (config.standard_conf.customer_service.comm.is_active.equals("1")) {
            if (config.standard_conf.customer_service.func.online_sw.equals("1")) {
                this.listData.add(new SettingBean("在线客服", "在线客服", this.settingConfig.mineListOnlineServiceIcon));
            }
            if (config.standard_conf.customer_service.func.pop_sw.equals("1")) {
                this.listData.add(new SettingBean("电话客服", "电话客服", this.settingConfig.mineListPhoneServiceIcon));
            }
        }
        if (config.standard_conf.free_control.comm.is_active.equals("1") && !CenterConstant.getUser().isVip && (config.standard_conf.free_control.func.free_time_sw.equals("1") || config.standard_conf.free_control.func.free_num_sw.equals("1"))) {
            this.listData.add(new SettingBean("免费试玩", "免费试玩", this.settingConfig.mineListFreePlayIcon));
        }
        if (config.standard_conf.complaint_control.comm.is_active.equals("1") && config.standard_conf.complaint_control.func.authentication_sw.equals("1")) {
            this.listData.add(new SettingBean("实名认证", "实名认证", this.settingConfig.mineListIdentityAuthenticationIcon));
        }
        if (!config.standard_conf.complaint_control.comm.is_active.equals("1") || config.standard_conf.complaint_control.func.tip_off.equals("1")) {
        }
        if (config.standard_conf.complaint_control.comm.is_active.equals("1") && config.standard_conf.complaint_control.func.compain_sw.equals("1")) {
            this.listData.add(new SettingBean(config.standard_conf.complaint_control.comm.conf_name, "投诉退款", config.standard_conf.complaint_control.func.compain_icon));
        }
        this.settingListAdapter.notifyDataSetChanged();
        if (config.standard_conf.complaint_control.comm.is_active.equals("1") && config.standard_conf.complaint_control.func.authentication_sw.equals("1")) {
            getIdentityAuthentication();
        }
    }

    private void jumpPhoneServerPager() {
        if (getContext() != null) {
            new ServiceDialog(getContext(), BusinessConstant.getConfig().standard_conf.customer_service.func.customer_serv).show();
        }
    }

    private void jumpWebServerPager() {
        if (getContext() != null) {
            NativeWebActivity.jump(getContext(), "客服", 0, BusinessConstant.getConfig().standard_conf.customer_service.func.online_service_info);
        }
    }

    private void getIdentityAuthentication() {
        String id_card_name = Save.instance.getString("id_card_name", BuildConfig.VERSION_NAME);
        String id_card_number = Save.instance.getString("id_card_number", BuildConfig.VERSION_NAME);
        BusHttp.common().identityAuthentication(id_card_name, id_card_number, new Result<IdentityAuthentication>() { // from class: ps.center.application.mine.v2.MineFragmentV2.3
            @Override // ps.center.library.http.base.Result
            public void success(IdentityAuthentication obj) {
                super.success(obj);
                MineFragmentV2.this.settingListAdapter.setIdentityAuthentication(obj.isAuthentication());
                MineFragmentV2.this.settingListAdapter.notifyDataSetChanged();
                Save.instance.put("IdentityAuthenticationStatus", Boolean.valueOf(obj.isAuthentication()));
            }

            @Override // ps.center.library.http.base.Result
            public void err(int code, String message) {
                super.err(code, message);
            }
        });
    }

    public void onResume() {
        super.onResume();
        setUserData(CenterConstant.getUser());
    }

    @Override // ps.center.views.DataChanger.DataChangeCallBack
    public void change(int i, Object o) {
        switch (i) {
            case 1:
                setUserData(CenterConstant.getUser());
                break;
        }
    }

    @Override // ps.center.business.utils.free.FreeManager.FreeTimeOverListener
    public void timeOver() {
        CenterHttp.get().getUserInfo(new Result<User>() { // from class: ps.center.application.mine.v2.MineFragmentV2.4
            @Override // ps.center.library.http.base.Result
            public void success(User obj) {
                DataChangeManager.get().change(1, BuildConfig.VERSION_NAME);
            }
        });
    }
}