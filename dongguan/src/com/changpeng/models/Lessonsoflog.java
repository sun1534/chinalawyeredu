package com.changpeng.models;

/**
 * Lessonsoflog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessonsoflog implements java.io.Serializable {

	// Fields

	private Integer logid;
	private Integer lessonid;
	private Integer lessonidOfserver;
	private Short flag;
	private String fromAddr;

	// Constructors

	/** default constructor */
	public Lessonsoflog() {
	}

	/** minimal constructor */
	public Lessonsoflog(Integer lessonid) {
		this.lessonid = lessonid;
	}

	/** full constructor */
	public Lessonsoflog(Integer lessonid, Integer lessonidOfserver, Short flag,
			String fromAddr) {
		this.lessonid = lessonid;
		this.lessonidOfserver = lessonidOfserver;
		this.flag = flag;
		this.fromAddr = fromAddr;
	}

	// Property accessors

	public Integer getLogid() {
		return this.logid;
	}

	public void setLogid(Integer logid) {
		this.logid = logid;
	}

	public Integer getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(Integer lessonid) {
		this.lessonid = lessonid;
	}

	public Integer getLessonidOfserver() {
		return this.lessonidOfserver;
	}

	public void setLessonidOfserver(Integer lessonidOfserver) {
		this.lessonidOfserver = lessonidOfserver;
	}

	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
	}

	public String getFromAddr() {
		return this.fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

}