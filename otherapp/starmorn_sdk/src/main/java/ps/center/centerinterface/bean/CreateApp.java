package ps.center.centerinterface.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/centerinterface/bean/CreateApp.class */
public class CreateApp implements Parcelable {
    public long uid;
    public static final Creator<CreateApp> CREATOR = new Creator<CreateApp>() { // from class: ps.center.centerinterface.bean.CreateApp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreateApp createFromParcel(Parcel in) {
            return new CreateApp(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreateApp[] newArray(int size) {
            return new CreateApp[size];
        }
    };

    public CreateApp() {
    }

    protected CreateApp(Parcel in) {
        this.uid = in.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.uid);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}