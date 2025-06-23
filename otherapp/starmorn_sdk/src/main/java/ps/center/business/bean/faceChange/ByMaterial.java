package ps.center.business.bean.faceChange;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/faceChange/ByMaterial.class */
public class ByMaterial implements Parcelable {
    public String id;
    public String active_id;
    public String material_id;
    public String material_name;
    public String material_url;
    public static final Creator<ByMaterial> CREATOR = new Creator<ByMaterial>() { // from class: ps.center.business.bean.faceChange.ByMaterial.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByMaterial createFromParcel(Parcel in) {
            return new ByMaterial(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByMaterial[] newArray(int size) {
            return new ByMaterial[size];
        }
    };

    public ByMaterial() {
    }

    protected ByMaterial(Parcel in) {
        this.id = in.readString();
        this.active_id = in.readString();
        this.material_id = in.readString();
        this.material_name = in.readString();
        this.material_url = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.active_id);
        dest.writeString(this.material_id);
        dest.writeString(this.material_name);
        dest.writeString(this.material_url);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}