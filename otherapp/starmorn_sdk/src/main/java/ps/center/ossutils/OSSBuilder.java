package ps.center.ossutils;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.jar:ps/center/ossutils/OSSBuilder.class */
public class OSSBuilder {
    private final Context context;
    private List<String> filePath;
    private List<Uri> uris;
    private OSSCity city;
    private OSSClearTime time;
    private String projectName;
    private OSSUploadListener ossUploadListener;

    public OSSBuilder(Context context) {
        this.context = context;
    }

    public OSSBuilder filePath(List<String> filePaths) {
        if (filePaths == null) {
            return this;
        }
        if (this.filePath == null) {
            this.filePath = new ArrayList();
        }
        this.filePath.addAll(filePaths);
        return this;
    }

    public OSSBuilder filePath(String... filePaths) {
        if (filePaths == null) {
            return this;
        }
        if (this.filePath == null) {
            this.filePath = new ArrayList();
        }
        this.filePath.addAll(Arrays.asList(filePaths));
        return this;
    }

    public OSSBuilder uris(List<Uri> uris) {
        if (uris == null) {
            return this;
        }
        if (this.uris == null) {
            this.uris = new ArrayList();
        }
        this.uris.addAll(uris);
        return this;
    }

    public OSSBuilder uris(Uri... uris) {
        if (uris == null) {
            return this;
        }
        if (this.uris == null) {
            this.uris = new ArrayList();
        }
        this.uris.addAll(Arrays.asList(uris));
        return this;
    }

    public OSSBuilder city(OSSCity ossCity) {
        this.city = ossCity;
        return this;
    }

    public OSSBuilder clearTime(OSSClearTime ossClearTime) {
        this.time = ossClearTime;
        return this;
    }

    public OSSBuilder projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public OSSUploadTask upload(OSSUploadListener ossUploadListener) {
        this.ossUploadListener = ossUploadListener;
        return new OSSUploadTask(this);
    }

    protected Context getContext() {
        return this.context;
    }

    protected List<String> getFilePath() {
        return this.filePath;
    }

    protected List<Uri> getUris() {
        return this.uris;
    }

    protected int getCity() {
        if (this.city == null) {
            return 1;
        }
        return this.city.getValue();
    }

    protected int getClearTime() {
        if (this.time == null) {
            return 1;
        }
        return this.time.getValue();
    }

    protected String getProjectName() {
        return this.projectName;
    }

    protected OSSUploadListener getOssUploadListener() {
        return this.ossUploadListener;
    }
}