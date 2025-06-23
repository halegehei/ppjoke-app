package ps.center.application.mine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ps.center.application.BuildConfig;
import ps.center.R;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.databinding.BusinessDialogDeviceBinding;
import ps.center.utils.ManifestUtils;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/mine/DeviceDialog.class */
public class DeviceDialog extends BaseDialogVB2<BusinessDialogDeviceBinding> {
    private Adapter adapter;
    private ArrayList<Model> listData;

    /* loaded from: classes.jar:ps/center/application/mine/DeviceDialog$OnItemClickListener.class */
    private interface OnItemClickListener {
        void itemClick(int i, Model model);
    }

    public DeviceDialog(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogDeviceBinding getLayout() {
        return BusinessDialogDeviceBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
        String business_sdk_version;
        try {
            business_sdk_version = ManifestUtils.getMetaDataValue(getContext(), "business_sdk_version");
        } catch (Exception e) {
            e.printStackTrace();
            business_sdk_version = "NONE";
        }
        this.listData = new ArrayList<>();
        this.listData.add(new Model(CenterConstant.UID, Save.instance.getInt(CenterConstant.UID, 0) + BuildConfig.VERSION_NAME));
        this.listData.add(new Model("oaid", Save.instance.getString("oaid", "null")));
        this.listData.add(new Model("imei", Save.instance.getString("imei", "null")));
        this.listData.add(new Model("mac", Save.instance.getString("mac", "null")));
        this.listData.add(new Model("version", Super.getSelfVersion()));
        this.listData.add(new Model("channel", ManifestUtils.getMetaDataValue(Super.getContext(), "PACKET", BuildConfig.VERSION_NAME)));
        this.listData.add(new Model("pkg", Super.getContext().getPackageName()));
        this.listData.add(new Model("sdk-v", business_sdk_version));
        this.adapter = new Adapter(getContext(), this.listData);
        ((BusinessDialogDeviceBinding) this.binding).list.setLayoutManager(new LinearLayoutManager(getContext()));
        ((BusinessDialogDeviceBinding) this.binding).list.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
        if (CenterConstant.getUser() != null) {
            this.listData.add(new Model("login", !CenterConstant.getUser().isSign ? "未登录" : "已登录"));
        }
        this.adapter.setOnItemClickListener((position, item) -> {
            copy(item.value);
            ToastUtils.show(getContext(), "已复制！");
            dismiss();
        });
        ((BusinessDialogDeviceBinding) this.binding).copyAll.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder();
            Iterator<Model> it = this.listData.iterator();
            while (it.hasNext()) {
                Model model = it.next();
                if (model.title != null && model.value != null) {
                    sb.append(model.title);
                    sb.append("=");
                    sb.append(model.value.equals(BuildConfig.VERSION_NAME) ? "null" : model.value);
                    sb.append("\n");
                }
            }
            copy(sb.toString());
            ToastUtils.show(getContext(), "已复制全部！");
            dismiss();
        });
    }

    public void copy(String str) {
        try {
            ClipboardManager clipboard = (ClipboardManager)
                    getContext().getSystemService(Context.CLIPBOARD_SERVICE);
           
            ClipData clipData = ClipData.newPlainText(null, str);
            clipboard.setPrimaryClip(clipData);
        } catch (Exception e) {
        }
    }

    /* loaded from: classes.jar:ps/center/application/mine/DeviceDialog$Model.class */
    private static class Model {
        public String title;
        public String value;

        public Model(String title, String value) {
            this.title = title;
            this.value = value;
        }
    }

    /* loaded from: classes.jar:ps/center/application/mine/DeviceDialog$Adapter.class */
    private static class Adapter extends RecyclerView.Adapter<DeviceViewHolder> {
        private final Context context;
        private final List<Model> models;
        private OnItemClickListener onItemClickListener;

        public Adapter(Context context, List<Model> models) {
            this.context = context;
            this.models = models;
        }


        @Override
        @NonNull
        public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.business_item_dialog_device, parent, false);
            return new DeviceViewHolder(itemView);
        }

        public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
            Model item = this.models.get(position);
            holder.title.setText(item.title);
            holder.value.setText((item.value == null || item.value.equals(BuildConfig.VERSION_NAME)) ? "null" : item.value);
            if (item.value == null || item.value.equals(BuildConfig.VERSION_NAME)) {
                holder.value.setBackgroundResource(R.drawable.business_item_dialog_device_right_err);
            } else {
                holder.value.setBackgroundResource(R.drawable.business_item_dialog_device_right);
            }
            holder.copy.setOnClickListener(v -> {
                if (this.onItemClickListener != null) {
                    this.onItemClickListener.itemClick(position, item);
                }
            });
        }

        public int getItemCount() {
            if (this.models != null) {
                return this.models.size();
            }
            return 0;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
    }

    /* loaded from: classes.jar:ps/center/application/mine/DeviceDialog$DeviceViewHolder.class */
    private static class DeviceViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView value;
        private final TextView copy;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.value = (TextView) itemView.findViewById(R.id.value);
            this.copy = (TextView) itemView.findViewById(R.id.copy);
        }
    }
}