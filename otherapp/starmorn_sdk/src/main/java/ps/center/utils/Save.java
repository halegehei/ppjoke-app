package ps.center.utils;

import android.content.SharedPreferences;
import android.os.Parcelable;
import com.tencent.mmkv.MMKV;
import java.util.Set;

/* loaded from: classes.jar:ps/center/utils/Save.class */
public class Save {
    public static volatile SaveBuild instance = new SaveBuild();
    private static boolean showLog = false;

    /* loaded from: classes.jar:ps/center/utils/Save$SaveBuild.class */
    public static class SaveBuild {
        private final MMKV mmkv;

        private SaveBuild() {
            this.mmkv = MMKV.defaultMMKV();
        }

        public boolean put(String key, Object value) {
            if (value instanceof String) {
                log("[ String ] [ " + key + " ] <= " + value);
                return this.mmkv.encode(key, (String) value);
            }
            if (value instanceof Integer) {
                log("[ Integer ] [ " + key + " ] <= " + value);
                return this.mmkv.encode(key, ((Integer) value).intValue());
            }
            if (value instanceof Boolean) {
                log("[ Boolean ] [ " + key + " ] <= " + value);
                return this.mmkv.encode(key, ((Boolean) value).booleanValue());
            }
            if (value instanceof Float) {
                log("[ Float ] [ " + key + " ] <= " + value);
                return this.mmkv.encode(key, ((Float) value).floatValue());
            }
            if (value instanceof Long) {
                log("[ Long ] [ " + key + " ] <= " + value);
                return this.mmkv.encode(key, ((Long) value).longValue());
            }
            if (value instanceof Double) {
                log("[ Double ] [ " + key + " ] <= " + value);
                return this.mmkv.encode(key, ((Double) value).doubleValue());
            }
            if (value instanceof byte[]) {
                log("[ byte[] ] [ " + key + " ] <= " + value);
                return this.mmkv.encode(key, (byte[]) value);
            }
            if (value == null) {
                log("[ Object ] [ " + key + " ] <= " + ((Object) null));
                return this.mmkv.encode(key, "null");
            }
            log("[ Object ] [ " + key + " ] <= " + value.toString());
            return this.mmkv.encode(key, value.toString());
        }

        public boolean put(String key, Set<String> set) {
            log("[ Set ] [ " + key + " ] <= " + set);
            return this.mmkv.encode(key, set);
        }

        public boolean put(String key, Parcelable parcelable) {
            log("[ Parcelable ] [ " + key + " ] <= " + parcelable);
            return this.mmkv.encode(key, parcelable);
        }

        public String getString(String key, String def) {
            String string = this.mmkv.decodeString(key, def);
            log("[ String ] [ " + key + " ] => " + string);
            return string;
        }

        public int getInt(String key, Integer def) {
            int i = this.mmkv.decodeInt(key, def.intValue());
            log("[ Integer ] [ " + key + " ] => " + i);
            return i;
        }

        public boolean getBoolean(String key, Boolean def) {
            boolean b = this.mmkv.decodeBool(key, def.booleanValue());
            log("[ Boolean ] [ " + key + " ] => " + b);
            return b;
        }

        public float getFloat(String key, Float def) {
            float v = this.mmkv.decodeFloat(key, def.floatValue());
            log("[ Float ] [ " + key + " ] => " + v);
            return v;
        }

        public long getLong(String key, Long def) {
            long l = this.mmkv.decodeLong(key, def.longValue());
            log("[ Long ] [ " + key + " ] => " + l);
            return l;
        }

        public double getDouble(String key, Double def) {
            double v = this.mmkv.decodeDouble(key, def.doubleValue());
            log("[ Double ] [ " + key + " ] => " + v);
            return v;
        }

        public byte[] getBytes(String key, byte[] def) {
            byte[] bytes = this.mmkv.decodeBytes(key, def);
            log("[ Bytes ] [ " + key + " ] => length:" + (bytes == null ? 0 : bytes.length));
            return bytes;
        }

        public Set<String> getSet(String key, Set<String> set) {
            Set<String> strings = this.mmkv.decodeStringSet(key, set);
            log("[ Set ] [ " + key + " ] => " + strings);
            return strings;
        }

        public <T extends Parcelable> T getParcelable(String str, Class<T> cls) {
            T t = (T) this.mmkv.decodeParcelable(str, cls);
            log("[ Parcelable ] [ " + str + " ] => " + t);
            return t;
        }

        public SharedPreferences.Editor remove(String key) {
            log("[ remove ] = " + key);
            return this.mmkv.remove(key);
        }

        public String[] allKeys() {
            String[] strings = this.mmkv.allKeys();
            log("[ allKeys ] = " + (strings == null ? 0 : strings.length));
            return strings;
        }

        public SharedPreferences.Editor clear() {
            log("[ clear ]");
            return this.mmkv.clear();
        }

        public void clearAll() {
            log("[ clearAll ]");
            this.mmkv.clearAll();
        }

        private void log(Object object) {
            if (Save.showLog) {
                android.util.Log.e("[ mylog ]", object.toString());
            }
        }

        public static void setLogEnable(boolean logEnable) {
            boolean unused = Save.showLog = logEnable;
        }
    }
}