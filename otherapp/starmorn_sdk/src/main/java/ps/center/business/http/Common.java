package ps.center.business.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ps.center.business.bean.common.IdentityAuthentication;
import ps.center.business.bean.common.ImageModeration;
import ps.center.business.bean.common.TextModeration;
import ps.center.business.http.base.BusinessBaseHttp;
import ps.center.business.http.base.BusinessRequest;
import ps.center.business.route.CommonUrls;
import ps.center.centerinterface.bean.OrderInfoBean;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

/* loaded from: classes.jar:ps/center/business/http/Common.class */
public class Common extends BusinessBaseHttp {
    public void textModeration(String text, final Result<TextModeration> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("text", text);
        BusinessRequest baseRequest = new BusinessRequest(CommonUrls.textModeration, "post", params, 6);
        getCommonApis().textModeration(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<TextModeration>() { // from class: ps.center.business.http.Common.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(TextModeration textModeration) {
                if (result != null) {
                    result.success(textModeration);
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

    public void uploadImageCheck(String type, String base64, final Result<ImageModeration> result) {
        getCommonApis().uploadImageCheck(CommonUrls.uploadImageCheck, type, base64).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<ImageModeration>() { // from class: ps.center.business.http.Common.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(ImageModeration imageModeration) {
                if (result != null) {
                    result.success(imageModeration);
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

    public void identityAuthentication(String name, String idcard_number, final Result<IdentityAuthentication> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("name", name);
        params.add("idcard_number", idcard_number);
        BusinessRequest baseRequest = new BusinessRequest(CommonUrls.identityAuthentication, "get", params, 6);
        getCommonApis().identityAuthentication(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<IdentityAuthentication>() { // from class: ps.center.business.http.Common.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(IdentityAuthentication imageModeration) {
                if (result != null) {
                    result.success(imageModeration);
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

    public void downOrder(String shop_id, String group_id, String custom_pay, final Result<OrderInfoBean> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("shop_id", shop_id);
        params.add("group_id", group_id);
        params.add("custom_pay", custom_pay);
        BusinessRequest baseRequest = new BusinessRequest(CommonUrls.downOrder, "post", params, 10);
        getCommonApis().downOrder(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<OrderInfoBean>() { // from class: ps.center.business.http.Common.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(OrderInfoBean imageModeration) {
                if (result != null) {
                    result.success(imageModeration);
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