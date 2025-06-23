package ps.center.business.bean.aiDraw;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.jar:ps/center/business/bean/aiDraw/ByMagicAvatarTaskInfo.class */
public class ByMagicAvatarTaskInfo implements Parcelable {
    public String status;
    public List<String> result_info;
    public static final Creator<ByMagicAvatarTaskInfo> CREATOR = new Creator<ByMagicAvatarTaskInfo>() { // from class: ps.center.business.bean.aiDraw.ByMagicAvatarTaskInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByMagicAvatarTaskInfo createFromParcel(Parcel in) {
            return new ByMagicAvatarTaskInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByMagicAvatarTaskInfo[] newArray(int size) {
            return new ByMagicAvatarTaskInfo[size];
        }
    };

    public ByMagicAvatarTaskInfo() {
    }

    protected ByMagicAvatarTaskInfo(Parcel in) {
        this.status = in.readString();
        this.result_info = in.createStringArrayList();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeStringList(this.result_info);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}