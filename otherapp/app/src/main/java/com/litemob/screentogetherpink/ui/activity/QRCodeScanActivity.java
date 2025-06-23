package com.litemob.screentogetherpink.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.zxing.Result;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.king.camera.scan.AnalyzeResult;
import com.king.camera.scan.CameraScan;
import com.king.camera.scan.analyze.Analyzer;
import com.king.zxing.BarcodeCameraScanActivity;
import com.king.zxing.DecodeConfig;
import com.king.zxing.DecodeFormatManager;
import com.king.zxing.analyze.MultiFormatAnalyzer;
import com.king.zxing.util.CodeUtils;
import com.litemob.screentogetherpink.R;
import com.litemob.screentogetherpink.okhttp.BasePostRequest;
import com.litemob.screentogetherpink.okhttp.Http;
import com.litemob.screentogetherpink.okhttp.Url;
import com.litemob.screentogetherpink.ui.bean.CommonBean;
import com.litemob.screentogetherpink.ui.dialog.LRDialog;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ps.center.views.dialog.BaseDialog;

import static com.bytedance.sdk.openadsdk.TTAppContextHolder.getContext;


public class QRCodeScanActivity extends BarcodeCameraScanActivity {

    private static final String TAG = "MainActivity";
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_IS_QR_CODE = "key_code";

    public static final int REQUEST_CODE_SCAN = 0x01;
    public static final int REQUEST_CODE_PHOTO = 0x02;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 使用findViewById获取控件的引用
        ImageView button = findViewById(R.id.iv_album);
        ImageView btnback = findViewById(R.id.backButton);

        // 使用setOnClickListener设置点击监听器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理点击事件
              //  Toast.makeText(QRCodeScanActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();

                if (XXPermissions.isGranted(QRCodeScanActivity.this,Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_MEDIA_IMAGES)) {


                            getAlbum();

                }else {

                    new LRDialog(QRCodeScanActivity.this, "权限申请", "本功能需要获取您的相册存储权限，用于选择照片进行扫码识别。您如果拒绝上述权限，将无法使用该功能。", "取消", "确定", new BaseDialog.Call() {
                        @Override
                        public void call(Object o) {
                            getPermissions();

                        }
                    }).show();


                }





            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void  getAlbum(){

        Intent pickIntent = new Intent(Intent.ACTION_PICK);
        pickIntent.setType("image/*");
        startActivityForResult(pickIntent, REQUEST_CODE_PHOTO);


    }




    private void getPermissions(){

        XXPermissions.with(QRCodeScanActivity.this)
                .permission(Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_MEDIA_IMAGES )
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {


                            getAlbum();
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            Toast.makeText(QRCodeScanActivity.this, "被永久拒绝授权，请手动授予相册访问权限", Toast.LENGTH_SHORT).show();
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(QRCodeScanActivity.this, permissions);
                        } else {
                            Toast.makeText(QRCodeScanActivity.this, "获取访问相册权限失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void initCameraScan(@NonNull CameraScan<Result> cameraScan) {
        super.initCameraScan(cameraScan);
        // 根据需要设置CameraScan相关配置
        cameraScan.setPlayBeep(true);
    }

    @Nullable
    @Override
    public Analyzer<Result> createAnalyzer() {
        //初始化解码配置
        DecodeConfig decodeConfig = new DecodeConfig();
        decodeConfig.setHints(DecodeFormatManager.QR_CODE_HINTS)//如果只有识别二维码的需求，这样设置效率会更高，不设置默认为DecodeFormatManager.DEFAULT_HINTS
                .setFullAreaScan(false)//设置是否全区域识别，默认false
                .setAreaRectRatio(0.8f)//设置识别区域比例，默认0.8，设置的比例最终会在预览区域裁剪基于此比例的一个矩形进行扫码识别
                .setAreaRectVerticalOffset(0)//设置识别区域垂直方向偏移量，默认为0，为0表示居中，可以为负数
                .setAreaRectHorizontalOffset(0);//设置识别区域水平方向偏移量，默认为0，为0表示居中，可以为负数
        // BarcodeCameraScanActivity默认使用的MultiFormatAnalyzer，这里也可以改为使用QRCodeAnalyzer
        return new MultiFormatAnalyzer(decodeConfig);
    }

    /**
     * 布局ID；通过覆写此方法可以自定义布局
     *
     * @return 布局ID
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_qrcode_scan;
    }

    @Override
    public void onScanResultCallback(@NonNull AnalyzeResult<Result> result) {
        // 停止分析
        getCameraScan().setAnalyzeImage(false);
        // 返回结果
//        Intent intent = new Intent();
//        intent.putExtra(CameraScan.SCAN_RESULT, result.getResult().getText());
//        setResult(Activity.RESULT_OK, intent);

        String str = result.getResult().getText();
        String[] parts = str.split("litemob");
        String part1 = parts[0]; // 17242904859667
        String part2 = parts[1]; // 15072671
        createScreenShareInfo(part1,part2);
       // Toast.makeText(QRCodeScanActivity.this, result.getResult().getText(), Toast.LENGTH_SHORT).show();
        //finish();
    }


    private void createScreenShareInfo(String device_code,String code) {

//        Log.e("888",device_code);
//
//        Log.e("888",code);

        BasePostRequest.Params params = new BasePostRequest.Params();
        params.add("device_code", device_code);
        params.add("code", code);



        BasePostRequest basePostRequest=new BasePostRequest(Url.createScreenShareInfo,params,25);

        Http.get().updateRefreshStatus(QRCodeScanActivity.this, basePostRequest, new ps.center.library.http.base.Result<CommonBean>() {
            @Override
            public void success(CommonBean obj) {
                super.success(obj);



                // Toast.makeText(AddActivity.this, obj.url, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(QRCodeScanActivity.this, PlayActivity.class); // NewActivity是你要打开的Activity的类名
                intent.putExtra("url", obj.url); // 将url作为额外数据放入Intent
                Log.e("666",obj.url);

                startActivity(intent); // 启动新的Activity

                finish();

            }

            @Override
            public void err(int code, String message) {
                super.err(code, message);

                Toast.makeText(QRCodeScanActivity.this, message, Toast.LENGTH_SHORT).show();


            }
        });


    }
    private void asyncThread(Runnable runnable) {
        executor.execute(runnable);
    }
    private void parsePhoto(Intent data) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
            //异步解析
            asyncThread(() -> {
                final String result = CodeUtils.parseCode(bitmap);

                //Toast.makeText(QRCodeScanActivity.this, result, Toast.LENGTH_SHORT).show();
                // 如果只需识别二维码，建议使用：parseQRCode；（因为识别的格式越明确，误识别率越低。）
//                final String result = CodeUtils.parseQRCode(bitmap);
                runOnUiThread(() -> {
                    Log.e("999", "result:" + result);

                    if(result==null){
                        Toast.makeText(QRCodeScanActivity.this, "未识别到有效二维码", Toast.LENGTH_SHORT).show();

                    }else {

                        if(result.contains("litemob")){
                            String[] parts = result.split("litemob");
                            String part1 = parts[0]; // 17242904859667
                            String part2 = parts[1]; // 15072671
                            createScreenShareInfo(part1,part2);
                        }else {

                            Toast.makeText(QRCodeScanActivity.this, "未识别到有效二维码", Toast.LENGTH_SHORT).show();


                        }

                    }



                 //   Toast.makeText(QRCodeScanActivity.this, result, Toast.LENGTH_SHORT).show();
//                    showToast(result);
                });

            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case REQUEST_CODE_SCAN:
                    String result = CameraScan.parseScanResult(data);
                    Toast.makeText(QRCodeScanActivity.this, result, Toast.LENGTH_SHORT).show();
                    break;
                case REQUEST_CODE_PHOTO:
                   parsePhoto(data);
                    break;
            }

        }
    }



}
