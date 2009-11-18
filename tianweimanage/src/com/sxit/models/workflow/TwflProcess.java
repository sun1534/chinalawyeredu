package com.sxit.models.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TwflProcess entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TwflProcess implements java.io.Serializable {

	// Fields

	private Integer processid;
	private TwflBusiness twflBusiness;
	private String processname;
	private String description;
	private Date createtime;
	private Integer statusid;
	private Integer usetype;
	private Integer xposition;
	private Integer yposition;
	private Integer newnodeid;
	private Integer authorid;
	private Set twflNodes = new HashSet(0);

	
	/** default constructor */
	public TwflProcess() {
	}

	/** full constructor */
	public TwflProcess(TwflBusiness twflBusiness, String processname, String description, Date createtime, Integer statusid, Integer usetype,
			Integer xposition, Integer yposition, Integer newnodeid, Integer authorid, Set twflNodes) {
		this.twflBusiness = twflBusiness;
		this.processname = processname;
		this.description = description;
		this.createtime = createtime;
		this.statusid = statusid;
		this.usetype = usetype;
		this.xposition = xposition;
		this.yposition = yposition;
		this.newnodeid = newnodeid;
		this.authorid = authorid;
		this.twflNodes = twflNodes;
	}

	// Property accessors

	public Integer getProcessid() {
		return this.processid;
	}

	public void setProcessid(Integer processid) {
		this.processid = processid;
	}

	public TwflBusiness getTwflBusiness() {
		return this.twflBusiness;
	}

	public void setTwflBusiness(TwflBusiness twflBusiness) {
		this.twflBusiness = twflBusiness;
	}

	public String getProcessname() {
		return this.processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getStatusid() {
		return this.statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public Integer getUsetype() {
		return this.usetype;
	}

	public void setUsetype(Integer usetype) {
		this.usetype = usetype;
	}

	public Integer getXposition() {
		return this.xposition;
	}

	public void setXposition(Integer xposition) {
		this.xposition = xposition;
	}

	public Integer getYposition() {
		return this.yposition;
	}

	public void setYposition(Integer yposition) {
		this.yposition = yposition;
	}

	public Integer getNewnodeid() {
		return this.newnodeid;
	}

	public void setNewnodeid(Integer newnodeid) {
		this.newnodeid = newnodeid;
	}

	public Integer getAuthorid() {
		return this.authorid;
	}

	public void setAuthorid(Integer authorid) {
		this.authorid = authorid;
	}

	public Set getTwflNodes() {
		return this.twflNodes;
	}

	public void setTwflNodes(Set twflNodes) {
		this.twflNodes = twflNodes;
	}

}