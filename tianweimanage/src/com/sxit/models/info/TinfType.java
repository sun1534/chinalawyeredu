package com.sxit.models.info;

import java.util.HashSet;
import java.util.Set;

/**
 * TinfType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TinfType implements java.io.Serializable {

	// Fields

	private int typeid;  //1.公司新闻；2.规章制度;3.公告列表;4.文件列表
	private String name;
	private Boolean isapprove ; //是否需要审批


	// Constructors

	/** default constructor */
	public TinfType() {
	}

	/** minimal constructor */
	public TinfType(int typeid) {
		this.typeid = typeid;
	}



	// Property accessors

	public int getTypeid() {
		return this.typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	public Boolean getIsapprove(){
		return this.isapprove;
	}
	
	public void setIsapprove(Boolean isapprove){
		this.isapprove = isapprove;
	}

}