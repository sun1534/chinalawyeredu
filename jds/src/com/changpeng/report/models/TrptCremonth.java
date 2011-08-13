package com.changpeng.report.models;

import com.sxit.system.model.TsysUser;

/**
 * TrptCremonth entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TrptCremonth implements java.io.Serializable {

	// Fields

	private Long cremonthid;
	private TsysUser tsysUser;
	private Long consigntype;
	private String rptmonth;
	private Double retask;
	private Double newtask;
	private Double backtask;
	private Double curtask;
	private Double ratetask;
	private Double nexttask;
	private Double endtask;

	// Constructors

	/** default constructor */
	public TrptCremonth() {
	}

	/** minimal constructor */
	public TrptCremonth(Long cremonthid) {
		this.cremonthid = cremonthid;
	}

	/** full constructor */
	public TrptCremonth(Long cremonthid, TsysUser tsysUser, Long consigntype,
			String rptmonth, Double retask, Double newtask, Double backtask,
			Double curtask, Double ratetask, Double nexttask, Double endtask) {
		this.cremonthid = cremonthid;
		this.tsysUser = tsysUser;
		this.consigntype = consigntype;
		this.rptmonth = rptmonth;
		this.retask = retask;
		this.newtask = newtask;
		this.backtask = backtask;
		this.curtask = curtask;
		this.ratetask = ratetask;
		this.nexttask = nexttask;
		this.endtask = endtask;
	}

	// Property accessors

	public Long getCremonthid() {
		return this.cremonthid;
	}

	public void setCremonthid(Long cremonthid) {
		this.cremonthid = cremonthid;
	}

	public TsysUser getTsysUser() {
		return this.tsysUser;
	}

	public void setTsysUser(TsysUser tsysUser) {
		this.tsysUser = tsysUser;
	}

	public Long getConsigntype() {
		return this.consigntype;
	}

	public void setConsigntype(Long consigntype) {
		this.consigntype = consigntype;
	}

	public String getRptmonth() {
		return this.rptmonth;
	}

	public void setRptmonth(String rptmonth) {
		this.rptmonth = rptmonth;
	}

	public Double getRetask() {
		return this.retask;
	}

	public void setRetask(Double retask) {
		this.retask = retask;
	}

	public Double getNewtask() {
		return this.newtask;
	}

	public void setNewtask(Double newtask) {
		this.newtask = newtask;
	}

	public Double getBacktask() {
		return this.backtask;
	}

	public void setBacktask(Double backtask) {
		this.backtask = backtask;
	}

	public Double getCurtask() {
		return this.curtask;
	}

	public void setCurtask(Double curtask) {
		this.curtask = curtask;
	}

	public Double getRatetask() {
		return this.ratetask;
	}

	public void setRatetask(Double ratetask) {
		this.ratetask = ratetask;
	}

	public Double getNexttask() {
		return this.nexttask;
	}

	public void setNexttask(Double nexttask) {
		this.nexttask = nexttask;
	}

	public Double getEndtask() {
		return this.endtask;
	}

	public void setEndtask(Double endtask) {
		this.endtask = endtask;
	}

}