/**
 * 
 */
package com.sxit.stat.models;

import com.sxit.system.util.NumberUtil;

/**
 * 
 * sgsn的流量分析
 * 以及sgsn按23g的流量分析
 * 
 * @author 华锋
 * Oct 16, 2009-5:03:50 PM
 *
 */
public class CellStatModel extends StatModel {

	private String bscrncid;
private String sgsnid;
private String cellid;
private String cellname;
	
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
	 * @return the nettype
	 */
	public String getNettype() {
		if(nettype==null||nettype.equals(""))
			return "未知";
		if(nettype.equals("1")){
			return "WLAN";
		}
		else if(nettype.equals("2")){
			return "GSM";

		}
		else if(nettype.equals("3")){
			return "TD";
	    }else 
		return nettype;
	}

	/**
	 * @param nettype the nettype to set
	 */
	public void setNettype(String nettype) {
		this.nettype = nettype;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the datetime
	 */
	public String getDatetime() {
		return datetime;
	}

	/**
	 * @param datetime the datetime to set
	 */
	public void setDatetime(String datetime) {
		this.datetime = datetime;
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
	 * @return the cellname
	 */
	public String getCellname() {
		return cellname;
	}

	/**
	 * @param cellname the cellname to set
	 */
	public void setCellname(String cellname) {
		this.cellname = cellname;
	}
	
	
	
}