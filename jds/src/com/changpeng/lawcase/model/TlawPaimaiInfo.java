package com.changpeng.lawcase.model;

import java.sql.Timestamp;


/**
 * 案件的拍卖信息
 * 
 * @author 
 */

public class TlawPaimaiInfo implements java.io.Serializable {


	private Long caseid;
	private String paimaidate; //拍卖日期
	private double paimaidijia;//拍卖底价
	private double chengjiaojia;//成交价
//	private double dijia;//底价
	private String remarks;
	private Timestamp modifytime;
	private int shifouhuakuan;
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
	 * @return the paimaidate
	 */
	public String getPaimaidate() {
		return paimaidate;
	}

	/**
	 * @param paimaidate the paimaidate to set
	 */
	public void setPaimaidate(String paimaidate) {
		this.paimaidate = paimaidate;
	}

	/**
	 * @return the paimaidijia
	 */
	public double getPaimaidijia() {
		return paimaidijia;
	}

	/**
	 * @param paimaidijia the paimaidijia to set
	 */
	public void setPaimaidijia(double paimaidijia) {
		this.paimaidijia = paimaidijia;
	}

	/**
	 * @return the chengjiaojia
	 */
	public double getChengjiaojia() {
		return chengjiaojia;
	}

	/**
	 * @param chengjiaojia the chengjiaojia to set
	 */
	public void setChengjiaojia(double chengjiaojia) {
		this.chengjiaojia = chengjiaojia;
	}

//	/**
//	 * @return the dijia
//	 */
//	public double getDijia() {
//		return dijia;
//	}
//
//	/**
//	 * @param dijia the dijia to set
//	 */
//	public void setDijia(double dijia) {
//		this.dijia = dijia;
//	}

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
	 * @return the shifouhuakuan
	 */
	public int getShifouhuakuan() {
		return shifouhuakuan;
	}

	/**
	 * @param shifouhuakuan the shifouhuakuan to set
	 */
	public void setShifouhuakuan(int shifouhuakuan) {
		this.shifouhuakuan = shifouhuakuan;
	}
	
	
}