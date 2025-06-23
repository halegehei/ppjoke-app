package ps.center.ossutils;

/* loaded from: classes.jar:ps/center/ossutils/OSSUploadListener.class */
public interface OSSUploadListener {
    void progress(int i, long j, long j2);

    void success(OSSUploadResult oSSUploadResult);

    void error(String str, String str2);
}