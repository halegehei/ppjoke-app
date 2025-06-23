package ps.center.business.bean.config;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.jar:ps/center/business/bean/config/AppConfig.class */
public class AppConfig implements Parcelable {
    public ConfigBean config;
    public StandardConfBean standard_conf;
    public static final Creator<AppConfig> CREATOR = new Creator<AppConfig>() { // from class: ps.center.business.bean.config.AppConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppConfig createFromParcel(Parcel in) {
            return new AppConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppConfig[] newArray(int size) {
            return new AppConfig[size];
        }
    };

    public AppConfig() {
    }

    /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$ConfigBean.class */
    public static class ConfigBean implements Parcelable {
        public String app_name;
        public String package_name;
        public String app_version;
        public String app_icon;
        public WechatBean wechat;
        public UmengBean umeng;
        public LoginOnekeyBean login_onekey;
        public CsjplatformBean csjplatform;
        public TdBean td;
        public JiguangBean jiguang;
        public static final Creator<ConfigBean> CREATOR = new Creator<ConfigBean>() { // from class: ps.center.business.bean.config.AppConfig.ConfigBean.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ConfigBean createFromParcel(Parcel in) {
                return new ConfigBean(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ConfigBean[] newArray(int size) {
                return new ConfigBean[size];
            }
        };

        public ConfigBean() {
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$ConfigBean$WechatBean.class */
        public static class WechatBean implements Parcelable {
            public String wechat_appid;
            public String wechat_secret;
            public String universal_links;
            public static final Creator<WechatBean> CREATOR = new Creator<WechatBean>() { // from class: ps.center.business.bean.config.AppConfig.ConfigBean.WechatBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public WechatBean createFromParcel(Parcel in) {
                    return new WechatBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public WechatBean[] newArray(int size) {
                    return new WechatBean[size];
                }
            };

            public WechatBean() {
            }

            protected WechatBean(Parcel in) {
                this.wechat_appid = in.readString();
                this.wechat_secret = in.readString();
                this.universal_links = in.readString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.wechat_appid);
                dest.writeString(this.wechat_secret);
                dest.writeString(this.universal_links);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$ConfigBean$UmengBean.class */
        public static class UmengBean implements Parcelable {
            public String umeng_appid;
            public static final Creator<UmengBean> CREATOR = new Creator<UmengBean>() { // from class: ps.center.business.bean.config.AppConfig.ConfigBean.UmengBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public UmengBean createFromParcel(Parcel in) {
                    return new UmengBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public UmengBean[] newArray(int size) {
                    return new UmengBean[size];
                }
            };

            public UmengBean() {
            }

            protected UmengBean(Parcel in) {
                this.umeng_appid = in.readString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.umeng_appid);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$ConfigBean$LoginOnekeyBean.class */
        public static class LoginOnekeyBean implements Parcelable {
            public String loginonekey_key;
            public static final Creator<LoginOnekeyBean> CREATOR = new Creator<LoginOnekeyBean>() { // from class: ps.center.business.bean.config.AppConfig.ConfigBean.LoginOnekeyBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public LoginOnekeyBean createFromParcel(Parcel in) {
                    return new LoginOnekeyBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public LoginOnekeyBean[] newArray(int size) {
                    return new LoginOnekeyBean[size];
                }
            };

            public LoginOnekeyBean() {
            }

            protected LoginOnekeyBean(Parcel in) {
                this.loginonekey_key = in.readString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.loginonekey_key);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$ConfigBean$CsjplatformBean.class */
        public static class CsjplatformBean implements Parcelable {
            public String csj_appid;
            public String csj_kaip;
            public String csj_banner;
            public String csj_xxl;
            public String csj_jili;
            public String csj_chap;
            public String csj_juhebanner;
            public String csj_juhexxl;
            public String csj_juhejili;
            public String csj_juhechap;
            public String csj_juhekaip;
            public static final Creator<CsjplatformBean> CREATOR = new Creator<CsjplatformBean>() { // from class: ps.center.business.bean.config.AppConfig.ConfigBean.CsjplatformBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public CsjplatformBean createFromParcel(Parcel in) {
                    return new CsjplatformBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public CsjplatformBean[] newArray(int size) {
                    return new CsjplatformBean[size];
                }
            };

            public CsjplatformBean() {
            }

            protected CsjplatformBean(Parcel in) {
                this.csj_appid = in.readString();
                this.csj_kaip = in.readString();
                this.csj_banner = in.readString();
                this.csj_xxl = in.readString();
                this.csj_jili = in.readString();
                this.csj_chap = in.readString();
                this.csj_juhebanner = in.readString();
                this.csj_juhexxl = in.readString();
                this.csj_juhejili = in.readString();
                this.csj_juhechap = in.readString();
                this.csj_juhekaip = in.readString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.csj_appid);
                dest.writeString(this.csj_kaip);
                dest.writeString(this.csj_banner);
                dest.writeString(this.csj_xxl);
                dest.writeString(this.csj_jili);
                dest.writeString(this.csj_chap);
                dest.writeString(this.csj_juhebanner);
                dest.writeString(this.csj_juhexxl);
                dest.writeString(this.csj_juhejili);
                dest.writeString(this.csj_juhechap);
                dest.writeString(this.csj_juhekaip);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$ConfigBean$TdBean.class */
        public static class TdBean implements Parcelable {
            public String td_appid;
            public String td_lingdo;
            public static final Creator<TdBean> CREATOR = new Creator<TdBean>() { // from class: ps.center.business.bean.config.AppConfig.ConfigBean.TdBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public TdBean createFromParcel(Parcel in) {
                    return new TdBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public TdBean[] newArray(int size) {
                    return new TdBean[size];
                }
            };

            public TdBean() {
            }

            protected TdBean(Parcel in) {
                this.td_appid = in.readString();
                this.td_lingdo = in.readString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.td_appid);
                dest.writeString(this.td_lingdo);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$ConfigBean$JiguangBean.class */
        public static class JiguangBean implements Parcelable {
            public String jiguang_key;
            public String jiguang_mstsecret;
            public static final Creator<JiguangBean> CREATOR = new Creator<JiguangBean>() { // from class: ps.center.business.bean.config.AppConfig.ConfigBean.JiguangBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public JiguangBean createFromParcel(Parcel in) {
                    return new JiguangBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public JiguangBean[] newArray(int size) {
                    return new JiguangBean[size];
                }
            };

            public JiguangBean() {
            }

            protected JiguangBean(Parcel in) {
                this.jiguang_key = in.readString();
                this.jiguang_mstsecret = in.readString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.jiguang_key);
                dest.writeString(this.jiguang_mstsecret);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        protected ConfigBean(Parcel in) {
            this.app_name = in.readString();
            this.package_name = in.readString();
            this.app_version = in.readString();
            this.app_icon = in.readString();
            this.wechat = (WechatBean) in.readParcelable(WechatBean.class.getClassLoader());
            this.umeng = (UmengBean) in.readParcelable(UmengBean.class.getClassLoader());
            this.login_onekey = (LoginOnekeyBean) in.readParcelable(LoginOnekeyBean.class.getClassLoader());
            this.csjplatform = (CsjplatformBean) in.readParcelable(CsjplatformBean.class.getClassLoader());
            this.td = (TdBean) in.readParcelable(TdBean.class.getClassLoader());
            this.jiguang = (JiguangBean) in.readParcelable(JiguangBean.class.getClassLoader());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.app_name);
            dest.writeString(this.package_name);
            dest.writeString(this.app_version);
            dest.writeString(this.app_icon);
            dest.writeParcelable(this.wechat, flags);
            dest.writeParcelable(this.umeng, flags);
            dest.writeParcelable(this.login_onekey, flags);
            dest.writeParcelable(this.csjplatform, flags);
            dest.writeParcelable(this.td, flags);
            dest.writeParcelable(this.jiguang, flags);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }

    /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean.class */
    public static class StandardConfBean implements Parcelable {
        public LoginModeBean login_mode;
        public AdSwitchBean ad_switch;
        public CustomerServiceBean customer_service;
        public AgreementContentBean agreement_content;
        public UpdatedBean updated;
        public PayPageBean pay_page;
        public FreeControlBean free_control;
        public PremissionListBean premission_list;
        public GuidePageBean guide_page;
        public ShareControlBean share_control;
        public RatingPopBean rating_pop;
        public ComplaintControlBean complaint_control;
        public static final Creator<StandardConfBean> CREATOR = new Creator<StandardConfBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public StandardConfBean createFromParcel(Parcel in) {
                return new StandardConfBean(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public StandardConfBean[] newArray(int size) {
                return new StandardConfBean[size];
            }
        };

        public StandardConfBean() {
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$LoginModeBean.class */
        public static class LoginModeBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<LoginModeBean> CREATOR = new Creator<LoginModeBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.LoginModeBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public LoginModeBean createFromParcel(Parcel in) {
                    return new LoginModeBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public LoginModeBean[] newArray(int size) {
                    return new LoginModeBean[size];
                }
            };

            public LoginModeBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$LoginModeBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.LoginModeBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$LoginModeBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String mode;
                public String is_check;
                public String forced;
                public String pay_login_pop;
                public String pay_login;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.LoginModeBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.mode = in.readString();
                    this.is_check = in.readString();
                    this.forced = in.readString();
                    this.pay_login_pop = in.readString();
                    this.pay_login = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.mode);
                    dest.writeString(this.is_check);
                    dest.writeString(this.forced);
                    dest.writeString(this.pay_login_pop);
                    dest.writeString(this.pay_login);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected LoginModeBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$AdSwitchBean.class */
        public static class AdSwitchBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<AdSwitchBean> CREATOR = new Creator<AdSwitchBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.AdSwitchBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public AdSwitchBean createFromParcel(Parcel in) {
                    return new AdSwitchBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public AdSwitchBean[] newArray(int size) {
                    return new AdSwitchBean[size];
                }
            };

            public AdSwitchBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$AdSwitchBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.AdSwitchBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$AdSwitchBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String kaiping;
                public String banner;
                public String xxl;
                public String chaping;
                public String jili_video;
                public String countdown_time;
                public String pay_kaiping_sw;
                public String custom_kaiping_sw;
                public String custom_condition_num;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.AdSwitchBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.kaiping = in.readString();
                    this.banner = in.readString();
                    this.xxl = in.readString();
                    this.chaping = in.readString();
                    this.jili_video = in.readString();
                    this.countdown_time = in.readString();
                    this.pay_kaiping_sw = in.readString();
                    this.custom_kaiping_sw = in.readString();
                    this.custom_condition_num = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.kaiping);
                    dest.writeString(this.banner);
                    dest.writeString(this.xxl);
                    dest.writeString(this.chaping);
                    dest.writeString(this.jili_video);
                    dest.writeString(this.countdown_time);
                    dest.writeString(this.pay_kaiping_sw);
                    dest.writeString(this.custom_kaiping_sw);
                    dest.writeString(this.custom_condition_num);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected AdSwitchBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$CustomerServiceBean.class */
        public static class CustomerServiceBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<CustomerServiceBean> CREATOR = new Creator<CustomerServiceBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.CustomerServiceBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public CustomerServiceBean createFromParcel(Parcel in) {
                    return new CustomerServiceBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public CustomerServiceBean[] newArray(int size) {
                    return new CustomerServiceBean[size];
                }
            };

            public CustomerServiceBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$CustomerServiceBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.CustomerServiceBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$CustomerServiceBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String online_sw;
                public String pop_sw;
                public String online_service_info;
                public String customer_serv;
                public String is_vip;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.CustomerServiceBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.online_sw = in.readString();
                    this.pop_sw = in.readString();
                    this.online_service_info = in.readString();
                    this.customer_serv = in.readString();
                    this.is_vip = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.online_sw);
                    dest.writeString(this.pop_sw);
                    dest.writeString(this.online_service_info);
                    dest.writeString(this.customer_serv);
                    dest.writeString(this.is_vip);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected CustomerServiceBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$AgreementContentBean.class */
        public static class AgreementContentBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<AgreementContentBean> CREATOR = new Creator<AgreementContentBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.AgreementContentBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public AgreementContentBean createFromParcel(Parcel in) {
                    return new AgreementContentBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public AgreementContentBean[] newArray(int size) {
                    return new AgreementContentBean[size];
                }
            };

            public AgreementContentBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$AgreementContentBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.AgreementContentBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$AgreementContentBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String privacy_policy;
                public String user_agreement;
                public String pay_agreement;
                public String auto_renew;
                public String disclaimers;
                public String copyright_notice;
                public String permission_list;
                public String beian_id;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.AgreementContentBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.privacy_policy = in.readString();
                    this.user_agreement = in.readString();
                    this.pay_agreement = in.readString();
                    this.auto_renew = in.readString();
                    this.disclaimers = in.readString();
                    this.copyright_notice = in.readString();
                    this.permission_list = in.readString();
                    this.beian_id = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.privacy_policy);
                    dest.writeString(this.user_agreement);
                    dest.writeString(this.pay_agreement);
                    dest.writeString(this.auto_renew);
                    dest.writeString(this.disclaimers);
                    dest.writeString(this.copyright_notice);
                    dest.writeString(this.permission_list);
                    dest.writeString(this.beian_id);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected AgreementContentBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$UpdatedBean.class */
        public static class UpdatedBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<UpdatedBean> CREATOR = new Creator<UpdatedBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.UpdatedBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public UpdatedBean createFromParcel(Parcel in) {
                    return new UpdatedBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public UpdatedBean[] newArray(int size) {
                    return new UpdatedBean[size];
                }
            };

            public UpdatedBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$UpdatedBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.UpdatedBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$UpdatedBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String app_link;
                public String forced;
                public String content;
                public String is_vip;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.UpdatedBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.app_link = in.readString();
                    this.forced = in.readString();
                    this.content = in.readString();
                    this.is_vip = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.app_link);
                    dest.writeString(this.forced);
                    dest.writeString(this.content);
                    dest.writeString(this.is_vip);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected UpdatedBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$PayPageBean.class */
        public static class PayPageBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<PayPageBean> CREATOR = new Creator<PayPageBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.PayPageBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PayPageBean createFromParcel(Parcel in) {
                    return new PayPageBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PayPageBean[] newArray(int size) {
                    return new PayPageBean[size];
                }
            };

            public PayPageBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$PayPageBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.PayPageBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$PayPageBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String exit_btn;
                public String return_delay_time;
                public String popup_pic;
                public String pay_page1;
                public String pay_page2;
                public String pay_page3;
                public String pay_element1;
                public String pay_element2;
                public String retain_sw;
                public String prepay_sw;
                public String backpay_sw;
                public String pay_retain_sw;
                public String pay_pop_up;
                public String pay_pop_up_txt;
                public String pay_page_switch;
                public String popup_switch;
                public List<String> paypage_rotation;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.PayPageBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.exit_btn = in.readString();
                    this.return_delay_time = in.readString();
                    this.popup_pic = in.readString();
                    this.pay_page1 = in.readString();
                    this.pay_page2 = in.readString();
                    this.pay_page3 = in.readString();
                    this.pay_element1 = in.readString();
                    this.pay_element2 = in.readString();
                    this.retain_sw = in.readString();
                    this.prepay_sw = in.readString();
                    this.backpay_sw = in.readString();
                    this.pay_retain_sw = in.readString();
                    this.pay_pop_up = in.readString();
                    this.pay_pop_up_txt = in.readString();
                    this.pay_page_switch = in.readString();
                    this.popup_switch = in.readString();
                    this.paypage_rotation = in.createStringArrayList();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.exit_btn);
                    dest.writeString(this.return_delay_time);
                    dest.writeString(this.popup_pic);
                    dest.writeString(this.pay_page1);
                    dest.writeString(this.pay_page2);
                    dest.writeString(this.pay_page3);
                    dest.writeString(this.pay_element1);
                    dest.writeString(this.pay_element2);
                    dest.writeString(this.retain_sw);
                    dest.writeString(this.prepay_sw);
                    dest.writeString(this.backpay_sw);
                    dest.writeString(this.pay_retain_sw);
                    dest.writeString(this.pay_pop_up);
                    dest.writeString(this.pay_pop_up_txt);
                    dest.writeString(this.pay_page_switch);
                    dest.writeString(this.popup_switch);
                    dest.writeStringList(this.paypage_rotation);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected PayPageBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$FreeControlBean.class */
        public static class FreeControlBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<FreeControlBean> CREATOR = new Creator<FreeControlBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.FreeControlBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FreeControlBean createFromParcel(Parcel in) {
                    return new FreeControlBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FreeControlBean[] newArray(int size) {
                    return new FreeControlBean[size];
                }
            };

            public FreeControlBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$FreeControlBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.FreeControlBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$FreeControlBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String free_num_sw;
                public String free_show;
                public String free_num;
                public String free_time;
                public String free_time_sw;
                public String free_zdtry_sw;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.FreeControlBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.free_num_sw = in.readString();
                    this.free_show = in.readString();
                    this.free_num = in.readString();
                    this.free_time = in.readString();
                    this.free_time_sw = in.readString();
                    this.free_zdtry_sw = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.free_num_sw);
                    dest.writeString(this.free_show);
                    dest.writeString(this.free_num);
                    dest.writeString(this.free_time);
                    dest.writeString(this.free_time_sw);
                    dest.writeString(this.free_zdtry_sw);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected FreeControlBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$PremissionListBean.class */
        public static class PremissionListBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<PremissionListBean> CREATOR = new Creator<PremissionListBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.PremissionListBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PremissionListBean createFromParcel(Parcel in) {
                    return new PremissionListBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PremissionListBean[] newArray(int size) {
                    return new PremissionListBean[size];
                }
            };

            public PremissionListBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$PremissionListBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.PremissionListBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$PremissionListBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String permission_us;
                public String prtsc_sw;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.PremissionListBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.permission_us = in.readString();
                    this.prtsc_sw = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.permission_us);
                    dest.writeString(this.prtsc_sw);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected PremissionListBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$GuidePageBean.class */
        public static class GuidePageBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<GuidePageBean> CREATOR = new Creator<GuidePageBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.GuidePageBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public GuidePageBean createFromParcel(Parcel in) {
                    return new GuidePageBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public GuidePageBean[] newArray(int size) {
                    return new GuidePageBean[size];
                }
            };

            public GuidePageBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$GuidePageBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.GuidePageBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$GuidePageBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public List<GuideWithTypeBean> guide_with_type;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.GuidePageBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$GuidePageBean$FuncBean$GuideWithTypeBean.class */
                public static class GuideWithTypeBean implements Parcelable {
                    public String type;
                    public String data;
                    public static final Creator<GuideWithTypeBean> CREATOR = new Creator<GuideWithTypeBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.GuidePageBean.FuncBean.GuideWithTypeBean.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // android.os.Parcelable.Creator
                        public GuideWithTypeBean createFromParcel(Parcel in) {
                            return new GuideWithTypeBean(in);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // android.os.Parcelable.Creator
                        public GuideWithTypeBean[] newArray(int size) {
                            return new GuideWithTypeBean[size];
                        }
                    };

                    public GuideWithTypeBean() {
                    }

                    protected GuideWithTypeBean(Parcel in) {
                        this.type = in.readString();
                        this.data = in.readString();
                    }

                    @Override // android.os.Parcelable
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(this.type);
                        dest.writeString(this.data);
                    }

                    @Override // android.os.Parcelable
                    public int describeContents() {
                        return 0;
                    }
                }

                protected FuncBean(Parcel in) {
                    this.guide_with_type = in.createTypedArrayList(GuideWithTypeBean.CREATOR);
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeTypedList(this.guide_with_type);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected GuidePageBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$ShareControlBean.class */
        public static class ShareControlBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<ShareControlBean> CREATOR = new Creator<ShareControlBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.ShareControlBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public ShareControlBean createFromParcel(Parcel in) {
                    return new ShareControlBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public ShareControlBean[] newArray(int size) {
                    return new ShareControlBean[size];
                }
            };

            public ShareControlBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$ShareControlBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.ShareControlBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$ShareControlBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String share_sw;
                public String share_id;
                public String is_vip;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.ShareControlBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.share_sw = in.readString();
                    this.share_id = in.readString();
                    this.is_vip = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.share_sw);
                    dest.writeString(this.share_id);
                    dest.writeString(this.is_vip);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected ShareControlBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$RatingPopBean.class */
        public static class RatingPopBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<RatingPopBean> CREATOR = new Creator<RatingPopBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.RatingPopBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public RatingPopBean createFromParcel(Parcel in) {
                    return new RatingPopBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public RatingPopBean[] newArray(int size) {
                    return new RatingPopBean[size];
                }
            };

            public RatingPopBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$RatingPopBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.RatingPopBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$RatingPopBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String score_sw;
                public String update_cond1;
                public String update_cond2;
                public String is_vip;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.RatingPopBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.score_sw = in.readString();
                    this.update_cond1 = in.readString();
                    this.update_cond2 = in.readString();
                    this.is_vip = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.score_sw);
                    dest.writeString(this.update_cond1);
                    dest.writeString(this.update_cond2);
                    dest.writeString(this.is_vip);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected RatingPopBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$ComplaintControlBean.class */
        public static class ComplaintControlBean implements Parcelable {
            public CommBean comm;
            public FuncBean func;
            public static final Creator<ComplaintControlBean> CREATOR = new Creator<ComplaintControlBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.ComplaintControlBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public ComplaintControlBean createFromParcel(Parcel in) {
                    return new ComplaintControlBean(in);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public ComplaintControlBean[] newArray(int size) {
                    return new ComplaintControlBean[size];
                }
            };

            public ComplaintControlBean() {
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$ComplaintControlBean$CommBean.class */
            public static class CommBean implements Parcelable {
                public String is_active;
                public String conf_name;
                public static final Creator<CommBean> CREATOR = new Creator<CommBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.ComplaintControlBean.CommBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean createFromParcel(Parcel in) {
                        return new CommBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public CommBean[] newArray(int size) {
                        return new CommBean[size];
                    }
                };

                public CommBean() {
                }

                protected CommBean(Parcel in) {
                    this.is_active = in.readString();
                    this.conf_name = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.is_active);
                    dest.writeString(this.conf_name);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            /* loaded from: classes.jar:ps/center/business/bean/config/AppConfig$StandardConfBean$ComplaintControlBean$FuncBean.class */
            public static class FuncBean implements Parcelable {
                public String compain_sw;
                public String compain_url;
                public String compain_icon;
                public String tip_off;
                public String authentication_sw;
                public static final Creator<FuncBean> CREATOR = new Creator<FuncBean>() { // from class: ps.center.business.bean.config.AppConfig.StandardConfBean.ComplaintControlBean.FuncBean.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean createFromParcel(Parcel in) {
                        return new FuncBean(in);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public FuncBean[] newArray(int size) {
                        return new FuncBean[size];
                    }
                };

                public FuncBean() {
                }

                protected FuncBean(Parcel in) {
                    this.compain_sw = in.readString();
                    this.compain_url = in.readString();
                    this.compain_icon = in.readString();
                    this.tip_off = in.readString();
                    this.authentication_sw = in.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.compain_sw);
                    dest.writeString(this.compain_url);
                    dest.writeString(this.compain_icon);
                    dest.writeString(this.tip_off);
                    dest.writeString(this.authentication_sw);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }
            }

            protected ComplaintControlBean(Parcel in) {
                this.comm = (CommBean) in.readParcelable(CommBean.class.getClassLoader());
                this.func = (FuncBean) in.readParcelable(FuncBean.class.getClassLoader());
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.comm, flags);
                dest.writeParcelable(this.func, flags);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }
        }

        protected StandardConfBean(Parcel in) {
            this.login_mode = (LoginModeBean) in.readParcelable(LoginModeBean.class.getClassLoader());
            this.ad_switch = (AdSwitchBean) in.readParcelable(AdSwitchBean.class.getClassLoader());
            this.customer_service = (CustomerServiceBean) in.readParcelable(CustomerServiceBean.class.getClassLoader());
            this.agreement_content = (AgreementContentBean) in.readParcelable(AgreementContentBean.class.getClassLoader());
            this.updated = (UpdatedBean) in.readParcelable(UpdatedBean.class.getClassLoader());
            this.pay_page = (PayPageBean) in.readParcelable(PayPageBean.class.getClassLoader());
            this.free_control = (FreeControlBean) in.readParcelable(FreeControlBean.class.getClassLoader());
            this.premission_list = (PremissionListBean) in.readParcelable(PremissionListBean.class.getClassLoader());
            this.guide_page = (GuidePageBean) in.readParcelable(GuidePageBean.class.getClassLoader());
            this.share_control = (ShareControlBean) in.readParcelable(ShareControlBean.class.getClassLoader());
            this.rating_pop = (RatingPopBean) in.readParcelable(RatingPopBean.class.getClassLoader());
            this.complaint_control = (ComplaintControlBean) in.readParcelable(ComplaintControlBean.class.getClassLoader());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.login_mode, flags);
            dest.writeParcelable(this.ad_switch, flags);
            dest.writeParcelable(this.customer_service, flags);
            dest.writeParcelable(this.agreement_content, flags);
            dest.writeParcelable(this.updated, flags);
            dest.writeParcelable(this.pay_page, flags);
            dest.writeParcelable(this.free_control, flags);
            dest.writeParcelable(this.premission_list, flags);
            dest.writeParcelable(this.guide_page, flags);
            dest.writeParcelable(this.share_control, flags);
            dest.writeParcelable(this.rating_pop, flags);
            dest.writeParcelable(this.complaint_control, flags);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }

    protected AppConfig(Parcel in) {
        this.config = (ConfigBean) in.readParcelable(ConfigBean.class.getClassLoader());
        this.standard_conf = (StandardConfBean) in.readParcelable(StandardConfBean.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.config, flags);
        dest.writeParcelable(this.standard_conf, flags);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}