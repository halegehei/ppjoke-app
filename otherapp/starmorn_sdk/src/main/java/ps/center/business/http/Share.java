package ps.center.business.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ps.center.business.bean.share.ShareBean;
import ps.center.business.http.base.BusinessBaseHttp;
import ps.center.business.http.base.BusinessRequest;
import ps.center.business.route.ConfigUrls;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

/* loaded from: classes.jar:ps/center/business/http/Share.class */
public class Share extends BusinessBaseHttp {
    public void getShareInfo(String type, String share_id, final Result<ShareBean> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("type", type);
        params.add("share_id", share_id);
        BusinessRequest baseRequest = new BusinessRequest(ConfigUrls.share, "get", params, 6);
        getConfigApis().share(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<ShareBean>() { // from class: ps.center.business.http.Share.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(ShareBean shareBean) {
                if (result != null) {
                    result.success(shareBean);
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