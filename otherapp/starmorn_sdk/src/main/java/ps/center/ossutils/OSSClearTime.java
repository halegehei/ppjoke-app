package ps.center.ossutils;

/* loaded from: classes.jar:ps/center/ossutils/OSSClearTime.class */
public enum OSSClearTime {
    oneMonth(0),
    halfYear(1),
    oneYear(2);

    private final int id;

    OSSClearTime(int id) {
        this.id = id;
    }

    public int getValue() {
        return this.id;
    }
}