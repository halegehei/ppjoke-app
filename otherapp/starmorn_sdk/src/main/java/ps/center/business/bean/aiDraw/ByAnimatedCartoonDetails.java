package ps.center.business.bean.aiDraw;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/aiDraw/ByAnimatedCartoonDetails.class */
public class ByAnimatedCartoonDetails implements Parcelable {
    public String image;
    public String status;
    public String reference_url;
    public String created_at;
    public static final Creator<ByAnimatedCartoonDetails> CREATOR = new Creator<ByAnimatedCartoonDetails>() { // from class: ps.center.business.bean.aiDraw.ByAnimatedCartoonDetails.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByAnimatedCartoonDetails createFromParcel(Parcel in) {
            return new ByAnimatedCartoonDetails(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByAnimatedCartoonDetails[] newArray(int size) {
            return new ByAnimatedCartoonDetails[size];
        }
    };

    public ByAnimatedCartoonDetails() {
    }

    protected ByAnimatedCartoonDetails(Parcel in) {
        this.image = in.readString();
        this.status = in.readString();
        this.reference_url = in.readString();
        this.created_at = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.status);
        dest.writeString(this.reference_url);
        dest.writeString(this.created_at);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}