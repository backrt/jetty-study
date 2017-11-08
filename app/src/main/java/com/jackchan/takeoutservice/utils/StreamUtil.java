package com.jackchan.takeoutservice.utils;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author：lhb on 2017/11/8 10:02
 * Mail：lihaibo@znds.com
 */

public class StreamUtil {

    private static final int FILE_BUFFER_SIZE = 1024;


    public static void fileStream(InputStream inputStream, OutputStream outputStream) {

        try {
            int count = -1;
            byte[] buffer = new byte[FILE_BUFFER_SIZE];
            while ((count = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
                outputStream.flush();
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {

        }
    }

}
