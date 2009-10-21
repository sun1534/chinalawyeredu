/**
 * 
 */
package com.sxit.stat.models;

import com.sxit.system.util.NumberUtil;

/**
 * @author 华锋
 * Oct 16, 2009-5:03:50 PM
 *
 */
public class TotalModel {

	private String date;
	/**
	 * 总流量,单位为M
	 */
//	private String totalStreamStr;
	private long totalStream;
	/**
	 * 网络类型,td,gsm等
	 */
	private String nettype;
	/**
	 * 总用户量
	 */
	private long totalUser;
	/**
	 * 平均流量,单位为k
	 */
//	private String averageStreamStr;
//	private float averageStream;
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
	 * @return the totalUser
	 */
	public long getTotalUser() {
		return totalUser;
	}
	/**
	 * @param totalUser the totalUser to set
	 */
	public void setTotalUser(long totalUser) {
		this.totalUser = totalUser;
	}
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
//	/**
//	 * @param averageStreamStr the averageStreamStr to set
//	 */
//	public void setAverageStreamStr(String averageStreamStr) {
//		this.averageStreamStr = averageStreamStr;
//	}
	

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
	
//	/**
//	 * @param averageStream the averageStream to set
//	 */
//	public void setAverageStream(float averageStream) {
//		this.averageStream = averageStream;
//	}

	
}
