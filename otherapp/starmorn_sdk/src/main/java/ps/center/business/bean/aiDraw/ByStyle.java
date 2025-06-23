package ps.center.business.bean.aiDraw;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.jar:ps/center/business/bean/aiDraw/ByStyle.class */
public class ByStyle implements Parcelable {
    public List<StyleListBean> style_list;
    public List<SizeListBean> size_list;
    public static final Creator<ByStyle> CREATOR = new Creator<ByStyle>() { // from class: ps.center.business.bean.aiDraw.ByStyle.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByStyle createFromParcel(Parcel in) {
            return new ByStyle(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ByStyle[] newArray(int size) {
            return new ByStyle[size];
        }
    };

    public ByStyle() {
    }

    /* loaded from: classes.jar:ps/center/business/bean/aiDraw/ByStyle$StyleListBean.class */
    public static class StyleListBean implements Parcelable {
        public String id;
        public String kind;
        public String type;
        public String url;
        public String name;
        public boolean select;
        public static final Creator<StyleListBean> CREATOR = new Creator<StyleListBean>() { // from class: ps.center.business.bean.aiDraw.ByStyle.StyleListBean.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public StyleListBean createFromParcel(Parcel in) {
                return new StyleListBean(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public StyleListBean[] newArray(int size) {
                return new StyleListBean[size];
            }
        };

        public StyleListBean() {
        }

        protected StyleListBean(Parcel in) {
            this.id = in.readString();
            this.kind = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.name = in.readString();
            this.select = in.readByte() != 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.kind);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeString(this.name);
            dest.writeByte((byte) (this.select ? 1 : 0));
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }

    /* loaded from: classes.jar:ps/center/business/bean/aiDraw/ByStyle$SizeListBean.class */
    public static class SizeListBean implements Parcelable {
        public String size_id;
        public String size;
        public boolean select;
        public static final Creator<SizeListBean> CREATOR = new Creator<SizeListBean>() { // from class: ps.center.business.bean.aiDraw.ByStyle.SizeListBean.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SizeListBean createFromParcel(Parcel in) {
                return new SizeListBean(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SizeListBean[] newArray(int size) {
                return new SizeListBean[size];
            }
        };

        public SizeListBean() {
        }

        protected SizeListBean(Parcel in) {
            this.size_id = in.readString();
            this.size = in.readString();
            this.select = in.readByte() != 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.size_id);
            dest.writeString(this.size);
            dest.writeByte((byte) (this.select ? 1 : 0));
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }

    protected ByStyle(Parcel in) {
        this.style_list = in.createTypedArrayList(StyleListBean.CREATOR);
        this.size_list = in.createTypedArrayList(SizeListBean.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.style_list);
        dest.writeTypedList(this.size_list);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}