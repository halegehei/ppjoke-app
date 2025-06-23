package ps.center.views.layout.recycleAutoPlay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ps.center.R;

/* loaded from: classes.jar:ps/center/views/layout/recycleAutoPlay/RecycleViewAutoPlayAdapter.class */
public class RecycleViewAutoPlayAdapter extends RecyclerView.Adapter<AutoPlayViewHolder> {
    private final Context context;
    private List<? extends AutoPlayItem> listData;
    private List<? extends AutoPlayUrlItem> urlListData;
    private LayoutOutListener layoutOutListener;
    private LayoutViewListener layoutViewListener;

    public RecycleViewAutoPlayAdapter(Context context, List<? extends AutoPlayItem> listData, LayoutOutListener layoutOutListener) {
        this.context = context;
        this.listData = listData;
        this.layoutOutListener = layoutOutListener;
    }

    public RecycleViewAutoPlayAdapter(Context context, List<? extends AutoPlayUrlItem> listData, LayoutViewListener layoutViewListener) {
        this.context = context;
        this.urlListData = listData;
        this.layoutViewListener = layoutViewListener;
    }

    @NonNull
    @Override
    public AutoPlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.ps_center_auto_play_list_layout, parent, false);
        return new AutoPlayViewHolder(inflate);
    }

    public void onBindViewHolder(@NonNull AutoPlayViewHolder holder, int position) {
        if (this.layoutOutListener != null) {
            int realPosition = position % this.listData.size();
            AutoPlayItem autoPlayItem = this.listData.get(realPosition);
            this.layoutOutListener.rootView(holder, autoPlayItem, realPosition, position);
        }
        if (this.layoutViewListener != null) {
            int realPosition2 = position % this.urlListData.size();
            AutoPlayUrlItem autoPlayItem2 = this.urlListData.get(realPosition2);
            this.layoutViewListener.rootView(holder, autoPlayItem2, realPosition2, position);
        }
    }

    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}