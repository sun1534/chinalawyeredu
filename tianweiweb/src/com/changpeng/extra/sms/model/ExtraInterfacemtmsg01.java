package com.changpeng.extra.sms.model;

import java.sql.Timestamp;

/**
 * ExtraInterfacemtmsg01 entity. @author MyEclipse Persistence Tools
 */

public class ExtraInterfacemtmsg01 implements java.io.Serializable {

	// Fields

	private Integer seqnum;
	private String msgid;
	private Integer sendtime;
	private Integer spnumber;
	private String servicenumber;
	private String srcnumber;
	private String destnumber;
	private String feenumber;
	private String msgsrc;
	private Short feeflag;
	private Short feetype;
	private Integer feecode;
	private Integer givenfee;
	private Short routemode;
	private Short agentflag;
	private String servicecode;
	private Short spflag;
	private Short netflag;
	private Short statusflag;
	private Short usertype;
	private Short ppusertype;
	private Short areacode;
	private Short stateareacode;
	private Short smscid;
	private String smscnumber;
	private Short scpid;
	private Short msglevel;
	private Short msgformat;
	private Short msglen;
	private String msgcontent;
	private Short errorcode;
	private String status;
	private Timestamp committime;
	private Integer finishtime;
	private Short sendflag;
	private String feemask;
	private String linkid;
	private Short destusertype;
	private Short portocolversion;
	private Short pktotal;
	private Short pknumber;
	private Integer msgId1;
	private Integer msgId2;
	private String respstat;
	private Integer summittime;
	private Timestamp reptime;

	// Constructors

	/** default constructor */
	public ExtraInterfacemtmsg01() {
	}

	/** full constructor */
	public ExtraInterfacemtmsg01(String msgid, Integer sendtime,
			Integer spnumber, String servicenumber, String srcnumber,
			String destnumber, String feenumber, String msgsrc, Short feeflag,
			Short feetype, Integer feecode, Integer givenfee, Short routemode,
			Short agentflag, String servicecode, Short spflag, Short netflag,
			Short statusflag, Short usertype, Short ppusertype, Short areacode,
			Short stateareacode, Short smscid, String smscnumber, Short scpid,
			Short msglevel, Short msgformat, Short msglen, String msgcontent,
			Short errorcode, String status, Timestamp committime,
			Integer finishtime, Short sendflag, String feemask, String linkid,
			Short destusertype, Short portocolversion, Short pktotal,
			Short pknumber, Integer msgId1, Integer msgId2, String respstat,
			Integer summittime, Timestamp reptime) {
		this.msgid = msgid;
		this.sendtime = sendtime;
		this.spnumber = spnumber;
		this.servicenumber = servicenumber;
		this.srcnumber = srcnumber;
		this.destnumber = destnumber;
		this.feenumber = feenumber;
		this.msgsrc = msgsrc;
		this.feeflag = feeflag;
		this.feetype = feetype;
		this.feecode = feecode;
		this.givenfee = givenfee;
		this.routemode = routemode;
		this.agentflag = agentflag;
		this.servicecode = servicecode;
		this.spflag = spflag;
		this.netflag = netflag;
		this.statusflag = statusflag;
		this.usertype = usertype;
		this.ppusertype = ppusertype;
		this.areacode = areacode;
		this.stateareacode = stateareacode;
		this.smscid = smscid;
		this.smscnumber = smscnumber;
		this.scpid = scpid;
		this.msglevel = msglevel;
		this.msgformat = msgformat;
		this.msglen = msglen;
		this.msgcontent = msgcontent;
		this.errorcode = errorcode;
		this.status = status;
		this.committime = committime;
		this.finishtime = finishtime;
		this.sendflag = sendflag;
		this.feemask = feemask;
		this.linkid = linkid;
		this.destusertype = destusertype;
		this.portocolversion = portocolversion;
		this.pktotal = pktotal;
		this.pknumber = pknumber;
		this.msgId1 = msgId1;
		this.msgId2 = msgId2;
		this.respstat = respstat;
		this.summittime = summittime;
		this.reptime = reptime;
	}

	// Property accessors

	public Integer getSeqnum() {
		return this.seqnum;
	}

	public void setSeqnum(Integer seqnum) {
		this.seqnum = seqnum;
	}

	public String getMsgid() {
		return this.msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public Integer getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Integer sendtime) {
		this.sendtime = sendtime;
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

	public String getFeenumber() {
		return this.feenumber;
	}

	public void setFeenumber(String feenumber) {
		this.feenumber = feenumber;
	}

	public String getMsgsrc() {
		return this.msgsrc;
	}

	public void setMsgsrc(String msgsrc) {
		this.msgsrc = msgsrc;
	}

	public Short getFeeflag() {
		return this.feeflag;
	}

	public void setFeeflag(Short feeflag) {
		this.feeflag = feeflag;
	}

	public Short getFeetype() {
		return this.feetype;
	}

	public void setFeetype(Short feetype) {
		this.feetype = feetype;
	}

	public Integer getFeecode() {
		return this.feecode;
	}

	public void setFeecode(Integer feecode) {
		this.feecode = feecode;
	}

	public Integer getGivenfee() {
		return this.givenfee;
	}

	public void setGivenfee(Integer givenfee) {
		this.givenfee = givenfee;
	}

	public Short getRoutemode() {
		return this.routemode;
	}

	public void setRoutemode(Short routemode) {
		this.routemode = routemode;
	}

	public Short getAgentflag() {
		return this.agentflag;
	}

	public void setAgentflag(Short agentflag) {
		this.agentflag = agentflag;
	}

	public String getServicecode() {
		return this.servicecode;
	}

	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}

	public Short getSpflag() {
		return this.spflag;
	}

	public void setSpflag(Short spflag) {
		this.spflag = spflag;
	}

	public Short getNetflag() {
		return this.netflag;
	}

	public void setNetflag(Short netflag) {
		this.netflag = netflag;
	}

	public Short getStatusflag() {
		return this.statusflag;
	}

	public void setStatusflag(Short statusflag) {
		this.statusflag = statusflag;
	}

	public Short getUsertype() {
		return this.usertype;
	}

	public void setUsertype(Short usertype) {
		this.usertype = usertype;
	}

	public Short getPpusertype() {
		return this.ppusertype;
	}

	public void setPpusertype(Short ppusertype) {
		this.ppusertype = ppusertype;
	}

	public Short getAreacode() {
		return this.areacode;
	}

	public void setAreacode(Short areacode) {
		this.areacode = areacode;
	}

	public Short getStateareacode() {
		return this.stateareacode;
	}

	public void setStateareacode(Short stateareacode) {
		this.stateareacode = stateareacode;
	}

	public Short getSmscid() {
		return this.smscid;
	}

	public void setSmscid(Short smscid) {
		this.smscid = smscid;
	}

	public String getSmscnumber() {
		return this.smscnumber;
	}

	public void setSmscnumber(String smscnumber) {
		this.smscnumber = smscnumber;
	}

	public Short getScpid() {
		return this.scpid;
	}

	public void setScpid(Short scpid) {
		this.scpid = scpid;
	}

	public Short getMsglevel() {
		return this.msglevel;
	}

	public void setMsglevel(Short msglevel) {
		this.msglevel = msglevel;
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

	public Timestamp getCommittime() {
		return this.committime;
	}

	public void setCommittime(Timestamp committime) {
		this.committime = committime;
	}

	public Integer getFinishtime() {
		return this.finishtime;
	}

	public void setFinishtime(Integer finishtime) {
		this.finishtime = finishtime;
	}

	public Short getSendflag() {
		return this.sendflag;
	}

	public void setSendflag(Short sendflag) {
		this.sendflag = sendflag;
	}

	public String getFeemask() {
		return this.feemask;
	}

	public void setFeemask(String feemask) {
		this.feemask = feemask;
	}

	public String getLinkid() {
		return this.linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public Short getDestusertype() {
		return this.destusertype;
	}

	public void setDestusertype(Short destusertype) {
		this.destusertype = destusertype;
	}

	public Short getPortocolversion() {
		return this.portocolversion;
	}

	public void setPortocolversion(Short portocolversion) {
		this.portocolversion = portocolversion;
	}

	public Short getPktotal() {
		return this.pktotal;
	}

	public void setPktotal(Short pktotal) {
		this.pktotal = pktotal;
	}

	public Short getPknumber() {
		return this.pknumber;
	}

	public void setPknumber(Short pknumber) {
		this.pknumber = pknumber;
	}

	public Integer getMsgId1() {
		return this.msgId1;
	}

	public void setMsgId1(Integer msgId1) {
		this.msgId1 = msgId1;
	}

	public Integer getMsgId2() {
		return this.msgId2;
	}

	public void setMsgId2(Integer msgId2) {
		this.msgId2 = msgId2;
	}

	public String getRespstat() {
		return this.respstat;
	}

	public void setRespstat(String respstat) {
		this.respstat = respstat;
	}

	public Integer getSummittime() {
		return this.summittime;
	}

	public void setSummittime(Integer summittime) {
		this.summittime = summittime;
	}

	public Timestamp getReptime() {
		return this.reptime;
	}

	public void setReptime(Timestamp reptime) {
		this.reptime = reptime;
	}

}