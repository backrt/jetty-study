package com.jackchan.takeoutservice.servlet2;

import android.util.Log;

import com.jackchan.takeoutservice.App;
import com.jackchan.takeoutservice.servlet.BaseServlet;
import com.jackchan.takeoutservice.servlet2.business.LocalAppProvider;
import com.jackchan.takeoutservice.servlet2.proper.LocalAppProper;
import com.jackchan.takeoutservice.utils.CommonUtil;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
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
        Log.d(TAG, "doGet");
        Log.d(TAG, "method:" + req.getMethod() + "|" + "uri:" + req.getRequestURI() + "|" + "url:" + req.getRequestURL());
        Log.d(TAG, "remote addr:" + req.getRemoteAddr() + "|" + "remote host:" + req.getRemoteHost() + "|" + "remote port:" + req.getRemotePort());

        //fetch local unstalled apps
        List<LocalAppProper> localAppPropers = LocalAppProvider.getLocalUninstalledApps(App.getContext());
        CommonUtil.renderHtml(resp, generateHtml(req.getRequestURL().toString(), localAppPropers));
    }


    private String generateHtml(String url, List<LocalAppProper> list) {
        Document document = new Document(url);
        document.appendChild(generateElement_head());
        document.appendChild(generateElement_body());
        document.appendChild(generateElement_table(list));

        return document.outerHtml();
    }

    private Element generateElement_head() {

        Element head = new Element("head");
        //meta
        Element meta = new Element("meta");
        meta.attr("charset", "utf-8");
        head.appendChild(meta);

        //title
        Element title = new Element("title");
        title.text("测试HTML草泥马");
        head.appendChild(title);

        return head;
    }

    public Element generateElement_body() {

        Element body = new Element("body");
        //h1
        Element h1 = new Element("h1");
        h1.text("代码生成html");
        body.appendChild(h1);

        return body;
    }


    public Element generateElement_table(List<LocalAppProper> localAppPropers) {

        int count = localAppPropers.size();
        final int COLUMN_SIZE = 6;
        int columns = count / 6 + (count % 6 == 0 ? 0 : 1);


        Element table = new Element("table");
        table.attr("id", "app_table");
        table.attr("border", "3");
        table.attr("align", "center");
        table.attr("cols", COLUMN_SIZE + "");
        table.attr("bordercolor", "#a32112");


        //trs
        for (int i = 0; i < columns; i++) {
            //tr
            Element tr = new Element("tr");
            tr.attr("id", "app_table_row_" + i);

            for (int j = 0; j < COLUMN_SIZE; j++) {

                int index = i * COLUMN_SIZE + j;
                LocalAppProper localAppProper;
                if (index < count) {
                    localAppProper = localAppPropers.get(index);
                    if (localAppProper == null) {
                        break;
                    }
                } else {
                    break;
                }

                Element td = new Element("td");
                //a
                Element a = new Element("a");
                a.attr("align", "center");

                // img
                Element img = new Element("img");
                img.attr("src", "/TakeoutService/image?name=" + localAppProper.getPackagename());
                img.attr("height", "100");
                img.attr("width", "100");
                //p
                Element p = new Element("p");
                p.text(localAppProper.getAppname());

                a.appendChild(img);
                a.appendChild(p);
                td.appendChild(a);
                tr.appendChild(td);
            }
            table.appendChild(tr);
        }
        return table;
    }


}
