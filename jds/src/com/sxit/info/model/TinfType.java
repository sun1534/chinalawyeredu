package com.sxit.info.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TinfType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TinfType implements java.io.Serializable {

	// Fields

	private Long typeid;  //1.公司新闻；2.规章制度;3.公告列表;4.文件列表
	private String name;
	private Boolean isapprove ; //是否需要审批
	private Set tinfSets = new HashSet(0);
	private Set tinfInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public TinfType() {
	}

	/** minimal constructor */
	public TinfType(Long typeid) {
		this.typeid = typeid;
	}

	/** full constructor */
	public TinfType(Long typeid, String name, Set tinfSets, Set tinfInfos) {
		this.typeid = typeid;
		this.name = name;
		this.tinfSets = tinfSets;
		this.tinfInfos = tinfInfos;
	}

	// Property accessors

	public Long getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getTinfSets() {
		return this.tinfSets;
	}

	public void setTinfSets(Set tinfSets) {
		this.tinfSets = tinfSets;
	}

	public Set getTinfInfos() {
		return this.tinfInfos;
	}

	public void setTinfInfos(Set tinfInfos) {
		this.tinfInfos = tinfInfos;
	}
	
	public Boolean getIsapprove(){
		return this.isapprove;
	}
	
	public void setIsapprove(Boolean isapprove){
		this.isapprove = isapprove;
	}

}