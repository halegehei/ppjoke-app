package ps.center.utils.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/ui/TextViewUtils.class */
public class TextViewUtils {
    public static void setText(TextView textView, String text, boolean isNullHide) {
        if (textView != null) {
            if (TextUtils.isEmpty(text)) {
                textView.setText(BuildConfig.VERSION_NAME);
                textView.setVisibility(isNullHide ? View.GONE : textView.getVisibility());
            } else {
                textView.setText(text);
            }
        }
    }
}