package ps.center.ossutils;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.jar:ps/center/ossutils/OSSUploadResult.class */
public class OSSUploadResult {
    public List<OSSUploadUrl> urls;

    public OSSUploadResult() {
        if (this.urls == null) {
            this.urls = new ArrayList();
        }
    }
}