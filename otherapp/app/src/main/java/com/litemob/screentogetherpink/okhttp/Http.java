package com.litemob.screentogetherpink.okhttp;

import android.app.Activity;

import com.google.gson.Gson;
import com.litemob.screentogetherpink.BaseConstant;
import com.litemob.screentogetherpink.ui.bean.CommonBean;
import com.litemob.screentogetherpink.ui.bean.DeviceBean;
import com.litemob.screentogetherpink.ui.bean.DeviceInfoBean;
//import com.umeng.common.OO3;

import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ps.center.library.http.LogUtils;
import ps.center.library.http.base.HttpManager;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

public final class Http extends HttpManager {

    private volatile static Http http;

    private static final boolean IS_SCANNER_REQUEST_INFO = BaseConstant.DEBUG;

    private Http() {
    }

    public static Http get() {
        if (http == null) {
            synchronized (Http.class) {
                if (http == null) {
                    http = new Http();
                }
            }
        }
        return http;
    }


    public String getRequestData(BaseGetRequest request) {
        String requestString;
        if (request != null) {

            String json = new Gson().toJson(request);

            if (IS_SCANNER_REQUEST_INFO) {
                LogUtils.e(json);
            }

//            requestString = OO3.enCode(json);
            requestString=json;
            return requestString;
        } else {
            LogUtils.e("request = null");
            return "";
        }
    }

    public String getRequestData(BasePostRequest request) {
        String requestString;
        if (request != null) {

            String json = new Gson().toJson(request);

            if (IS_SCANNER_REQUEST_INFO) {
                LogUtils.e(json);
            }

//            requestString = OO3.enCode(json);
            requestString=json;
            return requestString;
        } else {
            LogUtils.e("request = null");
            return "";
        }
    }
    public void byDeviceList(Activity activity, BaseGetRequest request, Result<List<DeviceBean>> result) {
        getApi(Apis.class).byDeviceList(getRequestData(request))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new HttpObserver<List<DeviceBean>>(activity) {
                    @Override
                    protected void success(@Nullable List<DeviceBean> data) {
                        if (result == null)
                            return;

                        result.success(data);
                    }

                    @Override
                    protected void err(int code, String message) {
                        if (result == null)
                            return;
                        result.err(code, message);
                    }

                    @Override
                    protected void end() {
                        if (result == null)
                            return;
                        result.end();
                    }
                });
    }


    public void byDeviceInfo(Activity activity, BaseGetRequest request, Result<DeviceInfoBean> result) {
        getApi(Apis.class).byDeviceInfo(getRequestData(request))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<DeviceInfoBean>(activity) {
                    @Override
                    protected void success(@Nullable DeviceInfoBean data) {
                        if (result == null)
                            return;

                        result.success(data);
                    }

                    @Override
                    protected void err(int code, String message) {
                        if (result == null)
                            return;
                        result.err(code, message);
                    }

                    @Override
                    protected void end() {
                        if (result == null)
                            return;
                        result.end();
                    }
                });
    }


    public void updateRefreshStatus(Activity activity, BasePostRequest request, Result<CommonBean> result) {
        getApi(Apis.class).updateRefreshStatus(getRequestData(request))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<CommonBean>(activity){

                    @Override
                    protected void success(@Nullable CommonBean data) {
                        if (result == null)
                            return;

                        result.success(data);
                    }

                    @Override
                    protected void err(int code, String message) {
                        if (result == null)
                            return;
                        result.err(code, message);
                    }

                    @Override
                    protected void end() {
                        if (result == null)
                            return;
                        result.end();
                    }
                });
    }



    public void pushStreamTask(Activity activity, BasePostRequest request, Result<CommonBean> result) {
        getApi(Apis.class).updateRefreshStatus(getRequestData(request))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<CommonBean>(activity){

                    @Override
                    protected void success(@Nullable CommonBean data) {
                        if (result == null)
                            return;

                        result.success(data);
                    }

                    @Override
                    protected void err(int code, String message) {
                        if (result == null)
                            return;
                        result.err(code, message);
                    }

                    @Override
                    protected void end() {
                        if (result == null)
                            return;
                        result.end();
                    }
                });
    }




    public void createScreenShareInfo(Activity activity, BasePostRequest request, Result<CommonBean> result) {
        getApi(Apis.class).updateRefreshStatus(getRequestData(request))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<CommonBean>(activity){

                    @Override
                    protected void success(@Nullable CommonBean data) {
                        if (result == null)
                            return;

                        result.success(data);
                    }

                    @Override
                    protected void err(int code, String message) {
                        if (result == null)
                            return;
                        result.err(code, message);
                    }

                    @Override
                    protected void end() {
                        if (result == null)
                            return;
                        result.end();
                    }
                });
    }


    public void delDeviceListInfo(Activity activity, BasePostRequest request, Result<CommonBean> result) {
        getApi(Apis.class).updateRefreshStatus(getRequestData(request))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<CommonBean>(activity){

                    @Override
                    protected void success(@Nullable CommonBean data) {
                        if (result == null)
                            return;

                        result.success(data);
                    }

                    @Override
                    protected void err(int code, String message) {
                        if (result == null)
                            return;
                        result.err(code, message);
                    }

                    @Override
                    protected void end() {
                        if (result == null)
                            return;
                        result.end();
                    }
                });
    }


    public void updateNickName(Activity activity, BasePostRequest request, Result<CommonBean> result) {
        getApi(Apis.class).updateRefreshStatus(getRequestData(request))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<CommonBean>(activity){

                    @Override
                    protected void success(@Nullable CommonBean data) {
                        if (result == null)
                            return;

                        result.success(data);
                    }

                    @Override
                    protected void err(int code, String message) {
                        if (result == null)
                            return;
                        result.err(code, message);
                    }

                    @Override
                    protected void end() {
                        if (result == null)
                            return;
                        result.end();
                    }
                });
    }



    public void updateDeviceName(Activity activity, BasePostRequest request, Result<CommonBean> result) {
        getApi(Apis.class).updateRefreshStatus(getRequestData(request))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<CommonBean>(activity){

                    @Override
                    protected void success(@Nullable CommonBean data) {
                        if (result == null)
                            return;

                        result.success(data);
                    }

                    @Override
                    protected void err(int code, String message) {
                        if (result == null)
                            return;
                        result.err(code, message);
                    }

                    @Override
                    protected void end() {
                        if (result == null)
                            return;
                        result.end();
                    }
                });
    }












}