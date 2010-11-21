package com.cqmm.bean;

import java.util.Date;

import com.cqmm.common.DateUtil;

public class CmdLog {
	private int id;
	private int userid;
	
	private String devicename;
	private String optname;
	private String result;
	private String optime;
	public CmdLog(){
		optime=DateUtil.getSimpleDateTime(new Date());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getOptname() {
		return optname;
	}
	public void setOptname(String optname) {
		this.optname = optname;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getOptime() {
		return optime;
	}
	public void setOptime(String optime) {
		this.optime = optime;
	}
	
	
	
}
