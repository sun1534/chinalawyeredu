package com.changpeng.service.model;

import java.util.Date;

/**
 * TserOwner entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TserOwner implements java.io.Serializable {

	// Fields

	private long ownerid;
	private String hourse;
	private String rentuser;
	private String area;
	private String guafee;
	private String renttime;
	private String rentdate;
	private String rentfee;
	private String rentmonth;
	private String addfeedate;
	private String taxfee;
	private String adminfee;
	private String taccomment;
	private String taxsoil;
	private String mobile;
	private String phone;
	private String recuser;
	private String comments;
	private Date createtime;
	private String admincomment;
	private String taxall;

	// Constructors

	/** default constructor */
	public TserOwner() {
	}

	/** full constructor */
	public TserOwner(String hourse, String rentuser, String area,
			String guafee, String renttime, String rentdate, String rentfee,
			String rentmonth, String addfeedate, String taxfee,
			String adminfee, String taccomment, String taxsoil, String mobile,
			String phone, String recuser, String comments, Date createtime,
			String admincomment, String taxall) {
		this.hourse = hourse;
		this.rentuser = rentuser;
		this.area = area;
		this.guafee = guafee;
		this.renttime = renttime;
		this.rentdate = rentdate;
		this.rentfee = rentfee;
		this.rentmonth = rentmonth;
		this.addfeedate = addfeedate;
		this.taxfee = taxfee;
		this.adminfee = adminfee;
		this.taccomment = taccomment;
		this.taxsoil = taxsoil;
		this.mobile = mobile;
		this.phone = phone;
		this.recuser = recuser;
		this.comments = comments;
		this.createtime = createtime;
		this.admincomment = admincomment;
		this.taxall = taxall;
	}

	// Property accessors

	public long getOwnerid() {
		return this.ownerid;
	}

	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}

	public String getHourse() {
		return this.hourse;
	}

	public void setHourse(String hourse) {
		this.hourse = hourse;
	}

	public String getRentuser() {
		return this.rentuser;
	}

	public void setRentuser(String rentuser) {
		this.rentuser = rentuser;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGuafee() {
		return this.guafee;
	}

	public void setGuafee(String guafee) {
		this.guafee = guafee;
	}

	public String getRenttime() {
		return this.renttime;
	}

	public void setRenttime(String renttime) {
		this.renttime = renttime;
	}

	public String getRentdate() {
		return this.rentdate;
	}

	public void setRentdate(String rentdate) {
		this.rentdate = rentdate;
	}

	public String getRentfee() {
		return this.rentfee;
	}

	public void setRentfee(String rentfee) {
		this.rentfee = rentfee;
	}

	public String getRentmonth() {
		return this.rentmonth;
	}

	public void setRentmonth(String rentmonth) {
		this.rentmonth = rentmonth;
	}

	public String getAddfeedate() {
		return this.addfeedate;
	}

	public void setAddfeedate(String addfeedate) {
		this.addfeedate = addfeedate;
	}

	public String getTaxfee() {
		return this.taxfee;
	}

	public void setTaxfee(String taxfee) {
		this.taxfee = taxfee;
	}

	public String getAdminfee() {
		return this.adminfee;
	}

	public void setAdminfee(String adminfee) {
		this.adminfee = adminfee;
	}

	public String getTaccomment() {
		return this.taccomment;
	}

	public void setTaccomment(String taccomment) {
		this.taccomment = taccomment;
	}

	public String getTaxsoil() {
		return this.taxsoil;
	}

	public void setTaxsoil(String taxsoil) {
		this.taxsoil = taxsoil;
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

	public String getRecuser() {
		return this.recuser;
	}

	public void setRecuser(String recuser) {
		this.recuser = recuser;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getAdmincomment() {
		return this.admincomment;
	}

	public void setAdmincomment(String admincomment) {
		this.admincomment = admincomment;
	}

	public String getTaxall() {
		return this.taxall;
	}

	public void setTaxall(String taxall) {
		this.taxall = taxall;
	}

}