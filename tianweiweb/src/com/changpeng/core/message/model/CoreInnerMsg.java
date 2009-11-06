package com.changpeng.core.message.model;

import java.sql.Timestamp;

/**
 * CoreInnerMsg entity. @author MyEclipse Persistence Tools
 */

public class CoreInnerMsg implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sendUserid;
	private Timestamp sendTime;
	private Integer parentId;
	private Short flag;
	private String content;
	private String title;

	// Constructors

	/** default constructor */
	public CoreInnerMsg() {
	}

	/** minimal constructor */
	public CoreInnerMsg(Integer sendUserid, Timestamp sendTime,
			Integer parentId, Short flag, String content) {
		this.sendUserid = sendUserid;
		this.sendTime = sendTime;
		this.parentId = parentId;
		this.flag = flag;
		this.content = content;
	}

	/** full constructor */
	public CoreInnerMsg(Integer sendUserid, Timestamp sendTime,
			Integer parentId, Short flag, String content, String title) {
		this.sendUserid = sendUserid;
		this.sendTime = sendTime;
		this.parentId = parentId;
		this.flag = flag;
		this.content = content;
		this.title = title;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSendUserid() {
		return this.sendUserid;
	}

	public void setSendUserid(Integer sendUserid) {
		this.sendUserid = sendUserid;
	}

	public Timestamp getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}