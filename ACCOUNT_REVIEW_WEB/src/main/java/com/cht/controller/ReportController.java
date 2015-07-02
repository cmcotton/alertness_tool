/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cht.layout.ILayout;
import com.cht.layout.ReportTable;
import com.cht.logic.AccountReview;
import com.cht.logic.IAccountReport;
import com.cht.proxy.LogProxy;
import com.cht.result.Result;

/**
 * 程式資訊摘要：
 * <P>
 * 類別名稱　　：MyHelloWorld.java
 * <P>
 * 程式內容說明：
 * <P>
 * 程式修改記錄：
 * <P>
 * XXXX-XX-XX：
 * <P>
 * 
 * @author chtd
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/report")
public class ReportController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {

        LogProxy logAdvice = new LogProxy();

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(logAdvice);

        logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
        return new ModelAndView("pages/index", "message", message);
    }

    @RequestMapping("/tables") 
    public ModelAndView getReportTable() {
        return new ModelAndView("pages/tables");
    }
    
    @RequestMapping("/forms") 
    public ModelAndView getForms() {
        return new ModelAndView("pages/forms");
    }
    
    @RequestMapping("/panels-wells") 
    public ModelAndView getPanelsWells() {
        return new ModelAndView("pages/panels-wells");
    }

    @RequestMapping("/grid") 
    public ModelAndView getGrid() {
        return new ModelAndView("pages/grid");
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public @ResponseBody
    void getUser(HttpServletResponse res) throws IOException {
        Result result = new Result();

        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
        result.setWhat(message);

        logger.debug(message);

        // List<String> list = Arrays.asList("a", "b");
        Result r = new Result();
        r.setWhat("jeigqjeigrjiqgr");
        r.setWho("I am the king");
        JSONObject json = new JSONObject(r);
        res.setContentType("json");
        res.getWriter().write(json.toString());
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public @ResponseBody
    void getReportList(HttpServletResponse res) throws IOException {
        LogProxy logAdvice = new LogProxy();

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(logAdvice);

        // gen report
        IAccountReport ar = new AccountReview();
        factory.setTarget(ar);
        IAccountReport proxy = (IAccountReport) factory.getProxy();
        proxy.execute();

        logger.info("after gen reports");

        // list report
        ILayout reportTable = new ReportTable();
        factory.setTarget(reportTable);
        ILayout reportProxy = (ILayout) factory.getProxy();
        List dataList = reportProxy.arrange();

        try {

            JSONObject json = new JSONObject();
            json.put("data", dataList);
            res.setCharacterEncoding("UTF-8");
            // response.setContentType("text/html"); //设置数据格式            
            res.setContentType("json");
            res.getWriter().write(json.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

}