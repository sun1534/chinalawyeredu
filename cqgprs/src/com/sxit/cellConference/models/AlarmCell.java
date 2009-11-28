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
	private double preallvolume;
	private double oldpreallvolume;
	private int preusercount;
	private int oldpreusercount;

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
	
	public String getPreAllvolumeStr(){
		if(preallvolume<1)
			return preallvolume+"（K）";
		double d = ((double) preallvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return totalStreamStr;
	}
	
	public String getOldPreAllvolumeStr(){
		if(oldpreallvolume<1)
			return oldpreallvolume+"（K）";
		double d = ((double) oldpreallvolume) / (1024);
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
	 * @return the preallvolume
	 */
	public double getPreallvolume() {
		return preallvolume;
	}

	/**
	 * @param preallvolume
	 *            the preallvolume to set
	 */
	public void setPreallvolume(double preallvolume) {
		this.preallvolume = preallvolume;
	}

	/**
	 * @return the oldpreallvolume
	 */
	public double getOldpreallvolume() {
		return oldpreallvolume;
	}

	/**
	 * @param oldpreallvolume
	 *            the oldpreallvolume to set
	 */
	public void setOldpreallvolume(double oldpreallvolume) {
		this.oldpreallvolume = oldpreallvolume;
	}

	/**
	 * @return the preusercount
	 */
	public int getPreusercount() {
		return preusercount;
	}

	/**
	 * @param preusercount
	 *            the preusercount to set
	 */
	public void setPreusercount(int preusercount) {
		this.preusercount = preusercount;
	}

	/**
	 * @return the oldpreusercount
	 */
	public int getOldpreusercount() {
		return oldpreusercount;
	}

	/**
	 * @param oldpreusercount
	 *            the oldpreusercount to set
	 */
	public void setOldpreusercount(int oldpreusercount) {
		this.oldpreusercount = oldpreusercount;
	}

}
