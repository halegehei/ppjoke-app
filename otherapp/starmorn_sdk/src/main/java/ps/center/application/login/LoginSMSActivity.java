package ps.center.application.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ps.center.application.BuildConfig;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.centerinterface.bean.CreateApp;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.databinding.BusinessActivityLoginSmsBinding;
import ps.center.library.http.base.Result;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.views.DataChanger.DataChangeCallBack;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;
import ps.center.views.activity.webview.NativeWebActivity;
import ps.center.views.dialog.loading.LoadingDialog;

/* loaded from: classes.jar:ps/center/application/login/LoginSMSActivity.class */
public class LoginSMSActivity extends BaseActivityVB<BusinessActivityLoginSmsBinding> implements DataChangeCallBack {
    private boolean isSelect = false;
    private LoadingDialog loadingDialog;
    private CountDownTimer timer;

    public static void start(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, (Class<?>) LoginSMSActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityLoginSmsBinding getLayout() {
        return BusinessActivityLoginSmsBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        this.isSelect = BusinessConstant.getConfig().standard_conf.login_mode.func.is_check.equals("1");
        setTipsListener();
        ((BusinessActivityLoginSmsBinding) this.binding).checkbox.setImageResource(this.isSelect ? ApplicationConfig.getSettingConfig().loginCheckBoxSelectImage : ApplicationConfig.getSettingConfig().loginCheckBoxDefaultImage);
        ((BusinessActivityLoginSmsBinding) this.binding).backImage.setImageResource(ApplicationConfig.getSettingConfig().loginPageThemeBackDrawable);
        ((BusinessActivityLoginSmsBinding) this.binding).pleasLoginTips.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeTextColor));
        ((BusinessActivityLoginSmsBinding) this.binding).pleasLoginTips2.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeTextColor));
        ((BusinessActivityLoginSmsBinding) this.binding).inputPhoneNumberTips.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeTextColor));
        ((BusinessActivityLoginSmsBinding) this.binding).inputCodeNumberTips.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeTextColor));
        ((BusinessActivityLoginSmsBinding) this.binding).sendCode.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeTextColor));
        ((BusinessActivityLoginSmsBinding) this.binding).phoneNumberLogo.setImageResource(ApplicationConfig.getSettingConfig().loginPhoneNumberIcon);
        ((BusinessActivityLoginSmsBinding) this.binding).codeNumberLogo.setImageResource(ApplicationConfig.getSettingConfig().loginCodeNumberIcon);
        ((BusinessActivityLoginSmsBinding) this.binding).loginBtn.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginPageThemeBtnTextColor));
        ((BusinessActivityLoginSmsBinding) this.binding).loginBtn.setBackgroundResource(ApplicationConfig.getSettingConfig().loginPageThemeBtnDrawable);
        ((BusinessActivityLoginSmsBinding) this.binding).editTextLayout1.setBackgroundResource(ApplicationConfig.getSettingConfig().loginSMSPageEditBackDrawable);
        ((BusinessActivityLoginSmsBinding) this.binding).editTextLayout2.setBackgroundResource(ApplicationConfig.getSettingConfig().loginSMSPageEditBackDrawable);
        ((BusinessActivityLoginSmsBinding) this.binding).phoneNumber.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginSMSPageEditTextColor));
        ((BusinessActivityLoginSmsBinding) this.binding).codeNumber.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().loginSMSPageEditTextColor));
        ((BusinessActivityLoginSmsBinding) this.binding).returnBtn.setImageResource(ApplicationConfig.getSettingConfig().loginPageReturnBtnDrawable);
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void setListener() {
        ((BusinessActivityLoginSmsBinding) this.binding).checkbox.setOnClickListener(v -> {
            this.isSelect = !this.isSelect;
            ((BusinessActivityLoginSmsBinding) this.binding).checkbox.setImageResource(this.isSelect ? ApplicationConfig.getSettingConfig().loginCheckBoxSelectImage : ApplicationConfig.getSettingConfig().loginCheckBoxDefaultImage);
        });
        ((BusinessActivityLoginSmsBinding) this.binding).loginBtn.setOnClickListener(v2 -> {
            if (!this.isSelect) {
                ToastUtils.show(this, "请先勾选同意协议和条款");
            } else {
                gotoLogin();
            }
        });
        ((BusinessActivityLoginSmsBinding) this.binding).sendCode.setOnClickListener(v3 -> {
            if (((BusinessActivityLoginSmsBinding) this.binding).phoneNumber.getText().toString().trim().equals(BuildConfig.VERSION_NAME) || ((BusinessActivityLoginSmsBinding) this.binding).phoneNumber.getText().toString().trim().length() != 11) {
                ToastUtils.show(this, "请输入正确的手机号码");
            } else if (this.timer == null) {
                CenterHttp.get().getPhoneCode(getPhoneNumber(), new Result<Object>() { // from class: ps.center.application.login.LoginSMSActivity.1
                    /* JADX WARN: Type inference failed for: r1v0, types: [ps.center.application.login.LoginSMSActivity$1$1] */
                    @Override // ps.center.library.http.base.Result
                    public void success(Object obj) {
                        LoginSMSActivity.this.timer = new CountDownTimer(60000L, 1000L) { // from class: ps.center.application.login.LoginSMSActivity.1.1
                            @Override // android.os.CountDownTimer
                            @SuppressLint({"SetTextI18n"})
                            public void onTick(long millisUntilFinished) {
                                ((BusinessActivityLoginSmsBinding) LoginSMSActivity.this.binding).sendCode.setText("重新发送(" + (millisUntilFinished / 1000) + ")");
                            }

                            @Override // android.os.CountDownTimer
                            public void onFinish() {
                                if (((BusinessActivityLoginSmsBinding) LoginSMSActivity.this.binding).phoneNumber.getText().toString().trim().equals(BuildConfig.VERSION_NAME)) {
                                    ((BusinessActivityLoginSmsBinding) LoginSMSActivity.this.binding).sendCode.setVisibility(8);
                                } else {
                                    ((BusinessActivityLoginSmsBinding) LoginSMSActivity.this.binding).sendCode.setVisibility(0);
                                }
                                ((BusinessActivityLoginSmsBinding) LoginSMSActivity.this.binding).sendCode.setText("发送验证码");
                                LoginSMSActivity.this.timer = null;
                            }
                        }.start();
                    }

                    @Override // ps.center.library.http.base.Result
                    public void err(int code, String message) {
                        if (code != 200 && code != 0) {
                            ToastUtils.show(LoginSMSActivity.this, message);
                        }
                    }
                });
            } else {
                ToastUtils.show(Super.getContext(), "请稍后再次发送");
            }
        });
        ((BusinessActivityLoginSmsBinding) this.binding).returnBtn.setOnClickListener(v4 -> {
            finish();
        });
        ((BusinessActivityLoginSmsBinding) this.binding).phoneNumber.addTextChangedListener(new TextWatcher() { // from class: ps.center.application.login.LoginSMSActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().equals(BuildConfig.VERSION_NAME)) {
                    if (LoginSMSActivity.this.timer == null) {
                        ((BusinessActivityLoginSmsBinding) LoginSMSActivity.this.binding).sendCode.setVisibility(8);
                        return;
                    } else {
                        ((BusinessActivityLoginSmsBinding) LoginSMSActivity.this.binding).sendCode.setVisibility(0);
                        return;
                    }
                }
                ((BusinessActivityLoginSmsBinding) LoginSMSActivity.this.binding).sendCode.setVisibility(0);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        ((BusinessActivityLoginSmsBinding) this.binding).codeNumber.addTextChangedListener(new TextWatcher() { // from class: ps.center.application.login.LoginSMSActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        ((BusinessActivityLoginSmsBinding) this.binding).codeNumber.setOnEditorActionListener((v5, actionId, event) -> {
            if (actionId == 2) {
                gotoLogin();
                return false;
            }
            return false;
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
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: ps.center.application.login.LoginSMSActivity.4
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View widget) {
                NativeWebActivity.jump(LoginSMSActivity.this, "用户协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.user_agreement);
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() { // from class: ps.center.application.login.LoginSMSActivity.5
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View widget) {
                NativeWebActivity.jump(LoginSMSActivity.this, "隐私政策", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.privacy_policy);
            }
        };
        spannableStringBuilder.setSpan(clickableSpan, 13, 19, 34);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 13, 19, 33);
        spannableStringBuilder.setSpan(clickableSpan2, 20, 26, 34);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 20, 26, 33);
        ((BusinessActivityLoginSmsBinding) this.binding).tipsText.setMovementMethod(LinkMovementMethod.getInstance());
        ((BusinessActivityLoginSmsBinding) this.binding).tipsText.setText(spannableStringBuilder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void gotoLogin() {
        if (getPhoneNumber().equals(BuildConfig.VERSION_NAME) || getPhoneNumber().length() != 11 || ((BusinessActivityLoginSmsBinding) this.binding).codeNumber.getText().toString().trim().equals(BuildConfig.VERSION_NAME)) {
            ToastUtils.show(this, "请输入正确的手机号或验证码");
        } else {
            CenterHttp.get().codeLogin(((BusinessActivityLoginSmsBinding) this.binding).codeNumber.getText().toString().trim(), getPhoneNumber(), new Result<CreateApp>() { // from class: ps.center.application.login.LoginSMSActivity.6
                @Override // ps.center.library.http.base.Result
                public void success(CreateApp obj) {
                    Save.instance.put(CenterConstant.UID, Long.valueOf(obj.uid));
                    CenterHttp.get().getUserInfo(new Result<User>() { // from class: ps.center.application.login.LoginSMSActivity.6.1
                        @Override // ps.center.library.http.base.Result
                        public void success(User obj2) {
                            DataChangeManager.get().change(1, "login success");
                            DataChangeManager.get().change(3, "login success");
                            ToastUtils.show(LoginSMSActivity.this, "登录成功");
                            LoginSMSActivity.this.finish();
                        }
                    });
                }

                @Override // ps.center.library.http.base.Result
                public void err(int code, String message) {
                    ToastUtils.show(LoginSMSActivity.this, "请检查手机号和验证码是否正确");
                }
            });
        }
    }

    public String getPhoneNumber() {
        return ((BusinessActivityLoginSmsBinding) this.binding).phoneNumber.getText().toString().trim();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ps.center.views.DataChanger.DataChangeCallBack
    public void change(int i, Object object) {
        switch (i) {
            case 1:
                if (this.loadingDialog != null) {
                    this.loadingDialog.dismiss();
                }
                finish();
                break;
            case 2:
                if (this.loadingDialog == null) {
                    this.loadingDialog = new LoadingDialog(this);
                }
                this.loadingDialog.show();
                CenterHttp.get().weChatLogin(object.toString(), new Result<CreateApp>() { // from class: ps.center.application.login.LoginSMSActivity.7
                    @Override // ps.center.library.http.base.Result
                    public void success(@Nullable CreateApp obj) {
                        DataChangeManager.get().change(1, "0");
                        LoginSMSActivity.this.loadingDialog.dismiss();
                        LoginSMSActivity.this.finish();
                    }
                });
                break;
        }
    }

    public void finish() {
        setResult(200);
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        DataChangeManager.get().unregisterChangCallBack(this);
    }
}