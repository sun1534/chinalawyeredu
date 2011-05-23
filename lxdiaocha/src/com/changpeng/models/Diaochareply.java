package com.changpeng.models;

import java.util.Date;

/**
 * Diaochareply entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Diaochareply implements java.io.Serializable {

	// Fields

	private int replyid;
	private Diaochawenti diaochawenti;
	private String replycontent;
	private String replyuser;
	private String lawyerno;
	private Date replytime;
	private String others;
	private String batchid;
	private int diaochaid;
	private String ip;
	private String cookie;

	// Constructors

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the cookie
	 */
	public String getCookie() {
		return cookie;
	}

	/**
	 * @param cookie
	 *            the cookie to set
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	/** default constructor */
	public Diaochareply() {
	}

	/** minimal constructor */
	public Diaochareply(Diaochawenti diaochawenti, String replyuser, Date replytime) {
		this.diaochawenti = diaochawenti;
		this.replyuser = replyuser;
		this.replytime = replytime;
	}

	/** full constructor */
	public Diaochareply(Diaochawenti diaochawenti, String replycontent, String replyuser, Date replytime) {
		this.diaochawenti = diaochawenti;
		this.replycontent = replycontent;
		this.replyuser = replyuser;
		this.replytime = replytime;
	}

	// Property accessors

	public int getReplyid() {
		return this.replyid;
	}

	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}

	public Diaochawenti getDiaochawenti() {
		return this.diaochawenti;
	}

	public void setDiaochawenti(Diaochawenti diaochawenti) {
		this.diaochawenti = diaochawenti;
	}

	public String getReplycontent() {
		return this.replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public String getReplyuser() {
		return this.replyuser;
	}

	public void setReplyuser(String replyuser) {
		this.replyuser = replyuser;
	}

	public Date getReplytime() {
		return this.replytime;
	}

	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	/**
	 * @return the batchid
	 */
	public String getBatchid() {
		return batchid;
	}

	/**
	 * @param batchid
	 *            the batchid to set
	 */
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	/**
	 * @return the diaochaid
	 */
	public int getDiaochaid() {
		return diaochaid;
	}

	/**
	 * @param diaochaid the diaochaid to set
	 */
	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}

	/**
	 * @return the lawyerno
	 */
	public String getLawyerno() {
		return lawyerno;
	}

	/**
	 * @param lawyerno the lawyerno to set
	 */
	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

}