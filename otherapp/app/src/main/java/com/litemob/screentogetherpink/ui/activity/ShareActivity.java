package com.litemob.screentogetherpink.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import androidx.annotation.NonNull;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.king.zxing.util.CodeUtils;
import com.litemob.screentogetherpink.BaseConstant;
import com.litemob.screentogetherpink.R;
import com.litemob.screentogetherpink.databinding.ActivityShareBinding;
import com.litemob.screentogetherpink.okhttp.BaseGetRequest;
import com.litemob.screentogetherpink.okhttp.BasePostRequest;

import com.litemob.screentogetherpink.okhttp.Http;
import com.litemob.screentogetherpink.okhttp.Url;
import com.litemob.screentogetherpink.ui.bean.CommonBean;
import com.litemob.screentogetherpink.ui.bean.DeviceInfoBean;
import com.litemob.screentogetherpink.ui.dialog.LRDialog;
import com.litemob.screentogetherpink.ui.dialog.LRImageDialog;
import com.litemob.screentogetherpink.ui.dialog.RDialog;
import com.litemob.screentogetherpink.ui.view.MediaService;
import com.litemob.screentogetherpink.ui.view.OnSingleClickListener;
import com.tencent.live2.V2TXLiveDef;
import com.tencent.live2.V2TXLivePusher;
import com.tencent.live2.impl.V2TXLivePusherImpl;

import java.lang.ref.WeakReference;
import java.util.List;

import ps.center.application.manager.PayManager;
import ps.center.application.manager.WeChat;
import ps.center.business.bean.service.MoneyInfo;
import ps.center.business.bean.service.MoneyStatus;
import ps.center.business.bean.service.SignInfo;
import ps.center.business.http.base.BusHttp;
import ps.center.business.utils.free.FreeManager;
import ps.center.library.http.base.Result;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.activity.IntentGet;
import ps.center.views.dialog.BaseDialog;
import static com.litemob.screentogetherpink.BaseConstant.handler;

import static com.bytedance.sdk.openadsdk.TTAppContextHolder.getContext;
import static com.tencent.live2.V2TXLiveCode.V2TXLIVE_ERROR_INVALID_LICENSE;

public class ShareActivity extends MyBaseActivity<ActivityShareBinding>{


    private boolean isPasswordVisible = false;
    private static final int REQUEST_MEDIA_PROJECTION = 1;
    private V2TXLivePusher mLivePusher;


     private boolean isPlaying;


     private boolean ismicon=true;





    private boolean isOpen;

    @Override
    protected ActivityShareBinding getLayout() {
        return ActivityShareBinding.inflate(this.getLayoutInflater());
    }

    @Override
    protected void initData(IntentGet intentGet) {

        mLivePusher = new V2TXLivePusherImpl(ShareActivity.this, V2TXLiveDef.V2TXLiveMode.TXLiveMode_RTC);

        binding.backButton.setOnClickListener(v -> {



            if(isPlaying){


                new LRDialog(ShareActivity.this,"是否结束投屏","结束投屏后，将于对方设备断开连接","取消","确定", new BaseDialog.Call() {
                    @Override
                    public void call(Object o) {
                        if("2".equals(o.toString())){

                            stopPublish();
                            finish();
                        }

                    }
                }).show();


            }else {


                finish();


            }




        });

        binding.tvClose.setOnClickListener(view -> {

            new LRDialog(ShareActivity.this,"是否结束投屏","结束投屏后，将于对方设备断开连接","取消","确定", new BaseDialog.Call() {
                @Override
                public void call(Object o) {
                    if("2".equals(o.toString())){

                        stopPublish();
                        finish();
                    }


                }
            }).show();


        });


        binding.ivQrcode.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                getByDeviceInfoQrcode();
            }
        });

        binding.ivQuery.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                new RDialog(ShareActivity.this,"温馨提示","验证码刷新后，原连接设备将失效，需重新进行设备连接","我已经知晓", new BaseDialog.Call() {
                    @Override
                    public void call(Object o) {

                    }
                }).show();
            }
        });


//        binding.etDeviceCode.setOnClickListener(v -> {
//
//        });



        binding.ivCopy.setOnClickListener(v -> {
            // 获取EditText中的文本
            String textToCopy = binding.etDeviceCode.getText().toString();

            // 获取剪贴板管理器
            ClipboardManager clipboard = (ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);

            // 创建一个ClipData对象并将文本复制到剪贴板
            ClipData clip = ClipData.newPlainText("verificationCode", textToCopy);
            clipboard.setPrimaryClip(clip);

            // 可选：提示用户内容已复制
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();
        });



//        binding.etVerificationCode.setOnClickListener(v -> {
//
//        });

        binding.ivView.setOnClickListener(v -> {

            if (isPasswordVisible) {
                // 隐藏密码
                binding.etVerificationCode.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                binding.ivView.setImageResource(R.mipmap.ic_share_eye_close); // 设置为闭眼的图标
            } else {
                // 显示密码
                binding.etVerificationCode.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                binding.ivView.setImageResource(R.mipmap.ic_share_eye); // 设置为睁眼的图标
            }
            isPasswordVisible = !isPasswordVisible;

            binding.etVerificationCode.setSelection(binding.etVerificationCode.getText().length());

        });

        binding.neverRefresh.setOnClickListener(v -> {
            // 设置选中状态

            updateRefreshStatus("3");
        });

        binding.refreshOnEnd.setOnClickListener(v -> {
            // 设置选中状态
            updateRefreshStatus("1");

        });

        binding.refreshDaily.setOnClickListener(v -> {
            // 设置选中状态
            updateRefreshStatus("2");

        });

        binding.connectButton.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {



                FreeManager.get().us(ShareActivity.this, new FreeManager.UsFreeListener<Object>() {
                    @Override
                    public void success(Object sssss) {

                        if (XXPermissions.isGranted(ShareActivity.this, Permission.SYSTEM_ALERT_WINDOW)) {

                            MediaProjectionManager projectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
                            Intent intent = projectionManager.createScreenCaptureIntent();
                            startActivityForResult(intent, REQUEST_MEDIA_PROJECTION);



                        }else {

                            new LRDialog(ShareActivity.this, "权限申请", "该功能需开启您的悬浮窗权限，用于投屏功能稳定运行，避免进程异常。", "取消", "确定", new BaseDialog.Call() {
                                @Override
                                public void call(Object o) {

                                    if("2".equals(o.toString())){
                                        getFloatWindowPermission();

                                    }

                                }
                            }).show();

                        }



                    }

                    @Override
                    public void notFree() {
                        new PayManager(ShareActivity.this, "后置付费", 0).go();
                    }
                });




            }
        });


        binding.microphoneIcon.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                ismicon=!ismicon;

                if(ismicon){
                    binding.microphoneIcon.setImageResource(R.mipmap.icon_mic_on);
                    mLivePusher.startMicrophone();

                }else {
                    binding.microphoneIcon.setImageResource(R.mipmap.icon_mic_close);
                    mLivePusher.stopMicrophone();
                }

            }
        });







        getByDeviceInfo();
    }



    @Override
    public void onBackPressed() {

        if(isPlaying){


            new LRDialog(ShareActivity.this,"是否结束投屏","结束投屏后，将于对方设备断开连接","取消","确定", new BaseDialog.Call() {
                @Override
                public void call(Object o) {
                    if("2".equals(o.toString())){

                        stopPublish();
                        finish();
                    }

                }
            }).show();


        }else {


            finish();
            super.onBackPressed();


        }



    }

    @NonNull
    @Override
    public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
        return super.getOnBackInvokedDispatcher();
    }

    private void getFloatWindowPermission(){
        XXPermissions.with(ShareActivity.this)
                .permission(Permission.SYSTEM_ALERT_WINDOW)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            Toast.makeText(ShareActivity.this, "获取悬浮窗权限成功", Toast.LENGTH_SHORT).show();
                            // 创建屏幕捕获的意图
                            MediaProjectionManager projectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
                            Intent intent = projectionManager.createScreenCaptureIntent();
                            startActivityForResult(intent, REQUEST_MEDIA_PROJECTION);
                        } else {
                            // Toast.makeText(ShareActivity.this, "获取部分权限成功，但部分权限未正常授予", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            Toast.makeText(ShareActivity.this, "被永久拒绝授权，请手动授予悬浮窗权限", Toast.LENGTH_SHORT).show();
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(ShareActivity.this, permissions);
                        } else {
                            Toast.makeText(ShareActivity.this, "获取悬浮窗权限失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1) {
            // 用户同意屏幕捕捉，你可以在这里获取MediaProjection对象并开始捕捉屏幕
           // DataChangeManager.get().change(BaseConstant.SCREEN_PERMISSION, true);
            //getPermissions();


            if (XXPermissions.isGranted(ShareActivity.this, Permission.READ_MEDIA_VIDEO, Permission.READ_MEDIA_AUDIO, Permission.WRITE_EXTERNAL_STORAGE, Permission.CAMERA, Permission.RECORD_AUDIO)) {


                   startGo();


            }else {

                new LRDialog(ShareActivity.this, "权限申请", "该功能需开启手机屏幕录制权限、存储和麦克风权限，用于投屏过程中语音及系统声音正常播放。", "取消", "确定", new BaseDialog.Call() {
                    @Override
                    public void call(Object o) {

                        if("2".equals(o.toString())){
                            // getLiveStreamingPermissions();

                            getLiveStreamingPermissions();

                        }

                    }
                }).show();



            }




        } else {


            // 用户拒绝了屏幕捕捉
           // DataChangeManager.get().change(BaseConstant.SCREEN_PERMISSION, false);
        }
    }



    private void getLiveStreamingPermissions(){

        XXPermissions.with(ShareActivity.this)
                .permission(Permission.READ_MEDIA_VIDEO, Permission.READ_MEDIA_AUDIO, Permission.WRITE_EXTERNAL_STORAGE, Permission.CAMERA, Permission.RECORD_AUDIO)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            Toast.makeText(ShareActivity.this, "获取直播权限成功", Toast.LENGTH_SHORT).show();
                            // 在这里开始直播

                            startGo();
                        } else {
                            Toast.makeText(ShareActivity.this, "获取部分权限成功，但部分权限未正常授予", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            Toast.makeText(ShareActivity.this, "被永久拒绝授权，请手动授予直播权限", Toast.LENGTH_SHORT).show();
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(ShareActivity.this, permissions);
                        } else {
                            Toast.makeText(ShareActivity.this, "获取直播权限失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


    private void getPermissions(){


        if (XXPermissions.isGranted(ShareActivity.this, Permission.READ_MEDIA_VIDEO, Permission.READ_MEDIA_AUDIO, Permission.WRITE_EXTERNAL_STORAGE, Permission.CAMERA, Permission.RECORD_AUDIO)) {
            XXPermissions.with(ShareActivity.this).permission(Permission.READ_MEDIA_VIDEO, Permission.READ_MEDIA_AUDIO, Permission.WRITE_EXTERNAL_STORAGE, Permission.CAMERA, Permission.RECORD_AUDIO).request(new OnPermissionCallback() {
                @Override
                public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                    startGo();

                    Toast.makeText(ShareActivity.this, "同意权限", Toast.LENGTH_SHORT).show();

                }
            });
        } else {

            new LRDialog(ShareActivity.this, "权限申请", "为了保证您的录屏、语音及系统声音正常播放，请为本应用开启屏幕录制，存储、麦克风权限用于获取您的声音与系统声音。", "取消", "确定", new BaseDialog.Call() {
                @Override
                public void call(Object o) {
                    XXPermissions.with(ShareActivity.this).permission(Permission.READ_MEDIA_VIDEO, Permission.READ_MEDIA_AUDIO, Permission.WRITE_EXTERNAL_STORAGE, Permission.CAMERA, Permission.RECORD_AUDIO).request(new OnPermissionCallback() {
                        @Override
                        public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                            startGo();
                        }
                    });
                }
            }).show();
        }
    }





    private void setVerificationCodeType(int type) {
        switch (type) {
            case 1:

                // 设置 "投屏结束刷新" 选中状态
                binding.refreshOnEnd.setBackgroundResource(R.drawable.rounded_button_background_select);
                binding.refreshOnEnd.setTextColor(Color.parseColor("#FF7680"));
                // 重置其他 TextView 为未选中状态
                binding.neverRefresh.setBackgroundResource(R.drawable.rounded_button_background);
                binding.neverRefresh.setTextColor(Color.parseColor("#000000"));
                binding.refreshDaily.setBackgroundResource(R.drawable.rounded_button_background);
                binding.refreshDaily.setTextColor(Color.parseColor("#000000"));
                break;

            case 2:

                // 设置 "每天刷新" 选中状态
                binding.refreshDaily.setBackgroundResource(R.drawable.rounded_button_background_select);
                binding.refreshDaily.setTextColor(Color.parseColor("#FF7680"));
                // 重置其他 TextView 为未选中状态
                binding.neverRefresh.setBackgroundResource(R.drawable.rounded_button_background);
                binding.neverRefresh.setTextColor(Color.parseColor("#000000"));
                binding.refreshOnEnd.setBackgroundResource(R.drawable.rounded_button_background);
                binding.refreshOnEnd.setTextColor(Color.parseColor("#000000"));
                break;

            case 3:

                // 设置 "从不刷新" 选中状态
                binding.neverRefresh.setBackgroundResource(R.drawable.rounded_button_background_select);
                binding.neverRefresh.setTextColor(Color.parseColor("#FF7680"));
                // 重置其他 TextView 为未选中状态
                binding.refreshOnEnd.setBackgroundResource(R.drawable.rounded_button_background);
                binding.refreshOnEnd.setTextColor(Color.parseColor("#000000"));
                binding.refreshDaily.setBackgroundResource(R.drawable.rounded_button_background);
                binding.refreshDaily.setTextColor(Color.parseColor("#000000"));
                break;
        }
    }

    private void getByDeviceInfo() {





        BaseGetRequest baseRequest = new BaseGetRequest(Url.byDeviceInfo, 25);
        Http.get().byDeviceInfo(ShareActivity.this, baseRequest, new Result<DeviceInfoBean>() {
            @Override
            public void success(DeviceInfoBean obj) {
                super.success(obj);
                binding.etDeviceCode.setText(obj.device_code);
                binding.etVerificationCode.setText(obj.code);
                Bitmap bitmap = CodeUtils.createQRCode(obj.device_code + BaseConstant.mark + obj.code, 600, Color.parseColor("#333333"));
                // 根据 code_type 赋值
                if ("1".equals(obj.code_type)) {
                    setVerificationCodeType(1);
                } else if ("2".equals(obj.code_type)) {
                    setVerificationCodeType(2);
                } else if ("3".equals(obj.code_type)) {
                    setVerificationCodeType(3);
                }

            }
        });


    }


    private void getByDeviceInfoQrcode() {





        BaseGetRequest baseRequest = new BaseGetRequest(Url.byDeviceInfo, 25);
        Http.get().byDeviceInfo(ShareActivity.this, baseRequest, new Result<DeviceInfoBean>() {
            @Override
            public void success(DeviceInfoBean obj) {
                super.success(obj);
                binding.etDeviceCode.setText(obj.device_code);
                binding.etVerificationCode.setText(obj.code);
                Bitmap bitmap = CodeUtils.createQRCode(obj.device_code + BaseConstant.mark + obj.code, 600, Color.parseColor("#333333"));
                // 根据 code_type 赋值

// 创建并显示对话框
                LRImageDialog dialog = new LRImageDialog(ShareActivity.this, "我的二维码", "", "取消", "分享", bitmap, new BaseDialog.Call() {
                    @Override
                    public void call(Object o) {
                        if ("2".equals(o)) {
                            // 在这里添加你的分享代码

                            WeChat.getInstance().sendBitmap(bitmap,0);

                        }else {


                        }
                    }
                });
                dialog.show();

            }
        });


    }



    private void updateRefreshStatus(String type) {


        BasePostRequest.Params params = new BasePostRequest.Params();
        params.add("type", type);



        BasePostRequest basePostRequest=new BasePostRequest("/api/screen/updateRefreshStatus",params,25);

        Http.get().updateRefreshStatus(ShareActivity.this, basePostRequest, new Result<CommonBean>() {
            @Override
            public void success(CommonBean obj) {
                super.success(obj);





            }

            @Override
            public void err(int code, String message) {
                super.err(code, message);
                if(code==200){
                    setVerificationCodeType(Integer.parseInt(type));
                    Toast.makeText(ShareActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ShareActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void startGo() {

        new RDialog(ShareActivity.this, "温馨提示", "已开始共享，其它设备输入您的设备码及验证码后，可观看您的屏幕", "我知道了", new BaseDialog.Call() {
            @Override
            public void call(Object o) {
                binding.l1.setVisibility(View.GONE);
                binding.l2.setVisibility(View.GONE);
                binding.l3.setVisibility(View.VISIBLE);


                BusHttp.service().getSignInfo(new Result<SignInfo>() {
                    @Override
                    public void success(SignInfo obj) {
                        BasePostRequest.Params params = new BasePostRequest.Params();
                        params.add("sign", obj.sign);
                        BasePostRequest basePostRequest = new BasePostRequest(Url.pushStreamTask, params, 25);
                        Http.get().pushStreamTask(ShareActivity.this, basePostRequest, new Result<CommonBean>() {
                            @Override
                            public void success(CommonBean bean) {
                                ShareActivity.this.startService(new Intent(ShareActivity.this, MediaService.class));
                                isPlaying=true;
                                mLivePusher.startMicrophone();

                                mLivePusher.startScreenCapture();
                                int ret = mLivePusher.startPush(bean.url);
                                Log.e("push","ss"+ret);
                                if (ret == V2TXLIVE_ERROR_INVALID_LICENSE) {
                                    Log.e("deletelog", "startRTMPPush: license 校验失败");
                                }

                                handler = new Handler();
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.postDelayed(this, 60 * 1000); // 每分钟
                                        upMoney();
                                    }
                                };

                                handler.postDelayed(runnable, 60 * 1000); // 每分钟


                            }
                        });

                    }
                });
            }
        }).show();
    }



    private void upMoney() {
        BusHttp.service().getMoneyStatus(new Result<MoneyStatus>() {
            @Override
            public void success(MoneyStatus obj) {
//1 余额可用 -1余额不可用 （已达上限）
                if (obj.status.equals("-1")) {
                    binding.l1.setVisibility(View.VISIBLE);
                    binding.l2.setVisibility(View.VISIBLE);
                    binding.l3.setVisibility(View.GONE);
                    stopPublish();
                    isOpen = true;
                    binding.microphoneIcon.setImageResource(R.mipmap.ic_share_connecting_mic);
                    DataChangeManager.get().change(BaseConstant.FRESH, "");
                } else {
                    BusHttp.service().addMoneyInfo("0.2", new Result<MoneyInfo>() {
                        @Override
                        public void success(MoneyInfo obj) {
                        }

                        @Override
                        public void end() {

                        }
                    });
                }
            }
        });
    }


    // 结束录屏直播，注意做好清理工作
    public void stopPublish() {
        if (mLivePusher != null) {
            mLivePusher.stopScreenCapture();

            mLivePusher.setObserver(null);
            mLivePusher.stopPush();

            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            Intent intent = new Intent(ShareActivity.this, MediaService.class);
            ShareActivity.this.stopService(intent);

        }
    }


    private static class MyHandler extends Handler {
        private final WeakReference<ShareActivity> mActivity;

        MyHandler(ShareActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ShareActivity activity = mActivity.get();
            if (activity != null) {
                // 执行任务
                activity.upMoney();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopPublish();
    }
}
