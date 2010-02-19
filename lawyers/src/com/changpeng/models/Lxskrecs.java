package com.changpeng.models;

import java.sql.Timestamp;

/**
 * Lxskrecs entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lxskrecs implements java.io.Serializable {

	// Fields

	private int skrecid;
	private String kahao;
	private int lessonid;
	private Timestamp skdate;
	private String skmode;
	private String ischeck;
	private Timestamp uploadtime;
	private Float timelong=0f;
	private String remarks;
	private int jifenyear;
	private int groupid;
	
	// Constructors

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
	 * @return the jifenyear
	 */
	public int getJifenyear() {
		return jifenyear;
	}


	/**
	 * @param jifenyear the jifenyear to set
	 */
	public void setJifenyear(int jifenyear) {
		this.jifenyear = jifenyear;
	}
	// Constructors

	/** default constructor */
	public Lxskrecs() {
	}

	
	// Property accessors

	public int getSkrecid() {
		return this.skrecid;
	}

	public void setSkrecid(int skrecid) {
		this.skrecid = skrecid;
	}

	public String getKahao() {
		return this.kahao;
	}

	public void setKahao(String kahao) {
		this.kahao = kahao;
	}

	public int getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}



	public String getSkmode() {
		return this.skmode;
	}

	public void setSkmode(String skmode) {
		this.skmode = skmode;
	}

	public String getIscheck() {
		return this.ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public Timestamp getUploadtime() {
		return this.uploadtime;
	}

	public void setUploadtime(Timestamp uploadtime) {
		this.uploadtime = uploadtime;
	}

	public Float getTimelong() {
		return this.timelong;
	}

	public void setTimelong(Float timelong) {
		this.timelong = timelong;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the skdate
	 */
	public Timestamp getSkdate() {
		return skdate;
	}

	/**
	 * @param skdate the skdate to set
	 */
	public void setSkdate(Timestamp skdate) {
		this.skdate = skdate;
	}

}