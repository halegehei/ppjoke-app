package ps.center.application.mine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import ps.center.application.BuildConfig;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.databinding.BusinessActivityMoreSettingBinding;
import ps.center.utils.ui.OnClickListener;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;
import ps.center.views.activity.webview.NativeWebActivity;

/* loaded from: classes.jar:ps/center/application/mine/MoreSettingActivity.class */
public class MoreSettingActivity extends BaseActivityVB<BusinessActivityMoreSettingBinding> {
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, (Class<?>) MoreSettingActivity.class);
        activity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityMoreSettingBinding getLayout() {
        return BusinessActivityMoreSettingBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        AppConfig.StandardConfBean.AgreementContentBean agreementContentBean = BusinessConstant.getConfig().standard_conf.agreement_content;
        if (agreementContentBean.comm.is_active.equals("1")) {
            if (agreementContentBean.func.beian_id.equals("0") || agreementContentBean.func.beian_id.equals(BuildConfig.VERSION_NAME)) {
                ((BusinessActivityMoreSettingBinding) this.binding).beianLayout.setVisibility(View.GONE);
                return;
            }
            ((BusinessActivityMoreSettingBinding) this.binding).beianLayout.setVisibility(View.VISIBLE);
            ((BusinessActivityMoreSettingBinding) this.binding).beianInfo.setText(agreementContentBean.func.beian_id);
            ((BusinessActivityMoreSettingBinding) this.binding).beianInfo.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().mineBeiAnTextColor));
            ((BusinessActivityMoreSettingBinding) this.binding).beianInfo.setPaintFlags(((BusinessActivityMoreSettingBinding) this.binding).beianInfo.getPaintFlags() | 8);
        }
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void setListener() {
        ((BusinessActivityMoreSettingBinding) this.binding).returnBtn.setOnClickListener(new OnClickListener() { // from class: ps.center.application.mine.MoreSettingActivity.1
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                MoreSettingActivity.this.finish();
            }
        });
        ((BusinessActivityMoreSettingBinding) this.binding).beianLayout.setOnClickListener(v -> {
            NativeWebActivity.jump(this, "ICP/IP地址/域名信息备案管理系统", 0, "https://beian.miit.gov.cn/");
        });
        AppConfig.StandardConfBean.AgreementContentBean agreement_content = BusinessConstant.getConfig().standard_conf.agreement_content;
        ((BusinessActivityMoreSettingBinding) this.binding).item1.setVisibility(agreement_content.func.user_agreement.equals("0") ? View.GONE : View.VISIBLE);
        ((BusinessActivityMoreSettingBinding) this.binding).item2.setVisibility(agreement_content.func.privacy_policy.equals("0") ? View.GONE : View.VISIBLE);
        ((BusinessActivityMoreSettingBinding) this.binding).item3.setVisibility(agreement_content.func.pay_agreement.equals("0") ? View.GONE : View.VISIBLE);
        ((BusinessActivityMoreSettingBinding) this.binding).item4.setVisibility(agreement_content.func.auto_renew.equals("0") ? View.GONE : View.VISIBLE);
        ((BusinessActivityMoreSettingBinding) this.binding).item1.setOnClickListener(new OnClickListener() { // from class: ps.center.application.mine.MoreSettingActivity.2
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(MoreSettingActivity.this, "用户协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.user_agreement);
            }
        });
        ((BusinessActivityMoreSettingBinding) this.binding).item2.setOnClickListener(new OnClickListener() { // from class: ps.center.application.mine.MoreSettingActivity.3
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(MoreSettingActivity.this, "隐私政策", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.privacy_policy);
            }
        });
        ((BusinessActivityMoreSettingBinding) this.binding).item3.setOnClickListener(new OnClickListener() { // from class: ps.center.application.mine.MoreSettingActivity.4
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(MoreSettingActivity.this, "付费协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.pay_agreement);
            }
        });
        ((BusinessActivityMoreSettingBinding) this.binding).item4.setOnClickListener(new OnClickListener() { // from class: ps.center.application.mine.MoreSettingActivity.5
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                NativeWebActivity.jump(MoreSettingActivity.this, "自动续费协议", 0, BusinessConstant.getConfig().standard_conf.agreement_content.func.auto_renew);
            }
        });
    }

    public void finish() {
        super.finish();
    }
}