package ps.center.business.bean.aiDraw;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/aiDraw/ByTemplateList.class */
public class ByTemplateList implements Parcelable {
    public String id;
    public String material_serial;
    public String describe;
    public String style;
    public String image;
    public String size_info;
    public String created_at;
    public static final Creator<ByTemplateList> CREATOR = new Creator<ByTemplateList>() { // from class: ps.center.business.bean.aiDraw.ByTemplateList.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByTemplateList createFromParcel(Parcel in) {
            return new ByTemplateList(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByTemplateList[] newArray(int size) {
            return new ByTemplateList[size];
        }
    };

    protected ByTemplateList(Parcel in) {
        this.id = in.readString();
        this.material_serial = in.readString();
        this.describe = in.readString();
        this.style = in.readString();
        this.image = in.readString();
        this.size_info = in.readString();
        this.created_at = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.material_serial);
        dest.writeString(this.describe);
        dest.writeString(this.style);
        dest.writeString(this.image);
        dest.writeString(this.size_info);
        dest.writeString(this.created_at);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}