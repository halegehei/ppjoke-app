package ps.center.business.utils.free;

import android.content.Context;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.library.http.base.Result;
import ps.center.utils.Save;
import ps.center.views.DataChanger.DataChangeManager;

/* loaded from: classes.jar:ps/center/business/utils/free/FreeManager.class */
public class FreeManager {
    private static FreeManager freeManager;
    private static final Object look = new Object();
    private FreeTimeOverListener freeTimeOverListener;
    private AppConfig.StandardConfBean.FreeControlBean freeConfig;
    private User user;
    private boolean isFreeUs;
    private long vipFreeTime;
    private int vipFreeCount;
    private String isTrial = "0";

    /* loaded from: classes.jar:ps/center/business/utils/free/FreeManager$FreeTimeOverListener.class */
    public interface FreeTimeOverListener {
        void timeOver();
    }

    /* loaded from: classes.jar:ps/center/business/utils/free/FreeManager$ReceiveFreeVipListener.class */
    public interface ReceiveFreeVipListener {
        void receiveFreeUserSuccess(FreeReceiveStatus freeReceiveStatus);

        void receiveFreeUserErr(FreeReceiveStatus freeReceiveStatus);
    }

    public static FreeManager get() {
        FreeManager freeManager2;
        synchronized (look) {
            if (freeManager == null) {
                freeManager = new FreeManager();
            }
            freeManager2 = freeManager;
        }
        return freeManager2;
    }

    private FreeManager() {
    }

    private void preData() {
        this.freeConfig = BusinessConstant.getConfig().standard_conf.free_control;
        this.user = CenterConstant.getUser();
        this.isFreeUs = Save.instance.getBoolean("isFreeUs", false);
        this.vipFreeTime = Save.instance.getLong("vipCountDownTime", 0L);
        this.vipFreeCount = Save.instance.getInt("vipFreeCount", 0);
    }

    public boolean isFreeUser() {
        if (CenterConstant.getUser() == null || BusinessConstant.getConfig() == null) {
            return false;
        }
        preData();
        if (!BusinessConstant.getConfig().standard_conf.pay_page.comm.is_active.equals("1") || !BusinessConstant.getConfig().standard_conf.pay_page.func.backpay_sw.equals("1")) {
            return true;
        }
        if (this.freeConfig.comm.is_active.equals("1") && this.isFreeUs) {
            if (this.freeConfig.func.free_time_sw.equals("1") || this.freeConfig.func.free_num_sw.equals("1")) {
                if (this.vipFreeCount > 0 || this.vipFreeTime - System.currentTimeMillis() > 0) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public void setDialogColors(String dialogBackColor, String titleTextColor, String contentTextColor, String buttonColor, String buttonTextColor) {
        Save.instance.put("freeDialogDialogBackColor", dialogBackColor);
        Save.instance.put("freeContentTextColor", contentTextColor);
        Save.instance.put("freeTitleTextColor", titleTextColor);
        Save.instance.put("freeButtonColor", buttonColor);
        Save.instance.put("freeButtonTextColor", buttonTextColor);
    }

    public String getHttpHeaderToMoney() {
        return (CenterConstant.getUser() == null || BusinessConstant.getConfig() == null || isFreeUser()) ? "0" : this.user.vip_money;
    }

    public String getHttpHeaderToIsTrial() {
        if (CenterConstant.getUser() == null || BusinessConstant.getConfig() == null) {
            return "0";
        }
        return this.isTrial;
    }

    public boolean isShowFreeFunction() {
        preData();
        return !this.user.isVip && this.freeConfig.comm.is_active.equals("1") && (this.freeConfig.func.free_num_sw.equals("1") || this.freeConfig.func.free_time_sw.equals("1"));
    }

    public void receiveFreeUser(final ReceiveFreeVipListener receiveFreeVipListener) {
        preData();
        if (CenterConstant.getUser() == null || BusinessConstant.getConfig() == null) {
            return;
        }
        if (!this.user.isVip && !this.isFreeUs) {
            if (this.freeConfig.comm.is_active.equals("1")) {
                if (this.freeConfig.func.free_num_sw.equals("1")) {
                    Save.instance.put("vipFreeCount", Integer.valueOf(Integer.parseInt(this.freeConfig.func.free_num)));
                    Save.instance.put("isFreeUs", (Object) true);
                    receiveFreeVipListener.receiveFreeUserSuccess(FreeReceiveStatus.FREE_RECEIVE_COUNT_SUCCESS);
                    return;
                } else {
                    if (this.freeConfig.func.free_time_sw.equals("1")) {
                        Save.instance.put("vipCountDownTime", Long.valueOf(System.currentTimeMillis() + (Integer.parseInt(this.freeConfig.func.free_time) * 1000)));
                        Save.instance.put("isFreeUs", (Object) true);
                        CenterHttp.get().getUserInfo(new Result<User>() { // from class: ps.center.business.utils.free.FreeManager.1
                            @Override // ps.center.library.http.base.Result
                            public void success(User obj) {
                                DataChangeManager.get().change(1, obj);
                                receiveFreeVipListener.receiveFreeUserSuccess(FreeReceiveStatus.FREE_RECEIVE_TIME_SUCCESS);
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (this.isFreeUs) {
            receiveFreeVipListener.receiveFreeUserErr(FreeReceiveStatus.FREE_RECEIVE_OVER);
        } else {
            receiveFreeVipListener.receiveFreeUserErr(FreeReceiveStatus.VIP_NOT_CAN_RECEIVE);
        }
    }

    public <T> void us(Context context, UsFreeListener<T> usFreeListener) {
        us(context, null, usFreeListener);
    }

    public <T> void us(Context context, T obj, UsFreeListener<T> usFreeListener) {
        preData();
        if (CenterConstant.getUser() == null || BusinessConstant.getConfig() == null) {
            return;
        }
        if (this.user.isVip) {
            this.isTrial = "0";
            usFreeListener.success(obj);
            return;
        }
        if (isFreeUser()) {
            if (this.vipFreeCount > 0) {
                if (this.freeConfig.func.free_show.equals("1")) {
                    new FreeNumDialog(context, String.valueOf(this.vipFreeCount), call -> {
                        if (call.equals("submit")) {
                            this.isTrial = "1";
                            usFreeListener.success(obj);
                            this.vipFreeCount--;
                            Save.instance.put("vipFreeCount", Integer.valueOf(this.vipFreeCount));
                            return;
                        }
                        usFreeListener.onCancelFreeDialog();
                    }).show();
                    return;
                }
                this.isTrial = "1";
                usFreeListener.success(obj);
                this.vipFreeCount--;
                Save.instance.put("vipFreeCount", Integer.valueOf(this.vipFreeCount));
                return;
            }
            if (this.vipFreeTime - System.currentTimeMillis() > 0) {
                this.isTrial = "1";
                usFreeListener.success(obj);
                return;
            } else if (!BusinessConstant.getConfig().standard_conf.pay_page.comm.is_active.equals("1") || !BusinessConstant.getConfig().standard_conf.pay_page.func.backpay_sw.equals("1")) {
                this.isTrial = "1";
                usFreeListener.success(obj);
                return;
            } else {
                this.isTrial = "0";
                usFreeListener.notFree();
                return;
            }
        }
        if (this.user.isVip || !BusinessConstant.getConfig().standard_conf.pay_page.comm.is_active.equals("1") || !BusinessConstant.getConfig().standard_conf.pay_page.func.backpay_sw.equals("1")) {
            this.isTrial = "1";
            usFreeListener.success(obj);
        } else {
            this.isTrial = "0";
            usFreeListener.notFree();
        }
    }

    public void registerFreeTimeOverListener(FreeTimeOverListener freeTimeOverListener) {
        this.freeTimeOverListener = freeTimeOverListener;
    }

    public FreeTimeOverListener getFreeTimeOverListener() {
        return this.freeTimeOverListener;
    }

    /* loaded from: classes.jar:ps/center/business/utils/free/FreeManager$UsFreeListener.class */
    public static abstract class UsFreeListener<T> {
        public abstract void success(T t);

        public abstract void notFree();

        public void onCancelFreeDialog() {
        }
    }
}