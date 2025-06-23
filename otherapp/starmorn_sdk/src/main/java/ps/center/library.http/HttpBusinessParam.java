package ps.center.library.http;

import com.google.gson.Gson;
import java.util.HashMap;

/* loaded from: classes.jar:ps/center/library/http/HttpBusinessParam.class */
public class HttpBusinessParam {
    public String path;
    public String method;
    public String params;
    public int business_id;

    public HttpBusinessParam(String path) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
    }

    public HttpBusinessParam(String path, String method) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        this.method = method;
    }

    public HttpBusinessParam(String path, String method, int business_id) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        this.method = method;
        this.business_id = business_id;
    }

    public HttpBusinessParam(String path, Params params) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        if (params.toJson() != null) {
            this.params = params.toJson();
        }
    }

    public HttpBusinessParam(String path, Params params, int business_id) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        this.business_id = business_id;
        if (params.toJson() != null) {
            this.params = params.toJson();
        }
    }

    public HttpBusinessParam(String path, int business_id) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        this.business_id = business_id;
    }

    public HttpBusinessParam(String path, String method, Params params, int business_id) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        this.method = method;
        this.business_id = business_id;
        if (params.toJson() != null) {
            this.params = params.toJson();
        }
    }

    /* loaded from: classes.jar:ps/center/library/http/HttpBusinessParam$Params.class */
    public static class Params {
        private HashMap<String, Object> hashMap = new HashMap<>();

        public Params add(String key, Object value) {
            this.hashMap.put(key, value);
            return this;
        }

        public String toJson() {
            if (this.hashMap != null && this.hashMap.size() > 0) {
                return new Gson().toJson(this.hashMap);
            }
            return null;
        }
    }
}