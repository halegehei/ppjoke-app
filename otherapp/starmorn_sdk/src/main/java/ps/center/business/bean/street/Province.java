package ps.center.business.bean.street;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/street/Province.class */
public class Province implements Parcelable {
    public String id;
    public String title;
    public static final Creator<Province> CREATOR = new Creator<Province>() { // from class: ps.center.business.bean.street.Province.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Province createFromParcel(Parcel in) {
            return new Province(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Province[] newArray(int size) {
            return new Province[size];
        }
    };

    public Province() {
    }

    protected Province(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}