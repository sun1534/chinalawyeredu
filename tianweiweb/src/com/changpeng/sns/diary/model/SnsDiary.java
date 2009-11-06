package com.changpeng.sns.diary.model;

import java.sql.Timestamp;

/**
 * SnsDiary entity. @author MyEclipse Persistence Tools
 */

public class SnsDiary implements java.io.Serializable {

	// Fields

	private Integer diaryid;
	private Integer diarytypeId;
	private Integer userid;
	private Short top;
	private String tile;
	private String content;
	private Integer clickCount;
	private Integer replyCount;
	private Timestamp createTime;
	private String createIp;
	private Timestamp updateTime;
	private Short privateFlag;
	private Short statusid;
	
	// Constructors

	

	/** default constructor */
	public SnsDiary() {
	}

	/** full constructor */
	public SnsDiary(Integer diarytypeId, Integer userid, Short top,
			String tile, String content, Integer clickCount,
			Integer replyCount, Timestamp createTime, String createIp,
			Timestamp updateTime, Short privateFlag) {
		this.diarytypeId = diarytypeId;
		this.userid = userid;
		this.top = top;
		this.tile = tile;
		this.content = content;
		this.clickCount = clickCount;
		this.replyCount = replyCount;
		this.createTime = createTime;
		this.createIp = createIp;
		this.updateTime = updateTime;
		this.privateFlag = privateFlag;
	}

	// Property accessors

	public Integer getDiaryid() {
		return this.diaryid;
	}

	public void setDiaryid(Integer diaryid) {
		this.diaryid = diaryid;
	}

	public Integer getDiarytypeId() {
		return this.diarytypeId;
	}

	public void setDiarytypeId(Integer diarytypeId) {
		this.diarytypeId = diarytypeId;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Short getTop() {
		return this.top;
	}

	public void setTop(Short top) {
		this.top = top;
	}

	public String getTile() {
		return this.tile;
	}

	public void setTile(String tile) {
		this.tile = tile;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getClickCount() {
		return this.clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getReplyCount() {
		return this.replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
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

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Short getPrivateFlag() {
		return this.privateFlag;
	}

	public void setPrivateFlag(Short privateFlag) {
		this.privateFlag = privateFlag;
	}
	public Short getStatusid() {
		return statusid;
	}

	public void setStatusid(Short statusid) {
		this.statusid = statusid;
	}
}