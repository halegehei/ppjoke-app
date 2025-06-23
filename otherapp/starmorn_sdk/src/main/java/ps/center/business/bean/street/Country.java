package ps.center.business.bean.street;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/street/Country.class */
public class Country implements Parcelable {
    public String id;
    public String name;
    public static final Creator<Country> CREATOR = new Creator<Country>() { // from class: ps.center.business.bean.street.Country.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public Country() {
    }

    protected Country(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}