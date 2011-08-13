package com.changpeng.customer.model;

import java.sql.Timestamp;

import com.changpeng.nonlaw.model.TnlwNonlaw;
import com.changpeng.operation.model.ToprCreditcard;

/**
 * TusrCustomerService entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TusrCustomerService implements java.io.Serializable {

	// Fields

	private int id;
	private TusrCustomerNew tusrCustomerNew;
	private int servicetype;
	private int serviceid;
	private Timestamp createtime = new java.sql.Timestamp(System.currentTimeMillis());
	private String createuser;
	private int createuserid;
	private String remarks;

	private ToprCreditcard card;
	private TnlwNonlaw nonlaw;

	// Constructors

	/**
	 * @return the card
	 */
	public ToprCreditcard getCard() {

		if (servicetype == 1)
			card= (ToprCreditcard) com.changpeng.common.Globals.getInstance().get(ToprCreditcard.class, serviceid*1L);
		return card;
	}

	/**
	 * @return the nonlaw
	 */
	public TnlwNonlaw getNonlaw() {
		if (servicetype == 2)
			nonlaw= (TnlwNonlaw) com.changpeng.common.Globals.getInstance().get(TnlwNonlaw.class, serviceid*1L);
		return nonlaw;
	}

	/** default constructor */
	public TusrCustomerService() {
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TusrCustomerNew getTusrCustomerNew() {
		return this.tusrCustomerNew;
	}

	public void setTusrCustomerNew(TusrCustomerNew tusrCustomerNew) {
		this.tusrCustomerNew = tusrCustomerNew;
	}

	public int getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(int servicetype) {
		this.servicetype = servicetype;
	}

	public int getServiceid() {
		return this.serviceid;
	}

	public void setServiceid(int serviceid) {
		this.serviceid = serviceid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public int getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}