package com.changpeng.lawcase.model;

import java.sql.Timestamp;

/**
 * TlawLianinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawLianinfo implements java.io.Serializable {

	// Fields

	private Long lianid;
	private Long caseid;
	/**
	 * 标的合计
	 */
	private Double biaodiheji;
	/**
	 * 标的本金
	 */
	private Double biaodibenjin;
	/**
	 * 标的利息
	 */
	private Double biaodilixi;
	private Double lawyerfee;//律师费
	private String liandate;//立案时间
	private String feedate;//缴费时间
	private String logtime;//
	private String susongno;
	private Double susongfee;
	private Double baoquanfee;
	private String jigou;       //受理机构
	private String faguan;     //受理法官
	private String zhuli;      //助理
	private String faguanlinkphone;  //法官联系电话
	private String zhulilinkphone;   //助理联系电话
	private Long createuserid;    
	private String createusername;
	private Timestamp createtime;
	private Timestamp lastupdate; 
	private int jixiao;

	// Constructors

	/**
	 * @return the jixiao
	 */
	public int getJixiao() {
		return jixiao;
	}

	/**
	 * @param jixiao the jixiao to set
	 */
	public void setJixiao(int jixiao) {
		this.jixiao = jixiao;
	}

	/** default constructor */
	public TlawLianinfo() {
	}

	/** minimal constructor */
	public TlawLianinfo(Long caseid, Timestamp createtime, Timestamp lastupdate) {
		this.caseid = caseid;
		this.createtime = createtime;
		this.lastupdate = lastupdate;
	}

	/** full constructor */
	public TlawLianinfo(Long caseid, Double biaodiheji, Double biaodibenjin,
			Double biaodilixi, Double lawyerfee, String liandate,
			String feedate, String logtime, String susongno, Double susongfee,
			Double baoquanfee, String jigou, String faguan, Long createuserid,
			String createusername, Timestamp createtime, Timestamp lastupdate) {
		this.caseid = caseid;
		this.biaodiheji = biaodiheji;
		this.biaodibenjin = biaodibenjin;
		this.biaodilixi = biaodilixi;
		this.lawyerfee = lawyerfee;
		this.liandate = liandate;
		this.feedate = feedate;
		this.logtime = logtime;
		this.susongno = susongno;
		this.susongfee = susongfee;
		this.baoquanfee = baoquanfee;
		this.jigou = jigou;
		this.faguan = faguan;
		this.createuserid = createuserid;
		this.createusername = createusername;
		this.createtime = createtime;
		this.lastupdate = lastupdate;
	}

	// Property accessors

	public Long getLianid() {
		return this.lianid;
	}

	public void setLianid(Long lianid) {
		this.lianid = lianid;
	}

	public Long getCaseid() {
		return this.caseid;
	}

	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}

	public Double getBiaodiheji() {
		return this.biaodiheji;
	}

	public void setBiaodiheji(Double biaodiheji) {
		this.biaodiheji = biaodiheji;
	}

	public Double getBiaodibenjin() {
		return this.biaodibenjin;
	}

	public void setBiaodibenjin(Double biaodibenjin) {
		this.biaodibenjin = biaodibenjin;
	}

	public Double getBiaodilixi() {
		return this.biaodilixi;
	}

	public void setBiaodilixi(Double biaodilixi) {
		this.biaodilixi = biaodilixi;
	}

	public Double getLawyerfee() {
		return this.lawyerfee;
	}

	public void setLawyerfee(Double lawyerfee) {
		this.lawyerfee = lawyerfee;
	}

	public String getLiandate() {
		return this.liandate;
	}

	public void setLiandate(String liandate) {
		this.liandate = liandate;
	}

	public String getFeedate() {
		return this.feedate;
	}

	public void setFeedate(String feedate) {
		this.feedate = feedate;
	}

	public String getLogtime() {
		return this.logtime;
	}

	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}

	public String getSusongno() {
		return this.susongno;
	}

	public void setSusongno(String susongno) {
		this.susongno = susongno;
	}

	public Double getSusongfee() {
		return this.susongfee;
	}

	public void setSusongfee(Double susongfee) {
		this.susongfee = susongfee;
	}

	public Double getBaoquanfee() {
		return this.baoquanfee;
	}

	public void setBaoquanfee(Double baoquanfee) {
		this.baoquanfee = baoquanfee;
	}

	public String getJigou() {
		return this.jigou;
	}

	public void setJigou(String jigou) {
		this.jigou = jigou;
	}

	public String getFaguan() {
		return this.faguan;
	}

	public void setFaguan(String faguan) {
		this.faguan = faguan;
	}

	public Long getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Long createuserid) {
		this.createuserid = createuserid;
	}

	public String getCreateusername() {
		return this.createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getLastupdate() {
		return this.lastupdate;
	}

	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	/**
	 * @return the zhuli
	 */
	public String getZhuli() {
		return zhuli;
	}

	/**
	 * @param zhuli the zhuli to set
	 */
	public void setZhuli(String zhuli) {
		this.zhuli = zhuli;
	}

	/**
	 * @return the faguanlinkphone
	 */
	public String getFaguanlinkphone() {
		return faguanlinkphone;
	}

	/**
	 * @param faguanlinkphone the faguanlinkphone to set
	 */
	public void setFaguanlinkphone(String faguanlinkphone) {
		this.faguanlinkphone = faguanlinkphone;
	}

	/**
	 * @return the zhulilinkphone
	 */
	public String getZhulilinkphone() {
		return zhulilinkphone;
	}

	/**
	 * @param zhulilinkphone the zhulilinkphone to set
	 */
	public void setZhulilinkphone(String zhulilinkphone) {
		this.zhulilinkphone = zhulilinkphone;
	}

}