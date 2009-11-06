package com.changpeng.core.user.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * CoreUser entity. @author MyEclipse Persistence Tools
 */

public class CoreUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mobile;
	private String userName;
	private String loginName;
	private String pwd;
	private Short userRole;
	private Timestamp regTime;
	private String regIp;
	private Short status;
	private Short userType;
	private Integer provinceId;
	private Integer cityId;
	private Integer districtId;
	private String pic;
	private String logo;
	private Short gender;
	private String privateFlag;
	private String sign;
	private Date birthday;
	private Timestamp modifyTime;
	private String cardno;
	private String entno;
	private Integer approveType;
	private Integer waitid;
	// Constructors

	/** default constructor */
	public CoreUser() {
	}

	/** minimal constructor */
	public CoreUser(String mobile, String userName, String pwd, Short userRole,
			Timestamp regTime, Short status, Short userType,
			Integer provinceId, Integer cityId, Integer districtId) {
		this.mobile = mobile;
		this.userName = userName;
		this.pwd = pwd;
		this.userRole = userRole;
		this.regTime = regTime;

		this.status = status;
		this.userType = userType;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
	}

	/** full constructor */
	public CoreUser(String mobile, String userName, String loginName,
			String pwd, Short userRole, Timestamp regTime, String regIp,
			Short status, Short userType,
			Integer provinceId, Integer cityId, Integer districtId, String pic,
			String logo, Short gender, String privateFlag, String sign,
			Date birthday, Timestamp modifyTime) {
		this.mobile = mobile;
		this.userName = userName;
		this.loginName = loginName;
		this.pwd = pwd;
		this.userRole = userRole;
		this.regTime = regTime;
		this.regIp = regIp;
		this.status = status;
		this.userType = userType;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
		this.pic = pic;
		this.logo = logo;
		this.gender = gender;
		this.privateFlag = privateFlag;
		this.sign = sign;
		this.birthday = birthday;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Short getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Short userRole) {
		this.userRole = userRole;
	}

	public Timestamp getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public String getRegIp() {
		return this.regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getUserType() {
		return this.userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Short getGender() {
		return this.gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public String getPrivateFlag() {
		return this.privateFlag;
	}

	public void setPrivateFlag(String privateFlag) {
		this.privateFlag = privateFlag;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getPiclogo(){
		StringBuffer sb=new StringBuffer(com.changpeng.common.sysdata.CommonData.DEFAULT_LOGO_URL);
		if(pic==null||pic.equals(""))
			sb.append(com.changpeng.common.sysdata.CommonData.DEFAULT_FAM_PIC);
		else
		if(pic.startsWith("default"))
			sb.append(pic);
		else
		    sb.append(com.changpeng.common.util.FileDirUtil.getDirByUid(id)).append(pic);
		return sb.toString();
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getEntno() {
		return entno;
	}

	public void setEntno(String entno) {
		this.entno = entno;
	}

	public Integer getApproveType() {
		return approveType;
	}

	public void setApproveType(Integer approveType) {
		this.approveType = approveType;
	}

	public Integer getWaitid() {
		return waitid;
	}

	public void setWaitid(Integer waitid) {
		this.waitid = waitid;
	}
}