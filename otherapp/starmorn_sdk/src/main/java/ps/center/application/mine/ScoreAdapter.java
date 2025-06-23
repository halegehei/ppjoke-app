package ps.center.application.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ps.center.R;

/* loaded from: classes.jar:ps/center/application/mine/ScoreAdapter.class */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
    private final Context context;
    private final List<ScoreStar> scoreStarList;
    private int starNum = 0;

    public ScoreAdapter(Context context, List<ScoreStar> scoreStarList) {
        this.context = context;
        this.scoreStarList = scoreStarList;
    }


    @Override
    @NonNull
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.business_item_score_star, parent, false);
        return new ScoreViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        if (position < this.starNum) {
            holder.star.setImageResource(R.mipmap.business_score_star_2);
        } else {
            holder.star.setImageResource(R.mipmap.business_score_star_1);
        }
    }
    @Override
    public int getItemCount() {
        if (this.scoreStarList != null) {
            return this.scoreStarList.size();
        }
        return 0;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
        notifyDataSetChanged();
    }

    public int getStarNum() {
        return this.starNum;
    }

    /* loaded from: classes.jar:ps/center/application/mine/ScoreAdapter$ScoreViewHolder.class */
    public static class ScoreViewHolder extends RecyclerView.ViewHolder {
        private final ImageView star;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            this.star = (ImageView) itemView.findViewById(R.id.star);
        }
    }
}