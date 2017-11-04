package com.jackchan.takeoutservice.servlet2;

import android.util.Log;

import com.jackchan.takeoutservice.App;
import com.jackchan.takeoutservice.bean2.LocalAppBean;
import com.jackchan.takeoutservice.bean2.LocalAppListDataBean;
import com.jackchan.takeoutservice.bean2.ResponseBean;
import com.jackchan.takeoutservice.servlet.BaseServlet;
import com.jackchan.takeoutservice.servlet2.business.LocalAppProvider;
import com.jackchan.takeoutservice.servlet2.proper.LocalAppProper;
import com.jackchan.takeoutservice.utils.CommonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author：lhb on 2017/10/19 15:57
 * Mail：lihaibo@znds.com
 */

public class LocalAppServlet2 extends BaseServlet {
    private final String TAG = LocalAppServlet2.class.getSimpleName();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        Log.d(TAG, "doGet");
        Log.d(TAG, "method:" + req.getMethod() + "|" + "uri:" + req.getRequestURI() + "|" + "url:" + req.getRequestURL());
        Log.d(TAG, "remote addr:" + req.getRemoteAddr() + "|" + "remote host:" + req.getRemoteHost() + "|" + "remote port:" + req.getRemotePort());


        ResponseBean bean = new ResponseBean();
        bean.setMsg("服务器正常");
        bean.setStatus_code(0);

        // data bean
        LocalAppListDataBean dataBean = new LocalAppListDataBean();
        List<LocalAppBean> list = new ArrayList<>();

        //fetch local unstalled apps
        List<LocalAppProper> localAppPropers = LocalAppProvider.getLocalUninstalledApps(App.getContext());

        for (LocalAppProper proper : localAppPropers) {
            LocalAppBean appBean = new LocalAppBean();
            appBean.setDescribe("测试应用");
            appBean.setLabel(proper.getAppname());
            appBean.setPackagename(proper.getPackagename());
            appBean.setUrl(proper.getUrl());
            appBean.setVername(proper.getVername());
            appBean.setVercode(proper.getVercode());

            //add
            list.add(appBean);
        }
        dataBean.setApps(list);
        dataBean.setItem_count(list.size());

        bean.setData(dataBean);

        // format response
//        CommonUtil.renderJson(resp, bean);
        CommonUtil.renderHtml(resp,generateHtml_applist());
    }


    String html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"utf-8\" />\n" +
            "\t\t<title>Jetty测试html</title>\n" +
            "\t</head>\n" +
            "\t<body>\n" +
            "\t\t<h1>测试数据</h1>\n" +
            "\t\t<table id=\"app_table\" border=\"3\" align=\"center\" cols=\"5\" bordercolor=\"#a32112\">\n" +
            "\n" +
            "\t\t\t<tr id=\"row1\">\n" +
            "\t\t\t\t<td><img src=\"img/ic_launcher.png\" height=\"100\" width=\"100\" />\n" +
            "\t\t\t\t</td>\n" +
            "\t\t\t\t<td><img src=\"img/ic_launcher.png\" height=\"100\" width=\"100\" /></td>\n" +
            "\t\t\t\t<td><img src=\"img/ic_launcher.png\" height=\"100\" width=\"100\" /></td>\n" +
            "\t\t\t\t<td><img src=\"img/ic_launcher.png\" height=\"100\" width=\"100\" /></td>\n" +
            "\t\t\t\t<td><img src=\"img/ic_launcher.png\" height=\"100\" width=\"100\" /></td>\n" +
            "\t\t\t</tr>\n" +
            "\t\t</table>\n" +
            "\t</body>\n" +
            "</html>";


    private String generateHtml_applist() {
        return html;
    }


}
