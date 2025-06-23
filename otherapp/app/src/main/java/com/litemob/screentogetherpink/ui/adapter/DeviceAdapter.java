package com.litemob.screentogetherpink.ui.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.litemob.screentogetherpink.R;
import com.litemob.screentogetherpink.ui.bean.DeviceBean;


public class DeviceAdapter extends BaseQuickAdapter<DeviceBean, BaseViewHolder> {

    public DeviceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceBean item) {
        helper.setText(R.id.tv_phone_name, item.nick_name);
        helper.setText(R.id.tv_phone_number, item.device_code);


        helper.addOnClickListener(R.id.iv_edit);
        helper.addOnClickListener(R.id.iv_del);
        helper.addOnClickListener(R.id.item_swipe_content);





    }

}