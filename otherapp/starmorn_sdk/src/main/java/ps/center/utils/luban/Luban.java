package ps.center.utils.luban;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.jar:ps/center/utils/luban/Luban.class */
public class Luban implements Handler.Callback {
    private static final String TAG = "Luban";
    private static final String DEFAULT_DISK_CACHE_DIR = "luban_disk_cache";
    private static final int MSG_COMPRESS_SUCCESS = 0;
    private static final int MSG_COMPRESS_START = 1;
    private static final int MSG_COMPRESS_ERROR = 2;
    private String mTargetDir;
    private boolean focusAlpha;
    private final int mLeastCompressSize;
    private final OnRenameListener mRenameListener;
    private final OnCompressListener mCompressListener;
    private final CompressionPredicate mCompressionPredicate;
    private final List<InputStreamProvider> mStreamProviders;
    private final Handler mHandler;

    private Luban(Builder builder) {
        this.mTargetDir = builder.mTargetDir;
        this.mRenameListener = builder.mRenameListener;
        this.mStreamProviders = builder.mStreamProviders;
        this.mCompressListener = builder.mCompressListener;
        this.mLeastCompressSize = builder.mLeastCompressSize;
        this.mCompressionPredicate = builder.mCompressionPredicate;
        this.mHandler = new Handler(Looper.getMainLooper(), this);
    }

    public static Builder with(Context context) {
        return new Builder(context);
    }

    private File getImageCacheFile(Context context, String suffix) {
        if (TextUtils.isEmpty(this.mTargetDir)) {
            this.mTargetDir = getImageCacheDir(context).getAbsolutePath();
        }
        String cacheBuilder = this.mTargetDir + "/" + System.currentTimeMillis() + ((int) (Math.random() * 1000.0d)) + (TextUtils.isEmpty(suffix) ? ".jpg" : suffix);
        return new File(cacheBuilder);
    }

    private File getImageCustomFile(Context context, String filename) {
        if (TextUtils.isEmpty(this.mTargetDir)) {
            this.mTargetDir = getImageCacheDir(context).getAbsolutePath();
        }
        String cacheBuilder = this.mTargetDir + "/" + filename;
        return new File(cacheBuilder);
    }

    private File getImageCacheDir(Context context) {
        return getImageCacheDir(context, DEFAULT_DISK_CACHE_DIR);
    }

    private static File getImageCacheDir(Context context, String cacheName) {
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir != null) {
            File result = new File(cacheDir, cacheName);
            if (!result.mkdirs() && (!result.exists() || !result.isDirectory())) {
                return null;
            }
            return result;
        }
        if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "default disk cache dir is null");
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launch(final Context context) {
        if (this.mStreamProviders == null || (this.mStreamProviders.size() == 0 && this.mCompressListener != null)) {
            this.mCompressListener.onError(new NullPointerException("image file cannot be null"));
        }
        Iterator<InputStreamProvider> iterator = this.mStreamProviders.iterator();
        while (iterator.hasNext()) {
            final InputStreamProvider path = iterator.next();
            AsyncTask.SERIAL_EXECUTOR.execute(new Runnable() { // from class: ps.center.utils.luban.Luban.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Luban.this.mHandler.sendMessage(Luban.this.mHandler.obtainMessage(1));
                        File result = Luban.this.compress(context, path);
                        Luban.this.mHandler.sendMessage(Luban.this.mHandler.obtainMessage(0, result));
                    } catch (IOException e) {
                        Luban.this.mHandler.sendMessage(Luban.this.mHandler.obtainMessage(2, e));
                    }
                }
            });
            iterator.remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File get(InputStreamProvider input, Context context) throws IOException {
        return new Engine(input, getImageCacheFile(context, Checker.SINGLE.extSuffix(input)), this.focusAlpha).compress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<File> get(Context context) throws IOException {
        List<File> results = new ArrayList<>();
        Iterator<InputStreamProvider> iterator = this.mStreamProviders.iterator();
        while (iterator.hasNext()) {
            results.add(compress(context, iterator.next()));
            iterator.remove();
        }
        return results;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File compress(Context context, InputStreamProvider path) throws IOException {
        File file;
        File result;
        File outFile = getImageCacheFile(context, Checker.SINGLE.extSuffix(path));
        if (this.mRenameListener != null) {
            String filename = this.mRenameListener.rename(path.getPath());
            outFile = getImageCustomFile(context, filename);
        }
        if (this.mCompressionPredicate != null) {
            if (this.mCompressionPredicate.apply(path.getPath()) && Checker.SINGLE.needCompress(this.mLeastCompressSize, path.getPath())) {
                result = new Engine(path, outFile, this.focusAlpha).compress();
            } else {
                result = new File(path.getPath());
            }
        } else {
            if (Checker.SINGLE.needCompress(this.mLeastCompressSize, path.getPath())) {
                file = new Engine(path, outFile, this.focusAlpha).compress();
            } else {
                file = new File(path.getPath());
            }
            result = file;
        }
        return result;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        if (this.mCompressListener == null) {
            return false;
        }
        switch (msg.what) {
            case 0:
                this.mCompressListener.onSuccess((File) msg.obj);
                break;
            case 1:
                this.mCompressListener.onStart();
                break;
            case 2:
                this.mCompressListener.onError((Throwable) msg.obj);
                break;
        }
        return false;
    }

    /* loaded from: classes.jar:ps/center/utils/luban/Luban$Builder.class */
    public static class Builder {
        private Context context;
        private String mTargetDir;
        private boolean focusAlpha;
        private OnRenameListener mRenameListener;
        private OnCompressListener mCompressListener;
        private CompressionPredicate mCompressionPredicate;
        private int mLeastCompressSize = 100;
        private List<InputStreamProvider> mStreamProviders = new ArrayList();

        Builder(Context context) {
            this.context = context;
        }

        private Luban build() {
            return new Luban(this);
        }

        public Builder load(InputStreamProvider inputStreamProvider) {
            this.mStreamProviders.add(inputStreamProvider);
            return this;
        }

        public Builder load(final File file) {
            this.mStreamProviders.add(new InputStreamProvider() { // from class: ps.center.utils.luban.Luban.Builder.1
                @Override // ps.center.utils.luban.InputStreamProvider
                public InputStream open() throws IOException {
                    return new FileInputStream(file);
                }

                @Override // ps.center.utils.luban.InputStreamProvider
                public String getPath() {
                    return file.getAbsolutePath();
                }
            });
            return this;
        }

        public Builder load(final String string) {
            this.mStreamProviders.add(new InputStreamProvider() { // from class: ps.center.utils.luban.Luban.Builder.2
                @Override // ps.center.utils.luban.InputStreamProvider
                public InputStream open() throws IOException {
                    return new FileInputStream(string);
                }

                @Override // ps.center.utils.luban.InputStreamProvider
                public String getPath() {
                    return string;
                }
            });
            return this;
        }

        public <T> Builder load(List<T> list) {
            for (T t : list) {
                if (t instanceof String) {
                    load((String) t);
                } else if (t instanceof File) {
                    load((File) t);
                } else if (t instanceof Uri) {
                    load((Uri) t);
                } else {
                    throw new IllegalArgumentException("Incoming data type exception, it must be String, File, Uri or Bitmap");
                }
            }
            return this;
        }

        public Builder load(final Uri uri) {
            this.mStreamProviders.add(new InputStreamProvider() { // from class: ps.center.utils.luban.Luban.Builder.3
                @Override // ps.center.utils.luban.InputStreamProvider
                public InputStream open() throws IOException {
                    return Builder.this.context.getContentResolver().openInputStream(uri);
                }

                @Override // ps.center.utils.luban.InputStreamProvider
                public String getPath() {
                    return uri.getPath();
                }
            });
            return this;
        }

        public Builder putGear(int gear) {
            return this;
        }

        public Builder setRenameListener(OnRenameListener listener) {
            this.mRenameListener = listener;
            return this;
        }

        public Builder setCompressListener(OnCompressListener listener) {
            this.mCompressListener = listener;
            return this;
        }

        public Builder setTargetDir(String targetDir) {
            this.mTargetDir = targetDir;
            return this;
        }

        public Builder setFocusAlpha(boolean focusAlpha) {
            this.focusAlpha = focusAlpha;
            return this;
        }

        public Builder ignoreBy(int size) {
            this.mLeastCompressSize = size;
            return this;
        }

        public Builder filter(CompressionPredicate compressionPredicate) {
            this.mCompressionPredicate = compressionPredicate;
            return this;
        }

        public void launch() {
            build().launch(this.context);
        }

        public File get(final String path) throws IOException {
            return build().get(new InputStreamProvider() { // from class: ps.center.utils.luban.Luban.Builder.4
                @Override // ps.center.utils.luban.InputStreamProvider
                public InputStream open() throws IOException {
                    return new FileInputStream(path);
                }

                @Override // ps.center.utils.luban.InputStreamProvider
                public String getPath() {
                    return path;
                }
            }, this.context);
        }

        public List<File> get() throws IOException {
            return build().get(this.context);
        }
    }
}