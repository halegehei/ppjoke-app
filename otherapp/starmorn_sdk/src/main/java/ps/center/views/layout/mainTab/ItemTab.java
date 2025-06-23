package ps.center.views.layout.mainTab;

/* loaded from: classes.jar:ps/center/views/layout/mainTab/ItemTab.class */
public class ItemTab implements BaseItemTab {
    private final int imageIdOn;
    private final int imageIdOff;
    private final String title;

    public ItemTab(int imageIdOn, int imageIdOff, String title) {
        this.imageIdOn = imageIdOn;
        this.imageIdOff = imageIdOff;
        this.title = title;
    }

    @Override // ps.center.views.layout.mainTab.BaseItemTab
    public int getImageIdOn() {
        return this.imageIdOn;
    }

    @Override // ps.center.views.layout.mainTab.BaseItemTab
    public int getImageIdOff() {
        return this.imageIdOff;
    }

    @Override // ps.center.views.layout.mainTab.BaseItemTab
    public String getTitle() {
        return this.title;
    }
}