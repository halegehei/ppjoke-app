package ps.center.business.bean.aiDraw;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/aiDraw/TaskInfo.class */
public class TaskInfo implements Parcelable {
    public String size_id;
    public String describe;
    public String style;
    public String style_id;
    public String image;
    public String reference_url;
    public String is_fortify;
    public String status;
    public String guidance;
    public String is_new_people;
    public String task_id;
    public String created_at;
    public String is_cartoon;
    public static final Creator<TaskInfo> CREATOR = new Creator<TaskInfo>() { // from class: ps.center.business.bean.aiDraw.TaskInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TaskInfo createFromParcel(Parcel in) {
            return new TaskInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TaskInfo[] newArray(int size) {
            return new TaskInfo[size];
        }
    };

    public TaskInfo() {
    }

    protected TaskInfo(Parcel in) {
        this.size_id = in.readString();
        this.describe = in.readString();
        this.style = in.readString();
        this.style_id = in.readString();
        this.image = in.readString();
        this.reference_url = in.readString();
        this.is_fortify = in.readString();
        this.status = in.readString();
        this.guidance = in.readString();
        this.is_new_people = in.readString();
        this.task_id = in.readString();
        this.created_at = in.readString();
        this.is_cartoon = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.size_id);
        dest.writeString(this.describe);
        dest.writeString(this.style);
        dest.writeString(this.style_id);
        dest.writeString(this.image);
        dest.writeString(this.reference_url);
        dest.writeString(this.is_fortify);
        dest.writeString(this.status);
        dest.writeString(this.guidance);
        dest.writeString(this.is_new_people);
        dest.writeString(this.task_id);
        dest.writeString(this.created_at);
        dest.writeString(this.is_cartoon);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}