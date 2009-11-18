package com.sxit.models.workflow;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TwflDohistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TwflDohistory implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer businessid;
	private Integer nodeid;
	private Integer serviceid;
	private int simpleapprove;
	private String domessage;
	private String flowremarks;
	private String remarks;
	private Integer douserid;
	private String dousername;
	private Timestamp dotime;

	// Constructors

	/** default constructor */
	public TwflDohistory() {
	}




	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBusinessid() {
		return this.businessid;
	}

	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}

	public Integer getNodeid() {
		return this.nodeid;
	}

	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}

	public Integer getServiceid() {
		return this.serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	public int getSimpleapprove() {
		return this.simpleapprove;
	}

	public void setSimpleapprove(int simpleapprove) {
		this.simpleapprove = simpleapprove;
	}

	public String getDomessage() {
		return this.domessage;
	}

	public void setDomessage(String domessage) {
		this.domessage = domessage;
	}

	public String getFlowremarks() {
		return this.flowremarks;
	}

	public void setFlowremarks(String flowremarks) {
		this.flowremarks = flowremarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getDouserid() {
		return this.douserid;
	}

	public void setDouserid(Integer douserid) {
		this.douserid = douserid;
	}

	public String getDousername() {
		return this.dousername;
	}

	public void setDousername(String dousername) {
		this.dousername = dousername;
	}

	public Timestamp getDotime() {
		return this.dotime;
	}

	public void setDotime(Timestamp dotime) {
		this.dotime = dotime;
	}

}