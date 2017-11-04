package com.jackchan.takeoutservice.imageServer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.jackchan.takeoutservice.utils.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author：lhb on 2017/11/4 16:37
 * Mail：lihaibo@znds.com
 */

public class ImageUtil {

    public static String drawableToFile(Context context, Drawable drawable, String packageName) {
        Bitmap bitmap = drawableToBitmap(drawable);
        if (bitmap == null) {
            return null;
        }

        return saveBitmap(context, bitmap, packageName);
    }


    /**
     * drawable 转换成bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();// 取drawable的长宽
        int height = drawable.getIntrinsicHeight();

        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;// 取drawable的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);// 建立对应bitmap
        Canvas canvas = new Canvas(bitmap);// 建立对应bitmap的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);// 把drawable内容画到画布中

        return bitmap;
    }


    public static String saveBitmap(Context context, Bitmap mBitmap, String packageName) {
        String savePath = FileUtil.getAppFileDir(context, "app_icon");
        File filePic;

        try {
            String fileName = FileUtil.generateFileName(packageName, "jpg");
            filePic = new File(savePath, fileName);
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            } else {
                return filePic.getAbsolutePath();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            return null;
        }

        return filePic.getAbsolutePath();
    }

}
