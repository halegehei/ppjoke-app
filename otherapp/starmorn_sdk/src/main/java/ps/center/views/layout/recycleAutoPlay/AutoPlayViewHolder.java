package ps.center.views.layout.recycleAutoPlay;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ps.center.R;

/* loaded from: classes.jar:ps/center/views/layout/recycleAutoPlay/AutoPlayViewHolder.class */
public class AutoPlayViewHolder extends RecyclerView.ViewHolder {
    public RelativeLayout rootLayout;
    public ImageView logo;
    public TextView title;

    public AutoPlayViewHolder(@NonNull View itemView) {
        super(itemView);
        this.rootLayout = (RelativeLayout) itemView.findViewById(R.id.itemLayout);
        this.logo = (ImageView) itemView.findViewById(R.id.logo);
        this.title = (TextView) itemView.findViewById(R.id.title);
    }
}