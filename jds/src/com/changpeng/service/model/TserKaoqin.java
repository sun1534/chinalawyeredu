package com.changpeng.service.model;

import java.util.Date;

/**
 * TserKaoqin entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TserKaoqin implements java.io.Serializable {

	// Fields

	private long kaoqinid;
	private String username;
	private int kqflag;
	private String kqdate;
	private String kqtime;
	private int kqresult;
	private Date createtime;
	public long getKaoqinid() {
		return kaoqinid;
	}
	public String getUsername() {
		return username;
	}
	public int getKqflag() {
		return kqflag;
	}
	public String getKqdate() {
		return kqdate;
	}
	public String getKqtime() {
		return kqtime;
	}
	public int getKqresult() {
		return kqresult;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setKaoqinid(long kaoqinid) {
		this.kaoqinid = kaoqinid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setKqflag(int kqflag) {
		this.kqflag = kqflag;
	}
	public void setKqdate(String kqdate) {
		this.kqdate = kqdate;
	}
	public void setKqtime(String kqtime) {
		this.kqtime = kqtime;
	}
	public void setKqresult(int kqresult) {
		this.kqresult = kqresult;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	
}