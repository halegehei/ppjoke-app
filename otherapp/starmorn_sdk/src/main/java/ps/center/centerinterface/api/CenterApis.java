package ps.center.centerinterface.api;

import io.reactivex.Observable;
import java.util.List;
import ps.center.centerinterface.bean.CreateApp;
import ps.center.centerinterface.bean.OrderInfoBean;
import ps.center.centerinterface.bean.Orders;
import ps.center.centerinterface.bean.PayPage;
import ps.center.centerinterface.bean.TestPlay;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.bean.UserCheck;
import ps.center.library.http.base.BaseBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/* loaded from: classes.jar:ps/center/centerinterface/api/CenterApis.class */
public interface CenterApis {
    @GET
    Observable<BaseBean<CreateApp>> create(@Url String str, @Query("mobile_brand") String str2, @Query("mobile_os_version") String str3);

    @GET
    Observable<BaseBean<User>> getUserInfo(@Url String str);

    @GET
    Observable<BaseBean<CreateApp>> logout(@Url String str);

    @GET
    Observable<BaseBean<CreateApp>> delete(@Url String str);

    @GET
    Observable<BaseBean<PayPage>> getPayPage(@Url String str);

    @GET
    Observable<BaseBean<List<Orders>>> orders(@Url String str);

    @GET
    Observable<BaseBean<OrderInfoBean>> getOrder(@Url String str, @Query("shop_id") String str2, @Query("group_id") String str3);

    @GET
    Observable<BaseBean<UserCheck>> checkUserAsPhone(@Url String str, @Query("tel") String str2);

    @GET
    Observable<BaseBean<TestPlay>> testPlay(@Url String str);

    @GET
    Observable<BaseBean<OrderInfoBean>> genCreate(@Url String str, @Query("shop_id") String str2, @Query("group_id") String str3, @Query("custom_pay") String str4);

    @GET
    Observable<BaseBean<CreateApp>> weChatLogin(@Url String str, @Query("code") String str2);

    @GET
    Observable<BaseBean<Object>> getPhoneCode(@Url String str, @Query("tel") String str2);

    @GET
    Observable<BaseBean<CreateApp>> oneKeyLogin(@Url String str, @Query("token") String str2);

    @GET
    Observable<BaseBean<CreateApp>> codeLogin(@Url String str, @Query("tel") String str2, @Query("code") String str3);
}