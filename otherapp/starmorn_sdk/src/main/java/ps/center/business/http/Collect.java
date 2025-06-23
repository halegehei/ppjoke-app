package ps.center.business.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import ps.center.business.bean.NullDataBean;
import ps.center.business.bean.collect.CollectRecord;
import ps.center.business.bean.collect.CollectStatus;
import ps.center.business.http.base.BusinessBaseHttp;
import ps.center.business.http.base.BusinessRequest;
import ps.center.business.route.CollectUrls;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

/* loaded from: classes.jar:ps/center/business/http/Collect.class */
public class Collect extends BusinessBaseHttp {
    public void addCollectInfo(String material_serial, String module_id, String info, final Result<NullDataBean> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("material_serial", material_serial);
        params.add("module_id", module_id);
        params.add("info", info);
        BusinessRequest baseRequest = new BusinessRequest(CollectUrls.addCollectInfo, "post", params, 15);
        getCollectApis().addCollectInfo(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<NullDataBean>() { // from class: ps.center.business.http.Collect.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(NullDataBean nullDataBean) {
                if (result != null) {
                    result.success(nullDataBean);
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

    public void createCollectInfo(String material_serial, String module_id, String info, final Result<CollectStatus> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("material_serial", material_serial);
        params.add("module_id", module_id);
        params.add("info", info);
        BusinessRequest baseRequest = new BusinessRequest(CollectUrls.createCollectInfo, "post", params, 15);
        getCollectApis().createCollectInfo(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CollectStatus>() { // from class: ps.center.business.http.Collect.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CollectStatus collectStatus) {
                if (result != null) {
                    result.success(collectStatus);
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

    public void getCollectRecordList(String page, String module_id, final Result<List<CollectRecord>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("page", page);
        params.add("module_id", module_id);
        BusinessRequest baseRequest = new BusinessRequest(CollectUrls.getCollectRecordList, "get", params, 15);
        getCollectApis().getCollectRecordList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<CollectRecord>>() { // from class: ps.center.business.http.Collect.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<CollectRecord> collectRecords) {
                if (result != null) {
                    result.success(collectRecords);
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

    public void byCollectStatus(String module_id, String material_serial, final Result<CollectStatus> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("module_id", module_id);
        params.add("material_serial", material_serial);
        BusinessRequest baseRequest = new BusinessRequest(CollectUrls.byCollectStatus, "get", params, 15);
        getCollectApis().byCollectStatus(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CollectStatus>() { // from class: ps.center.business.http.Collect.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CollectStatus collectStatus) {
                if (result != null) {
                    result.success(collectStatus);
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

    public void cancelCollectStatus(String material_serial, final Result<CollectStatus> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("material_serial", material_serial);
        BusinessRequest baseRequest = new BusinessRequest(CollectUrls.cancelCollectStatus, "post", params, 15);
        getCollectApis().cancelCollectStatus(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CollectStatus>() { // from class: ps.center.business.http.Collect.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CollectStatus collectStatus) {
                if (result != null) {
                    result.success(collectStatus);
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