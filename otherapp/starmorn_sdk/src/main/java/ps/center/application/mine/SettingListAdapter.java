package ps.center.application.mine;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import ps.center.R;
import ps.center.application.config.SettingConfig;

/* loaded from: classes.jar:ps/center/application/mine/SettingListAdapter.class */
public class SettingListAdapter extends RecyclerView.Adapter<SettingListAdapter.SettingViewHolder> {
    private final Context context;
    private final List<SettingBean> settingBeans;
    private final SettingConfig settingConfig;
    private OnItemClickListener onItemClickListener;
    private final int layoutId;
    private boolean isIdentityAuthentication = false;

    /* loaded from: classes.jar:ps/center/application/mine/SettingListAdapter$OnItemClickListener.class */
    public interface OnItemClickListener {
        void itemClick(int i, SettingBean settingBean);
    }

    public SettingListAdapter(Context context, int layoutId, SettingConfig settingConfig, List<SettingBean> settingBeans) {
        this.context = context;
        this.layoutId = layoutId;
        this.settingBeans = settingBeans;
        this.settingConfig = settingConfig;
    }
    @Override
    @NonNull
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(this.layoutId, parent, false);
        return new SettingViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        SettingBean item = this.settingBeans.get(position);
        if (item.icon instanceof String) {
            Glide.with(this.context).load((String) item.icon).into(holder.icon);
        } else {
            Glide.with(this.context).load(Integer.valueOf(((Integer) item.icon).intValue())).into(holder.icon);
        }
        if (item.name.equals("实名认证")) {
            holder.statusText.setVisibility(0);
            if (this.isIdentityAuthentication) {
                holder.statusText.setText("已认证");
                holder.statusText.setTextColor(-12797623);
            } else {
                holder.statusText.setText("未认证");
                holder.statusText.setTextColor(-1027502);
            }
        } else {
            holder.statusText.setVisibility(8);
        }
        holder.title.setText(item.title);
        holder.title.setTextColor(Color.parseColor(this.settingConfig.mineListTextColor));
        holder.gotoMarkBtn.setImageResource(this.settingConfig.mineListItemJumpIcon);
        holder.itemView.setOnClickListener(v -> {
            if (this.onItemClickListener != null) {
                this.onItemClickListener.itemClick(position, item);
            }
        });
    }
    @Override
    public int getItemCount() {
        if (this.settingBeans != null) {
            return this.settingBeans.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setIdentityAuthentication(boolean identityAuthentication) {
        this.isIdentityAuthentication = identityAuthentication;
    }

    /* loaded from: classes.jar:ps/center/application/mine/SettingListAdapter$SettingViewHolder.class */
    public static class SettingViewHolder extends RecyclerView.ViewHolder {
        private final ImageView icon;
        private final TextView title;
        private final TextView statusText;
        private final ImageView gotoMarkBtn;

        public SettingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.statusText = (TextView) itemView.findViewById(R.id.statusText);
            this.gotoMarkBtn = (ImageView) itemView.findViewById(R.id.gotoMarkBtn);
        }
    }
}