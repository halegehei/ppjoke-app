package ps.center.views.activity;

import android.content.Intent;
import android.os.Bundle;

public class IntentGet {
    private final Intent intent;

    public IntentGet(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public <T> T getIntentValue(String str, T t) {
        Bundle extras;
        T t2;
        if (this.intent != null && (extras = this.intent.getExtras()) != null && (t2 = (T) extras.get(str)) != null && t != null && t2.getClass().equals(t.getClass())) {
            return t2;
        }
        return t;
    }
}