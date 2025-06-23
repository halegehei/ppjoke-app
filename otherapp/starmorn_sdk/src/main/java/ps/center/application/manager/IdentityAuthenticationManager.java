package ps.center.application.manager;

import android.content.Context;
import ps.center.application.dialog.IdentityAuthenticationDialog;
import ps.center.application.dialog.IdentityAuthenticationTipsDialog;
import ps.center.business.BusinessConstant;
import ps.center.utils.Save;

/* loaded from: classes.jar:ps/center/application/manager/IdentityAuthenticationManager.class */
public final class IdentityAuthenticationManager {
    private static final Object lock = new Object();
    private static volatile IdentityAuthenticationManager identityAuthenticationManager;

    private IdentityAuthenticationManager() {
    }

    public static IdentityAuthenticationManager get() {
        if (identityAuthenticationManager == null) {
            synchronized (lock) {
                if (identityAuthenticationManager == null) {
                    identityAuthenticationManager = new IdentityAuthenticationManager();
                }
            }
        }
        return identityAuthenticationManager;
    }

    public boolean getStatus(Context context) {
        boolean identityAuthenticationStatus = Save.instance.getBoolean("IdentityAuthenticationStatus", false);
        if (BusinessConstant.getConfig().standard_conf.complaint_control.comm.is_active.equals("0") || BusinessConstant.getConfig().standard_conf.complaint_control.func.authentication_sw.equals("0")) {
            return true;
        }
        if (!identityAuthenticationStatus) {
            new IdentityAuthenticationTipsDialog(context, call -> {
                new IdentityAuthenticationDialog(context, call2 -> {
                }).show();
            }).show();
        }
        return identityAuthenticationStatus;
    }
}