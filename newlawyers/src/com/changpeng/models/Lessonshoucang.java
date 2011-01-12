package com.changpeng.models;

import java.util.Date;

/**
 * Lessonshoucang entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessonshoucang implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer lawyerid;
	private Integer lessonid;
	private Date shoucangdate;
	private String remarks;

	// Constructors

	/** default constructor */
	public Lessonshoucang() {
	}

	/** minimal constructor */
	public Lessonshoucang(Integer lawyerid, Integer lessonid, Date shoucangdate) {
		this.lawyerid = lawyerid;
		this.lessonid = lessonid;
		this.shoucangdate = shoucangdate;
	}

	/** full constructor */
	public Lessonshoucang(Integer lawyerid, Integer lessonid, Date shoucangdate, String remarks) {
		this.lawyerid = lawyerid;
		this.lessonid = lessonid;
		this.shoucangdate = shoucangdate;
		this.remarks = remarks;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLawyerid() {
		return this.lawyerid;
	}

	public void setLawyerid(Integer lawyerid) {
		this.lawyerid = lawyerid;
	}

	public Integer getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(Integer lessonid) {
		this.lessonid = lessonid;
	}

	public Date getShoucangdate() {
		return this.shoucangdate;
	}

	public void setShoucangdate(Date shoucangdate) {
		this.shoucangdate = shoucangdate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}