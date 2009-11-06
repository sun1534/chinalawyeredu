package com.changpeng.global.model;

import java.sql.Timestamp;

/**
 * GlobalCommentfeedback entity. @author MyEclipse Persistence Tools
 */

public class GlobalCommentfeedback implements java.io.Serializable {

	// Fields

	private Integer commentbackid;
	private String type;
	private String content;
	private Timestamp commenttime;
	private Integer userid;
	private String username;
	private String ip;

	// Constructors

	/** default constructor */
	public GlobalCommentfeedback() {
	}

	/** full constructor */
	public GlobalCommentfeedback(String type, String content,
			Timestamp commenttime, Integer userid, String username, String ip) {
		this.type = type;
		this.content = content;
		this.commenttime = commenttime;
		this.userid = userid;
		this.username = username;
		this.ip = ip;
	}

	// Property accessors

	public Integer getCommentbackid() {
		return this.commentbackid;
	}

	public void setCommentbackid(Integer commentbackid) {
		this.commentbackid = commentbackid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCommenttime() {
		return this.commenttime;
	}

	public void setCommenttime(Timestamp commenttime) {
		this.commenttime = commenttime;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}