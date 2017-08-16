/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cht.analyzer.Analyzer;
import com.cht.analyzer.Loader;
import com.cht.analyzer.RuleBlock;
import com.cht.db.MySQLDBConnection;
import com.cht.entity.LoginLogEntity;
import com.cht.service.RuleManager;

/**
 * 程式資訊摘要：
 * <P>
 * 類別名稱　　：ReportServiceImpl.java
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

@Service
public class RuleManagerImpl implements RuleManager {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private List riskEvents;
    
    List<RuleBlock> ruleBlocks;
    
    private Map<String, List> ruleChains;

    /*
     * (non-Javadoc)
     * 
     * @see com.cht.service.RuleManager#runAnalysis()
     */
    @Override
    public void runAnalysis(String ruleId) {
        Loader loader = new Loader();
        List<Analyzer> analyzers = loader.load();

        MySQLDBConnection db = new MySQLDBConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

        
        try {
            Date[] period = { sdf.parse("2015-08-19 00:00:00"), sdf.parse("2015-10-20 00:00:00") };
            
            final List<LoginLogEntity> source = db.queryLoginLog(period);
            
            // run every analyzer, should be in batch job
            analyzers.forEach(a -> run(a, ruleId, source));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.riskEvents = null;
        

     
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cht.service.RuleManager#getTableData(java.lang.String)
     */
    @Override
    public List getTableData(String ruleId) {

        logger.debug("rule: {}", ruleId);

        runAnalysis(ruleId);

        return this.riskEvents;
    }

    private void run(Analyzer a, String ruleId, List source) {
        if (ruleId.equals(a.getRuleId())) {
            List riskEvents = a.analyze(source);
            this.riskEvents = riskEvents;
        }
    }

    /* (non-Javadoc)
     * @see com.cht.service.RuleManager#loadRuleBlock()
     */
    @Override
    public List<RuleBlock> loadRuleBlock() {
        
        if (ruleBlocks == null) {
            Loader loader = new Loader();
            ruleBlocks = loader.loadRuleBlock();
        }
        
        ruleBlocks.forEach(rb -> {            
                logger.info("rule block: {}", rb.getName());
                logger.info("123");
            }
        );
        
        return ruleBlocks;
    }
    
    /* (non-Javadoc)
     * @see com.cht.service.RegulationManager#fillinRule(java.lang.String, java.lang.String)
     */
    @Override
    public void runRuleChain(String ruleId) {
        
        if (ruleBlocks == null) {
            Loader loader = new Loader();
            ruleBlocks = loader.loadRuleBlock();
        }
        
//        ruleBlocks = ruleBlocks.stream().filter(rb -> rb.getId().equals("rbFi le1")).collect(Collectors.toList());
        logger.info("ruleBlocks size: {}", ruleBlocks.size());
        
        logger.info("run ruleId: {}", ruleId);  
        
        if ("0".equals(ruleId)) { 
        
            // data IN comparedData e.g. sensitive user id
            List<String> data = ruleBlocks.get(2).importData();
            List<String> comparedData = ruleBlocks.get(1).importData();
            
            data.retainAll(comparedData);
            
            try {
                File file = new File("d:/sensitive_user_id.txt");
                FileWriter fileWriter = new FileWriter(file);
                
                for (String s : data) {
                    fileWriter.write(s + "\n");
                }
                
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            

        } else if ("1".equals(ruleId)) {
            // data NOT IN comparedData e.g. abnormal user id
            
            List<String> data = ruleBlocks.get(2).importData();
            List<String> comparedData = ruleBlocks.get(0).importData();
            
            data.removeAll(comparedData);
            
            try {
                File file = new File("d:/abnormal_user_id.txt");
                FileWriter fileWriter = new FileWriter(file);
                try {
                    for (String s : data) {
                        fileWriter.write(s + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } else {
            logger.info("wrong rule chain id");
        }
        
    }
   
}
