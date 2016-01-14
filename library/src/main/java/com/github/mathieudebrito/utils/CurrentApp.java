package com.github.mathieudebrito.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class CurrentApp {

    public static String getVersionName(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "unknown";
        }
    }

    public static String getName(Context context) {
        PackageManager lPackageManager = context.getPackageManager();
        ApplicationInfo lApplicationInfo = null;
        try {
            lApplicationInfo = lPackageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
        }
        return (String) (lApplicationInfo != null ? lPackageManager.getApplicationLabel(lApplicationInfo) : "Unknown");
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static boolean isRelease(Context context) {
        return !getPackageName(context).contains("debug") && !getPackageName(context).contains("alpha") && !getPackageName(context).contains("beta");
    }
}
