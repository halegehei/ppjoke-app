package ps.center.business.bean.common;

/* loaded from: classes.jar:ps/center/business/bean/common/IdentityAuthentication.class */
public class IdentityAuthentication {
    public int card;

    public boolean isAuthentication() {
        return this.card == 1;
    }
}