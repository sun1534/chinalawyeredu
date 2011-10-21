package com.changpeng.operation.model;

import java.util.Date;

/**
 * ToprCreditlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ToprCreditlog implements java.io.Serializable {

	// Fields

	private long logid;
	private ToprCredittask toprCredittask;
	private long userid;
	private String username;
	private String bankno;
	private Date createtime;
	private String logtime;
	private String comments;
	private String contacttype;
	private String contactmanner;
	private String contactobj;
	private String contactresult;
	private String cuishoucuoshi;
	private String promisemoney;
	private String promisedate;
	
	
//	CONTACTTYPE    VARCHAR2(20 BYTE),
//	  CONTACTMANNER  VARCHAR2(100 BYTE),
//	  CONTACTOBJ     VARCHAR2(50 BYTE),
//	  CONTACTRESULT  VARCHAR2(300 BYTE),
//	  CUISHOUCUOSHI  VARCHAR2(100 BYTE),
//	  PROMISEMONEY   VARCHAR2(100 BYTE),
//	  PROMISEDATE    VARCHAR2(20 BYTE)
	
	private ToprCreditcard toprCreditcard;
	// Constructors

	public ToprCreditcard getToprCreditcard() {
		return toprCreditcard;
	}

	public void setToprCreditcard(ToprCreditcard toprCreditcard) {
		this.toprCreditcard = toprCreditcard;
	}

	/** default constructor */
	public ToprCreditlog() {
	}

	/** minimal constructor */
	public ToprCreditlog(ToprCredittask toprCredittask, long userid,
			Date createtime, String logtime) {
		this.toprCredittask = toprCredittask;
		this.userid = userid;
		this.createtime = createtime;
		this.logtime = logtime;
	}

	/** full constructor */
	public ToprCreditlog(ToprCredittask toprCredittask, long userid,
			Date createtime, String logtime, String comments) {
		this.toprCredittask = toprCredittask;
		this.userid = userid;
		this.createtime = createtime;
		this.logtime = logtime;
		this.comments = comments;
	}

	// Property accessors

	public long getLogid() {
		return this.logid;
	}

	public void setLogid(long logid) {
		this.logid = logid;
	}

	public ToprCredittask getToprCredittask() {
		return this.toprCredittask;
	}

	public void setToprCredittask(ToprCredittask toprCredittask) {
		this.toprCredittask = toprCredittask;
	}

	public long getUserid() {
		return this.userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getLogtime() {
		return this.logtime;
	}

	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the contacttype
	 */
	public String getContacttype() {
		return contacttype;
	}

	/**
	 * @param contacttype the contacttype to set
	 */
	public void setContacttype(String contacttype) {
		this.contacttype = contacttype;
	}

	/**
	 * @return the contactobj
	 */
	public String getContactobj() {
		return contactobj;
	}

	/**
	 * @param contactobj the contactobj to set
	 */
	public void setContactobj(String contactobj) {
		this.contactobj = contactobj;
	}

	/**
	 * @return the contactresult
	 */
	public String getContactresult() {
		return contactresult;
	}

	/**
	 * @param contactresult the contactresult to set
	 */
	public void setContactresult(String contactresult) {
		this.contactresult = contactresult;
	}

	/**
	 * @return the cuishoucuoshi
	 */
	public String getCuishoucuoshi() {
		return cuishoucuoshi;
	}

	/**
	 * @param cuishoucuoshi the cuishoucuoshi to set
	 */
	public void setCuishoucuoshi(String cuishoucuoshi) {
		this.cuishoucuoshi = cuishoucuoshi;
	}

	/**
	 * @return the promisemoney
	 */
	public String getPromisemoney() {
		return promisemoney;
	}

	/**
	 * @param promisemoney the promisemoney to set
	 */
	public void setPromisemoney(String promisemoney) {
		this.promisemoney = promisemoney;
	}

	/**
	 * @return the promisdate
	 */
	public String getPromisedate() {
		return promisedate;
	}

	/**
	 * @param promisdate the promisdate to set
	 */
	public void setPromisedate(String promisdate) {
		this.promisedate = promisdate;
	}

	/**
	 * @return the contactmanner
	 */
	public String getContactmanner() {
		return contactmanner;
	}

	/**
	 * @param contactmanner the contactmanner to set
	 */
	public void setContactmanner(String contactmanner) {
		this.contactmanner = contactmanner;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the bankno
	 */
	public String getBankno() {
		return bankno;
	}

	/**
	 * @param bankno the bankno to set
	 */
	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

}