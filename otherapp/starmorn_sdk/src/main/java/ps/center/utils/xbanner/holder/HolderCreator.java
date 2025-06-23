package ps.center.utils.xbanner.holder;

/* loaded from: classes.jar:ps/center/utils/xbanner/holder/HolderCreator.class */
public interface HolderCreator<VH extends ViewHolder> {
    VH createViewHolder(int i);

    int getViewType(int i);
}