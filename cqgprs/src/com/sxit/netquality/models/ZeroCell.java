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
public class ZeroCell {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfHH = new java.text.SimpleDateFormat("HH:00");

	
	private String cellid;
	private long stattime;
	private float historyvolume;
	private float prehistoryvolume;
	private float allvolume;
	private int dayflag;
	
	
	public Cell getCell(){
		return com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(cellid);
	}
	
	public String getBscid(){
		return com.sxit.netquality.service.BasicSetService.CELL_BSC.get(cellid);
	}
	
	public String getSgsnid(){
		return com.sxit.netquality.service.BasicSetService.BSC_SGSN.get(getBscid());
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
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}
	/**
	 * @param cellid the cellid to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
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
