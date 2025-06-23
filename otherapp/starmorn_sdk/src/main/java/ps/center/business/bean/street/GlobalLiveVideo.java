package ps.center.business.bean.street;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/street/GlobalLiveVideo.class */
public class GlobalLiveVideo implements Parcelable {
    public String id;
    public String title;
    public String video_url;
    public String is_hot;
    public String view_people;
    public static final Creator<GlobalLiveVideo> CREATOR = new Creator<GlobalLiveVideo>() { // from class: ps.center.business.bean.street.GlobalLiveVideo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalLiveVideo createFromParcel(Parcel in) {
            return new GlobalLiveVideo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalLiveVideo[] newArray(int size) {
            return new GlobalLiveVideo[size];
        }
    };

    public GlobalLiveVideo() {
    }

    protected GlobalLiveVideo(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.video_url = in.readString();
        this.is_hot = in.readString();
        this.view_people = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.video_url);
        dest.writeString(this.is_hot);
        dest.writeString(this.view_people);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}