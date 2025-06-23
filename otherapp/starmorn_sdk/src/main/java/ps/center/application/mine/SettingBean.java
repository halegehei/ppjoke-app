package ps.center.application.mine;

/* loaded from: classes.jar:ps/center/application/mine/SettingBean.class */
public class SettingBean {
    public String title;
    public String name;
    public Object icon;
    public String url;

    public SettingBean(String title, String name, int icon) {
        this.title = title;
        this.name = name;
        this.icon = Integer.valueOf(icon);
    }

    public SettingBean(String title, String name, String url) {
        this.title = title;
        this.name = name;
        this.icon = url;
    }
}