package ps.center.application.pay;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ps.center.application.BuildConfig;
import ps.center.R;
import ps.center.application.config.ApplicationConfig;
import ps.center.centerinterface.bean.PayPage;

/* loaded from: classes.jar:ps/center/application/pay/SingleAndDoubleAdapter.class */
public class SingleAndDoubleAdapter extends RecyclerView.Adapter<SingleAndDoubleAdapter.SingleDoubleItemViewHolder> {
    private Context context;
    private List<PayPage.GroupsBean.PriceInfoBean> listData;
    private List<PayPage.GroupsBean.PriceInfoBean> listDataZfb;
    private boolean isDoublePayType;
    private boolean isWxPay;
    private int itemLayout;
    private OnItemClickListener onItemClickListener;

    /* loaded from: classes.jar:ps/center/application/pay/SingleAndDoubleAdapter$OnItemClickListener.class */
    public interface OnItemClickListener {
        void itemClick(PayPage.GroupsBean.PriceInfoBean priceInfoBean, int i);
    }

    public SingleAndDoubleAdapter(Context context, int itemLayout, boolean isDoublePayType, boolean isWxPay, List<PayPage.GroupsBean.PriceInfoBean> listData, List<PayPage.GroupsBean.PriceInfoBean> listDataZfb) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.listData = listData;
        this.listDataZfb = listDataZfb;
        this.isDoublePayType = isDoublePayType;
        this.isWxPay = isWxPay;
    }

    @Override
    @NonNull
    public SingleDoubleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(this.itemLayout, parent, false);
        return new SingleDoubleItemViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull SingleDoubleItemViewHolder holder, int position) {
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
        if (item.is_top.equals("1")) {
            holder.vip_type.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay1ItemSelectTitleTextColor));
            holder.desc.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay1ItemSelectBottomTextColor));
            holder.root_layout.setBackgroundResource(ApplicationConfig.getSettingConfig().pay1ItemSelectBackRes);
        } else {
            holder.vip_type.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay1ItemDefaultTitleTextColor));
            holder.desc.setTextColor(Color.parseColor(ApplicationConfig.getSettingConfig().pay1ItemDefaultBottomTextColor));
            holder.root_layout.setBackgroundResource(ApplicationConfig.getSettingConfig().pay1ItemDefaultBackRes);
        }
        if (item.top_label.equals(BuildConfig.VERSION_NAME)) {
            holder.tips.setText(item.top_label);
            holder.tips.setVisibility(View.GONE);
        } else {
            holder.tips.setText(item.top_label);
            holder.tips.setVisibility(View.VISIBLE);
        }
        holder.vip_type.setText(item.type_name);
        holder.desc.setText(item.bottom_label);
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

    public int getItemCount() {
        if (this.listData != null) {
            return this.listData.size();
        }
        return 0;
    }


    public static class SingleDoubleItemViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout root_layout;
        private TextView vip_type;
        private TextView desc;
        private TextView tips;

        public SingleDoubleItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root_layout = (RelativeLayout) itemView.findViewById(R.id.root_layout);
            this.vip_type = (TextView) itemView.findViewById(R.id.vip_type);
            this.desc = (TextView) itemView.findViewById(R.id.desc);
            this.tips = (TextView) itemView.findViewById(R.id.tips);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}