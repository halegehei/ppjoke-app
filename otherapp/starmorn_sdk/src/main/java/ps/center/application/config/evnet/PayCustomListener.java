package ps.center.application.config.evnet;

import android.content.Intent;
import androidx.annotation.Nullable;

import ps.center.centerinterface.bean.PayPage;
import ps.center.databinding.BusinessActivityPayBinding;

/* loaded from: classes.jar:ps/center/application/config/evnet/PayCustomListener.class */
public abstract class PayCustomListener extends PayCustomAbsListener {
    @Override // ps.center.application.config.evnet.PayCustomAbsListener
    public void rootBindingObj(@Nullable BusinessActivityPayBinding businessActivityPayBinding) {
    }

    @Override // ps.center.application.config.evnet.PayCustomAbsListener
    public void payPageInitData(@Nullable Intent intent) {
    }

    @Override // ps.center.application.config.evnet.PayCustomAbsListener
    public void payPageFinishEnd() {
    }

    @Override // ps.center.application.config.evnet.PayCustomAbsListener
    public boolean readyDataOpenPayActivityBefore(String action, PayPage payPage) {
        return true;
    }
}