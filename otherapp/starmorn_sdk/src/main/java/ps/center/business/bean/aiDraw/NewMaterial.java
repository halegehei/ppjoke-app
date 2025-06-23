package ps.center.business.bean.aiDraw;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/aiDraw/NewMaterial.class */
public class NewMaterial implements Parcelable {
    public String id;
    public String material_name;
    public String material_url;
    public String material_serial;
    public String describe;
    public String style;
    public String size_info;
    public String style_id;
    public String head_url;
    public String nick_name;
    public String created_at;
    public static final Creator<NewMaterial> CREATOR = new Creator<NewMaterial>() { // from class: ps.center.business.bean.aiDraw.NewMaterial.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewMaterial createFromParcel(Parcel in) {
            return new NewMaterial(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewMaterial[] newArray(int size) {
            return new NewMaterial[size];
        }
    };

    public NewMaterial() {
    }

    protected NewMaterial(Parcel in) {
        this.id = in.readString();
        this.material_name = in.readString();
        this.material_url = in.readString();
        this.material_serial = in.readString();
        this.describe = in.readString();
        this.style = in.readString();
        this.size_info = in.readString();
        this.style_id = in.readString();
        this.head_url = in.readString();
        this.nick_name = in.readString();
        this.created_at = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.material_name);
        dest.writeString(this.material_url);
        dest.writeString(this.material_serial);
        dest.writeString(this.describe);
        dest.writeString(this.style);
        dest.writeString(this.size_info);
        dest.writeString(this.style_id);
        dest.writeString(this.head_url);
        dest.writeString(this.nick_name);
        dest.writeString(this.created_at);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}