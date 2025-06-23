package ps.center.utils.luban;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.jar:ps/center/utils/luban/InputStreamProvider.class */
public interface InputStreamProvider {
    InputStream open() throws IOException;

    String getPath();
}