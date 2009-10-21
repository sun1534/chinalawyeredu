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
public class CellDayStat {

	private String bscrncid;
private String sgsnid;
private String cellid;
private String cellname;
	private long totalStream;
	private int totalUser;
	
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
	 * 以兆为单位
	 * @return
	 */
	public float getTotalStreamStr(){
		double d = ((double) totalStream) / (1024 * 1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return Float.parseFloat(totalStreamStr);
	}
	
	/**
	 * 得到平均流量,单位为k
	 * @return the averageStream
	 */
	public float getAverageStreamStr() {
		float f= ((float) totalStream) / (1024 * totalUser);
		String averageStreamStr= NumberUtil.toMoney(f);
		return Float.parseFloat(averageStreamStr);
	}
	
	/**
	 * 得到平均流量,单位为k
	 * @return the averageStream
	 */
	public float getAverageStream() {
		float f= ((float) totalStream) / (1024 * totalUser);
		String averageStreamStr= NumberUtil.toMoney(f);
		return Float.parseFloat(averageStreamStr);
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
	public long getTotalStream() {
		return totalStream;
	}

	/**
	 * @param totalStream the totalStream to set
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
	 * @return the nettype
	 */
	public String getNettype() {
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