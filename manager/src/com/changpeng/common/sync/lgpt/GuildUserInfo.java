/**
 * 
 */
package com.changpeng.common.sync.lgpt;

/**
 * 律协管理员
 * 
 * @author 华锋
 * Jul 11, 2009-4:49:32 PM
 *
 */
public class GuildUserInfo {

	private String streamId;
	private String userId;
	private String loginName;
	private String userName;
	private String password;
	private String depatId;
	private String dataType;
	private String pxxtExchangeFlag;
	private String oaExchangeFlag;
	private String webExchangeFlag;
	private String memo1;
	private String memo2;
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
	 * @return the depatId
	 */
	public String getDepatId() {
		return depatId;
	}
	/**
	 * @param depatId the depatId to set
	 */
	public void setDepatId(String depatId) {
		this.depatId = depatId;
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
	 * @return the memo1
	 */
	public String getMemo1() {
		return memo1;
	}
	/**
	 * @param memo1 the memo1 to set
	 */
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	/**
	 * @return the memo2
	 */
	public String getMemo2() {
		return memo2;
	}
	/**
	 * @param memo2 the memo2 to set
	 */
	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}
	
	
	
//	流水号	stream_id	VARCHAR2(20)	TRUE	FALSE	TRUE	
//	用户编号	user_id	VARCHAR2(20)	FALSE	FALSE	FALSE	
//	登录名	login_name	VARCHAR2(50)	FALSE	FALSE	FALSE	
//	用户名	user_name	VARCHAR2(50)	FALSE	FALSE	FALSE	
//	用户密码	password	VARCHAR2(50)	FALSE	FALSE	FALSE	
//	所属部门	depat_id	VARCHAR2(50)	FALSE	FALSE	FALSE	
//	所属机构	org_id	VARCHAR2(50)	FALSE	FALSE	FALSE	
//	数据类型	Data_type	VARCHAR2(10)	FALSE	FALSE	FALSE	
//	数据同步状态	pxxt_Exchange_flag	VARCHAR2(100)	FALSE	FALSE	FALSE	
//	数据同步状态2	OA_Exchange_flag	VARCHAR2(100)	FALSE	FALSE	FALSE	
//	数据同步状态3	WEB_Exchange_flag	VARCHAR2(100)	FALSE	FALSE	FALSE	
//	数据建立日期	Create_date	VARCHAR2(32)	FALSE	FALSE	FALSE	
//	备注1	memo1	VARCHAR2(32)	FALSE	FALSE	FALSE	
//	备注2	memo2	VARCHAR2(32)	FALSE	FALSE	FALSE	
}
