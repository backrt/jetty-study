package com.jackchan.takeoutservice.utils;

import android.content.Context;

import java.io.File;

/**
 * Author：lhb on 2017/11/4 16:45
 * Mail：lihaibo@znds.com
 */

public class FileUtil {

    public static String getAppFileDir(Context context, String cacheDir) {

        return context.getCacheDir().getAbsolutePath() + File.separator + cacheDir;
    }

    public static String generateFileName(String packageName, String fileExtension) {
        return packageName + "." + fileExtension;
    }

}
