package ps.center.utils;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.jar:ps/center/utils/Permission.class */
public final class Permission {
    private boolean isAllGranted = true;
    private FragmentActivity activity;

    /* loaded from: classes.jar:ps/center/utils/Permission$Result.class */
    public interface Result {
        void result(Status status);
    }

    /* loaded from: classes.jar:ps/center/utils/Permission$Status.class */
    public enum Status {
        OK,
        NO,
        EVER_NO
    }

    public static Permission newInstance(FragmentActivity activity) {
        return new Permission(activity);
    }

    private Permission(FragmentActivity activity) {
        this.activity = activity;
    }

    private Permission() {
    }

    public Status check(String permission) {
        if (ContextCompat.checkSelfPermission(this.activity, permission) != 0) {
            return Status.NO;
        }
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this.activity, permission) && ContextCompat.checkSelfPermission(this.activity, permission) != 0) {
            return Status.EVER_NO;
        }
        return Status.OK;
    }

    public Status checkResult(String[] permissions, int[] granted) {
        int length = granted.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int grant = granted[i];
            if (grant == 0) {
                i++;
            } else {
                this.isAllGranted = false;
                break;
            }
        }
        if (this.isAllGranted) {
            return Status.OK;
        }
        List<String> notAsk = new ArrayList<>();
        for (String permission : permissions) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this.activity, permission) && ContextCompat.checkSelfPermission(this.activity, permission) != 0) {
                notAsk.add(permission);
            }
        }
        if (notAsk.size() > 0) {
            return Status.EVER_NO;
        }
        return Status.NO;
    }

    public boolean isGranted(String permission) {
        if (ContextCompat.checkSelfPermission(this.activity, permission) != 0) {
            return false;
        }
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this.activity, permission) && ContextCompat.checkSelfPermission(this.activity, permission) != 0) {
            return false;
        }
        return true;
    }
}