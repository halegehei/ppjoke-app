package ps.center.business.bean.street;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/street/HomeScenic.class */
public class HomeScenic implements Parcelable {
    public String id;
    public String title;
    public String thumbnail;
    public String url;
    public static final Creator<HomeScenic> CREATOR = new Creator<HomeScenic>() { // from class: ps.center.business.bean.street.HomeScenic.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HomeScenic createFromParcel(Parcel in) {
            return new HomeScenic(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HomeScenic[] newArray(int size) {
            return new HomeScenic[size];
        }
    };

    public HomeScenic() {
    }

    protected HomeScenic(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.thumbnail = in.readString();
        this.url = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.thumbnail);
        dest.writeString(this.url);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}