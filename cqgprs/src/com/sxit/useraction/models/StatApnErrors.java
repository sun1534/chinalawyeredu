/**
 * 
 */
package com.sxit.useraction.models;

import java.text.DateFormat;

/**
 * @author 华锋
 * Nov 8, 2009-6:15:15 PM
 *
 */
public class StatApnErrors {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dftime = new java.text.SimpleDateFormat("HH:00");

	private String apnni;
	private int usercount;
	private int errorcount;
	private String dayflag;
	private long stattime;
	private int errcode;
	
	/**
	 * @return the errcode
	 */
	public int getErrcode() {
		return errcode;
	}
	/**
	 * @param errcode the errcode to set
	 */
	public void setErrcode(int errcode) {
		this.errcode = errcode;
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
	 * @return the usercount
	 */
	public int getUsercount() {
		return usercount;
	}
	/**
	 * @param usercount the usercount to set
	 */
	public void setUsercount(int usercount) {
		this.usercount = usercount;
	}
	/**
	 * @return the errorcount
	 */
	public int getErrorcount() {
		return errorcount;
	}
	/**
	 * @param errorcount the errorcount to set
	 */
	public void setErrorcount(int errorcount) {
		this.errorcount = errorcount;
	}
	/**
	 * @return the dayflag
	 */
	public String getDayflag() {
		return dayflag;
	}
	/**
	 * @param dayflag the dayflag to set
	 */
	public void setDayflag(String dayflag) {
		this.dayflag = dayflag;
	}
	/**
	 * @param stattime the stattime to set
	 */
	public void setStattime(long stattime) {
		this.stattime = stattime;
	}
	public String getStatdate(){
		return df.format(new java.sql.Timestamp(stattime*1000));
	}
	public String getStattime(){
		return dftime.format(new java.sql.Timestamp(stattime*1000));
	}
}
