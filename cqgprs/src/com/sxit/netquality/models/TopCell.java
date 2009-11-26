/**
 * 
 */
package com.sxit.netquality.models;

import com.sxit.netquality.service.BasicSetService;
import com.sxit.system.util.NumberUtil;

/**
 * 
 * 排名靠前的小区信息
 * 
 * @author 华锋
 * Oct 21, 2009-10:17:45 PM
 *
 */
public class TopCell {

	private String cellid;
	private String lac;
	private String bscid;
	
//	private String bscrncid;
	
	
	/**
	 * @return the bscid
	 */
	public String getBscid() {
		return bscid;
	}
	/**
	 * @param bscid the bscid to set
	 */
	public void setBscid(String bscid) {
		this.bscid = bscid;
	}
	public String getSgsnid(){
		return BasicSetService.BSC_SGSN.get(getBscrncid());
	}
	private double totalStream;
	private double currentStream;
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
	
	public String getCellkey(){
		if(lac==null||lac.equals(""))
			return cellid;
			return lac+"-"+cellid;
	}
	
	/**
	 * @return the bscrncid
	 */
	public String getBscrncid() {
		return BasicSetService.CELL_BSC.get(getCellkey());
	}

	/**
	 * @return the totalStream
	 */
	public double getTotalStream() {
		return totalStream;
	}
	/**
	 * @param totalStream the totalStream to set
	 */
	public void setTotalStream(double totalStream) {
		this.totalStream = totalStream;
	}
	/**
	 * @return the currentStream
	 */
	public double getCurrentStream() {
		return currentStream;
	}
	/**
	 * @param currentStream the currentStream to set
	 */
	public void setCurrentStream(double currentStream) {
		this.currentStream = currentStream;
	}
	
	public String getCurrentStreamStr(){
		if(currentStream<1)
			return currentStream+"（K）";
		double d = ((double) currentStream) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
//		return Float.parseFloat(totalStreamStr);
		return totalStreamStr;
	}
	
	public String getTotalStreamStr(){
		if(totalStream<1)
			return totalStream+"（K）";
		double d = ((double) totalStream) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
//		return Float.parseFloat(totalStreamStr);
		return totalStreamStr;
	}
	/**
	 * @return the lac
	 */
	public String getLac() {
		return lac;
	}
	/**
	 * @param lac the lac to set
	 */
	public void setLac(String lac) {
		this.lac = lac;
	}
}
