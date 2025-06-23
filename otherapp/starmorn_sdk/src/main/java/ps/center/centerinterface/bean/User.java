package ps.center.centerinterface.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import ps.center.utils.Save;

/* loaded from: classes.jar:ps/center/centerinterface/bean/User.class */
public class User implements Parcelable {
    public boolean isSign;
    public String username;
    public String avatar;
    public boolean isVip;
    public long vip_time;
    public int channel_id;
    public String packet;
    public String wx_openid;
    public String wx_unionid;
    public String tel;
    public String email;
    public String apple_user;
    public int user_create;
    public int vip_type;
    public int refund;
    public String vip_money;
    public int money;
    public List<String> region;
    public static final Creator<User> CREATOR = new Creator<User>() { // from class: ps.center.centerinterface.bean.User.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User() {
    }

    public int getFreeNum() {
        return Save.instance.getInt("freeNum", 0);
    }

    public boolean decrementFreeNum() {
        try {
            int freeNum = Save.instance.getInt("freeNum", 0);
            if (freeNum > 0) {
                Save.instance.put("freeNum", Integer.valueOf(freeNum - 1));
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected User(Parcel in) {
        this.isSign = in.readByte() != 0;
        this.username = in.readString();
        this.avatar = in.readString();
        this.isVip = in.readByte() != 0;
        this.vip_time = in.readLong();
        this.channel_id = in.readInt();
        this.packet = in.readString();
        this.wx_openid = in.readString();
        this.wx_unionid = in.readString();
        this.tel = in.readString();
        this.email = in.readString();
        this.apple_user = in.readString();
        this.user_create = in.readInt();
        this.vip_type = in.readInt();
        this.refund = in.readInt();
        this.vip_money = in.readString();
        this.money = in.readInt();
        this.region = in.createStringArrayList();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.isSign ? 1 : 0));
        dest.writeString(this.username);
        dest.writeString(this.avatar);
        dest.writeByte((byte) (this.isVip ? 1 : 0));
        dest.writeLong(this.vip_time);
        dest.writeInt(this.channel_id);
        dest.writeString(this.packet);
        dest.writeString(this.wx_openid);
        dest.writeString(this.wx_unionid);
        dest.writeString(this.tel);
        dest.writeString(this.email);
        dest.writeString(this.apple_user);
        dest.writeInt(this.user_create);
        dest.writeInt(this.vip_type);
        dest.writeInt(this.refund);
        dest.writeString(this.vip_money);
        dest.writeInt(this.money);
        dest.writeStringList(this.region);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}