package ps.center.utils.exception;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import ps.center.R;
import ps.center.utils.ClipboardUtils;
import ps.center.utils.OrSuccess;

/* loaded from: classes.jar:ps/center/utils/exception/ExceptionDetailsActivity.class */
public class ExceptionDetailsActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_exception_details_activity);
        TextView content = (TextView) findViewById(R.id.content);
        String contentText = getIntent().getStringExtra("content");
        if (contentText != null) {
            content.setText(contentText);
            content.setOnLongClickListener(v -> {
                ClipboardUtils.copyToClip(this, contentText, new OrSuccess<String>() { // from class: ps.center.utils.exception.ExceptionDetailsActivity.1
                    @Override // ps.center.utils.OrSuccess
                    public void success(String obj) {
                        Toast.makeText(ExceptionDetailsActivity.this, "复制成功", Toast.LENGTH_LONG).show();
                    }

                    @Override // ps.center.utils.OrSuccess
                    public void err(String obj) {
                    }
                });
                return false;
            });
        } else {
            content.setText("空数据");
        }
    }
}