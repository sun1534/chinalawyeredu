package com.changpeng.models;


/**
 * Articles entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Articles implements java.io.Serializable {

	// Fields

	private int articleid;
	private int type;
	private String title;
	private String content;
	private int status;
	private java.sql.Timestamp createtime;
	private int createuserid;
	private String createuser;
	private boolean toshouye;
	private int listorder;
	private int provinceid;
	private int cityid;
	private int officeid;
private int thegroup;
	
	// Constructors

	/**
 * @return the thegroup
 */
public int getThegroup() {
	return thegroup;
}


/**
 * @param thegroup the thegroup to set
 */
public void setThegroup(int thegroup) {
	this.thegroup = thegroup;
}


	/**
	 * @return the officeid
	 */
	public int getOfficeid() {
		return officeid;
	}


	/**
	 * @param officeid the officeid to set
	 */
	public void setOfficeid(int officeid) {
		this.officeid = officeid;
	}


	/**
	 * @return the provinceid
	 */
	public int getProvinceid() {
		return provinceid;
	}


	/**
	 * @param provinceid the provinceid to set
	 */
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}


	/**
	 * @return the cityid
	 */
	public int getCityid() {
		return cityid;
	}


	/**
	 * @param cityid the cityid to set
	 */
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}


	/** default constructor */
	public Articles() {
	}

	
	// Property accessors

	public int getArticleid() {
		return this.articleid;
	}

	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public java.sql.Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public int getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public boolean getToshouye() {
		return this.toshouye;
	}

	public void setToshouye(boolean toshouye) {
		this.toshouye = toshouye;
	}

	public int getListorder() {
		return this.listorder;
	}

	public void setListorder(int listorder) {
		this.listorder = listorder;
	}



}