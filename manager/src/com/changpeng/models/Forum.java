package com.changpeng.models;

import java.sql.Timestamp;

/**
 * Forum entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Forum implements java.io.Serializable {

	// Fields

	private int forumid;
	private int thetype;
	private String title;
	private boolean ismain;
	private int mainforum;
	private int replycount;
	private String forumcontent;
	private Timestamp lastupdate;
	private String createuser;
	private Timestamp createtime;
	private int createuserid;
	
	private int provinceid;
	private int cityid;
	private int officeid;
	
	private int thegroup;
	
	private int roleid = 1;

	// Constructors

	/**
	 * @return the roleid
	 */
	public int getRoleid() {
		return roleid;
	}

	/**
	 * @param roleid
	 *            the roleid to set
	 */
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	/** default constructor */
	public Forum() {
	}

	// Property accessors

	public int getForumid() {
		return this.forumid;
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	public int getThetype() {
		return this.thetype;
	}

	public void setThetype(int thetype) {
		this.thetype = thetype;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getIsmain() {
		return this.ismain;
	}

	public void setIsmain(boolean ismain) {
		this.ismain = ismain;
	}

	public int getMainforum() {
		return this.mainforum;
	}

	public void setMainforum(int mainforum) {
		this.mainforum = mainforum;
	}

	public int getReplycount() {
		return this.replycount;
	}

	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	public String getForumcontent() {
		return this.forumcontent;
	}

	public void setForumcontent(String forumcontent) {
		this.forumcontent = forumcontent;
	}

	public Timestamp getLastupdate() {
		return this.lastupdate;
	}

	public void setLastupdate(Timestamp lastupTimestamp) {
		this.lastupdate = lastupTimestamp;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public int getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
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

}