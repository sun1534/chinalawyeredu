package com.sxit.models.workflow;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TwflDirection entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TwflDirection implements java.io.Serializable {

	// Fields

	private Integer id;
	private TwflNode toNode;
	private TwflNode fromNode;
	private String conditions;
	private String remarks;
	private Integer createuserid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public TwflDirection() {
	}


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	

	public TwflNode getToNode() {
		return toNode;
	}


	public void setToNode(TwflNode toNode) {
		this.toNode = toNode;
	}


	public TwflNode getFromNode() {
		return fromNode;
	}


	public void setFromNode(TwflNode fromNode) {
		this.fromNode = fromNode;
	}

	public String getConditions() {
		return conditions;
	}


	public void setConditions(String conditions) {
		this.conditions = conditions;
	}


	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}