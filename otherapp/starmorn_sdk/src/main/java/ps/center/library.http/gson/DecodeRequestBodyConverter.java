package ps.center.library.http.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

public class DecodeRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE =
            MediaType.get("application/json; charset=UTF-8");
    private static final String CHARSET_NAME = "UTF-8";

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public DecodeRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        // 1. 写入 Buffer
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), CHARSET_NAME);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);

        // 2. 使用 TypeAdapter 将 value 序列化到 jsonWriter
        adapter.write(jsonWriter, value);

        // 3. 刷新并关闭流
        jsonWriter.flush();
        // 记得关闭 writer（可在 Java7+ 改用 try-with-resources）
        writer.close();

        // 4. 从 Buffer 中读取 UTF-8 字符串
        String json = buffer.readUtf8();

        // 5. 创建 RequestBody
        return RequestBody.create(MEDIA_TYPE, json);
    }
}
