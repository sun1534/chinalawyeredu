package com.changpeng.operation.model;

import java.util.Date;

/**
 * ToprRepaylog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ToprRepaylog implements java.io.Serializable {

	// Fields

	private long repaylogid;
	private ToprCreditcard toprCreditcard;
	private long userid;
	private Date createtime;
	private String repaytime;
	private String comments;
	private String fee;
	private String usafee;
	private String hkfee;
	private String eurfee;
	private int repaystatus;
	public int getRepaystatus() {
		return repaystatus;
	}
	public void setRepaystatus(int repaystatus) {
		this.repaystatus = repaystatus;
	}
	public String getUsafee() {
		return usafee;
	}
	public String getHkfee() {
		return hkfee;
	}
	public String getEurfee() {
		return eurfee;
	}
	public void setUsafee(String usafee) {
		this.usafee = usafee;
	}
	public void setHkfee(String hkfee) {
		this.hkfee = hkfee;
	}
	public void setEurfee(String eurfee) {
		this.eurfee = eurfee;
	}
	public long getRepaylogid() {
		return repaylogid;
	}
	public ToprCreditcard getToprCreditcard() {
		return toprCreditcard;
	}
	public long getUserid() {
		return userid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public String getRepaytime() {
		return repaytime;
	}
	public String getComments() {
		return comments;
	}
	public String getFee() {
		return fee;
	}
	public void setRepaylogid(long repaylogid) {
		this.repaylogid = repaylogid;
	}
	public void setToprCreditcard(ToprCreditcard toprCreditcard) {
		this.toprCreditcard = toprCreditcard;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public void setRepaytime(String repaytime) {
		this.repaytime = repaytime;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}

	
}