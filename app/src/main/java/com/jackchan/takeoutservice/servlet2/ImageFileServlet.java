package com.jackchan.takeoutservice.servlet2;

import android.os.Environment;

import com.jackchan.takeoutservice.App;
import com.jackchan.takeoutservice.imageServer.ImageUtil;
import com.jackchan.takeoutservice.servlet.BaseServlet;
import com.jackchan.takeoutservice.utils.StreamUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageFileServlet extends BaseServlet {

    public static String baseurl = "http://127.0.0.1:8090/TakeoutService/image?name=";

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        File file = ImageUtil.getAppIconFile(App.getContext(), name);
        if (file != null
                &&
                file.exists()) {
            System.out.println("icon file == "+file.getAbsolutePath());

            resp.setStatus(HttpServletResponse.SC_OK);
            long length = file.length();
            resp.setContentLength((int) length);
            OutputStream out = resp.getOutputStream();
            FileInputStream stream = new FileInputStream(file);
            StreamUtil.fileStream(stream, out);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

