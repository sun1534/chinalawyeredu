package com.uu800.admin.base.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * TsysRight entity. @author MyEclipse Persistence Tools
 */

public class TsysRight extends Right implements java.io.Serializable {


    // Fields    

     private String rightid;
     private TsysRight parentRight;
     private String moduletype;
     private String rightcode;
     private long grade;
     private long nodetype;
     private String pathcode;
     private String rightname;
     private long logflag;
     private long createuser;
     private Date createtime;
     private Set tsysRoles = new HashSet(0);
     private Set tsysMenus = new HashSet(0);
     private Set tsysRights = new HashSet(0);


    // Constructors

    /** default constructor */
    public TsysRight() {
    }

	/** minimal constructor */
    public TsysRight(String rightid, String rightcode) {
        this.rightid = rightid;
        this.rightcode = rightcode;
    }
    
    /** full constructor */
    public TsysRight(String rightid, TsysRight parentRight, String moduletype, String rightcode, long grade, long nodetype, String pathcode, String rightname, long logflag, long createuser, Date createtime, Set tsysRoles, Set tsysMenus, Set tsysRights) {
        this.rightid = rightid;
        this.parentRight = parentRight;
        this.moduletype = moduletype;
        this.rightcode = rightcode;
        this.grade = grade;
        this.nodetype = nodetype;
        this.pathcode = pathcode;
        this.rightname = rightname;
        this.logflag = logflag;
        this.createuser = createuser;
        this.createtime = createtime;
        this.tsysRoles = tsysRoles;
        this.tsysMenus = tsysMenus;
        this.tsysRights = tsysRights;
    }

   
    // Property accessors

    @Override
	public String getRightid() {
        return this.rightid;
    }
    
    @Override
	public void setRightid(String rightid) {
        this.rightid = rightid;
    }



    public String getModuletype() {
        return this.moduletype;
    }
    
    public void setModuletype(String moduletype) {
        this.moduletype = moduletype;
    }

    @Override
	public String getRightcode() {
        return this.rightcode;
    }
    
    @Override
	public void setRightcode(String rightcode) {
        this.rightcode = rightcode;
    }

    @Override
	public long getGrade() {
        return this.grade;
    }
    
    @Override
	public void setGrade(long grade) {
        this.grade = grade;
    }

    public long getNodetype() {
        return this.nodetype;
    }
    
    public void setNodetype(long nodetype) {
        this.nodetype = nodetype;
    }

    public String getPathcode() {
        return this.pathcode;
    }
    
    public void setPathcode(String pathcode) {
        this.pathcode = pathcode;
    }

    @Override
	public String getRightname() {
        return this.rightname;
    }
    
    @Override
	public void setRightname(String rightname) {
        this.rightname = rightname;
    }

    public long getLogflag() {
        return this.logflag;
    }
    
    public void setLogflag(long logflag) {
        this.logflag = logflag;
    }

    public long getCreateuser() {
        return this.createuser;
    }
    
    public void setCreateuser(long createuser) {
        this.createuser = createuser;
    }

    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Set getTsysRoles() {
        return this.tsysRoles;
    }
    
    public void setTsysRoles(Set tsysRoles) {
        this.tsysRoles = tsysRoles;
    }

    public Set getTsysMenus() {
        return this.tsysMenus;
    }
    
    public void setTsysMenus(Set tsysMenus) {
        this.tsysMenus = tsysMenus;
    }

    public Set getTsysRights() {
        return this.tsysRights;
    }
    
    public void setTsysRights(Set tsysRights) {
        this.tsysRights = tsysRights;
    }

	public TsysRight getParentRight() {
		return parentRight;
	}

	public void setParentRight(TsysRight parentRight) {
		this.parentRight = parentRight;
	}
   








}