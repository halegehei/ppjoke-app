package ps.center.utils.exception;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ps.center.R;
import ps.center.application.exception.ExceptionLogsBean;

/* loaded from: classes.jar:ps/center/utils/exception/BugsListAdapter.class */
public class BugsListAdapter extends RecyclerView.Adapter<BugsListAdapter.ViewHolder> {
    private List<ExceptionLogsBean.ExceptionInfo> busData;
    private Context context;

    public BugsListAdapter(Context context, List<ExceptionLogsBean.ExceptionInfo> busData) {
        this.busData = busData;
        this.context = context;
    }

    @Override
    @NonNull
    /* renamed from: onCreateViewHolder, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.business_item_exception_event_content_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bugContent.setText(String.format("在%s分发生错误", this.busData.get(position).time));
        holder.bugContent.setOnClickListener(v -> {
            Intent intent = new Intent(this.context, (Class<?>) ExceptionDetailsActivity.class);
            intent.putExtra("content", this.busData.get(position).content);
            this.context.startActivity(intent);
        });
    }

    public int getItemCount() {
        return this.busData.size();
    }

    /* loaded from: classes.jar:ps/center/utils/exception/BugsListAdapter$ViewHolder.class */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView bugContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bugContent = (TextView) itemView.findViewById(R.id.bugContent);
        }
    }
}