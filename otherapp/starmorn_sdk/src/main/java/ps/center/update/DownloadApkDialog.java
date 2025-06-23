package ps.center.update;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import ps.center.application.BuildConfig;
import ps.center.R;

/* loaded from: classes.jar:ps/center/update/DownloadApkDialog.class */
public class DownloadApkDialog extends BaseDialog {
    private static int progress;
    private static ProgressBar mProgress;
    private static final int DOWNLOADING = 1;
    private static final int DOWNLOADED = 2;
    private static final int DOWNLOAD_FAILED = 3;
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_btn;
    private TextView tv_cancle;
    private TextView tv_know_btn;
    private TextView tv_progress;
    private ImageView iv_bg;
    private boolean isMust;
    private LinearLayout ll_before;
    private LinearLayout ll_after;
    private LinearLayout ll_finish;
    private Context context;
    private String url;
    private String title;
    private String content;
    private TextView tv_cancle_a;
    public int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    public Handler mHandler;
    private static final String savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/downloadApk/";
    private static final String saveFileName = savePath + System.currentTimeMillis() + "update.apk";
    private static boolean cancelFlag = false;

    public DownloadApkDialog(Context context, boolean isMust, String url, String title, String content) {
        super(context, R.style.dialogBlackBack, 0, true);
        this.MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10001;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: ps.center.update.DownloadApkDialog.2
            @Override // android.os.Handler
            @SuppressLint({"HandlerLeak"})
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        DownloadApkDialog.mProgress.setProgress(DownloadApkDialog.progress);
                        DownloadApkDialog.this.tv_progress.setText(DownloadApkDialog.progress + "%");
                        break;
                    case 2:
                        DownloadApkDialog.mProgress.setEnabled(true);
                        DownloadApkDialog.this.installApk();
                        Toast.makeText(DownloadApkDialog.this.context, "正在安装~", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(DownloadApkDialog.this.context, "下载失败~", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
        this.isMust = isMust;
        this.context = context;
        this.url = url;
        this.title = title;
        this.content = content;
    }

    @Override // ps.center.update.BaseDialog
    protected int getLayout() {
        return R.layout.business_dialog_download_apk;
    }

    @Override // ps.center.update.BaseDialog
    protected void initView() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.tv_content = (TextView) findViewById(R.id.tv_content);
        this.tv_btn = (TextView) findViewById(R.id.tv_btn);
        this.tv_cancle = (TextView) findViewById(R.id.tv_cancle);
        this.iv_bg = (ImageView) findViewById(R.id.iv_bg);
        this.ll_before = (LinearLayout) findViewById(R.id.ll_before);
        this.ll_after = (LinearLayout) findViewById(R.id.ll_after);
        this.ll_finish = (LinearLayout) findViewById(R.id.ll_finish);
        this.tv_know_btn = (TextView) findViewById(R.id.tv_know_btn);
        mProgress = (ProgressBar) findViewById(R.id.mProgress);
        this.tv_progress = (TextView) findViewById(R.id.tv_progress);
        this.tv_cancle_a = (TextView) findViewById(R.id.tv_cancle_a);
        this.ll_before.setVisibility(View.VISIBLE);
        this.ll_after.setVisibility(View.GONE);
        this.ll_finish.setVisibility(View.GONE);
        if (this.isMust) {
            this.tv_cancle.setVisibility(View.GONE);
            this.tv_cancle_a.setVisibility(View.GONE);
            this.iv_bg.setImageResource(R.mipmap.business_img_updata_must);
            onBackPressed();
        } else {
            this.tv_cancle.setVisibility(View.VISIBLE);
            this.tv_cancle_a.setVisibility(View.VISIBLE);
            this.iv_bg.setImageResource(R.mipmap.business_img_updata_normal);
        }
        this.tv_title.setText(this.title);
        this.tv_content.setText(this.content);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
    }

    @Override // ps.center.update.BaseDialog
    protected void initData() {
    }

    @Override // ps.center.update.BaseDialog
    protected void setListener() {
        this.tv_btn.setOnClickListener(v -> {
            downLoadApk(this.context);
        });
        this.tv_cancle.setOnClickListener(v2 -> {
            dismiss();
        });
        this.tv_know_btn.setOnClickListener(v3 -> {
            dismiss();
        });
        this.tv_cancle_a.setOnClickListener(v4 -> {
            dismiss();
            cancelFlag = true;
        });
    }

    private void downLoadApk(Context context) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this.MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            return;
        }
        this.ll_before.setVisibility(View.GONE);
        this.ll_after.setVisibility(View.VISIBLE);
        this.ll_finish.setVisibility(View.GONE);
        new Thread(new Runnable() { // from class: ps.center.update.DownloadApkDialog.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    URL url1 = new URL(DownloadApkDialog.this.url);
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.connect();
                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    File file = new File(DownloadApkDialog.savePath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    String apkFile = DownloadApkDialog.saveFileName;
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);
                    int count = 0;
                    byte[] buf = new byte[1024];
                    while (true) {
                        int numread = is.read(buf);
                        count += numread;
                        int unused = DownloadApkDialog.progress = (int) ((count / length) * 100.0f);
                        DownloadApkDialog.this.mHandler.sendEmptyMessage(1);
                        if (numread <= 0) {
                            DownloadApkDialog.this.mHandler.sendEmptyMessage(2);
                            break;
                        } else {
                            fos.write(buf, 0, numread);
                            if (DownloadApkDialog.cancelFlag) {
                                break;
                            }
                        }
                    }
                    fos.close();
                    is.close();
                } catch (Exception e) {
                    DownloadApkDialog.this.mHandler.sendEmptyMessage(3);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installApk() {
        Log.e("安装包地址：", BuildConfig.VERSION_NAME + saveFileName);
        Intent intent = new Intent("android.intent.action.VIEW");
        File file = new File(saveFileName);
        if (Build.VERSION.SDK_INT >= 24) {
            Uri apkUri = FileProvider.getUriForFile(this.context, this.context.getPackageName() + ".fileprovider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        this.context.startActivity(intent);
        this.ll_before.setVisibility(View.VISIBLE);
        this.ll_after.setVisibility(View.GONE);
        this.ll_finish.setVisibility(View.GONE);
    }
}