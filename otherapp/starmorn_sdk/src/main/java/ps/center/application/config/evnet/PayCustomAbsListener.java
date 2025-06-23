package ps.center.application.config.evnet;

import android.content.Intent;
import androidx.annotation.Nullable;

import ps.center.centerinterface.bean.PayPage;
import ps.center.databinding.BusinessActivityPayBinding;

/* loaded from: classes.jar:ps/center/application/config/evnet/PayCustomAbsListener.class */
public abstract class PayCustomAbsListener {
    public abstract void rootBindingObj(@Nullable BusinessActivityPayBinding businessActivityPayBinding);

    public abstract void payPageInitData(@Nullable Intent intent);

    public abstract void payPageFinishEnd();

    public abstract boolean readyDataOpenPayActivityBefore(String str, PayPage payPage);
}