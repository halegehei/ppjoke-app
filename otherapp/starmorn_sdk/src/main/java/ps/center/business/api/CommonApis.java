package ps.center.business.api;

import io.reactivex.Observable;
import ps.center.business.bean.common.IdentityAuthentication;
import ps.center.business.bean.common.ImageModeration;
import ps.center.business.bean.common.TextModeration;
import ps.center.centerinterface.bean.OrderInfoBean;
import ps.center.library.http.base.BaseBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* loaded from: classes.jar:ps/center/business/api/CommonApis.class */
public interface CommonApis {
    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<TextModeration>> textModeration(@Field("encode") String str);

    @FormUrlEncoded
    @POST
    Observable<BaseBean<ImageModeration>> uploadImageCheck(@Url String str, @Field("type") String str2, @Field("base64") String str3);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<IdentityAuthentication>> identityAuthentication(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<OrderInfoBean>> downOrder(@Field("encode") String str);
}