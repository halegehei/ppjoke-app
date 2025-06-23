package ps.center.application.pay;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import ps.center.centerinterface.bean.PayPage;

/* loaded from: classes.jar:ps/center/application/pay/JumpPayBeans.class */
public class JumpPayBeans implements Parcelable {
    private PayPage.GroupsBean payInfo;
    private PayPage.GroupsBean dialogPayInfo;
    private ArrayList<String> showRules;
    private ArrayList<String> dialogShowRules;
    private PayPage.GroupsBean payInfoZfb;
    private PayPage.GroupsBean dialogPayInfoZfb;
    private ArrayList<String> showRulesZfb;
    private ArrayList<String> dialogShowRulesZfb;
    public static final Creator<JumpPayBeans> CREATOR = new Creator<JumpPayBeans>() { // from class: ps.center.application.pay.JumpPayBeans.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JumpPayBeans createFromParcel(Parcel in) {
            return new JumpPayBeans(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JumpPayBeans[] newArray(int size) {
            return new JumpPayBeans[size];
        }
    };

    public JumpPayBeans(PayPage.GroupsBean payInfo, PayPage.GroupsBean dialogPayInfo, ArrayList<String> showRules, ArrayList<String> dialogShowRules, PayPage.GroupsBean payInfoZfb, PayPage.GroupsBean dialogPayInfoZfb, ArrayList<String> showRulesZfb, ArrayList<String> dialogShowRulesZfb) {
        this.payInfo = payInfo;
        this.dialogPayInfo = dialogPayInfo;
        this.showRules = showRules;
        this.dialogShowRules = dialogShowRules;
        this.payInfoZfb = payInfoZfb;
        this.dialogPayInfoZfb = dialogPayInfoZfb;
        this.showRulesZfb = showRulesZfb;
        this.dialogShowRulesZfb = dialogShowRulesZfb;
    }

    protected JumpPayBeans(Parcel in) {
        this.payInfo = (PayPage.GroupsBean) in.readParcelable(PayPage.GroupsBean.class.getClassLoader());
        this.dialogPayInfo = (PayPage.GroupsBean) in.readParcelable(PayPage.GroupsBean.class.getClassLoader());
        this.showRules = in.createStringArrayList();
        this.dialogShowRules = in.createStringArrayList();
        this.payInfoZfb = (PayPage.GroupsBean) in.readParcelable(PayPage.GroupsBean.class.getClassLoader());
        this.dialogPayInfoZfb = (PayPage.GroupsBean) in.readParcelable(PayPage.GroupsBean.class.getClassLoader());
        this.showRulesZfb = in.createStringArrayList();
        this.dialogShowRulesZfb = in.createStringArrayList();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.payInfo, flags);
        dest.writeParcelable(this.dialogPayInfo, flags);
        dest.writeStringList(this.showRules);
        dest.writeStringList(this.dialogShowRules);
        dest.writeParcelable(this.payInfoZfb, flags);
        dest.writeParcelable(this.dialogPayInfoZfb, flags);
        dest.writeStringList(this.showRulesZfb);
        dest.writeStringList(this.dialogShowRulesZfb);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}