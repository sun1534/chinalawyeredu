/**
 * 
 */
package com.sxit.cellConference.models;

import java.text.DateFormat;

import com.sxit.netquality.models.Cell;

/**
 * @author 华锋 Nov 26, 2009-12:36:03 AM
 * 
 */
public class ConferenceCell {
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String cellid;
	private String lac;
	private String cellname;
	private String bscid;
	private long updatetime;
	private int isactive;

	private int timeview;// TIMEVIEW NUMBER(10),每天监控的时间点
	private int flowalarmvalue; // FLOWALARMVALUE NUMBER(10),流量告警阀值,对比昨天同一时段流量
	private int useralarmvalue; // USERALARMVALUE NUMBER(10),总用户数告警阀值,对比昨天同一时段用户数

	private int createuserid;
	private String createusername;

	public String getCellkey() {
		if (lac == null || lac.equals(""))
			return cellid;
		return lac + "-" + cellid;
	}

	public Cell getCell() {
		return com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(getCellkey());
	}

	public String getLastupdate() {
		return dfyyyyMmddHHmmss.format(new java.sql.Timestamp(updatetime * 1000));
	}

	/**
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}

	/**
	 * @param cellid
	 *            the cellid to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}

	/**
	 * @return the lac
	 */
	public String getLac() {
		return lac;
	}

	/**
	 * @param lac
	 *            the lac to set
	 */
	public void setLac(String lac) {
		this.lac = lac;
	}

	/**
	 * @return the cellname
	 */
	public String getCellname() {
		return cellname;
	}

	/**
	 * @param cellname
	 *            the cellname to set
	 */
	public void setCellname(String cellname) {
		this.cellname = cellname;
	}

	/**
	 * @return the bscid
	 */
	public String getBscid() {
		return bscid;
	}

	/**
	 * @param bscid
	 *            the bscid to set
	 */
	public void setBscid(String bscid) {
		this.bscid = bscid;
	}

	/**
	 * @return the updatetime
	 */
	public long getUpdatetime() {
		return updatetime;
	}

	/**
	 * @param updatetime
	 *            the updatetime to set
	 */
	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * @return the isactive
	 */
	public int getIsactive() {
		return isactive;
	}

	/**
	 * @param isactive
	 *            the isactive to set
	 */
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	/**
	 * @return the createuserid
	 */
	public int getCreateuserid() {
		return createuserid;
	}

	/**
	 * @param createuserid
	 *            the createuserid to set
	 */
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}

	/**
	 * @return the createusername
	 */
	public String getCreateusername() {
		return createusername;
	}

	/**
	 * @param createusername
	 *            the createusername to set
	 */
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	/**
	 * @return the timeview
	 */
	public int getTimeview() {
		return timeview;
	}

	/**
	 * @param timeview the timeview to set
	 */
	public void setTimeview(int timeview) {
		this.timeview = timeview;
	}

	/**
	 * @return the flowalarmvalue
	 */
	public int getFlowalarmvalue() {
		return flowalarmvalue;
	}

	/**
	 * @param flowalarmvalue the flowalarmvalue to set
	 */
	public void setFlowalarmvalue(int flowalarmvalue) {
		this.flowalarmvalue = flowalarmvalue;
	}

	/**
	 * @return the useralarmvalue
	 */
	public int getUseralarmvalue() {
		return useralarmvalue;
	}

	/**
	 * @param useralarmvalue the useralarmvalue to set
	 */
	public void setUseralarmvalue(int useralarmvalue) {
		this.useralarmvalue = useralarmvalue;
	}
}
