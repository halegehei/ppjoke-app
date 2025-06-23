package ps.center.business.bean.faceChange;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/faceChange/ByImageFusionInfo.class */
public class ByImageFusionInfo implements Parcelable {
    public int status;
    public String url;
    public static final Creator<ByImageFusionInfo> CREATOR = new Creator<ByImageFusionInfo>() { // from class: ps.center.business.bean.faceChange.ByImageFusionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByImageFusionInfo createFromParcel(Parcel in) {
            return new ByImageFusionInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByImageFusionInfo[] newArray(int size) {
            return new ByImageFusionInfo[size];
        }
    };

    public ByImageFusionInfo() {
    }

    protected ByImageFusionInfo(Parcel in) {
        this.status = in.readInt();
        this.url = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.url);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}