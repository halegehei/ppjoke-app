package ps.center.application.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.ArrayList;
import java.util.Iterator;
import ps.center.application.BuildConfig;
import ps.center.application.DataChangeEvent.DataChangeStatus;
import ps.center.R;
import ps.center.application.config.AppCustomEvent;
import ps.center.application.config.ApplicationConfig;
import ps.center.application.config.SettingConfig;
import ps.center.application.login.LoginManager;
import ps.center.application.manager.WeChat;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.centerinterface.bean.PayPage;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.databinding.BusinessActivityPayBinding;
import ps.center.library.http.base.Result;
import ps.center.utils.LogUtils;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.utils.ui.OnClickListener;
import ps.center.views.DataChanger.DataChangeCallBack;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;
import ps.center.views.activity.webview.NativeWebActivity;
import ps.center.views.dialog.BaseDialogVB2;
import ps.center.views.layout.recycleAutoPlay.AutoPlayUrlItem;

/* loaded from: classes.jar:ps/center/application/pay/PayActivity.class */
public class PayActivity extends BaseActivityVB<BusinessActivityPayBinding> implements DataChangeCallBack {
    private PayPage.GroupsBean.PriceInfoBean selectPayItem;
    private PayPage.GroupsBean payInfo;
    private PayPage.GroupsBean dialogPayItem;
    private ArrayList<String> showRules;
    private ArrayList<String> dialogShowRules;
    private PayPage.GroupsBean payInfoZfb;
    private PayPage.GroupsBean dialogPayItemZfb;
    private ArrayList<String> showRulesZfb;
    private ArrayList<String> dialogShowRulesZfb;
    private CardPayAdapter cardPayAdapter;
    private SingleAndDoubleAdapter singleAndDoubleAdapter;
    private ExitDialogManager exitPayDialog;
    private CountDownTimer closeCountDownTimer;
    private int selectItemIndex = 0;
    private String action = BuildConfig.VERSION_NAME;
    private boolean isWxPay = true;
    private boolean checkedPermission = false;
    private boolean isDoublePayType = false;
    private boolean hideReturn = false;

    public static void start(Activity activity, PayPage.GroupsBean payInfo, PayPage.GroupsBean dialogPayInfo, ArrayList<String> showRules, ArrayList<String> dialogShowRules, PayPage.GroupsBean payInfoZfb, PayPage.GroupsBean dialogPayInfoZfb, ArrayList<String> showRulesZfb, ArrayList<String> dialogShowRulesZfb, boolean hideReturn, String action, int requestCode) {
        Intent intent = new Intent(activity, (Class<?>) PayActivity.class);
        intent.putExtra("action", action);
        intent.putExtra("payInfo", payInfo);
        intent.putExtra("dialogPayInfo", dialogPayInfo);
        intent.putStringArrayListExtra("showRules", showRules);
        intent.putStringArrayListExtra("dialogShowRules", dialogShowRules);
        intent.putExtra("payInfoZfb", payInfoZfb);
        intent.putExtra("dialogPayInfoZfb", dialogPayInfoZfb);
        intent.putStringArrayListExtra("showRulesZfb", showRulesZfb);
        intent.putStringArrayListExtra("dialogShowRulesZfb", dialogShowRulesZfb);
        intent.putExtra("hideReturn", hideReturn);
        intent.putExtra("requestCode", requestCode);
        start(activity, intent, requestCode);
    }

    private static void start(Activity activity, Intent intent, int requestCode) {
        if (requestCode == 0) {
            activity.startActivity(intent);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityPayBinding getLayout() {
        return BusinessActivityPayBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        AppCustomEvent.get().getPayCustomListener().payPageInitData(getIntent());
        DataChangeManager.get().registerChangCallBack(this);
        this.action = getIntent().getStringExtra("action");
        this.payInfo = (PayPage.GroupsBean) getIntent().getParcelableExtra("payInfo");
        this.dialogPayItem = (PayPage.GroupsBean) getIntent().getParcelableExtra("dialogPayInfo");
        this.showRules = getIntent().getStringArrayListExtra("showRules");
        this.dialogShowRules = getIntent().getStringArrayListExtra("dialogShowRules");
        this.payInfoZfb = (PayPage.GroupsBean) getIntent().getParcelableExtra("payInfoZfb");
        this.dialogPayItemZfb = (PayPage.GroupsBean) getIntent().getParcelableExtra("dialogPayInfoZfb");
        this.showRulesZfb = getIntent().getStringArrayListExtra("showRulesZfb");
        this.dialogShowRulesZfb = getIntent().getStringArrayListExtra("dialogShowRulesZfb");
        this.hideReturn = getIntent().getBooleanExtra("hideReturn", false);
        if (this.payInfoZfb != null) {
            this.isDoublePayType = true;
        }
        setPayListLocation();
        showLoginBtn();
        setIsShowCloseBtn();
        getPayListInfo();
        setAppConfigs();
        backUISet();
        getBotsData();
        setCheckedPermissionStatus();
        AppCustomEvent.get().getPayCustomListener().rootBindingObj((BusinessActivityPayBinding) this.binding);
    }

    private void setPayListLocation() {
        ((BusinessActivityPayBinding) this.binding).itemTopTips.post(() -> {
            if (this.action.equals(BuildConfig.VERSION_NAME) && ApplicationConfig.getSettingConfig().payListIsHide) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((BusinessActivityPayBinding) this.binding).itemTopTips.getLayoutParams();
                layoutParams.topMargin = ((int) (Super.getWidth() * ApplicationConfig.getSettingConfig().payItemListOffsetTopLocation)) + Super.dp2px(75.0f);
                ((BusinessActivityPayBinding) this.binding).itemTopTips.setLayoutParams(layoutParams);
            } else {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) ((BusinessActivityPayBinding) this.binding).itemTopTips.getLayoutParams();
                layoutParams2.topMargin = (int) (Super.getWidth() * ApplicationConfig.getSettingConfig().payItemListOffsetTopLocation);
                ((BusinessActivityPayBinding) this.binding).itemTopTips.setLayoutParams(layoutParams2);
            }
        });
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void setListener() {
        ((BusinessActivityPayBinding) this.binding).returnBtn.setOnClickListener(new OnClickListener(1500L) { // from class: ps.center.application.pay.PayActivity.1
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                PayActivity.this.finish();
            }
        });
        ((BusinessActivityPayBinding) this.binding).leftClick.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.pay.PayActivity.2
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(PayActivity.this, "用户协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.user_agreement);
            }
        });
        ((BusinessActivityPayBinding) this.binding).centerClick.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.pay.PayActivity.3
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(PayActivity.this, "隐私政策", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.privacy_policy);
            }
        });
        ((BusinessActivityPayBinding) this.binding).rightClick.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.pay.PayActivity.4
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(PayActivity.this, "付费协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.pay_agreement);
            }
        });
        ((BusinessActivityPayBinding) this.binding).lastClick.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.pay.PayActivity.5
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(PayActivity.this, "自动续费协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.auto_renew);
            }
        });
        ((BusinessActivityPayBinding) this.binding).payTheLogin.setOnClickListener(v -> {
            if (!CenterConstant.getUser().isSign) {
                LoginManager.login(this, 0);
            }
        });
        ((BusinessActivityPayBinding) this.binding).mdView.setOnClickListener(v2 -> {
        });
        ((BusinessActivityPayBinding) this.binding).payCheckBox.setOnClickListener(v3 -> {
            checkedPermissionStatus();
        });
        ((BusinessActivityPayBinding) this.binding).zfbPayCheckLayout.setOnClickListener(v4 -> {
            selectPayType(false);
            if (this.isDoublePayType) {
                if (this.isWxPay) {
                    this.selectPayItem = this.payInfo.price_info.get(this.selectItemIndex);
                } else {
                    this.selectPayItem = this.payInfoZfb.price_info.get(this.selectItemIndex);
                }
            }
            ((BusinessActivityPayBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
        });
        ((BusinessActivityPayBinding) this.binding).wxPayCheckLayout.setOnClickListener(v5 -> {
            selectPayType(true);
            if (this.isDoublePayType) {
                if (this.isWxPay) {
                    this.selectPayItem = this.payInfo.price_info.get(this.selectItemIndex);
                } else {
                    this.selectPayItem = this.payInfoZfb.price_info.get(this.selectItemIndex);
                }
            }
            ((BusinessActivityPayBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
        });
        if (this.payInfo.price_info.size() >= 3) {
            this.cardPayAdapter.setOnItemClickListener((item, position) -> {
                this.selectItemIndex = position;
                if (this.isDoublePayType) {
                    if (this.isWxPay) {
                        this.selectPayItem = this.payInfo.price_info.get(position);
                    } else {
                        this.selectPayItem = this.payInfoZfb.price_info.get(position);
                    }
                    ((BusinessActivityPayBinding) this.binding).payBtn.setText(this.selectPayItem.button);
                    ((BusinessActivityPayBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
                    for (PayPage.GroupsBean.PriceInfoBean payPage : this.payInfo.price_info) {
                        payPage.is_top = "0";
                    }
                    this.payInfo.price_info.get(position).is_top = "1";
                    for (PayPage.GroupsBean.PriceInfoBean payPage2 : this.payInfoZfb.price_info) {
                        payPage2.is_top = "0";
                    }
                    this.payInfoZfb.price_info.get(position).is_top = "1";
                } else {
                    this.selectPayItem = this.payInfo.price_info.get(position);
                    ((BusinessActivityPayBinding) this.binding).payBtn.setText(this.selectPayItem.button);
                    ((BusinessActivityPayBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
                    for (PayPage.GroupsBean.PriceInfoBean payPage3 : this.payInfo.price_info) {
                        payPage3.is_top = "0";
                    }
                    this.payInfo.price_info.get(position).is_top = "1";
                }
                this.cardPayAdapter.notifyDataSetChanged();
                showSelectPayType();
            });
        } else {
            this.singleAndDoubleAdapter.setOnItemClickListener((item2, position2) -> {
                this.selectItemIndex = position2;
                if (this.isDoublePayType) {
                    if (this.isWxPay) {
                        this.selectPayItem = this.payInfo.price_info.get(position2);
                    } else {
                        this.selectPayItem = this.payInfoZfb.price_info.get(position2);
                    }
                    ((BusinessActivityPayBinding) this.binding).payBtn.setText(this.selectPayItem.button);
                    ((BusinessActivityPayBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
                    for (PayPage.GroupsBean.PriceInfoBean payPage : this.payInfo.price_info) {
                        payPage.is_top = "0";
                    }
                    this.payInfo.price_info.get(position2).is_top = "1";
                    for (PayPage.GroupsBean.PriceInfoBean payPage2 : this.payInfoZfb.price_info) {
                        payPage2.is_top = "0";
                    }
                    this.payInfoZfb.price_info.get(position2).is_top = "1";
                } else {
                    this.selectPayItem = this.payInfo.price_info.get(position2);
                    ((BusinessActivityPayBinding) this.binding).payBtn.setText(this.selectPayItem.button);
                    ((BusinessActivityPayBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
                    for (PayPage.GroupsBean.PriceInfoBean payPage3 : this.payInfo.price_info) {
                        payPage3.is_top = "0";
                    }
                    this.payInfo.price_info.get(position2).is_top = "1";
                }
                this.singleAndDoubleAdapter.notifyDataSetChanged();
                showSelectPayType();
            });
        }
        ((BusinessActivityPayBinding) this.binding).videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: ps.center.application.pay.PayActivity.6
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mp) {
                ((BusinessActivityPayBinding) PayActivity.this.binding).videoView.start();
            }
        });
        findViewById(R.id.pay_btn).setOnClickListener(v6 -> {
            pay();
        });
    }

    private void setCheckedPermissionStatus() {
        if (BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up.equals("0")) {
            ((BusinessActivityPayBinding) this.binding).payCheckBox.setVisibility(View.GONE);
            ((BusinessActivityPayBinding) this.binding).permissionTipsText.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayBinding) this.binding).payCheckBox.setVisibility(View.VISIBLE);
            ((BusinessActivityPayBinding) this.binding).permissionTipsText.setVisibility(View.VISIBLE);
        }
        if (this.checkedPermission) {
            ((BusinessActivityPayBinding) this.binding).payCheckBox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxSelectImage);
        } else {
            ((BusinessActivityPayBinding) this.binding).payCheckBox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxDefaultImage);
        }
    }

    private void checkedPermissionStatus() {
        this.checkedPermission = !this.checkedPermission;
        setCheckedPermissionStatus();
    }

    private boolean isCheckedPermission() {
        return BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up.equals("0") || this.checkedPermission;
    }

    private void setAppConfigs() {
        SettingConfig settingConfig = ApplicationConfig.getSettingConfig();
        if (settingConfig.payPageDefaultBackImage != 0) {
            ((BusinessActivityPayBinding) this.binding).defaultImage.setImageResource(settingConfig.payPageDefaultBackImage);
        }
        if (this.action.equals(BuildConfig.VERSION_NAME) && settingConfig.payListIsHide) {
            ((BusinessActivityPayBinding) this.binding).list.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayBinding) this.binding).list.setVisibility(View.VISIBLE);
        }
        ((BusinessActivityPayBinding) this.binding).rootLayout.setBackgroundColor(Color.parseColor(settingConfig.payPageDefaultBackColor));
        ((BusinessActivityPayBinding) this.binding).payBtn.setTextColor(Color.parseColor(settingConfig.paySubmitBtnTextColor));
        ((BusinessActivityPayBinding) this.binding).payBtn.setBackgroundResource(settingConfig.paySubmitBtnDrawable);
        ((BusinessActivityPayBinding) this.binding).freeDayTitle.setTextColor(Color.parseColor(settingConfig.payBigTitleFreeDayTextColor));
        ((BusinessActivityPayBinding) this.binding).beiYong.setTextColor(Color.parseColor(settingConfig.paySpareLabelTextColor));
        ((BusinessActivityPayBinding) this.binding).payTheLogin.setTextColor(Color.parseColor(settingConfig.payLoginBtnTextColor));
        ((BusinessActivityPayBinding) this.binding).payTheLogin.setBackgroundResource(settingConfig.payLoginBtnBackDrawable);
        ((BusinessActivityPayBinding) this.binding).freeDay.setTextColor(Color.parseColor(settingConfig.payFreeDayTextColor));
        ((BusinessActivityPayBinding) this.binding).dingyueshuoming.setTextColor(Color.parseColor(settingConfig.payDingYueTextColor));
        ((BusinessActivityPayBinding) this.binding).permissionTipsText.setTextColor(Color.parseColor(settingConfig.payAgreementTextColor));
        ((BusinessActivityPayBinding) this.binding).leftClick.setTextColor(Color.parseColor(settingConfig.payAgreementTextColor));
        ((BusinessActivityPayBinding) this.binding).centerClick.setTextColor(Color.parseColor(settingConfig.payAgreementTextColor));
        ((BusinessActivityPayBinding) this.binding).rightClick.setTextColor(Color.parseColor(settingConfig.payAgreementTextColor));
        ((BusinessActivityPayBinding) this.binding).lastClick.setTextColor(Color.parseColor(settingConfig.payAgreementTextColor));
        ((BusinessActivityPayBinding) this.binding).payTypeWeChatText.setTextColor(Color.parseColor(settingConfig.payTypeWeChatTextColor));
        ((BusinessActivityPayBinding) this.binding).payTypeZFBText.setTextColor(Color.parseColor(settingConfig.payTypeZFBTextColor));
        if (settingConfig.payTitleTextIsShow) {
            if (this.action.equals(BuildConfig.VERSION_NAME)) {
                ((BusinessActivityPayBinding) this.binding).titleText.setText(settingConfig.payTitleTextNotAction);
            } else {
                ((BusinessActivityPayBinding) this.binding).titleText.setText(settingConfig.payTitleText);
            }
            ((BusinessActivityPayBinding) this.binding).titleText.setVisibility(View.VISIBLE);
            ((BusinessActivityPayBinding) this.binding).titleText.setTextColor(Color.parseColor(settingConfig.payTitleTextColor));
        } else {
            ((BusinessActivityPayBinding) this.binding).titleText.setVisibility(View.GONE);
            ((BusinessActivityPayBinding) this.binding).titleText.setText(BuildConfig.VERSION_NAME);
        }
        AppConfig.StandardConfBean.AgreementContentBean agreement_content = BusinessConstant.getConfig().standard_conf.agreement_content;
        if (agreement_content.comm.is_active.equals("1")) {
            ((BusinessActivityPayBinding) this.binding).checkboxLayout1.setVisibility(View.VISIBLE);
            ((BusinessActivityPayBinding) this.binding).leftClick.setVisibility(agreement_content.func.user_agreement.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessActivityPayBinding) this.binding).centerClick.setVisibility(agreement_content.func.privacy_policy.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessActivityPayBinding) this.binding).rightClick.setVisibility(agreement_content.func.pay_agreement.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessActivityPayBinding) this.binding).lastClick.setVisibility(agreement_content.func.auto_renew.equals("0") ? View.GONE : View.VISIBLE);
            return;
        }
        ((BusinessActivityPayBinding) this.binding).checkboxLayout1.setVisibility(View.GONE);
    }

    private void showLoginBtn() {
        if (BusinessConstant.getConfig().standard_conf.login_mode.comm.is_active.equals("1")) {
            if (this.action.equals("前置付费") && BusinessConstant.getConfig().standard_conf.login_mode.func.pay_login.equals("1") && !CenterConstant.getUser().isSign) {
                ((BusinessActivityPayBinding) this.binding).payTheLogin.setVisibility(View.VISIBLE);
                return;
            } else {
                ((BusinessActivityPayBinding) this.binding).payTheLogin.setVisibility(View.GONE);
                return;
            }
        }
        ((BusinessActivityPayBinding) this.binding).payTheLogin.setVisibility(View.GONE);
    }

    private void backUISet() {
        AppConfig.StandardConfBean.PayPageBean pay_page = BusinessConstant.getConfig().standard_conf.pay_page;
        if (!pay_page.func.pay_page1.equals(BuildConfig.VERSION_NAME)) {
            if (pay_page.func.pay_page1.endsWith(".mp4")) {
                ((BusinessActivityPayBinding) this.binding).videoView.setVideoURI(Uri.parse(BusinessConstant.getConfig().standard_conf.pay_page.func.pay_page1));
                ((BusinessActivityPayBinding) this.binding).videoView.start();
            } else {
                ((BusinessActivityPayBinding) this.binding).videoView.setVisibility(View.GONE);
                Glide.with(Super.getContext()).asBitmap().listener(new RequestListener<Bitmap>() { // from class: ps.center.application.pay.PayActivity.7
                     @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        ((BusinessActivityPayBinding) PayActivity.this.binding).backImage1.post(() -> {
                            PayActivity.this.runOnUiThread(() -> {
                                ViewGroup.LayoutParams layoutParams = ((BusinessActivityPayBinding) PayActivity.this.binding).backImage1.getLayoutParams();
                                layoutParams.width = Super.getWidth();
                                layoutParams.height = (int) (layoutParams.width * 1.7777f);
                                ((BusinessActivityPayBinding) PayActivity.this.binding).backImage1.setLayoutParams(layoutParams);
                                ((BusinessActivityPayBinding) PayActivity.this.binding).backImage1.setImageBitmap(resource);
                            });
                        });
                        return false;
                    }
                }).load(pay_page.func.pay_page1).submit();
            }
        }
        if (!pay_page.func.pay_page2.equals(BuildConfig.VERSION_NAME)) {
            Glide.with(Super.getContext()).asBitmap().listener(new RequestListener<Bitmap>() { // from class: ps.center.application.pay.PayActivity.8

               @Override
                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }
                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    ((BusinessActivityPayBinding) PayActivity.this.binding).backImage2.post(() -> {
                        PayActivity.this.runOnUiThread(() -> {
                            ViewGroup.LayoutParams layoutParams = ((BusinessActivityPayBinding) PayActivity.this.binding).backImage2.getLayoutParams();
                            layoutParams.width = Super.getWidth();
                            layoutParams.height = (int) (layoutParams.width * 1.7777f);
                            ((BusinessActivityPayBinding) PayActivity.this.binding).backImage2.setLayoutParams(layoutParams);
                            ((BusinessActivityPayBinding) PayActivity.this.binding).backImage2.setImageBitmap(resource);
                        });
                    });
                    return false;
                }
            }).load(pay_page.func.pay_page2).submit();
        }
        if (!pay_page.func.pay_page3.equals(BuildConfig.VERSION_NAME)) {
            Glide.with(Super.getContext()).asBitmap().listener(new RequestListener<Bitmap>() { // from class: ps.center.application.pay.PayActivity.9
               @Override
                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }
                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    ((BusinessActivityPayBinding) PayActivity.this.binding).backImage3.post(() -> {
                        PayActivity.this.runOnUiThread(() -> {
                            ViewGroup.LayoutParams layoutParams = ((BusinessActivityPayBinding) PayActivity.this.binding).backImage3.getLayoutParams();
                            layoutParams.width = Super.getWidth();
                            layoutParams.height = (int) (layoutParams.width * 1.7777f);
                            ((BusinessActivityPayBinding) PayActivity.this.binding).backImage3.setLayoutParams(layoutParams);
                            ((BusinessActivityPayBinding) PayActivity.this.binding).backImage3.setImageBitmap(resource);
                        });
                    });
                    return false;
                }
            }).load(pay_page.func.pay_page3).submit();
        }
    }

    private void setIsShowCloseBtn() {
        if (this.hideReturn) {
            ((BusinessActivityPayBinding) this.binding).returnBtn.setVisibility(View.GONE);
            return;
        }
        if (this.action.equals("前置付费")) {
            if (BusinessConstant.getConfig().standard_conf.pay_page.func.exit_btn.equals("1")) {
                startCloseBtnCountDownTimerShow();
                return;
            } else {
                ((BusinessActivityPayBinding) this.binding).returnBtn.setVisibility(View.GONE);
                return;
            }
        }
        startCloseBtnCountDownTimerShow();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [ps.center.application.pay.PayActivity$10] */
    private void startCloseBtnCountDownTimerShow() {
        int time;
        if (this.closeCountDownTimer == null) {
            try {
                time = Integer.parseInt(BusinessConstant.getConfig().standard_conf.pay_page.func.return_delay_time) * 1000;
            } catch (NumberFormatException e) {
                LogUtils.e("关闭按钮显示的倒计时配置项配置类型错误， 不是整数");
                e.printStackTrace();
                time = 0;
            }
            this.closeCountDownTimer = new CountDownTimer(time, 1000L) { // from class: ps.center.application.pay.PayActivity.10
                @Override // android.os.CountDownTimer
                public void onTick(long millisUntilFinished) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    ((BusinessActivityPayBinding) PayActivity.this.binding).returnBtn.setVisibility(View.VISIBLE);
                }
            }.start();
        }
    }

    private void cancelCountDownTimer() {
        if (this.closeCountDownTimer != null) {
            this.closeCountDownTimer.cancel();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void getBotsData() {
        new DanMuManager(this, ((BusinessActivityPayBinding) this.binding).dmList1, ((BusinessActivityPayBinding) this.binding).dmList2).build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void getPayListInfo() {
        if (this.payInfo.is_double_pay == 4) {
            this.isWxPay = false;
        }
        for (int i = 0; i < this.payInfo.price_info.size(); i++) {
            if (this.payInfo.price_info.get(i).is_top.equals("1")) {
                this.selectPayItem = this.payInfo.price_info.get(i);
                this.selectItemIndex = i;
            }
        }
        try {
            if (this.isWxPay) {
                this.selectPayItem = this.payInfo.price_info.get(this.selectItemIndex);
            } else {
                this.selectPayItem = this.payInfoZfb.price_info.get(this.selectItemIndex);
            }
        } catch (Exception e) {
            this.selectPayItem = this.payInfo.price_info.get(this.selectItemIndex);
        }
        showSelectPayType();
        ((BusinessActivityPayBinding) this.binding).list.setFocusable(false);
        ((BusinessActivityPayBinding) this.binding).list.setHasFixedSize(true);
        ((BusinessActivityPayBinding) this.binding).list.setNestedScrollingEnabled(false);
        if (this.payInfo.price_info.size() >= 3) {
            if (this.isDoublePayType) {
                this.cardPayAdapter = new CardPayAdapter(this, R.layout.business_item_card_pay_list, this.action, true, this.isWxPay, this.payInfo.price_info, this.showRules, this.payInfoZfb.price_info, this.showRulesZfb);
            } else {
                this.cardPayAdapter = new CardPayAdapter(this, R.layout.business_item_card_pay_list, this.action, false, this.isWxPay, this.payInfo.price_info, this.showRules, null, this.showRulesZfb);
            }
            ((BusinessActivityPayBinding) this.binding).list.setAdapter(this.cardPayAdapter);
            if (this.payInfo.price_info.size() == 3) {
                ((BusinessActivityPayBinding) this.binding).list.setLayoutManager(new GridLayoutManager(this, 3));
            } else if (this.payInfo.price_info.size() > 3) {
                ((BusinessActivityPayBinding) this.binding).list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            }
            if (CenterConstant.getUser().isVip) {
                Iterator<PayPage.GroupsBean.PriceInfoBean> it = this.payInfo.price_info.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    PayPage.GroupsBean.PriceInfoBean item = it.next();
                    if (!item.renew_type.equals("0")) {
                        this.payInfo.price_info.remove(item);
                        break;
                    }
                }
            }
            this.cardPayAdapter.notifyDataSetChanged();
        } else {
            if (this.isDoublePayType) {
                this.singleAndDoubleAdapter = new SingleAndDoubleAdapter(this, R.layout.business_item_double_pay_list, true, this.isWxPay, this.payInfo.price_info, this.payInfoZfb.price_info);
            } else {
                this.singleAndDoubleAdapter = new SingleAndDoubleAdapter(this, R.layout.business_item_double_pay_list, false, this.isWxPay, this.payInfo.price_info, null);
            }
            ((BusinessActivityPayBinding) this.binding).list.setAdapter(this.singleAndDoubleAdapter);
            ((BusinessActivityPayBinding) this.binding).list.setLayoutManager(new GridLayoutManager(this, this.payInfo.price_info.size()));
            this.singleAndDoubleAdapter.notifyDataSetChanged();
        }
        ((BusinessActivityPayBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
        ((BusinessActivityPayBinding) this.binding).payBtn.setText(this.selectPayItem.button);
        if (this.selectPayItem.free_day.equals(BuildConfig.VERSION_NAME)) {
            ((BusinessActivityPayBinding) this.binding).freeDayTitle.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayBinding) this.binding).freeDayTitle.setVisibility(View.VISIBLE);
        }
        ((BusinessActivityPayBinding) this.binding).freeDayTitle.setText(this.selectPayItem.free_day);
        if (this.selectPayItem.spare_label.equals(BuildConfig.VERSION_NAME)) {
            ((BusinessActivityPayBinding) this.binding).beiYong.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayBinding) this.binding).beiYong.setVisibility(View.VISIBLE);
        }
        ((BusinessActivityPayBinding) this.binding).beiYong.setText(this.selectPayItem.spare_label);
    }

    private void showSelectPayType() {
        if (!this.isDoublePayType) {
            if (!this.selectPayItem.renew_type.equals("0")) {
                selectPayType(false);
                if (this.selectPayItem.free_day.equals(BuildConfig.VERSION_NAME)) {
                    checkPayTypeShow(2);
                } else {
                    ((BusinessActivityPayBinding) this.binding).freeDay.setText(this.selectPayItem.spare_label2);
                    checkPayTypeShow(3);
                }
            } else if (this.payInfo.is_double_pay == 0) {
                checkPayTypeShow(1);
                selectPayType(true);
            } else if (this.payInfo.is_double_pay == 1) {
                checkPayTypeShow(2);
                selectPayType(false);
            } else if (this.payInfo.is_double_pay == 2) {
                checkPayTypeShow(0);
                selectPayType(this.isWxPay);
            } else if (this.payInfo.is_double_pay == 4) {
                checkPayTypeShow(4);
                selectPayType(this.isWxPay);
            } else {
                checkPayTypeShow(0);
                selectPayType(this.isWxPay);
            }
        } else {
            checkPayTypeShow(0);
            if (this.payInfo.is_double_pay == 4) {
                selectPayType(this.isWxPay);
            }
        }
        if (this.selectPayItem.free_day.equals(BuildConfig.VERSION_NAME)) {
            ((BusinessActivityPayBinding) this.binding).freeDayTitle.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayBinding) this.binding).freeDayTitle.setVisibility(View.VISIBLE);
        }
        ((BusinessActivityPayBinding) this.binding).freeDayTitle.setText(this.selectPayItem.free_day);
        if (this.selectPayItem.spare_label.equals(BuildConfig.VERSION_NAME)) {
            ((BusinessActivityPayBinding) this.binding).beiYong.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayBinding) this.binding).beiYong.setVisibility(View.VISIBLE);
        }
        ((BusinessActivityPayBinding) this.binding).beiYong.setText(this.selectPayItem.spare_label);
    }

    private void selectPayType(boolean isWxPay) {
        if (isWxPay) {
            ((BusinessActivityPayBinding) this.binding).zfbPayCheckBox.setImageResource(R.mipmap.business_pay_checkbox_off);
            ((BusinessActivityPayBinding) this.binding).wxPayCheckBox.setImageResource(R.mipmap.business_pay_checkbox_on);
        } else {
            ((BusinessActivityPayBinding) this.binding).zfbPayCheckBox.setImageResource(R.mipmap.business_pay_checkbox_on);
            ((BusinessActivityPayBinding) this.binding).wxPayCheckBox.setImageResource(R.mipmap.business_pay_checkbox_off);
        }
        this.isWxPay = isWxPay;
        if (this.cardPayAdapter != null) {
            this.cardPayAdapter.setIsWxPay(isWxPay);
            this.cardPayAdapter.notifyDataSetChanged();
        }
        if (this.singleAndDoubleAdapter != null) {
            this.singleAndDoubleAdapter.setIsWxPay(isWxPay);
            this.singleAndDoubleAdapter.notifyDataSetChanged();
        }
    }

    private void checkPayTypeShow(int type) {
        if (this.isDoublePayType) {
            type = 0;
        }
        if (this.payInfo != null && this.payInfo.is_double_pay == 4) {
            ((BusinessActivityPayBinding) this.binding).wxPayCheckLayout.post(() -> {
                LinearLayout wxPayCheckLayout = ((BusinessActivityPayBinding) this.binding).wxPayCheckLayout;
                ((BusinessActivityPayBinding) this.binding).freeDayLayout.removeView(wxPayCheckLayout);
                ((BusinessActivityPayBinding) this.binding).freeDayLayout.addView(wxPayCheckLayout);
            });
        }
        switch (type) {
            case 0:
            case 4:
                ((BusinessActivityPayBinding) this.binding).wxPayCheckLayout.setVisibility(View.VISIBLE);
                ((BusinessActivityPayBinding) this.binding).zfbPayCheckLayout.setVisibility(View.VISIBLE);
                ((BusinessActivityPayBinding) this.binding).freeDay.setVisibility(View.GONE);
                break;
            case 1:
                ((BusinessActivityPayBinding) this.binding).wxPayCheckLayout.setVisibility(View.VISIBLE);
                ((BusinessActivityPayBinding) this.binding).zfbPayCheckLayout.setVisibility(View.GONE);
                ((BusinessActivityPayBinding) this.binding).freeDay.setVisibility(View.GONE);
                break;
            case 2:
                ((BusinessActivityPayBinding) this.binding).wxPayCheckLayout.setVisibility(View.GONE);
                ((BusinessActivityPayBinding) this.binding).zfbPayCheckLayout.setVisibility(View.VISIBLE);
                ((BusinessActivityPayBinding) this.binding).freeDay.setVisibility(View.GONE);
                break;
            case 3:
                ((BusinessActivityPayBinding) this.binding).wxPayCheckLayout.setVisibility(View.GONE);
                ((BusinessActivityPayBinding) this.binding).zfbPayCheckLayout.setVisibility(View.GONE);
                ((BusinessActivityPayBinding) this.binding).freeDay.setVisibility(View.VISIBLE);
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void pay() {
        if (isCheckedPermission()) {
            if (BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up.equals("1") && !BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up_txt.equals(BuildConfig.VERSION_NAME) && !BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up_txt.equals("0")) {
                SurePayDialog surePayDialog = new SurePayDialog(this, new BaseDialogVB2.Call() { // from class: ps.center.application.pay.PayActivity.11
                    @Override // ps.center.views.dialog.BaseDialogVB2.Call
                    public void call(Object dialogCall) {
                        PayActivity.this.pay(false);
                    }
                });
                surePayDialog.show();
                return;
            } else {
                pay(false);
                return;
            }
        }
        ToastUtils.show(Super.getContext(), "请选阅读并同意协议");
        ((BusinessActivityPayBinding) this.binding).scrollView.scrollBy(0, 9999);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void pay(boolean isDialogPay) {
        if (isDialogPay) {
            if (this.dialogPayItem != null) {
                try {
                    if (this.isDoublePayType) {
                        if (this.isWxPay) {
                            WeChat.getInstance().payNativeNew(this, this.dialogPayItem.price_info.get(0).id + BuildConfig.VERSION_NAME, this.dialogPayItem.id + BuildConfig.VERSION_NAME, "0");
                        } else {
                            WeChat.getInstance().payNativeNew(this, this.dialogPayItem.price_info.get(0).id + BuildConfig.VERSION_NAME, this.dialogPayItemZfb.id + BuildConfig.VERSION_NAME, "1");
                        }
                    } else {
                        WeChat.getInstance().payNativeNew(this, this.dialogPayItem.price_info.get(0).id + BuildConfig.VERSION_NAME, this.dialogPayItem.id + BuildConfig.VERSION_NAME, "0");
                    }
                    return;
                } catch (Exception e) {
                    ToastUtils.show(this, "支付失败：" + e);
                    return;
                }
            }
            return;
        }
        if (this.isDoublePayType) {
            if (this.isWxPay) {
                WeChat.getInstance().payNativeNew(this, this.selectPayItem.id + BuildConfig.VERSION_NAME, this.payInfo.id + BuildConfig.VERSION_NAME, this.isWxPay ? "0" : "1");
                return;
            } else {
                WeChat.getInstance().payNativeNew(this, this.selectPayItem.id + BuildConfig.VERSION_NAME, this.payInfoZfb.id + BuildConfig.VERSION_NAME, this.isWxPay ? "0" : "1");
                return;
            }
        }
        WeChat.getInstance().payNativeNew(this, this.selectPayItem.id + BuildConfig.VERSION_NAME, this.payInfo.id + BuildConfig.VERSION_NAME, this.isWxPay ? "0" : "1");
    }

    public void finish() {
        if (this.hideReturn) {
            return;
        }
        cancelCountDownTimer();
        switch (this.action) {
            case "前置付费":
                if (!BusinessConstant.getConfig().standard_conf.pay_page.func.exit_btn.equals("1")) {
                    if (CenterConstant.getUser().isVip) {
                        goMainActivity();
                        break;
                    } else {
                        return;
                    }
                } else {
                    goMainActivity();
                    break;
                }
            case "后置付费":
                if (!CenterConstant.getUser().isVip && showExitDialog()) {
                    return;
                }
                break;
        }
        super.finish();
        AppCustomEvent.get().getPayCustomListener().payPageFinishEnd();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void goMainActivity() {
        String mainActivityClassName = Save.instance.getString("mainActivityClassName", BuildConfig.VERSION_NAME);
        try {
            Class<?> mainActivityClass = Class.forName(mainActivityClassName);
            Intent intent = new Intent((Context) this, mainActivityClass);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        DataChangeManager.get().unregisterChangCallBack(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean showExitDialog() {
        PayPage.GroupsBean.PriceInfoBean priceInfoBean;
        ArrayList<String> showRule;
        try {
            if (this.dialogPayItem.price_info != null && this.dialogPayItem.price_info.size() > 0 && BusinessConstant.getConfig().standard_conf.pay_page.comm.is_active.equals("1") && BusinessConstant.getConfig().standard_conf.pay_page.func.retain_sw.equals("1")) {
                if (this.dialogPayItemZfb == null) {
                    priceInfoBean = this.dialogPayItem.price_info.get(0);
                    showRule = this.dialogShowRules;
                } else if (this.isWxPay) {
                    priceInfoBean = this.dialogPayItem.price_info.get(0);
                    showRule = this.dialogShowRules;
                } else {
                    priceInfoBean = this.dialogPayItemZfb.price_info.get(0);
                    showRule = this.dialogShowRulesZfb;
                }
                this.exitPayDialog = new ExitDialogManager(this, this.action, priceInfoBean, showRule, this.checkedPermission, call -> {
                    try {
                        if (call instanceof Boolean) {
                            this.checkedPermission = ((Boolean) call).booleanValue();
                            setCheckedPermissionStatus();
                        } else if (call.equals("close")) {
                            this.exitPayDialog.dismiss();
                            if (this.action.equals("后置付费")) {
                                super.finish();
                                AppCustomEvent.get().getPayCustomListener().payPageFinishEnd();
                            }
                        } else if (call.equals("submit")) {
                            if (isCheckedPermission()) {
                                this.exitPayDialog.dismiss();
                                pay(true);
                            } else {
                                ToastUtils.show(Super.getContext(), "请选阅读并同意协议");
                                ((BusinessActivityPayBinding) this.binding).scrollView.scrollBy(0, 9999);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                this.exitPayDialog.show();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void onStop() {
        super.onStop();
        try {
            if (((BusinessActivityPayBinding) this.binding).videoView.canPause()) {
                ((BusinessActivityPayBinding) this.binding).videoView.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        try {
            ((BusinessActivityPayBinding) this.binding).videoView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // ps.center.views.DataChanger.DataChangeCallBack
    public void change(int action, Object object) {
        switch (action) {
            case 3:
                showLoginBtn();
                if (this.action.equals("前置付费") && CenterConstant.getUser().isVip) {
                    goMainActivity();
                    super.finish();
                    AppCustomEvent.get().getPayCustomListener().payPageFinishEnd();
                    DataChangeManager.get().unregisterChangCallBack(this);
                    break;
                }
                break;
            case 5:
                CenterHttp.get().getUserInfo(new Result<User>() { // from class: ps.center.application.pay.PayActivity.12
                    @Override // ps.center.library.http.base.Result
                    public void success(User obj) {
                        if (obj.isVip) {
                            Iterator<PayPage.GroupsBean.PriceInfoBean> it = PayActivity.this.payInfo.price_info.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                PayPage.GroupsBean.PriceInfoBean item = it.next();
                                if (!item.renew_type.equals("0")) {
                                    PayActivity.this.payInfo.price_info.remove(item);
                                    break;
                                }
                            }
                            if (PayActivity.this.payInfo.price_info.size() >= 3) {
                                PayActivity.this.cardPayAdapter.notifyDataSetChanged();
                            }
                        }
                        if (PayActivity.this.hideReturn) {
                            PayActivity.this.hideReturn = false;
                        }
                        DataChangeManager.get().change(1, BuildConfig.VERSION_NAME);
                        PayActivity.this.finish();
                    }
                });
                break;
            case DataChangeStatus.PAY_CANCEL /* 6 */:
                if (!this.action.equals("前置付费") || !BusinessConstant.getConfig().standard_conf.pay_page.func.pay_retain_sw.equals("1") || showExitDialog()) {
                }
                break;
        }
    }

    /* loaded from: classes.jar:ps/center/application/pay/PayActivity$PayBots.class */
    private static class PayBots implements AutoPlayUrlItem {
        public String title;
        public String url;

        public PayBots(String title, String url) {
            this.title = title;
            this.url = url;
        }

        @Override // ps.center.views.layout.recycleAutoPlay.AutoPlayUrlItem
        public int logoIcon() {
            return 0;
        }

        @Override // ps.center.views.layout.recycleAutoPlay.AutoPlayUrlItem
        public String getText() {
            return this.title;
        }

        @Override // ps.center.views.layout.recycleAutoPlay.AutoPlayUrlItem
        public String iconUrl() {
            return this.url;
        }
    }
}