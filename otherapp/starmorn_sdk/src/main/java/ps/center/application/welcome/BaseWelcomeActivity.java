package ps.center.application.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import com.bumptech.glide.Glide;
import ps.center.adsdk.ADManager;
import ps.center.adsdk.ADTask;
import ps.center.adsdk.LaunchType;
import ps.center.application.BuildConfig;
import ps.center.application.guide.GuideActivity;
import ps.center.application.manager.PayManager;
import ps.center.application.utils.UmengUtil;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.business.http.base.BusHttp;
import ps.center.business.utils.free.FreeManager;
import ps.center.business.utils.free.FreeReceiveStatus;
import ps.center.centerinterface.bean.CreateApp;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.databinding.BusinessActivityWelcomeBinding;
import ps.center.library.http.base.Result;
import ps.center.utils.ActivityUtils;
import ps.center.utils.ChannelUtils;
import ps.center.utils.LogUtils;
import ps.center.utils.Permission;
import ps.center.utils.Save;
import ps.center.utils.StoreUtils;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.views.ViewsLibraryConfig;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;

/* loaded from: classes.jar:ps/center/application/welcome/BaseWelcomeActivity.class */
public abstract class BaseWelcomeActivity extends BaseActivityVB<BusinessActivityWelcomeBinding> {
    public static String a = "myClassName";

    protected abstract Class<? extends BaseActivityVB<? extends ViewBinding>> setMainActivityClass();

    protected abstract void preInitAndGoNextPager(NextAction nextAction);

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityWelcomeBinding getLayout() {
        return BusinessActivityWelcomeBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        Class<?> mainActivityClass = setMainActivityClass();
        String name = mainActivityClass.getName();
        Save.instance.put("mainActivityClassName", name);
        getConfig();
    }

    public void getConfig() {
        BusHttp.config().getConfig(new Result<AppConfig>() { // from class: ps.center.application.welcome.BaseWelcomeActivity.1
            @Override // ps.center.library.http.base.Result
            public void success(AppConfig obj) {
                BaseWelcomeActivity.this.preLoadGuidePageImage();
                boolean permissionDialogShow = Save.instance.getBoolean("permissionDialogShow", false);
                if (permissionDialogShow) {
                    BaseWelcomeActivity.this.initUMEng(!StoreUtils.isStorePacket());
                } else {
                    BaseWelcomeActivity.this.showPermissionDialog();
                }
            }

            @Override // ps.center.library.http.base.Result
            public void err(int code, String message) {
                if (code != 200) {
                    ToastUtils.show(Super.getContext(), message);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void showPermissionDialog() {
        new PermissionDialog(this, call -> {
            if (call.equals("cancel")) {
                ActivityUtils.jumpPhoneHome(this);
            } else if (call.equals("submit")) {
                Save.instance.put("permissionDialogShow", (Object) true);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    boolean isRequestPermission = BusinessConstant.getConfig().standard_conf.premission_list.comm.is_active.equals("1") && BusinessConstant.getConfig().standard_conf.premission_list.func.permission_us.equals("0");
                    if (ChannelUtils.isStorePacket()) {
                        initUMEng(false);
                    } else if (isRequestPermission) {
                        requestPermissions(new String[]{"android.permission.READ_PHONE_STATE"}, 200);
                    } else {
                        initUMEng(false);
                    }
                }, 250L);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void initUMEng(boolean isReceiveImeiParams) {
        ADManager.initAndEmpower(this);
        UmengUtil umengUtil = new UmengUtil(this, isReceiveImeiParams, this::createUser);
        umengUtil.build();
    }

    public void createUser(String mobile_brand, String mobile_os_version) {
        CenterHttp.get().createApp(mobile_brand, mobile_os_version, new Result<CreateApp>() { // from class: ps.center.application.welcome.BaseWelcomeActivity.2
            @Override // ps.center.library.http.base.Result
            public void success(CreateApp obj) {
                BaseWelcomeActivity.this.getUserInfo();
            }
        });
    }

    public void getUserInfo() {
        CenterHttp.get().getUserInfo(new Result<User>() { // from class: ps.center.application.welcome.BaseWelcomeActivity.3
            @Override // ps.center.library.http.base.Result
            public void success(User obj) {
                BusHttp.config().getConfig(new Result<AppConfig>() { // from class: ps.center.application.welcome.BaseWelcomeActivity.3.1
                    @Override // ps.center.library.http.base.Result
                    public void success(AppConfig obj2) {
                        if (!CenterConstant.getUser().isVip && obj2.standard_conf.free_control.comm.is_active.equals("1") && obj2.standard_conf.free_control.func.free_zdtry_sw.equals("1")) {
                            FreeManager.get().receiveFreeUser(new FreeManager.ReceiveFreeVipListener() { // from class: ps.center.application.welcome.BaseWelcomeActivity.3.1.1
                                @Override // ps.center.business.utils.free.FreeManager.ReceiveFreeVipListener
                                public void receiveFreeUserSuccess(FreeReceiveStatus freeReceiveStatus) {
                                    LogUtils.e("主动领取免费试用成功");
                                }

                                @Override // ps.center.business.utils.free.FreeManager.ReceiveFreeVipListener
                                public void receiveFreeUserErr(FreeReceiveStatus freeReceiveStatus) {
                                    try {
                                        LogUtils.e("主动领取免费试用失败:" + freeReceiveStatus.name());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        BaseWelcomeActivity.this.preLoadGuidePageImage();
                        if (obj2.standard_conf.premission_list.comm.is_active.equals("1")) {
                            ViewsLibraryConfig.IS_OPEN_ALL_FLAG_SECURE = !obj2.standard_conf.premission_list.func.prtsc_sw.equals("1");
                        }
                        BaseWelcomeActivity.this.loadStartAd();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void loadStartAd() {
        ADManager.loadKaiPingAd(this, LaunchType.StartTheAdLoadFirst, new ADTask.CallBack() { // from class: ps.center.application.welcome.BaseWelcomeActivity.4
            @Override // ps.center.adsdk.ADTask.CallBack
            public void show() {
            }

            @Override // ps.center.adsdk.ADTask.CallBack
            public void over() {
                BaseWelcomeActivity.this.jumpGuide();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpGuide() {
        preInitAndGoNextPager(() -> {
            boolean isGuideWelcomePager = Save.instance.getBoolean("isGuideWelcomePager", false);
            if (isGuideWelcomePager) {
                if (CenterConstant.getUser().isVip) {
                    jumpMainActivity();
                    return;
                } else if (BusinessConstant.getConfig().standard_conf.pay_page.comm.is_active.equals("1") && BusinessConstant.getConfig().standard_conf.pay_page.func.prepay_sw.equals("1")) {
                    customPayAction();
                    return;
                } else {
                    jumpMainActivity();
                    return;
                }
            }
            if (BusinessConstant.getConfig().standard_conf.guide_page.comm.is_active.equals("1") && BusinessConstant.getConfig().standard_conf.guide_page.func.guide_with_type.size() > 0) {
                jumpCustomGuidePager();
                return;
            }
            if (CenterConstant.getUser().isVip) {
                jumpMainActivity();
            } else if (BusinessConstant.getConfig().standard_conf.pay_page.comm.is_active.equals("1") && BusinessConstant.getConfig().standard_conf.pay_page.func.prepay_sw.equals("1")) {
                customPayAction();
            } else {
                jumpMainActivity();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void jumpMainActivity() {
        String mainActivityClassName = Save.instance.getString("mainActivityClassName", BuildConfig.VERSION_NAME);
        try {
            Class<?> mainActivityClass = Class.forName(mainActivityClassName);
            Intent intent = new Intent((Context) this, mainActivityClass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {
            Permission permission = Permission.newInstance(this);
            Permission.Status status = permission.checkResult(permissions, grantResults);
            switch (status) {
                case OK:
                    initUMEng(true);
                    break;
                case NO:
                case EVER_NO:
                    initUMEng(false);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preLoadGuidePageImage() {
        try {
            AppConfig.StandardConfBean.GuidePageBean guide_page = BusinessConstant.getConfig().standard_conf.guide_page;
            String imageUrl = guide_page.func.guide_with_type.get(0).data;
            Glide.with(Super.getContext()).load(imageUrl).submit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void customPayAction() {
        new PayManager(this, "前置付费", 0).go();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void jumpCustomGuidePager() {
        GuideActivity.start(this);
    }
}