package com.changpeng.help.model;

import java.util.Date;

/**
 * ThlpMsg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ThlpMsg implements java.io.Serializable {

	// Fields

	private long msgid;
	private String title;
	private String contents;
	private long fromuid;
	private String fromuname;
	private long touser;
	private Date createtime;
	public long getMsgid() {
		return msgid;
	}
	public String getTitle() {
		return title;
	}
	public String getContents() {
		return contents;
	}
	public long getFromuid() {
		return fromuid;
	}
	public String getFromuname() {
		return fromuname;
	}
	public long getTouser() {
		return touser;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setMsgid(long msgid) {
		this.msgid = msgid;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setFromuid(long fromuid) {
		this.fromuid = fromuid;
	}
	public void setFromuname(String fromuname) {
		this.fromuname = fromuname;
	}
	public void setTouser(long touser) {
		this.touser = touser;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	
}