package ps.center.application.pay;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import ps.center.application.BuildConfig;
import ps.center.R;
import ps.center.application.config.ApplicationConfig;
import ps.center.centerinterface.bean.PayPage;
import ps.center.utils.Save;
import ps.center.utils.TimeUtils;

/* loaded from: classes.jar:ps/center/application/pay/CardPayAdapter.class */
public class CardPayAdapter extends RecyclerView.Adapter<CardPayAdapter.PayItemViewHolder> {
    private CountDownTimer timer;
    private final ArrayList<String> showRules;
    private final ArrayList<String> showRulesZfb;
    private final String timeTag;
    private final Context context;
    private final int layout;
    private final boolean isDoublePayType;
    private boolean isWxPay;
    private final List<PayPage.GroupsBean.PriceInfoBean> listData;
    private final List<PayPage.GroupsBean.PriceInfoBean> listDataZfb;
    private OnItemClickListener onItemClickListener;

    /* loaded from: classes.jar:ps/center/application/pay/CardPayAdapter$OnItemClickListener.class */
    public interface OnItemClickListener {
        void itemClick(PayPage.GroupsBean.PriceInfoBean priceInfoBean, int i);
    }

    public CardPayAdapter(Context context, int itemLayout, String action, boolean isDoublePayType, boolean isWxPay, List<PayPage.GroupsBean.PriceInfoBean> listData, ArrayList<String> showRules, List<PayPage.GroupsBean.PriceInfoBean> listDataZfb, ArrayList<String> showRulesZfb) {
        this.context = context;
        this.layout = itemLayout;
        this.showRules = showRules;
        this.showRulesZfb = showRulesZfb;
        this.listData = listData;
        this.isDoublePayType = isDoublePayType;
        this.isWxPay = isWxPay;
        this.listDataZfb = listDataZfb;
        this.timeTag = String.format("%s%s", action, "firstOpenPayPagerTime");
    }

    private void startDownTimer(final PayPage.GroupsBean.PriceInfoBean itemData, int time, final TextView tv) {
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
        this.timer = new CountDownTimer(time, 1000L) { // from class: ps.center.application.pay.CardPayAdapter.1
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String time2 = TimeUtils.timeToHHMMSS(millisUntilFinished);
                tv.setText(String.format("限时%s", time2));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (itemData.top_label.equals(BuildConfig.VERSION_NAME)) {
                    tv.setText(itemData.top_label);
                    tv.setVisibility(8);
                } else {
                    tv.setText(itemData.top_label);
                    tv.setVisibility(0);
                }
            }
        };
        this.timer.start();
    }

    private boolean isTimeOut(long s) {
        long firstOpenPayPagerTime = Save.instance.getLong(this.timeTag, 0L);
        return System.currentTimeMillis() - firstOpenPayPagerTime >= firstOpenPayPagerTime + (s * 1000);
    }

    private int getTime(String timeStr) {
        return Integer.parseInt(timeStr);
    }

    private int getDownTimerNumber(String timeStr) {
        long firstOpenPayPagerTime = Save.instance.getLong(this.timeTag, 0L);
        return (int) ((firstOpenPayPagerTime + (Integer.parseInt(timeStr) * 1000)) - System.currentTimeMillis());
    }

    @Override
    @NonNull
    public PayItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(this.layout, parent, false);
        return new PayItemViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull PayItemViewHolder holder, int position) {
        PayPage.GroupsBean.PriceInfoBean item;
        if (this.isDoublePayType) {
            if (this.isWxPay) {
                item = this.listData.get(position);
            } else {
                item = this.listDataZfb.get(position);
            }
        } else {
            item = this.listData.get(position);
        }
        holder.money3.setVisibility(8);
        if (item.is_top.equals("1")) {
            holder.vip_type.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectTitleTextColor));
            holder.money1.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectPriceTextColor));
            holder.money2.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectPriceTextColor));
            holder.desc1.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectBottomTextColor));
            holder.desc2.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectBottomTextColor));
            holder.root_layout.setBackgroundResource(ApplicationConfig.getSettingConfig().pay3ItemSelectBackRes);
        } else {
            holder.vip_type.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultTitleTextColor));
            holder.money1.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultPriceTextColor));
            holder.money2.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultPriceTextColor));
            holder.desc1.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultBottomTextColor));
            holder.desc2.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultBottomTextColor));
            holder.root_layout.setBackgroundResource(ApplicationConfig.getSettingConfig().pay3ItemDefaultBackRes);
        }
        if (!item.renew_type.equals("0")) {
            holder.money2.setText(item.renew_price);
            holder.desc1.setVisibility(8);
            holder.desc2.setVisibility(8);
            holder.desc3.setVisibility(0);
            if (isTimeOut(getTime(this.showRules.get(2)))) {
                if (item.top_label.equals(BuildConfig.VERSION_NAME)) {
                    holder.tips.setText(item.top_label);
                    holder.tips.setVisibility(8);
                } else {
                    holder.tips.setText(item.top_label);
                    holder.tips.setVisibility(0);
                }
            } else {
                startDownTimer(item, getDownTimerNumber(this.showRules.get(2)), holder.tips);
            }
        } else {
            holder.money2.setText(item.price);
            holder.desc1.setVisibility(0);
            holder.desc2.setVisibility(8);
            holder.desc3.setVisibility(8);
            if (item.top_label.equals(BuildConfig.VERSION_NAME)) {
                holder.tips.setText(item.top_label);
                holder.tips.setVisibility(8);
            } else {
                holder.tips.setText(item.top_label);
                holder.tips.setVisibility(0);
            }
        }
        holder.vip_type.setText(item.type_name);
        holder.desc1.setText(item.bottom_label);
        holder.desc2.setText(item.spare_label);
        PayPage.GroupsBean.PriceInfoBean priceInfoBean = item;
        holder.itemView.setOnClickListener(v -> {
            if (this.onItemClickListener != null) {
                this.onItemClickListener.itemClick(priceInfoBean, position);
            }
        });
    }

    public void setIsWxPay(boolean isWxPay) {
        this.isWxPay = isWxPay;
    }
    @Override
    public int getItemCount() {
        if (this.listData != null) {
            return this.listData.size();
        }
        return 0;
    }

    /* loaded from: classes.jar:ps/center/application/pay/CardPayAdapter$PayItemViewHolder.class */
    public static class PayItemViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout root_layout;
        private TextView vip_type;
        private TextView money1;
        private TextView money2;
        private TextView money3;
        private TextView desc1;
        private TextView desc2;
        private TextView desc3;
        private TextView tips;

        public PayItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root_layout = (RelativeLayout) itemView.findViewById(R.id.root_layout);
            this.vip_type = (TextView) itemView.findViewById(R.id.vip_type);
            this.money1 = (TextView) itemView.findViewById(R.id.money1);
            this.money2 = (TextView) itemView.findViewById(R.id.money2);
            this.money3 = (TextView) itemView.findViewById(R.id.money3);
            this.desc1 = (TextView) itemView.findViewById(R.id.desc1);
            this.desc2 = (TextView) itemView.findViewById(R.id.desc2);
            this.desc3 = (TextView) itemView.findViewById(R.id.desc3);
            this.desc3 = (TextView) itemView.findViewById(R.id.desc3);
            this.tips = (TextView) itemView.findViewById(R.id.tips);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}