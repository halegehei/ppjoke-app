package ps.center.centerinterface.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/centerinterface/bean/Orders.class */
public class Orders implements Parcelable {
    public String out_trade_no;
    public int status;
    public int total_fee;
    public int shop_id;
    public String create_at;
    public String transaction_id;
    public Object payment_time;
    public Object back_time;
    public String notice;
    public String custom;
    public static final Creator<Orders> CREATOR = new Creator<Orders>() { // from class: ps.center.centerinterface.bean.Orders.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    protected Orders(Parcel in) {
        this.out_trade_no = in.readString();
        this.status = in.readInt();
        this.total_fee = in.readInt();
        this.shop_id = in.readInt();
        this.create_at = in.readString();
        this.transaction_id = in.readString();
        this.notice = in.readString();
        this.custom = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.out_trade_no);
        dest.writeInt(this.status);
        dest.writeInt(this.total_fee);
        dest.writeInt(this.shop_id);
        dest.writeString(this.create_at);
        dest.writeString(this.transaction_id);
        dest.writeString(this.notice);
        dest.writeString(this.custom);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}