package ps.center.application.login;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import ps.center.application.BuildConfig;
import ps.center.centerinterface.bean.CreateApp;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.databinding.BusinessActivityUnUserBinding;
import ps.center.library.http.base.HttpStatus;
import ps.center.library.http.base.Result;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.utils.ui.OnClickListener;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;

/* loaded from: classes.jar:ps/center/application/login/UnUserActivity.class */
public class UnUserActivity extends BaseActivityVB<BusinessActivityUnUserBinding> {
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, (Class<?>) UnUserActivity.class);
        activity.startActivityForResult(intent, 200);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityUnUserBinding getLayout() {
        return BusinessActivityUnUserBinding.inflate(getLayoutInflater());
    }

    /* renamed from: ps.center.application.login.UnUserActivity$1, reason: invalid class name */
    /* loaded from: classes.jar:ps/center/application/login/UnUserActivity$1.class */
    class AnonymousClass1 extends OnClickListener {
        AnonymousClass1(long time) {
            super(time);
        }

        @Override // ps.center.utils.ui.OnClickListener
        public void click(View view) {
            new UnUserDialog(UnUserActivity.this, false, call -> {
                final UnUserDialog dialog = (UnUserDialog) call;
                CenterHttp.get().delete(new Result<CreateApp>() { // from class: ps.center.application.login.UnUserActivity.1.1
                    @Override // ps.center.library.http.base.Result
                    public void success(CreateApp obj) {
                        CenterHttp.get().getUserInfo(new Result<User>() { // from class: ps.center.application.login.UnUserActivity.1.1.1
                            @Override // ps.center.library.http.base.Result
                            public void success(User obj2) {
                                DataChangeManager.get().change(1, BuildConfig.VERSION_NAME);
                                dialog.dismiss();
                                ToastUtils.show(Super.getContext(), "注销成功");
                                UnUserActivity.this.setResult(HttpStatus.SOCKET_TIMEOUT);
                                UnUserActivity.this.finish();
                            }
                        });
                    }
                });
            }).show();
        }
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        ((BusinessActivityUnUserBinding) this.binding).returnBtn.setOnClickListener(v -> {
            finish();
        });
        ((BusinessActivityUnUserBinding) this.binding).unUserBtn.setOnClickListener(new AnonymousClass1(1000L));
    }
}