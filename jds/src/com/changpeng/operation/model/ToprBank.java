package com.changpeng.operation.model;

/**
 * ToprBank entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ToprBank implements java.io.Serializable {

	// Fields

	private long bankid;
	private String bankname;
	private String contactpeople;
	private String contactphone;
	private String contactmobile;
	private String address;

	// Constructors

	/** default constructor */
	public ToprBank() {
	}



	// Property accessors

	public long getBankid() {
		return this.bankid;
	}

	public void setBankid(long bankid) {
		this.bankid = bankid;
	}

	public String getBankname() {
		return this.bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getContactpeople() {
		return this.contactpeople;
	}

	public void setContactpeople(String contactpeople) {
		this.contactpeople = contactpeople;
	}

	public String getContactphone() {
		return this.contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public String getContactmobile() {
		return this.contactmobile;
	}

	public void setContactmobile(String contactmobile) {
		this.contactmobile = contactmobile;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}