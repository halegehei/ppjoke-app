package ps.center.business.bean.street;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/street/HomeStreet.class */
public class HomeStreet implements Parcelable {
    public String id;
    public String title;
    public String image;
    public String url;
    public String accuracy;
    public String latitude;
    public static final Creator<HomeStreet> CREATOR = new Creator<HomeStreet>() { // from class: ps.center.business.bean.street.HomeStreet.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HomeStreet createFromParcel(Parcel in) {
            return new HomeStreet(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HomeStreet[] newArray(int size) {
            return new HomeStreet[size];
        }
    };

    public HomeStreet() {
    }

    protected HomeStreet(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.image = in.readString();
        this.url = in.readString();
        this.accuracy = in.readString();
        this.latitude = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.image);
        dest.writeString(this.url);
        dest.writeString(this.accuracy);
        dest.writeString(this.latitude);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}