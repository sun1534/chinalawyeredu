package com.changpeng.lawcase.model;

import java.sql.Timestamp;


/**
 * 案件的评估
 * 
 * @author 
 */

public class TlawPingguInfo implements java.io.Serializable {


	private Long caseid;
	private String pinggudate;
	private double pingguzhi;
	
	private double pinggufee;
	private double kaisuofee;
	
	private String remarks;
	
	private Timestamp modifytime;
	
	private long modifyuserid;
	
	private String modifyusername;

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
	 * @return the pinggudate
	 */
	public String getPinggudate() {
		return pinggudate;
	}

	/**
	 * @param pinggudate the pinggudate to set
	 */
	public void setPinggudate(String pinggudate) {
		this.pinggudate = pinggudate;
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
	 * @return the pingguzhi
	 */
	public double getPingguzhi() {
		return pingguzhi;
	}

	/**
	 * @param pingguzhi the pingguzhi to set
	 */
	public void setPingguzhi(double pingguzhi) {
		this.pingguzhi = pingguzhi;
	}

	/**
	 * @return the pinggufee
	 */
	public double getPinggufee() {
		return pinggufee;
	}

	/**
	 * @param pinggufee the pinggufee to set
	 */
	public void setPinggufee(double pinggufee) {
		this.pinggufee = pinggufee;
	}

	/**
	 * @return the kaisuofee
	 */
	public double getKaisuofee() {
		return kaisuofee;
	}

	/**
	 * @param kaisuofee the kaisuofee to set
	 */
	public void setKaisuofee(double kaisuofee) {
		this.kaisuofee = kaisuofee;
	}

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
	
}