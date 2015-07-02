/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import com.cht.util.PropertyReader;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：FileManager.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author chtd
 *@version 1.0
 *@since 1.0
 */
public class FileManager {
    
    public File[] getFiles() {
        
        PropertyReader pr = new PropertyReader();
        String reportPath = null;
        try {
            reportPath = pr.getPropValues(PropertyReader.REPORT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File filePath = new File(reportPath);
        FileFilter filter = new FileFilter() {
            
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().contains("accountreview")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        
        return filePath.listFiles(filter);
        
    }

}
