package ps.center.application.pay.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import ps.center.application.BuildConfig;
import ps.center.business.BusinessConstant;
import ps.center.utils.LogUtils;
import ps.center.utils.ToastUtils;
import ps.center.views.DataChanger.DataChangeManager;

/* loaded from: classes.jar:ps/center/application/pay/wxapi/BaseWXPayEntryActivity.class */
public class BaseWXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.api = WXAPIFactory.createWXAPI(this, BusinessConstant.getConfig().config.wechat.wechat_appid);
        this.api.handleIntent(getIntent(), this);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    public void onReq(BaseReq req) {
    }

    public void onResp(BaseResp resp) {
        if (resp.errCode == 0) {
            ToastUtils.show(this, "支付完成");
            DataChangeManager.get().change(5, BuildConfig.VERSION_NAME);
        } else {
            ToastUtils.show(this, "支付失败");
            LogUtils.e(resp.errCode + ":" + resp.errStr + ":" + resp.transaction + ":" + resp.openId + BuildConfig.VERSION_NAME + resp.toString());
            DataChangeManager.get().change(6, BuildConfig.VERSION_NAME);
        }
        finish();
    }
}