package ps.center.centerinterface.bean;

import android.text.TextUtils;
import java.util.Map;

/* loaded from: classes.jar:ps/center/centerinterface/bean/PayResult.class */
public class PayResult {
    private String resultStatus;
    private String result;
    private String memo;

    public PayResult(Map<String, String> rawResult) {
        if (rawResult == null) {
            return;
        }
        for (String key : rawResult.keySet()) {
            if (TextUtils.equals(key, "resultStatus")) {
                this.resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, "result")) {
                this.result = rawResult.get(key);
            } else if (TextUtils.equals(key, "memo")) {
                this.memo = rawResult.get(key);
            }
        }
    }

    public String toString() {
        return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + "}";
    }

    public String getResultStatus() {
        return this.resultStatus;
    }

    public String getMemo() {
        return this.memo;
    }

    public String getResult() {
        return this.result;
    }
}