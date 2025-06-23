package ps.center.centerinterface.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/centerinterface/bean/TestPlay.class */
public class TestPlay implements Parcelable {
    public long vip_time;
    public int add_time;
    public static final Creator<TestPlay> CREATOR = new Creator<TestPlay>() { // from class: ps.center.centerinterface.bean.TestPlay.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestPlay createFromParcel(Parcel in) {
            return new TestPlay(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestPlay[] newArray(int size) {
            return new TestPlay[size];
        }
    };

    public TestPlay() {
    }

    protected TestPlay(Parcel in) {
        this.vip_time = in.readLong();
        this.add_time = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.vip_time);
        dest.writeInt(this.add_time);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}