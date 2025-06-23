package ps.center.views.activity;

import android.app.Activity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewbinding.ViewBinding;
import java.util.Iterator;
import java.util.Map;
import ps.center.application.BuildConfig;
import ps.center.utils.ActivityUtils;
import ps.center.utils.MD5Utils;
import ps.center.utils.Save;
import ps.center.views.toolsViews.PermissionRequestTipsDialog;


public abstract class PermissionBaseActivity<T extends ViewBinding> extends BaseActivityVB<T> {
    private CallBack permissionCallBack;
    private String permissionTags = BuildConfig.VERSION_NAME;
    private final ActivityResultLauncher<String[]> permissionsRequest = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
        if (this.permissionCallBack != null) {
            boolean isAllSuccess = true;
            Iterator it = result.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, Boolean> item = (Map.Entry) it.next();
                if (!item.getValue().booleanValue()) {
                    isAllSuccess = false;
                    break;
                }
            }
            String tagMd5 = MD5Utils.md5(this.permissionTags);
            Save.instance.put(tagMd5, (Object) false);
            this.permissionCallBack.result(isAllSuccess);
        }
    });

    /* loaded from: classes.jar:ps/center/views/activity/PermissionBaseActivity$CallBack.class */
    public interface CallBack {
        void result(boolean z);
    }

    public void requestPermission(String[] permissions, CallBack permissionCallBack) {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("尊敬的用户您好, 您当前使用的功能需要申请以下权限才能正常使用：");
        stringBuffer.append("\n");
        for (String permission : permissions) {
            stringBuffer.append(permission);
            stringBuffer.append("\n");
        }
        stringBuffer.append("若您希望继续使用本功能，请前往授权。");
        requestPermission(stringBuffer.toString(), permissions, permissionCallBack);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void requestPermission(String tips, String[] permissions, CallBack permissionCallBack) {
        this.permissionTags = tips;
        this.permissionCallBack = permissionCallBack;
        String tagMd5 = MD5Utils.md5(this.permissionTags);
        boolean isRequest = Save.instance.getBoolean(tagMd5, true);
        if (isRequest) {
            new PermissionRequestTipsDialog(this, this.permissionTags, call -> {
                if (call.equals("submit")) {
                    this.permissionsRequest.launch(permissions);
                }
            }).show();
            return;
        }
        if (isAllSuccess(permissions)) {
            permissionCallBack.result(true);
            return;
        }
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("您尚未授权本功能以下权限：");
        stringBuffer.append("\n");
        for (String permission : permissions) {
            stringBuffer.append(permission);
            stringBuffer.append("\n");
        }
        stringBuffer.append("若您希望继续使用，可前往设置页面开启权限后再试。");
        new PermissionRequestTipsDialog(this, "提示", stringBuffer.toString(), "取消", "去设置", call2 -> {
            ActivityUtils.goAppSettings((Activity) this);
        }).show();
    }

    private boolean isAllSuccess(String[] permission) {
        boolean isAllSuccess = true;
        int length = permission.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String item = permission[i];
            if (isGrantedPermission(item)) {
                i++;
            } else {
                isAllSuccess = false;
                break;
            }
        }
        return isAllSuccess;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isGrantedPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != 0) {
            return false;
        }
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission) && ContextCompat.checkSelfPermission(this, permission) != 0) {
            return false;
        }
        return true;
    }
}