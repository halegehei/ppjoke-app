package ps.center.views.activity.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import ps.center.R;
import ps.center.views.activity.BaseActivity;
import ps.center.views.dialog.CustomDialog;
import ps.center.views.dialog.Dialog;
import ps.center.views.dialog.DialogStyle;
import ps.center.views.dialog.LoadingListener;

public class NativeWebActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private ImageView back_btn;
    private WebView web_view;
    private CustomDialog loadingDialog;

    public static void jump(Context context, String title, int backIcon, String url) {
        Intent intent = new Intent(context, (Class<?>) NativeWebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("backIcon", backIcon);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override // ps.center.views.activity.BaseActivity
    protected int getLayout() {
        return R.layout.business_activity_native_web;
    }

    @Override // ps.center.views.activity.BaseActivity
    protected void initView() {
        this.title = (TextView) findViewById(R.id.title);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        findViewById(R.id.back_btn, this);
        this.web_view = (WebView) findViewById(R.id.web_view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ps.center.views.activity.BaseActivity
    @SuppressLint({"SetJavaScriptEnabled"})
    protected void initData() {
        Dialog.createLoading(this, DialogStyle.blackBack, 10000L).outCancel(false).keyBackCancel(false).setTimeoutListener(new LoadingListener() { // from class: ps.center.views.activity.webview.NativeWebActivity.1
            @Override // ps.center.views.dialog.LoadingListener
            public void start(CustomDialog customDialog) {
                NativeWebActivity.this.loadingDialog = customDialog;
            }

            @Override // ps.center.views.dialog.LoadingListener
            public void timeout(CustomDialog customDialog) {
                customDialog.dismiss();
            }
        }).show();
        String titleText = getIntent().getStringExtra("title");
        this.title.setText(titleText);
        int backIcon = getIntent().getIntExtra("backIcon", 0);
        if (backIcon != 0) {
            this.back_btn.setImageResource(backIcon);
        }
        String url = getIntent().getStringExtra("url");
        WebSettings webSettings = this.web_view.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(true);
        webSettings.setDefaultFontSize(12);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        this.web_view.setWebViewClient(new WebViewClient() { // from class: ps.center.views.activity.webview.NativeWebActivity.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url2) {
                view.loadUrl(url2);
                return false;
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url2) {
                super.onPageFinished(view, url2);
                if (NativeWebActivity.this.loadingDialog != null && NativeWebActivity.this.loadingDialog.isShowing()) {
                    NativeWebActivity.this.loadingDialog.dismiss();
                }
            }
        });
        this.web_view.loadUrl(url);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.back_btn) {
            if (this.web_view.canGoBack()) {
                this.web_view.goBack();
            } else {
                finish();
            }
        }
    }
}