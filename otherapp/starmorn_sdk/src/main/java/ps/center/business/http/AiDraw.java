package ps.center.business.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
import ps.center.business.http.base.BusinessBaseHttp;
import ps.center.business.http.base.BusinessRequest;
import ps.center.business.route.AiDrawUrls;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

/* loaded from: classes.jar:ps/center/business/http/AiDraw.class */
public class AiDraw extends BusinessBaseHttp {
    public void getNewMaterialList(String page, final Result<List<NewMaterial>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("page", page);
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.getNewMaterialList, "get", params, 18);
        getAiDrawApis().getNewMaterialList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<NewMaterial>>() { // from class: ps.center.business.http.AiDraw.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<NewMaterial> newMaterialList) {
                if (result != null) {
                    result.success(newMaterialList);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void getMaterialDetails(String id, final Result<MaterialDetails> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("id", id);
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.getMaterialDetails, "get", params, 18);
        getAiDrawApis().getMaterialDetails(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<MaterialDetails>() { // from class: ps.center.business.http.AiDraw.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(MaterialDetails materialDetails) {
                if (result != null) {
                    result.success(materialDetails);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byTemplateList(String page, final Result<List<ByTemplateList>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("page", page);
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.byTemplateList, "get", params, 18);
        getAiDrawApis().byTemplateList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<ByTemplateList>>() { // from class: ps.center.business.http.AiDraw.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<ByTemplateList> byTemplateList) {
                if (result != null) {
                    result.success(byTemplateList);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byStyleList(final Result<ByStyle> result) {
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.byStyleList, "get", 18);
        getAiDrawApis().byStyleList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<ByStyle>() { // from class: ps.center.business.http.AiDraw.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(ByStyle materialDetails) {
                if (result != null) {
                    result.success(materialDetails);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void dabHandDraw(String describe, String style_id, String size_id, String image, String is_new_people, String is_fortify, String guidance, String sign, final Result<DabHandDraw> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("describe", describe);
        params.add("style_id", style_id);
        params.add("size_id", size_id);
        params.add("image", image);
        params.add("is_new_people", is_new_people);
        params.add("is_fortify", is_fortify);
        params.add("guidance", guidance);
        params.add("sign", sign);
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.dabHandDraw, "post", params, 18);
        getAiDrawApis().dabHandDraw(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<DabHandDraw>() { // from class: ps.center.business.http.AiDraw.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(DabHandDraw dabHandDraw) {
                if (result != null) {
                    result.success(dabHandDraw);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byTaskInfo(String taskId, final Result<TaskInfo> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("task_id", taskId);
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.byTaskInfo, "get", params, 18);
        getAiDrawApis().byTaskInfo(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<TaskInfo>() { // from class: ps.center.business.http.AiDraw.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(TaskInfo taskInfo) {
                if (result != null) {
                    result.success(taskInfo);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void createMagicAvatarTask(String image, String type, String sign, final Result<CreateMagicAvatarTask> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("image", image);
        params.add("type", type);
        params.add("sign", sign);
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.createMagicAvatarTask, "post", params, 18);
        getAiDrawApis().createMagicAvatarTask(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CreateMagicAvatarTask>() { // from class: ps.center.business.http.AiDraw.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CreateMagicAvatarTask taskInfo) {
                if (result != null) {
                    result.success(taskInfo);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byMagicAvatarTaskInfo(String task_id, final Result<ByMagicAvatarTaskInfo> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("task_id", task_id);
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.byMagicAvatarTaskInfo, "get", params, 18);
        getAiDrawApis().byMagicAvatarTaskInfo(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<ByMagicAvatarTaskInfo>() { // from class: ps.center.business.http.AiDraw.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(ByMagicAvatarTaskInfo taskInfo) {
                if (result != null) {
                    result.success(taskInfo);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void getSizeList(final Result<List<SizeList>> result) {
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.getSizeList, "get", 18);
        getAiDrawApis().getSizeList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<SizeList>>() { // from class: ps.center.business.http.AiDraw.9
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<SizeList> taskInfo) {
                if (result != null) {
                    result.success(taskInfo);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void createAnimatedCartoonTask(String image, String size_id, String type, String sign, final Result<CreateAnimatedCartoonTask> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("image", image);
        params.add("size_id", size_id);
        params.add("type", type);
        params.add("sign", sign);
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.createAnimatedCartoonTask, "post", params, 18);
        getAiDrawApis().createAnimatedCartoonTask(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CreateAnimatedCartoonTask>() { // from class: ps.center.business.http.AiDraw.10
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CreateAnimatedCartoonTask taskInfo) {
                if (result != null) {
                    result.success(taskInfo);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byAnimatedCartoonDetails(String task_id, final Result<ByAnimatedCartoonDetails> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("task_id", task_id);
        BusinessRequest baseRequest = new BusinessRequest(AiDrawUrls.byAnimatedCartoonDetails, "get", params, 18);
        getAiDrawApis().byAnimatedCartoonDetails(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<ByAnimatedCartoonDetails>() { // from class: ps.center.business.http.AiDraw.11
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(ByAnimatedCartoonDetails taskInfo) {
                if (result != null) {
                    result.success(taskInfo);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }
}