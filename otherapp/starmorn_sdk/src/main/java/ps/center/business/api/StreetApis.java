package ps.center.business.api;

import io.reactivex.Observable;
import java.util.List;
import ps.center.business.bean.street.AbroadScenic;
import ps.center.business.bean.street.City;
import ps.center.business.bean.street.Country;
import ps.center.business.bean.street.GlobalLiveVideo;
import ps.center.business.bean.street.HomeClassify;
import ps.center.business.bean.street.HomeScenic;
import ps.center.business.bean.street.HomeStreet;
import ps.center.business.bean.street.HomeStreetScape;
import ps.center.business.bean.street.HotScenic;
import ps.center.business.bean.street.Province;
import ps.center.business.bean.street.Scenic;
import ps.center.library.http.base.BaseBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/* loaded from: classes.jar:ps/center/business/api/StreetApis.class */
public interface StreetApis {
    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<HomeClassify>>> byHomeClassifyList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<HomeScenic>>> byHomeScenicList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<GlobalLiveVideo>>> byGlobalLiveVideoList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<Province>>> byProvinceList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<City>>> byCityList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<Scenic>>> byScenicList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<Country>>> byCountryList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<AbroadScenic>>> getAbroadScenicList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<HomeStreetScape>>> byHomeStreetScape(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<HotScenic>>> byHotScenicList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<HomeStreet>>> getHomeStreetList(@Field("encode") String str);
}