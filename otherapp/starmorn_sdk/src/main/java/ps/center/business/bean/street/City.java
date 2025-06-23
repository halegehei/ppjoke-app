package ps.center.business.bean.street;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/street/City.class */
public class City implements Parcelable {
    public String id;
    public String title;
    public String subTitle;
    public String image;
    public static final Creator<City> CREATOR = new Creator<City>() { // from class: ps.center.business.bean.street.City.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public City() {
    }

    protected City(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.subTitle = in.readString();
        this.image = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.subTitle);
        dest.writeString(this.image);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}