package com.changpeng.sns.photo.model;

import java.sql.Timestamp;

/**
 * SnsPhotoComment entity. @author MyEclipse Persistence Tools
 */

public class SnsPhotoComment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer photoid;
	private Integer userid;
	private Integer replyFloorid;
	private Short hiddenFlag;
	private Short anonymousFlag;
	private Timestamp createTime;
	private String content;

	// Constructors

	/** default constructor */
	public SnsPhotoComment() {
	}

	/** full constructor */
	public SnsPhotoComment(Integer photoid, Integer userid,
			Integer replyFloorid, Short hiddenFlag, Short anonymousFlag,
			Timestamp createTime, String content) {
		this.photoid = photoid;
		this.userid = userid;
		this.replyFloorid = replyFloorid;
		this.hiddenFlag = hiddenFlag;
		this.anonymousFlag = anonymousFlag;
		this.createTime = createTime;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPhotoid() {
		return this.photoid;
	}

	public void setPhotoid(Integer photoid) {
		this.photoid = photoid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getReplyFloorid() {
		return this.replyFloorid;
	}

	public void setReplyFloorid(Integer replyFloorid) {
		this.replyFloorid = replyFloorid;
	}

	public Short getHiddenFlag() {
		return this.hiddenFlag;
	}

	public void setHiddenFlag(Short hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}

	public Short getAnonymousFlag() {
		return this.anonymousFlag;
	}

	public void setAnonymousFlag(Short anonymousFlag) {
		this.anonymousFlag = anonymousFlag;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}