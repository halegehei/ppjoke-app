package com.litemob.screentogetherpink.okhttp;


import com.litemob.screentogetherpink.ui.bean.CommonBean;
import com.litemob.screentogetherpink.ui.bean.DeviceBean;
import com.litemob.screentogetherpink.ui.bean.DeviceInfoBean;

import java.util.List;

import io.reactivex.Observable;
import ps.center.library.http.base.BaseBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface   Apis {

    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    @FormUrlEncoded
    Observable<BaseBean<List<DeviceBean>>> byDeviceList(@Field("encode") String encode);

    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    @FormUrlEncoded
    Observable<BaseBean<CommonBean>> pushStreamTask(@Field("encode") String encode);


    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    @FormUrlEncoded
    Observable<BaseBean<CommonBean>> createScreenShareInfo(@Field("encode") String encode);


    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    @FormUrlEncoded
    Observable<BaseBean<CommonBean>> delDeviceListInfo(@Field("encode") String encode);



    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    @FormUrlEncoded
    Observable<BaseBean<CommonBean>> updateNickName(@Field("encode") String encode);

    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    @FormUrlEncoded
    Observable<BaseBean<DeviceInfoBean>> byDeviceInfo(@Field("encode") String encode);

    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    @FormUrlEncoded
    Observable<BaseBean<CommonBean>> updateDeviceName(@Field("encode") String encode);


    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    @FormUrlEncoded
    Observable<BaseBean<CommonBean>> updateRefreshStatus(@Field("encode") String encode);




}
