package ps.center.application.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import ps.center.application.BuildConfig;
import ps.center.application.config.ApplicationConfig;
import ps.center.application.login.LoginManager;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.share.ShareBean;
import ps.center.business.http.base.BusHttp;
import ps.center.centerinterface.bean.OrderInfoBean;
import ps.center.centerinterface.bean.PayResult;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.library.http.base.Result;
import ps.center.utils.LogUtils;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.views.DataChanger.DataChangeManager;

/* loaded from: classes.jar:ps/center/application/manager/WeChat.class */
public class WeChat {
    private static WeChat weChat;
    private static IWXAPI api;
    private static Context mContext;
    private static Type type;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: ps.center.application.manager.WeChat.5
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            PayResult result = new PayResult((Map) msg.obj);
            if (result.getResultStatus().equals("9000")) {
                ToastUtils.show(Super.getContext(), "支付完成");
                DataChangeManager.get().change(5, "success");
            } else {
                ToastUtils.show(Super.getContext(), "支付失败");
            }
        }
    };

    /* loaded from: classes.jar:ps/center/application/manager/WeChat$CheckLoginCallBack.class */
    public interface CheckLoginCallBack {
        void ok();

        void no();
    }

    /* loaded from: classes.jar:ps/center/application/manager/WeChat$Type.class */
    public enum Type {
        LOGIN_DEFAULT,
        LOGIN_MINE,
        SHARE,
        CASH
    }

    public static WeChat getInstance() {
        if (weChat == null) {
            weChat = new WeChat();
            initWeiXin(Super.getContext(), BusinessConstant.getConfig().config.wechat.wechat_appid);
        }
        return weChat;
    }

    private static void initWeiXin(Context context, String WeiXinAppId) {
        mContext = context;
        if (TextUtils.isEmpty(WeiXinAppId)) {
            Toast.makeText(context.getApplicationContext(), "微信组件异常", Toast.LENGTH_SHORT).show();
        }
        api = WXAPIFactory.createWXAPI(context, WeiXinAppId, true);
        api.registerApp(WeiXinAppId);
    }

    public IWXAPI getApi() {
        return api;
    }

    public static Type getType() {
        return type;
    }

    public void login(Type types) {
        type = types;
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "transform";
        if (getApi() != null) {
            getApi().sendReq(req);
        } else {
            Toast.makeText(mContext, "微信组件异常 （api = null）", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkLogin(CheckLoginCallBack checkLoginCallBack) {
    }

    public void shareToChat(final ShareBean bean) {
        if (!getInstance().getApi().isWXAppInstalled()) {
            return;
        }
        Glide.with(Super.getContext()).asBitmap().listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                LogUtils.e("分享时加载图片失败了" + e.getMessage());
                return false;
            }
            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                LogUtils.e("分享图片加载成功");
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = bean.link;
                WXMediaMessage msg = new WXMediaMessage(webpage);
                msg.title = bean.title;
                msg.description = bean.content;
                msg.setThumbImage(resource);
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.scene = 0;
                req.message = msg;
                req.transaction = String.valueOf(System.currentTimeMillis());
                WeChat.this.getApi().sendReq(req);
                return false;
            }
        }).load(bean.icon).submit();
    }



    public void shareToPyq(final ShareBean bean) {
        if (!getInstance().getApi().isWXAppInstalled()) {
            return;
        }
        Glide.with(Super.getContext()).asBitmap().listener(new RequestListener<Bitmap>() { // from class: ps.center.application.manager.WeChat.2

            @Override
            public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                LogUtils.e("分享时加载图片失败了" + e.getMessage());
                return false;
            }
            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                LogUtils.e("分享图片加载成功");
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = bean.link;
                WXMediaMessage msg = new WXMediaMessage(webpage);
                msg.title = bean.title;
                msg.description = bean.content;
                msg.setThumbImage(resource);
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.scene = 1;
                req.message = msg;
                req.transaction = String.valueOf(System.currentTimeMillis());
                WeChat.this.getApi().sendReq(req);
                return false;
            }
        }).load(bean.icon).submit();
    }

    public void sendBitmap(Bitmap bitmap, int scene) {
        WXImageObject imgObj = new WXImageObject(bitmap);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        BitmapPool pool = Glide.get(mContext).getBitmapPool();
        Bitmap thumbBmp = TransformationUtils.fitCenter(pool, bitmap, 100, 100);
        msg.setThumbImage(thumbBmp);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = scene;
        api.sendReq(req);
    }

    public void sendVedio(Bitmap bitmap, int scene, String url, String title) {
        WXVideoObject video = new WXVideoObject();
        video.videoUrl = url;
        WXMediaMessage msg = new WXMediaMessage(video);
        msg.title = title;
        msg.description = "视频描述";
        BitmapPool pool = Glide.get(mContext).getBitmapPool();
        Bitmap thumbBmp = TransformationUtils.fitCenter(pool, bitmap, 100, 100);
        msg.setThumbImage(thumbBmp);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("video");
        req.message = msg;
        req.scene = scene;
        api.sendReq(req);
    }

    private String buildTransaction(String type2) {
        return type2 == null ? String.valueOf(System.currentTimeMillis()) : type2 + System.currentTimeMillis();
    }

    public byte[] bmpToByteArray(Bitmap bmp, boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void payNativeNew(final Activity activity, String shop_id, String group_id, final String payType) {
        if (BusinessConstant.getConfig().standard_conf.login_mode.func.forced.equals("1") && !CenterConstant.getUser().isSign) {
            LoginManager.login(activity, 200);
        } else if (ApplicationConfig.getSettingConfig().isCouponsPayChannel == 1) {
            couponsPay(activity, shop_id, group_id, payType);
        } else {
            CenterHttp.get().genCreate(shop_id, group_id, payType, new Result<OrderInfoBean>() { // from class: ps.center.application.manager.WeChat.3
                /* JADX WARN: Type inference failed for: r0v10, types: [ps.center.application.manager.WeChat$3$1] */
                @Override // ps.center.library.http.base.Result
                public void success(final OrderInfoBean obj) {
                    if (payType.equals("0")) {
                        PayReq req = new PayReq();
                        req.appId = obj.prepay_id.appid;
                        req.partnerId = obj.prepay_id.partnerid;
                        req.prepayId = obj.prepay_id.prepayid;
                        req.nonceStr = obj.prepay_id.noncestr;
                        req.timeStamp = obj.prepay_id.timestamp + BuildConfig.VERSION_NAME;
                        req.packageValue = obj.prepay_id.packageX;
                        req.sign = obj.prepay_id.sign;
                        req.extData = "appdata";
                        WeChat.api.sendReq(req);
                        return;
                    }
                    try {
                        try {
                            Class.forName("com.alipay.sdk.app.PayTask");
                            new Thread() { // from class: ps.center.application.manager.WeChat.3.1
                                @Override // java.lang.Thread, java.lang.Runnable
                                public void run() {
                                    String orderInfo = obj.url;
                                    PayTask alipay = new PayTask(activity);
                                    Map<String, String> result = alipay.payV2(orderInfo, true);
                                    Message msg = new Message();
                                    msg.what = 200;
                                    msg.obj = result;
                                    WeChat.this.mHandler.sendMessage(msg);
                                }
                            }.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtils.show(Super.getContext(), "支付宝调起失败, 请使用其他支付方式支付");
                            BusHttp.bot().dingBotErr("未集成支付宝SDK，但配置了支付宝支付。", null);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        ToastUtils.show(Super.getContext(), "支付宝调起失败, 请使用其他支付方式支付");
                    }
                }

                @Override // ps.center.library.http.base.Result
                public void err(int code, String message) {
                    super.err(code, message);
                    ToastUtils.show(Super.getContext(), "支付订单获取失败，请稍后再试");
                    BusHttp.bot().dingBotErr(String.format("获取订单报错, code=%s, message=%s", Integer.valueOf(code), message), null);
                }
            });
        }
    }

    private void couponsPay(final Activity activity, String shop_id, String group_id, final String payType) {
        BusHttp.common().downOrder(shop_id, group_id, payType, new Result<OrderInfoBean>() { // from class: ps.center.application.manager.WeChat.4
            /* JADX WARN: Type inference failed for: r0v10, types: [ps.center.application.manager.WeChat$4$1] */
            @Override // ps.center.library.http.base.Result
            public void success(final OrderInfoBean obj) {
                if (payType.equals("0")) {
                    PayReq req = new PayReq();
                    req.appId = obj.prepay_id.appid;
                    req.partnerId = obj.prepay_id.partnerid;
                    req.prepayId = obj.prepay_id.prepayid;
                    req.nonceStr = obj.prepay_id.noncestr;
                    req.timeStamp = obj.prepay_id.timestamp + BuildConfig.VERSION_NAME;
                    req.packageValue = obj.prepay_id.packageX;
                    req.sign = obj.prepay_id.sign;
                    req.extData = "appdata";
                    WeChat.api.sendReq(req);
                    return;
                }
                try {
                    try {
                        Class.forName("com.alipay.sdk.app.PayTask");
                        new Thread() { // from class: ps.center.application.manager.WeChat.4.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                String orderInfo = obj.url;
                                PayTask alipay = new PayTask(activity);
                                Map<String, String> result = alipay.payV2(orderInfo, true);
                                Message msg = new Message();
                                msg.what = 200;
                                msg.obj = result;
                                WeChat.this.mHandler.sendMessage(msg);
                            }
                        }.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.show(Super.getContext(), "支付宝调起失败, 请使用其他支付方式支付");
                        BusHttp.bot().dingBotErr("未集成支付宝SDK，但配置了支付宝支付。", null);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    ToastUtils.show(Super.getContext(), "支付宝调起失败, 请使用其他支付方式支付");
                }
            }

            @Override // ps.center.library.http.base.Result
            public void err(int code, String message) {
                super.err(code, message);
                ToastUtils.show(Super.getContext(), "支付订单获取失败，请稍后再试");
                BusHttp.bot().dingBotErr(String.format("获取订单报错, code=%s, message=%s", Integer.valueOf(code), message), null);
            }
        });
    }
}