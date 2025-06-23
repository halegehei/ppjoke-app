package ps.center.application.guide;

import android.content.Context;
import android.content.Intent;
import androidx.viewbinding.ViewBinding;
import ps.center.application.BuildConfig;
import ps.center.application.manager.PayManager;
import ps.center.business.BusinessConstant;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.utils.Save;
import ps.center.views.activity.BaseActivityVB;

/* loaded from: classes.jar:ps/center/application/guide/CustomGuidePager.class */
public abstract class CustomGuidePager<T extends ViewBinding> extends BaseActivityVB<T> {
    protected void onStart() {
        super.onStart();
        Save.instance.put("isGuideWelcomePager", (Object) true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void jumpNextPager() {
        if (CenterConstant.getUser().isVip) {
            jumpMainActivity();
        } else if (BusinessConstant.getConfig().standard_conf.pay_page.comm.is_active.equals("1") && BusinessConstant.getConfig().standard_conf.pay_page.func.prepay_sw.equals("1")) {
            new PayManager(this, "前置付费", 0).go();
        } else {
            jumpMainActivity();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void jumpMainActivity() {
        String mainActivityClassName = Save.instance.getString("mainActivityClassName", BuildConfig.VERSION_NAME);
        try {
            Class<?> mainActivityClass = Class.forName(mainActivityClassName);
            Intent intent = new Intent((Context) this, mainActivityClass);
            startActivity(intent);
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}