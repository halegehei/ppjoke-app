package ps.center.utils;

import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/ChannelUtils.class */
public class ChannelUtils {
    public static boolean isStorePacket() {
        String store = ManifestUtils.getMetaDataValue(Super.getContext(), "PACKET", BuildConfig.VERSION_NAME);
        switch (store) {
            case "xiaomi":
            case "yyb":
            case "vivo":
            case "oppo":
            case "huawei":
            case "store":
            case "store360":
            case "baidustore":
            case "meizu":
            case "rongyao":
            case "default":
                return true;
            default:
                return false;
        }
    }

    public static boolean isBaiDuPacket() {
        String store = ManifestUtils.getMetaDataValue(Super.getContext(), "PACKET", BuildConfig.VERSION_NAME);
        switch (store) {
            case "bdds":
            case "cjhc":
                return true;
            default:
                return false;
        }
    }
}