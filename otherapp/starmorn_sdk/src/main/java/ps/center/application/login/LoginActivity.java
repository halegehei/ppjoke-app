package ps.center.application.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import androidx.annotation.NonNull;
import ps.center.application.BuildConfig;
import ps.center.R;
import ps.center.application.config.ApplicationConfig;
import ps.center.application.manager.WeChat;
import ps.center.business.BusinessConstant;
import ps.center.centerinterface.bean.CreateApp;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.databinding.BusinessActivityLoginBinding;
import ps.center.library.http.base.Result;
import ps.center.utils.ManifestUtils;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.utils.UIUtils;
import ps.center.utils.ui.OnClickListener;
import ps.center.views.DataChanger.DataChangeCallBack;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;
import ps.center.views.activity.webview.NativeWebActivity;

/* loaded from: classes.jar:ps/center/application/login/LoginActivity.class */
public class LoginActivity extends BaseActivityVB<BusinessActivityLoginBinding> implements View.OnClickListener, DataChangeCallBack {
    public static final int RESULT_CODE = 901;
    private boolean isCheckbox = false;

    public static void start(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, (Class<?>) LoginActivity.class);
        intent.putExtra("requestCode", requestCode);
        activity.startActivityForResult(intent, requestCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityLoginBinding getLayout() {
        return BusinessActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        ((BusinessActivityLoginBinding) this.binding).loginBtn.setOnClickListener(this);
        DataChangeManager.get().registerChangCallBack(this);
        ((BusinessActivityLoginBinding) this.binding).icon.setImageResource(UIUtils.stringToResourceId(Super.getContext(), "mipmap", ManifestUtils.getMetaDataValue(Super.getContext(), "ICON_R")));
        ((BusinessActivityLoginBinding) this.binding).name.setText(ManifestUtils.getMetaDataValue(Super.getContext(), "APP_NAME", BuildConfig.VERSION_NAME));
        setTipsListener();
        ((BusinessActivityLoginBinding) this.binding).backImage.setImageResource(ApplicationConfig.getSettingConfig().loginPageThemeBackDrawable);
        ((BusinessActivityLoginBinding) this.binding).name.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeTextColor));
        ((BusinessActivityLoginBinding) this.binding).title.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeTextColor));
        ((BusinessActivityLoginBinding) this.binding).loginBtnText.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeBtnTextColor));
        ((BusinessActivityLoginBinding) this.binding).loginBtn.setBackgroundResource(ApplicationConfig.getSettingConfig().loginPageThemeBtnDrawable);
        ((BusinessActivityLoginBinding) this.binding).loginBtnStartDrawable.setImageResource(ApplicationConfig.getSettingConfig().loginOneKeyWechatIcon);
        ((BusinessActivityLoginBinding) this.binding).returnBtn.setImageResource(ApplicationConfig.getSettingConfig().loginPageReturnBtnDrawable);
        this.isCheckbox = BusinessConstant.getConfig().standard_conf.login_mode.func.is_check.equals("1");
        if (this.isCheckbox) {
            ((BusinessActivityLoginBinding) this.binding).checkbox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxSelectImage);
        } else {
            ((BusinessActivityLoginBinding) this.binding).checkbox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxDefaultImage);
        }
        ((BusinessActivityLoginBinding) this.binding).checkbox.setOnClickListener(new OnClickListener() { // from class: ps.center.application.login.LoginActivity.1
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                LoginActivity.this.isCheckbox = !LoginActivity.this.isCheckbox;
                if (LoginActivity.this.isCheckbox) {
                    ((BusinessActivityLoginBinding) LoginActivity.this.binding).checkbox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxSelectImage);
                } else {
                    ((BusinessActivityLoginBinding) LoginActivity.this.binding).checkbox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxDefaultImage);
                }
            }
        });
        ((BusinessActivityLoginBinding) this.binding).returnBtn.setOnClickListener(new OnClickListener() { // from class: ps.center.application.login.LoginActivity.2
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                LoginActivity.this.finish();
            }
        });
    }

    private void setTipsListener() {
        SpannableString textSpanned1 = new SpannableString("登录即同意并已阅读本产品的");
        SpannableString textSpanned2 = new SpannableString("《用户协议》");
        SpannableString textSpanned4 = new SpannableString("和");
        SpannableString textSpanned3 = new SpannableString("《隐私政策》");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) textSpanned1);
        spannableStringBuilder.append((CharSequence) textSpanned2);
        spannableStringBuilder.append((CharSequence) textSpanned4);
        spannableStringBuilder.append((CharSequence) textSpanned3);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: ps.center.application.login.LoginActivity.3
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View widget) {
                NativeWebActivity.jump(LoginActivity.this, "用户协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.user_agreement);
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() { // from class: ps.center.application.login.LoginActivity.4
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View widget) {
                NativeWebActivity.jump(LoginActivity.this, "隐私政策", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.privacy_policy);
            }
        };
        spannableStringBuilder.setSpan(clickableSpan, 13, 19, 34);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 13, 19, 33);
        spannableStringBuilder.setSpan(clickableSpan2, 20, 26, 34);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 20, 26, 33);
        ((BusinessActivityLoginBinding) this.binding).tipsText.setMovementMethod(LinkMovementMethod.getInstance());
        ((BusinessActivityLoginBinding) this.binding).tipsText.setText(spannableStringBuilder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        if (view.getId() == R.id.login_btn) {
            if (this.isCheckbox) {
                WeChat.getInstance().login(WeChat.Type.LOGIN_DEFAULT);
            } else {
                ToastUtils.show(this, "请先阅读并勾选以下协议～");
            }
        }
    }

    @Override // ps.center.views.DataChanger.DataChangeCallBack
    public void change(int var1, Object var2) {
        if (var1 == 2) {
            String weChatCode = (String) var2;
            CenterHttp.get().weChatLogin(weChatCode, new Result<CreateApp>() { // from class: ps.center.application.login.LoginActivity.5
                @Override // ps.center.library.http.base.Result
                public void success(CreateApp obj) {
                    CenterHttp.get().getUserInfo(new Result<User>() { // from class: ps.center.application.login.LoginActivity.5.1
                        @Override // ps.center.library.http.base.Result
                        public void success(User obj2) {
                            DataChangeManager.get().change(3, BuildConfig.VERSION_NAME);
                            DataChangeManager.get().change(1, BuildConfig.VERSION_NAME);
                            LoginActivity.this.finish();
                        }
                    });
                }
            });
        }
    }

    public void finish() {
        if (CenterConstant.getUser().isSign) {
            setResult(RESULT_CODE);
        } else {
            setResult(-1);
        }
        if (!CenterConstant.getUser().isSign) {
            DataChangeManager.get().change(4, BuildConfig.VERSION_NAME);
        }
        DataChangeManager.get().unregisterChangCallBack(this);
        super.finish();
    }
}