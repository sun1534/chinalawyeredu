/**
 * 
 */
package com.sxit.netquality.models;

import java.text.DateFormat;

/**
 * 
 * 小区信息
 * 
 * @author 华锋
 * Oct 21, 2009-10:17:45 PM
 *
 */
public class Nsvc {

	private String nsvc;
	private String nsvcindex;
	private String bscid;
	private String sgsnid;
	private String opst;
	private int capacity;
	private int updatetime;
	private int opttype;
	private int isactive;
	private static final DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public String getLastupdate(){
		return df.format(new java.sql.Timestamp(updatetime*1000L));
	}
	/**
	 * @return the nsvc
	 */
	public String getNsvc() {
		return nsvc;
	}
	/**
	 * @param nsvc the nsvc to set
	 */
	public void setNsvc(String nsvc) {
		this.nsvc = nsvc;
	}
	/**
	 * @return the nsvcindex
	 */
	public String getNsvcindex() {
		return nsvcindex;
	}
	/**
	 * @param nsvcindex the nsvcindex to set
	 */
	public void setNsvcindex(String nsvcindex) {
		this.nsvcindex = nsvcindex;
	}
	/**
	 * @return the bscid
	 */
	public String getBscid() {
		return bscid;
	}
	/**
	 * @param bscid the bscid to set
	 */
	public void setBscid(String bscid) {
		this.bscid = bscid;
	}
	/**
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return sgsnid;
	}
	/**
	 * @param sgsnid the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}
	/**
	 * @return the opst
	 */
	public String getOpst() {
		return opst;
	}
	/**
	 * @param opst the opst to set
	 */
	public void setOpst(String opst) {
		this.opst = opst;
	}
	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	/**
	 * @return the updatetime
	 */
	public int getUpdatetime() {
		return updatetime;
	}
	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(int updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * @return the opttype
	 */
	public int getOpttype() {
		return opttype;
	}
	/**
	 * @param opttype the opttype to set
	 */
	public void setOpttype(int opttype) {
		this.opttype = opttype;
	}
	/**
	 * @return the isactive
	 */
	public int getIsactive() {
		return isactive;
	}
	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	
}
