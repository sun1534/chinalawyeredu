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
public class SgsnStatModel extends StatModel  {

	
	private boolean sgsnidtr=true;
	private boolean ggsnidtr=true;
	private int sgsnidrowspan=1;
	private int ggsnidrowspan=1;
	
	private String sgsnid;
	/**
	 * 覆盖范围
	 */
	private String sgsnArea;
	
	/**
	 * 网络类型,td,gsm等
	 */
	private String nettype;
	private String date;
	private String datetime;
	private String ggsnid;
	private String apnni;
	/**
	 * @return the ggsnid
	 */
	public String getGgsnid() {
		return ggsnid;
	}
	/**
	 * @param ggsnid the ggsnid to set
	 */
	public void setGgsnid(String ggsnid) {
		this.ggsnid = ggsnid;
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
	 * @return the sgsnArea
	 */
	public String getSgsnArea() {
		return sgsnArea;
	}
	/**
	 * @param sgsnArea the sgsnArea to set
	 */
	public void setSgsnArea(String sgsnArea) {
		this.sgsnArea = sgsnArea;
	}
	
	
	/**
	 * @param tatalStream the tatalStream to set
	 */
	public void setTotalStream(long totalStream) {
		this.totalStream = totalStream;
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
	public String getNettype() {
		if(nettype==null||nettype.equals(""))
			return "未知";
		if(nettype.equals("1")){
//			return "WLAN";
			return "TD";
		}
		else if(nettype.equals("2")){
			return "GSM";

		}
		else if(nettype.equals("3")){
			return "WLAN";
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
	 * @return the sgsnidtr
	 */
	public boolean isSgsnidtr() {
		return sgsnidtr;
	}
	/**
	 * @param sgsnidtr the sgsnidtr to set
	 */
	public void setSgsnidtr(boolean sgsnidtr) {
		this.sgsnidtr = sgsnidtr;
	}
	/**
	 * @return the ggsnidtr
	 */
	public boolean isGgsnidtr() {
		return ggsnidtr;
	}
	/**
	 * @param ggsnidtr the ggsnidtr to set
	 */
	public void setGgsnidtr(boolean ggsnidtr) {
		this.ggsnidtr = ggsnidtr;
	}
	/**
	 * @return the sgsnidrowspan
	 */
	public int getSgsnidrowspan() {
		return sgsnidrowspan;
	}
	/**
	 * @param sgsnidrowspan the sgsnidrowspan to set
	 */
	public void setSgsnidrowspan(int sgsnidrowspan) {
		this.sgsnidrowspan = sgsnidrowspan;
	}
	/**
	 * @return the ggsnidrowspan
	 */
	public int getGgsnidrowspan() {
		return ggsnidrowspan;
	}
	/**
	 * @param ggsnidrowspan the ggsnidrowspan to set
	 */
	public void setGgsnidrowspan(int ggsnidrowspan) {
		this.ggsnidrowspan = ggsnidrowspan;
	}
	

//	/**
//	 * 妈的，这里的用户数都打6折
//	 * 
//	 * @return the totalUser
//	 */
//	@Override
//	public int getTotalUser() {
//		
//		return (int)(totalUser*0.6);
//	}
}