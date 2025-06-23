package ps.center.business.bean.street;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/street/Scenic.class */
public class Scenic implements Parcelable {
    public String id;
    public String title;
    public String prov;
    public String city;
    public String level;
    public String url;
    public String thumbnail;
    public String is_hot;
    public String view_people;
    public static final Creator<Scenic> CREATOR = new Creator<Scenic>() { // from class: ps.center.business.bean.street.Scenic.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Scenic createFromParcel(Parcel in) {
            return new Scenic(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Scenic[] newArray(int size) {
            return new Scenic[size];
        }
    };

    public Scenic() {
    }

    protected Scenic(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.prov = in.readString();
        this.city = in.readString();
        this.level = in.readString();
        this.url = in.readString();
        this.thumbnail = in.readString();
        this.is_hot = in.readString();
        this.view_people = in.readString();
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
        dest.writeString(this.is_hot);
        dest.writeString(this.view_people);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}