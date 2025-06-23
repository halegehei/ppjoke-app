package ps.center.business.bean.share;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.jar:ps/center/business/bean/share/ShareBean.class */
public class ShareBean implements Parcelable {
    public String link;
    public String icon;
    public String title;
    public String content;
    public static final Creator<ShareBean> CREATOR = new Creator<ShareBean>() { // from class: ps.center.business.bean.share.ShareBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareBean createFromParcel(Parcel in) {
            return new ShareBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareBean[] newArray(int size) {
            return new ShareBean[size];
        }
    };

    public ShareBean() {
    }

    protected ShareBean(Parcel in) {
        this.link = in.readString();
        this.icon = in.readString();
        this.title = in.readString();
        this.content = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.link);
        dest.writeString(this.icon);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}