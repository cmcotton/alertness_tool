package com.cht.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cht.util.PropertyReader;

public class DBConnection {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Connection con = null; // Database objects
    // 連接object
    private Statement stat = null;
    // 執行,傳入之sql為完整字串
    private ResultSet rs = null;
    // 結果集
    private PreparedStatement pst = null;
    // 執行,傳入之sql為預儲之字申,需要傳入變數之位置
    // 先利用?來做標示

    private String dropdbSQL = "DROP TABLE User ";

    private String createdbSQL = "CREATE TABLE User (" + "    id     INTEGER " + "  , name    VARCHAR(20) "
            + "  , passwd  VARCHAR(20))";

    private String insertdbSQL = "insert into User(id,name,passwd) " + "select ifNULL(max(id),0)+1,?,? FROM User";

    private String selectSQL = "select * from User ";

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
        
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        return rs;
    }
    
    public DBConnection() {
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
            con = DriverManager.getConnection("jdbc:mysql://localhost/Sysadm", user, adv);
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
    
    public void close(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            logger.error("Exception :" + e.toString());
        }
    }

}