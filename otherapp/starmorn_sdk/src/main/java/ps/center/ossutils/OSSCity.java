package ps.center.ossutils;

/* loaded from: classes.jar:ps/center/ossutils/OSSCity.class */
public enum OSSCity {
    beijing(1),
    shanghai(2);

    private final int id;

    OSSCity(int id) {
        this.id = id;
    }

    public int getValue() {
        return this.id;
    }
}