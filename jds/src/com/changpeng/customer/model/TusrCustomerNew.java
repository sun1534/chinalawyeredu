package com.changpeng.customer.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TusrCustomerNew entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TusrCustomerNew implements java.io.Serializable {

	// Fields

	private int customerid;
	private String username;
	private String usernameen;
	private String idcard;
	//1身份证
	private int idcardtype=1;
	private String company;
	private String compaddr;
	private String comppostcode;
	private String nativeplace;
	private String homeaddr;
	private String homephone;
	private String birthday;
	private String mobile1;
	private String mobile2;
	private String compphone;
	private String compfax;
	private String compemail;
	private String qq;
	private String msn;
	//1:VIP 2:一般
	private int customerflag=2;
	//1:机构客户 2:个人客户 3当事人客户
	private int customertype=3;
	//第一次来源2非诉1信用卡3诉讼4手动新增
	private int createsrc;
	//新增人
	private int createuser;
	private Timestamp createtime=new java.sql.Timestamp(System.currentTimeMillis());
	private String remarks;
	private int createsrcid;
	private String idcardaddr;
	private String personalemail;
	private Set tusrCustomerServices = new HashSet(0);

	// Constructors

	/** default constructor */
	public TusrCustomerNew() {
	}

	// Property accessors

	public int getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameen() {
		return this.usernameen;
	}

	public void setUsernameen(String usernameen) {
		this.usernameen = usernameen;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public int getIdcardtype() {
		return this.idcardtype;
	}

	public void setIdcardtype(int idcardtype) {
		this.idcardtype = idcardtype;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompaddr() {
		return this.compaddr;
	}

	public void setCompaddr(String compaddr) {
		this.compaddr = compaddr;
	}

	public String getComppostcode() {
		return this.comppostcode;
	}

	public void setComppostcode(String comppostcode) {
		this.comppostcode = comppostcode;
	}

	public String getNativeplace() {
		return this.nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	public String getHomeaddr() {
		return this.homeaddr;
	}

	public void setHomeaddr(String homeaddr) {
		this.homeaddr = homeaddr;
	}

	public String getHomephone() {
		return this.homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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

	public String getCompphone() {
		return this.compphone;
	}

	public void setCompphone(String compphone) {
		this.compphone = compphone;
	}

	public String getCompfax() {
		return this.compfax;
	}

	public void setCompfax(String compfax) {
		this.compfax = compfax;
	}

	public String getCompemail() {
		return this.compemail;
	}

	public void setCompemail(String compemail) {
		this.compemail = compemail;
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

	public int getCustomerflag() {
		return this.customerflag;
	}

	public void setCustomerflag(int customerflag) {
		this.customerflag = customerflag;
	}

	public int getCustomertype() {
		return this.customertype;
	}

	public void setCustomertype(int customertype) {
		this.customertype = customertype;
	}

	public int getCreatesrc() {
		return this.createsrc;
	}

	public void setCreatesrc(int createsrc) {
		this.createsrc = createsrc;
	}

	public int getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(int createuser) {
		this.createuser = createuser;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getCreatesrcid() {
		return this.createsrcid;
	}

	public void setCreatesrcid(int createsrcid) {
		this.createsrcid = createsrcid;
	}

	public String getIdcardaddr() {
		return this.idcardaddr;
	}

	public void setIdcardaddr(String idcardaddr) {
		this.idcardaddr = idcardaddr;
	}

	public String getPersonalemail() {
		return this.personalemail;
	}

	public void setPersonalemail(String personalemail) {
		this.personalemail = personalemail;
	}

	public Set getTusrCustomerServices() {
		return this.tusrCustomerServices;
	}

	public void setTusrCustomerServices(Set tusrCustomerServices) {
		this.tusrCustomerServices = tusrCustomerServices;
	}

}