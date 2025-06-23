package ps.center.business.bean.collect;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/collect/CollectStatus.class */
public class CollectStatus implements Parcelable {
    public String status;
    public static final Creator<CollectStatus> CREATOR = new Creator<CollectStatus>() { // from class: ps.center.business.bean.collect.CollectStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CollectStatus createFromParcel(Parcel in) {
            return new CollectStatus(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CollectStatus[] newArray(int size) {
            return new CollectStatus[size];
        }
    };

    protected CollectStatus(Parcel in) {
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