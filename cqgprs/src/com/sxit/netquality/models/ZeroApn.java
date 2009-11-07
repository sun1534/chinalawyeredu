/**
 * 
 */
package com.sxit.netquality.models;

import java.text.DateFormat;

/**
 * @author 华锋
 * Nov 7, 2009-10:17:31 AM
 *
 */
public class ZeroApn {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfHH = new java.text.SimpleDateFormat("HH:00");

	private String apnni;
	private long stattime;
	private float historyvolume;
	private float prehistoryvolume;
	private float allvolume;
	private int dayflag;
	
	public Apn getApn(){
		return com.sxit.netquality.service.BasicSetService.ALL_APNS.get(apnni);
	}
	
	/**
	 * @return the dayflag
	 */
	public int getDayflag() {
		return dayflag;
	}

	/**
	 * @param dayflag the dayflag to set
	 */
	public void setDayflag(int dayflag) {
		this.dayflag = dayflag;
	}

	public String getStatDate(){
		return df.format(new java.sql.Timestamp(stattime*1000));
	}
	
	public String getStatHour(){
		return dfHH.format(new java.sql.Timestamp(stattime*1000));
	}
	
	/**
	 * @return the apnni
	 */
	public String getApnni() {
		return apnni;
	}
	/**
	 * @param apnni the apnni to set
	 */
	public void setApnni(String apnni) {
		this.apnni = apnni;
	}
	/**
	 * @return the stattime
	 */
	public long getStattime() {
		return stattime;
	}
	/**
	 * @param stattime the stattime to set
	 */
	public void setStattime(long stattime) {
		this.stattime = stattime;
	}
	/**
	 * @return the historyvolume
	 */
	public float getHistoryvolume() {
		return historyvolume;
	}
	/**
	 * @param historyvolume the historyvolume to set
	 */
	public void setHistoryvolume(float historyvolume) {
		this.historyvolume = historyvolume;
	}
	/**
	 * @return the prehistoryvolume
	 */
	public float getPrehistoryvolume() {
		return prehistoryvolume;
	}
	/**
	 * @param prehistoryvolume the prehistoryvolume to set
	 */
	public void setPrehistoryvolume(float prehistoryvolume) {
		this.prehistoryvolume = prehistoryvolume;
	}
	/**
	 * @return the allvolume
	 */
	public float getAllvolume() {
		return allvolume;
	}
	/**
	 * @param allvolume the allvolume to set
	 */
	public void setAllvolume(float allvolume) {
		this.allvolume = allvolume;
	}
	
	
}