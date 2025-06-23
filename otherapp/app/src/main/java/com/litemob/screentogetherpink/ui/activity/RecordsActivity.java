package com.litemob.screentogetherpink.ui.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.litemob.screentogetherpink.R;
import com.litemob.screentogetherpink.databinding.ActivityConnectdeviceBinding;
import com.litemob.screentogetherpink.databinding.ActivityRecordsBinding;
import com.litemob.screentogetherpink.okhttp.BaseGetRequest;
import com.litemob.screentogetherpink.okhttp.BasePostRequest;
import com.litemob.screentogetherpink.okhttp.Http;
import com.litemob.screentogetherpink.okhttp.Url;
import com.litemob.screentogetherpink.ui.adapter.DeviceAdapter;

import com.litemob.screentogetherpink.ui.bean.CommonBean;
import com.litemob.screentogetherpink.ui.bean.DeviceBean;
import com.litemob.screentogetherpink.ui.bean.DeviceInfoBean;
import com.litemob.screentogetherpink.ui.dialog.LRDialog;
import com.litemob.screentogetherpink.ui.dialog.LREditDialog;
import com.litemob.screentogetherpink.ui.dialog.LRImageDialog;
import com.litemob.screentogetherpink.ui.view.SpacesItemDecoration;


import java.util.ArrayList;
import java.util.List;

import ps.center.library.http.base.Result;
import ps.center.views.activity.IntentGet;
import ps.center.views.dialog.BaseDialog;

public class RecordsActivity extends MyBaseActivity<ActivityRecordsBinding>{
    private DeviceAdapter adapter;
    private List<DeviceBean> mfakeData;

    @Override
    protected ActivityRecordsBinding getLayout() {
        return ActivityRecordsBinding.inflate(this.getLayoutInflater());
    }

    @Override
    protected void initData(IntentGet intentGet) {
        adapter = new DeviceAdapter(R.layout.item_records_more);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setBackgroundColor(Color.TRANSPARENT);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        binding.recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(binding.recyclerView);
        getByDeviceList();
        // 在你的Adapter的构造方法或者convert方法中，添加你想要点击的子视图的id

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_edit) {

                    LREditDialog dialog = new LREditDialog(RecordsActivity.this, "请输入好友昵称", "", "取消", "确定", new BaseDialog.Call() {
                        @Override
                        public void call(Object o) {


                            if ("".equals(o.toString())) {

                                Toast.makeText(RecordsActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                            }else {
                                String name =o.toString();
                                DeviceBean newItem=mfakeData.get(position);
                                String id=newItem.id;
                                updateNickName(name,id,position);

                               // updateDeviceName(name,position);

                            }
                        }
                    });
                    dialog.show();



                }else if(view.getId() == R.id.iv_del){

                    LRDialog dialog=new LRDialog(RecordsActivity.this, "确认删除该设备吗?", "", "取消", "确定", new BaseDialog.Call() {
                        @Override
                        public void call(Object o) {

                            if ("2".equals(o.toString())) {

                                DeviceBean newItem=mfakeData.get(position);
                                String id=newItem.id;

                                delDeviceListInfo(id,position);

                            }

                        }
                    });
                    dialog.show();






                }else if (view.getId() == R.id.item_swipe_content) {
                    // Toast.makeText(getActivity(), "点击了第" + (position + 1) + "项的子视图", Toast.LENGTH_SHORT).show();
                    DeviceBean newItem=mfakeData.get(position);
                    Intent intent = new Intent(RecordsActivity.this, AddActivity.class);
                    intent.putExtra("code", newItem.code);
                    intent.putExtra("device_code", newItem.device_code);
                    startActivity(intent);
                }
            }
        });

        binding.backButton.setOnClickListener(v -> {
            finish();
        });

    }



    private void getByDeviceList() {

//        mfakeData = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            DeviceBean device = new DeviceBean();
//            device.id = "id" + i;
//            device.nick_name = "Device " + i;
//            device.friend_id = "friend_id" + i;
//            device.device_code = "device_code" + i;
//            device.code = "code" + i;
//            mfakeData.add(device);
//        }
//
//        adapter.setNewData(mfakeData);



        BasePostRequest.Params params = new BasePostRequest.Params();

        BaseGetRequest baseRequest = new BaseGetRequest(Url.byDeviceList, 25);
        Http.get().byDeviceList(RecordsActivity.this, baseRequest, new Result<List<DeviceBean>>() {
            @Override
            public void success(List<DeviceBean> obj) {
                super.success(obj);


                mfakeData=obj;
                adapter.setNewData(mfakeData);




            }

            @Override
            public void err(int code, String message) {
                super.err(code, message);
                Toast.makeText(RecordsActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void updateDeviceName(String name,int position) {


        BasePostRequest.Params params = new BasePostRequest.Params();
        params.add("name", name);



        BasePostRequest basePostRequest=new BasePostRequest(Url.updateDeviceName,params,25);

        Http.get().updateDeviceName(RecordsActivity.this, basePostRequest, new Result<CommonBean>() {
            @Override
            public void success(CommonBean obj) {
                super.success(obj);





            }

            @Override
            public void err(int code, String message) {
                super.err(code, message);
                if(code==200){

                    DeviceBean newItem=mfakeData.get(position);
                    newItem.device_code = name;
                    adapter.getData().set(position, newItem);
                    adapter.notifyItemChanged(position);
                    Toast.makeText(RecordsActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RecordsActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    private void updateNickName(String name,String id,int position) {


        BasePostRequest.Params params = new BasePostRequest.Params();
        params.add("nick_name", name);



        BasePostRequest basePostRequest=new BasePostRequest(Url.updateNickName,params,25);

        Http.get().updateNickName(RecordsActivity.this, basePostRequest, new Result<CommonBean>() {
            @Override
            public void success(CommonBean obj) {
                super.success(obj);





            }

            @Override
            public void err(int code, String message) {
                super.err(code, message);
                if(code==200){



                    DeviceBean newItem=mfakeData.get(position);
                    newItem.nick_name = name;
                    adapter.getData().set(position, newItem);
                    adapter.notifyItemChanged(position);
                    Toast.makeText(RecordsActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RecordsActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    private void delDeviceListInfo(String id,int position) {





        BasePostRequest.Params params = new BasePostRequest.Params();
        params.add("id", id);



        BasePostRequest basePostRequest=new BasePostRequest(Url.delDeviceListInfo,params,25);

        Http.get().delDeviceListInfo(RecordsActivity.this, basePostRequest, new Result<CommonBean>() {
            @Override
            public void success(CommonBean obj) {
                super.success(obj);





            }

            @Override
            public void err(int code, String message) {
                super.err(code, message);
                if(code==200){

                    adapter.remove(position);
                    Toast.makeText(RecordsActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RecordsActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }







}
