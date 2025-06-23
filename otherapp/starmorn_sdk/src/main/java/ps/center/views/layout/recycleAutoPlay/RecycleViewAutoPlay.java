package ps.center.views.layout.recycleAutoPlay;

import android.app.Activity;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* loaded from: classes.jar:ps/center/views/layout/recycleAutoPlay/RecycleViewAutoPlay.class */
public class RecycleViewAutoPlay {
    private Activity activity;
    private RecyclerView recyclerView;
    private List<? extends AutoPlayItem> listData;
    private List<? extends AutoPlayUrlItem> urlListData;
    private LayoutOutListener layoutOutListener;
    private LayoutViewListener layoutViewListener;
    private int maxMove;
    private int duration;

    public RecycleViewAutoPlay(Activity activity, RecyclerView recyclerView, List<? extends AutoPlayItem> listData, LayoutOutListener layoutOutListener, int duration, int maxMove) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        this.listData = listData;
        this.layoutOutListener = layoutOutListener;
        this.duration = duration;
        this.maxMove = maxMove;
        RecycleViewAutoPlayAdapter autoPlayBeanRecycleViewAutoPlayAdapter = new RecycleViewAutoPlayAdapter(activity, listData, layoutOutListener);
        recyclerView.setAdapter(autoPlayBeanRecycleViewAutoPlayAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, 0, false));
        autoPlayBeanRecycleViewAutoPlayAdapter.notifyDataSetChanged();
    }

    public RecycleViewAutoPlay(Activity activity, RecyclerView recyclerView, List<? extends AutoPlayUrlItem> urlListData, LayoutViewListener layoutViewListener, int duration, int maxMove) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        this.urlListData = urlListData;
        this.layoutViewListener = layoutViewListener;
        this.duration = duration;
        this.maxMove = maxMove;
        RecycleViewAutoPlayAdapter autoPlayBeanRecycleViewAutoPlayAdapter = new RecycleViewAutoPlayAdapter(activity, urlListData, layoutViewListener);
        recyclerView.setAdapter(autoPlayBeanRecycleViewAutoPlayAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, 0, false));
        autoPlayBeanRecycleViewAutoPlayAdapter.notifyDataSetChanged();
    }

    public void run() {
        try {
            this.recyclerView.postDelayed(() -> {
                this.recyclerView.smoothScrollBy(this.maxMove, 0, new LinearInterpolator(), this.duration);
            }, 50L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}