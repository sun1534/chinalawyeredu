/**
 * 
 */
package com.changpeng.common.sync.lgpt;

import java.util.Date;

/**
 * 事务所管理员
 * 
 * @author 华锋
 * Jul 11, 2009-4:50:03 PM
 *
 */
public class OrgUserInfo {
	
	private String streamId;
	private String userId;
	private String userName;
	private String password;
	private Date registerTime;
	private String officeLicenseId;
	private String orgId;
	private String dataType;
	private String pxxtExchangeFlag;
	private String oaExchangeFlag;
	private String webExchangeFlag;
	private String createDate;
	/**
	 * @return the streamId
	 */
	public String getStreamId() {
		return streamId;
	}
	/**
	 * @param streamId the streamId to set
	 */
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the registerTime
	 */
	public Date getRegisterTime() {
		return registerTime;
	}
	/**
	 * @param registerTime the registerTime to set
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * @return the officeLicenseId
	 */
	public String getOfficeLicenseId() {
		return officeLicenseId;
	}
	/**
	 * @param officeLicenseId the officeLicenseId to set
	 */
	public void setOfficeLicenseId(String officeLicenseId) {
		this.officeLicenseId = officeLicenseId;
	}
	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return the pxxtExchangeFlag
	 */
	public String getPxxtExchangeFlag() {
		return pxxtExchangeFlag;
	}
	/**
	 * @param pxxtExchangeFlag the pxxtExchangeFlag to set
	 */
	public void setPxxtExchangeFlag(String pxxtExchangeFlag) {
		this.pxxtExchangeFlag = pxxtExchangeFlag;
	}
	/**
	 * @return the oaExchangeFlag
	 */
	public String getOaExchangeFlag() {
		return oaExchangeFlag;
	}
	/**
	 * @param oaExchangeFlag the oaExchangeFlag to set
	 */
	public void setOaExchangeFlag(String oaExchangeFlag) {
		this.oaExchangeFlag = oaExchangeFlag;
	}
	/**
	 * @return the webExchangeFlag
	 */
	public String getWebExchangeFlag() {
		return webExchangeFlag;
	}
	/**
	 * @param webExchangeFlag the webExchangeFlag to set
	 */
	public void setWebExchangeFlag(String webExchangeFlag) {
		this.webExchangeFlag = webExchangeFlag;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
//	流水号	stream_id	VARCHAR2(20)	TRUE	FALSE	TRUE	
//	用户编号	user_id	VARCHAR2(20)	FALSE	FALSE	FALSE	
//	用户名	user_name	VARCHAR2(50)	FALSE	FALSE	FALSE	
//	用户密码	password	VARCHAR2(50)	FALSE	FALSE	FALSE	
//	注册时间	register_time	DATE	FALSE	FALSE	FALSE	
//	律师所执业证号	office_license_id	VARCHAR2(50)	FALSE	FALSE	FALSE	
//	律师所机构ID	org_id	VARCHAR2(50)	FALSE	FALSE	FALSE	
//	数据类型	Data_type	VARCHAR2(10)	FALSE	FALSE	FALSE	
//	数据同步状态	pxxt_Exchange_flag	VARCHAR2(100)	FALSE	FALSE	FALSE	
//	数据同步状态2	OA_Exchange_flag	VARCHAR2(100)	FALSE	FALSE	FALSE	
//	数据同步状态3	WEB_Exchange_flag	VARCHAR2(100)	FALSE	FALSE	FALSE	
//	数据建立日期	Create_date	VARCHAR2(32)	FALSE	FALSE	FALSE	
//	备注1	memo1	VARCHAR2(32)	FALSE	FALSE	FALSE	
//	备注2	memo2	VARCHAR2(32)	FALSE	FALSE	FALSE	
}
