package ps.center.business.bean.common;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/common/ImageModeration.class */
public class ImageModeration implements Parcelable {
    public String status;
    public static final Creator<ImageModeration> CREATOR = new Creator<ImageModeration>() { // from class: ps.center.business.bean.common.ImageModeration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageModeration createFromParcel(Parcel in) {
            return new ImageModeration(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageModeration[] newArray(int size) {
            return new ImageModeration[size];
        }
    };

    public ImageModeration() {
    }

    protected ImageModeration(Parcel in) {
        this.status = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}