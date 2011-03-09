/**
 * Userinfo.java
 */
package com.uu800.admin.base.entity;

import java.sql.Timestamp;
import java.util.List;
/**
 * 
 * 存储用户登录信息的userinfo对象
 * 
 * @author 华锋
 * Jul 10, 20105:59:02 PM
 */
public class Userinfo {

	/**
	 * 主键id
	 */
	protected long id;
	/**
	 * 姓名
	 */
	protected String userName;
	/**
	 * 登录名
	 */
	protected String loginName;
	
	/**
	 * 图像logo,跟名字一起显示的头像信息
	 */
	protected String pic;
	
	/**
	 * 登录ip
	 */
	protected String loginIp;
	/**
	 * 总计登录次数
	 */
	protected int loginCount;
	/**
	 * 最后一次登录时间
	 */
	protected Timestamp lastLoginTime;
	/**
	 * log_login表里的主键
	 */
	protected int loginId;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return the pic
	 */
	public String getPic() {
		return pic;
	}
	/**
	 * @param pic the pic to set
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	/**
	 * @return the loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}
	/**
	 * @param loginIp the loginIp to set
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	/**
	 * @return the loginCount
	 */
	public int getLoginCount() {
		return loginCount;
	}
	/**
	 * @param loginCount the loginCount to set
	 */
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	/**
	 * @return the lastLoginTime
	 */
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * @return the loginId
	 */
	public int getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	/**
	 * 这个需要被override
	 * @param rightCode
	 * @return
	 */
	public boolean hasRight(String rightCode){
		
		return true;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
}