package com.sxit.models.service;

import java.util.Date;


/**
 * GlobalKeyword entity. @author MyEclipse Persistence Tools
 */

public class GlobalKeyword  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String keywords;
     private Integer createuserid;
     private Date createtime;
     private String remarks;


    // Constructors

    /** default constructor */
    public GlobalKeyword() {
    }

	/** minimal constructor */
    public GlobalKeyword(String keywords, Integer createuserid, Date createtime) {
        this.keywords = keywords;
        this.createuserid = createuserid;
        this.createtime = createtime;
    }
    
    /** full constructor */
    public GlobalKeyword(String keywords, Integer createuserid, Date createtime, String remarks) {
        this.keywords = keywords;
        this.createuserid = createuserid;
        this.createtime = createtime;
        this.remarks = remarks;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeywords() {
        return this.keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getCreateuserid() {
        return this.createuserid;
    }
    
    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
   








}