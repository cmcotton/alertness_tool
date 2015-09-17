package com.cht.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cht.entity.FillinEntity;
import com.cht.entity.LoginLogEntity;
import com.cht.entity.OperationLogEntity;
import com.cht.entity.RegulationEntity;
import com.cht.entity.RuleEntity;
import com.cht.util.PropertyReader;

public class MySQLDBConnection implements AuxiliaryQuery {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Connection con = null; // Database objects
    
    public Connection getConnection() {
        if (con != null) {
            return con;
        }
        return con;
    }
    
    public ResultSet query(Connection con) {
        String sql = "SELECT * FROM AccountReport Order by OwnerUnit";
        
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        
        return rs;
    }
    
    public List<RegulationEntity> queryRegulation() {
        List<RegulationEntity> list = new ArrayList<>();
        
        String sql = "SELECT * FROM regulation";
        
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                int index = 2;
                RegulationEntity entity = new RegulationEntity();
                entity.setNo(rs.getString(index++));
                entity.setCriteria(rs.getString(index++));
                entity.setName(rs.getString(index++));
                entity.setDesc(rs.getString(index++));
                entity.setApplicable(rs.getString(index++));
                entity.setPass(rs.getString(index++));
                entity.setNonexec(rs.getString(index++));
                entity.setViolate(rs.getString(index++));
                entity.setProperty(rs.getString(index++));
                
                list.add(entity);
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        } finally {
            close(con);
        }
        
        return list;
    }   
    
    public List<RuleEntity> queryRule(String appliedRegu) {
        List<RuleEntity> list = new ArrayList<>();
        
        String sql = "SELECT no, name, description, appliedRegu, property "
                + "FROM rule ";
        
        if (!StringUtils.isEmpty(appliedRegu)) {
            sql += " WHERE appliedRegu = ?";
        }
        
        System.out.println(sql);
        System.out.println(appliedRegu);
        
        PreparedStatement stmt = null;
        try {
            
            stmt = con.prepareStatement(sql);
            
            if (!StringUtils.isEmpty(appliedRegu)) {
                stmt.setString(1, appliedRegu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int index = 1;
                RuleEntity entity = new RuleEntity();
                entity.setNo(rs.getString(index++));               
                entity.setName(rs.getString(index++));
                entity.setDesc(rs.getString(index++));
                entity.setAppliedRegu(rs.getString(index++));
                entity.setProperty(rs.getString(index++));
                
                list.add(entity);
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        } finally {
            close(con);
        }
        
        return list;
    }
    
    public List<LoginLogEntity> queryLoginLog(Date[] period) {
        List<LoginLogEntity> list = new ArrayList<>();
        
        String sql = "SELECT id, userid, datetime, action, ip "
                + "FROM loginlog ";
        
        if (period.length == 2) {
            sql += " WHERE datetime BETWEEN ? AND ? ";
        }
        
        sql += " order by datetime";
        
        logger.debug("sql: {}", sql);
        logger.debug(period[0] + " " + period[1]);
        
        PreparedStatement stmt = null;
        try {
            
            stmt = con.prepareStatement(sql);
            
            if (period.length == 2) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                stmt.setString(1, sdf.format(period[0]));
                stmt.setString(2, sdf.format(period[1]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int index = 1;
                LoginLogEntity entity = new LoginLogEntity();
                entity.setId(rs.getString(index++));
                entity.setUserid(rs.getString(index++));
                entity.setDatetime(rs.getString(index++));
                entity.setAction(rs.getString(index++));
                entity.setIp(rs.getLong(index++));
                
                list.add(entity);
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        } finally {
            close(con);
        }
        
        return list;
    }
    
    public List<OperationLogEntity> queryOperationLog(Date[] period) {
        List<OperationLogEntity> list = new ArrayList<>();
        
        String sql = "SELECT userid, datetime, action, ip "
                + "FROM operationlog ";
        
        if (period.length == 2) {
            sql += " WHERE datetime BETWEEN ? AND ? ";
        }
        
        sql += " order by datetime";
        
        logger.debug("sql: {}", sql);
        logger.debug(period[0] + " " + period[1]);
        
        PreparedStatement stmt = null;
        try {
            
            stmt = con.prepareStatement(sql);
            
            if (period.length == 2) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                stmt.setString(1, sdf.format(period[0]));
                stmt.setString(2, sdf.format(period[1]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int index = 1;
                OperationLogEntity entity = new OperationLogEntity();
                entity.setUserId(rs.getString(index++));
                entity.setDatetime(rs.getString(index++));
                entity.setAction(rs.getString(index++));
                entity.setIp(rs.getLong(index++));
                
                list.add(entity);
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        } finally {
            close(con);
        }
        
        return list;
    }
    
    /**
     * @precondition: has regulations in table.
     * post condition: update result
     * 
     * @param oper : String {0, 1}
     * @param ids array of id 
     * 
     */    
    public void updateRegulationApplicable(String oper, String[] ids) {
        String sql = "UPDATE regulation SET applicable = ? ";
        
        if (ids.length > 0) {
            String strIN = StringUtils.join(ids, "', '");
            sql += " WHERE no IN ('" + strIN + "')";
        }
        
        logger.info("oper: {}", oper);
        logger.info(sql);
        
        PreparedStatement stmt = null;
        try {
            
            stmt = con.prepareStatement(sql);
            
            if (ids.length > 0) {
                if (oper.equals("apply")) {
                    stmt.setInt(1, 1);
                } else {
                    stmt.setInt(1, 0);
                }
            }
            
            int cnt = stmt.executeUpdate();
            
            logger.info(">>>{} rows affected.", cnt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
    public MySQLDBConnection() {
        PropertyReader pr = new PropertyReader();
        
        String user = "";
        String adv = "";
        try {
            user = pr.getPropValues(PropertyReader.DB_USER);
            adv = pr.getPropValues(PropertyReader.DB_ADV);
        } catch (IOException e1) {
             logger.error(e1.getMessage());
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            // 註冊driver
            con = DriverManager.getConnection("jdbc:mysql://localhost/account?useUnicode=true&characterEncoding=UTF-8", user, adv);
            // 取得connection

            // jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
            // localhost是主機名,test是database名
            // useUnicode=true&characterEncoding=Big5使用的編碼

        } catch (ClassNotFoundException e) {
            logger.error("DriverClassNotFound :" + e.toString());
        }// 有可能會產生sqlexception
        catch (SQLException x) {
            logger.error("Exception :" + x.toString());
        }

    }
 
    public List<FillinEntity> queryFillin(String rule) {
        List<FillinEntity> datalist = new ArrayList<FillinEntity>();        
        
        String sql = "SELECT submitter, datetime, rule, attachment, action "
                    + "FROM fillin "
                    + "WHERE rule = ? "
                    + "ORDER BY DATETIME";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            
            int index = 1;
            stmt.setString(index++, rule);
            
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        
        try {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int index = 1;
                FillinEntity entity = new FillinEntity();
                entity.setSubmitter(rs.getString(index++));               
                entity.setDatetime(rs.getString(index++));
                entity.setRule(rs.getString(index++));
                entity.setAttachment(rs.getString(index++));
                entity.setAction(rs.getString(index++));
                
                datalist .add(entity);
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        } finally {
            close(con);
        }
        
        return datalist;
    }

    public void insertFillin(FillinEntity fillin) {        
        
        String sql = "INSERT INTO fillin (submitter, datetime, rule, attachment, action) "
                + "VALUES (?, NOW(), ?, ?, ?) ";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            
            int index = 1;
            stmt.setString(index++, fillin.getSubmitter());
            stmt.setString(index++, fillin.getRule());
            stmt.setString(index++, fillin.getAttachment());
            stmt.setString(index++, fillin.getAction());
            
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        
        try {
            stmt.execute();
        } catch (SQLException e) {
            logger.error(e.toString());
        } finally {
            
        }
        
        
        // update regu
        if (fillin.getAction().equals("pass")) {
            sql = "update regulation set pass = 1 and violate = 0 where no = 'A.7.1.1'";
        } else {
            sql = "update regulation set pass = 0 and violate = 1 where no = 'A.7.1.1'";
        }
        Statement stmt2;
        try {
            stmt2 = con.createStatement();
            stmt2.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        close(con);
        
    }
    
    public void close(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            logger.error("Exception :" + e.toString());
        }
    }

    /* (non-Javadoc)
     * @see com.cht.db.AuxiliaryQuery#queryAccount()
     */
    @Override
    public List<String> queryAccount() {
        List<String> datalist = new ArrayList<String>();
        
        String sql = "SELECT accountname "
                    + "FROM accountdata ";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);                   
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        
        try {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int index = 1;
                datalist.add(rs.getString(index++));
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        } finally {
            close(con);
        }
        
        return datalist;        
    }

}
