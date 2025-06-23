package ps.center.ossutils;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ps.center.application.BuildConfig;
import ps.center.business.bean.oss.OSSUploadParams;
import ps.center.business.http.base.BusHttp;
import ps.center.library.http.base.Result;
import ps.center.utils.Uri2Path;

/* loaded from: classes.jar:ps/center/ossutils/OSSUploadTask.class */
public class OSSUploadTask {
    public static final String TAG = "OSSUploadTask";
    private final OSSBuilder builder;
    private OSSUploadParams ossUploadParams;
    private OSSUploadResult result;
    private OSSAsyncTask<PutObjectResult> task;
    private boolean isCancelTask = false;
    private final List<Object> uploadObj = new ArrayList();
    private int uploadIndex = 0;
    private final Result<OSSUploadParams> getOSSUploadParams = new Result<OSSUploadParams>() { // from class: ps.center.ossutils.OSSUploadTask.2
        @Override // ps.center.library.http.base.Result
        public void success(OSSUploadParams ossUploadParams) {
            if (ossUploadParams == null) {
                if (OSSUploadTask.this.builder.getOssUploadListener() != null) {
                    OSSUploadTask.this.builder.getOssUploadListener().error("-1", "上传参数接口返回了空");
                }
            } else {
                OSSUploadTask.this.ossUploadParams = ossUploadParams;
                OSSUploadTask.this.preUpload();
            }
        }

        @Override // ps.center.library.http.base.Result
        public void err(int code, String message) {
            if (OSSUploadTask.this.builder.getOssUploadListener() != null) {
                OSSUploadTask.this.builder.getOssUploadListener().error(String.valueOf(code), message);
            }
        }
    };

    static /* synthetic */ int access$012(OSSUploadTask x0, int x1) {
        int i = x0.uploadIndex + x1;
        x0.uploadIndex = i;
        return i;
    }

    OSSUploadTask(OSSBuilder ossBuilder) {
        this.builder = ossBuilder;
        getUploadParams();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preUpload() {
        if (this.builder == null) {
            throw new NullPointerException("Builder == null");
        }
        if (this.builder.getUris() == null && this.builder.getFilePath() == null) {
            throw new NullPointerException("未设置要上传的文件  uri = null, filePath = null");
        }
        if (TextUtils.isEmpty(this.builder.getProjectName())) {
            throw new NullPointerException("本SDK必须设置项目名称，请使用英文项目名称: projectName(name) ");
        }
        if (this.builder.getFilePath() != null) {
            this.uploadObj.addAll(this.builder.getFilePath());
        }
        if (this.builder.getUris() != null) {
            this.uploadObj.addAll(this.builder.getUris());
        }
        upload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upload() {
        PutObjectRequest putObjectRequest;
        try {
            if (this.isCancelTask) {
                return;
            }
            if (this.uploadIndex >= this.uploadObj.size()) {
                if (this.builder.getOssUploadListener() != null) {
                    this.builder.getOssUploadListener().success(this.result);
                    return;
                }
                return;
            }
            OSSClient ossClient = new OSSClient(this.builder.getContext(), this.ossUploadParams.endpoint, new OSSAuthCredentialsProvider(this.ossUploadParams.callback_url));
            Object obj = this.uploadObj.get(this.uploadIndex);
            String fileName = String.valueOf(System.currentTimeMillis()) + new Random().nextInt(99999);
            if (obj instanceof Uri) {
                Uri fileUri = (Uri) obj;
                putObjectRequest = new PutObjectRequest(this.ossUploadParams.bucket_name, this.ossUploadParams.path + fileName + getLastName(fileUri), fileUri);
            } else {
                String filePath = (String) obj;
                putObjectRequest = new PutObjectRequest(this.ossUploadParams.bucket_name, this.ossUploadParams.path + fileName + getLastName(filePath), filePath);
            }
            putObjectRequest.setProgressCallback((request, currentSize, totalSize) -> {
                if (this.builder.getOssUploadListener() != null) {
                    this.builder.getOssUploadListener().progress(this.uploadIndex, currentSize, totalSize);
                }
            });
            this.task = ossClient.asyncPutObject(putObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: ps.center.ossutils.OSSUploadTask.1
                public void onSuccess(PutObjectRequest request2, PutObjectResult result) {
                    OSSUploadTask.access$012(OSSUploadTask.this, 1);
                    if (OSSUploadTask.this.result == null) {
                        OSSUploadTask.this.result = new OSSUploadResult();
                    }
                    String successUrl = OSSUploadTask.this.ossUploadParams.url + "/" + request2.getObjectKey();
                    OSSUploadUrl ossUploadUrl = new OSSUploadUrl();
                    ossUploadUrl.url = successUrl;
                    ossUploadUrl.fileName = new File(request2.getObjectKey()).getName();
                    OSSUploadTask.this.result.urls.add(ossUploadUrl);
                    OSSUploadTask.this.upload();
                }

                public void onFailure(PutObjectRequest request2, ClientException clientException, ServiceException serviceException) {
                    if (clientException != null) {
                        clientException.printStackTrace();
                        if (OSSUploadTask.this.builder.getOssUploadListener() != null) {
                            OSSUploadTask.this.builder.getOssUploadListener().error("-1", "非阿里云存储提示；网络错误");
                            return;
                        }
                    }
                    if (serviceException != null && OSSUploadTask.this.builder.getOssUploadListener() != null) {
                        OSSUploadTask.this.builder.getOssUploadListener().error(serviceException.getErrorCode(), "网络错误");
                    }
                }
            });
        } catch (Exception e) {
            if (this.builder.getOssUploadListener() != null) {
                this.builder.getOssUploadListener().error("-1", "上传中出现错误");
            }
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }
    }

    void getUploadParams() {
        BusHttp.oss().getOSSUploadParams(String.valueOf(this.builder.getCity()), String.valueOf(this.builder.getClearTime()), this.builder.getProjectName(), this.getOSSUploadParams);
    }

    public void cancel() {
        this.isCancelTask = true;
        try {
            if (this.task != null) {
                this.task.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getLastName(Uri uri) {
        try {
            String path = Uri2Path.getPath(this.builder.getContext(), uri, false).toLowerCase();
            if (path.endsWith(".png")) {
                return ".png";
            }
            if (path.endsWith(".jpg")) {
                return ".jpg";
            }
            if (path.endsWith(".jpeg")) {
                return ".jpeg";
            }
            if (path.endsWith(".mp4")) {
                return ".mp4";
            }
            if (path.endsWith(".mp3")) {
                return ".mp3";
            }
            if (path.endsWith(".wav")) {
                return ".wav";
            }
            if (path.endsWith(".pcm")) {
                return ".pcm";
            }
            if (path.length() > 10 && path.contains(".")) {
                String newString = path.substring(path.lastIndexOf("."));
                if (newString.length() > 10) {
                    return BuildConfig.VERSION_NAME;
                }
                return newString;
            }
            return BuildConfig.VERSION_NAME;
        } catch (Exception e) {
            e.printStackTrace();
            return BuildConfig.VERSION_NAME;
        }
    }

    private String getLastName(String filePath) {
        try {
            String path = new File(filePath).getAbsolutePath().toLowerCase();
            if (path.endsWith(".png")) {
                return ".png";
            }
            if (path.endsWith(".jpg")) {
                return ".jpg";
            }
            if (path.endsWith(".jpeg")) {
                return ".jpeg";
            }
            if (path.endsWith(".mp4")) {
                return ".mp4";
            }
            if (path.endsWith(".mp3")) {
                return ".mp3";
            }
            if (path.endsWith(".wav")) {
                return ".wav";
            }
            if (path.endsWith(".pcm")) {
                return ".pcm";
            }
            if (path.length() > 10 && path.contains(".")) {
                String newString = path.substring(path.lastIndexOf("."));
                if (newString.length() > 10) {
                    return BuildConfig.VERSION_NAME;
                }
                return newString;
            }
            return BuildConfig.VERSION_NAME;
        } catch (Exception e) {
            e.printStackTrace();
            return BuildConfig.VERSION_NAME;
        }
    }
}