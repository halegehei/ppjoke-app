package ps.center.centerinterface.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import ps.center.business.http.base.BusHttp;
import ps.center.centerinterface.api.CenterApis;
import ps.center.centerinterface.bean.CreateApp;
import ps.center.centerinterface.bean.OrderInfoBean;
import ps.center.centerinterface.bean.Orders;
import ps.center.centerinterface.bean.PayPage;
import ps.center.centerinterface.bean.TestPlay;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.bean.UserCheck;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.service.VipCountDownService;
import ps.center.centerinterface.url.CenterUrls;
import ps.center.library.http.base.HttpManager;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;
import ps.center.utils.LogUtils;
import ps.center.utils.Save;
import ps.center.utils.TimeUtils;

/* loaded from: classes.jar:ps/center/centerinterface/http/CenterHttp.class */
public class CenterHttp {
    private static final Object mLook = new Object();
    private static CenterHttp http;

    public static CenterHttp get() {
        CenterHttp centerHttp;
        synchronized (mLook) {
            if (http == null) {
                http = new CenterHttp();
            }
            centerHttp = http;
        }
        return centerHttp;
    }

    private CenterApis getApi() {
        return (CenterApis) HttpManager.getApi(CenterApis.class);
    }

    public void getPayPage(final Result<PayPage> result) {
        getApi().getPayPage(CenterUrls.getPayPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<PayPage>() { // from class: ps.center.centerinterface.http.CenterHttp.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(PayPage obj) {
                if (result != null) {
                    result.success(obj);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                BusHttp.bot().dingBotErr(String.format("支付页数据请求异常：err_code=%s, message=%s", Integer.valueOf(i), s), null);
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void orders(final Result<List<Orders>> result) {
        getApi().orders(CenterUrls.orders).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<Orders>>() { // from class: ps.center.centerinterface.http.CenterHttp.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<Orders> obj) {
                if (result != null) {
                    result.success(obj);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void getOrder(String shop_id, String group_id, final Result<OrderInfoBean> result) {
        getApi().getOrder(CenterUrls.getOrder, shop_id, group_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<OrderInfoBean>() { // from class: ps.center.centerinterface.http.CenterHttp.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(OrderInfoBean user) {
                if (result != null) {
                    result.success(user);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void createApp(String mobile_brand, String mobile_os_version, final Result<CreateApp> result) {
        getApi().create(CenterUrls.createApp, mobile_brand, mobile_os_version).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CreateApp>() { // from class: ps.center.centerinterface.http.CenterHttp.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CreateApp user) {
                Save.instance.put(CenterConstant.UID, Long.valueOf(user.uid));
                if (result != null) {
                    result.success(user);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void getUserInfo(final Result<User> result) {
        getApi().getUserInfo(CenterUrls.getUserInfo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<User>() { // from class: ps.center.centerinterface.http.CenterHttp.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(User user) {
                try {
                    long vipCountDownTime = Save.instance.getLong("vipCountDownTime", -1L);
                    if (user.isVip) {
                        LogUtils.e("会员过期时间:%s, 当前时间:%s", TimeUtils.timeToData(vipCountDownTime), TimeUtils.timeToData(System.currentTimeMillis()));
                        LogUtils.e("开启会员计时：%s", Long.valueOf(vipCountDownTime));
                    } else if (vipCountDownTime == -1) {
                        VipCountDownService.get().close();
                    } else if (vipCountDownTime > System.currentTimeMillis()) {
                        user.isVip = true;
                        user.vip_time = vipCountDownTime / 1000;
                        LogUtils.e("会员过期时间:%s, 当前时间:%s", TimeUtils.timeToData(vipCountDownTime), TimeUtils.timeToData(System.currentTimeMillis()));
                        LogUtils.e("开启会员计时：%s", Long.valueOf(vipCountDownTime));
                        VipCountDownService.get().run(user);
                    } else {
                        VipCountDownService.get().close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CenterConstant.setUser(user);
                if (result != null) {
                    result.success(user);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void logout(final Result<CreateApp> result) {
        getApi().logout(CenterUrls.logout).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CreateApp>() { // from class: ps.center.centerinterface.http.CenterHttp.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CreateApp user) {
                Save.instance.put(CenterConstant.UID, Long.valueOf(user.uid));
                if (result != null) {
                    result.success(user);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void delete(final Result<CreateApp> result) {
        getApi().delete(CenterUrls.delete).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CreateApp>() { // from class: ps.center.centerinterface.http.CenterHttp.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CreateApp user) {
                Save.instance.put(CenterConstant.UID, Long.valueOf(user.uid));
                if (result != null) {
                    result.success(user);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void genCreate(String shop_id, String group_id, String custom_pay, final Result<OrderInfoBean> result) {
        getApi().genCreate(CenterUrls.getOrder, shop_id, group_id, custom_pay).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<OrderInfoBean>() { // from class: ps.center.centerinterface.http.CenterHttp.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(OrderInfoBean data) {
                if (result == null) {
                    return;
                }
                result.success(data);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int code, String message) {
                BusHttp.bot().dingBotErr(String.format("请求订单异常：err_code=%s, message=%s", Integer.valueOf(code), message), null);
                if (result == null) {
                    return;
                }
                result.err(code, message);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result == null) {
                    return;
                }
                result.end();
            }
        });
    }

    public void weChatLogin(String code, final Result<CreateApp> result) {
        getApi().weChatLogin(CenterUrls.weChatLogin, code).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CreateApp>() { // from class: ps.center.centerinterface.http.CenterHttp.9
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CreateApp user) {
                Save.instance.put(CenterConstant.UID, Long.valueOf(user.uid));
                if (result != null) {
                    result.success(user);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void getPhoneCode(String tel, final Result<Object> result) {
        getApi().getPhoneCode(CenterUrls.getSMSCode, tel).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<Object>() { // from class: ps.center.centerinterface.http.CenterHttp.10
            @Override // ps.center.library.http.base.HttpObserver
            protected void success(Object data) {
                if (result == null) {
                    return;
                }
                result.success(data);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int code, String message) {
                if (result == null) {
                    return;
                }
                result.err(code, message);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result == null) {
                    return;
                }
                result.end();
            }
        });
    }

    public void getPhoneCode(final Result<TestPlay> result) {
        getApi().testPlay(CenterUrls.tryVip).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<TestPlay>() { // from class: ps.center.centerinterface.http.CenterHttp.11
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(TestPlay data) {
                if (result == null) {
                    return;
                }
                result.success(data);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int code, String message) {
                if (result == null) {
                    return;
                }
                result.err(code, message);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result == null) {
                    return;
                }
                result.end();
            }
        });
    }

    public void checkUserAsPhone(String tel, final Result<UserCheck> result) {
        getApi().checkUserAsPhone(CenterUrls.checkUserAsPhone, tel).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<UserCheck>() { // from class: ps.center.centerinterface.http.CenterHttp.12
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(UserCheck data) {
                if (result == null) {
                    return;
                }
                result.success(data);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int code, String message) {
                if (result == null) {
                    return;
                }
                result.err(code, message);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result == null) {
                    return;
                }
                result.end();
            }
        });
    }

    public void codeLogin(String code, String tel, final Result<CreateApp> result) {
        getApi().codeLogin(CenterUrls.loginPhone, tel, code).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CreateApp>() { // from class: ps.center.centerinterface.http.CenterHttp.13
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CreateApp data) {
                if (result == null) {
                    return;
                }
                result.success(data);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int code2, String message) {
                if (result == null) {
                    return;
                }
                result.err(code2, message);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result == null) {
                    return;
                }
                result.end();
            }
        });
    }

    public void oneKeyLogin(String token, final Result<CreateApp> result) {
        getApi().oneKeyLogin(CenterUrls.codeLogin, token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<CreateApp>() { // from class: ps.center.centerinterface.http.CenterHttp.14
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(CreateApp data) {
                if (result == null) {
                    return;
                }
                Save.instance.put(CenterConstant.UID, Long.valueOf(data.uid));
                result.success(data);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int code, String message) {
                if (result == null) {
                    return;
                }
                result.err(code, message);
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result == null) {
                    return;
                }
                result.end();
            }
        });
    }
}