package com.changpeng.help.model;

import java.util.Date;

/**
 * ThlpSuggest entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ThlpSuggest implements java.io.Serializable {

	// Fields

	private long suggestid;
	private String title;
	private String contents;
	private String createuser;
	private Date createtime;
	private long parentid;
	public long getSuggestid() {
		return suggestid;
	}
	public String getTitle() {
		return title;
	}
	public String getContents() {
		return contents;
	}
	public String getCreateuser() {
		return createuser;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public long getParentid() {
		return parentid;
	}
	public void setSuggestid(long suggestid) {
		this.suggestid = suggestid;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	
}