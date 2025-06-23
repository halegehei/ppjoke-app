package ps.center.business.http.base;

import com.google.gson.Gson;
import java.util.HashMap;

/* loaded from: classes.jar:ps/center/business/http/base/BusinessRequest.class */
public class BusinessRequest {
    public String path;
    public String method;
    public String params;
    public int business_id;

    public BusinessRequest(String path) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
    }

    public BusinessRequest(String path, String method) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        this.method = method;
    }

    public BusinessRequest(String path, String method, int business_id) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        this.method = method;
        this.business_id = business_id;
    }

    public BusinessRequest(String path, Params params) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        if (params.toJson() != null) {
            this.params = params.toJson();
        }
    }

    public BusinessRequest(String path, Params params, int business_id) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        this.business_id = business_id;
        if (params.toJson() != null) {
            this.params = params.toJson();
        }
    }

    public BusinessRequest(String path, int business_id) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        this.business_id = business_id;
    }

    public BusinessRequest(String path, String method, Params params, int business_id) {
        this.method = "get";
        this.business_id = 2;
        this.path = path;
        this.method = method;
        this.business_id = business_id;
        if (params.toJson() != null) {
            this.params = params.toJson();
        }
    }

    /* loaded from: classes.jar:ps/center/business/http/base/BusinessRequest$Params.class */
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

        public BusinessRequest build(String path) {
            return build(path, "get", 2);
        }

        public BusinessRequest build(String path, String method) {
            return build(path, method, 2);
        }

        public BusinessRequest build(String path, int business_id) {
            return build(path, "get", business_id);
        }

        public BusinessRequest build(String path, String method, int business_id) {
            if (this.hashMap == null) {
                throw new NullPointerException("请求params参数为空；如果你的请求不带参数，请直接使用 new BusinessRequest(..), new BusinessRequest.Params().build(); 是给带参数的情况使用的。");
            }
            BusinessRequest businessRequest = new BusinessRequest(path);
            businessRequest.path = path;
            businessRequest.params = toJson();
            businessRequest.method = method;
            businessRequest.business_id = business_id;
            return businessRequest;
        }
    }
}