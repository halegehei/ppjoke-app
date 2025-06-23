package com.litemob.screentogetherpink.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.TextView;


import com.litemob.screentogetherpink.BaseConstant;
import com.litemob.screentogetherpink.R;
import com.litemob.screentogetherpink.databinding.ActivityMainBinding;
import com.litemob.screentogetherpink.ui.fragment.MainFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ps.center.application.DataChangeEvent.DataChangeStatus;
import ps.center.application.mine.MineFragment;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.business.utils.free.FreeManager;
import ps.center.centerinterface.bean.User;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.centerinterface.http.CenterHttp;
import ps.center.library.http.base.Result;
import ps.center.update.AppUpdateManager;
import ps.center.utils.ActivityStackUtils;
import ps.center.utils.Save;
import ps.center.utils.ToastUtils;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.activity.IntentGet;
import ps.center.views.fragment.BaseFragmentVB;


public class MainActivity extends MyBaseActivity<ActivityMainBinding> implements FreeManager.FreeTimeOverListener {

    private MainFragment mainFragment;
    private MineFragment mineFragment;

    @Override
    protected ActivityMainBinding getLayout() {
        return ActivityMainBinding.inflate(this.getLayoutInflater());
    }

    @Override
    protected void initData(IntentGet intentGet) {
        ActivityStackUtils.getInstance().finishAllActivity(MainActivity.this);


        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            checkUpdate();
        }, 500);
        binding.tvTab1.setOnClickListener(v -> {
            setAc(binding.tvTab1);
            if (mainFragment == null) mainFragment = new MainFragment();
            showFrag(mainFragment);
        });
        binding.tvTab2.setOnClickListener(v -> {
            setAc(binding.tvTab2);
            if (mineFragment == null) mineFragment = new MineFragment();
            showFrag(mineFragment);
        });

        setAc(binding.tvTab1);
        if (mainFragment == null) mainFragment = new MainFragment();
        showFrag(mainFragment);


        FreeManager.get().registerFreeTimeOverListener(MainActivity.this);

        if (!Save.instance.getBoolean("isShowRating", false) && BusinessConstant.getConfig().standard_conf.rating_pop.comm.is_active.equals("1") && BusinessConstant.getConfig().standard_conf.rating_pop.func.score_sw.equals("1")) {
            if (BusinessConstant.getConfig().standard_conf.rating_pop.func.is_vip.equals("1")) {
                if (CenterConstant.getUser().isVip) {
                    showRating();
                }
            } else {
                showRating();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            // 用户同意屏幕捕捉，你可以在这里获取MediaProjection对象并开始捕捉屏幕
            DataChangeManager.get().change(BaseConstant.SCREEN_PERMISSION, true);
        } else {
            // 用户拒绝了屏幕捕捉
            DataChangeManager.get().change(BaseConstant.SCREEN_PERMISSION, false);
        }
    }

    public void showRating() {
        String time = BusinessConstant.getConfig().standard_conf.rating_pop.func.update_cond1;
        int times = 0;
        try {
            times = Integer.parseInt(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (times <= 0) {
            return;
        }
        new CountDownTimer(times * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                Save.instance.put("isShowRating", true);
                DataChangeManager.get().change(BaseConstant.SHOW_RATING, "");
            }
        }.start();
    }

    private void setAc(TextView tv) {
        binding.tvTab1.setActivated(false);
        binding.tvTab2.setActivated(false);
        tv.setActivated(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showFrag(BaseFragmentVB fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment frag : manager.getFragments()) {
            if (frag != fragment) {
                transaction.hide(frag);
            } else if (frag.isHidden()) {
                transaction.show(frag);
            }
        }

        if (!fragment.isAdded()) transaction.add(R.id.frameLayout, fragment);
        transaction.commit();
    }

    private long exitTime = 2000;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtils.show(MainActivity.this, "再按一次退出APP");
                exitTime = System.currentTimeMillis();
                return false;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    private void checkUpdate() {
        AppConfig.StandardConfBean.UpdatedBean updated = BusinessConstant.getConfig().standard_conf.updated;
        if (updated.comm.is_active.equals("1")) {
            if (updated.func.is_vip.equals("1")) {
                if (CenterConstant.getUser().isVip) {
                    AppUpdateManager.show(MainActivity.this, updated.func.forced.equals("1"), updated.func.app_link, updated.comm.conf_name, updated.func.content);
                }
            } else {
                AppUpdateManager.show(MainActivity.this, updated.func.forced.equals("1"), updated.func.app_link, updated.comm.conf_name, updated.func.content);
            }
        }
    }


    @Override
    public void timeOver() {
// 免费试用到期后回调刷新用户信息
        CenterHttp.get().getUserInfo(new Result<User>() {
            @Override
            public void success(User obj) {
                DataChangeManager.get().change(DataChangeStatus.UPDATE_USER_INFO, "");
            }
        });
    }


}
