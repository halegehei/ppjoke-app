package ps.center.business.bean.street;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/street/HomeClassify.class */
public class HomeClassify implements Parcelable {
    public String id;
    public String name;
    public static final Creator<HomeClassify> CREATOR = new Creator<HomeClassify>() { // from class: ps.center.business.bean.street.HomeClassify.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HomeClassify createFromParcel(Parcel in) {
            return new HomeClassify(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HomeClassify[] newArray(int size) {
            return new HomeClassify[size];
        }
    };

    public HomeClassify() {
    }

    protected HomeClassify(Parcel in) {
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