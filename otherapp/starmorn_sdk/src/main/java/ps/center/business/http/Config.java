package ps.center.business.http;

import android.util.Log;

import com.google.gson.Gson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ps.center.business.BusinessConstant;
import ps.center.business.CheckParameter;
import ps.center.business.bean.config.AppConfig;
import ps.center.business.http.base.BusHttp;
import ps.center.business.http.base.BusinessBaseHttp;
import ps.center.business.http.base.BusinessRequest;
import ps.center.business.route.ConfigUrls;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

/* loaded from: classes.jar:ps/center/business/http/Config.class */
public class Config extends BusinessBaseHttp {
    public void getConfig(final Result<AppConfig> result) {
        BusinessRequest baseRequest = new BusinessRequest(ConfigUrls.getConfig, "get", 1);
        getConfigApis().getConfig(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<AppConfig>() { // from class: ps.center.business.http.Config.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(AppConfig appConfig) {
                String checkResult = CheckParameter.getInstance().fullReException(appConfig);
                if (checkResult != null) {
                    BusHttp.bot().dingBotErr(String.format("配置检测异常：\n%s", checkResult), null);
                }
                BusinessConstant.setConfig(appConfig);
                if (result != null) {
                    result.success(appConfig);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public <T> void getAbilityConfig(final Class<T> clazz, final Result<T> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("key", "ability");
        BusinessRequest baseRequest = new BusinessRequest(ConfigUrls.getConfig, "get", params, 1);
        getConfigApis().getAbilityConfig(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<Object>() { // from class: ps.center.business.http.Config.2
            @Override // ps.center.library.http.base.HttpObserver
            protected void success(Object appConfig) {
                try {
                    Log.e("111",appConfig.toString());
                    Gson gson = new Gson();
                    String json = gson.toJson(appConfig);
                    Object fromJson = gson.fromJson(json, clazz);
                    if (result != null) {
                        result.success((T) fromJson);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.err(-1, "解析失败");
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }
}