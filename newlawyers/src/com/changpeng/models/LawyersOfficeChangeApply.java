package com.changpeng.models;

import java.sql.Timestamp;

/**
 * LawyersOfficeChangeApply entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LawyersOfficeChangeApply implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer lawyerid=0;
	private String lawyername;
	private Integer oldprovince=0;
	private Integer oldcity=0;
	private Integer oldoffice=0;
	private Integer newprovince=0;
	private Integer newcity=0;
	private Integer newoffice=0;
	private String applyReason;
	private Timestamp applyTime;
	private String confirmContent;
	private Timestamp confirmTime;
	private int confirmuserid=0;
	private String confirmusername;
	private Short status=0;
	private String remarks;
	private String applyname;
	private Integer applyusertype;

	// Constructors

	/**
	 * @return the applyname
	 */
	public String getApplyname() {
		return applyname;
	}



	/**
	 * @param applyname the applyname to set
	 */
	public void setApplyname(String applyname) {
		this.applyname = applyname;
	}



	/**
	 * @return the applyusertype
	 */
	public int getApplyusertype() {
		return applyusertype;
	}



	/**
	 * @param applyusertype the applyusertype to set
	 */
	public void setApplyusertype(int applyusertype) {
		this.applyusertype = applyusertype;
	}



	/** default constructor */
	public LawyersOfficeChangeApply() {
	}

	

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLawyerid() {
		return this.lawyerid;
	}

	public void setLawyerid(Integer lawyerid) {
		this.lawyerid = lawyerid;
	}

	public String getLawyername() {
		return this.lawyername;
	}

	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}

	public Integer getOldprovince() {
		return this.oldprovince;
	}

	public void setOldprovince(Integer oldprovince) {
		this.oldprovince = oldprovince;
	}

	public Integer getOldcity() {
		return this.oldcity;
	}

	public void setOldcity(Integer oldcity) {
		this.oldcity = oldcity;
	}

	public Integer getOldoffice() {
		return this.oldoffice;
	}

	public void setOldoffice(Integer oldoffice) {
		this.oldoffice = oldoffice;
	}

	public Integer getNewprovince() {
		return this.newprovince;
	}

	public void setNewprovince(Integer newprovince) {
		this.newprovince = newprovince;
	}

	public Integer getNewcity() {
		return this.newcity;
	}

	public void setNewcity(Integer newcity) {
		this.newcity = newcity;
	}

	public Integer getNewoffice() {
		return this.newoffice;
	}

	public void setNewoffice(Integer newoffice) {
		this.newoffice = newoffice;
	}

	public String getApplyReason() {
		return this.applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public Timestamp getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}

	public String getConfirmContent() {
		return this.confirmContent;
	}

	public void setConfirmContent(String confirmContent) {
		this.confirmContent = confirmContent;
	}

	public Timestamp getConfirmTime() {
		return this.confirmTime;
	}

	public void setConfirmTime(Timestamp confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getStatusStr(){
		if(status==0)
			return "暂未处理";
		else if(status==1)
			return "申请通过";
		else  if(status==2)
			return "申请未通过:"+confirmContent;
		else
			return "已取消申请";
	}



	/**
	 * @return the confirmuserid
	 */
	public int getConfirmuserid() {
		return confirmuserid;
	}



	/**
	 * @param confirmuserid the confirmuserid to set
	 */
	public void setConfirmuserid(int confirmuserid) {
		this.confirmuserid = confirmuserid;
	}



	/**
	 * @return the confirmusername
	 */
	public String getConfirmusername() {
		return confirmusername;
	}



	/**
	 * @param confirmusername the confirmusername to set
	 */
	public void setConfirmusername(String confirmusername) {
		this.confirmusername = confirmusername;
	}



	/**
	 * @param applyusertype the applyusertype to set
	 */
	public void setApplyusertype(Integer applyusertype) {
		this.applyusertype = applyusertype;
	}

}