package com.changpeng.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Lawyers entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lawyers implements java.io.Serializable {

	// Fields

	private int lawyerid;
	private int lawyertype;
	private String lawyername;
	private String lawyerenname;
	private String lawyerno;
	private String certno;
	private String systemno;
	private String loginname;
	private String passwd;
	private String cardno;
	private Date carddate;
	private String gender;
	private Date birthday;
	private boolean ismarrige;
	private int countryid;
	private int provinceid;
	private int cityid;
	private int districtid;
	private int theoffice;// 属于哪个事务所
	private int directunion; // 直属律协
	private int provinceunion; // 省律协
	private Date zhiyedate;
	private String photo;
	private String photoname;
	private String specility;
	private String school;
	private int foreignlan;
	private String foreignlevel;
	private int degree;
	private int policy;
	private Date worktime;
	private String postcode;
	private String mobile1;
	private String mobile2;
	private String homephone;
	private String officephone;
	private String fax;
	private String qq;
	private String msn;
	private String email;
	private int status;
	private int regsrc;
	private Timestamp createtime;
	private int createuser;
	private String createusername;
	private String remarks;
	private float dabiaofen;

	// Constructors

	/**
	 * @return the dabiaofen
	 */
	public float getDabiaofen() {
		return dabiaofen;
	}

	/**
	 * @param dabiaofen
	 *            the dabiaofen to set
	 */
	public void setDabiaofen(float dabiaofen) {
		this.dabiaofen = dabiaofen;
	}

	/** default constructor */
	public Lawyers() {
	}

	// Property accessors

	public int getLawyerid() {
		return this.lawyerid;
	}

	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	public int getLawyertype() {
		return this.lawyertype;
	}

	public void setLawyertype(int lawyertype) {
		this.lawyertype = lawyertype;
	}

	public String getLawyername() {
		return this.lawyername;
	}

	public void setLawyername(String lawyername) {

		this.lawyername = lawyername;
	}

	public String getLawyerenname() {

		return this.lawyerenname;
	}

	public void setLawyerenname(String lawyerenname) {
		this.lawyerenname = lawyerenname;
	}

	public String getLawyerno() {
		return this.lawyerno;
	}

	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

	public String getCertno() {
		return this.certno;
	}

	public void setCertno(String certno) {
		this.certno = certno;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public Date getCarddate() {
		return this.carddate;
	}

	public void setCarddate(Date carddate) {
		this.carddate = carddate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean getIsmarrige() {
		return this.ismarrige;
	}

	public void setIsmarrige(boolean ismarrige) {
		this.ismarrige = ismarrige;
	}

	public int getCountryid() {
		return this.countryid;
	}

	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}

	public int getProvinceid() {
		return this.provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public int getCityid() {
		return this.cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public int getDistrictid() {
		return this.districtid;
	}

	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}

	public int getTheoffice() {
		return this.theoffice;
	}

	public void setTheoffice(int theoffice) {
		this.theoffice = theoffice;
	}

	public int getDirectunion() {
		return this.directunion;
	}

	public void setDirectunion(int directunion) {
		this.directunion = directunion;
	}

	public int getProvinceunion() {
		return this.provinceunion;
	}

	public void setProvinceunion(int provinceunion) {
		this.provinceunion = provinceunion;
	}

	public Date getZhiyedate() {
		return this.zhiyedate;
	}

	public void setZhiyedate(Date zhiyedate) {
		this.zhiyedate = zhiyedate;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhotoname() {
		return this.photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public String getSpecility() {
		return this.specility;
	}

	public void setSpecility(String specility) {
		this.specility = specility;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getForeignlan() {
		return this.foreignlan;
	}

	public void setForeignlan(int foreignlan) {
		this.foreignlan = foreignlan;
	}

	public String getForeignlevel() {
		return this.foreignlevel;
	}

	public void setForeignlevel(String foreignlevel) {
		this.foreignlevel = foreignlevel;
	}

	public int getDegree() {
		return this.degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getPolicy() {
		return this.policy;
	}

	public void setPolicy(int policy) {
		this.policy = policy;
	}

	public Date getWorktime() {
		return this.worktime;
	}

	public void setWorktime(Date worktime) {
		this.worktime = worktime;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMobile1() {
		return this.mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return this.mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getHomephone() {
		return this.homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getOfficephone() {
		return this.officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRegsrc() {
		return this.regsrc;
	}

	public void setRegsrc(int regsrc) {
		this.regsrc = regsrc;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public int getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(int createuser) {
		this.createuser = createuser;
	}

	public String getCreateusername() {
		return this.createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 绑定这个用户的此次登录的id
	 */
	private int loginId;

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public int getLoginId() {
		return this.loginId;
	}

	/**
	 * 记录最近一次的登录时间
	 */
	private Timestamp lastLoginTime;

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**
	 * 总计登录次数
	 */
	private int loginCount;

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getLoginCount() {
		return this.loginCount;
	}

	/**
	 * @return the loginname
	 */
	public String getLoginname() {
		return loginname;
	}

	/**
	 * @param loginname
	 *            the loginname to set
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	/**
	 * @return the systemno
	 */
	public String getSystemno() {
		return systemno;
	}

	/**
	 * @param systemno
	 *            the systemno to set
	 */
	public void setSystemno(String systemno) {
		this.systemno = systemno;
	}

}