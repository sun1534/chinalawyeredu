package com.changpeng.extra.sms.model;

import java.sql.Timestamp;

/**
 * ExtraInterfacemomsg entity. @author MyEclipse Persistence Tools
 */

public class ExtraInterfacemomsg implements java.io.Serializable {

	// Fields

	private Integer seqnum;
	private String ismgmsgid;
	private Integer spnumber;
	private String servicenumber;
	private String srcnumber;
	private String destnumber;
	private Integer srcismgcode;
	private Short netflag;
	private Short usertype;
	private Short maskflag;
	private String srcmask;
	private String linkid;
	private Short messagetype;
	private Short errorcode;
	private String status;
	private Short pid;
	private Short udhi;
	private Short msgformat;
	private Short msglen;
	private String msgcontent;
	private Short opflag;
	private Timestamp committime;
	private Short recvflag;
	private Integer reserve1;
	private String reserve2;

	// Constructors

	/** default constructor */
	public ExtraInterfacemomsg() {
	}

	/** full constructor */
	public ExtraInterfacemomsg(String ismgmsgid, Integer spnumber,
			String servicenumber, String srcnumber, String destnumber,
			Integer srcismgcode, Short netflag, Short usertype, Short maskflag,
			String srcmask, String linkid, Short messagetype, Short errorcode,
			String status, Short pid, Short udhi, Short msgformat,
			Short msglen, String msgcontent, Short opflag,
			Timestamp committime, Short recvflag, Integer reserve1,
			String reserve2) {
		this.ismgmsgid = ismgmsgid;
		this.spnumber = spnumber;
		this.servicenumber = servicenumber;
		this.srcnumber = srcnumber;
		this.destnumber = destnumber;
		this.srcismgcode = srcismgcode;
		this.netflag = netflag;
		this.usertype = usertype;
		this.maskflag = maskflag;
		this.srcmask = srcmask;
		this.linkid = linkid;
		this.messagetype = messagetype;
		this.errorcode = errorcode;
		this.status = status;
		this.pid = pid;
		this.udhi = udhi;
		this.msgformat = msgformat;
		this.msglen = msglen;
		this.msgcontent = msgcontent;
		this.opflag = opflag;
		this.committime = committime;
		this.recvflag = recvflag;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
	}

	// Property accessors

	public Integer getSeqnum() {
		return this.seqnum;
	}

	public void setSeqnum(Integer seqnum) {
		this.seqnum = seqnum;
	}

	public String getIsmgmsgid() {
		return this.ismgmsgid;
	}

	public void setIsmgmsgid(String ismgmsgid) {
		this.ismgmsgid = ismgmsgid;
	}

	public Integer getSpnumber() {
		return this.spnumber;
	}

	public void setSpnumber(Integer spnumber) {
		this.spnumber = spnumber;
	}

	public String getServicenumber() {
		return this.servicenumber;
	}

	public void setServicenumber(String servicenumber) {
		this.servicenumber = servicenumber;
	}

	public String getSrcnumber() {
		return this.srcnumber;
	}

	public void setSrcnumber(String srcnumber) {
		this.srcnumber = srcnumber;
	}

	public String getDestnumber() {
		return this.destnumber;
	}

	public void setDestnumber(String destnumber) {
		this.destnumber = destnumber;
	}

	public Integer getSrcismgcode() {
		return this.srcismgcode;
	}

	public void setSrcismgcode(Integer srcismgcode) {
		this.srcismgcode = srcismgcode;
	}

	public Short getNetflag() {
		return this.netflag;
	}

	public void setNetflag(Short netflag) {
		this.netflag = netflag;
	}

	public Short getUsertype() {
		return this.usertype;
	}

	public void setUsertype(Short usertype) {
		this.usertype = usertype;
	}

	public Short getMaskflag() {
		return this.maskflag;
	}

	public void setMaskflag(Short maskflag) {
		this.maskflag = maskflag;
	}

	public String getSrcmask() {
		return this.srcmask;
	}

	public void setSrcmask(String srcmask) {
		this.srcmask = srcmask;
	}

	public String getLinkid() {
		return this.linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public Short getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(Short messagetype) {
		this.messagetype = messagetype;
	}

	public Short getErrorcode() {
		return this.errorcode;
	}

	public void setErrorcode(Short errorcode) {
		this.errorcode = errorcode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Short getPid() {
		return this.pid;
	}

	public void setPid(Short pid) {
		this.pid = pid;
	}

	public Short getUdhi() {
		return this.udhi;
	}

	public void setUdhi(Short udhi) {
		this.udhi = udhi;
	}

	public Short getMsgformat() {
		return this.msgformat;
	}

	public void setMsgformat(Short msgformat) {
		this.msgformat = msgformat;
	}

	public Short getMsglen() {
		return this.msglen;
	}

	public void setMsglen(Short msglen) {
		this.msglen = msglen;
	}

	public String getMsgcontent() {
		return this.msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

	public Short getOpflag() {
		return this.opflag;
	}

	public void setOpflag(Short opflag) {
		this.opflag = opflag;
	}

	public Timestamp getCommittime() {
		return this.committime;
	}

	public void setCommittime(Timestamp committime) {
		this.committime = committime;
	}

	public Short getRecvflag() {
		return this.recvflag;
	}

	public void setRecvflag(Short recvflag) {
		this.recvflag = recvflag;
	}

	public Integer getReserve1() {
		return this.reserve1;
	}

	public void setReserve1(Integer reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return this.reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

}