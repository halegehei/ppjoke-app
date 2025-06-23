package com.litemob.screentogetherpink.ui.activity;


import android.util.Log;

import com.litemob.screentogetherpink.BaseConstant;
import com.litemob.screentogetherpink.ui.bean.Ability;
import com.tencent.live2.V2TXLivePremier;

import androidx.viewbinding.ViewBinding;
import ps.center.application.welcome.BaseWelcomeActivity;
import ps.center.application.welcome.NextAction;
import ps.center.business.CheckParameter;
import ps.center.business.http.base.BusHttp;
import ps.center.library.http.base.Result;
import ps.center.utils.ToastUtils;
import ps.center.views.activity.BaseActivityVB;

public class WelcomActivity extends BaseWelcomeActivity {

    @Override
    protected Class<? extends BaseActivityVB<? extends ViewBinding>> setMainActivityClass() {
        return MainActivity.class;
    }

    @Override
    protected void preInitAndGoNextPager(NextAction nextAction) {
        BusHttp.config().getAbilityConfig(Ability.class, new Result<Ability>() {
            @Override
            public void success(Ability obj) {
                Log.e("config",obj.toString());
                String checkResult = CheckParameter.getInstance().fullReException(obj);
                if (checkResult != null) {
                    BusHttp.bot().dingBotErr(String.format("配置检测异常：\n%s", checkResult), (Result) null);
                }
                BaseConstant.licence_url = obj.ability.used.func.licence_url;
                BaseConstant.licence_key = obj.ability.used.func.licence_key;
                Log.e("",BaseConstant.licence_url);
                Log.e("",BaseConstant.licence_key);

                V2TXLivePremier.setLicence(WelcomActivity.this, BaseConstant.licence_url, BaseConstant.licence_key);
                V2TXLivePremier.setObserver(new V2TXLivePremier.V2TXLivePremierObserver() {
                    @Override
                    public void onLicenceLoaded(int result, String reason) {
                        Log.e("deletelog", "onLicenceLoaded: result:" + result + ", reason:" + reason);
                    }
                });
                nextAction.go();
            }

            @Override
            public void err(int code, String message) {
                if (code == -1) {
                  //  BusHttp.bot().dingBotErr(String.format("配置检测异常：\n%s", "ability参数" + message), (Result) null);
                }
                ToastUtils.show(WelcomActivity.this, "网络状态不佳，请重试！");
            }
        });


    }


}
