package ps.center.business.api;

import io.reactivex.Observable;
import ps.center.business.bean.oss.OSSSigned;
import ps.center.business.bean.oss.OSSUploadParams;
import ps.center.library.http.base.BaseBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/* loaded from: classes.jar:ps/center/business/api/OSSApis.class */
public interface OSSApis {
    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<OSSSigned>> getOSSSigned(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<OSSUploadParams>> getOSSUploadParams(@Field("encode") String str);
}