package ps.center.library.http.base;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import ps.center.library.http.LogUtils;
import ps.center.library.http.gson.CustomConverterFactory;
import ps.center.library.http.gson.ShowJson;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/* loaded from: classes.jar:ps/center/library/http/base/HttpManager.class */
public class HttpManager {
    private static final String TAG = "HttpManager";
    private static Set<Headers> headersSet;
    private static Encrypt encrypt;
    private static PreBodyContent preBodyContent;
    private static HashMap<String, Object> apis;
    private static OkHttpClient.Builder builder;

    /* loaded from: classes.jar:ps/center/library/http/base/HttpManager$Encrypt.class */
    public static abstract class Encrypt {
        public abstract String deCode(String str);
    }

    /* loaded from: classes.jar:ps/center/library/http/base/HttpManager$Headers.class */
    public static abstract class Headers {
        public abstract HashMap<String, String> add(HashMap<String, String> hashMap);
    }

    /* loaded from: classes.jar:ps/center/library/http/base/HttpManager$PreBodyContent.class */
    public static abstract class PreBodyContent {
        public abstract String body(String str);
    }

    private static void init(Headers headers, Encrypt encrypt2, PreBodyContent preBodyContent2) {
        if (encrypt2 != null) {
            encrypt = encrypt2;
        }
        if (preBodyContent2 != null) {
            preBodyContent = preBodyContent2;
        }
        if (headersSet == null) {
            headersSet = new HashSet();
        }
        headersSet.add(headers);
    }

    public static <T> T getApi(Class<T> cls) {
        if (builder == null) {
            builder = new OkHttpClient.Builder();
        }
        if (apis == null) {
            apis = new HashMap<>();
        }
        if (apis.get(cls.getName()) != null) {
            LogUtils.e(String.format("%s, 当前%s映射类已存在，直接返回复用", TAG, cls.getName()));
            return (T) apis.get(cls.getName());
        }
        builder.interceptors().clear();
        builder.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            requestBuilder.addHeader("Connection", "close");
            if (headersSet != null) {
                for (Headers headers : headersSet) {
                    HashMap<String, String> add = headers.add(new HashMap<>());
                    if (add != null && add.size() > 0) {
                        for (Map.Entry<String, String> item : add.entrySet()) {
                            requestBuilder.addHeader(item.getKey(), item.getValue());
                        }
                    }
                }
            }
            builder.connectTimeout(20L, TimeUnit.SECONDS);
            builder.writeTimeout(20L, TimeUnit.SECONDS);
            builder.readTimeout(20L, TimeUnit.SECONDS);
            builder.callTimeout(20L, TimeUnit.SECONDS);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        builder.connectTimeout(20L, TimeUnit.SECONDS);
        builder.writeTimeout(20L, TimeUnit.SECONDS);
        builder.readTimeout(20L, TimeUnit.SECONDS);
        builder.callTimeout(20L, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        builder.networkInterceptors().clear();
        builder.addNetworkInterceptor(new HttpLoggingInterceptor(new ShowJson()).setLevel(HttpLoggingInterceptor.Level.BODY));
        T t = (T) new Retrofit.Builder().baseUrl("https://www.baidu.com/").client(builder.build()).addConverterFactory(CustomConverterFactory.create(new Gson(), encrypt, preBodyContent)).addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync()).build().create(cls);
        apis.put(cls.getName(), t);
        LogUtils.e(String.format("%s, 当前%s映射类不存在，生成并存储，当前存储数量：%s", TAG, cls.getName(), Integer.valueOf(apis.size())));
        return t;
    }

    public static void initHttp(Headers headers) {
        init(headers, null, null);
    }

    public static void initHttp(Headers headers, Encrypt encrypt2) {
        init(headers, encrypt2, null);
    }

    public static void initHttp(Headers headers, PreBodyContent preBodyContent2) {
        init(headers, null, preBodyContent2);
    }

    public static void initHttp(Headers headers, Encrypt encrypt2, PreBodyContent preBodyContent2) {
        init(headers, encrypt2, preBodyContent2);
    }
}