package ps.center.business.http.base;

import com.google.gson.Gson;
import ps.center.application.BuildConfig;
import ps.center.business.api.AiDrawApis;
import ps.center.business.api.CollectApis;
import ps.center.business.api.CommonApis;
import ps.center.business.api.ConfigApis;
import ps.center.business.api.FaceChangeApis;
import ps.center.business.api.OSSApis;
import ps.center.business.api.ServicesApis;
import ps.center.business.api.StreetApis;
import ps.center.library.http.LogUtils;
import ps.center.library.http.base.HttpManager;

/* loaded from: classes.jar:ps/center/business/http/base/BusinessBaseHttp.class */
public class BusinessBaseHttp {
    public String getRequestData(BusinessRequest request) {
        if (request != null) {
            String json = new Gson().toJson(request);
            String requestString =json;
            return requestString;
        }
        LogUtils.e("request = null");
        return BuildConfig.VERSION_NAME;
    }

    protected OSSApis getOSSApis() {
        return (OSSApis) HttpManager.getApi(OSSApis.class);
    }

    protected ConfigApis getConfigApis() {
        return (ConfigApis) HttpManager.getApi(ConfigApis.class);
    }

    protected StreetApis getStreetApis() {
        return (StreetApis) HttpManager.getApi(StreetApis.class);
    }

    protected AiDrawApis getAiDrawApis() {
        return (AiDrawApis) HttpManager.getApi(AiDrawApis.class);
    }

    protected CollectApis getCollectApis() {
        return (CollectApis) HttpManager.getApi(CollectApis.class);
    }

    protected CommonApis getCommonApis() {
        return (CommonApis) HttpManager.getApi(CommonApis.class);
    }

    protected ServicesApis getServicesApis() {
        return (ServicesApis) HttpManager.getApi(ServicesApis.class);
    }

    protected FaceChangeApis getFaceChangeApis() {
        return (FaceChangeApis) HttpManager.getApi(FaceChangeApis.class);
    }
}