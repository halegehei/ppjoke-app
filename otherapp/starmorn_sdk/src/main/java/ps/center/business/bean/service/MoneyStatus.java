package ps.center.business.bean.service;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

/* loaded from: classes.jar:ps/center/business/bean/service/MoneyStatus.class */
public class MoneyStatus implements Parcelable {
    public String status;
    public static final Creator<MoneyStatus> CREATOR = new Creator<MoneyStatus>() { // from class: ps.center.business.bean.service.MoneyStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MoneyStatus createFromParcel(Parcel in) {
            return new MoneyStatus(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MoneyStatus[] newArray(int size) {
            return new MoneyStatus[size];
        }
    };

    public MoneyStatus() {
    }

    protected MoneyStatus(Parcel in) {
        this.status = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.status);
    }
}