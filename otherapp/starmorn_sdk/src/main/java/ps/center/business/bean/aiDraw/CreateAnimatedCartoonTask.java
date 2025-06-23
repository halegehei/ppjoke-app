package ps.center.business.bean.aiDraw;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/aiDraw/CreateAnimatedCartoonTask.class */
public class CreateAnimatedCartoonTask implements Parcelable {
    public String task_id;
    public static final Creator<CreateAnimatedCartoonTask> CREATOR = new Creator<CreateAnimatedCartoonTask>() { // from class: ps.center.business.bean.aiDraw.CreateAnimatedCartoonTask.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreateAnimatedCartoonTask createFromParcel(Parcel in) {
            return new CreateAnimatedCartoonTask(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreateAnimatedCartoonTask[] newArray(int size) {
            return new CreateAnimatedCartoonTask[size];
        }
    };

    public CreateAnimatedCartoonTask() {
    }

    protected CreateAnimatedCartoonTask(Parcel in) {
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