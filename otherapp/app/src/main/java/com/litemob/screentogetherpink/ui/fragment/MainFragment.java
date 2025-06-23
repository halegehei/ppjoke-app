package com.litemob.screentogetherpink.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.projection.MediaProjectionManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.litemob.screentogetherpink.R;
import com.litemob.screentogetherpink.databinding.FragmentHomeBinding;
import com.litemob.screentogetherpink.okhttp.BaseGetRequest;
import com.litemob.screentogetherpink.okhttp.BasePostRequest;
import com.litemob.screentogetherpink.okhttp.Http;
import com.litemob.screentogetherpink.okhttp.Url;
import com.litemob.screentogetherpink.ui.activity.AddActivity;
import com.litemob.screentogetherpink.ui.activity.QRCodeScanActivity;
import com.litemob.screentogetherpink.ui.activity.RecordsActivity;
import com.litemob.screentogetherpink.ui.activity.ShareActivity;
import com.litemob.screentogetherpink.ui.adapter.DeviceAdapter;
import com.litemob.screentogetherpink.ui.bean.CommonBean;
import com.litemob.screentogetherpink.ui.bean.DeviceBean;
import com.litemob.screentogetherpink.ui.dialog.LRDialog;
import com.litemob.screentogetherpink.ui.dialog.LREditDialog;
import com.litemob.screentogetherpink.ui.view.OnSingleClickListener;
import com.litemob.screentogetherpink.ui.view.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import ps.center.application.manager.PayManager;
import ps.center.application.manager.WeChat;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.share.ShareBean;
import ps.center.business.http.base.BusHttp;
import ps.center.business.utils.free.FreeManager;
import ps.center.library.http.base.Result;
import ps.center.views.dialog.BaseDialog;
import ps.center.views.fragment.BaseFragmentVB;
import ps.center.views.fragment.BundleGet;

public class MainFragment extends BaseFragmentVB<FragmentHomeBinding> {

    private DeviceAdapter adapter;
    private List<DeviceBean> mfakeData;

    @Override
    protected FragmentHomeBinding getLayout() {
        return FragmentHomeBinding.inflate(this.getLayoutInflater());
    }

    @Override
    public void onStart() {
        super.onStart();

        getByDeviceList();
    }

    @Override
    protected void initData(BundleGet bundleGet) {

        adapter = new DeviceAdapter(R.layout.item_records);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setBackgroundColor(Color.TRANSPARENT);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        binding.recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
//        binding.recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));




        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_edit) {
                    Toast.makeText(getActivity(), "编辑", Toast.LENGTH_SHORT).show();
                }else if(view.getId() == R.id.iv_del){
                    Toast.makeText(getActivity(), "删除", Toast.LENGTH_SHORT).show();
                }
            }
        });



        binding.topRightButtonShare.setOnClickListener(view -> {

            BusHttp.share().getShareInfo("1", BusinessConstant.getConfig().standard_conf.share_control.func.share_id, new Result<ShareBean>() {
                @Override
                public void success(ShareBean obj) {
                    ShareBean bean = new ShareBean();
                    bean.link = obj.link;
                    bean.title = obj.title;
                    bean.icon = obj.icon;
                    bean.content = obj.content;
                    WeChat.getInstance().shareToChat(bean);

                }
            });

        });

        binding.leftButtonShare.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(getActivity(), ShareActivity.class));
            }
        });

        binding.ivQrcode.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {



                FreeManager.get().us(getActivity(), new FreeManager.UsFreeListener<Object>() {
                    @Override
                    public void success(Object sssss) {

                        if (XXPermissions.isGranted(getActivity(), Permission.CAMERA)) {

                            Intent intent = new Intent(getActivity(), QRCodeScanActivity.class);
                            startActivity(intent);



                        }else {
                            new LRDialog(getActivity(), "权限申请", "本功能需要获取您的相机权限，用于扫描识别二维码。您如果拒绝上述权限，将无法使用该功能。" , "取消", "确定", new BaseDialog.Call() {
                                @Override
                                public void call(Object o) {
                                    getQrcondePermissions();
                                }
                            }).show();



                        }

                    }

                    @Override
                    public void notFree() {
                        new PayManager(getActivity(), "后置付费", 0).go();
                    }
                });










            }
        });

        binding.ivAdd.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(getActivity(), AddActivity.class));
            }
        });

        binding.rightTextMore.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(getActivity(), RecordsActivity.class));
            }
        });



        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_edit) {

                    LREditDialog dialog = new LREditDialog(getActivity(), "请输入好友昵称", "", "取消", "确定", new BaseDialog.Call() {
                        @Override
                        public void call(Object o) {


                            if ("".equals(o.toString())) {

                                Toast.makeText(getActivity(), "不能为空", Toast.LENGTH_SHORT).show();
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

                    LRDialog dialog=new LRDialog(getActivity(), "确认删除该设备吗?", "", "取消", "确定", new BaseDialog.Call() {
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






                } else if (view.getId() == R.id.item_swipe_content) {
                   // Toast.makeText(getActivity(), "点击了第" + (position + 1) + "项的子视图", Toast.LENGTH_SHORT).show();
                DeviceBean newItem=mfakeData.get(position);
                Intent intent = new Intent(getActivity(), AddActivity.class);
                intent.putExtra("code", newItem.code);
                intent.putExtra("device_code", newItem.device_code);
                startActivity(intent);
                }
            }
        });
    }

    private void getQrcondePermissions(){

        XXPermissions.with(getActivity())
                .permission(Permission.CAMERA)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            Intent intent = new Intent(getActivity(), QRCodeScanActivity.class);
                            startActivity(intent);

                        } else {
                            // Toast.makeText(ShareActivity.this, "获取部分权限成功，但部分权限未正常授予", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            Toast.makeText(getActivity(), "被永久拒绝授权，请手动授予照相机权限", Toast.LENGTH_SHORT).show();

                            XXPermissions.startPermissionActivity(getActivity(), permissions);
                        } else {
                            Toast.makeText(getActivity() ,"照相机权限失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }




    private void getByDeviceList() {


//
//        List<DeviceBean> fakeData = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            DeviceBean device = new DeviceBean();
//            device.id = "id" + i;
//            device.nick_name = "Device " + i;
//            device.friend_id = "friend_id" + i;
//            device.device_code = "device_code" + i;
//            device.code = "code" + i;
//            fakeData.add(device);
//        }







        BasePostRequest.Params params = new BasePostRequest.Params();

        BaseGetRequest baseRequest = new BaseGetRequest(Url.byDeviceList, 25);
        Http.get().byDeviceList(getActivity(), baseRequest, new Result<List<DeviceBean>>() {
            @Override
            public void success(List<DeviceBean> obj) {
                super.success(obj);


                mfakeData=obj;
                getVisibleOrNo();
                adapter.setNewData(mfakeData);




            }

            @Override
            public void err(int code, String message) {
                super.err(code, message);
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void getVisibleOrNo(){

        if (!mfakeData.isEmpty()){



            binding.noRecordsLayout.setVisibility(View.GONE);
            binding.rightTextMore.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.VISIBLE);
        }else {

            binding.noRecordsLayout.setVisibility(View.VISIBLE);
            binding.rightTextMore.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.GONE);
        }


    }

    private void updateNickName(String name,String id,int position) {


        BasePostRequest.Params params = new BasePostRequest.Params();
        params.add("nick_name", name);
        params.add("id", id);



        BasePostRequest basePostRequest=new BasePostRequest(Url.updateNickName,params,25);

        Http.get().updateNickName(getActivity(), basePostRequest, new Result<CommonBean>() {
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
                    Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    private void delDeviceListInfo(String id,int position) {





        BasePostRequest.Params params = new BasePostRequest.Params();
        params.add("id", id);



        BasePostRequest basePostRequest=new BasePostRequest(Url.delDeviceListInfo,params,25);

        Http.get().delDeviceListInfo(getActivity(), basePostRequest, new Result<CommonBean>() {
            @Override
            public void success(CommonBean obj) {
                super.success(obj);





            }

            @Override
            public void err(int code, String message) {
                super.err(code, message);
                if(code==200){

                    adapter.remove(position);
                    getVisibleOrNo();
                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }






}
