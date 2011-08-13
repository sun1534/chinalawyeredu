package com.sxit.info.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sxit.system.model.TsysDepartment;
import com.sxit.system.model.TsysUser;

/**
 * TinfInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TinfInfo implements java.io.Serializable {

	// Fields

	private Long infoid;
	private TinfType tinfType;
	private String infoname;
	private String filenumber;
	private String content;
	private Long statusid;	//1:草稿，2:待批，3:通过审批，4:未通过审批，0:发布，-1:冻结
	private TsysDepartment department;
	private TsysUser createuser;
	private Timestamp createtime;
	private TsysUser modifyuser;
	private Timestamp modifytime;
	private String waitid;
	private boolean isweb;
	private Set<TinfAttach> tinfAttachs = new HashSet<TinfAttach>(0);

	// Constructors

	/** default constructor */
	public TinfInfo() {
	}

	/** minimal constructor */
	public TinfInfo(Long infoid, TinfType tinfType) {
		this.infoid = infoid;
		this.tinfType = tinfType;
	}

	/** full constructor */
	public TinfInfo(Long infoid, TinfType tinfType, String infoname,
			String filenumber, String content, Long statusid,
			TsysDepartment department, TsysUser createuser, Timestamp createtime,
			TsysUser modifyuser, Timestamp modifytime, String waitid, Set tinfAttachs) {
		this.infoid = infoid;
		this.tinfType = tinfType;
		this.infoname = infoname;
		this.filenumber = filenumber;
		this.content = content;
		this.statusid = statusid;
		this.department = department;
		this.createuser = createuser;
		this.createtime = createtime;
		this.modifyuser = modifyuser;
		this.modifytime = modifytime;
		this.waitid = waitid;
		this.tinfAttachs = tinfAttachs;
	}

	// Property accessors

	public Long getInfoid() {
		return this.infoid;
	}

	public void setInfoid(Long infoid) {
		this.infoid = infoid;
	}

	public TinfType getTinfType() {
		return this.tinfType;
	}

	public void setTinfType(TinfType tinfType) {
		this.tinfType = tinfType;
	}

	public String getInfoname() {
		return this.infoname;
	}

	public void setInfoname(String infoname) {
		this.infoname = infoname;
	}

	public String getFilenumber() {
		return this.filenumber;
	}

	public void setFilenumber(String filenumber) {
		this.filenumber = filenumber;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getStatusid() {
		return this.statusid;
	}

	public void setStatusid(Long statusid) {
		this.statusid = statusid;
	}

	public TsysDepartment getDepartment() {
		return this.department;
	}

	public void setDepartment(TsysDepartment department) {
		this.department = department;
	}

	public TsysUser getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(TsysUser createuser) {
		this.createuser = createuser;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public TsysUser getModifyuser() {
		return this.modifyuser;
	}

	public void setModifyuser(TsysUser modifyuser) {
		this.modifyuser = modifyuser;
	}

	public Timestamp getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	public String getWaitid() {
		return this.waitid;
	}

	public void setWaitid(String waitid) {
		this.waitid = waitid;
	}

	public Set getTinfAttachs() {
		return this.tinfAttachs;
	}

	public void setTinfAttachs(Set tinfAttachs) {
		this.tinfAttachs = tinfAttachs;
	}

	public boolean isIsweb() {
		return isweb;
	}

	public void setIsweb(boolean isweb) {
		this.isweb = isweb;
	}

}