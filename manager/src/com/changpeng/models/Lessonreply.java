package com.changpeng.models;

import java.sql.Timestamp;

/**
 * Lessonreply entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessonreply implements java.io.Serializable {

	// Fields

	private int replyid;
	private int lessonid;
	private int replyuserid;
	private String replyuser;
	private String replycontent;
	private Timestamp replytime;

	// Constructors

	/** default constructor */
	public Lessonreply() {
	}


	// Property accessors

	public int getReplyid() {
		return this.replyid;
	}

	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}



	public int getReplyuserid() {
		return this.replyuserid;
	}

	public void setReplyuserid(int replyuserid) {
		this.replyuserid = replyuserid;
	}

	public String getReplyuser() {
		return this.replyuser;
	}

	public void setReplyuser(String replyuser) {
		this.replyuser = replyuser;
	}

	public String getReplycontent() {
		return this.replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public Timestamp getReplytime() {
		return this.replytime;
	}

	public void setReplytime(Timestamp replytime) {
		this.replytime = replytime;
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

}