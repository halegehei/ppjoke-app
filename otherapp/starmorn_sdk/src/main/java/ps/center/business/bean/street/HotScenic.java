package ps.center.business.bean.street;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/street/HotScenic.class */
public class HotScenic implements Parcelable {
    public String id;
    public String title;
    public String prov;
    public String city;
    public String level;
    public String url;
    public String thumbnail;
    public static final Creator<HotScenic> CREATOR = new Creator<HotScenic>() { // from class: ps.center.business.bean.street.HotScenic.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HotScenic createFromParcel(Parcel in) {
            return new HotScenic(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HotScenic[] newArray(int size) {
            return new HotScenic[size];
        }
    };

    public HotScenic() {
    }

    protected HotScenic(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.prov = in.readString();
        this.city = in.readString();
        this.level = in.readString();
        this.url = in.readString();
        this.thumbnail = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.prov);
        dest.writeString(this.city);
        dest.writeString(this.level);
        dest.writeString(this.url);
        dest.writeString(this.thumbnail);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}