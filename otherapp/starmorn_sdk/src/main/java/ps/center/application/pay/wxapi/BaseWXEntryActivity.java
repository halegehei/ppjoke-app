package ps.center.application.pay.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import java.util.Map;
import ps.center.application.BuildConfig;
import ps.center.application.manager.WeChat;
import ps.center.utils.LogUtils;
import ps.center.utils.ToastUtils;
import ps.center.views.DataChanger.DataChangeManager;

/* loaded from: classes.jar:ps/center/application/pay/wxapi/BaseWXEntryActivity.class */
public class BaseWXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.api = WeChat.getInstance().getApi();
        try {
            if (!this.api.handleIntent(getIntent(), this)) {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        String state;
        LogUtils.e("错误状态码：" + baseResp.errCode);
        switch (baseResp.errCode) {
            case -4:
                LogUtils.e("onResp ERR_AUTH_DENIED");
                finish();
                break;
            case -3:
            case -1:
            default:
                LogUtils.e("onResp default errCode " + baseResp.errCode);
                finish();
                break;
            case -2:
                LogUtils.e("onResp ERR_USER_CANCEL ");
                finish();
                break;
            case 0:
                switch (baseResp.getType()) {
                    case 2:
                        finish();
                        break;
                    case 5:
                        finish();
                        break;
                    case 19:
                        WXLaunchMiniProgram.Resp launchMiniProResp = (WXLaunchMiniProgram.Resp) baseResp;
                        String extraData = launchMiniProResp.extMsg;
                        Gson gson = new Gson();
                        Map<String, String> map = (Map) gson.fromJson(extraData, Map.class);
                        if (map != null && (state = map.get("rspMsg")) != null) {
                            if (state.equals("支付成功")) {
                                ToastUtils.show(this, "支付完成");
                                DataChangeManager.get().change(5, BuildConfig.VERSION_NAME);
                            } else {
                                ToastUtils.show(this, "支付失败");
                                DataChangeManager.get().change(6, BuildConfig.VERSION_NAME);
                            }
                        }
                        finish();
                        break;
                    default:
                        finish();
                        break;
                }
                if (baseResp instanceof SendAuth.Resp) {
                    SendAuth.Resp newResp = (SendAuth.Resp) baseResp;
                    String code = newResp.code;
                    start(code);
                    LogUtils.e("微信code: " + code);
                    break;
                }
                break;
        }
    }

    private void start(String code) {
        if (WeChat.getType() == WeChat.Type.LOGIN_MINE) {
            DataChangeManager.get().change(2, code);
        } else {
            DataChangeManager.get().change(2, code);
        }
        finish();
    }
}