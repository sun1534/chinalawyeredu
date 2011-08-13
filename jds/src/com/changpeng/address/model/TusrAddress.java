package com.changpeng.address.model;

import java.util.Date;

import com.sxit.system.model.TsysUser;

/**
 * 用户个人通讯录(客户通讯录)
 * @author sinhoo
 * Jul 9, 2009
 */

public class TusrAddress implements java.io.Serializable {

	// Fields

	private long addressid;
	private TsysUser tsysUser;
	private Date createtime;
	private String username;
	private String phone;
	private String homeaddr;
	private String company;
	private String email;
	private String comments;
	private int oprflag; //1.信用卡催收业务 2.非诉催收业务 3.诉讼业务
	private long oprid; //关联的业务ID
	private int customerid;
	/**
	 * @return the customerid
	 */
	public int getCustomerid() {
		return customerid;
	}
	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public long getAddressid() {
		return addressid;
	}
	public TsysUser getTsysUser() {
		return tsysUser;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public String getUsername() {
		return username;
	}
	public String getPhone() {
		return phone;
	}
	public String getHomeaddr() {
		return homeaddr;
	}
	public String getCompany() {
		return company;
	}
	public String getEmail() {
		return email;
	}
	public String getComments() {
		return comments;
	}
	public int getOprflag() {
		return oprflag;
	}
	public long getOprid() {
		return oprid;
	}
	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}
	public void setTsysUser(TsysUser tsysUser) {
		this.tsysUser = tsysUser;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setHomeaddr(String homeaddr) {
		this.homeaddr = homeaddr;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setOprflag(int oprflag) {
		this.oprflag = oprflag;
	}
	public void setOprid(long oprid) {
		this.oprid = oprid;
	}
	
}