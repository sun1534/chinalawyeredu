/**
 * TSysLog.java
 */
package com.sxit.models.log;

import java.sql.Timestamp;

/**
 * 系统操作日志
 * 
 * @author 华锋
 * 2008-2-22 上午10:38:45
 *
 */
public class SysLog {
	private int logid;
	private int userid;
	//和某次登录对应起来
	private int loginid;
//	private String module;
	private String rightCode;
	private Timestamp opTime;
	
	private String opResult;
	
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setLogid(int logid){
	    this.logid=logid;	
	}
	public int getLogid(){
		return this.logid;
	}
	
	public void setUserid(int userid){
		this.userid=userid;
	}
	public int getUserid(){
		return this.userid;
	}
	public void setLoginid(int loginid){
		this.loginid=loginid;
	}
	public int getLoginid(){
		return this.loginid;
	}
//	public void setModule(String module){
//		this.module=module;
//	}
//	public String getModule(){
//		return this.module;
//	}
	public void setRightCode(String rightCode){
		this.rightCode=rightCode;
	}
	public String getRightCode(){
		return this.rightCode;
	}
	public void setOpTime(Timestamp optime){
		this.opTime=optime;
	}
	public Timestamp getOpTime(){
		return this.opTime;
	}
	public void setOpResult(String opResult){
		this.opResult=opResult;
	}
	public String getOpResult(){
		return this.opResult;
	}
}
