package ps.center.application.manager;

import android.app.Activity;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ps.center.application.config.AppCustomEvent;
import ps.center.application.manager.payInfo.PayItemCallback;
import ps.center.application.pay.ListPayActivity;
import ps.center.application.pay.PayActivity;
import ps.center.application.pay.PayScrollActivity;
import ps.center.business.BusinessConstant;
import ps.center.business.http.base.BusHttp;
import ps.center.centerinterface.bean.PayPage;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.library.http.base.Result;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.views.dialog.loading.LoadingDialog;

/* loaded from: classes.jar:ps/center/application/manager/PayManager.class */
public class PayManager {
    private final String timeTag;
    private LoadingDialog loadingDialog;
    private final String action;
    private final int requestCode;
    private final Activity activity;
    private boolean hideReturn;
    private PayItemCallback payItemCallback;

    public PayManager(Activity activity, String action, int requestCode) {
        this.hideReturn = false;
        this.activity = activity;
        this.action = action;
        this.requestCode = requestCode;
        this.timeTag = String.format("%s%s", action, "firstOpenPayPagerTime");
    }

    public PayManager(Activity activity, String action, int requestCode, boolean hideReturn) {
        this.hideReturn = false;
        this.activity = activity;
        this.action = action;
        this.requestCode = requestCode;
        this.hideReturn = hideReturn;
        this.timeTag = String.format("%s%s", action, "firstOpenPayPagerTime");
    }

    public PayManager(Activity activity, String action, int requestCode, PayItemCallback payItemCallback) {
        this.hideReturn = false;
        this.activity = activity;
        this.action = action;
        this.requestCode = requestCode;
        this.payItemCallback = payItemCallback;
        this.timeTag = String.format("%s%s", action, "firstOpenPayPagerTime");
    }

    public void go() {
        if (this.activity != null && !this.activity.isFinishing()) {
            this.loadingDialog = new LoadingDialog(this.activity);
            this.loadingDialog.show();
        }
        getPayHttpInfo();
    }

    private void getPayHttpInfo() {
        CenterHttp.get().getPayPage(new Result<PayPage>() { // from class: ps.center.application.manager.PayManager.1
            @Override // ps.center.library.http.base.Result
            public void success(PayPage payPage) {
                if (PayManager.this.loadingDialog != null) {
                    PayManager.this.loadingDialog.dismiss();
                    PayManager.this.loadingDialog = null;
                }
                boolean isNext = AppCustomEvent.get().getPayCustomListener().readyDataOpenPayActivityBefore(PayManager.this.action, payPage);
                if (isNext) {
                    PayManager.this.checkGotoPayInfo(payPage);
                }
            }

            @Override // ps.center.library.http.base.Result
            public void err(int code, String message) {
                ToastUtils.show(Super.getContext(), "网络状态不佳，请重试");
                BusHttp.bot().dingBotErr(String.format("获取价格列表错误, code=%s, message=%s", Integer.valueOf(code), message), null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:65:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0223  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkGotoPayInfo(ps.center.centerinterface.bean.PayPage r11) {
        /*
            Method dump skipped, instructions count: 589
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: ps.center.application.manager.PayManager.checkGotoPayInfo(ps.center.centerinterface.bean.PayPage):void");
    }

    private void getSingleItemPayInfo(PayPage payPage) {
        ArrayList<String> showRules;
        PayPage.GroupsBean payInfo;
        if (payPage.groups.size() >= 4 && payPage.groups.size() <= 6 && payPage.show_rules.size() >= 4 && payPage.show_rules.size() <= 6) {
            ArrayList<String> arrayList = (ArrayList) payPage.show_rules.get(3);
            showRules = arrayList;
            payInfo = isTimeOutOrNoTime(payPage, arrayList);
        } else if (payPage.groups.size() > 6 && payPage.show_rules.size() > 6) {
            ArrayList<String> arrayList2 = (ArrayList) payPage.show_rules.get(6);
            showRules = arrayList2;
            payInfo = isTimeOutOrNoTime(payPage, arrayList2);
        } else {
            ToastUtils.show(Super.getContext(), "模版数必须大于3或大于6");
            return;
        }
        try {
            PayPage.GroupsBean.PriceInfoBean[] picList = new PayPage.GroupsBean.PriceInfoBean[payInfo.price_info.size()];
            for (int i = 0; i < payInfo.price_info.size(); i++) {
                picList[Integer.parseInt(payInfo.price_info.get(i).sort) - 1] = payInfo.price_info.get(i);
            }
            payInfo.price_info.clear();
            payInfo.price_info.addAll(Arrays.asList(picList));
        } catch (Exception e) {
            Log.e("PAY_MANAGER", "排序参数错误，放弃排序");
        }
        this.payItemCallback.result(payInfo, showRules);
    }

    private void onSortPayList(PayPage.GroupsBean payInfo, PayPage.GroupsBean dialogPayInfo, ArrayList<String> showRules, ArrayList<String> dialogShowRules, PayPage.GroupsBean payInfoZfb, PayPage.GroupsBean dialogPayInfoZfb, ArrayList<String> showRulesZfb, ArrayList<String> dialogShowRulesZfb) {
        try {
            PayPage.GroupsBean.PriceInfoBean[] picList = new PayPage.GroupsBean.PriceInfoBean[payInfo.price_info.size()];
            for (int i = 0; i < payInfo.price_info.size(); i++) {
                picList[Integer.parseInt(payInfo.price_info.get(i).sort) - 1] = payInfo.price_info.get(i);
            }
            payInfo.price_info.clear();
            payInfo.price_info.addAll(Arrays.asList(picList));
        } catch (Exception e) {
            ToastUtils.show(Super.getContext(), "排序参数错误");
        }
        if (payInfoZfb != null) {
            try {
                PayPage.GroupsBean.PriceInfoBean[] picList2 = new PayPage.GroupsBean.PriceInfoBean[payInfoZfb.price_info.size()];
                for (int i2 = 0; i2 < payInfoZfb.price_info.size(); i2++) {
                    picList2[Integer.parseInt(payInfoZfb.price_info.get(i2).sort) - 1] = payInfoZfb.price_info.get(i2);
                }
                payInfoZfb.price_info.clear();
                payInfoZfb.price_info.addAll(Arrays.asList(picList2));
            } catch (Exception e2) {
                ToastUtils.show(Super.getContext(), "支付宝排序参数错误");
            }
        }
        gotoPayPager(payInfo, dialogPayInfo, showRules, dialogShowRules, payInfoZfb, dialogPayInfoZfb, showRulesZfb, dialogShowRulesZfb);
    }

    private void gotoPayPager(PayPage.GroupsBean payInfo, PayPage.GroupsBean dialogPayInfo, ArrayList<String> showRules, ArrayList<String> dialogShowRules, PayPage.GroupsBean payInfoZfb, PayPage.GroupsBean dialogPayInfoZfb, ArrayList<String> showRulesZfb, ArrayList<String> dialogShowRulesZfb) {
        if (this.activity == null || this.activity.isFinishing()) {
            return;
        }
        String payPageSwitch = BusinessConstant.getConfig().standard_conf.pay_page.func.pay_page_switch;
        if (payPageSwitch.equals("1")) {
            PayActivity.start(this.activity, payInfo, dialogPayInfo, showRules, dialogShowRules, payInfoZfb, dialogPayInfoZfb, showRulesZfb, dialogShowRulesZfb, this.hideReturn, this.action, this.requestCode);
            return;
        }
        if (payPageSwitch.equals("2")) {
            PayScrollActivity.start(this.activity, payInfo, dialogPayInfo, showRules, dialogShowRules, payInfoZfb, dialogPayInfoZfb, showRulesZfb, dialogShowRulesZfb, this.hideReturn, this.action, this.requestCode);
        } else if (payPageSwitch.equals("3")) {
            ListPayActivity.start(this.activity, payInfo, dialogPayInfo, showRules, dialogShowRules, payInfoZfb, dialogPayInfoZfb, showRulesZfb, dialogShowRulesZfb, this.hideReturn, this.action, this.requestCode);
        } else {
            BusHttp.bot().dingBotErr("请检查付费页配置字段 pay_page_switch 是否有效", null);
        }
    }

    private PayPage.GroupsBean getGroupItem(PayPage payPage, String modelName) {
        PayPage.GroupsBean result = null;
        for (PayPage.GroupsBean item : payPage.groups) {
            if (item.template_type.equals(modelName)) {
                result = item;
            }
        }
        return result;
    }

    private boolean isTimeOut(long s) {
        long firstOpenPayPagerTime = Save.instance.getLong(this.timeTag, 0L);
        return System.currentTimeMillis() - firstOpenPayPagerTime >= firstOpenPayPagerTime + (s * 1000);
    }

    public String getTimeTag() {
        return this.timeTag;
    }

    private PayPage.GroupsBean isTimeOutOrNoTime(PayPage payPage, List<String> showRules) {
        PayPage.GroupsBean result;
        if (showRules.get(2).equals("0")) {
            result = getGroupItem(payPage, showRules.get(1));
        } else if (isTimeOut(Integer.parseInt(showRules.get(2)))) {
            result = getGroupItem(payPage, showRules.get(1));
        } else {
            result = getGroupItem(payPage, showRules.get(0));
        }
        return result;
    }
}