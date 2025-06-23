package ps.center.business.api;

import io.reactivex.Observable;
import java.util.List;
import ps.center.business.bean.NullDataBean;
import ps.center.business.bean.collect.CollectRecord;
import ps.center.business.bean.collect.CollectStatus;
import ps.center.library.http.base.BaseBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/* loaded from: classes.jar:ps/center/business/api/CollectApis.class */
public interface CollectApis {
    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<NullDataBean>> addCollectInfo(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<CollectStatus>> createCollectInfo(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<CollectRecord>>> getCollectRecordList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<NullDataBean>> delCollectRecordInfo(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<CollectStatus>> byCollectStatus(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<CollectStatus>> cancelCollectStatus(@Field("encode") String str);
}