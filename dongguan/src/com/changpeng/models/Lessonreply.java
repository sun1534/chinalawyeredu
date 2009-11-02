package com.changpeng.models;

import java.util.Date;

/**
 * Lessonreply entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessonreply implements java.io.Serializable {

	// Fields

	private int replyid;
	private Lessons lessons;
	private String replyuser;
	private String replycontent;
	private Date replytime;

	// Constructors

	/** default constructor */
	public Lessonreply() {
	}

	/** minimal constructor */
	public Lessonreply(Lessons lessons, String replyuser, Date replytime) {
		this.lessons = lessons;
		this.replyuser = replyuser;
		this.replytime = replytime;
	}

	/** full constructor */
	public Lessonreply(Lessons lessons, String replyuser, String replycontent,
			Date replytime) {
		this.lessons = lessons;
		this.replyuser = replyuser;
		this.replycontent = replycontent;
		this.replytime = replytime;
	}

	// Property accessors

	public int getReplyid() {
		return this.replyid;
	}

	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}

	public Lessons getLessons() {
		return this.lessons;
	}

	public void setLessons(Lessons lessons) {
		this.lessons = lessons;
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

	public Date getReplytime() {
		return this.replytime;
	}

	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

}