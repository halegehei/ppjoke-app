package ps.center.views.layout.bottomLayout;

import android.graphics.Color;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
public class BottomLayoutManager {
    private CoordinatorLayout coordinatorLayout;
    private View contentLayout;
    private boolean isAlphaBack = false;
    private boolean isBackCancel = false;
    private boolean isBackClick = false;

    public BottomLayoutManager(CoordinatorLayout coordinatorLayout, View contentLayout) {
        this.coordinatorLayout = coordinatorLayout;
        this.contentLayout = contentLayout;
    }

    public void isAlphaBack(boolean isAlphaBack) {
        this.isAlphaBack = isAlphaBack;
    }

    private void init() {
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(this.contentLayout);
        behavior.setHideable(false);
        behavior.setDraggable(true);
        behavior.setPeekHeight(0);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: ps.center.views.layout.bottomLayout.BottomLayoutManager.1
            float viewY = -1.0f;
            float height = -1.0f;

            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (this.viewY == -1.0f) {
                    this.viewY = bottomSheet.getY();
                    this.height = bottomSheet.getHeight();
                }
            }

            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (BottomLayoutManager.this.isAlphaBack) {
                    float num = this.viewY - bottomSheet.getY();
                    float v = (num / this.height) * 179.0f;
                    BottomLayoutManager.this.coordinatorLayout.setBackgroundColor(Color.HSVToColor((int) v, new float[]{0.0f, 0.0f, 0.0f}));
                }
            }
        });
    }
}