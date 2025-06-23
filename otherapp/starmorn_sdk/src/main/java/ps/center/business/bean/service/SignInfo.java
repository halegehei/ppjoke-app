package ps.center.business.bean.service;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/service/SignInfo.class */
public class SignInfo implements Parcelable {
    public String sign;
    public static final Creator<SignInfo> CREATOR = new Creator<SignInfo>() { // from class: ps.center.business.bean.service.SignInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SignInfo createFromParcel(Parcel in) {
            return new SignInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SignInfo[] newArray(int size) {
            return new SignInfo[size];
        }
    };

    public SignInfo() {
    }

    protected SignInfo(Parcel in) {
        this.sign = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sign);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}