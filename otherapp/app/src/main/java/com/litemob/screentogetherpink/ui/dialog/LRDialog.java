package com.litemob.screentogetherpink.ui.dialog;

import android.content.Context;
import android.telecom.Call;
import android.widget.TextView;

import com.litemob.screentogetherpink.R;

import ps.center.views.dialog.BaseDialog;

public class LRDialog extends BaseDialog {

    private Context context;
    private Call call;
    private TextView tv_btn_l, tv_btn_r, tv_content, tv_title;
    private String str, str_l, str_r, title;

    public LRDialog(Context context, String title, String str, String str_l, String str_r, Call call) {
        super(context, R.style.dialogBlackBack, R.style.dialogAnimation__down__center);
        this.context = context;
        this.call = call;
        this.title = title;
        this.str = str;
        this.str_l = str_l;
        this.str_r = str_r;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_lr;
    }

    @Override
    protected void initView() {
        tv_btn_l = findViewById(R.id.tv_btn_l);
        tv_btn_r = findViewById(R.id.tv_btn_r);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        tv_title = findViewById(R.id.tv_title);
        tv_content.setText(str);
        tv_title.setText(title);
        tv_btn_l.setText(str_l);
        tv_btn_r.setText(str_r);

        tv_btn_l.setOnClickListener(view -> {
            dismiss();
        });
        tv_btn_r.setOnClickListener(view -> {
            call.call("2");
            dismiss();
        });

    }


    @Override
    protected void initData() {

    }


}
