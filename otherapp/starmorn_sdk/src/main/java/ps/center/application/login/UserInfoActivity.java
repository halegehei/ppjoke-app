package ps.center.application.login;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import ps.center.application.BuildConfig;
import ps.center.application.config.ApplicationConfig;
import ps.center.centerinterface.bean.CreateApp;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.databinding.BusinessActivityUserInfoBinding;
import ps.center.library.http.base.Result;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.utils.ui.OnClickListener;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;

/* loaded from: classes.jar:ps/center/application/login/UserInfoActivity.class */
public class UserInfoActivity extends BaseActivityVB<BusinessActivityUserInfoBinding> {
    public static final int RESULT_CODE = 343452;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, (Class<?>) UserInfoActivity.class);
        activity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityUserInfoBinding getLayout() {
        return BusinessActivityUserInfoBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        Glide.with(this).load(CenterConstant.getUser().avatar).error(ApplicationConfig.getSettingConfig().mineUserDefaultIcon).apply(new RequestOptions().circleCrop()).into(((BusinessActivityUserInfoBinding) this.binding).userIcon);
        ((BusinessActivityUserInfoBinding) this.binding).nickname.setText(String.format("用户%s", Long.valueOf(Save.instance.getLong(CenterConstant.UID, 0L))));
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void setListener() {
        ((BusinessActivityUserInfoBinding) this.binding).returnBtn.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.login.UserInfoActivity.1
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                UserInfoActivity.this.finish();
            }
        });
        ((BusinessActivityUserInfoBinding) this.binding).item3.setOnClickListener(new AnonymousClass2(1000L));
        ((BusinessActivityUserInfoBinding) this.binding).item4.setOnClickListener(new OnClickListener(1000L) { // from class: ps.center.application.login.UserInfoActivity.3
            @Override // ps.center.utils.ui.OnClickListener
            public void click(View view) {
                UnUserActivity.start(UserInfoActivity.this);
            }
        });
    }

    /* renamed from: ps.center.application.login.UserInfoActivity$2, reason: invalid class name */
    /* loaded from: classes.jar:ps/center/application/login/UserInfoActivity$2.class */
    class AnonymousClass2 extends OnClickListener {
        AnonymousClass2(long time) {
            super(time);
        }

        @Override // ps.center.utils.ui.OnClickListener
        public void click(View view) {
            new LogoutDialog(UserInfoActivity.this, false, call -> {
                final LogoutDialog dialog = (LogoutDialog) call;
                CenterHttp.get().logout(new Result<CreateApp>() { // from class: ps.center.application.login.UserInfoActivity.2.1
                    @Override // ps.center.library.http.base.Result
                    public void success(CreateApp obj) {
                        CenterHttp.get().getUserInfo(new Result<User>() { // from class: ps.center.application.login.UserInfoActivity.2.1.1
                            @Override // ps.center.library.http.base.Result
                            public void success(User obj2) {
                                DataChangeManager.get().change(1, BuildConfig.VERSION_NAME);
                                dialog.dismiss();
                                ToastUtils.show(Super.getContext(), "退出成功");
                                UserInfoActivity.this.finish();
                            }
                        });
                    }
                });
            }).show();
        }
    }

    public void finish() {
        setResult(RESULT_CODE);
        super.finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 201) {
            finish();
        }
    }
}