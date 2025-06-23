package ps.center.business.bean.common;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/common/TextModeration.class */
public class TextModeration implements Parcelable {
    public String status;
    public static final Creator<TextModeration> CREATOR = new Creator<TextModeration>() { // from class: ps.center.business.bean.common.TextModeration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextModeration createFromParcel(Parcel in) {
            return new TextModeration(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextModeration[] newArray(int size) {
            return new TextModeration[size];
        }
    };

    public TextModeration() {
    }

    protected TextModeration(Parcel in) {
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