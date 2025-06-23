package com.litemob.screentogetherpink.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.litemob.screentogetherpink.databinding.ActivityConnectdeviceBinding;
import com.litemob.screentogetherpink.okhttp.BasePostRequest;

import com.litemob.screentogetherpink.okhttp.Http;
import com.litemob.screentogetherpink.okhttp.Url;
import com.litemob.screentogetherpink.ui.bean.CommonBean;

import ps.center.application.manager.PayManager;
import ps.center.business.utils.free.FreeManager;
import ps.center.library.http.base.Result;
import ps.center.views.activity.IntentGet;

import static com.mobile.auth.gatewayauth.utils.ReflectionUtils.getActivity;

public class AddActivity extends MyBaseActivity<ActivityConnectdeviceBinding>{


    private String code;
    private String deviceCode;
    @Override
    protected ActivityConnectdeviceBinding getLayout() {
        return ActivityConnectdeviceBinding.inflate(this.getLayoutInflater());
    }

    @Override
    protected void initData(IntentGet intentGet) {

//        code =intentGet.getIntentValue("code", "");
//        deviceCode = intentGet.getIntentValue("devicecode", "");

       code = getIntent().getStringExtra("code");
       deviceCode = getIntent().getStringExtra("device_code");

        binding.etDeviceCode.setText(deviceCode);
        binding.etVerificationCode.setText(code);



        binding.backButton.setOnClickListener(v -> {
            finish();
        });

        binding.connectButton.setOnClickListener(view -> {
           // Toast.makeText(this, "开始连接", Toast.LENGTH_SHORT).show();



            if(TextUtils.isEmpty(binding.etDeviceCode.getText().toString())){
                Toast.makeText(this, "请输入正确设备码", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(binding.etVerificationCode.getText().toString())){
                Toast.makeText(this, "请输入正确验证码", Toast.LENGTH_SHORT).show();
            } else {


                FreeManager.get().us(AddActivity.this, new FreeManager.UsFreeListener<Object>() {
                    @Override
                    public void success(Object sssss) {
                        createScreenShareInfo(binding.etDeviceCode.getText().toString(), binding.etVerificationCode.getText().toString());
                    }

                    @Override
                    public void notFree() {
                        new PayManager(getActivity(), "后置付费", 0).go();
                    }
                });


            }


        });


    }


    private void createScreenShareInfo(String device_code,String code) {


        BasePostRequest.Params params = new BasePostRequest.Params();
        params.add("device_code", device_code);
        params.add("code", code);



        BasePostRequest basePostRequest=new BasePostRequest(Url.createScreenShareInfo,params,25);

        Http.get().updateRefreshStatus(AddActivity.this, basePostRequest, new Result<CommonBean>() {
            @Override
            public void success(CommonBean obj) {
                super.success(obj);



               // Toast.makeText(AddActivity.this, obj.url, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddActivity.this, PlayActivity.class); // NewActivity是你要打开的Activity的类名
                intent.putExtra("url", obj.url); // 将url作为额外数据放入Intent
                Log.e("666",obj.url);

                startActivity(intent); // 启动新的Activity

            }

            @Override
            public void err(int code, String message) {
                super.err(code, message);

                Toast.makeText(AddActivity.this, message, Toast.LENGTH_SHORT).show();


            }
        });


    }

}
