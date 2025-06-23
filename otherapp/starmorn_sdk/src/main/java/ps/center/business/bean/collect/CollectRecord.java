package ps.center.business.bean.collect;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/collect/CollectRecord.class */
public class CollectRecord implements Parcelable {
    public String id;
    public String module_id;
    public String material_serial;
    public String info;
    public String created_at;
    public static final Creator<CollectRecord> CREATOR = new Creator<CollectRecord>() { // from class: ps.center.business.bean.collect.CollectRecord.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CollectRecord createFromParcel(Parcel in) {
            return new CollectRecord(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CollectRecord[] newArray(int size) {
            return new CollectRecord[size];
        }
    };

    public CollectRecord() {
    }

    protected CollectRecord(Parcel in) {
        this.id = in.readString();
        this.module_id = in.readString();
        this.material_serial = in.readString();
        this.info = in.readString();
        this.created_at = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.module_id);
        dest.writeString(this.material_serial);
        dest.writeString(this.info);
        dest.writeString(this.created_at);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}