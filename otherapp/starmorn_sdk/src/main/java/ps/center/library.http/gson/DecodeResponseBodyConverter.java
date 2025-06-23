package ps.center.library.http.gson;

import com.google.gson.TypeAdapter;
import java.io.IOException;
import okhttp3.ResponseBody;
import ps.center.library.http.base.HttpManager;
import retrofit2.Converter;

/* loaded from: classes.jar:ps/center/library/http/gson/DecodeResponseBodyConverter.class */
public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final HttpManager.Encrypt encrypt;
    private final HttpManager.PreBodyContent preBodyContent;

    DecodeResponseBodyConverter(TypeAdapter<T> adapter, HttpManager.Encrypt encrypt, HttpManager.PreBodyContent preBodyContent) {
        this.adapter = adapter;
        this.encrypt = encrypt;
        this.preBodyContent = preBodyContent;
    }

    public T convert(ResponseBody responseBody) throws IOException {
        String string = responseBody.string();
        if (this.preBodyContent != null) {
            string = this.preBodyContent.body(string);
        }
        if (this.encrypt != null && !string.startsWith("{")) {
            string = this.encrypt.deCode(string);
        }
        return (T) this.adapter.fromJson(string);
    }
}