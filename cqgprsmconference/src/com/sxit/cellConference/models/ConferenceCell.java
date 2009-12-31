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

	private int timeview1;// TIMEVIEW NUMBER(10),每天上午的监控时间点
	private int timeview2;// TIMEVIEW NUMBER(10),每天上午的监控时间点
	private float flowalarmvalue; // FLOWALARMVALUE
									// NUMBER(10),流量告警阀值,对比昨天同一时段流量
	private float useralarmvalue; // USERALARMVALUE
									// NUMBER(10),总用户数告警阀值,对比昨天同一时段用户数

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
	public int getTimeview1() {
		return timeview1;
	}

	public String getTimeview1str() {
		int timeview1hour = timeview1 / 100;
		int timeview1min = timeview1 % 100;
		String min = timeview1min + "";
		if (timeview1min < 10)
			min = "0" + timeview1min;
		return timeview1hour + ":" + min + "-" + (timeview1hour + 1) + ":" + min;
	}

	public String getTimeview2str() {
		int timeview2hour = timeview2 / 100;
		int timeview2min = timeview2 % 100;
		String min = timeview2min + "";
		if (timeview2min < 10)
			min = "0" + timeview2min;
		return timeview2hour + ":" + min + "-" + (timeview2hour + 1) + ":" + min;
	}

	/**
	 * @param timeview
	 *            the timeview to set
	 */
	public void setTimeview1(int timeview) {
		this.timeview1 = timeview;
	}

	/**
	 * @return the flowalarmvalue
	 */
	public float getFlowalarmvalue() {
		return flowalarmvalue;
	}

	/**
	 * @param flowalarmvalue
	 *            the flowalarmvalue to set
	 */
	public void setFlowalarmvalue(float flowalarmvalue) {
		this.flowalarmvalue = flowalarmvalue;
	}

	/**
	 * @return the useralarmvalue
	 */
	public float getUseralarmvalue() {
		return useralarmvalue;
	}

	/**
	 * @param useralarmvalue
	 *            the useralarmvalue to set
	 */
	public void setUseralarmvalue(float useralarmvalue) {
		this.useralarmvalue = useralarmvalue;
	}

	/**
	 * @return the timeview2
	 */
	public int getTimeview2() {
		return timeview2;
	}

	/**
	 * @param timeview2
	 *            the timeview2 to set
	 */
	public void setTimeview2(int timeview2) {
		this.timeview2 = timeview2;
	}
}
