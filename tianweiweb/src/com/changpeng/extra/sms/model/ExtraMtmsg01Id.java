package com.changpeng.extra.sms.model;

import java.sql.Timestamp;

/**
 * ExtraMtmsg01Id entity. @author MyEclipse Persistence Tools
 */

public class ExtraMtmsg01Id implements java.io.Serializable {

	// Fields

	private String msgid;
	private Integer sendtime;
	private Integer srcismgcode;
	private Integer destismgcode;
	private Integer smscsequence;
	private Integer spnumber;
	private String servicenumber;
	private String srcnumber;
	private String destnumber;
	private String feenumber;
	private Short feeflag;
	private Short feetype;
	private Integer feecode;
	private Integer givenfee;
	private Short agentflag;
	private Short msgreason;
	private String servicecode;
	private String msgsrc;
	private Short routemode;
	private Short spflag;
	private Short statusflag;
	private Short usertype;
	private Short ppusertype;
	private Short areacode;
	private Short stateareacode;
	private Short smscid;
	private String smscnumber;
	private Short scpid;
	private Short pid;
	private Short udhi;
	private Short msglevel;
	private Short msgformat;
	private Integer validtime;
	private Integer attime;
	private Timestamp committime;
	private Integer finishtime;
	private Short sendcount;
	private Short sendflag;
	private Short netflag;
	private Short errorcode;
	private String status;
	private Short ticketstatus;
	private Short ticketflag;
	private Short msglen;
	private String msgcontent;
	private Short dsmpflag;
	private Short maskflag;
	private String destmask;
	private String feemask;
	private String linkid;
	private Short destusertype;
	private Short portocolversion;
	private Short pktotal;
	private Short pknumber;

	// Constructors

	/** default constructor */
	public ExtraMtmsg01Id() {
	}

	/** full constructor */
	public ExtraMtmsg01Id(String msgid, Integer sendtime, Integer srcismgcode,
			Integer destismgcode, Integer smscsequence, Integer spnumber,
			String servicenumber, String srcnumber, String destnumber,
			String feenumber, Short feeflag, Short feetype, Integer feecode,
			Integer givenfee, Short agentflag, Short msgreason,
			String servicecode, String msgsrc, Short routemode, Short spflag,
			Short statusflag, Short usertype, Short ppusertype, Short areacode,
			Short stateareacode, Short smscid, String smscnumber, Short scpid,
			Short pid, Short udhi, Short msglevel, Short msgformat,
			Integer validtime, Integer attime, Timestamp committime,
			Integer finishtime, Short sendcount, Short sendflag, Short netflag,
			Short errorcode, String status, Short ticketstatus,
			Short ticketflag, Short msglen, String msgcontent, Short dsmpflag,
			Short maskflag, String destmask, String feemask, String linkid,
			Short destusertype, Short portocolversion, Short pktotal,
			Short pknumber) {
		this.msgid = msgid;
		this.sendtime = sendtime;
		this.srcismgcode = srcismgcode;
		this.destismgcode = destismgcode;
		this.smscsequence = smscsequence;
		this.spnumber = spnumber;
		this.servicenumber = servicenumber;
		this.srcnumber = srcnumber;
		this.destnumber = destnumber;
		this.feenumber = feenumber;
		this.feeflag = feeflag;
		this.feetype = feetype;
		this.feecode = feecode;
		this.givenfee = givenfee;
		this.agentflag = agentflag;
		this.msgreason = msgreason;
		this.servicecode = servicecode;
		this.msgsrc = msgsrc;
		this.routemode = routemode;
		this.spflag = spflag;
		this.statusflag = statusflag;
		this.usertype = usertype;
		this.ppusertype = ppusertype;
		this.areacode = areacode;
		this.stateareacode = stateareacode;
		this.smscid = smscid;
		this.smscnumber = smscnumber;
		this.scpid = scpid;
		this.pid = pid;
		this.udhi = udhi;
		this.msglevel = msglevel;
		this.msgformat = msgformat;
		this.validtime = validtime;
		this.attime = attime;
		this.committime = committime;
		this.finishtime = finishtime;
		this.sendcount = sendcount;
		this.sendflag = sendflag;
		this.netflag = netflag;
		this.errorcode = errorcode;
		this.status = status;
		this.ticketstatus = ticketstatus;
		this.ticketflag = ticketflag;
		this.msglen = msglen;
		this.msgcontent = msgcontent;
		this.dsmpflag = dsmpflag;
		this.maskflag = maskflag;
		this.destmask = destmask;
		this.feemask = feemask;
		this.linkid = linkid;
		this.destusertype = destusertype;
		this.portocolversion = portocolversion;
		this.pktotal = pktotal;
		this.pknumber = pknumber;
	}

	// Property accessors

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

	public Integer getSrcismgcode() {
		return this.srcismgcode;
	}

	public void setSrcismgcode(Integer srcismgcode) {
		this.srcismgcode = srcismgcode;
	}

	public Integer getDestismgcode() {
		return this.destismgcode;
	}

	public void setDestismgcode(Integer destismgcode) {
		this.destismgcode = destismgcode;
	}

	public Integer getSmscsequence() {
		return this.smscsequence;
	}

	public void setSmscsequence(Integer smscsequence) {
		this.smscsequence = smscsequence;
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

	public Short getAgentflag() {
		return this.agentflag;
	}

	public void setAgentflag(Short agentflag) {
		this.agentflag = agentflag;
	}

	public Short getMsgreason() {
		return this.msgreason;
	}

	public void setMsgreason(Short msgreason) {
		this.msgreason = msgreason;
	}

	public String getServicecode() {
		return this.servicecode;
	}

	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}

	public String getMsgsrc() {
		return this.msgsrc;
	}

	public void setMsgsrc(String msgsrc) {
		this.msgsrc = msgsrc;
	}

	public Short getRoutemode() {
		return this.routemode;
	}

	public void setRoutemode(Short routemode) {
		this.routemode = routemode;
	}

	public Short getSpflag() {
		return this.spflag;
	}

	public void setSpflag(Short spflag) {
		this.spflag = spflag;
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

	public Integer getValidtime() {
		return this.validtime;
	}

	public void setValidtime(Integer validtime) {
		this.validtime = validtime;
	}

	public Integer getAttime() {
		return this.attime;
	}

	public void setAttime(Integer attime) {
		this.attime = attime;
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

	public Short getSendcount() {
		return this.sendcount;
	}

	public void setSendcount(Short sendcount) {
		this.sendcount = sendcount;
	}

	public Short getSendflag() {
		return this.sendflag;
	}

	public void setSendflag(Short sendflag) {
		this.sendflag = sendflag;
	}

	public Short getNetflag() {
		return this.netflag;
	}

	public void setNetflag(Short netflag) {
		this.netflag = netflag;
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

	public Short getTicketstatus() {
		return this.ticketstatus;
	}

	public void setTicketstatus(Short ticketstatus) {
		this.ticketstatus = ticketstatus;
	}

	public Short getTicketflag() {
		return this.ticketflag;
	}

	public void setTicketflag(Short ticketflag) {
		this.ticketflag = ticketflag;
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

	public Short getDsmpflag() {
		return this.dsmpflag;
	}

	public void setDsmpflag(Short dsmpflag) {
		this.dsmpflag = dsmpflag;
	}

	public Short getMaskflag() {
		return this.maskflag;
	}

	public void setMaskflag(Short maskflag) {
		this.maskflag = maskflag;
	}

	public String getDestmask() {
		return this.destmask;
	}

	public void setDestmask(String destmask) {
		this.destmask = destmask;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ExtraMtmsg01Id))
			return false;
		ExtraMtmsg01Id castOther = (ExtraMtmsg01Id) other;

		return ((this.getMsgid() == castOther.getMsgid()) || (this.getMsgid() != null
				&& castOther.getMsgid() != null && this.getMsgid().equals(
				castOther.getMsgid())))
				&& ((this.getSendtime() == castOther.getSendtime()) || (this
						.getSendtime() != null
						&& castOther.getSendtime() != null && this
						.getSendtime().equals(castOther.getSendtime())))
				&& ((this.getSrcismgcode() == castOther.getSrcismgcode()) || (this
						.getSrcismgcode() != null
						&& castOther.getSrcismgcode() != null && this
						.getSrcismgcode().equals(castOther.getSrcismgcode())))
				&& ((this.getDestismgcode() == castOther.getDestismgcode()) || (this
						.getDestismgcode() != null
						&& castOther.getDestismgcode() != null && this
						.getDestismgcode().equals(castOther.getDestismgcode())))
				&& ((this.getSmscsequence() == castOther.getSmscsequence()) || (this
						.getSmscsequence() != null
						&& castOther.getSmscsequence() != null && this
						.getSmscsequence().equals(castOther.getSmscsequence())))
				&& ((this.getSpnumber() == castOther.getSpnumber()) || (this
						.getSpnumber() != null
						&& castOther.getSpnumber() != null && this
						.getSpnumber().equals(castOther.getSpnumber())))
				&& ((this.getServicenumber() == castOther.getServicenumber()) || (this
						.getServicenumber() != null
						&& castOther.getServicenumber() != null && this
						.getServicenumber()
						.equals(castOther.getServicenumber())))
				&& ((this.getSrcnumber() == castOther.getSrcnumber()) || (this
						.getSrcnumber() != null
						&& castOther.getSrcnumber() != null && this
						.getSrcnumber().equals(castOther.getSrcnumber())))
				&& ((this.getDestnumber() == castOther.getDestnumber()) || (this
						.getDestnumber() != null
						&& castOther.getDestnumber() != null && this
						.getDestnumber().equals(castOther.getDestnumber())))
				&& ((this.getFeenumber() == castOther.getFeenumber()) || (this
						.getFeenumber() != null
						&& castOther.getFeenumber() != null && this
						.getFeenumber().equals(castOther.getFeenumber())))
				&& ((this.getFeeflag() == castOther.getFeeflag()) || (this
						.getFeeflag() != null
						&& castOther.getFeeflag() != null && this.getFeeflag()
						.equals(castOther.getFeeflag())))
				&& ((this.getFeetype() == castOther.getFeetype()) || (this
						.getFeetype() != null
						&& castOther.getFeetype() != null && this.getFeetype()
						.equals(castOther.getFeetype())))
				&& ((this.getFeecode() == castOther.getFeecode()) || (this
						.getFeecode() != null
						&& castOther.getFeecode() != null && this.getFeecode()
						.equals(castOther.getFeecode())))
				&& ((this.getGivenfee() == castOther.getGivenfee()) || (this
						.getGivenfee() != null
						&& castOther.getGivenfee() != null && this
						.getGivenfee().equals(castOther.getGivenfee())))
				&& ((this.getAgentflag() == castOther.getAgentflag()) || (this
						.getAgentflag() != null
						&& castOther.getAgentflag() != null && this
						.getAgentflag().equals(castOther.getAgentflag())))
				&& ((this.getMsgreason() == castOther.getMsgreason()) || (this
						.getMsgreason() != null
						&& castOther.getMsgreason() != null && this
						.getMsgreason().equals(castOther.getMsgreason())))
				&& ((this.getServicecode() == castOther.getServicecode()) || (this
						.getServicecode() != null
						&& castOther.getServicecode() != null && this
						.getServicecode().equals(castOther.getServicecode())))
				&& ((this.getMsgsrc() == castOther.getMsgsrc()) || (this
						.getMsgsrc() != null
						&& castOther.getMsgsrc() != null && this.getMsgsrc()
						.equals(castOther.getMsgsrc())))
				&& ((this.getRoutemode() == castOther.getRoutemode()) || (this
						.getRoutemode() != null
						&& castOther.getRoutemode() != null && this
						.getRoutemode().equals(castOther.getRoutemode())))
				&& ((this.getSpflag() == castOther.getSpflag()) || (this
						.getSpflag() != null
						&& castOther.getSpflag() != null && this.getSpflag()
						.equals(castOther.getSpflag())))
				&& ((this.getStatusflag() == castOther.getStatusflag()) || (this
						.getStatusflag() != null
						&& castOther.getStatusflag() != null && this
						.getStatusflag().equals(castOther.getStatusflag())))
				&& ((this.getUsertype() == castOther.getUsertype()) || (this
						.getUsertype() != null
						&& castOther.getUsertype() != null && this
						.getUsertype().equals(castOther.getUsertype())))
				&& ((this.getPpusertype() == castOther.getPpusertype()) || (this
						.getPpusertype() != null
						&& castOther.getPpusertype() != null && this
						.getPpusertype().equals(castOther.getPpusertype())))
				&& ((this.getAreacode() == castOther.getAreacode()) || (this
						.getAreacode() != null
						&& castOther.getAreacode() != null && this
						.getAreacode().equals(castOther.getAreacode())))
				&& ((this.getStateareacode() == castOther.getStateareacode()) || (this
						.getStateareacode() != null
						&& castOther.getStateareacode() != null && this
						.getStateareacode()
						.equals(castOther.getStateareacode())))
				&& ((this.getSmscid() == castOther.getSmscid()) || (this
						.getSmscid() != null
						&& castOther.getSmscid() != null && this.getSmscid()
						.equals(castOther.getSmscid())))
				&& ((this.getSmscnumber() == castOther.getSmscnumber()) || (this
						.getSmscnumber() != null
						&& castOther.getSmscnumber() != null && this
						.getSmscnumber().equals(castOther.getSmscnumber())))
				&& ((this.getScpid() == castOther.getScpid()) || (this
						.getScpid() != null
						&& castOther.getScpid() != null && this.getScpid()
						.equals(castOther.getScpid())))
				&& ((this.getPid() == castOther.getPid()) || (this.getPid() != null
						&& castOther.getPid() != null && this.getPid().equals(
						castOther.getPid())))
				&& ((this.getUdhi() == castOther.getUdhi()) || (this.getUdhi() != null
						&& castOther.getUdhi() != null && this.getUdhi()
						.equals(castOther.getUdhi())))
				&& ((this.getMsglevel() == castOther.getMsglevel()) || (this
						.getMsglevel() != null
						&& castOther.getMsglevel() != null && this
						.getMsglevel().equals(castOther.getMsglevel())))
				&& ((this.getMsgformat() == castOther.getMsgformat()) || (this
						.getMsgformat() != null
						&& castOther.getMsgformat() != null && this
						.getMsgformat().equals(castOther.getMsgformat())))
				&& ((this.getValidtime() == castOther.getValidtime()) || (this
						.getValidtime() != null
						&& castOther.getValidtime() != null && this
						.getValidtime().equals(castOther.getValidtime())))
				&& ((this.getAttime() == castOther.getAttime()) || (this
						.getAttime() != null
						&& castOther.getAttime() != null && this.getAttime()
						.equals(castOther.getAttime())))
				&& ((this.getCommittime() == castOther.getCommittime()) || (this
						.getCommittime() != null
						&& castOther.getCommittime() != null && this
						.getCommittime().equals(castOther.getCommittime())))
				&& ((this.getFinishtime() == castOther.getFinishtime()) || (this
						.getFinishtime() != null
						&& castOther.getFinishtime() != null && this
						.getFinishtime().equals(castOther.getFinishtime())))
				&& ((this.getSendcount() == castOther.getSendcount()) || (this
						.getSendcount() != null
						&& castOther.getSendcount() != null && this
						.getSendcount().equals(castOther.getSendcount())))
				&& ((this.getSendflag() == castOther.getSendflag()) || (this
						.getSendflag() != null
						&& castOther.getSendflag() != null && this
						.getSendflag().equals(castOther.getSendflag())))
				&& ((this.getNetflag() == castOther.getNetflag()) || (this
						.getNetflag() != null
						&& castOther.getNetflag() != null && this.getNetflag()
						.equals(castOther.getNetflag())))
				&& ((this.getErrorcode() == castOther.getErrorcode()) || (this
						.getErrorcode() != null
						&& castOther.getErrorcode() != null && this
						.getErrorcode().equals(castOther.getErrorcode())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null
						&& castOther.getStatus() != null && this.getStatus()
						.equals(castOther.getStatus())))
				&& ((this.getTicketstatus() == castOther.getTicketstatus()) || (this
						.getTicketstatus() != null
						&& castOther.getTicketstatus() != null && this
						.getTicketstatus().equals(castOther.getTicketstatus())))
				&& ((this.getTicketflag() == castOther.getTicketflag()) || (this
						.getTicketflag() != null
						&& castOther.getTicketflag() != null && this
						.getTicketflag().equals(castOther.getTicketflag())))
				&& ((this.getMsglen() == castOther.getMsglen()) || (this
						.getMsglen() != null
						&& castOther.getMsglen() != null && this.getMsglen()
						.equals(castOther.getMsglen())))
				&& ((this.getMsgcontent() == castOther.getMsgcontent()) || (this
						.getMsgcontent() != null
						&& castOther.getMsgcontent() != null && this
						.getMsgcontent().equals(castOther.getMsgcontent())))
				&& ((this.getDsmpflag() == castOther.getDsmpflag()) || (this
						.getDsmpflag() != null
						&& castOther.getDsmpflag() != null && this
						.getDsmpflag().equals(castOther.getDsmpflag())))
				&& ((this.getMaskflag() == castOther.getMaskflag()) || (this
						.getMaskflag() != null
						&& castOther.getMaskflag() != null && this
						.getMaskflag().equals(castOther.getMaskflag())))
				&& ((this.getDestmask() == castOther.getDestmask()) || (this
						.getDestmask() != null
						&& castOther.getDestmask() != null && this
						.getDestmask().equals(castOther.getDestmask())))
				&& ((this.getFeemask() == castOther.getFeemask()) || (this
						.getFeemask() != null
						&& castOther.getFeemask() != null && this.getFeemask()
						.equals(castOther.getFeemask())))
				&& ((this.getLinkid() == castOther.getLinkid()) || (this
						.getLinkid() != null
						&& castOther.getLinkid() != null && this.getLinkid()
						.equals(castOther.getLinkid())))
				&& ((this.getDestusertype() == castOther.getDestusertype()) || (this
						.getDestusertype() != null
						&& castOther.getDestusertype() != null && this
						.getDestusertype().equals(castOther.getDestusertype())))
				&& ((this.getPortocolversion() == castOther
						.getPortocolversion()) || (this.getPortocolversion() != null
						&& castOther.getPortocolversion() != null && this
						.getPortocolversion().equals(
								castOther.getPortocolversion())))
				&& ((this.getPktotal() == castOther.getPktotal()) || (this
						.getPktotal() != null
						&& castOther.getPktotal() != null && this.getPktotal()
						.equals(castOther.getPktotal())))
				&& ((this.getPknumber() == castOther.getPknumber()) || (this
						.getPknumber() != null
						&& castOther.getPknumber() != null && this
						.getPknumber().equals(castOther.getPknumber())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMsgid() == null ? 0 : this.getMsgid().hashCode());
		result = 37 * result
				+ (getSendtime() == null ? 0 : this.getSendtime().hashCode());
		result = 37
				* result
				+ (getSrcismgcode() == null ? 0 : this.getSrcismgcode()
						.hashCode());
		result = 37
				* result
				+ (getDestismgcode() == null ? 0 : this.getDestismgcode()
						.hashCode());
		result = 37
				* result
				+ (getSmscsequence() == null ? 0 : this.getSmscsequence()
						.hashCode());
		result = 37 * result
				+ (getSpnumber() == null ? 0 : this.getSpnumber().hashCode());
		result = 37
				* result
				+ (getServicenumber() == null ? 0 : this.getServicenumber()
						.hashCode());
		result = 37 * result
				+ (getSrcnumber() == null ? 0 : this.getSrcnumber().hashCode());
		result = 37
				* result
				+ (getDestnumber() == null ? 0 : this.getDestnumber()
						.hashCode());
		result = 37 * result
				+ (getFeenumber() == null ? 0 : this.getFeenumber().hashCode());
		result = 37 * result
				+ (getFeeflag() == null ? 0 : this.getFeeflag().hashCode());
		result = 37 * result
				+ (getFeetype() == null ? 0 : this.getFeetype().hashCode());
		result = 37 * result
				+ (getFeecode() == null ? 0 : this.getFeecode().hashCode());
		result = 37 * result
				+ (getGivenfee() == null ? 0 : this.getGivenfee().hashCode());
		result = 37 * result
				+ (getAgentflag() == null ? 0 : this.getAgentflag().hashCode());
		result = 37 * result
				+ (getMsgreason() == null ? 0 : this.getMsgreason().hashCode());
		result = 37
				* result
				+ (getServicecode() == null ? 0 : this.getServicecode()
						.hashCode());
		result = 37 * result
				+ (getMsgsrc() == null ? 0 : this.getMsgsrc().hashCode());
		result = 37 * result
				+ (getRoutemode() == null ? 0 : this.getRoutemode().hashCode());
		result = 37 * result
				+ (getSpflag() == null ? 0 : this.getSpflag().hashCode());
		result = 37
				* result
				+ (getStatusflag() == null ? 0 : this.getStatusflag()
						.hashCode());
		result = 37 * result
				+ (getUsertype() == null ? 0 : this.getUsertype().hashCode());
		result = 37
				* result
				+ (getPpusertype() == null ? 0 : this.getPpusertype()
						.hashCode());
		result = 37 * result
				+ (getAreacode() == null ? 0 : this.getAreacode().hashCode());
		result = 37
				* result
				+ (getStateareacode() == null ? 0 : this.getStateareacode()
						.hashCode());
		result = 37 * result
				+ (getSmscid() == null ? 0 : this.getSmscid().hashCode());
		result = 37
				* result
				+ (getSmscnumber() == null ? 0 : this.getSmscnumber()
						.hashCode());
		result = 37 * result
				+ (getScpid() == null ? 0 : this.getScpid().hashCode());
		result = 37 * result
				+ (getPid() == null ? 0 : this.getPid().hashCode());
		result = 37 * result
				+ (getUdhi() == null ? 0 : this.getUdhi().hashCode());
		result = 37 * result
				+ (getMsglevel() == null ? 0 : this.getMsglevel().hashCode());
		result = 37 * result
				+ (getMsgformat() == null ? 0 : this.getMsgformat().hashCode());
		result = 37 * result
				+ (getValidtime() == null ? 0 : this.getValidtime().hashCode());
		result = 37 * result
				+ (getAttime() == null ? 0 : this.getAttime().hashCode());
		result = 37
				* result
				+ (getCommittime() == null ? 0 : this.getCommittime()
						.hashCode());
		result = 37
				* result
				+ (getFinishtime() == null ? 0 : this.getFinishtime()
						.hashCode());
		result = 37 * result
				+ (getSendcount() == null ? 0 : this.getSendcount().hashCode());
		result = 37 * result
				+ (getSendflag() == null ? 0 : this.getSendflag().hashCode());
		result = 37 * result
				+ (getNetflag() == null ? 0 : this.getNetflag().hashCode());
		result = 37 * result
				+ (getErrorcode() == null ? 0 : this.getErrorcode().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getTicketstatus() == null ? 0 : this.getTicketstatus()
						.hashCode());
		result = 37
				* result
				+ (getTicketflag() == null ? 0 : this.getTicketflag()
						.hashCode());
		result = 37 * result
				+ (getMsglen() == null ? 0 : this.getMsglen().hashCode());
		result = 37
				* result
				+ (getMsgcontent() == null ? 0 : this.getMsgcontent()
						.hashCode());
		result = 37 * result
				+ (getDsmpflag() == null ? 0 : this.getDsmpflag().hashCode());
		result = 37 * result
				+ (getMaskflag() == null ? 0 : this.getMaskflag().hashCode());
		result = 37 * result
				+ (getDestmask() == null ? 0 : this.getDestmask().hashCode());
		result = 37 * result
				+ (getFeemask() == null ? 0 : this.getFeemask().hashCode());
		result = 37 * result
				+ (getLinkid() == null ? 0 : this.getLinkid().hashCode());
		result = 37
				* result
				+ (getDestusertype() == null ? 0 : this.getDestusertype()
						.hashCode());
		result = 37
				* result
				+ (getPortocolversion() == null ? 0 : this.getPortocolversion()
						.hashCode());
		result = 37 * result
				+ (getPktotal() == null ? 0 : this.getPktotal().hashCode());
		result = 37 * result
				+ (getPknumber() == null ? 0 : this.getPknumber().hashCode());
		return result;
	}

}