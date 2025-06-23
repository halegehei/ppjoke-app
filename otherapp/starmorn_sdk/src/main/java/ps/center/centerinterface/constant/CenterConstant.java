package ps.center.centerinterface.constant;

import android.os.Parcelable;
import ps.center.centerinterface.bean.User;
import ps.center.utils.Save;

/* loaded from: classes.jar:ps/center/centerinterface/constant/CenterConstant.class */
public class CenterConstant {
    public static final String UID = "uid";

    public static void setUser(User user) {
        Save.instance.put("userInfo", (Parcelable) user);
    }

    public static User getUser() {
        return (User) Save.instance.getParcelable("userInfo", User.class);
    }
}