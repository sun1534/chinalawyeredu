package com.changpeng.lawcase.model;

import java.sql.Timestamp;

/**
 * 执行立案信息
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawZhixingInfo implements java.io.Serializable {

	
	private Long caseid;
	/**
	 * 执行立案时间
	 */
	private String zhixingliandate;
	/**
	 * 执行月份
	 */
    private String zhixingmonth;
    /**
     * 执行案号
     */
    private String zhixingno;
    /**
     * 抵押物地址
     */
    private String diyawuaddress;
    
	private Timestamp modifytime;
	
	private long modifyuserid;
	
	private String modifyusername;
	private String remarks;
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the caseid
	 */
	public Long getCaseid() {
		return caseid;
	}

	/**
	 * @param caseid the caseid to set
	 */
	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}

	/**
	 * @return the zhixingliandate
	 */
	public String getZhixingliandate() {
		return zhixingliandate;
	}

	/**
	 * @param zhixingliandate the zhixingliandate to set
	 */
	public void setZhixingliandate(String zhixingliandate) {
		this.zhixingliandate = zhixingliandate;
	}

	/**
	 * @return the zhixingmonth
	 */
	public String getZhixingmonth() {
		return zhixingmonth;
	}

	/**
	 * @param zhixingmonth the zhixingmonth to set
	 */
	public void setZhixingmonth(String zhixingmonth) {
		this.zhixingmonth = zhixingmonth;
	}

	/**
	 * @return the zhixingno
	 */
	public String getZhixingno() {
		return zhixingno;
	}

	/**
	 * @param zhixingno the zhixingno to set
	 */
	public void setZhixingno(String zhixingno) {
		this.zhixingno = zhixingno;
	}

	/**
	 * @return the modifytime
	 */
	public Timestamp getModifytime() {
		return modifytime;
	}

	/**
	 * @param modifytime the modifytime to set
	 */
	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	/**
	 * @return the modifyuserid
	 */
	public long getModifyuserid() {
		return modifyuserid;
	}

	/**
	 * @param modifyuserid the modifyuserid to set
	 */
	public void setModifyuserid(long modifyuserid) {
		this.modifyuserid = modifyuserid;
	}

	/**
	 * @return the modifyusername
	 */
	public String getModifyusername() {
		return modifyusername;
	}

	/**
	 * @param modifyusername the modifyusername to set
	 */
	public void setModifyusername(String modifyusername) {
		this.modifyusername = modifyusername;
	}

	/**
	 * @return the diyawuaddress
	 */
	public String getDiyawuaddress() {
		return diyawuaddress;
	}

	/**
	 * @param diyawuaddress the diyawuaddress to set
	 */
	public void setDiyawuaddress(String diyawuaddress) {
		this.diyawuaddress = diyawuaddress;
	}
	
	
}