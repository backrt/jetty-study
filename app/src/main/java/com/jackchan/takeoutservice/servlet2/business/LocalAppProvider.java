package com.jackchan.takeoutservice.servlet2.business;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.jackchan.takeoutservice.servlet2.proper.LocalAppProper;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：lhb on 2017/10/23 16:41
 * Mail：lihaibo@znds.com
 */

public class LocalAppProvider {

    public static List<LocalAppProper> getLocalUninstalledApps(Context context) {

        if (null == context)
            return null;

        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null)
            return null;

        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        if (packageInfoList == null
                || packageInfoList.isEmpty())
            return null;

        List<LocalAppProper> properList = new ArrayList<>();
        for (PackageInfo packageInfo : packageInfoList) {

            LocalAppProper proper = new LocalAppProper();

            // init propers
            proper.setAppname((String) packageInfo.applicationInfo.loadLabel(packageManager));
            proper.setVercode(packageInfo.versionCode);
            proper.setVername(packageInfo.versionName);
            proper.setUrl(packageInfo.applicationInfo.publicSourceDir);
            proper.setPackagename(packageInfo.packageName);
            proper.setDrawable(packageInfo.applicationInfo.loadIcon(packageManager));

            properList.add(proper);
        }

        packageInfoList.clear();
        return properList;
    }


    public static LocalAppProper getLocalUninstalledApp(Context context, String packageName) {

        if (context == null)
            return null;


        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null)
            return null;

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);

            LocalAppProper proper = new LocalAppProper();

            // init propers
            proper.setAppname((String) packageInfo.applicationInfo.loadLabel(packageManager));
            proper.setVercode(packageInfo.versionCode);
            proper.setVername(packageInfo.versionName);
            proper.setUrl(packageInfo.applicationInfo.publicSourceDir);
            proper.setPackagename(packageInfo.packageName);

            return proper;

        } catch (PackageManager.NameNotFoundException e) {
        }

        return null;
    }

}
