/**
 * TSysLoginLog.java
 */
package com.changpeng.models.system;

import java.sql.Timestamp;

/**
 * 
 * 用户登陆日志信息
 * @author 华锋
 * 2008-2-22 上午10:40:44
 *
 *1、新增一个和系统启动时候绑定的contextid
 *2、
 *
 */
public class SysLoginLog {

	private int loginid;
	private int userid;
	private Timestamp loginTime;
	private Timestamp logoutTime;
	private String loginip;
	//private short loginCount;
	private boolean islast;
	private int inSysTime;
	private String contextid;
	private String remarks;
	private String loginremarks;
	public void setLoginid(int loginid){
	    this.loginid=loginid;	
	}
	public int getLoginid(){
		return this.loginid;
	}
	
	public void setUserid(int userid){
		this.userid=userid;
	}
	public int getUserid(){
		return this.userid;
	}
	
	public void setLoginTime(Timestamp logintime){
		this.loginTime=logintime;
	}
	public Timestamp getLoginTime(){
		return this.loginTime;
	}
	
	public void setLogoutTime(Timestamp logoutime){
		this.logoutTime=logoutime;
	}
	public Timestamp getLogoutTime(){
		return this.logoutTime;
	}
	
	public void setLoginip(String loginip){
		this.loginip=loginip;
	}
	public String getLoginip(){
		return this.loginip;
	}
	
//	public void setLoginCount(short logincount){
//		this.loginCount=logincount;
//	}
//	public short getLoginCount(){
//		return this.loginCount;
//	}
	
	public void setIslast(boolean islast){
		this.islast=islast;
	}
	public boolean getIslast(){
		return this.islast;
	}
	
	public void setInSysTime(int insystime){
		this.inSysTime=insystime;
	}
	
	public int getInSysTime(){
		return this.inSysTime;
	}
	
	public void setContextid(String id){
		this.contextid=id;
	}
	
	public String getContextid(){
		return this.contextid;
	}
	
	public void setRemarks(String remarks){
		this.remarks=remarks;
	}
	
	public String getRemarks(){
		return this.remarks;
	}
	public void setLoginremarks(String remarks){
		this.loginremarks=remarks;
	}
	
	public String getLoginremarks(){
		return this.loginremarks;
	}
	 
}