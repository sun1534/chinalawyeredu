package com.changpeng.models;

import java.sql.Timestamp;

/**
 * Lxnetrecs entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lxnetrecs implements java.io.Serializable {

	// Fields

	private int netrecsid;
	private int lessonid;
	private int userid;
	private Float allminutes=0f;
	private Float lookedminutes=0f;
	private Timestamp lasttime;
	private String remarks;

	// Constructors

	/** default constructor */
	public Lxnetrecs() {
	}

	
	// Property accessors

	public int getNetrecsid() {
		return this.netrecsid;
	}

	public void setNetrecsid(int netrecsid) {
		this.netrecsid = netrecsid;
	}

	public int getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Float getAllminutes() {
		return this.allminutes;
	}

	public void setAllminutes(Float allminutes) {
		this.allminutes = allminutes;
	}

	public Float getLookedminutes() {
		return this.lookedminutes;
	}

	public void setLookedminutes(Float lookedminutes) {
		this.lookedminutes = lookedminutes;
	}

	public Timestamp getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Timestamp lasttime) {
		this.lasttime = lasttime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}