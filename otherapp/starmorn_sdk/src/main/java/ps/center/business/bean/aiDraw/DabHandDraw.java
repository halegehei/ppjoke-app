package ps.center.business.bean.aiDraw;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/aiDraw/DabHandDraw.class */
public class DabHandDraw implements Parcelable {
    public String task_id;
    public static final Creator<DabHandDraw> CREATOR = new Creator<DabHandDraw>() { // from class: ps.center.business.bean.aiDraw.DabHandDraw.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DabHandDraw createFromParcel(Parcel in) {
            return new DabHandDraw(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DabHandDraw[] newArray(int size) {
            return new DabHandDraw[size];
        }
    };

    public DabHandDraw() {
    }

    protected DabHandDraw(Parcel in) {
        this.task_id = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.task_id);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}