package com.changpeng.customer.model;

import java.util.Date;

/**
 * TusrCustomer entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TusrCustomer implements java.io.Serializable {

	// Fields

	private long customerid;
	private String username;
	private String company;
	private String address;
	private String postcode;
	private String nativeplace;
	private String birthday;
	private String mobile;
	private String phone;
	private String fax;
	private String email;
	private String qq;
	private String linkman;
	private String linkmobile;
	private String linkphone;
	private String linkfax;
	private String linkemail;
	private int customerflag;
	private int customertype;
	private long createuser;
	private Date createtime;

	// Constructors

	/** default constructor */
	public TusrCustomer() {
	}

	/** full constructor */
	public TusrCustomer(String username, String company, String address,
			String postcode, String nativeplace, String birthday,
			String mobile, String phone, String fax, String email, String qq,
			String linkman, String linkmobile, String linkphone,
			String linkfax, String linkemail, int customerflag,
			int customertype, long createuser, Date createtime) {
		this.username = username;
		this.company = company;
		this.address = address;
		this.postcode = postcode;
		this.nativeplace = nativeplace;
		this.birthday = birthday;
		this.mobile = mobile;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.qq = qq;
		this.linkman = linkman;
		this.linkmobile = linkmobile;
		this.linkphone = linkphone;
		this.linkfax = linkfax;
		this.linkemail = linkemail;
		this.customerflag = customerflag;
		this.customertype = customertype;
		this.createuser = createuser;
		this.createtime = createtime;
	}

	// Property accessors

	public long getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getNativeplace() {
		return this.nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkmobile() {
		return this.linkmobile;
	}

	public void setLinkmobile(String linkmobile) {
		this.linkmobile = linkmobile;
	}

	public String getLinkphone() {
		return this.linkphone;
	}

	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}

	public String getLinkfax() {
		return this.linkfax;
	}

	public void setLinkfax(String linkfax) {
		this.linkfax = linkfax;
	}

	public String getLinkemail() {
		return this.linkemail;
	}

	public void setLinkemail(String linkemail) {
		this.linkemail = linkemail;
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

	public long getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(long createuser) {
		this.createuser = createuser;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}