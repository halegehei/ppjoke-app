package ps.center.library.http.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import ps.center.library.http.base.HttpManager;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* loaded from: classes.jar:ps/center/library/http/gson/CustomConverterFactory.class */
public class CustomConverterFactory extends Converter.Factory {
    private final Gson gson;
    private final HttpManager.Encrypt encrypt;
    private final HttpManager.PreBodyContent preBodyContent;

    public static CustomConverterFactory create() {
        return create(new Gson());
    }

    public static CustomConverterFactory create(Gson gson) {
        return new CustomConverterFactory(gson, null, null);
    }

    public static CustomConverterFactory create(Gson gson, HttpManager.Encrypt encrypt, HttpManager.PreBodyContent preBodyContent) {
        return new CustomConverterFactory(gson, encrypt, preBodyContent);
    }

    private CustomConverterFactory(Gson gson, HttpManager.Encrypt encrypt, HttpManager.PreBodyContent preBodyContent) {
        if (gson == null) {
            throw new NullPointerException("Http请求时, 返回的数据 == null");
        }
        this.gson = gson;
        this.encrypt = encrypt;
        this.preBodyContent = preBodyContent;
    }

    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = this.gson.getAdapter(TypeToken.get(type));
        return new DecodeResponseBodyConverter(adapter, this.encrypt, this.preBodyContent);
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = this.gson.getAdapter(TypeToken.get(type));
        return new DecodeRequestBodyConverter(this.gson, adapter);
    }
}