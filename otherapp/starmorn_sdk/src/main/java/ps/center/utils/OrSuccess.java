package ps.center.utils;

/* loaded from: classes.jar:ps/center/utils/OrSuccess.class */
public interface OrSuccess<T> {
    void success(T t);

    void err(T t);
}