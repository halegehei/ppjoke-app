package ps.center.views.layout.mainTab;

import android.content.Context;
import android.widget.FrameLayout;
import androidx.annotation.CheckResult;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.jar:ps/center/views/layout/mainTab/MainTab.class */
public class MainTab {
    public Builder builder;

    public MainTab(Builder builder) {
        this.builder = builder;
    }

    @CheckResult
    public static Builder create(Context context) {
        return new Builder(context);
    }

    /* loaded from: classes.jar:ps/center/views/layout/mainTab/MainTab$Builder.class */
    public static final class Builder {
        private Context context;
        public FrameLayout rootLayout;
        public List<ItemTab> items;
        public boolean isCenter;
        public ItemAnimationMode itemAnimationMode;

        public Builder(Context context) {
            this.context = context;
        }

        @CheckResult
        public Builder addItem(ItemTab itemTab) {
            if (this.items == null) {
                this.items = new ArrayList();
            }
            this.items.add(itemTab);
            return this;
        }

        @CheckResult
        public Builder setAnimation(ItemAnimationMode itemAnimationMode) {
            this.itemAnimationMode = itemAnimationMode;
            return this;
        }

        @CheckResult
        public Builder bindLayout(FrameLayout rootLayout) {
            this.rootLayout = rootLayout;
            return this;
        }

        @CheckResult
        public Builder isCenter(boolean isCenter) {
            this.isCenter = isCenter;
            return this;
        }

        public MainTab build() {
            return new MainTab(this);
        }
    }
}