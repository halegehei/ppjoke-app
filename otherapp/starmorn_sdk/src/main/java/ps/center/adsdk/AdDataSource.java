package ps.center.adsdk;

/* loaded from: classes.jar:ps/center/adsdk/AdDataSource.class */
public class AdDataSource {
    private static ADTask.CallBack callBack;

    public static ADTask.CallBack getCallBack() {
        return callBack;
    }

    public static void setCallBack(ADTask.CallBack callBack2) {
        callBack = callBack2;
    }
}