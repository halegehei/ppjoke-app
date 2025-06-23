package ps.center.application.manager.payInfo;

import java.util.ArrayList;
import ps.center.centerinterface.bean.PayPage;

/* loaded from: classes.jar:ps/center/application/manager/payInfo/PayItemCallback.class */
public interface PayItemCallback {
    void result(PayPage.GroupsBean groupsBean, ArrayList<String> arrayList);
}