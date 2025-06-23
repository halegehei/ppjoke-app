package ps.center.business.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import ps.center.business.bean.faceChange.ByImageFusionInfo;
import ps.center.business.bean.faceChange.ByMaterial;
import ps.center.business.bean.faceChange.CreateImageFusionInfo;
import ps.center.business.http.base.BusinessBaseHttp;
import ps.center.business.http.base.BusinessRequest;
import ps.center.business.route.FaceChangeUrls;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

/* loaded from: classes.jar:ps/center/business/http/FaceChange.class */
public class FaceChange extends BusinessBaseHttp {
    public void byMaterialList1(String type, String page, final Result<List<ByMaterial>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("type", type);
        params.add("page", page);
        BusinessRequest baseRequest = new BusinessRequest(FaceChangeUrls.byMaterialList1, "get", params, 26);
        getFaceChangeApis().byMaterialList1(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<ByMaterial>>() { // from class: ps.center.business.http.FaceChange.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<ByMaterial> byMaterials) {
                if (result != null) {
                    result.success(byMaterials);
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

    public void byMaterialList2(String classify_id, String page, final Result<List<ByMaterial>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("classify_id", classify_id);
        params.add("page", page);
        BusinessRequest baseRequest = new BusinessRequest(FaceChangeUrls.byMaterialList2, "post", params, 26);
        getFaceChangeApis().byMaterialList2(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<ByMaterial>>() { // from class: ps.center.business.http.FaceChange.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<ByMaterial> byMaterials) {
                if (result != null) {
                    result.success(byMaterials);
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

    public void createImageFusionInfo(String image, String id, final Result<CreateImageFusionInfo> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("image", image);
        params.add("id", id);
        BusinessRequest baseRequest = new BusinessRequest(FaceChangeUrls.createImageFusionInfo, "post", params, 26);
        getFaceChangeApis().createImageFusionInfo(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CreateImageFusionInfo>() { // from class: ps.center.business.http.FaceChange.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CreateImageFusionInfo createImageFusionInfo) {
                if (result != null) {
                    result.success(createImageFusionInfo);
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

    public void byImageFusionInfo(String task_id, final Result<ByImageFusionInfo> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("task_id", task_id);
        BusinessRequest baseRequest = new BusinessRequest(FaceChangeUrls.byImageFusionInfo, "get", params, 26);
        getFaceChangeApis().byImageFusionInfo(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<ByImageFusionInfo>() { // from class: ps.center.business.http.FaceChange.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(ByImageFusionInfo byImageFusionInfo) {
                if (result != null) {
                    result.success(byImageFusionInfo);
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