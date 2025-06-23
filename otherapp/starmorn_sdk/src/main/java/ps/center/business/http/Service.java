package ps.center.business.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ps.center.business.bean.service.MoneyInfo;
import ps.center.business.bean.service.MoneyStatus;
import ps.center.business.bean.service.SignInfo;
import ps.center.business.http.base.BusinessBaseHttp;
import ps.center.business.http.base.BusinessRequest;
import ps.center.business.route.ServiceUrls;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

/* loaded from: classes.jar:ps/center/business/http/Service.class */
public class Service extends BusinessBaseHttp {
    public void getSignInfo(final Result<SignInfo> result) {
        BusinessRequest baseRequest = new BusinessRequest(ServiceUrls.getSignInfo, "get", 17);
        getServicesApis().getSignInfo(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<SignInfo>() { // from class: ps.center.business.http.Service.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(SignInfo signInfo) {
                if (result != null) {
                    result.success(signInfo);
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

    public void getMoneyStatus(final Result<MoneyStatus> result) {
        BusinessRequest baseRequest = new BusinessRequest(ServiceUrls.getMoneyStatus, "get", 17);
        getServicesApis().getMoneyStatus(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<MoneyStatus>() { // from class: ps.center.business.http.Service.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(MoneyStatus moneyStatus) {
                if (result != null) {
                    result.success(moneyStatus);
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

    public void addMoneyInfo(String cost, final Result<MoneyInfo> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("cost", cost);
        BusinessRequest baseRequest = new BusinessRequest(ServiceUrls.addMoneyInfo, "get", params, 17);
        getServicesApis().addMoneyInfo(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<MoneyInfo>() { // from class: ps.center.business.http.Service.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(MoneyInfo moneyInfo) {
                if (result != null) {
                    result.success(moneyInfo);
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