/**
 * 
 */
package com.sxit.stat.models;

import java.text.DateFormat;

import com.sxit.netquality.models.Cell;

/**
 * 
 * sgsn的流量分析 以及sgsn按23g的流量分析
 * 
 * @author 华锋 Oct 16, 2009-5:03:50 PM
 * 
 */
public class SubCellStatModel extends StatModel {

	private static final DateFormat dfhh = new java.text.SimpleDateFormat("yyyy-MM-dd HH:00:00");

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String subsidiaryid;
	/**
	 * @return the subsidiaryid
	 */
	public String getSubsidiaryid() {
		return subsidiaryid;
	}

	/**
	 * @param subsidiaryid the subsidiaryid to set
	 */
	public void setSubsidiaryid(String subsidiaryid) {
		this.subsidiaryid = subsidiaryid;
	}

	public String getSubsidiaryname(){
		return com.sxit.netquality.service.BasicSetService.ALL_SUBS.get(subsidiaryid);
	}


	/**
	 * 网络类型,td,gsm等
	 */
	private String nettype;
	/**
	 * 某天的数据
	 */
	private String date;
	/**
	 * 某天-时的数据
	 */
	private String datetime;
	private boolean setdatetime;
	private boolean setdate;
	private long stattime;

	/**
	 * @return the stattime
	 */
	public long getStattime() {
		return stattime;
	}

	/**
	 * @param stattime
	 *            the stattime to set
	 */
	public void setStattime(long stattime) {
		this.stattime = stattime;
	}




	/**
	 * @return the nettype
	 */
	public String getNettype() {
		if (nettype == null || nettype.equals(""))
			return "未知";
		if (nettype.equals("3")) {
			return "WLAN";
		} else if (nettype.equals("2")) {
			return "GSM";

		} else if (nettype.equals("1")) {
			return "TD";
		} else
			return nettype;
	}

	/**
	 * @param nettype
	 *            the nettype to set
	 */
	public void setNettype(String nettype) {
		this.nettype = nettype;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		if (setdate)
			return date;
		return df.format(new java.sql.Timestamp(stattime * 1000));
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		setdate = true;
		this.date = date;
	}

	/**
	 * @return the datetime
	 */
	public String getDatetime() {
		if (setdatetime)
			return datetime;
		return dfhh.format(new java.sql.Timestamp(stattime * 1000));
	}

	/**
	 * @param datetime
	 *            the datetime to set
	 */
	public void setDatetime(String datetime) {
		setdatetime = true;
		this.datetime = datetime;
	}
}