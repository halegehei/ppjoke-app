package ps.center.centerinterface.check;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/centerinterface/check/CheckParameter.class */
public final class CheckParameter {
    private static CheckParameter checkParameter;
    private static final Object lock = new Object();

    private CheckParameter() {
    }

    public static CheckParameter getInstance() {
        CheckParameter checkParameter2;
        synchronized (lock) {
            if (checkParameter == null) {
                checkParameter = new CheckParameter();
            }
            checkParameter2 = checkParameter;
        }
        return checkParameter2;
    }

    public String fullReException(Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        fullReException(obj, "data", stringBuilder);
        if (stringBuilder.toString().trim().equals(BuildConfig.VERSION_NAME)) {
            return null;
        }
        return stringBuilder.toString();
    }

    private void fullReException(Object obj, String path, StringBuilder sb) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String newPath = path + "." + field.getName();
            try {
                if (field.getType().isPrimitive() || field.getType() == String.class) {
                    if (field.get(obj) == null) {
                        sb.append("[字段]");
                        sb.append(newPath);
                        sb.append("\n");
                        Class<?> fieldType = field.getType();
                        if (fieldType == String.class) {
                            field.set(obj, "0");
                        } else if (fieldType == Integer.class) {
                            field.set(obj, 0);
                        } else if (fieldType == Double.class) {
                            field.set(obj, Double.valueOf(0.0d));
                        } else if (fieldType == Float.class) {
                            field.set(obj, Float.valueOf(0.0f));
                        } else if (fieldType == Long.class) {
                            field.set(obj, 0L);
                        } else if (fieldType == Boolean.class) {
                            field.set(obj, false);
                        } else if (fieldType == List.class || fieldType == ArrayList.class) {
                            field.set(obj, new ArrayList());
                        } else if (fieldType.isArray()) {
                            field.set(obj, new ArrayList());
                        } else if (fieldType == Map.class || fieldType == HashMap.class) {
                            field.set(obj, new HashMap());
                        }
                    }
                } else {
                    Object fieldValue = field.get(obj);
                    if (fieldValue == null) {
                        try {
                            Object newInstance = field.getType().newInstance();
                            sb.append("[模版]");
                            sb.append(newPath);
                            sb.append("\n");
                            field.set(obj, newInstance);
                            fullReException(newInstance, newPath, sb);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        fullReException(fieldValue, newPath, sb);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}