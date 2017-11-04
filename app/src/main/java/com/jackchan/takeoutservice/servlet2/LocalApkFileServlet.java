package com.jackchan.takeoutservice.servlet2;

import android.text.TextUtils;
import android.util.Log;

import com.jackchan.takeoutservice.App;
import com.jackchan.takeoutservice.servlet.BaseServlet;
import com.jackchan.takeoutservice.servlet2.business.LocalAppProvider;
import com.jackchan.takeoutservice.servlet2.proper.LocalAppProper;
import com.jackchan.takeoutservice.utils.CommonUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author：lhb on 2017/10/19 15:57
 * Mail：lihaibo@znds.com
 */

public class LocalApkFileServlet extends BaseServlet {

    private final String TAG = LocalApkFileServlet.class.getSimpleName();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        Log.d(TAG, "doGet");


        Log.d(TAG, "method:" + req.getMethod() + "|" + "uri:" + req.getRequestURI() + "|" + "url:" + req.getRequestURL());
        Log.d(TAG, "remote addr:" + req.getRemoteAddr() + "|" + "remote host:" + req.getRemoteHost() + "|" + "remote port:" + req.getRemotePort());


        String packageName = req.getParameter("packagename");
        if (TextUtils.isEmpty(packageName)) {
            CommonUtil.renderHtml(resp, "错误--没有传包名");
            return;
        }


        Log.d(TAG, "packagename = " + packageName);

        LocalAppProper proper = LocalAppProvider.getLocalUninstalledApp(App.getContext(), packageName);

        Log.d(TAG, proper.toString());

        //

        downloadChineseFileByOutputStream(resp, proper.getUrl(), generateFileName(proper));
    }

    private String generateFileName(LocalAppProper proper) {
        final String DEVIDE = "_";
        final String FILE_SUFFIX = ".apk";

        return proper.getAppname() + DEVIDE
                + proper.getPackagename() + DEVIDE
                + "V" + proper.getVername() + DEVIDE
                + proper.getVercode() + FILE_SUFFIX;
    }


    private void downloadChineseFileByOutputStream(HttpServletResponse response, String filePath, String fileName)
            throws FileNotFoundException, IOException {

        Log.d(TAG, "== begin response apk file  == ");
        Log.d(TAG, "apk file path = " + filePath);
        Log.d(TAG, "apk file name = " + fileName);
        //设置content-disposition响应头控制浏览器以下载的形式打开文件，中文文件名要使用URLEncoder.encode方法进行编码，否则会出现文件名乱码
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        InputStream in = new FileInputStream(filePath);//获取文件输入流
        int len = 0;
        byte[] buffer = new byte[1024];
        OutputStream out = response.getOutputStream();
        while ((len = in.read(buffer)) > 0) {
            //将缓冲区的数据输出到客户端浏览器
            out.write(buffer, 0, len);
        }
        out.flush();
        in.close();
    }

}
