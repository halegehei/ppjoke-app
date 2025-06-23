package ps.center.business.api;

import io.reactivex.Observable;
import java.util.List;
import ps.center.business.bean.faceChange.ByImageFusionInfo;
import ps.center.business.bean.faceChange.ByMaterial;
import ps.center.business.bean.faceChange.CreateImageFusionInfo;
import ps.center.library.http.base.BaseBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/* loaded from: classes.jar:ps/center/business/api/FaceChangeApis.class */
public interface FaceChangeApis {
    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<ByMaterial>>> byMaterialList1(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<ByMaterial>>> byMaterialList2(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<CreateImageFusionInfo>> createImageFusionInfo(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<ByImageFusionInfo>> byImageFusionInfo(@Field("encode") String str);
}