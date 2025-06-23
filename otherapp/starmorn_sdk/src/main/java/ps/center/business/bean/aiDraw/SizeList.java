package ps.center.business.bean.aiDraw;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/aiDraw/SizeList.class */
public class SizeList implements Parcelable {
    public String size_id;
    public String size;
    public static final Creator<SizeList> CREATOR = new Creator<SizeList>() { // from class: ps.center.business.bean.aiDraw.SizeList.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SizeList createFromParcel(Parcel in) {
            return new SizeList(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SizeList[] newArray(int size) {
            return new SizeList[size];
        }
    };

    public SizeList() {
    }

    protected SizeList(Parcel in) {
        this.size_id = in.readString();
        this.size = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.size_id);
        dest.writeString(this.size);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}