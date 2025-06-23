package ps.center.application.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.List;
import ps.center.application.BuildConfig;
import ps.center.application.DataChangeEvent.DataChangeStatus;
import ps.center.R;
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
import ps.center.databinding.BusinessActivityPayScrollBinding;
import ps.center.library.http.base.Result;
import ps.center.utils.LogUtils;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.utils.ui.OnClickListener;
import ps.center.utils.xbanner.XBanner;
import ps.center.utils.xbanner.entity.BaseBannerInfo;
import ps.center.views.DataChanger.DataChangeCallBack;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;
import ps.center.views.activity.webview.NativeWebActivity;
import ps.center.views.layout.recycleAutoPlay.AutoPlayUrlItem;

/* loaded from: classes.jar:ps/center/application/pay/PayScrollActivity.class */
public class PayScrollActivity extends BaseActivityVB<BusinessActivityPayScrollBinding> implements DataChangeCallBack {
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
        Intent intent = new Intent(activity, (Class<?>) PayScrollActivity.class);
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
    public BusinessActivityPayScrollBinding getLayout() {
        return BusinessActivityPayScrollBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
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
    }

    private void setPayListLocation() {
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void setListener() {
        ((BusinessActivityPayScrollBinding) this.binding).returnBtn.setOnClickListener(new OnClickListener(1500L) { // from class: ps.center.application.pay.PayScrollActivity.1
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                PayScrollActivity.this.finish();
            }
        });
        ((BusinessActivityPayScrollBinding) this.binding).leftClick.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.pay.PayScrollActivity.2
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(PayScrollActivity.this, "用户协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.user_agreement);
            }
        });
        ((BusinessActivityPayScrollBinding) this.binding).centerClick.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.pay.PayScrollActivity.3
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(PayScrollActivity.this, "隐私政策", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.privacy_policy);
            }
        });
        ((BusinessActivityPayScrollBinding) this.binding).rightClick.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.pay.PayScrollActivity.4
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(PayScrollActivity.this, "付费协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.pay_agreement);
            }
        });
        ((BusinessActivityPayScrollBinding) this.binding).lastClick.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.pay.PayScrollActivity.5
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(PayScrollActivity.this, "自动续费协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.auto_renew);
            }
        });
        ((BusinessActivityPayScrollBinding) this.binding).payTheLogin.setOnClickListener(v -> {
            if (!CenterConstant.getUser().isSign) {
                LoginManager.login(this, 0);
            }
        });
        ((BusinessActivityPayScrollBinding) this.binding).mdView.setOnClickListener(v2 -> {
        });
        ((BusinessActivityPayScrollBinding) this.binding).payCheckBox.setOnClickListener(v3 -> {
            checkedPermissionStatus();
        });
        ((BusinessActivityPayScrollBinding) this.binding).zfbPayCheckLayout.setOnClickListener(v4 -> {
            selectPayType(false);
            if (this.isDoublePayType) {
                if (this.isWxPay) {
                    this.selectPayItem = this.payInfo.price_info.get(this.selectItemIndex);
                } else {
                    this.selectPayItem = this.payInfoZfb.price_info.get(this.selectItemIndex);
                }
            }
        });
        ((BusinessActivityPayScrollBinding) this.binding).wxPayCheckLayout.setOnClickListener(v5 -> {
            selectPayType(true);
            if (this.isDoublePayType) {
                if (this.isWxPay) {
                    this.selectPayItem = this.payInfo.price_info.get(this.selectItemIndex);
                } else {
                    this.selectPayItem = this.payInfoZfb.price_info.get(this.selectItemIndex);
                }
            }
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
                    ((BusinessActivityPayScrollBinding) this.binding).payBtn.setText(this.selectPayItem.button);
                    ((BusinessActivityPayScrollBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
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
                    ((BusinessActivityPayScrollBinding) this.binding).payBtn.setText(this.selectPayItem.button);
                    ((BusinessActivityPayScrollBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
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
                    ((BusinessActivityPayScrollBinding) this.binding).payBtn.setText(this.selectPayItem.button);
                    ((BusinessActivityPayScrollBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
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
                    ((BusinessActivityPayScrollBinding) this.binding).payBtn.setText(this.selectPayItem.button);
                    ((BusinessActivityPayScrollBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
                    for (PayPage.GroupsBean.PriceInfoBean payPage3 : this.payInfo.price_info) {
                        payPage3.is_top = "0";
                    }
                    this.payInfo.price_info.get(position2).is_top = "1";
                }
                this.singleAndDoubleAdapter.notifyDataSetChanged();
                showSelectPayType();
            });
        }
        findViewById(R.id.pay_btn).setOnClickListener(v6 -> {
            pay();
        });
    }

    private void setCheckedPermissionStatus() {
        if (BusinessConstant.getConfig().standard_conf.pay_page.func.pay_pop_up.equals("0")) {
            ((BusinessActivityPayScrollBinding) this.binding).payCheckBox.setVisibility(View.GONE);
            ((BusinessActivityPayScrollBinding) this.binding).permissionTipsText.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayScrollBinding) this.binding).payCheckBox.setVisibility(View.VISIBLE);
            ((BusinessActivityPayScrollBinding) this.binding).permissionTipsText.setVisibility(View.VISIBLE);
        }
        if (this.checkedPermission) {
            ((BusinessActivityPayScrollBinding) this.binding).payCheckBox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxSelectImage);
        } else {
            ((BusinessActivityPayScrollBinding) this.binding).payCheckBox.setImageResource(ApplicationConfig.getSettingConfig().loginCheckBoxDefaultImage);
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
            ((BusinessActivityPayScrollBinding) this.binding).defaultImage.setImageResource(settingConfig.payPageDefaultBackImage);
        }
        if (this.action.equals(BuildConfig.VERSION_NAME) && settingConfig.payListIsHide) {
            ((BusinessActivityPayScrollBinding) this.binding).list.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayScrollBinding) this.binding).list.setVisibility(View.VISIBLE);
        }
        ((BusinessActivityPayScrollBinding) this.binding).rootLayout.setBackgroundColor(Color.parseColor(settingConfig.payPageDefaultBackColor));
        ((BusinessActivityPayScrollBinding) this.binding).payBtn.setTextColor(Color.parseColor(settingConfig.paySubmitBtnTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).payBtn.setBackgroundResource(settingConfig.paySubmitBtnDrawable);
        ((BusinessActivityPayScrollBinding) this.binding).freeDayTitle.setTextColor(Color.parseColor(settingConfig.payBigTitleFreeDayTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).beiYong.setTextColor(Color.parseColor(settingConfig.paySpareLabelTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).payTheLogin.setTextColor(Color.parseColor(settingConfig.payLoginBtnTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).payTheLogin.setBackgroundResource(settingConfig.payLoginBtnBackDrawable);
        ((BusinessActivityPayScrollBinding) this.binding).freeDay.setTextColor(Color.parseColor(settingConfig.payFreeDayTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).dingyueshuoming.setTextColor(Color.parseColor(settingConfig.payDingYueTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).permissionTipsText.setTextColor(Color.parseColor(settingConfig.payAgreementTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).leftClick.setTextColor(Color.parseColor(settingConfig.payAgreementTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).centerClick.setTextColor(Color.parseColor(settingConfig.payAgreementTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).rightClick.setTextColor(Color.parseColor(settingConfig.payAgreementTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).lastClick.setTextColor(Color.parseColor(settingConfig.payAgreementTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).payTypeWeChatText.setTextColor(Color.parseColor(settingConfig.payTypeWeChatTextColor));
        ((BusinessActivityPayScrollBinding) this.binding).payTypeZFBText.setTextColor(Color.parseColor(settingConfig.payTypeZFBTextColor));
        AppConfig.StandardConfBean.AgreementContentBean agreement_content = BusinessConstant.getConfig().standard_conf.agreement_content;
        if (agreement_content.comm.is_active.equals("1")) {
            ((BusinessActivityPayScrollBinding) this.binding).checkboxLayout1.setVisibility(View.VISIBLE);
            ((BusinessActivityPayScrollBinding) this.binding).leftClick.setVisibility(agreement_content.func.user_agreement.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessActivityPayScrollBinding) this.binding).centerClick.setVisibility(agreement_content.func.privacy_policy.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessActivityPayScrollBinding) this.binding).rightClick.setVisibility(agreement_content.func.pay_agreement.equals("0") ? View.GONE : View.VISIBLE);
            ((BusinessActivityPayScrollBinding) this.binding).lastClick.setVisibility(agreement_content.func.auto_renew.equals("0") ? View.GONE : View.VISIBLE);
            return;
        }
        ((BusinessActivityPayScrollBinding) this.binding).checkboxLayout1.setVisibility(View.GONE);
    }

    private void showLoginBtn() {
        if (BusinessConstant.getConfig().standard_conf.login_mode.comm.is_active.equals("1")) {
            if (this.action.equals("前置付费") && BusinessConstant.getConfig().standard_conf.login_mode.func.pay_login.equals("1") && !CenterConstant.getUser().isSign) {
                ((BusinessActivityPayScrollBinding) this.binding).payTheLogin.setVisibility(View.VISIBLE);
                return;
            } else {
                ((BusinessActivityPayScrollBinding) this.binding).payTheLogin.setVisibility(View.GONE);
                return;
            }
        }
        ((BusinessActivityPayScrollBinding) this.binding).payTheLogin.setVisibility(View.GONE);
    }

    private void backUISet() {
        AppConfig.StandardConfBean.PayPageBean pay_page = BusinessConstant.getConfig().standard_conf.pay_page;
        List<String> payBanner = pay_page.func.paypage_rotation;
        if (payBanner != null && payBanner.size() > 0) {
            ((BusinessActivityPayScrollBinding) this.binding).banner.setVisibility(View.VISIBLE);
            final List<BannerBean> bannerBeans = new ArrayList<>();
            for (int i = 0; i < payBanner.size(); i++) {
                bannerBeans.add(new BannerBean(payBanner.get(i)));
            }
            Glide.with(Super.getContext()).asBitmap().listener(new RequestListener<Bitmap>() { // from class: ps.center.application.pay.PayScrollActivity.6


                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    XBanner xBanner = ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).banner;
                    List list = bannerBeans;
                    xBanner.post(() -> {
                        PayScrollActivity.this.runOnUiThread(() -> {
                            float scale = resource.getHeight() / resource.getWidth();
                            ViewGroup.LayoutParams layoutParams = ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).banner.getLayoutParams();
                            layoutParams.width = Super.getWidth();
                            layoutParams.height = (int) (layoutParams.width * scale);
                            ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).banner.setLayoutParams(layoutParams);
                            ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).banner.setBannerData(R.layout.business_pay_banner_item_layout, (List<? extends BaseBannerInfo>) list);
                            ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).banner.loadImage(new XBanner.XBannerAdapter() { // from class: ps.center.application.pay.PayScrollActivity.6.1
                                @Override // ps.center.utils.xbanner.XBanner.XBannerAdapter
                                public void loadBanner(XBanner banner, Object model2, View view, int position) {
                                    BannerBean bean = (BannerBean) model2;
                                    ImageView image = (ImageView) view.findViewById(R.id.image);
                                    Glide.with(Super.getContext()).load(bean.imageUrl).into(image);
                                }
                            });
                        });
                    });
                    return false;
                }
            }).load(payBanner.get(0)).submit();
        } else {
            ((BusinessActivityPayScrollBinding) this.binding).banner.setVisibility(View.GONE);
        }
        if (!pay_page.func.pay_page1.equals(BuildConfig.VERSION_NAME)) {
            Glide.with(Super.getContext()).asBitmap().listener(new RequestListener<Bitmap>() { // from class: ps.center.application.pay.PayScrollActivity.7

                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).defaultImage.post(() -> {
                        PayScrollActivity.this.runOnUiThread(() -> {
                            float scale = resource.getHeight() / resource.getWidth();
                            ViewGroup.LayoutParams layoutParams = ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).defaultImage.getLayoutParams();
                            layoutParams.width = Super.getWidth();
                            layoutParams.height = (int) (layoutParams.width * scale);
                            ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).defaultImage.setLayoutParams(layoutParams);
                            ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).defaultImage.setImageBitmap(resource);
                        });
                    });
                    return false;
                }
            }).load(pay_page.func.pay_page1).submit();
        }
        if (!pay_page.func.pay_page2.equals(BuildConfig.VERSION_NAME)) {
            Glide.with(Super.getContext()).asBitmap().listener(new RequestListener<Bitmap>() { // from class: ps.center.application.pay.PayScrollActivity.8

                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).backImage2.post(() -> {
                        PayScrollActivity.this.runOnUiThread(() -> {
                            float scale = resource.getHeight() / resource.getWidth();
                            ViewGroup.LayoutParams layoutParams = ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).backImage2.getLayoutParams();
                            layoutParams.width = Super.getWidth();
                            layoutParams.height = (int) (layoutParams.width * scale);
                            ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).backImage2.setLayoutParams(layoutParams);
                            ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).backImage2.setImageBitmap(resource);
                        });
                    });
                    return false;
                }
            }).load(pay_page.func.pay_page2).submit();
        }
        if (!pay_page.func.pay_page3.equals(BuildConfig.VERSION_NAME)) {
            Glide.with(Super.getContext()).asBitmap().listener(new RequestListener<Bitmap>() { // from class: ps.center.application.pay.PayScrollActivity.9


                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).backImage3.post(() -> {
                        PayScrollActivity.this.runOnUiThread(() -> {
                            float scale = resource.getHeight() / resource.getWidth();
                            ViewGroup.LayoutParams layoutParams = ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).backImage3.getLayoutParams();
                            layoutParams.width = Super.getWidth();
                            layoutParams.height = (int) (layoutParams.width * scale);
                            ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).backImage3.setLayoutParams(layoutParams);
                            ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).backImage3.setImageBitmap(resource);
                        });
                    });
                    return false;
                }
            }).load(pay_page.func.pay_page3).submit();
        }
    }

    private void setIsShowCloseBtn() {
        if (this.hideReturn) {
            ((BusinessActivityPayScrollBinding) this.binding).returnBtn.setVisibility(View.GONE);
            return;
        }
        if (this.action.equals("前置付费")) {
            if (BusinessConstant.getConfig().standard_conf.pay_page.func.exit_btn.equals("1")) {
                startCloseBtnCountDownTimerShow();
                return;
            } else {
                ((BusinessActivityPayScrollBinding) this.binding).returnBtn.setVisibility(View.GONE);
                return;
            }
        }
        startCloseBtnCountDownTimerShow();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [ps.center.application.pay.PayScrollActivity$10] */
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
            this.closeCountDownTimer = new CountDownTimer(time, 1000L) { // from class: ps.center.application.pay.PayScrollActivity.10
                @Override // android.os.CountDownTimer
                public void onTick(long millisUntilFinished) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    ((BusinessActivityPayScrollBinding) PayScrollActivity.this.binding).returnBtn.setVisibility(View.VISIBLE);
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
        new DanMuManager(this, ((BusinessActivityPayScrollBinding) this.binding).dmList1, ((BusinessActivityPayScrollBinding) this.binding).dmList2).build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void getPayListInfo() {
        for (int i = 0; i < this.payInfo.price_info.size(); i++) {
            if (this.payInfo.price_info.get(i).is_top.equals("1")) {
                this.selectPayItem = this.payInfo.price_info.get(i);
                this.selectItemIndex = i;
            }
        }
        showSelectPayType();
        ((BusinessActivityPayScrollBinding) this.binding).list.setFocusable(false);
        ((BusinessActivityPayScrollBinding) this.binding).list.setHasFixedSize(true);
        ((BusinessActivityPayScrollBinding) this.binding).list.setNestedScrollingEnabled(false);
        if (this.payInfo.price_info.size() >= 3) {
            if (this.isDoublePayType) {
                this.cardPayAdapter = new CardPayAdapter(this, R.layout.business_item_card_pay_list_as_scroll_pay, this.action, true, this.isWxPay, this.payInfo.price_info, this.showRules, this.payInfoZfb.price_info, this.showRulesZfb);
            } else {
                this.cardPayAdapter = new CardPayAdapter(this, R.layout.business_item_card_pay_list_as_scroll_pay, this.action, false, this.isWxPay, this.payInfo.price_info, this.showRules, null, this.showRulesZfb);
            }
            ((BusinessActivityPayScrollBinding) this.binding).list.setAdapter(this.cardPayAdapter);
            if (this.payInfo.price_info.size() == 3) {
                ((BusinessActivityPayScrollBinding) this.binding).list.setLayoutManager(new GridLayoutManager(this, 3));
            } else if (this.payInfo.price_info.size() > 3) {
                ((BusinessActivityPayScrollBinding) this.binding).list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
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
            ((BusinessActivityPayScrollBinding) this.binding).list.setAdapter(this.singleAndDoubleAdapter);
            ((BusinessActivityPayScrollBinding) this.binding).list.setLayoutManager(new GridLayoutManager(this, this.payInfo.price_info.size()));
            this.singleAndDoubleAdapter.notifyDataSetChanged();
        }
        ((BusinessActivityPayScrollBinding) this.binding).dingyueshuoming.setText(this.selectPayItem.dingyueshuoming);
        ((BusinessActivityPayScrollBinding) this.binding).payBtn.setText(this.selectPayItem.button);
        if (this.selectPayItem.free_day.equals(BuildConfig.VERSION_NAME)) {
            ((BusinessActivityPayScrollBinding) this.binding).freeDayTitle.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayScrollBinding) this.binding).freeDayTitle.setVisibility(View.VISIBLE);
        }
        ((BusinessActivityPayScrollBinding) this.binding).freeDayTitle.setText(this.selectPayItem.free_day);
        if (this.selectPayItem.spare_label.equals(BuildConfig.VERSION_NAME)) {
            ((BusinessActivityPayScrollBinding) this.binding).beiYong.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayScrollBinding) this.binding).beiYong.setVisibility(View.VISIBLE);
        }
        ((BusinessActivityPayScrollBinding) this.binding).beiYong.setText(this.selectPayItem.spare_label);
    }

    private void showSelectPayType() {
        if (!this.isDoublePayType) {
            if (!this.selectPayItem.renew_type.equals("0")) {
                selectPayType(false);
                if (this.selectPayItem.free_day.equals(BuildConfig.VERSION_NAME)) {
                    checkPayTypeShow(2);
                } else {
                    ((BusinessActivityPayScrollBinding) this.binding).freeDay.setText(this.selectPayItem.spare_label2);
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
            ((BusinessActivityPayScrollBinding) this.binding).freeDayTitle.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayScrollBinding) this.binding).freeDayTitle.setVisibility(View.VISIBLE);
        }
        ((BusinessActivityPayScrollBinding) this.binding).freeDayTitle.setText(this.selectPayItem.free_day);
        if (this.selectPayItem.spare_label.equals(BuildConfig.VERSION_NAME)) {
            ((BusinessActivityPayScrollBinding) this.binding).beiYong.setVisibility(View.GONE);
        } else {
            ((BusinessActivityPayScrollBinding) this.binding).beiYong.setVisibility(View.VISIBLE);
        }
        ((BusinessActivityPayScrollBinding) this.binding).beiYong.setText(this.selectPayItem.spare_label);
    }

    private void selectPayType(boolean isWxPay) {
        if (isWxPay) {
            ((BusinessActivityPayScrollBinding) this.binding).zfbPayCheckBox.setImageResource(R.mipmap.business_pay_checkbox_off);
            ((BusinessActivityPayScrollBinding) this.binding).wxPayCheckBox.setImageResource(R.mipmap.business_pay_checkbox_on2);
        } else {
            ((BusinessActivityPayScrollBinding) this.binding).zfbPayCheckBox.setImageResource(R.mipmap.business_pay_checkbox_on2);
            ((BusinessActivityPayScrollBinding) this.binding).wxPayCheckBox.setImageResource(R.mipmap.business_pay_checkbox_off);
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
            ((BusinessActivityPayScrollBinding) this.binding).wxPayCheckLayout.post(() -> {
                LinearLayout wxPayCheckLayout = ((BusinessActivityPayScrollBinding) this.binding).wxPayCheckLayout;
                ((BusinessActivityPayScrollBinding) this.binding).freeDayLayout.removeView(wxPayCheckLayout);
                ((BusinessActivityPayScrollBinding) this.binding).freeDayLayout.addView(wxPayCheckLayout);
            });
        }
        switch (type) {
            case 0:
            case 4:
                ((BusinessActivityPayScrollBinding) this.binding).wxPayCheckLayout.setVisibility(View.VISIBLE);
                ((BusinessActivityPayScrollBinding) this.binding).zfbPayCheckLayout.setVisibility(View.VISIBLE);
                ((BusinessActivityPayScrollBinding) this.binding).freeDay.setVisibility(View.GONE);
                break;
            case 1:
                ((BusinessActivityPayScrollBinding) this.binding).wxPayCheckLayout.setVisibility(View.VISIBLE);
                ((BusinessActivityPayScrollBinding) this.binding).zfbPayCheckLayout.setVisibility(View.GONE);
                ((BusinessActivityPayScrollBinding) this.binding).freeDay.setVisibility(View.GONE);
                break;
            case 2:
                ((BusinessActivityPayScrollBinding) this.binding).wxPayCheckLayout.setVisibility(View.GONE);
                ((BusinessActivityPayScrollBinding) this.binding).zfbPayCheckLayout.setVisibility(View.VISIBLE);
                ((BusinessActivityPayScrollBinding) this.binding).freeDay.setVisibility(View.GONE);
                break;
            case 3:
                ((BusinessActivityPayScrollBinding) this.binding).wxPayCheckLayout.setVisibility(View.GONE);
                ((BusinessActivityPayScrollBinding) this.binding).zfbPayCheckLayout.setVisibility(View.GONE);
                ((BusinessActivityPayScrollBinding) this.binding).freeDay.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void pay() {
        if (isCheckedPermission()) {
            pay(false);
        } else {
            ToastUtils.show(Super.getContext(), "请选阅读并同意协议");
            ((BusinessActivityPayScrollBinding) this.binding).scrollView.scrollBy(0, 9999);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void pay(boolean isDialogPay) {
        if (isDialogPay) {
            if (this.dialogPayItem != null) {
                try {
                    if (this.isDoublePayType) {
                        if (this.isWxPay) {
                            WeChat.getInstance().payNativeNew(this, this.dialogPayItem.price_info.get(0).id + BuildConfig.VERSION_NAME, this.payInfo.id + BuildConfig.VERSION_NAME, "0");
                        } else {
                            WeChat.getInstance().payNativeNew(this, this.dialogPayItem.price_info.get(0).id + BuildConfig.VERSION_NAME, this.payInfoZfb.id + BuildConfig.VERSION_NAME, "1");
                        }
                    } else {
                        WeChat.getInstance().payNativeNew(this, this.dialogPayItem.price_info.get(0).id + BuildConfig.VERSION_NAME, this.payInfo.id + BuildConfig.VERSION_NAME, "0");
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
                            }
                        } else if (call.equals("submit")) {
                            if (isCheckedPermission()) {
                                this.exitPayDialog.dismiss();
                                pay(true);
                            } else {
                                ToastUtils.show(Super.getContext(), "请选阅读并同意协议");
                                ((BusinessActivityPayScrollBinding) this.binding).scrollView.scrollBy(0, 9999);
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
    }

    protected void onResume() {
        super.onResume();
    }

    @Override // ps.center.views.DataChanger.DataChangeCallBack
    public void change(int action, Object object) {
        switch (action) {
            case 3:
                showLoginBtn();
                if (this.action.equals("前置付费") && CenterConstant.getUser().isVip) {
                    goMainActivity();
                    super.finish();
                    DataChangeManager.get().unregisterChangCallBack(this);
                    break;
                }
                break;
            case 5:
                CenterHttp.get().getUserInfo(new Result<User>() { // from class: ps.center.application.pay.PayScrollActivity.11
                    @Override // ps.center.library.http.base.Result
                    public void success(User obj) {
                        if (obj.isVip) {
                            Iterator<PayPage.GroupsBean.PriceInfoBean> it = PayScrollActivity.this.payInfo.price_info.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                PayPage.GroupsBean.PriceInfoBean item = it.next();
                                if (!item.renew_type.equals("0")) {
                                    PayScrollActivity.this.payInfo.price_info.remove(item);
                                    break;
                                }
                            }
                            if (PayScrollActivity.this.payInfo.price_info.size() >= 3) {
                                PayScrollActivity.this.cardPayAdapter.notifyDataSetChanged();
                            }
                        }
                        if (PayScrollActivity.this.hideReturn) {
                            PayScrollActivity.this.hideReturn = false;
                        }
                        DataChangeManager.get().change(1, BuildConfig.VERSION_NAME);
                        PayScrollActivity.this.finish();
                    }
                });
                break;
            case DataChangeStatus.PAY_CANCEL /* 6 */:
                if (!this.action.equals("前置付费") || !BusinessConstant.getConfig().standard_conf.pay_page.func.pay_retain_sw.equals("1") || showExitDialog()) {
                }
                break;
        }
    }

    /* loaded from: classes.jar:ps/center/application/pay/PayScrollActivity$PayBots.class */
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