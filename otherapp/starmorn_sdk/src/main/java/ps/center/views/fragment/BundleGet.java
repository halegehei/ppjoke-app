package ps.center.views.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;

/* loaded from: classes.jar:ps/center/views/fragment/BundleGet.class */
public class BundleGet {
    private Bundle bundle;

    public BundleGet(Bundle bundle) {
        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return this.bundle;
    }

    public <T> T getIntentValue(String str, @NonNull T t) {
        T t2;
        if (this.bundle != null && (t2 = (T) this.bundle.get(str)) != null && t2.getClass().equals(t.getClass())) {
            return t2;
        }
        return t;
    }
}