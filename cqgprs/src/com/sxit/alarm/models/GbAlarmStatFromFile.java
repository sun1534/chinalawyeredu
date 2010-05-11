/**
 * 
 */
package com.sxit.alarm.models;

import java.text.DateFormat;

/**
 * @author 华锋
 * Nov 17, 2009-10:45:52 PM
 *
 */
public class GbAlarmStatFromFile {
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String gbindex;
	private String sgsnid;
	private int  stattime;
	private int alarmcount;
	private int isHistory;
	/**
	 * @return the isHistory
	 */
	public int getIsHistory() {
		return isHistory;
	}
	/**
	 * @param isHistory the isHistory to set
	 */
	public void setIsHistory(int isHistory) {
		this.isHistory = isHistory;
	}
	/**
	 * @return the gbindex
	 */
	public String getGbindex() {
		return gbindex;
	}
	/**
	 * @param gbindex the gbindex to set
	 */
	public void setGbindex(String gbindex) {
		this.gbindex = gbindex;
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
	 * @return the stattime
	 */
	public int getStattime() {
		return stattime;
	}
	/**
	 * @param stattime the stattime to set
	 */
	public void setStattime(int stattime) {
		this.stattime = stattime;
	}
	/**
	 * @return the alarmcount
	 */
	public int getAlarmcount() {
		return alarmcount;
	}
	/**
	 * @param alarmcount the alarmcount to set
	 */
	public void setAlarmcount(int alarmcount) {
		this.alarmcount = alarmcount;
	}
	
}
