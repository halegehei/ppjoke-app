package ps.center.business.bean.faceChange;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

/* loaded from: classes.jar:ps/center/business/bean/faceChange/CreateImageFusionInfo.class */
public class CreateImageFusionInfo implements Parcelable {
    public String task_id;
    public static final Creator<CreateImageFusionInfo> CREATOR = new Creator<CreateImageFusionInfo>() { // from class: ps.center.business.bean.faceChange.CreateImageFusionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreateImageFusionInfo createFromParcel(Parcel in) {
            return new CreateImageFusionInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreateImageFusionInfo[] newArray(int size) {
            return new CreateImageFusionInfo[size];
        }
    };

    public CreateImageFusionInfo() {
    }

    protected CreateImageFusionInfo(Parcel in) {
        this.task_id = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.task_id);
    }
}