package ps.center.application.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.List;
import ps.center.application.BuildConfig;
import ps.center.application.manager.PayManager;
import ps.center.business.BusinessConstant;
import ps.center.business.bean.config.AppConfig;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.databinding.BusinessActivityGuideBinding;
import ps.center.utils.Save;
import ps.center.views.activity.BaseActivityVB;
import ps.center.views.activity.IntentGet;

/* loaded from: classes.jar:ps/center/application/guide/GuideActivity.class */
public class GuideActivity extends BaseActivityVB<BusinessActivityGuideBinding> {
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, (Class<?>) GuideActivity.class);
        activity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.activity.BaseActivityVB
    public BusinessActivityGuideBinding getLayout() {
        return BusinessActivityGuideBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.activity.BaseActivityVB
    protected void initData(IntentGet intentGet) {
        Save.instance.put("isGuideWelcomePager", (Object) true);
        List<Fragment> fragments = new ArrayList<>();
        List<AppConfig.StandardConfBean.GuidePageBean.FuncBean.GuideWithTypeBean> guide_with_type = BusinessConstant.getConfig().standard_conf.guide_page.func.guide_with_type;
        int index = 0;
        for (AppConfig.StandardConfBean.GuidePageBean.FuncBean.GuideWithTypeBean item : guide_with_type) {
            if (item.type.equals("1")) {
                GuideFragment guideFragment = new GuideFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url", item.data);
                bundle.putString("type", item.type);
                bundle.getInt("index", index);
                guideFragment.setArguments(bundle);
                fragments.add(guideFragment);
            } else if (item.type.equals("2")) {
                GuideFragment guideFragment2 = new GuideFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("url", item.data);
                bundle2.putString("type", item.type);
                bundle2.getInt("index", index);
                guideFragment2.setArguments(bundle2);
                fragments.add(guideFragment2);
            } else {
                GuideH5Fragment guideFragment3 = new GuideH5Fragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString("url", item.data);
                bundle3.putString("type", item.type);
                bundle3.getInt("index", index);
                guideFragment3.setArguments(bundle3);
                fragments.add(guideFragment3);
            }
            index++;
        }
        GuideAdapter guideAdapter = new GuideAdapter(this, fragments);
        ((BusinessActivityGuideBinding) this.binding).viewPager.setAdapter(guideAdapter);
        ((BusinessActivityGuideBinding) this.binding).viewPager.setOffscreenPageLimit(fragments.size());
    }

    public void nextPage() {
        ((BusinessActivityGuideBinding) this.binding).viewPager.setCurrentItem(((BusinessActivityGuideBinding) this.binding).viewPager.getCurrentItem() + 1);
    }

    public void goMain() {
        if (CenterConstant.getUser().isVip) {
            jumpMainActivity();
        } else if (BusinessConstant.getConfig().standard_conf.pay_page.comm.is_active.equals("1") && BusinessConstant.getConfig().standard_conf.pay_page.func.prepay_sw.equals("1")) {
            customPayAction();
        } else {
            jumpMainActivity();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void jumpMainActivity() {
        String mainActivityClassName = Save.instance.getString("mainActivityClassName", BuildConfig.VERSION_NAME);
        try {
            Class<?> mainActivityClass = Class.forName(mainActivityClassName);
            Intent intent = new Intent((Context) this, mainActivityClass);
            startActivity(intent);
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void finish() {
        super.finish();
    }

    /* loaded from: classes.jar:ps/center/application/guide/GuideActivity$GuideAdapter.class */
    private static class GuideAdapter extends FragmentStateAdapter {
        private final List<Fragment> fragments;

        public GuideAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
            super(fragmentActivity);
            this.fragments = fragments;
        }

        @NonNull
        public Fragment createFragment(int position) {
            return this.fragments.get(position);
        }

        public int getItemCount() {
            return this.fragments.size();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void customPayAction() {
        new PayManager(this, "前置付费", 0).go();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}