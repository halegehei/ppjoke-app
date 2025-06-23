package ps.center.business.bean.config;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/config/Bots.class */
public class Bots implements Parcelable {
    public String nickname;
    public String good_name;
    public String headimgurl;
    public static final Creator<Bots> CREATOR = new Creator<Bots>() { // from class: ps.center.business.bean.config.Bots.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Bots createFromParcel(Parcel in) {
            return new Bots(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Bots[] newArray(int size) {
            return new Bots[size];
        }
    };

    public Bots() {
    }

    protected Bots(Parcel in) {
        this.nickname = in.readString();
        this.good_name = in.readString();
        this.headimgurl = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nickname);
        dest.writeString(this.good_name);
        dest.writeString(this.headimgurl);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}