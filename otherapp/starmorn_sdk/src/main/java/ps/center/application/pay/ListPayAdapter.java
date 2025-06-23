package ps.center.application.pay;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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


public class ListPayAdapter extends RecyclerView.Adapter<ListPayAdapter.PayItemViewHolder> {
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
    private int defaultIsTop = 0;

    /* loaded from: classes.jar:ps/center/application/pay/ListPayAdapter$OnItemClickListener.class */
    public interface OnItemClickListener {
        void itemClick(PayPage.GroupsBean.PriceInfoBean priceInfoBean, int i);
    }

    public ListPayAdapter(Context context, int itemLayout, String action, boolean isDoublePayType, boolean isWxPay, List<PayPage.GroupsBean.PriceInfoBean> listData, ArrayList<String> showRules, List<PayPage.GroupsBean.PriceInfoBean> listDataZfb, ArrayList<String> showRulesZfb) {
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
        this.timer = new CountDownTimer(time, 1000L) { // from class: ps.center.application.pay.ListPayAdapter.1
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String time2 = TimeUtils.timeToHHMMSS(millisUntilFinished);
                tv.setText(String.format("限时%s", time2));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (itemData.top_label.equals(BuildConfig.VERSION_NAME)) {
                    tv.setText(itemData.top_label);
                    tv.setVisibility(View.GONE);
                } else {
                    tv.setText(itemData.top_label);
                    tv.setVisibility(View.VISIBLE);
                }
            }
        };
        this.timer.start();
    }

    public void setDefaultIsTop(int defaultIsTop) {
        this.defaultIsTop = defaultIsTop;
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
        if (position == this.defaultIsTop) {
            holder.isTop.setVisibility(View.VISIBLE);
        } else {
            holder.isTop.setVisibility(View.GONE);
        }
        if (item.is_top.equals("1")) {
            holder.typeName.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectTitleTextColor));
            holder.bottomLabel.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectBottomTextColor));
            holder.price.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectPriceTextColor));
            holder.price2.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectPriceTextColor));
            holder.freeDay.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectPriceTextColor));
            holder.spareLabel.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectPriceTextColor));
            holder.spareLabel2.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemSelectPriceTextColor));
            holder.root_layout.setBackgroundResource(R.drawable.business_pay3_item_select_bg1);
        } else {
            holder.typeName.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultTitleTextColor));
            holder.bottomLabel.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultBottomTextColor));
            holder.price.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultPriceTextColor));
            holder.price2.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultPriceTextColor));
            holder.freeDay.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultPriceTextColor));
            holder.spareLabel.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultBottomTextColor));
            holder.spareLabel2.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay3ItemDefaultBottomTextColor));
            holder.root_layout.setBackgroundResource(R.drawable.business_pay3_item_select_bg2);
        }
        if (!item.renew_type.equals("0")) {
            if (isTimeOut(getTime(this.showRules.get(2)))) {
                if (item.top_label.equals(BuildConfig.VERSION_NAME)) {
                    holder.tips.setText(item.top_label);
                    holder.tips.setVisibility(View.GONE);
                } else {
                    holder.tips.setText(item.top_label);
                    holder.tips.setVisibility(View.VISIBLE);
                }
            } else {
                startDownTimer(item, getDownTimerNumber(this.showRules.get(2)), holder.tips);
            }
        } else if (item.top_label.equals(BuildConfig.VERSION_NAME)) {
            holder.tips.setText(item.top_label);
            holder.tips.setVisibility(View.GONE);
        } else {
            holder.tips.setText(item.top_label);
            holder.tips.setVisibility(View.VISIBLE);
        }
        holder.typeName.setText(item.type_name);
        holder.price.setText(item.price);
        holder.freeDay.setText(item.free_day);
        holder.spareLabel.setText(item.spare_label);
        holder.spareLabel2.setText(item.spare_label2);
        holder.bottomLabel.setText(item.bottom_label);
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

    /* loaded from: classes.jar:ps/center/application/pay/ListPayAdapter$PayItemViewHolder.class */
    public static class PayItemViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout root_layout;
        private ImageView isTop;
        private TextView typeName;
        private TextView bottomLabel;
        private TextView price;
        private TextView price2;
        private TextView freeDay;
        private TextView spareLabel;
        private TextView spareLabel2;
        private TextView tips;

        public PayItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root_layout = (RelativeLayout) itemView.findViewById(R.id.root_layout);
            this.isTop = (ImageView) itemView.findViewById(R.id.isTop);
            this.typeName = (TextView) itemView.findViewById(R.id.typeName);
            this.bottomLabel = (TextView) itemView.findViewById(R.id.bottomLabel);
            this.price = (TextView) itemView.findViewById(R.id.price);
            this.price2 = (TextView) itemView.findViewById(R.id.price2);
            this.freeDay = (TextView) itemView.findViewById(R.id.freeDay);
            this.spareLabel = (TextView) itemView.findViewById(R.id.spareLabel);
            this.spareLabel2 = (TextView) itemView.findViewById(R.id.spareLabel2);
            this.tips = (TextView) itemView.findViewById(R.id.tips);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}