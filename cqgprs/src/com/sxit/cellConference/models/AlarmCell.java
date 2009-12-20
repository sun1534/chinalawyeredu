/**
 * 
 */
package com.sxit.cellConference.models;

import java.text.DateFormat;

import com.sxit.netquality.models.Cell;
import com.sxit.system.util.NumberUtil;

/**
 * @author 华锋 Nov 26, 2009-12:36:03 AM
 * 
 */
public class AlarmCell {
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String cellid;
	private String lac;
	private long stattime;
	private int usercount;
	private double allvolume;
	private double predayallvolume;
	private double pretimeallvolume;
	private int predayusercount;
	private int pretimeusercount;

	public String getCellkey() {
		if (lac == null || lac.equals(""))
			return cellid;
		return lac + "-" + cellid;
	}
	
	public String getAllvolumeStr(){
		if(allvolume<1)
			return allvolume+"（K）";
		double d = ((double) allvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return totalStreamStr;
	}
	
	public String getPredayallvolumeStr(){
		if(predayallvolume<1)
			return predayallvolume+"（K）";
		double d = ((double) predayallvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return totalStreamStr;
	}
	
	public String getPretimeallvolumeStr(){
		if(pretimeallvolume<1)
			return pretimeallvolume+"（K）";
		double d = ((double) pretimeallvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return totalStreamStr;
	}

	public Cell getCell() {
		return com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(getCellkey());
	}

	public String getStattimestr() {
		return dfyyyyMmddHHmmss.format(new java.sql.Timestamp(stattime * 1000));
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
	 * @return the usercount
	 */
	public int getUsercount() {
		return usercount;
	}

	/**
	 * @param usercount
	 *            the usercount to set
	 */
	public void setUsercount(int usercount) {
		this.usercount = usercount;
	}

	/**
	 * @return the allvolume
	 */
	public double getAllvolume() {
		return allvolume;
	}

	/**
	 * @param allvolume
	 *            the allvolume to set
	 */
	public void setAllvolume(double allvolume) {
		this.allvolume = allvolume;
	}

	/**
	 * @return the predayallvolume
	 */
	public double getPredayallvolume() {
		return predayallvolume;
	}

	/**
	 * @param predayallvolume the predayallvolume to set
	 */
	public void setPredayallvolume(double predayallvolume) {
		this.predayallvolume = predayallvolume;
	}

	/**
	 * @return the pretimeallvolume
	 */
	public double getPretimeallvolume() {
		return pretimeallvolume;
	}

	/**
	 * @param pretimeallvolume the pretimeallvolume to set
	 */
	public void setPretimeallvolume(double pretimeallvolume) {
		this.pretimeallvolume = pretimeallvolume;
	}

	/**
	 * @return the predayusercount
	 */
	public int getPredayusercount() {
		return predayusercount;
	}

	/**
	 * @param predayusercount the predayusercount to set
	 */
	public void setPredayusercount(int predayusercount) {
		this.predayusercount = predayusercount;
	}

	/**
	 * @return the pretimeusercount
	 */
	public int getPretimeusercount() {
		return pretimeusercount;
	}

	/**
	 * @param pretimeusercount the pretimeusercount to set
	 */
	public void setPretimeusercount(int pretimeusercount) {
		this.pretimeusercount = pretimeusercount;
	}

	

}
