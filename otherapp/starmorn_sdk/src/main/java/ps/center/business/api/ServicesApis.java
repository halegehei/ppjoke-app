package ps.center.business.api;

import io.reactivex.Observable;
import ps.center.business.bean.service.MoneyInfo;
import ps.center.business.bean.service.MoneyStatus;
import ps.center.business.bean.service.SignInfo;
import ps.center.library.http.base.BaseBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/* loaded from: classes.jar:ps/center/business/api/ServicesApis.class */
public interface ServicesApis {
    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<SignInfo>> getSignInfo(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<MoneyStatus>> getMoneyStatus(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<MoneyInfo>> addMoneyInfo(@Field("encode") String str);
}