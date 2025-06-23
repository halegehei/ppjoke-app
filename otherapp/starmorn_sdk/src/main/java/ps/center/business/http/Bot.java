package ps.center.business.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import ps.center.business.bean.config.AppConfig;
import ps.center.business.bean.config.Bots;
import ps.center.business.http.base.BusinessBaseHttp;
import ps.center.business.http.base.BusinessRequest;
import ps.center.business.route.ConfigUrls;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

/* loaded from: classes.jar:ps/center/business/http/Bot.class */
public class Bot extends BusinessBaseHttp {
    public void dingBot(String text, final Result<AppConfig> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("text", text);
        BusinessRequest baseRequest = new BusinessRequest(ConfigUrls.botSend, "get", params, 35);
        getConfigApis().botReport(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<AppConfig>() { // from class: ps.center.business.http.Bot.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(AppConfig config) {
                if (result != null) {
                    result.success(config);
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

    public void dingBotErr(String text, final Result<AppConfig> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("text", text);
        params.add("errno", 1);
        BusinessRequest baseRequest = new BusinessRequest(ConfigUrls.botSend, "get", params, 35);
        getConfigApis().botReport(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<AppConfig>() { // from class: ps.center.business.http.Bot.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(AppConfig config) {
                if (result != null) {
                    result.success(config);
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

    public void getUserMachineList(final Result<List<Bots>> result) {
        BusinessRequest baseRequest = new BusinessRequest(ConfigUrls.getUserMachineList, "get", 6);
        getConfigApis().getUserMachineList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<Bots>>() { // from class: ps.center.business.http.Bot.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<Bots> bots) {
                if (result != null) {
                    result.success(bots);
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