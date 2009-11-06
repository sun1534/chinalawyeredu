package com.changpeng.sns.userwall.model;

import java.sql.Timestamp;

/**
 * SnsUserWall entity. @author MyEclipse Persistence Tools
 */

public class SnsUserWall implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer replyUserid;
	private Integer parentId;
	private Short flag;
	private Timestamp createTime;
	private String createIp;
	private String content;

	// Constructors

	/** default constructor */
	public SnsUserWall() {
	}

	/** full constructor */
	public SnsUserWall(Integer userid, Integer replyUserid, Integer parentId,
			Short flag, Timestamp createTime, String createIp, String content) {
		this.userid = userid;
		this.replyUserid = replyUserid;
		this.parentId = parentId;
		this.flag = flag;
		this.createTime = createTime;
		this.createIp = createIp;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getReplyUserid() {
		return this.replyUserid;
	}

	public void setReplyUserid(Integer replyUserid) {
		this.replyUserid = replyUserid;
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

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateIp() {
		return this.createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}