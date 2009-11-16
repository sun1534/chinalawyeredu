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
public class BsnRncStatModel extends StatModel {

	private String bscrncid;
	private String sgsnid;
	
	/**
	 * 网络类型,td,gsm等
	 */
	private String nettype;
	private String date;
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

	

	public String getNettype() {
//		if(nettype==null||nettype.equals(""))
//			return "未知";
//		if(nettype.equals("1")){
//			return "WLAN";
//		}
//		else if(nettype.equals("2")){
//			return "GSM";
//
//		}
//		else if(nettype.equals("3")){
//			return "TD";
//	    }else 
		this.nettype= com.sxit.netquality.service.BasicSetService.ALL_BSCS.get(bscrncid).getNettype();
		return this.nettype;
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
		sgsnid=com.sxit.netquality.service.BasicSetService.BSC_SGSN.get(bscrncid);
		return sgsnid;
	}

	/**
	 * @param sgsnid the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}

	/**
	 * @param nettype the nettype to set
	 */
	public void setNettype(String nettype) {
		this.nettype = nettype;
	}
	
	
	
}