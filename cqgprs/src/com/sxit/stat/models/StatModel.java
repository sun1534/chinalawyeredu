/**
 * 
 */
package com.sxit.stat.models;

import com.sxit.system.util.NumberUtil;

/**
 * @author 华锋
 * Oct 30, 2009-3:08:01 PM
 *
 */
public class StatModel {
	protected double totalStream=0.0d;

	protected long totalUser=0;

	
	
	/**
	 * 以兆为单位
	 * @return
	 */
	public String getTotalStreamStr(){
		if(totalStream<1)
			return totalStream+"（K）";
		double d = ((double) totalStream) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
//		return Float.parseFloat(totalStreamStr);
		return totalStreamStr;
	}
	
	/**
	 * @return the totalStream
	 */
	public double getTotalStream() {
//		return totalStream;
//		if(totalStream<1)
//			return totalStream+"（K）";
		double d = ((double) totalStream) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return Float.parseFloat(totalStreamStr);
		
	}

	/**
	 * @param totalStream the totalStream to set
	 */
	public void setTotalStream(double totalStream) {
		this.totalStream = totalStream;
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
	 * 得到平均流量,单位为k
	 * @return the averageStream
	 */
	public float getAverageStreamStr() {
		if(totalUser==0)
			return 0.0f;
		float f= ((float) totalStream) / (totalUser);
		String averageStreamStr= NumberUtil.toMoney(f);
		return Float.parseFloat(averageStreamStr);
	}
	
	/**
	 * 得到平均流量,单位为k
	 * @return the averageStream
	 */
	public float getAverageStream() {
		if(totalUser==0)
			return 0.0f;
		float f= ((float) totalStream) / ( totalUser);
		String averageStreamStr= NumberUtil.toMoney(f);
		return Float.parseFloat(averageStreamStr);
	}
}
