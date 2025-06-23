package ps.center.application.login;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.mobile.auth.gatewayauth.AuthRegisterXmlConfig;
import com.mobile.auth.gatewayauth.AuthUIConfig;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.TokenResultListener;
import com.mobile.auth.gatewayauth.ui.AbstractPnsViewDelegate;
import java.util.Map;
import ps.center.application.BuildConfig;
import ps.center.R;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.centerinterface.bean.CreateApp;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.library.http.base.Result;
import ps.center.utils.LogUtils;
import ps.center.utils.ManifestUtils;
import ps.center.utils.Super;
import ps.center.utils.UIUtils;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.dialog.loading.LoadingDialog;

/* loaded from: classes.jar:ps/center/application/login/LoginManager.class */
public class LoginManager {
    public static PhoneNumberAuthHelper instance;
    private static LoadingDialog loadingDialog;

    public static void login(final Activity context, final int requestCode) {
        if (CenterConstant.getUser().isSign) {
            UserInfoActivity.start(context);
            return;
        }
        if (BusinessConstant.getConfig().standard_conf.login_mode.func.mode.equals("1") || BusinessConstant.getConfig().standard_conf.login_mode.func.mode.equals("5")) {
            LoginActivity.start(context, requestCode);
            return;
        }
        if (BusinessConstant.getConfig().standard_conf.login_mode.func.mode.equals("4")) {
            LoginSMSActivity.start(context, requestCode);
            return;
        }
        instance = PhoneNumberAuthHelper.getInstance(context, new TokenResultListener() { // from class: ps.center.application.login.LoginManager.1
            public void onTokenSuccess(String s) {
                Handler handler = new Handler(Looper.getMainLooper());
                Activity activity = context;
                int i = requestCode;
                handler.post(() -> {
                    LogUtils.e("一键登录Success：" + s);
                    Gson gson = new Gson();
                    try {
                        Map map = (Map) gson.fromJson(s, Map.class);
                        if (map.get("code") != null) {
                            if (map.get("code").equals("600024")) {
                                LoginManager.instance.getLoginToken(activity, 5000);
                            } else if (map.get("code").equals("700002")) {
                                if (LoginManager.loadingDialog == null) {
                                    LoadingDialog unused = LoginManager.loadingDialog = new LoadingDialog(activity);
                                }
                                LoginManager.loadingDialog.show();
                            } else if (map.get("code").equals("600000")) {
                                String tokenStr = (String) map.get("token");
                                CenterHttp.get().oneKeyLogin(tokenStr, new Result<CreateApp>() { // from class: ps.center.application.login.LoginManager.1.1
                                    @Override // ps.center.library.http.base.Result
                                    public void success(@Nullable CreateApp obj) {
                                        CenterHttp.get().getUserInfo(new Result<User>() { // from class: ps.center.application.login.LoginManager.1.1.1
                                            @Override // ps.center.library.http.base.Result
                                            public void success(@Nullable User obj2) {
                                                DataChangeManager.get().change(1, "login success");
                                                DataChangeManager.get().change(3, "login success");
                                                if (LoginManager.loadingDialog != null && LoginManager.loadingDialog.isShowing()) {
                                                    LoginManager.loadingDialog.dismiss();
                                                }
                                                LoginManager.instance.quitLoginPage();
                                            }
                                        });
                                    }
                                });
                            } else if (map.get("code").equals("600002") || map.get("code").equals("600007") || map.get("code").equals("600008") || map.get("code").equals("600005")) {
                                LoginManager.instance.quitLoginPage();
                                LoginSMSActivity.start(activity, i);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            public void onTokenFailed(String s) {
                LoginManager.instance.quitLoginPage();
                if (!s.contains("700000")) {
                    LoginSMSActivity.start(context, requestCode);
                }
            }
        });
        AuthUIConfig.Builder authUIConfig = new AuthUIConfig.Builder();
        authUIConfig.setStatusBarHidden(false);
        authUIConfig.setStatusBarColor(Color.parseColor("#000000"));
        String ICON = ManifestUtils.getMetaDataValue(Super.getContext(), "ICON_R", BuildConfig.VERSION_NAME);
        authUIConfig.setNavHidden(true);
        authUIConfig.setSwitchAccHidden(true);
        authUIConfig.setLogBtnText("本机一键登录");
        authUIConfig.setLogoHeight(114);
        authUIConfig.setLogoWidth(114);
        authUIConfig.setLogoOffsetY(50);
        authUIConfig.setLogoImgPath(ICON);
        authUIConfig.setAppPrivacyOne("《用户协议》", BusinessConstant.getConfig().standard_conf.agreement_content.func.user_agreement);
        authUIConfig.setAppPrivacyTwo("《隐私政策》", BusinessConstant.getConfig().standard_conf.agreement_content.func.privacy_policy);
        authUIConfig.setSloganText(BuildConfig.VERSION_NAME);
        authUIConfig.setLogBtnHeight(55);
        authUIConfig.setLogBtnWidth(265);
        authUIConfig.setLogBtnBackgroundDrawable(Super.getContext().getDrawable(ApplicationConfig.getSettingConfig().loginPageThemeBtnDrawable));
        authUIConfig.setLogBtnTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeBtnTextColor));
        authUIConfig.setCheckedImgDrawable(Super.getContext().getDrawable(ApplicationConfig.getSettingConfig().loginCheckBoxSelectImage));
        authUIConfig.setUncheckedImgDrawable(Super.getContext().getDrawable(ApplicationConfig.getSettingConfig().loginCheckBoxDefaultImage));
        authUIConfig.setCheckBoxWidth(25);
        authUIConfig.setCheckBoxHeight(25);
        authUIConfig.setLogBtnOffsetY(400);
        authUIConfig.setNumFieldOffsetY(280);
        authUIConfig.setSloganOffsetY(320);
        authUIConfig.setSloganTextSizeDp(14);
        authUIConfig.setNumberColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginOneKeyNumberTextColor));
        authUIConfig.setAppPrivacyColor(Color.parseColor("#666666"), Color.parseColor("#666666"));
        authUIConfig.setVendorPrivacyPrefix("《");
        authUIConfig.setVendorPrivacySuffix("》");
        AuthRegisterXmlConfig.Builder builder = new AuthRegisterXmlConfig.Builder();
        builder.setLayout(R.layout.business_more_login_layout, new AbstractPnsViewDelegate() { // from class: ps.center.application.login.LoginManager.2
            public void onViewCreated(View view) {
                LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
                layout.setBackgroundResource(ApplicationConfig.getSettingConfig().loginOneKeyWechatBtnDrawable);
                ImageView wechatIcon = (ImageView) view.findViewById(R.id.wechatIcon);
                wechatIcon.setImageResource(ApplicationConfig.getSettingConfig().loginOneKeyWechatIcon);
                ImageView bg = (ImageView) view.findViewById(R.id.bg);
                bg.setImageResource(ApplicationConfig.getSettingConfig().loginPageThemeBackDrawable);
                ImageView back_btn = (ImageView) view.findViewById(R.id.back_btn);
                back_btn.setImageResource(ApplicationConfig.getSettingConfig().loginPageReturnBtnDrawable);
                TextView title = (TextView) view.findViewById(R.id.title);
                title.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeTextColor));
                TextView wx_login = (TextView) view.findViewById(R.id.wx_login);
                wx_login.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginOneKeyWechatTextColor));
                ImageView icon = (ImageView) view.findViewById(R.id.icon);
                int iconId = UIUtils.stringToResourceId(Super.getContext(), "mipmap", ManifestUtils.getMetaDataValue(Super.getContext(), "ICON_R"));
                icon.setImageResource(iconId);
                TextView tv_app_name = (TextView) view.findViewById(R.id.tv_app_name);
                tv_app_name.setText(String.format("- - %s - -", ManifestUtils.getMetaDataValue(Super.getContext(), "APP_NAME")));
                tv_app_name.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeTextColor));
                TextView phone_login = (TextView) view.findViewById(R.id.phone_login);
                phone_login.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginOneKeyOtherLoginTextColor));
                Activity activity = context;
                int i = requestCode;
                phone_login.setOnClickListener(v -> {
                    LoginManager.instance.quitLoginPage();
                    LoginSMSActivity.start(activity, i);
                });
                View findViewById = findViewById(R.id.layout);
                Activity activity2 = context;
                int i2 = requestCode;
                findViewById.setOnClickListener(v2 -> {
                    LoginManager.instance.quitLoginPage();
                    LoginActivity.start(activity2, i2);
                });
                findViewById(R.id.back_btn).setOnClickListener(v3 -> {
                    LoginManager.instance.quitLoginPage();
                });
            }
        });
        instance.addAuthRegisterXmlConfig(builder.build());
        instance.setAuthUIConfig(authUIConfig.create());
        instance.setAuthSDKInfo(BusinessConstant.getConfig().config.login_onekey.loginonekey_key);
        instance.checkEnvAvailable(2);
    }
}