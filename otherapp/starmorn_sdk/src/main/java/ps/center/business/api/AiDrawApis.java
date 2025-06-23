package ps.center.business.api;

import io.reactivex.Observable;
import java.util.List;
import ps.center.business.bean.aiDraw.ByAnimatedCartoonDetails;
import ps.center.business.bean.aiDraw.ByMagicAvatarTaskInfo;
import ps.center.business.bean.aiDraw.ByStyle;
import ps.center.business.bean.aiDraw.ByTemplateList;
import ps.center.business.bean.aiDraw.CreateAnimatedCartoonTask;
import ps.center.business.bean.aiDraw.CreateMagicAvatarTask;
import ps.center.business.bean.aiDraw.DabHandDraw;
import ps.center.business.bean.aiDraw.MaterialDetails;
import ps.center.business.bean.aiDraw.NewMaterial;
import ps.center.business.bean.aiDraw.SizeList;
import ps.center.business.bean.aiDraw.TaskInfo;
import ps.center.library.http.base.BaseBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/* loaded from: classes.jar:ps/center/business/api/AiDrawApis.class */
public interface AiDrawApis {
    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<NewMaterial>>> getNewMaterialList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<MaterialDetails>> getMaterialDetails(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<ByTemplateList>>> byTemplateList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<ByStyle>> byStyleList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<DabHandDraw>> dabHandDraw(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<TaskInfo>> byTaskInfo(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<CreateMagicAvatarTask>> createMagicAvatarTask(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<ByMagicAvatarTaskInfo>> byMagicAvatarTaskInfo(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<List<SizeList>>> getSizeList(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<CreateAnimatedCartoonTask>> createAnimatedCartoonTask(@Field("encode") String str);

    @FormUrlEncoded
    @POST("https://bucenter.proxy.sanwubeixin.cn/api/proxy/forward/base")
    Observable<BaseBean<ByAnimatedCartoonDetails>> byAnimatedCartoonDetails(@Field("encode") String str);
}