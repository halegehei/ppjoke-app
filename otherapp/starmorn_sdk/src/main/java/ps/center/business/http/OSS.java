package ps.center.business.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ps.center.business.bean.oss.OSSSigned;
import ps.center.business.bean.oss.OSSUploadParams;
import ps.center.business.http.base.BusinessBaseHttp;
import ps.center.business.http.base.BusinessRequest;
import ps.center.business.route.OSSUrls;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

/* loaded from: classes.jar:ps/center/business/http/OSS.class */
public class OSS extends BusinessBaseHttp {
    public void getOSSSigned(final Result<OSSSigned> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        BusinessRequest baseRequest = new BusinessRequest(OSSUrls.getOSSSigned, "get", params, 6);
        getOSSApis().getOSSSigned(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<OSSSigned>() { // from class: ps.center.business.http.OSS.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(OSSSigned ossSigned) {
                if (result != null) {
                    result.success(ossSigned);
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

    public void getOSSUploadParams(String city, String time, String projectName, final Result<OSSUploadParams> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("type", city);
        params.add("kind", time);
        params.add("name", projectName);
        BusinessRequest baseRequest = new BusinessRequest(OSSUrls.getOSSUploadParams, "get", params, 6);
        getOSSApis().getOSSUploadParams(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<OSSUploadParams>() { // from class: ps.center.business.http.OSS.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(OSSUploadParams ossUploadParams) {
                if (result != null) {
                    result.success(ossUploadParams);
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