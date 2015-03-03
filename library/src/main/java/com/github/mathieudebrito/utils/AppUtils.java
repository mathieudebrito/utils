package com.github.mathieudebrito.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtils {

    public static String getVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "unknown";
        }
    }

    public static String getAppName(Context context) {
        PackageManager lPackageManager = context.getPackageManager();
        ApplicationInfo lApplicationInfo = null;
        try {
            lApplicationInfo = lPackageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
        }
        return (String) (lApplicationInfo != null ? lPackageManager.getApplicationLabel(lApplicationInfo) : "Unknown");
    }

    public static String getPackage(Context context) {
        return context.getPackageName();
    }

    public static boolean isRelease(Context context) {
        return !getPackage(context).contains("debug") && !getPackage(context).contains("alpha") && !getPackage(context).contains("beta");
    }
}
