package com.changpeng.core.message.model;

import java.sql.Timestamp;

/**
 * CoreSysMsg entity. @author MyEclipse Persistence Tools
 */

public class CoreSysMsg implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer destUserid;
	private Timestamp sendTime;
	private Short sendType;
	private String content;

	// Constructors

	/** default constructor */
	public CoreSysMsg() {
	}

	/** full constructor */
	public CoreSysMsg(Integer destUserid, Timestamp sendTime, Short sendType,
			String content) {
		this.destUserid = destUserid;
		this.sendTime = sendTime;
		this.sendType = sendType;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDestUserid() {
		return this.destUserid;
	}

	public void setDestUserid(Integer destUserid) {
		this.destUserid = destUserid;
	}

	public Timestamp getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Short getSendType() {
		return this.sendType;
	}

	public void setSendType(Short sendType) {
		this.sendType = sendType;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}