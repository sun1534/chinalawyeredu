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
public class SgsnStatModel {

	private String sgsnid;
	/**
	 * 覆盖范围
	 */
	private String sgsnArea;
	private long totalStream;
	private int totalUser;
	
	/**
	 * 网络类型,td,gsm等
	 */
	private String nettype;
	private String date;
	private String datetime;
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
	 * @return the tatalStream
	 */
	public long getTotalStream() {
		return totalStream;
	}
	/**
	 * 以兆为单位
	 * @return
	 */
	public float getTotalStreamStr(){
		double d = ((double) totalStream) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return Float.parseFloat(totalStreamStr);
	}
	
	/**
	 * 得到平均流量,单位为k
	 * @return the averageStream
	 */
	public float getAverageStreamStr() {
		float f= ((float) totalStream) / ( totalUser);
		String averageStreamStr= NumberUtil.toMoney(f);
		return Float.parseFloat(averageStreamStr);
	}
	
	/**
	 * 得到平均流量,单位为k
	 * @return the averageStream
	 */
	public float getAverageStream() {
		float f= ((float) totalStream) / ( totalUser);
		String averageStreamStr= NumberUtil.toMoney(f);
		return Float.parseFloat(averageStreamStr);
	}
	
	/**
	 * @param tatalStream the tatalStream to set
	 */
	public void setTotalStream(long totalStream) {
		this.totalStream = totalStream;
	}

	/**
	 * @return the totalUser
	 */
	public int getTotalUser() {
		return totalUser;
	}
	/**
	 * @param totalUser the totalUser to set
	 */
	public void setTotalUser(int totalUser) {
		this.totalUser = totalUser;
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
}