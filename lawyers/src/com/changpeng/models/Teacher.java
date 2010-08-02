package com.changpeng.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Teachers entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Teacher implements java.io.Serializable {

	// Fields

	private int userid;
	private String loginname;
	private String password;
	private String username;
	private int teacherType;
	private int status;
	private String gender;
	private String email;
	private String officephone;
	private Date birthday;
	private String mobile;
	private int provinceid;
	private int cityid;
	private int officeid;
	private int createuserid;
	private String createuser;
	private Timestamp createtime;
	private String comments;
	private String remarks;
	private String website;

	// Constructors

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	
	private int lessoncount;
	
	
	/**
	 * @return the lessoncount
	 */
	public int getLessoncount() {
		return lessoncount;
	}

	/**
	 * @param lessoncount the lessoncount to set
	 */
	public void setLessoncount(int lessoncount) {
		this.lessoncount = lessoncount;
	}

	/** default constructor */
	public Teacher() {
	}
	
	

	public String getTeacherTypeStr(){
		return com.changpeng.lessons.util.CommonDatas.TeacherType.get(teacherType);
	}
	
	// Property accessors

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTeacherType() {
		return this.teacherType;
	}

	public void setTeacherType(int teacherType) {
		this.teacherType = teacherType;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public int getProvinceid() {
		return this.provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public int getCityid() {
		return this.cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public int getOfficeid() {
		return this.officeid;
	}

	public void setOfficeid(int officeid) {
		this.officeid = officeid;
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

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}


	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}