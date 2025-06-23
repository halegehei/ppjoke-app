package ps.center.application.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import ps.center.application.BuildConfig;
import ps.center.application.DataChangeEvent.DataChangeStatus;
import ps.center.business.http.base.BusHttp;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.databinding.BusinessActivityFeedbackBinding;
import ps.center.utils.ManifestUtils;
import ps.center.utils.Save;
import ps.center.utils.ToastUtils;
import ps.center.utils.exception.ExceptionLogsActivity;
import ps.center.utils.ui.OnClickListener;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;

/* loaded from: classes.jar:ps/center/application/mine/FeedbackActivity.class */
public class FeedbackActivity extends BaseActivityVB<BusinessActivityFeedbackBinding> {
    private String feedbackType = "请选择反馈类型";
    private String title = "问题反馈";

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) FeedbackActivity.class));
    }

    public static void start(Activity activity, String title) {
        activity.startActivity(new Intent(activity, (Class<?>) FeedbackActivity.class).putExtra("title", title));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityFeedbackBinding getLayout() {
        return BusinessActivityFeedbackBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        this.title = getIntent().getStringExtra("title");
        if (this.title != null) {
            try {
                ((BusinessActivityFeedbackBinding) this.binding).title.setText(this.title);
            } catch (Exception e) {
                ((BusinessActivityFeedbackBinding) this.binding).title.setText("问题反馈");
            }
        }
        ((BusinessActivityFeedbackBinding) this.binding).submit.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.mine.FeedbackActivity.1
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                String business_sdk_version;
                if (((BusinessActivityFeedbackBinding) FeedbackActivity.this.binding).editV.getText().toString().contains("设备信息")) {
                    new DeviceDialog(FeedbackActivity.this).show();
                    return;
                }
                if (!"报错日志".equals(((BusinessActivityFeedbackBinding) FeedbackActivity.this.binding).editV.getText().toString().trim())) {
                    if (FeedbackActivity.this.feedbackType.equals("请选择反馈类型")) {
                        ToastUtils.show(FeedbackActivity.this, "请选择反馈类型");
                        return;
                    }
                    if (((BusinessActivityFeedbackBinding) FeedbackActivity.this.binding).editV.getText().toString().trim().equals(BuildConfig.VERSION_NAME)) {
                        ToastUtils.show(FeedbackActivity.this, "请输入内容");
                        ((BusinessActivityFeedbackBinding) FeedbackActivity.this.binding).editV.setText(BuildConfig.VERSION_NAME);
                        return;
                    }
                    try {
                        business_sdk_version = ManifestUtils.getMetaDataValue(FeedbackActivity.this, "business_sdk_version");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        business_sdk_version = "NONE";
                    }
                    try {
                        String content = ((BusinessActivityFeedbackBinding) FeedbackActivity.this.binding).editV.getText().toString();
                        BusHttp.bot().dingBot((((((((((((content + "\n") + "用户ID：" + Save.instance.getLong(CenterConstant.UID, -1L)) + "\n") + "用户昵称：" + CenterConstant.getUser().username) + "\n") + "用户电话：" + ((BusinessActivityFeedbackBinding) FeedbackActivity.this.binding).phoneEdit.getText().toString().trim()) + "\n") + "会员状态：" + CenterConstant.getUser().isVip) + "\n") + "设备：SDK( " + Build.VERSION.SDK_INT + "; " + business_sdk_version + " ); OS( " + Build.BRAND + " )") + "\n") + "反馈类型：" + FeedbackActivity.this.feedbackType, null);
                    } catch (Exception e3) {
                    }
                    ToastUtils.show(FeedbackActivity.this, "提交成功, 我们会尽快处理您的问题。");
                    FeedbackActivity.this.finish();
                    return;
                }
                ExceptionLogsActivity.start(FeedbackActivity.this);
            }
        });
        ((BusinessActivityFeedbackBinding) this.binding).returnBtnV.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.mine.FeedbackActivity.2
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                FeedbackActivity.this.finish();
            }
        });
        ((BusinessActivityFeedbackBinding) this.binding).messageType.setOnClickListener(v -> {
            new FeedbackTypeDialog(this, call -> {
                int type = ((Integer) call).intValue();
                switch (type) {
                    case 1:
                        this.feedbackType = "闪退&崩溃";
                        break;
                    case 2:
                        this.feedbackType = "界面加载缓慢";
                        break;
                    case 3:
                        this.feedbackType = "内容与宣传不符";
                        break;
                    case 4:
                        this.feedbackType = "结果返回缓慢";
                        break;
                    case 5:
                        this.feedbackType = "无法返回结果";
                        break;
                    case DataChangeStatus.PAY_CANCEL /* 6 */:
                        this.feedbackType = "涉嫌欺诈";
                        break;
                    case 7:
                        this.feedbackType = "其他";
                        break;
                }
                ((BusinessActivityFeedbackBinding) this.binding).feedbackType.setText(this.feedbackType);
            }).show();
        });
    }
}