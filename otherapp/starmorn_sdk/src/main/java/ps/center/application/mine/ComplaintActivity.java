package ps.center.application.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import ps.center.application.BuildConfig;
import ps.center.business.http.base.BusHttp;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.databinding.BusinessActivityComplaintBinding;
import ps.center.utils.ManifestUtils;
import ps.center.utils.Save;
import ps.center.utils.ToastUtils;
import ps.center.utils.exception.ExceptionLogsActivity;
import ps.center.utils.ui.OnClickListener;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;

/* loaded from: classes.jar:ps/center/application/mine/ComplaintActivity.class */
public class ComplaintActivity extends BaseActivityVB<BusinessActivityComplaintBinding> {
    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) ComplaintActivity.class));
    }

    public static void start(Activity activity, String title) {
        activity.startActivity(new Intent(activity, (Class<?>) ComplaintActivity.class).putExtra("title", title));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityComplaintBinding getLayout() {
        return BusinessActivityComplaintBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        ((BusinessActivityComplaintBinding) this.binding).title.setText("投诉举报");
        ((BusinessActivityComplaintBinding) this.binding).submit.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.mine.ComplaintActivity.1
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                String business_sdk_version;
                if (((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).editV.getText().toString().contains("设备信息")) {
                    new DeviceDialog(ComplaintActivity.this).show();
                    return;
                }
                if ("报错日志".equals(((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).editV.getText().toString().trim())) {
                    ExceptionLogsActivity.start(ComplaintActivity.this);
                    return;
                }
                if (((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).editV1.getText().toString().trim().equals(BuildConfig.VERSION_NAME)) {
                    ToastUtils.show(ComplaintActivity.this, "请输入投诉标题");
                    ((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).editV.setText(BuildConfig.VERSION_NAME);
                    return;
                }
                if (((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).editV.getText().toString().trim().equals(BuildConfig.VERSION_NAME)) {
                    ToastUtils.show(ComplaintActivity.this, "请输入投诉内容");
                    ((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).editV.setText(BuildConfig.VERSION_NAME);
                    return;
                }
                if (((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).phoneEdit.getText().toString().trim().equals(BuildConfig.VERSION_NAME)) {
                    ToastUtils.show(ComplaintActivity.this, "请输入联系方式");
                    ((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).editV.setText(BuildConfig.VERSION_NAME);
                    return;
                }
                try {
                    business_sdk_version = ManifestUtils.getMetaDataValue(ComplaintActivity.this, "business_sdk_version");
                } catch (Exception e) {
                    e.printStackTrace();
                    business_sdk_version = "NONE";
                }
                try {
                    String content = "标题:" + ((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).editV1.getText().toString();
                    BusHttp.bot().dingBot((((((((((((((content + "\n") + "内容:" + ((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).editV.getText().toString()) + "\n") + "用户ID：" + Save.instance.getLong(CenterConstant.UID, -1L)) + "\n") + "用户昵称：" + CenterConstant.getUser().username) + "\n") + "用户电话：" + ((BusinessActivityComplaintBinding) ComplaintActivity.this.binding).phoneEdit.getText().toString().trim()) + "\n") + "会员状态：" + CenterConstant.getUser().isVip) + "\n") + "设备：SDK( " + Build.VERSION.SDK_INT + "; " + business_sdk_version + " ); OS( " + Build.BRAND + " )") + "\n") + "反馈类型：投诉举报页面类型", null);
                } catch (Exception e2) {
                }
                ToastUtils.show(ComplaintActivity.this, "投诉提交成功, 我们会尽快处理您的问题。");
                ComplaintActivity.this.finish();
            }
        });
        ((BusinessActivityComplaintBinding) this.binding).returnBtnV.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.mine.ComplaintActivity.2
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                ComplaintActivity.this.finish();
            }
        });
    }
}