/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * 程式資訊摘要：
 * <P>
 * 類別名稱　　：PropertyReader.java
 * <P>
 * 程式內容說明：
 * <P>
 * 程式修改記錄：
 * <P>
 * XXXX-XX-XX：
 * <P>
 * @author chtd
 * @version 1.0
 * @since 1.0
 */
public class PropertyReader {
    
    public static String REPORT_PATH = "REPORT_PATH";
    public static String DB_USER = "DB_USER";
    public static String DB_ADV = "DB_ADV";

    public String getPropValues(String key) throws IOException {

        String result = "";
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        Date time = new Date(System.currentTimeMillis());

        // get the property value and print it out
        result = prop.getProperty(key);
        return result;
    }
}
