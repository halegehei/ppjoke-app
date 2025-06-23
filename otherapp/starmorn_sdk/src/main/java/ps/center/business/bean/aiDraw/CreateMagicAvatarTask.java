package ps.center.business.bean.aiDraw;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/aiDraw/CreateMagicAvatarTask.class */
public class CreateMagicAvatarTask implements Parcelable {
    public String job_id;
    public static final Creator<CreateMagicAvatarTask> CREATOR = new Creator<CreateMagicAvatarTask>() { // from class: ps.center.business.bean.aiDraw.CreateMagicAvatarTask.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreateMagicAvatarTask createFromParcel(Parcel in) {
            return new CreateMagicAvatarTask(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreateMagicAvatarTask[] newArray(int size) {
            return new CreateMagicAvatarTask[size];
        }
    };

    public CreateMagicAvatarTask() {
    }

    protected CreateMagicAvatarTask(Parcel in) {
        this.job_id = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.job_id);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}