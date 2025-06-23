package ps.center.library.http.base;

/* loaded from: classes.jar:ps/center/library/http/base/HttpConfig.class */
public class HttpConfig<T> {
    private String baseUrl;
    private Object api;
    private Class<T> aClass;
    private HttpManager.Headers headers;
    private HttpManager.Encrypt encrypt;

    public HttpConfig(String baseUrl, Class<T> aClass, HttpManager.Headers headers, HttpManager.Encrypt encrypt) {
        this.baseUrl = baseUrl;
        this.aClass = aClass;
        this.headers = headers;
        this.encrypt = encrypt;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Class<T> getaClass() {
        return this.aClass;
    }

    public void setaClass(Class<T> aClass) {
        this.aClass = aClass;
    }

    public HttpManager.Headers getHeaders() {
        return this.headers;
    }

    public void setHeaders(HttpManager.Headers headers) {
        this.headers = headers;
    }

    public HttpManager.Encrypt getEncrypt() {
        return this.encrypt;
    }

    public void setEncrypt(HttpManager.Encrypt encrypt) {
        this.encrypt = encrypt;
    }

    public Object getApi() {
        return this.api;
    }

    public void setApi(Object api) {
        this.api = api;
    }
}