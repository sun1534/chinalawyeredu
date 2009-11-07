/**
 * 
 */
package com.sxit.netquality.models;

import com.sxit.netquality.service.BasicSetService;

/**
 * 
 * 小区信息
 * 
 * @author 华锋
 * Oct 21, 2009-10:17:45 PM
 *
 */
public class TopCell {

	private String cellid;
	private String bscrncid;
	
	
	public String getSgsnid(){
		return BasicSetService.BSC_SGSN.get(bscrncid);
	}
	private float totalStream;
	private float currentStream;
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
	 * @return the bscrncid
	 */
	public String getBscrncid() {
		return bscrncid;
	}
	/**
	 * @param bscrncid the bscrncid to set
	 */
	public void setBscrncid(String bscrncid) {
		this.bscrncid = bscrncid;
	}
	/**
	 * @return the totalStream
	 */
	public float getTotalStream() {
		return totalStream;
	}
	/**
	 * @param totalStream the totalStream to set
	 */
	public void setTotalStream(float totalStream) {
		this.totalStream = totalStream;
	}
	/**
	 * @return the currentStream
	 */
	public float getCurrentStream() {
		return currentStream;
	}
	/**
	 * @param currentStream the currentStream to set
	 */
	public void setCurrentStream(float currentStream) {
		this.currentStream = currentStream;
	}
	

}
