package com.mooc.ppjoke.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mooc.libnetwork.ApiResponse;
import com.mooc.libnetwork.ApiService;
import com.mooc.libnetwork.JsonCallback;
import com.mooc.ppjoke.R;
import com.mooc.ppjoke.model.User;

public class PhoneLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputPhone;
    private EditText inputCode;
    private View sendCode;
    private View loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_phone_login);

        inputPhone = findViewById(R.id.input_phone);
        inputCode = findViewById(R.id.input_code);
        sendCode = findViewById(R.id.action_send_code);
        loginBtn = findViewById(R.id.action_login);

        sendCode.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.action_send_code) {
            requestCode();
        } else if (v.getId() == R.id.action_login) {
            login();
        }
    }

    private void requestCode() {
        String phone = inputPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiService.get("/login/captcha")
                .addParam("tel", phone)
                .execute(new JsonCallback<Object>() {
                    @Override
                    public void onSuccess(ApiResponse<Object> response) {
                        Toast.makeText(PhoneLoginActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ApiResponse<Object> response) {
                        Toast.makeText(PhoneLoginActivity.this, response.message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void login() {
        final String phone = inputPhone.getText().toString().trim();
        String code = inputCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiService.get("/login/phone")
                .addParam("tel", phone)
                .addParam("code", code)
                .execute(new JsonCallback<User>() {
                    @Override
                    public void onSuccess(ApiResponse<User> response) {
                        if (response.body != null) {
                            UserManager.get().save(response.body);
                            finish();
                        } else {
                            Toast.makeText(PhoneLoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ApiResponse<User> response) {
                        Toast.makeText(PhoneLoginActivity.this, response.message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
