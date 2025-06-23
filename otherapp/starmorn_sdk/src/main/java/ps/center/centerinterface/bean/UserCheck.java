package ps.center.centerinterface.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/centerinterface/bean/UserCheck.class */
public class UserCheck implements Parcelable {
    public String username;
    public long uid;
    public String avatar;
    public String user_reg;
    public String wx_openid;
    public String wx_unionid;
    public String tel;
    public static final Creator<UserCheck> CREATOR = new Creator<UserCheck>() { // from class: ps.center.centerinterface.bean.UserCheck.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserCheck createFromParcel(Parcel in) {
            return new UserCheck(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserCheck[] newArray(int size) {
            return new UserCheck[size];
        }
    };

    public UserCheck() {
    }

    protected UserCheck(Parcel in) {
        this.username = in.readString();
        this.uid = in.readLong();
        this.avatar = in.readString();
        this.user_reg = in.readString();
        this.wx_openid = in.readString();
        this.wx_unionid = in.readString();
        this.tel = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeLong(this.uid);
        dest.writeString(this.avatar);
        dest.writeString(this.user_reg);
        dest.writeString(this.wx_openid);
        dest.writeString(this.wx_unionid);
        dest.writeString(this.tel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}