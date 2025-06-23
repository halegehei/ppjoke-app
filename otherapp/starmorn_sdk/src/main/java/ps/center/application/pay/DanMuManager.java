package ps.center.application.pay;

import android.app.Activity;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ps.center.application.config.ApplicationConfig;
import ps.center.application.config.SettingConfig;
import ps.center.business.bean.config.Bots;
import ps.center.business.http.base.BusHttp;
import ps.center.library.http.base.Result;
import ps.center.utils.Super;
import ps.center.views.layout.recycleAutoPlay.AutoPlayUrlItem;
import ps.center.views.layout.recycleAutoPlay.RecycleViewAutoPlay;

/* loaded from: classes.jar:ps/center/application/pay/DanMuManager.class */
public class DanMuManager {
    private Activity activity;
    private RecyclerView[] recyclerView;
    private SettingConfig settingConfig;

    public DanMuManager(Activity activity, RecyclerView... recyclerView) {
        this.activity = activity;
        this.recyclerView = recyclerView;
    }

    public void build() {
        this.settingConfig = ApplicationConfig.getSettingConfig();
        if (this.settingConfig.payPageDanMuMaxLine > 0) {
            BusHttp.bot().getUserMachineList(new Result<List<Bots>>() { // from class: ps.center.application.pay.DanMuManager.1
                @Override // ps.center.library.http.base.Result
                public void success(List<Bots> obj) {
                    for (int i = 0; i < DanMuManager.this.settingConfig.payPageDanMuMaxLine; i++) {
                        List<PayBots> payBots = new ArrayList<>();
                        for (Bots item : obj) {
                            payBots.add(new PayBots(item.nickname + item.good_name, item.headimgurl));
                        }
                        Collections.shuffle(payBots);
                        try {
                            if (DanMuManager.this.recyclerView != null && DanMuManager.this.recyclerView.length > i) {
                                DanMuManager.this.startRun(DanMuManager.this.recyclerView[i], payBots);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRun(RecyclerView recyclerView, List<? extends AutoPlayUrlItem> data) {
        new RecycleViewAutoPlay(this.activity, recyclerView, data, (autoPlayViewHolder, autoPlayItem, i, i1) -> {
            autoPlayViewHolder.rootLayout.setBackgroundResource(this.settingConfig.payPageDanMuItemDrawable);
            autoPlayViewHolder.title.setText(autoPlayItem.getText());
            autoPlayViewHolder.title.setTextColor(Color.parseColor(this.settingConfig.payPageDanMuItemTextColor));
            if (this.settingConfig.payPageDanMuItemHeaderImageIsShow) {
                autoPlayViewHolder.logo.setVisibility(0);
                Glide.with(Super.getContext()).load(autoPlayItem.iconUrl()).into(autoPlayViewHolder.logo);
            } else {
                autoPlayViewHolder.logo.setVisibility(8);
            }
        }, 1000000, 100000).run();
    }

    /* loaded from: classes.jar:ps/center/application/pay/DanMuManager$PayBots.class */
    private static class PayBots implements AutoPlayUrlItem {
        public String title;
        public String url;

        public PayBots(String title, String url) {
            this.title = title;
            this.url = url;
        }

        @Override // ps.center.views.layout.recycleAutoPlay.AutoPlayUrlItem
        public int logoIcon() {
            return 0;
        }

        @Override // ps.center.views.layout.recycleAutoPlay.AutoPlayUrlItem
        public String getText() {
            return this.title;
        }

        @Override // ps.center.views.layout.recycleAutoPlay.AutoPlayUrlItem
        public String iconUrl() {
            return this.url;
        }
    }
}