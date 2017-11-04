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

public class LocalAppServlet extends BaseServlet {

    private final String TAG = LocalAppServlet.class.getSimpleName();

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
        CommonUtil.renderJson(resp, bean);
    }
}
