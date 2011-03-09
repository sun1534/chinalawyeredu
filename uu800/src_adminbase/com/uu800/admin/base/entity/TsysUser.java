package com.uu800.admin.base.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * TsysUser entity. @author MyEclipse Persistence Tools
 */

public class TsysUser extends User implements java.io.Serializable {


    // Fields    

     private long userid;
     private TsysOrg tsysOrg;
     private TsysAreaCode tsysAreaCode;
     private String username;
     private String name;
     private String gender;
     private String employeeid;
     private String password;
     private int status;
     private String department;
     private String title;
     private String email;
     private String officephone;
     private String mobile;
     private String userip;
     private String comments;
     private long createuser;
     private Date createtime;
     private long modifyuser;
     private Date modifytime;
     private int style;
     private int usertype;
 	 private int operatetype;
	 private Set tsysRoles = new HashSet(0);


    // Constructors

    /** default constructor */
    public TsysUser() {
    }

	/** minimal constructor */
    public TsysUser(long userid, String username, String name, String password, int status) {
        this.userid = userid;
        this.username = username;
        this.name = name;
        this.password = password;
        this.status = status;
    }
    
    /** full constructor */
	public TsysUser(TsysOrg tsysOrg, TsysAreaCode tsysAreaCode,
			String username, String name, String gender, String employeeid,
			String password, int status, String department, String title,
			String email, String officephone, String mobile, String userip,
			String comments, long createuser, Date createtime, long modifyuser,
			Date modifytime, int style, int usertype, int operatetype,
			Set tsysRoles)
	{
		this.tsysOrg = tsysOrg;
		this.tsysAreaCode = tsysAreaCode;
		this.username = username;
		this.name = name;
		this.gender = gender;
		this.employeeid = employeeid;
		this.password = password;
		this.status = status;
		this.department = department;
		this.title = title;
		this.email = email;
		this.officephone = officephone;
		this.mobile = mobile;
		this.userip = userip;
		this.comments = comments;
		this.createuser = createuser;
		this.createtime = createtime;
		this.modifyuser = modifyuser;
		this.modifytime = modifytime;
		this.style = style;
		this.usertype = usertype;
		this.operatetype = operatetype;
		this.tsysRoles = tsysRoles;
	}

   
    // Property accessors

    @Override
	public long getUserid() {
        return this.userid;
    }
    
    @Override
	public void setUserid(long userid) {
        this.userid = userid;
    }

    public TsysOrg getTsysOrg() {
        return this.tsysOrg;
    }
    
    public void setTsysOrg(TsysOrg tsysOrg) {
        this.tsysOrg = tsysOrg;
    }

    @Override
	public String getUsername() {
        return this.username;
    }
    
    @Override
	public void setUsername(String username) {
        this.username = username;
    }

    @Override
	public String getName() {
        return this.name;
    }
    
    @Override
	public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmployeeid() {
        return this.employeeid;
    }
    
    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    @Override
	public String getPassword() {
        return this.password;
    }
    
    @Override
	public void setPassword(String password) {
        this.password = password;
    }

    @Override
	public int getStatus() {
        return this.status;
    }
    
    @Override
	public void setStatus(int status) {
        this.status = status;
    }

    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficephone() {
        return this.officephone;
    }
    
    public void setOfficephone(String officephone) {
        this.officephone = officephone;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
	public String getUserip() {
        return this.userip;
    }
    
    @Override
	public void setUserip(String userip) {
        this.userip = userip;
    }

    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
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

    public long getModifyuser() {
        return this.modifyuser;
    }
    
    public void setModifyuser(long modifyuser) {
        this.modifyuser = modifyuser;
    }

    public Date getModifytime() {
        return this.modifytime;
    }
    
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    @Override
	public int getStyle() {
        return this.style;
    }
    
    @Override
	public void setStyle(int style) {
        this.style = style;
    }

    @Override
	public int getUsertype() {
        return this.usertype;
    }
    
    @Override
	public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

	public TsysAreaCode getTsysAreaCode()
	{
		return tsysAreaCode;
	}

	public void setTsysAreaCode(TsysAreaCode tsysAreaCode)
	{
		this.tsysAreaCode = tsysAreaCode;
	}

	public int getOperatetype()
	{
		return operatetype;
	}

	public void setOperatetype(int operatetype)
	{
		this.operatetype = operatetype;
	}

	public Set getTsysRoles()
	{
		return tsysRoles;
	}

	public void setTsysRoles(Set tsysRoles)
	{
		this.tsysRoles = tsysRoles;
	}


   

	/**
	 * @return the id
	 */
	@Override
	public long getId() {
//		return id;
		return (int)this.userid;
	}

	/**
	 * @return the userName
	 */
	@Override
	public String getUserName() {
//		return userName;
		return this.name;
	}

	/**
	 * @return the loginName
	 */
	@Override
	public String getLoginName() {
//		return loginName;
		return this.username;
	}






}