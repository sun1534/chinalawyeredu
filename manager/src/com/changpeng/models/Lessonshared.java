package com.changpeng.models;

import java.sql.Timestamp;

/**
 * Lessonshared entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessonshared implements java.io.Serializable {

	// Fields

	private int shareid;
//	private SysGroup sysGroup;
	private int groupid;    //分配给谁了
	private Lessons lessons; //共享的课程
	private int createuserid;
	private String createusername;
	private Timestamp createtime;

	private  float xuefen;   
	// Constructors

	/** default constructor */
	public Lessonshared() {
	}



	// Property accessors

	public int getShareid() {
		return this.shareid;
	}

	public void setShareid(int shareid) {
		this.shareid = shareid;
	}
//
//	public SysGroup getSysGroup() {
//		return this.sysGroup;
//	}
//
//	public void setSysGroup(SysGroup sysGroup) {
//		this.sysGroup = sysGroup;
//	}

	public Lessons getLessons() {
		return this.lessons;
	}

	public void setLessons(Lessons lessons) {
		this.lessons = lessons;
	}

	public int getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}

	public String getCreateusername() {
		return this.createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}



	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}



	/**
	 * @param groupid the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}



	/**
	 * @return the xuefen
	 */
	public float getXuefen() {
		return xuefen;
	}



	/**
	 * @param xuefen the xuefen to set
	 */
	public void setXuefen(float xuefen) {
		this.xuefen = xuefen;
	}

}