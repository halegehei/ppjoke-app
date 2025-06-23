package ps.center.centerinterface.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.jar:ps/center/centerinterface/bean/PayPage.class */
public class PayPage implements Parcelable {
    public List<GroupsBean> groups;
    public boolean is_channel;
    public String show_rule;
    public List<List<String>> show_rules;
    public String p_url;
    public static final Creator<PayPage> CREATOR = new Creator<PayPage>() { // from class: ps.center.centerinterface.bean.PayPage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PayPage createFromParcel(Parcel in) {
            return new PayPage(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PayPage[] newArray(int size) {
            return new PayPage[size];
        }
    };

    public PayPage() {
    }

    /* loaded from: classes.jar:ps/center/centerinterface/bean/PayPage$GroupsBean.class */
    public static class GroupsBean implements Parcelable {
        public List<PriceInfoBean> price_info;
        public String name;
        public String template_type;
        public String pay_type;
        public int is_double_pay;
        public int id;
        public int change_time;
        public String time;
        public static final Creator<GroupsBean> CREATOR = new Creator<GroupsBean>() { // from class: ps.center.centerinterface.bean.PayPage.GroupsBean.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GroupsBean createFromParcel(Parcel in) {
                return new GroupsBean(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GroupsBean[] newArray(int size) {
                return new GroupsBean[size];
            }
        };

        public GroupsBean() {
        }

        /* loaded from: classes.jar:ps/center/centerinterface/bean/PayPage$GroupsBean$PriceInfoBean.class */
        public static class PriceInfoBean implements Parcelable {
            public int id;
            public String renew_type;
            public String shop_id;
            public String shop_name;
            public String renew_price;
            public String price;
            public String before_day;
            public String type_name;
            public String top_label;
            public String bottom_label;
            public String free_day;
            public String button;
            public String dingyueshuoming;
            public String product_id;
            public String is_top;
            public String sort;
            public String spare_label;
            public String spare_label2;
            public String low_price;
            public String pay_time;
            public static final Creator<PriceInfoBean> CREATOR = new Creator<PriceInfoBean>() { // from class: ps.center.centerinterface.bean.PayPage.GroupsBean.PriceInfoBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PriceInfoBean createFromParcel(Parcel in) {
                    return new PriceInfoBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PriceInfoBean[] newArray(int size) {
                    return new PriceInfoBean[size];
                }
            };

            public PriceInfoBean() {
            }

            protected PriceInfoBean(Parcel in) {
                this.id = in.readInt();
                this.renew_type = in.readString();
                this.shop_id = in.readString();
                this.shop_name = in.readString();
                this.renew_price = in.readString();
                this.price = in.readString();
                this.before_day = in.readString();
                this.type_name = in.readString();
                this.top_label = in.readString();
                this.bottom_label = in.readString();
                this.free_day = in.readString();
                this.button = in.readString();
                this.dingyueshuoming = in.readString();
                this.product_id = in.readString();
                this.is_top = in.readString();
                this.sort = in.readString();
                this.spare_label = in.readString();
                this.spare_label2 = in.readString();
                this.low_price = in.readString();
                this.pay_time = in.readString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.renew_type);
                dest.writeString(this.shop_id);
                dest.writeString(this.shop_name);
                dest.writeString(this.renew_price);
                dest.writeString(this.price);
                dest.writeString(this.before_day);
                dest.writeString(this.type_name);
                dest.writeString(this.top_label);
                dest.writeString(this.bottom_label);
                dest.writeString(this.free_day);
                dest.writeString(this.button);
                dest.writeString(this.dingyueshuoming);
                dest.writeString(this.product_id);
                dest.writeString(this.is_top);
                dest.writeString(this.sort);
                dest.writeString(this.spare_label);
                dest.writeString(this.spare_label2);
                dest.writeString(this.low_price);
                dest.writeString(this.pay_time);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        protected GroupsBean(Parcel in) {
            this.price_info = in.createTypedArrayList(PriceInfoBean.CREATOR);
            this.name = in.readString();
            this.template_type = in.readString();
            this.pay_type = in.readString();
            this.is_double_pay = in.readInt();
            this.id = in.readInt();
            this.change_time = in.readInt();
            this.time = in.readString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(this.price_info);
            dest.writeString(this.name);
            dest.writeString(this.template_type);
            dest.writeString(this.pay_type);
            dest.writeInt(this.is_double_pay);
            dest.writeInt(this.id);
            dest.writeInt(this.change_time);
            dest.writeString(this.time);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }

    protected PayPage(Parcel in) {
        this.groups = in.createTypedArrayList(GroupsBean.CREATOR);
        this.is_channel = in.readByte() != 0;
        this.show_rule = in.readString();
        this.p_url = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.groups);
        dest.writeByte((byte) (this.is_channel ? 1 : 0));
        dest.writeString(this.show_rule);
        dest.writeString(this.p_url);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}