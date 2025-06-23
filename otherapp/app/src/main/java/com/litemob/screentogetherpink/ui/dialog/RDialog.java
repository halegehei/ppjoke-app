package com.litemob.screentogetherpink.ui.dialog;

import android.content.Context;
import android.telecom.Call;
import android.widget.TextView;

import com.litemob.screentogetherpink.R;

import ps.center.views.dialog.BaseDialog;

public class RDialog extends BaseDialog {

    private Context context;
    private Call call;
    private TextView tv_btn_l, tv_btn_r, tv_content, tv_title;
    private String str, str_l, str_r, title;

    public RDialog(Context context, String title, String str,  String str_r, Call call) {
        super(context, R.style.dialogBlackBack, R.style.dialogAnimation__down__center);
        this.context = context;
        this.call = call;
        this.title = title;
        this.str = str;
        this.str_r = str_r;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_r;
    }

    @Override
    protected void initView() {
        tv_btn_r = findViewById(R.id.tv_btn_r);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        tv_content.setText(str);
        tv_title.setText(title);
        tv_btn_r.setText(str_r);

        tv_btn_r.setOnClickListener(view -> {
            call.call("2");
            dismiss();
        });

    }


    @Override
    protected void initData() {

    }


}
