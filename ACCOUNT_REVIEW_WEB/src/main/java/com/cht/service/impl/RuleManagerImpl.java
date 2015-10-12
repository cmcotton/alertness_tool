/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cht.analyzer.Analyzer;
import com.cht.analyzer.Loader;
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
}
