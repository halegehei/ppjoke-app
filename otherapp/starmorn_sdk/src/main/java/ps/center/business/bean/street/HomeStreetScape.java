package ps.center.business.bean.street;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.jar:ps/center/business/bean/street/HomeStreetScape.class */
public class HomeStreetScape implements Parcelable {
    public String id;
    public String address;
    public String people;
    public String nickname;
    public String headerimg;
    public List<String> pic_thumb_arr;
    public String url;
    public static final Creator<HomeStreetScape> CREATOR = new Creator<HomeStreetScape>() { // from class: ps.center.business.bean.street.HomeStreetScape.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HomeStreetScape createFromParcel(Parcel in) {
            return new HomeStreetScape(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HomeStreetScape[] newArray(int size) {
            return new HomeStreetScape[size];
        }
    };

    protected HomeStreetScape(Parcel in) {
        this.id = in.readString();
        this.address = in.readString();
        this.people = in.readString();
        this.nickname = in.readString();
        this.headerimg = in.readString();
        this.pic_thumb_arr = in.createStringArrayList();
        this.url = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.address);
        dest.writeString(this.people);
        dest.writeString(this.nickname);
        dest.writeString(this.headerimg);
        dest.writeStringList(this.pic_thumb_arr);
        dest.writeString(this.url);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}