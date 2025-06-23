package com.litemob.screentogetherpink.ui.bean;

public class Ability {
    public AbilityBean ability;

    public static class AbilityBean {
        public UsedBean used;

        public static class UsedBean {
            public CommBean comm;
            public FuncBean func;

        }

        public static class CommBean {
            public String is_active;
            public String conf_name;
        }

        public static class FuncBean {
            public String licence_url;
            public String licence_key;
        }
    }
}
