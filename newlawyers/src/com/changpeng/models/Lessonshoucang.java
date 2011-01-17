package com.changpeng.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Lessonshoucang entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessonshoucang implements java.io.Serializable {

	// Fields

	private int id;
	private int lawyerid;
	private int lessonid;
	private Timestamp shoucangdate;
	private String remarks;

	// Constructors

	/** default constructor */
	public Lessonshoucang() {
	}


	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLawyerid() {
		return this.lawyerid;
	}

	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	public int getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public Timestamp getShoucangdate() {
		return this.shoucangdate;
	}

	public void setShoucangdate(Timestamp shoucangdate) {
		this.shoucangdate = shoucangdate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}