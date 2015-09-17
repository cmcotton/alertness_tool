/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.entity;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：RegulationEntity.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author chtd
 *@version 1.0
 *@since 1.0
 */
public class RegulationEntity {
    
    private String no;
    
    private String criteria;
    
    private String name;
    
    private String desc;
    
    private String applicable;
    
    private String pass;
    
    private String nonexec;
    
    private String violate;
    
    private String property; // {auto, manual}

    /**
     * @return the no
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * @return the criteria
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * @param criteria the criteria to set
     */
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the applicable
     */
    public String getApplicable() {
        return applicable;
    }

    /**
     * @param aplpicable the applicable to set
     */
    public void setApplicable(String applicable) {
        this.applicable = applicable;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the nonexec
     */
    public String getNonexec() {
        return nonexec;
    }

    /**
     * @param nonexec the nonexec to set
     */
    public void setNonexec(String nonexec) {
        this.nonexec = nonexec;
    }

    /**
     * @return the violate
     */
    public String getViolate() {
        return violate;
    }

    /**
     * @param violate the violate to set
     */
    public void setViolate(String violate) {
        this.violate = violate;
    }

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }

    
}
