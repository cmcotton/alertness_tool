/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cht.entity.FillinEntity;
import com.cht.entity.LoginLogEntity;
import com.cht.layout.DataTableColumn;
import com.cht.proxy.LogProxy;
import com.cht.service.RuleManager;

/**
 * 程式資訊摘要：
 * 類別名稱　　：MyHelloWorld.java
 * 程式內容說明：
 * 程式修改記錄：
 * XXXX-XX-XX：
 * 
 * @author chtd
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/audit")
public class AuditController {

    @Resource
    private RuleManager ruleManager;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "/serviceloader")
    public void run(HttpServletResponse res) {
        
        ruleManager.runAnalysis("");
        
        try {

            JSONObject json = new JSONObject();

            json.put("data", "aaa");
            res.setCharacterEncoding("UTF-8");
            res.setContentType("json");
            res.getWriter().write(json.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    /**
     * 登出入紀錄
     * @param res
     * @throws IOException
     */
    @RequestMapping(value = "/postGridData", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    void postGridData(@RequestParam("rule") String ruleId, HttpServletResponse res) throws IOException {
        logger.info("rule: {}", ruleId);
        
        LogProxy logAdvice = new LogProxy();

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(logAdvice);

        List<LoginLogEntity> datalist = ruleManager.getTableData(ruleId);
        
        if (datalist == null) {
            logger.error("unimplmented rule: {}", ruleId);
            try {
                JSONObject json = new JSONObject();
                json.put("error", "unimp");
                res.setCharacterEncoding("UTF-8");            
                res.setContentType("json");
                res.getWriter().write(json.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONArray jsonArray = new JSONArray();
                
                for (LoginLogEntity fillin : datalist) {
                    JSONObject json = new JSONObject(fillin);
                    jsonArray.put(json);
                }
                
                res.setCharacterEncoding("UTF-8");   
                res.setContentType("json");
                res.getWriter().write(jsonArray.toString());
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        

    }
   
    @RequestMapping(value = "/getReportColumn", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    void getReportColumn(@RequestParam("rule") String rule, HttpServletResponse res) throws IOException {
        logger.info("rule: {}", rule);
        
        try {
            JSONArray cols = new JSONArray();
            
            DataTableColumn col = new DataTableColumn().setName("userid").setTitle("使用者");
            cols.put(new JSONObject(col));
            col = new DataTableColumn().setName("datetime").setTitle("日期時間");
            cols.put(new JSONObject(col));
//            col = new DataTableColumn().setName("event").setTitle("事件");
//            cols.put(new JSONObject(col));
            col = new DataTableColumn().setName("action").setTitle("行為");
            cols.put(new JSONObject(col));
            col = new DataTableColumn().setName("ip").setTitle("來源IP位址");
            cols.put(new JSONObject(col));
            
            res.setCharacterEncoding("UTF-8");
            // response.setContentType("text/html"); //设置数据格式            
            res.setContentType("json");
            res.getWriter().write(cols.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
   
}