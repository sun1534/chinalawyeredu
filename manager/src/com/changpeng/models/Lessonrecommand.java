package com.changpeng.models;

import java.sql.Timestamp;

/**
 * 推荐课程列表 entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessonrecommand implements java.io.Serializable {

	// Fields

	private int id;
	private int lessonid;
	private int userid;
	private int statusid;
	private Timestamp recommanddate;
	
	private String reason;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the lessonid
	 */
	public int getLessonid() {
		return lessonid;
	}

	/**
	 * @param lessonid the lessonid to set
	 */
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the recommanddate
	 */
	public Timestamp getRecommanddate() {
		return recommanddate;
	}

	/**
	 * @param recommanddate the recommanddate to set
	 */
	public void setRecommanddate(Timestamp recommanddate) {
		this.recommanddate = recommanddate;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the statusid
	 */
	public int getStatusid() {
		return statusid;
	}

	/**
	 * @param statusid the statusid to set
	 */
	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

}