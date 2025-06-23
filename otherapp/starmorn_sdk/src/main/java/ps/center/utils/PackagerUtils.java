package ps.center.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.jar:ps/center/utils/PackagerUtils.class */
public class PackagerUtils {
    private Context context;

    public PackagerUtils(Context context) {
        this.context = context;
    }

    public List<PackageInfo> getSystemPackages() {
        List<PackageInfo> packageInfoNew = new ArrayList<>();
        if (this.context != null) {
            List<PackageInfo> packageInfoList = this.context.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packageInfoList.size(); i++) {
                PackageInfo packageInfo = packageInfoList.get(i);
                ApplicationInfo appInfo = packageInfo.applicationInfo;
                if ((appInfo.flags & 1) != 0) {
                    packageInfoNew.add(packageInfo);
                }
            }
        }
        return packageInfoNew;
    }

    public List<PackageInfo> getUserPackages() {
        List<PackageInfo> packageInfoNew = new ArrayList<>();
        if (this.context != null) {
            List<PackageInfo> packageInfoList = this.context.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packageInfoList.size(); i++) {
                PackageInfo packageInfo = packageInfoList.get(i);
                ApplicationInfo appInfo = packageInfo.applicationInfo;
                if ((appInfo.flags & 1) == 0 && !appInfo.packageName.equals(this.context.getPackageName())) {
                    packageInfoNew.add(packageInfo);
                }
            }
        }
        return packageInfoNew;
    }
}