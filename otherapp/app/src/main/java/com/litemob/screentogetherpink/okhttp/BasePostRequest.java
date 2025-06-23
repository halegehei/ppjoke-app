package com.litemob.screentogetherpink.okhttp;

import com.google.gson.Gson;

import java.util.HashMap;

public class BasePostRequest {
    public String path;
    public String method = "post";
    public String params;
    public int business_id = 5;


    public BasePostRequest(String path) {
        this.path = path;
    }

    public BasePostRequest(String path, String method) {
        this.path = path;
        this.method = method;
    }

    public BasePostRequest(String path, String method, int business_id) {
        this.path = path;
        this.method = method;
        this.business_id = business_id;
    }

    public BasePostRequest(String path, Params params) {
        this.path = path;
        if (params.toJson() != null){
            this.params = params.toJson();
        }
    }

    public BasePostRequest(String path, Params params, int business_id) {
        this.path = path;
        this.business_id = business_id;
        if (params.toJson() != null){
            this.params = params.toJson();
        }
    }

    public BasePostRequest(String path, int business_id) {
        this.path = path;
        this.business_id = business_id;
    }

    public BasePostRequest(String path, String method, Params params, int business_id) {
        this.path = path;
        this.method = method;
        this.business_id = business_id;
        if (params.toJson() != null){
            this.params = params.toJson();
        }
    }

    public static class Params {

        private HashMap<String, Object> hashMap;

        public Params() {
            this.hashMap = new HashMap<>();
        }

        public Params add(String key, Object value) {
            hashMap.put(key, value);
            return this;
        }

        public String toJson(){
            if (hashMap != null && hashMap.size() > 0){
                return new Gson().toJson(hashMap);
            }else{
                return null;
            }
        }
    }
}
