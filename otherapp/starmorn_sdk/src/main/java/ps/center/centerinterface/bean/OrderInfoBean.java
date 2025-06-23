package ps.center.centerinterface.bean;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes.jar:ps/center/centerinterface/bean/OrderInfoBean.class */
public class OrderInfoBean implements Serializable {
    public PrepayIdBean prepay_id;
    public String out_trade_no;
    public String url;

    /* loaded from: classes.jar:ps/center/centerinterface/bean/OrderInfoBean$PrepayIdBean.class */
    public static class PrepayIdBean implements Serializable {
        public Object sub_appid;
        public Object sub_mch_id;
        public String partnerid;
        public String prepayid;

        @SerializedName("package")
        public String packageX;
        public int timestamp;
        public String appid;
        public String noncestr;
        public String sign;
    }
}