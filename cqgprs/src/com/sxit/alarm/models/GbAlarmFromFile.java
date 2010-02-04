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
public class GbAlarmFromFile {
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String gbindex;
	private String sgsnid;
	private long alarmtime;
	private String reason;
	private String communication;
	private String pcm;
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
	 * @return the alarmtime
	 */
	public long getAlarmtime() {
		return alarmtime;
	}
	/**
	 * @param alarmtime the alarmtime to set
	 */
	public void setAlarmtime(long alarmtime) {
		this.alarmtime = alarmtime;
	}
	
	public String getAlarmdate(){
		return dfyyyyMmddHHmmss.format(new java.sql.Timestamp(alarmtime*1000));
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the communication
	 */
	public String getCommunication() {
		return communication;
	}
	/**
	 * @param communication the communication to set
	 */
	public void setCommunication(String communication) {
		this.communication = communication;
	}
	/**
	 * @return the pcm
	 */
	public String getPcm() {
		return pcm;
	}
	/**
	 * @param pcm the pcm to set
	 */
	public void setPcm(String pcm) {
		this.pcm = pcm;
	}
}
