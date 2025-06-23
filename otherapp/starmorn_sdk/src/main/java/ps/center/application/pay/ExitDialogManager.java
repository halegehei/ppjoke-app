package ps.center.application.pay;

import android.content.Context;
import java.util.ArrayList;
import ps.center.business.BusinessConstant;
import ps.center.centerinterface.bean.PayPage;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/pay/ExitDialogManager.class */
public class ExitDialogManager {
    private final String action;
    private final PayPage.GroupsBean.PriceInfoBean obj;
    private final ArrayList<String> dialogShowRules;
    private boolean checkPermission;
    private final BaseDialogVB2.Call call;
    private Context context;
    private ExitPayDialog exitPayDialog;
    private ExitPay2Dialog exitPay2Dialog;
    private ExitPay3Dialog exitPay3Dialog;

    public ExitDialogManager(Context context, String action, PayPage.GroupsBean.PriceInfoBean priceInfoBean, ArrayList<String> dialogShowRules, boolean checkPermission, BaseDialogVB2.Call call) {
        this.context = context;
        this.obj = priceInfoBean;
        this.call = call;
        this.dialogShowRules = dialogShowRules;
        this.action = action;
        this.checkPermission = checkPermission;
    }

    public void show() {
        String popupSwitch = BusinessConstant.getConfig().standard_conf.pay_page.func.popup_switch;
        if (popupSwitch.equals("1")) {
            this.exitPayDialog = new ExitPayDialog(this.context, this.action, this.obj, this.dialogShowRules, this.checkPermission, this.call);
            this.exitPayDialog.show();
        } else if (popupSwitch.equals("2")) {
            this.exitPay2Dialog = new ExitPay2Dialog(this.context, this.action, this.obj, this.dialogShowRules, this.checkPermission, this.call);
            this.exitPay2Dialog.show();
        } else if (popupSwitch.equals("3")) {
            this.exitPay3Dialog = new ExitPay3Dialog(this.context, this.action, this.obj, this.dialogShowRules, this.checkPermission, this.call);
            this.exitPay3Dialog.show();
        }
    }

    public void dismiss() {
        String popupSwitch = BusinessConstant.getConfig().standard_conf.pay_page.func.popup_switch;
        if (popupSwitch.equals("1") && this.exitPayDialog != null) {
            this.exitPayDialog.dismiss();
            return;
        }
        if (popupSwitch.equals("2") && this.exitPay2Dialog != null) {
            this.exitPay2Dialog.dismiss();
        } else if (popupSwitch.equals("3") && this.exitPay3Dialog != null) {
            this.exitPay3Dialog.dismiss();
        }
    }
}